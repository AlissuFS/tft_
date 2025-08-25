package com.tftaicoach

import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.File

object AugmentAnalyzer {
    fun getTierMap(ctx: Context): Map<String,String> {
        val f = File(ctx.filesDir, "meta.json")
        if (!f.exists()) return emptyMap()
        val txt = f.readText()
        val map = mutableMapOf<String,String>()
        try {
            val jo = JSONObject(txt)
            val keys = jo.keys()
            while (keys.hasNext()) {
                val k = keys.next()
                val tier = jo.getJSONObject(k).optString("tier","B")
                map[k] = tier
            }
        } catch (e: Exception) { Log.e("AugmentAnalyzer","parse error", e) }
        return map
    }
    fun rank(offered: List<String>, ctx: Context): List<Pair<String,String>> {
        val tiers = getTierMap(ctx)
        return offered.map { it to (tiers[it] ?: "C") }
    }
}
