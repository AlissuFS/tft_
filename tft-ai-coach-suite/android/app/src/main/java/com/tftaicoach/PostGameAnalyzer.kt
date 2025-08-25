package com.tftaicoach
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
object PostGameAnalyzer {
    class DBHelper(ctx: Context): SQLiteOpenHelper(ctx, "tftcoach.db", null, 1) {
        override fun onCreate(db: SQLiteDatabase) { db.execSQL("CREATE TABLE games(id INTEGER PRIMARY KEY AUTOINCREMENT, result TEXT, notes TEXT)") }
        override fun onUpgrade(db: SQLiteDatabase, old: Int, new: Int) {}
    }
    fun saveMatch(ctx: Context, result:String, notes:String) {
        val db = DBHelper(ctx).writableDatabase
        val stmt = db.compileStatement("INSERT INTO games(result, notes) VALUES(?,?)")
        stmt.bindString(1, result); stmt.bindString(2, notes); stmt.executeInsert(); db.close()
    }
}
