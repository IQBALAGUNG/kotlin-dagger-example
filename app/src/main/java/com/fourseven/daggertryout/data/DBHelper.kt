package com.fourseven.daggertryout.data

import android.content.ContentValues
import android.content.Context
import android.content.res.Resources
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.fourseven.daggertryout.data.model.User
import com.fourseven.daggertryout.di.ApplicationContext
import com.fourseven.daggertryout.di.DatabaseInfo
import java.sql.SQLException
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Created by Iqbal Agung Gunawan on 20/07/2018.
 */
@Singleton
class DBHelper @Inject constructor(@ApplicationContext context: Context, @DatabaseInfo dbName: String, @DatabaseInfo version: Int) : SQLiteOpenHelper(context, dbName, null, version) {

    companion object {
        //USER TABLE
        const val USER_TABLE_NAME = "users"
        const val USER_COLUMN_USER_ID = "id"
        const val USER_COLUMN_USER_NAME = "usr_name"
        const val USER_COLUMN_USER_ADDRESS = "usr_add"
        const val USER_COLUMN_USER_CREATED_AT = "created_at"
        const val USER_COLUMN_USER_UPDATED_AT = "updated_at"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        tableCreateStatements(db!!)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $USER_TABLE_NAME")
        onCreate(db)
    }

    private fun tableCreateStatements(db: SQLiteDatabase) {
        try {
            db.execSQL(
                    "CREATE TABLE IF NOT EXISTS " +
                            "$USER_TABLE_NAME ($USER_COLUMN_USER_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "$USER_COLUMN_USER_NAME VARCHAR(20)," +
                            "$USER_COLUMN_USER_ADDRESS VARCHAR(50)," +
                            "$USER_COLUMN_USER_CREATED_AT VARCHAR(10) DEFAULT ${getCurrentTimeStamp()}," +
                            "$USER_COLUMN_USER_UPDATED_AT VARCHAR(10) DEFAULT ${getCurrentTimeStamp()})")
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    @Throws(Resources.NotFoundException::class, NullPointerException::class)
    fun getUser(userId: Long?): User {
        var cursor: Cursor? = null
        try {
            val db = this.readableDatabase
            cursor = db.rawQuery(
                    "SELECT * FROM "
                            + USER_TABLE_NAME
                            + " WHERE "
                            + USER_COLUMN_USER_ID
                            + " = ? ",
                    arrayOf(userId.toString() + ""))

            if (cursor!!.count > 0) {
                cursor.moveToFirst()
                val user = User()
                user.id = cursor.getLong(cursor.getColumnIndex(USER_COLUMN_USER_ID))
                user.name = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_NAME))
                user.address = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_ADDRESS))
                user.createdAt = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_CREATED_AT))
                user.updatedAt = cursor.getString(cursor.getColumnIndex(USER_COLUMN_USER_UPDATED_AT))
                return user
            } else {
                throw Resources.NotFoundException("User with id $userId does not exists")
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
            throw e
        } finally {
            cursor?.close()
        }
    }

    @Throws(Exception::class)
    fun insertUser(user: User): Long {
        try {
            val db = this.readableDatabase
            val contentValues = ContentValues()
            contentValues.put(USER_COLUMN_USER_NAME, user.name)
            contentValues.put(USER_COLUMN_USER_ADDRESS, user.address)
            return db.insert(USER_TABLE_NAME, null, contentValues)
        } catch (e: Exception) {
            e.printStackTrace()
            throw e
        }
    }

    private fun getCurrentTimeStamp(): String {
        val timeStamp = System.currentTimeMillis() / 1000
        return timeStamp.toString()
    }
}
