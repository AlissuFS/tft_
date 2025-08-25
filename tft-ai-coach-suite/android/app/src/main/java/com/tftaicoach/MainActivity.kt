package com.tftaicoach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PatchUpdater.syncPatch(this)
        startService(android.content.Intent(this, OverlayService::class.java))
    }
}
