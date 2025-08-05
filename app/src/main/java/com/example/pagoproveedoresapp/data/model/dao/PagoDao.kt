package com.example.pagoproveedoresapp.data.model.dao

import androidx.room.*
import com.example.pagoproveedoresapp.data.model.PagoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PagoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pago: PagoEntity): Long

    @Query("SELECT * FROM pagos WHERE proveedorId = :proveedorId ORDER BY fecha DESC")
    fun getPagosByProveedor(proveedorId: Int): Flow<List<PagoEntity>>

    @Query("SELECT * FROM pagos ORDER BY fecha DESC")
    fun getAllPagos(): Flow<List<PagoEntity>>

    @Update
    suspend fun update(pago: PagoEntity)

    @Delete
    suspend fun delete(pago: PagoEntity)
}
