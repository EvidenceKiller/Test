package com.ai.duet

import android.graphics.ImageFormat
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adl.base.log.AdlLogger
import com.adl.base.recorder.NsRecordAdapter
import com.adl.lib.AdlCamera
import com.adl.lib.AdlCameraConfig
import com.adl.lib.AdlCameraFrameProcessor
import com.adl.lib.AdlCameraPreviewView.OnViewStatusListener
import com.adl.ts.general.databinding.ActivityHkcameraBinding
import com.yuv.tool.YuvTool
import okhttp3.internal.closeQuietly
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class HKCameraActivity : AppCompatActivity() {
    private val TAG:String = HKCameraActivity::class.java.simpleName
    private lateinit var binding: ActivityHkcameraBinding
    lateinit var camera1: AdlCamera
    var frameProcessor1: AdlCameraFrameProcessor? = null

    lateinit var mRecorder: NsRecordAdapter
    lateinit var dir:File
    var mNv21Width: Int = 1920
    var mNv21Height: Int = 1920

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHkcameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ipAddr = "172.16.0.232"

        camera1 = AdlCameraConfig.builder()
            .setCameraType(AdlCameraConfig.CameraType.V4L2)
            .setPreviewSize(1920, 1080)//需要根据相机支持的尺寸来设置,否则会报错
            .setPreviewEnable(true)
            .setCallbackFps(60)
            .setPreviewFps(60)
//            .setConnect(ipAddr, 8000, "admin", "zy123456")
//            .setPreviewFormat(ImageFormat.NV21)
//            .setStreamType(ImageFormat.YV12)
//            .setAutoDecode(true)
            .build()

        dir = File("/sdcard/camera_test")
        if (!dir.exists()) {
            dir.mkdirs()
        }


        frameProcessor1 =
            AdlCameraFrameProcessor { yuv, info ->
                mNv21Width = info?.width?:1920
                mNv21Height = info.height?:1080

//                var inputStream = FileOutputStream(File("/sdcard/camera_test/11.yuv"))
//                inputStream.write(yuv)
//                inputStream.closeQuietly()
                mRecorder.inputData(yuv)
            }

        mRecorder = NsRecordAdapter.create(NsRecordAdapter.RecorderSource.YUV, NsRecordAdapter.YuvFormat.NV21)

        binding.btnOpenCamera.setOnClickListener {
            binding.previewView.startPreview(camera1, object :OnViewStatusListener{
                override fun onAvailable() {
                }

                override fun onCameraOpen() {
                }

                override fun onDestroyed() {
                }

                override fun onStreamLoss() {

                }

                override fun onOpenFail() {

                }

            })
            binding.previewView.addFrameProcessor(frameProcessor1)
        }
        binding.btnCloseCamera.setOnClickListener {
            binding.previewView.stopPreview()
            binding.previewView.release()
        }
        binding.btnStartRecord.setOnClickListener {
            val fileName = "brightPercent_backlightComp__" + "_${System.currentTimeMillis()}"
            startRecorder(File(dir,"${fileName}.mp4"), 30, (1.8f * 1024 * 1024).toInt())
        }
        binding.btnStopRecord.setOnClickListener {
            mRecorder.onStop()
        }
    }

    private fun startRecorder(file: File?, fps: Int, bitRate: Int) {
        if (file != null) {
            Log.d(TAG, "startRecorder: ${file?.absolutePath}")
            mRecorder.setFps(fps)
            mRecorder.onStart(file, mNv21Width, mNv21Height, bitRate)
        }
    }

    override fun onPause() {
        super.onPause()
    }
}