package com.example.ilamakarov.mywater.Wawe

import android.graphics.Color
import com.example.ilamakarov.mywater.R
import me.itangqi.waveloadingview.WaveLoadingView

class WaweSettings(var mWaveLoadingView: WaveLoadingView){


    fun settingWawe( ){
        //mWaveLoadingView = findViewById(R.id.waveLoadingView) as WaveLoadingView
        mWaveLoadingView!!.setShapeType(WaveLoadingView.ShapeType.CIRCLE)
        mWaveLoadingView!!.setCenterTitleColor(Color.parseColor("#4550aa")); // 509be0
        mWaveLoadingView!!.setBottomTitleColor(Color.parseColor("#4550aa")); // 509be0
        mWaveLoadingView!!.setAmplitudeRatio(20)
        mWaveLoadingView!!.setAnimDuration(4000)
        mWaveLoadingView!!.setWaveColor(Color.parseColor("#00ADDA"));//00ADDA #85c6f9
    }



}