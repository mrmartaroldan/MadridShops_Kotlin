package com.keepcoding.madridshops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.keepcoding.madridshops.repository.db.DBConstantsShop
import com.keepcoding.madridshops.repository.db.DBHelper
import com.keepcoding.madridshops.repository.model.ShopEntity

internal class ShopDAO
(
        val dbHelper: DBHelper
): DAOPersistable<ShopEntity>
{
    private val dbReadOnlyConnection: SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase


    override fun insert(element: ShopEntity): Long {
        var id: Long = 0

        id = dbReadWriteConnection.insert(DBConstantsShop.TABLE_SHOP, null, contentValues(element))

        return id
    }

    fun contentValues(shopEntity: ShopEntity): ContentValues {
        val content = ContentValues()

        content.put(DBConstantsShop.KEY_SHOP_ID_JSON, shopEntity.id)
        content.put(DBConstantsShop.KEY_SHOP_NAME , shopEntity.name)
        content.put(DBConstantsShop.KEY_SHOP_DESCRIPTION , shopEntity.description)
        content.put(DBConstantsShop.KEY_SHOP_LATITUDE , shopEntity.latitude)
        content.put(DBConstantsShop.KEY_SHOP_LONGITUDE , shopEntity.longitude)
        content.put(DBConstantsShop.KEY_SHOP_IMAGE_URL , shopEntity.img)
        content.put(DBConstantsShop.KEY_SHOP_LOGO_IMAGE_URL , shopEntity.logo)
        content.put(DBConstantsShop.KEY_SHOP_ADDRESS , shopEntity.address)
        content.put(DBConstantsShop.KEY_SHOP_OPENING_HOURS , shopEntity.openingHours)

        return content
    }

    override fun delete(element: ShopEntity): Long {
        if (element.databaseId < 1) {
            return 0
        }

        return delete(element.databaseId)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(
                DBConstantsShop.TABLE_SHOP,
                DBConstantsShop.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(id.toString())
        ).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstantsShop.TABLE_SHOP,
                null,
                null
        ).toLong() >= 0
    }

    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)

        cursor.moveToFirst()

        return entityFromCursor(cursor)!!
    }

    override fun query(): List<ShopEntity> {
        val queryResult = ArrayList<ShopEntity>()

        val cursor = dbReadOnlyConnection.query(
                DBConstantsShop.TABLE_SHOP,
                DBConstantsShop.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstantsShop.KEY_SHOP_NAME + " ASC")

        while (cursor.moveToNext()) {
            val se = entityFromCursor(cursor)
            queryResult.add(se!!)
        }

        return queryResult
    }

    fun entityFromCursor(cursor: Cursor): ShopEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }

        return ShopEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_ID_JSON)),
                cursor.getLong(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_DATABASE_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(DBConstantsShop.KEY_SHOP_ADDRESS)))
    }

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(DBConstantsShop.TABLE_SHOP,
                DBConstantsShop.ALL_COLUMNS,
                DBConstantsShop.KEY_SHOP_DATABASE_ID + " = ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstantsShop.KEY_SHOP_DATABASE_ID
        )
        return cursor
    }

    override fun update(id: Long, element: ShopEntity): Long {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(
                DBConstantsShop.TABLE_SHOP,
                contentValues(element)
                , DBConstantsShop.KEY_SHOP_DATABASE_ID + " = ?", arrayOf(id.toString()))
        return numberOfRecordsUpdated.toLong()
    }


}