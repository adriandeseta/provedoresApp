package com.example.pagoproveedoresapp.data.model.dao

import androidx.room.*
import com.example.pagoproveedoresapp.data.model.ProveedorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProveedorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(proveedor: ProveedorEntity): Long

    @Query("SELECT * FROM proveedores ORDER BY nombre ASC")
    fun getAllProveedores(): Flow<List<ProveedorEntity>>

    @Update
    suspend fun update(proveedor: ProveedorEntity)

    @Delete
    suspend fun delete(proveedor: ProveedorEntity)
}
