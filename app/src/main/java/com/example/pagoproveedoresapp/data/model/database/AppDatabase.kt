package com.example.pagoproveedoresapp.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pagoproveedoresapp.data.model.PagoEntity
import com.example.pagoproveedoresapp.data.model.ProveedorEntity
import com.example.pagoproveedoresapp.data.model.dao.PagoDao
import com.example.pagoproveedoresapp.data.model.dao.ProveedorDao

@Database(entities = [ProveedorEntity::class, PagoEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun proveedorDao(): ProveedorDao
    abstract fun pagoDao(): PagoDao
}
