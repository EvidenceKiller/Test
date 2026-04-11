package com.ai.duet

import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.SeekBar
import androidx.annotation.NonNull
import com.adl.lib.AdlCamera
import com.adl.lib.AdlCameraConfig
import com.adl.lib.AdlCameraFrameProcessor
import com.adl.lib.AdlCameraPreviewInfo
import com.adl.lib.AdlCameraPreviewView.OnViewStatusListener
import com.adl.lib.AdlCameraUsb
import com.adl.ts.general.databinding.ActivityV4l2CameraBinding
import com.adl.v4l2.camera.V4l2Param
import com.ai.test.BaseActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel


/**
 * Describe   : v4l2摄像头
 * Author     : longhailin
 * Date       : 2025/5/16
 */
class V4l2CameraActivity : BaseActivity(), Fps.FpsListener {
    companion object {
        private const val TAG = "UVCCameraActivity"
    }

    private lateinit var binding: ActivityV4l2CameraBinding

    private val scope = MainScope()
    private lateinit var adlCamera: AdlCamera
    private lateinit var frameProcessor: AdlCameraFrameProcessor
    private lateinit var fps: Fps
    protected var mNv21Width = 0
    protected var mNv21Height = 0
    private var mNv21Buffer: ByteArray? = null
    private var mNv21Processing: ByteArray? = null


    private val viewStatusListener = object : OnViewStatusListener {
        override fun onAvailable() {
        }

        override fun onCameraOpen() {
            //调整相机控制参数,比如:
            //“Brightness“亮度、”Contrast“对比度、”Saturation“饱和度、”Sharpness“锐度、”White Balance Temperature“白平衡、”Backlight Compensation“背光补偿、
            //”Gain“增益、”Power Line Frequency“频率、”White Balance Temperature, Auto“自动白平衡、“Auto-Exposure“自动曝光、”Exposure Time“曝光时间、”Zoom“变焦、”Pan“平移
            printCameraInfo()

        }

        override fun onDestroyed() {
        }

        override fun onStreamLoss() {

        }

        override fun onOpenFail() {

        }
    }

    private fun printCameraInfo() {
        Log.e(TAG,"printCameraInfo---------------")
        binding.root.postDelayed(
            {

                adlCamera.asV4l2Camera().control.brightness.let {
                    Log.e(TAG,"brightness----min:${it?.min}-max:${it?.max} ;cur:${it?.cur} ;defaultValue:${it?.defaultValue}-")
                }
                adlCamera.asV4l2Camera().control.contrast.let {
                    Log.e(TAG,"contrast----min:${it?.min}-max:${it?.max} ;cur:${it?.cur} ;defaultValue:${it?.defaultValue}")
                }
                adlCamera.asV4l2Camera().control.saturation.let {
                    Log.e(TAG,"saturation----min:${it?.min}-max:${it?.max} ;cur:${it?.cur} ;defaultValue:${it?.defaultValue}")
                }
                adlCamera.asV4l2Camera().control.gain.let {
                    Log.e(TAG,"gain----min:${it?.min}-max:${it?.max} ;cur:${it?.cur} ;defaultValue:${it?.defaultValue}")
                }
                adlCamera.asV4l2Camera().control.sharpness.let {
                    Log.e(TAG,"sharpness----min:${it?.min}-max:${it?.max} ;cur:${it?.cur} ;defaultValue:${it?.defaultValue}")
                }
//                adlCamera.asV4l2Camera().control.exposureTime.let {
//                    Log.e(TAG,"exposureTime----min:${it?.min}-max:${it?.max} ;cur:${it?.cur} ;defaultValue:${it?.defaultValue}")
//                }
            }, 1000
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityV4l2CameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        collect()
//        dev/video31   1
//        dev/video31   1
        adlCamera = AdlCameraConfig.builder()
            .setCameraType(AdlCameraConfig.CameraType.V4L2)
//            .addUsbDevice(AdlCameraUsb("1", "/dev/video31"))
            .setPreviewSize(1920, 1080)//需要根据相机支持的尺寸来设置,否则会报错
            .setPreviewEnable(true)//是否预览
            .setCallbackFps(60)//数据回调帧率
            .setCallbackDropMode(AdlCameraConfig.CallbackDropMode.NONE)//数据回调的丢帧模式,默认不丢帧,ONE表示隔一帧丢一阵,TWO表示间隔2帧丢一帧,以此类推,可选。
            .setPreviewFps(30)//预览帧率
            .build()
        fps = Fps().apply {
            setListener(this@V4l2CameraActivity)
        }


    }


    var autoExposureTime = false
    var exposureTime: V4l2Param? = null
    private fun initView() {
//        glSurfaceView = binding.previewYuv
        binding.bntClose.setOnClickListener { finish() }


        binding.bntStart.setOnClickListener {
        }
        binding.btnOpenCamera.setOnClickListener { startPreview() }
        binding.btnCloseCamera.setOnClickListener { stopPreview() }
        frameProcessor = AdlCameraFrameProcessor { yuv: ByteArray?, info: AdlCameraPreviewInfo? ->
//            Log.e(TAG, "initView: ", )
//            mNv21Width = info!!.width
//            mNv21Height = info.height
//            val imageId = info.imageId
            fps.updateFps()
        }
        binding.previewView.addFrameProcessor(frameProcessor)
        binding.sbvBrightness.setContent("亮度", true, 1,10, 6)
        binding.sbvContrast.setContent("对比度", true, 1,10, 6)
        binding.sbvSaturation.setContent("饱和度", true, 1,10, 6)
        binding.sbvSharpness.setContent("锐度", true, 1,10, 6)
        binding.sbvGain.setContent("增益", true, 1,10, 6)
        binding.sbvBrightness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
                p0?.progress?.let { adlCamera.asV4l2Camera().control.setBrightnessPercent(it) }
            }
        })

        binding.sbvContrast.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
                p0?.progress?.let { adlCamera.asV4l2Camera().control.setContrastPercent(it) }
            }
        })

        binding.sbvSaturation.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
                p0?.progress?.let { adlCamera.asV4l2Camera().control.setSaturationPercent(it) }
            }
        })

        binding.sbvGain.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
                p0?.progress?.let { adlCamera.asV4l2Camera().control.setGainPercent(it) }
            }
        })
        binding.sbvSharpness.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
                p0?.progress?.let { adlCamera.asV4l2Camera().control.setSharpnessPercent(it) }
            }
        })
        binding.sbvExposureTime.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {}
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Log.e(TAG, "onStopTrackingTouch: ${p0?.progress}")
                exposureTime?.let {
                    p0?.progress?.let { adlCamera.asV4l2Camera().control.setExposureTime(it) }
                }

            }
        })
        binding.bntAutoExposureTime.setOnClickListener {
            if (autoExposureTime){
                binding.bntAutoExposureTime.text = "自动曝光(自动)"
                adlCamera.asV4l2Camera()?.control?.autoExposure = true
                exposureTime = null
            } else {
                binding.bntAutoExposureTime.text = "自动曝光(手动)"
                adlCamera.asV4l2Camera()?.control?.autoExposure = false
                exposureTime = adlCamera.asV4l2Camera()?.control?.exposureTime
                exposureTime?.let {param->
                    binding.sbvExposureTime.setContent("曝光值", true, param.min, param.max, param.cur)
                }

            }
            autoExposureTime = !autoExposureTime
        }

        binding.btnGetPowerLineFrequency.setOnClickListener {
            var powerlineFrequencyLimit = adlCamera.asV4l2Camera()?.powerlineFrequencyLimit
            Log.e(TAG, "initView: powerlineFrequencyLimit:$powerlineFrequencyLimit")
            if (powerlineFrequencyLimit != null){
                Log.e(TAG, "initView: min:${powerlineFrequencyLimit[0]}")
                Log.e(TAG, "initView: max:${powerlineFrequencyLimit[1]}")
                Log.e(TAG, "initView: def:${powerlineFrequencyLimit[2]}")
            }
        }
        binding.btnSetPowerLineFrequency.setOnClickListener {
            var powerlineFrequency = adlCamera.asV4l2Camera()?.setPowerlineFrequency(curFrequency)
            Log.e(TAG, "initView: powerlineFrequency:$powerlineFrequency; curFrequency:$curFrequency")
            //  V4L2_CID_POWER_LINE_FREQUENCY_DISABLED = 0,
            //  V4L2_CID_POWER_LINE_FREQUENCY_50HZ = 1,
            //  V4L2_CID_POWER_LINE_FREQUENCY_60HZ = 2,
            //  V4L2_CID_POWER_LINE_FREQUENCY_AUTO = 3,
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

    private fun collect() {
    }

    private fun startPreview() {
        //如果要设置相机参数在预览方法中传递viewStatusListener，在相机开启成功的回调里去获取UVCControl，然后设置
        binding.previewView.startPreview(adlCamera, viewStatusListener)
    }

    private fun stopPreview() {
        binding.previewView.stopPreview()
        binding.previewView.release()
    }

    override fun onResume() {
        super.onResume()
        startPreview()
    }

    override fun onPause() {
        super.onPause()
        stopPreview()
    }

    override fun onStop() {
        super.onStop()
        Log.e(TAG, "onStop: ")
        mNv21Processing = null
        mNv21Buffer = null
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy: ")
        binding.previewView.release()
        fps.release()
        scope.cancel()
    }

    override fun onFps(fps: Int) {
        binding.tvFps.post {
            binding.tvFps.text = "数据回调fps:$fps"
        }
    }
}