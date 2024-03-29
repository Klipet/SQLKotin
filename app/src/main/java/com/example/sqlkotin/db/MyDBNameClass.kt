package com.example.sqlkotin.db

import android.provider.BaseColumns

object MyDBNameClass : BaseColumns {
    const val TABLE_NAME = "my_table"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
    const val COLUMN_NAME_IMAGE_URI = "uri"
    const val DATABASE_VERSION = 2
    const val DATABASE_NAME = "MyDB.db"

    const val CREAT_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT, $COLUMN_NAME_CONTENT TEXT, $COLUMN_NAME_IMAGE_URI TEXT)"

    const val DROP_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

}