package psychegrammer.example.onething.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import psychegrammer.example.onething.model.*
import java.sql.Date
import java.text.DateFormat

class ThingsDatabaseHandler(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        // SQL - Structured Query Language
        var CREATE_THING_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                                KEY_THING_NAME + " TEXT," +
                                KEY_THING_ASSIGNED_BY + " TEXT," +
                                KEY_THING_ASSIGNED_TO + " TEXT," +
                                KEY_THING_ASSIGNED_TIME + " LONG" + ")"

        db?.execSQL(CREATE_THING_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXIST" + TABLE_NAME)

        // Create table again
        onCreate(db)
    }

//    CRUD

    fun createThing(thing: Thing) {
        var db: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues()
        values.put(KEY_THING_NAME, thing.thingName)
        values.put(KEY_THING_ASSIGNED_BY, thing.assignedBy)
        values.put(KEY_THING_ASSIGNED_TO, thing.assignedTo)
        values.put(KEY_THING_ASSIGNED_TIME, System.currentTimeMillis())

        db.insert(TABLE_NAME, null, values)

        Log.d("DATA INSERTED", "SUCCESS")
        db.close()
    }

    fun readAThing(id: Int): Thing {
        var db: SQLiteDatabase = writableDatabase
        var cursor: Cursor = db.query(TABLE_NAME, arrayOf(KEY_ID,
            KEY_THING_NAME, KEY_THING_ASSIGNED_BY,
            KEY_THING_ASSIGNED_TO, KEY_THING_ASSIGNED_TIME),
            KEY_ID + "=?", arrayOf(id.toString()),
            null, null, null, null)

        if (cursor != null)
            cursor.moveToFirst()

        var thing = Thing()
        thing.thingName = cursor.getString(cursor.getColumnIndex(KEY_THING_NAME))
        thing.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_THING_ASSIGNED_BY))
        thing.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_THING_ASSIGNED_TO))
        thing.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_THING_ASSIGNED_TIME))


        var dateFormat: java.text.DateFormat = DateFormat.getDateInstance()
        var formattedDate = dateFormat.format(Date(cursor.getLong(cursor.getColumnIndex(KEY_THING_ASSIGNED_TIME))).time) // Aug 8 2019

        return thing
    }

    fun readThings(): ArrayList<Thing> {
        var db: SQLiteDatabase = readableDatabase
        var list: ArrayList<Thing> = ArrayList()

        var selectAll = "SELECT * FROM " + TABLE_NAME


        var cursor: Cursor = db.rawQuery(selectAll, null)

        if (cursor.moveToFirst()) {
            do {
                var thing = Thing()
                thing.thingName = cursor.getString(cursor.getColumnIndex(KEY_THING_NAME))
                thing.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_THING_ASSIGNED_BY))
                thing.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_THING_ASSIGNED_TO))
                thing.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_THING_ASSIGNED_TIME))

                list.add(thing)
            } while (cursor.moveToNext())
        }

        return list

    }


    fun updateThing(thing: Thing) {
        var db: SQLiteDatabase = writableDatabase


        var values: ContentValues = ContentValues()
        values.put(KEY_THING_NAME, thing.thingName)
        values.put(KEY_THING_ASSIGNED_BY, thing.assignedBy)
        values.put(KEY_THING_ASSIGNED_TO, thing.assignedTo)
        values.put(KEY_THING_ASSIGNED_TIME, System.currentTimeMillis())
    }
}