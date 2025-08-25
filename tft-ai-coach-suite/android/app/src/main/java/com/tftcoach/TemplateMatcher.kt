package com.tftcoach

import android.content.Context
import android.graphics.BitmapFactory
import android.util.Log
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object TemplateMatcher {

    fun recommendAugment(ctx: Context, offered: List<String>): String {
        try {
            val url = URL("http://10.0.2.2:8000/recommend")
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.doOutput = true
            val payload = JSONObject().put("augments", offered).toString()
            conn.outputStream.use { it.write(payload.toByteArray()) }
            val resp = conn.inputStream.bufferedReader().readText()
            conn.disconnect()
            val best = JSONObject(resp).getString("best")
            return best
        } catch (e: Exception) {
            Log.e("TemplateMatcher", "Erro recommend backend", e)
            return offered.firstOrNull() ?: "N/A"
        }
    }
}