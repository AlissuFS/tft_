package com.tftcoach

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object PatchUpdater {
    private const val TAG = "PatchUpdater"
    private const val BACKEND_BASE = "http://10.0.2.2:8000"

    fun syncPatch(ctx: Context) {
        thread {
            try {
                val metaJson = getJson("$BACKEND_BASE/meta")
                val augments = metaJson.keys()
                val dir = File(ctx.filesDir, "augments")
                if (!dir.exists()) dir.mkdirs()
                for (a in augments) {
                    val url = metaJson.getString(a)
                    val file = File(dir, "$a.png")
                    if (!file.exists()) downloadFile(url, file)
                }
                Log.i(TAG, "Patch atualizado com sucesso")
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao atualizar patch", e)
            }
        }
    }

    private fun getJson(urlStr: String): JSONObject {
        val url = URL(urlStr)
        val conn = url.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        val data = conn.inputStream.bufferedReader().readText()
        conn.disconnect()
        return JSONObject(data)
    }

    private fun downloadFile(urlStr: String, dest: File) {
        val url = URL(urlStr)
        val conn = url.openConnection() as HttpURLConnection
        conn.connect()
        conn.inputStream.use { input ->
            dest.outputStream().use { out -> input.copyTo(out) }
        }
        conn.disconnect()
    }

    fun loadTemplatesFromCache(ctx: Context): Map<String, Bitmap> {
        val dir = File(ctx.filesDir, "augments")
        val bitmaps = mutableMapOf<String, Bitmap>()
        dir.listFiles()?.forEach { f ->
            val bmp = BitmapFactory.decodeFile(f.absolutePath)
            bitmaps[f.nameWithoutExtension] = bmp
        }
        return bitmaps
    }
}