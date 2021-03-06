package com.example.officeworkerapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CacheDao {
//    @Query("select * from databaseorderitem where date=(:getDate)")
//    fun getOrderItems(getDate: String): LiveData<List<DatabaseOrderItem>>

    @Query("select * from databaseorderitem")
    fun getOrderItems(): LiveData<List<DatabaseOrderItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllOrders(vararg orders: DatabaseOrderItem)
}