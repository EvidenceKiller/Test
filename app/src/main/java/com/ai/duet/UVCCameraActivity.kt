package com.ai.duet

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
//import androidx.lifecycle.lifecycleScope
import com.adl.base.bean.Quadrilateral
import com.adl.base.common.AdlHandlerThread
import com.adl.base.config.AdlUIConfig
import com.adl.base.config.RoleConfigMulti
import com.adl.base.overlay.OnRenderScene
import com.adl.base.recorder.NsYuvRecorder
import com.adl.lib.AdlCamera
import com.adl.lib.AdlCameraConfig
import com.adl.lib.AdlCameraFrameProcessor
import com.adl.lib.AdlCameraPreviewInfo
import com.adl.lib.AdlCameraPreviewView.OnViewStatusListener
import com.adl.lib.camera.uvc.NsUVCCameraSource
import com.adl.mount.core.TsModelRes
import com.adl.mount.core.TsModelResPool
import com.adl.mount.core.TsTaskDoubleBody
import com.adl.mount.impl.TsSportLifecycleDouble
import com.adl.mount.sport.TsSportEngine
import com.adl.mount.sport.TsSportRegion
import com.adl.mount.sport.TsYuvFrameRecCache
import com.adl.sport.general.dual.DoubleHighLegLift
import com.adl.sport.general.dual.DoubleHighLegLiftCallback
import com.adl.sport.general.dual.DoubleHighLegLiftConfig
import com.adl.ts.general.databinding.ActivityUvcCameraBinding
import com.ai.test.BaseActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.io.File

/**
 * Describe   : uvc摄像头
 * Author     : zhoumiqi
 * Date       : 2024/8/8
 */
class UVCCameraActivity : BaseActivity(), Fps.FpsListener {
    companion object {
        private const val TAG = "UVCCameraActivity"
    }

    private lateinit var binding: ActivityUvcCameraBinding

    private val scope = MainScope()
    private val leftCount = MutableStateFlow(0)
    private val rightCount = MutableStateFlow(0)

    private lateinit var sportEngine: TsSportEngine
    private lateinit var sportLifecycleDouble: TsSportLifecycleDouble
    private lateinit var config: DoubleHighLegLiftConfig
    private var leftRegion: TsSportRegion? = null
    private var rightRegion: TsSportRegion? = null
    private lateinit var adlCamera: AdlCamera
    private lateinit var frameProcessor: AdlCameraFrameProcessor
    private lateinit var fps: Fps
    private var mHandlerThread: AdlHandlerThread? = null
    private var mRecorder: NsYuvRecorder? = null
    protected var mNv21Width = 0
    protected var mNv21Height = 0
    private var mNv21Buffer: ByteArray? = null
    private var mNv21Processing: ByteArray? = null

    @Volatile
    private var mStartRecorder = false
    private val doubleHighLegLift = DoubleHighLegLift(object : DoubleHighLegLiftCallback() {
        override fun onLeftCompleteOnce() {
            leftCount.value += 1
        }

        override fun onRightCompleteOnce() {
            rightCount.value += 1
        }

        override fun onKneeAngle(isLeft: Boolean, leftAngle: Double, rightAngle: Double) {
            if (isLeft) {
                binding.tvLeftAngle.post {
                    binding.tvLeftAngle.text = "L:$leftAngle R:$rightAngle"
                }
            } else {
                binding.tvRightAngle.post {
                    binding.tvRightAngle.text = "L:$leftAngle R:$rightAngle"
                }
            }
        }
    })
    private val viewStatusListener = object : OnViewStatusListener {
        override fun onAvailable() {
        }

        override fun onCameraOpen() {
            //调整相机控制参数,比如:
            //“Brightness“亮度、”Contrast“对比度、”Saturation“饱和度、”Sharpness“锐度、”White Balance Temperature“白平衡、”Backlight Compensation“背光补偿、
            //”Gain“增益、”Power Line Frequency“频率、”White Balance Temperature, Auto“自动白平衡、“Auto-Exposure“自动曝光、”Exposure Time“曝光时间、”Zoom“变焦、”Pan“平移
            printCameraInfo()

            /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                binding.tvBrightnessValue.text = "$brightnessPercent"
                binding.tvContrastValue.text = "$contrast"
                binding.tvSaturationValue.text = "$saturation"
                binding.tvSharpnessValue.text = "$sharpness"
                binding.tvExposureTimeAbsoluteValue.text = "$exposureTimeAbsolute"
                binding.tvWhiteBalanceValue.text = "$whiteBalance"
                binding.tvBacklightCompValue.text = "$backlightComp"
                binding.tvGainValue.text = "$gain"
                binding.tvPowerlineFrequencyValue.text = "$powerlineFrequency"
            }*/
        }

        override fun onDestroyed() {
        }

        override fun onStreamLoss() {

        }

        override fun onOpenFail() {

        }

    }

    private fun printCameraInfo() {
        /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.let {
            Log.d(
                TAG,
                "control hash:${it.hashCode()}\r\n" +
                        "亮度:${it.brightnessPercent}%\r\n" +
                        "对比度:${it.contrast}\r\n" +
                        "饱和度:${it.saturation}\n" +
                        "锐度:${it.sharpness}\r\n" +
                        "曝光时间：${it.exposureTimeAbsolute}\r\n" +
                        "白平衡:${it.whiteBalance}\r\n" +
                        "背光补偿:${it.backlightComp}\r\n" +
                        "增益:${it.gain}\r\n" +
                        "频率:${it.powerlineFrequency}\r\n" +
                        "自动白平衡:${it.whiteBalanceAuto}\r\n" +
                        "自动曝光:${it.autoExposureMode}\r\n" +
                        "绝对变焦:${it.zoomAbsolute}\r\n" +
                        "相对变焦:${it.zoomRelative}\r\n" +
                        "绝对平移:${it.panAbsolute}\r\n" +
                        "相对平移:${it.panRelative}"
            )
        }*/
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUvcCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        uvcControlAdjust()
        collect()
        adlCamera = AdlCameraConfig.builder()
            .setCameraType(AdlCameraConfig.CameraType.UVC)
            .setPreviewSize(1920, 1080)//需要根据相机支持的尺寸来设置,否则会报错
            .setPreviewEnable(true)//是否预览
            .setCallbackFps(60)//数据回调帧率
            .setCallbackDropMode(AdlCameraConfig.CallbackDropMode.NONE)//数据回调的丢帧模式,默认不丢帧,ONE表示隔一帧丢一阵,TWO表示间隔2帧丢一帧,以此类推,可选。
            .setPreviewFps(30)//预览帧率
//                .setAutoFocus(true)//自动聚焦,可选
//                .setExposureTime(156)//曝光时间,可选
            .build()
        fps = Fps().apply {
            setListener(this@UVCCameraActivity)
        }
        mHandlerThread = AdlHandlerThread("#SportThread")
        // 视频录制
        mRecorder = NsYuvRecorder.create()
        mStartRecorder = false
    }

    private fun initView() {
        binding.bntClose.setOnClickListener { finish() }
        binding.bntReset.setOnClickListener {
            binding.overlay.clearScreen()
            leftCount.value = 0
            rightCount.value = 0
            doubleHighLegLift.onReset()
        }
        binding.seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                    brightnessPercent = progress
                    Log.d(TAG, "bright:$progress")
                }*/
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
               /* if (adlCamera.asUVCCamera()?.cameraHelper?.uvcControl == null) {
                    Toast.makeText(this@UVCCameraActivity, "请先打开相机", Toast.LENGTH_SHORT)
                        .show()
                }*/
            }
        })
        binding.bntStart.setOnClickListener {
            // 运动区域
            val list: List<Quadrilateral> = binding.overlay.previewRegion
            if (list.size == 2) {
                val lq = list[0]
                val rq = list[1]
                leftRegion = TsSportRegion(lq.p1, lq.p2, lq.p3, lq.p4)
                rightRegion = TsSportRegion(rq.p1, rq.p2, rq.p3, rq.p4)
                doubleHighLegLift.setRegions(list)
            }
        }
        binding.btnOpenCamera.setOnClickListener { startPreview() }
        binding.btnCloseCamera.setOnClickListener { stopPreview() }
        frameProcessor = AdlCameraFrameProcessor { yuv: ByteArray?, info: AdlCameraPreviewInfo? ->
//            Log.d(TAG, "onFrame:" + info?.frameTimestamp)
            mNv21Width = info!!.width
            mNv21Height = info.height
            val imageId = info.imageId
            sportEngine.onFrame(
                yuv, info, TsTaskDoubleBody().setRegion(leftRegion, rightRegion)
            )
            fps.updateFps()
            ////////////////// 异步调用视频录制 /////////////////

            ////////////////// 异步调用视频录制 /////////////////
            if (mStartRecorder && mNv21Processing == null) {
                // 录制视频
                mHandlerThread!!.post {


                    // copy data
                    if (mNv21Buffer == null || mNv21Buffer!!.size != yuv?.size) {
                        mNv21Buffer = ByteArray(yuv!!.size)
                    }
                    System.arraycopy(yuv, 0, mNv21Buffer, 0, yuv.size)
                    mNv21Processing = mNv21Buffer
                    try {
                        if (mStartRecorder) mRecorder!!.inputNv21(mNv21Processing)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    } finally {
                        mNv21Processing = null
                    }
                }
            }
        }
        binding.previewView.addFrameProcessor(frameProcessor)

        binding.overlay.apply {
            enableDebug(true)
//            enableDrawBox(false)
//            enableDrawSkeleton(true)
//            // 加载页面配置文件
//            val uiConfig = AdlUIConfig.instance().readConfig(
//                "_double_person",
//                RoleConfigMulti::class.java
//            )
//            setConfig(uiConfig, OnRenderScene.StatusSportSet)
//            // 配置，绘制 绑定
//            setListener {
//                sportEngine.open(this@UVCCameraActivity.config, this, DoubleDrawer())
//                sportEngine.toSporting()
//                sportLifecycleDouble.toSporting()
//            }
        }
        initSportEngin()

        binding.btnSetBrightness.setOnClickListener {
           /* adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                brightnessPercent = binding.etBrightness.text.toString().toInt()
                binding.tvBrightnessValue.text = "$brightnessPercent"
            }*/
        }
        binding.btnSetContrast.setOnClickListener {
            /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                contrast = binding.etContrast.text.toString().toInt()
                binding.tvContrastValue.text = "$contrast"
            }*/
        }
        binding.btnSetSaturation.setOnClickListener {
           /* adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                saturation = binding.etSaturation.text.toString().toInt()
                binding.tvSaturationValue.text = "$saturation"
            }*/
        }
        binding.btnSetSharpness.setOnClickListener {
            /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                sharpness = binding.etSharpness.text.toString().toInt()
                binding.tvSharpnessValue.text = "$sharpness"
            }*/
        }
        binding.btnSetWhiteBalance.setOnClickListener {
            /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                whiteBalance = binding.etWhiteBalance.text.toString().toInt()
                binding.tvWhiteBalanceValue.text = "$whiteBalance"
            }*/
        }
        binding.btnSetGain.setOnClickListener {
           /* adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                gain = binding.etGain.text.toString().toInt()
                binding.tvGainValue.text = "$gain"
            }*/
        }
        binding.btnSetExposureTimeAbsolute.setOnClickListener {
            /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                autoExposureMode = 1
                exposureTimeAbsolute = binding.etExposureTimeAbsolute.text.toString().toInt()
                binding.tvExposureTimeAbsoluteValue.text = "$exposureTimeAbsolute"
            }*/
        }
        binding.btnSetPowerlineFrequency.setOnClickListener {
            /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                powerlineFrequency = binding.etPowerlineFrequency.text.toString().toInt()
                binding.tvPowerlineFrequencyValue.text = "$powerlineFrequency"
            }*/
        }
        binding.btnSetBacklightComp.setOnClickListener {
            /*adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
                backlightComp = binding.etBacklightComp.text.toString().toInt()
                binding.tvBacklightCompValue.text = "$backlightComp"
            }*/
        }

        binding.btnStartRecord.setOnClickListener {
            /*val brightPercent = adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.brightnessPercent
            val contrast = adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.contrast
            val saturation = adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.saturation
            val sharpness = adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.sharpness
            val whiteBalance = adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.whiteBalance
            val gain = adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.gain
            val exposureTimeAbsolute =
                adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.exposureTimeAbsolute
            val powerlineFrequency =
                adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.powerlineFrequency
            val backlightComp = adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.backlightComp
            val fileName = "brightPercent_${brightPercent}_" +
                    "contrast_${contrast}_" +
                    "saturation_${saturation}_" +
                    "sharpness_${sharpness}_" +
                    "whiteBalance_${whiteBalance}_" +
                    "gain_${gain}_" +
                    "exposureTimeAbsolute_${exposureTimeAbsolute}_" +
                    "powerlineFrequency_${powerlineFrequency}_" +
                    "backlightComp_${backlightComp}_" +
                    "_${System.currentTimeMillis()}"
            val dir = File("/sdcard/camera_test")
            if (!dir.exists()) {
                dir.mkdirs()
            }
            startRecorder(File(dir,"${fileName}.mp4"))
            binding.btnStartRecord.isEnabled = false*/
        }
        binding.btnStopRecord.setOnClickListener {
            stopRecorder(0)
            binding.btnStartRecord.isEnabled = true
        }
        binding.btnNormal.setOnClickListener {

            adlCamera.asUVCCamera()?.configUvcModel(NsUVCCameraSource.UVCModel.Normal)
        }
        binding.btnStrongerLight.setOnClickListener {
            adlCamera.asUVCCamera()?.configUvcModel(NsUVCCameraSource.UVCModel.StrongLight)
        }
        binding.btnWeakLight.setOnClickListener {
            adlCamera.asUVCCamera()?.configUvcModel(NsUVCCameraSource.UVCModel.WeakLight)
        }

        binding.btnBackLight.setOnClickListener {
            adlCamera.asUVCCamera()?.configUvcModel(NsUVCCameraSource.UVCModel.BackLight)
        }

        binding.btnGetPowerLineFrequency.setOnClickListener {
            var powerlineFrequencyLimit = adlCamera.asUVCCamera()?.powerlineFrequencyLimit
            Log.e(TAG, "initView: powerlineFrequencyLimit:$powerlineFrequencyLimit")
            if (powerlineFrequencyLimit != null){
                Log.e(TAG, "initView: min:${powerlineFrequencyLimit[0]}")
                Log.e(TAG, "initView: max:${powerlineFrequencyLimit[1]}")
                Log.e(TAG, "initView: def:${powerlineFrequencyLimit[2]}")
            }
        }
        binding.btnSetPowerLineFrequency.setOnClickListener {
            var powerlineFrequency = adlCamera.asUVCCamera()?.setPowerlineFrequency(curFrequency)
            Log.e(TAG, "initView: powerlineFrequency:$powerlineFrequency; curFrequency:$curFrequency")
            when(curFrequency){
                0 -> binding.btnSetPowerLineFrequency.text = "DISABLED"
                1 -> binding.btnSetPowerLineFrequency.text = "50HZ"
                2 -> binding.btnSetPowerLineFrequency.text = "60HZ"
                3 -> binding.btnSetPowerLineFrequency.text = "AUTO"
            }
            curFrequency++
            if (curFrequency > 3) curFrequency = 0

        }
    }
    var curFrequency = 0

    open class MyOnSeekBarChangeListener : OnSeekBarChangeListener{
        override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        }

        override fun onStartTrackingTouch(p0: SeekBar?) {
        }

        override fun onStopTrackingTouch(p0: SeekBar?) {
        }

    }
    private fun uvcControlAdjust(){

//        binding.btnObtain.setOnClickListener {
//            var queryMaxExposureTime = adlCamera.asUVCCamera().queryExposureAbsoluteTime()
//
//            queryMaxExposureTime?.let {
//                binding.sbvExposure.setContent("曝光时间", true, queryMaxExposureTime[0],queryMaxExposureTime[1], queryMaxExposureTime[2])
//            }
//            adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.let {
//                lifecycleScope.launch {
//                    it.isExposureTimeAuto = false
//                }
//            }
//            binding.sbvBrightness.setContent("亮度", true, 1,10, 6)
//            binding.sbvContrast.setContent("对比度", true, 1,10, 6)
//            binding.sbvSaturation.setContent("饱和度", true, 1,10, 6)
//            binding.sbvSharpness.setContent("锐度", true, 1,10, 6)
//        }
//
//
//        binding.sbvSharpness.setOnSeekBarChangeListener(object : MyOnSeekBarChangeListener() {
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
//                if (binding.etSaturation.text.toString().isNullOrEmpty()){
//                    return
//                }
//                adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
//
//                }
//            }
//        })
//
//        binding.sbvExposure.setOnSeekBarChangeListener(object : MyOnSeekBarChangeListener() {
//            override fun onStopTrackingTouch(p0: SeekBar?) {
//                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
//                Log.e(TAG, "onStopTrackingTouch AA: ${p0?.progress}")
//                adlCamera.asUVCCamera()?.cameraHelper?.uvcControl?.apply {
//                    Log.e(TAG, "onStopTrackingTouch exposureTimeAbsolute: $exposureTimeAbsolute")
//                    Thread.sleep(100)
//                    exposureTimeAbsolute = p0?.progress?:0
//                    Log.e(TAG, "onStopTrackingTouch exposureTimeAbsolute: $exposureTimeAbsolute")
//                }
//            }
//        })
    }

    private fun initSportEngin() {
        config = DoubleHighLegLiftConfig().apply {
            bodyModelId = 1006 // 人框
            poseModelId = 2004 // 骨骼
            debug = true
            readVideo = true
            drawBody = true
            drawAllBody = true
            drawSkeleton = true
        }
        // 单引擎
        sportEngine = TsSportEngine.instance().apply {
            init(TsYuvFrameRecCache.pool().prepare(25), 4)
            open(config)// 配置项，必须
            TsModelResPool.loadModel(listOf(TsModelRes(config.bodyModelId, 8), TsModelRes(config.poseModelId, 8)))
            sportLifecycleDouble = TsSportLifecycleDouble(doubleHighLegLift)
            setSportLifecycle(sportLifecycleDouble)
        }
    }

    private fun collect() {
        scope.launch {
            launch {
                leftCount.map { it }.distinctUntilChanged().collectLatest {
                    binding.tvLeftCount.text = "$it"
                }
            }
            launch {
                rightCount.map { it }.distinctUntilChanged().collectLatest {
                    binding.tvRightCount.text = "$it"
                }
            }
        }
    }

    private fun startPreview() {
        // binding.previewView.startPreview(adlCamera)
        //如果要设置相机参数在预览方法中传递viewStatusListener，在相机开启成功的回调里去获取UVCControl，然后设置
        binding.previewView.startPreview(adlCamera, viewStatusListener)
    }

    private fun stopPreview() {
        binding.previewView.stopPreview()
    }

    override fun onResume() {
        super.onResume()
        startPreview()
    }

    override fun onPause() {
        super.onPause()
        stopPreview()
        sportEngine.toNone()
        sportLifecycleDouble.toIdle()
    }

    override fun onStop() {
        super.onStop()
        // 停止
        mRecorder!!.onStop()
        mStartRecorder = false
        mHandlerThread!!.quit()
        mNv21Processing = null
        mNv21Buffer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        sportEngine.onRelease()
        binding.previewView.release()
        binding.overlay.release()
        fps.release()
        scope.cancel()
    }

    override fun onFps(fps: Int) {
        binding.tvFps.post {
            binding.tvFps.text = "数据回调fps:$fps"
        }
    }

    fun startRecorder(file: File?) {
        startRecorder(file, (1.8f * 1024 * 1024).toInt())
    }

    private fun startRecorder(file: File?, bitRate: Int) {
        startRecorder(file, 30, bitRate)
    }

    private fun startRecorder(file: File?, fps: Int, bitRate: Int) {
        if (file != null) {
            mRecorder!!.onStop()
            mRecorder!!.setFps(fps)
            mRecorder!!.onStart(file, mNv21Width, mNv21Height, bitRate)
            mStartRecorder = true
        }
    }

    fun stopRecorder(delayMillis: Long) {
        stopRecorder(delayMillis, null)
    }

    fun stopRecorder(callback: Runnable?) {
        stopRecorder(0, callback)
    }

    private fun stopRecorder(delayMillis: Long, callback: Runnable?) {
        if (delayMillis == 0L) mStartRecorder = false
        mHandlerThread!!.handler.postDelayed({
            mStartRecorder = false
            mRecorder!!.onStop()
            callback?.run()
        }, delayMillis)
    }
}