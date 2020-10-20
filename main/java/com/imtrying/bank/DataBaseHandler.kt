package com.imtrying.bank

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
val DATABASENAME = "MY DATABASE"
val TABLENAME = "XUsers"
val COL_NAME = "name"
val COL_EMAIL = "email"
val COL_ID = "id"
val COL_BAL = "balance"
class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASENAME, null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable =
            "CREATE TABLE $TABLENAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,$COL_NAME VARCHAR(256), $COL_EMAIL VARCHAR(256), $COL_BAL INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //onCreate(db);
    }

    fun deleteTable() {
        val database = this.writableDatabase
        val query = "DROP TABLE IF EXISTS $TABLENAME"
        database.execSQL(query)
        database.close()
    }

    fun fundTransfer(sender: User, rec: User, transfer: Int) {
        val db = this.writableDatabase

        val senBal = sender.bal - transfer
        val recBal = rec.bal + transfer

        val senID = sender.id
        val recID = rec.id

        val cv1 = ContentValues()
        cv1.put(COL_ID, sender.id)
        cv1.put(COL_NAME, sender.name)
        cv1.put(COL_EMAIL, sender.email)
        cv1.put(COL_BAL, senBal)
        db.update(TABLENAME, cv1, "id=$senID", null)

        val cv2 = ContentValues()
        cv2.put(COL_ID, rec.id)
        cv2.put(COL_NAME, rec.name)
        cv2.put(COL_EMAIL, rec.email)
        cv2.put(COL_BAL, recBal)
        db.update(TABLENAME, cv2, "id=$recID", null)
    }

    fun findUserName(name: String): String {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLENAME WHERE $COL_NAME LIKE '%$name%'"
        val result = db.rawQuery(query, null)
        if (result != null) {
            result.moveToFirst()
            val userName = result.getString(result.getColumnIndex(COL_NAME))
            result.close()
            return userName
        }
        val user1 = "John"
        return  user1
    }
    fun findUser(name: String) : User {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLENAME WHERE $COL_NAME LIKE '%$name%'"
        val result = db.rawQuery(query, null)
        var user2 =  User()
        if (result != null) {
            result.moveToFirst()
            val userName = result.getString(result.getColumnIndex(COL_NAME))
            var userId = result.getInt(result.getColumnIndex(COL_ID))
            var userEmail = result.getString(result.getColumnIndex(COL_EMAIL))
            var userBal = result.getInt(result.getColumnIndex(COL_BAL))
            user2 = User(userName, userEmail, userBal, userId)
            result.close()
            return user2
        }
        db.close()
        return user2
    }

    fun findUser(id: Int): User {
        val db = this.writableDatabase
        val query = "SELECT * FROM $TABLENAME WHERE $COL_ID = $id"
        val result = db.rawQuery(query, null)
        var user2 =  User()
        if (result != null) {
            result.moveToFirst()
            var userId = result.getInt(result.getColumnIndex(COL_ID))
            var userName = result.getString(result.getColumnIndex(COL_NAME))
            var userEmail = result.getString(result.getColumnIndex(COL_EMAIL))
            var userBal = result.getInt(result.getColumnIndex(COL_BAL))
            user2 = User(userName, userEmail, userBal, userId)
            result.close()
            return user2
        }

        db.close()
        return user2
    }

    fun insertData(user: User) {
        val database = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, user.name)
        contentValues.put(COL_EMAIL, user.email)
        contentValues.put(COL_BAL, user.bal)
        val result = database.insert(TABLENAME, null, contentValues)
        if (result == (0).toLong()) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
        }
    }
    fun readData(): ArrayList<User> {
        val list: ArrayList<User> = ArrayList()
        val db = this.readableDatabase
        val query = "Select * from $TABLENAME"
        val result = db.rawQuery(query, null)
        if (result.moveToFirst()) {
            do {
                var userId = result.getInt(result.getColumnIndex(COL_ID))
                var userName = result.getString(result.getColumnIndex(COL_NAME))
                var userEmail = result.getString(result.getColumnIndex(COL_EMAIL))
                var userBal = result.getInt(result.getColumnIndex(COL_BAL))
                var user = User(userName, userEmail, userBal, userId)
                list.add(user)
            }
            while (result.moveToNext())
            result.close()
        }
        db.close()
        return list
    }
}