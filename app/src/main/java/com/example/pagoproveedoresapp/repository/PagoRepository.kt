package com.example.pagoproveedoresapp.repository

import com.example.pagoproveedoresapp.data.model.PagoEntity
import com.example.pagoproveedoresapp.data.model.ProveedorEntity
import com.example.pagoproveedoresapp.data.model.dao.PagoDao
import com.example.pagoproveedoresapp.data.model.dao.ProveedorDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagoRepository @Inject constructor(
    private val proveedorDao: ProveedorDao,
    private val pagoDao: PagoDao
) {

    // Proveedores
    fun getAllProveedores(): Flow<List<ProveedorEntity>> = proveedorDao.getAllProveedores()

    suspend fun insertProveedor(proveedor: ProveedorEntity): Long =
        proveedorDao.insert(proveedor)

    suspend fun updateProveedor(proveedor: ProveedorEntity) =
        proveedorDao.update(proveedor)

    suspend fun deleteProveedor(proveedor: ProveedorEntity) =
        proveedorDao.delete(proveedor)

    // Pagos
    fun getPagosByProveedor(proveedorId: Int): Flow<List<PagoEntity>> =
        pagoDao.getPagosByProveedor(proveedorId)

    fun getAllPagos(): Flow<List<PagoEntity>> = pagoDao.getAllPagos()

    suspend fun insertPago(pago: PagoEntity): Long = pagoDao.insert(pago)

    suspend fun updatePago(pago: PagoEntity) = pagoDao.update(pago)

    suspend fun deletePago(pago: PagoEntity) = pagoDao.delete(pago)
}
