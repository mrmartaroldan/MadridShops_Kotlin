package com.keepcoding.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.keepcoding.madridshops.repository.db.DBConstantsActivity
import com.keepcoding.madridshops.repository.db.DBConstantsShop
import com.keepcoding.madridshops.repository.db.DBHelper
import com.keepcoding.madridshops.repository.model.ActivityEntity
import com.keepcoding.madridshops.repository.model.ShopEntity

internal class ActivityDAO
(
        val dbHelper: DBHelper
): DAOPersistable<ActivityEntity>
{
    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun insert(element: ActivityEntity): Long {
        var id: Long = 0

        id = dbReadWriteConnection.insert(DBConstantsActivity.TABLE_ACTIVITY, null, contentValues(element))

        return id
    }

    fun contentValues(activityEntity: ActivityEntity): ContentValues {
        val content = ContentValues()

        content.put(DBConstantsActivity.KEY_ACTIVITY_ID_JSON, activityEntity.id)
        content.put(DBConstantsActivity.KEY_ACTIVITY_NAME , activityEntity.name)
        content.put(DBConstantsActivity.KEY_ACTIVITY_DESCRIPTION , activityEntity.description)
        content.put(DBConstantsActivity.KEY_ACTIVITY_LATITUDE , activityEntity.latitude)
        content.put(DBConstantsActivity.KEY_ACTIVITY_LONGITUDE , activityEntity.longitude)
        content.put(DBConstantsActivity.KEY_ACTIVITY_IMAGE_URL , activityEntity.img)
        content.put(DBConstantsActivity.KEY_ACTIVITY_LOGO_IMAGE_URL , activityEntity.logo)
        content.put(DBConstantsActivity.KEY_ACTIVITY_ADDRESS , activityEntity.address)
        content.put(DBConstantsActivity.KEY_ACTIVITY_OPENING_HOURS , activityEntity.openingHours)

        return content
    }

    override fun delete(element: ActivityEntity): Long {
        if (element.databaseId < 1) {
            return 0
        }

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(
                DBConstantsActivity.TABLE_ACTIVITY,
                DBConstantsActivity.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        ).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstantsActivity.TABLE_ACTIVITY,
                null,
                null
        ).toLong() >= 0
    }

    override fun query(id: Long): ActivityEntity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!
    }

    override fun query(): List<ActivityEntity> {
        val queryResult = ArrayList<ActivityEntity>()

        val cursor = dbReadOnlyConnection.query(
                DBConstantsActivity.TABLE_ACTIVITY,
                DBConstantsActivity.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstantsActivity.KEY_ACTIVITY_NAME + " ASC")

        while (cursor.moveToNext()) {
            val se = entityFromCursor(cursor)
            queryResult.add(se!!)
        }

        return queryResult
    }

    fun entityFromCursor(cursor: Cursor): ActivityEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        return ActivityEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(DBConstantsActivity.KEY_ACTIVITY_ADDRESS)))
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(DBConstantsActivity.TABLE_ACTIVITY,
                DBConstantsActivity.ALL_COLUMNS,
                DBConstantsActivity.KEY_ACTIVITY_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstantsActivity.KEY_ACTIVITY_DATABASE_ID
        )
        return cursor
    }

    override fun update(id: Long, element: ActivityEntity): Long {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(
                DBConstantsActivity.TABLE_ACTIVITY,
                contentValues(element)
                , DBConstantsActivity.KEY_ACTIVITY_DATABASE_ID + " = ?", arrayOf(id.toString()))
        return numberOfRecordsUpdated.toLong()
    }


}