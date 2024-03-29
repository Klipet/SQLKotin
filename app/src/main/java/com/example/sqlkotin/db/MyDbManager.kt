package com.example.sqlkotin.db


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase


class MyDbManager(context: Context) {
    val myDbHelper = MyDbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDb(){
        db = myDbHelper.writableDatabase
    }
    fun insertToDb(title: String, content: String, uri: String){
        val values = ContentValues().apply {
            put(MyDBNameClass.COLUMN_NAME_TITLE, title)
            put(MyDBNameClass.COLUMN_NAME_CONTENT, content)
            put(MyDBNameClass.COLUMN_NAME_IMAGE_URI, uri)
        }
        db?.insert(MyDBNameClass.TABLE_NAME, null, values)
    }


    @SuppressLint("Range")
    fun readDbData(): ArrayList<ListItem>{
        val dataList = ArrayList<ListItem>()
            val cursor = db?.query(MyDBNameClass.TABLE_NAME,
            null, null, null,null,null,null)
        while ( cursor?.moveToNext()!!){
            val dataTitle = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_TITLE))
            val dataContent = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_CONTENT))
            val dataUri = cursor.getString(cursor.getColumnIndex(MyDBNameClass.COLUMN_NAME_IMAGE_URI))
            var item = ListItem()
            item.title = dataTitle
            item.desc = dataContent
            item.uri = dataUri
            dataList.add(item)
        }
        cursor?.close()
        return dataList
    }
    fun closeDb(){
        myDbHelper.close()
    }

}