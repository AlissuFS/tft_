package com.tftaicoach
import android.content.Context

object CompRecommender {
    fun recommend(hp:Int, gold:Int, scout:List<String>, ctx: Context): String {
        return if (gold >= 60) "Hyper-roll Comp" else if (hp < 30) "Defensive Comp" else "Balanced Meta Comp"
    }
}
