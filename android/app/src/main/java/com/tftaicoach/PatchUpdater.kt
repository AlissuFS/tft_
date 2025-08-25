package com.tftaicoach
import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.File
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

object PatchUpdater {
    private const val TAG = "PatchUpdater"
    private const val BACKEND_META = "https://raw.githubusercontent.com/placeholder/meta-repo/main/backend/meta.json"

    fun syncPatch(ctx: Context) {
        thread {
            try {
                val meta = getJson(BACKEND_META)
                val f = File(ctx.filesDir, "meta.json")
                f.writeText(meta.toString())
                val keys = meta.keys()
                val dir = File(ctx.filesDir, "augments"); if (!dir.exists()) dir.mkdirs()
                while (keys.hasNext()) {
                    val k = keys.next()
                    val url = meta.getJSONObject(k).getString("icon_url")
                    val out = File(dir, "$k.png")
                    if (!out.exists()) downloadFile(url, out)
                }
                Log.i(TAG, "Patch sync complete")
            } catch (e: Exception) { Log.e(TAG, "Patch sync error", e) }
        }
    }
    private fun getJson(urlStr: String): JSONObject {
        val url = URL(urlStr); val conn = url.openConnection() as HttpURLConnection; conn.requestMethod = "GET"
        val data = conn.inputStream.bufferedReader().readText(); conn.disconnect(); return JSONObject(data)
    }
    private fun downloadFile(urlStr: String, dest: File) {
        val url = URL(urlStr); val conn = url.openConnection() as HttpURLConnection; conn.connect()
        conn.inputStream.use { input -> dest.outputStream().use { it.write(input.readBytes()) } }; conn.disconnect()
    }
}
