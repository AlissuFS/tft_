package com.tftcoach

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Atualiza patch ao iniciar
        PatchUpdater.syncPatch(this)

        // Aqui inicializaria captura e overlay
    }
}