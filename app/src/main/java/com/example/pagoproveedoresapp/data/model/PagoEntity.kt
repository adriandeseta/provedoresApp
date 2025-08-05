package com.example.pagoproveedoresapp.data.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Index

@Entity(
    tableName = "pagos",
    foreignKeys = [
        ForeignKey(
            entity = ProveedorEntity::class,
            parentColumns = ["id"],
            childColumns = ["proveedorId"],
            onDelete = ForeignKey.CASCADE
        )
    ],  // coma aquí para separar los parámetros
    indices = [Index(value = ["proveedorId"])]  // índice correcto
)
data class PagoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val proveedorId: Int,
    val fecha: Long,
    val monto: Double,
    val metodoPago: String,
    val descripcion: String?
)
