package com.tftaicoach

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.view.LayoutInflater
import android.view.WindowManager
import android.view.Gravity
import android.graphics.PixelFormat
import android.view.View

class OverlayService : Service() {
    private var view: View? = null
    override fun onBind(intent: Intent?): IBinder? = null
    override fun onCreate() {
        super.onCreate()
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        val params = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
            PixelFormat.TRANSLUCENT)
        params.gravity = Gravity.TOP or Gravity.START
        params.x = 20; params.y = 120
        val inflater = LayoutInflater.from(this)
        view = inflater.inflate(R.layout.activity_main, null)
        wm.addView(view, params)
    }
    override fun onDestroy() {
        super.onDestroy()
        view?.let { (getSystemService(WINDOW_SERVICE) as WindowManager).removeView(it) }
    }
}
