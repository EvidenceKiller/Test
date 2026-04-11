package com.ai.duet

import android.os.Bundle
import com.adl.base.bean.Quadrilateral
import com.adl.base.config.AdlUIConfig
import com.adl.base.config.RoleConfigMulti
import com.adl.base.overlay.OnRenderScene
import com.adl.lib.AdlCamera
import com.adl.lib.AdlCameraConfig
import com.adl.lib.AdlCameraFrameProcessor
import com.adl.lib.AdlCameraPreviewInfo
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
import com.adl.ts.general.databinding.ActivityDoubleCameraBinding
import com.ai.test.BaseActivity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

/**
 * Describe   : 双人高抬腿摄像头
 * Author     : zhoumiqi
 * Date       : 2024/7/29
 */
class DoubleCamaraActivity : BaseActivity() {
    private lateinit var binding: ActivityDoubleCameraBinding

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDoubleCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        collect()
    }

    private fun initView() {
        binding.bntClose.setOnClickListener { finish() }
        binding.bntReset.setOnClickListener {
            binding.overlay.clearScreen()
            leftCount.value = 0
            rightCount.value = 0
            doubleHighLegLift.onReset()
        }
        binding.bntStart.setOnClickListener {
            binding.previewView.stopPreview()

            // 运动区域
            val list: List<Quadrilateral> = binding.overlay.previewRegion
            if (list.size == 2) {
                val lq = list[0]
                val rq = list[1]
                leftRegion = TsSportRegion(lq.p1, lq.p2, lq.p3, lq.p4)
                rightRegion = TsSportRegion(rq.p1, rq.p2, rq.p3, rq.p4)
                doubleHighLegLift.setRegions(list)
            }
            adlCamera = AdlCameraConfig.builder()
                .setCameraType(AdlCameraConfig.CameraType.CameraX)
                .setPreviewSize(1920, 1080)
                .setPreviewFps(25)
                .build()
            binding.previewView.addFrameProcessor(frameProcessor)
            binding.previewView.startPreview(adlCamera)
        }

        frameProcessor = AdlCameraFrameProcessor { yuv: ByteArray?, info: AdlCameraPreviewInfo? ->
            sportEngine.onFrame(
                yuv, info, TsTaskDoubleBody().setRegion(leftRegion, rightRegion)
            )
        }

        binding.overlay.apply {
            enableDebug(true)
            enableDrawBox(false)
            enableDrawSkeleton(true)
            // 加载页面配置文件
            val uiConfig = AdlUIConfig.instance().readConfig(
                "_double_person",
                RoleConfigMulti::class.java
            )
            setConfig(uiConfig, OnRenderScene.StatusSportSet)
            // 配置，绘制 绑定
            setListener {
                //sportEngine.open(this@DoubleCamaraActivity.config, this, DoubleDrawer())
                sportEngine.toSporting()
                sportLifecycleDouble.toSporting()
            }
        }
        initSportEngin()
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

    override fun onPause() {
        super.onPause()
        binding.previewView.stopPreview()
        sportEngine.toNone()
        sportLifecycleDouble.toIdle()
    }

    override fun onDestroy() {
        super.onDestroy()
        sportEngine.onRelease()
        binding.previewView.release()
        binding.overlay.release()
        scope.cancel()
    }
}