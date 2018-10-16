package com.module.footballclub.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by knalb on 10/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)

class MyDatabaseOpenHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteTeam.db", null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteObject.TABLE_FAVORITE, true,
                FavoriteObject.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteObject.TEAM_ID to TEXT + UNIQUE,
                FavoriteObject.TEAM_NAME to TEXT,
                FavoriteObject.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(FavoriteObject.TABLE_FAVORITE, true)
    }

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(context.applicationContext)
            }
            return instance!!
        }
    }
}
