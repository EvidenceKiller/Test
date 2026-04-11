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

class YuvTestCameraActivity : AppCompatActivity() {
    private val TAG:String = YuvTestCameraActivity::class.java.simpleName
    private lateinit var binding: ActivityHkcameraBinding

    lateinit var dir:File
    var mNv21Width: Int = 1920
    var mNv21Height: Int = 1920

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHkcameraBinding.inflate(layoutInflater)
        setContentView(binding.root)


        dir = File("/sdcard/camera_test")
        if (!dir.exists()) {
            dir.mkdirs()
        }
//        binding.yuvPreviewView.setBuffer(null, 2560, 1440)
////        binding.nv21PreviewView.setBuffer(null, 2560, 1440)
//        binding.nv21PreviewView.setNV12(false)
//        binding.nv21PreviewView.setBuffer(null, 1920, 1080)
        binding.btnOpenCamera.setOnClickListener {

//            binding.yuvPreviewView.setYv12(true)
//            var file= File(dir, "2560x1440_yv12.yuv")
//            var file= File(dir, "2560x1440_nv12.yuv")
            var file= File(dir, "1920x1080_nv21.yuv")
            AdlLogger.e("YuvTest file path:${file.absolutePath}")
            var inputStream = FileInputStream(file)
            var readBytes = inputStream.readBytes()
            AdlLogger.e("YuvTest file readBytes:${readBytes.size}")
//            binding.yuvPreviewView.newDataArrived(readBytes)
//            binding.nv21PreviewView.newDataArrived(readBytes)
        }
        binding.btnCloseCamera.setOnClickListener {
        }
        binding.btnStartRecord.setOnClickListener {
        }
        binding.btnStopRecord.setOnClickListener {
        }
    }

    override fun onPause() {
        super.onPause()
    }
}