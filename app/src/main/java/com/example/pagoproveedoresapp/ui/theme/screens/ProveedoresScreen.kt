package com.example.pagoproveedoresapp.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagoproveedoresapp.data.model.PagoEntity
import com.example.pagoproveedoresapp.data.model.ProveedorEntity
import com.example.pagoproveedoresapp.ui.theme.viewmodel.PagoViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ProveedoresScreen(viewModel: PagoViewModel) {
    val proveedores by viewModel.proveedores.collectAsState()
    val pagos by viewModel.pagosPorProveedor.collectAsState()

    var proveedorSeleccionado by remember { mutableStateOf<ProveedorEntity?>(null) }

    // Formulario Proveedor
    var nombre by remember { mutableStateOf("") }
    var cuit by remember { mutableStateOf("") }
    var rubro by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    // Formulario Pago
    var monto by remember { mutableStateOf("") }
    var metodo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }

    Row(modifier = Modifier.fillMaxSize().padding(8.dp)) {

        // --- Columna de proveedores ---
        Column(modifier = Modifier.weight(1f).padding(8.dp)) {
            Text("Proveedores", style = MaterialTheme.typography.titleLarge)
            Spacer(Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(proveedores) { proveedor ->
                    Text(
                        text = proveedor.nombre,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                proveedorSeleccionado = proveedor
                                nombre = proveedor.nombre
                                cuit = proveedor.cuit
                                rubro = proveedor.rubro
                                email = proveedor.email
                                viewModel.seleccionarProveedor(proveedor.id)
                            }
                            .padding(8.dp)
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            // Botón para limpiar formulario proveedor
            Button(
                onClick = {
                    proveedorSeleccionado = null
                    nombre = ""
                    cuit = ""
                    rubro = ""
                    email = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Nuevo proveedor")
            }

            Spacer(Modifier.height(8.dp))

            // Formulario proveedor
            OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = cuit, onValueChange = { cuit = it }, label = { Text("CUIT") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = rubro, onValueChange = { rubro = it }, label = { Text("Rubro") }, modifier = Modifier.fillMaxWidth())
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(8.dp))

            // --- Botones de acción proveedor ---
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Button(
                    onClick = {
                        if (proveedorSeleccionado == null) {
                            // Nuevo proveedor
                            viewModel.agregarProveedor(
                                ProveedorEntity(
                                    nombre = nombre,
                                    cuit = cuit,
                                    rubro = rubro,
                                    email = email
                                )
                            )
                        } else {
                            // Actualizar proveedor
                            viewModel.editarProveedor(
                                proveedorSeleccionado!!.copy(
                                    nombre = nombre,
                                    cuit = cuit,
                                    rubro = rubro,
                                    email = email
                                )
                            )
                        }
                        proveedorSeleccionado = null
                        nombre = ""
                        cuit = ""
                        rubro = ""
                        email = ""
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(if (proveedorSeleccionado == null) "Agregar" else "Actualizar")
                }

                if (proveedorSeleccionado != null) {
                    OutlinedButton(
                        onClick = {
                            viewModel.eliminarProveedor(proveedorSeleccionado!!)
                            proveedorSeleccionado = null
                            nombre = ""
                            cuit = ""
                            rubro = ""
                            email = ""
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = MaterialTheme.colorScheme.error
                        )
                    ) {
                        Text("Borrar")
                    }
                }
            }
        }

        // --- Columna de pagos ---
        Column(modifier = Modifier.weight(1f).padding(8.dp)) {
            Text(
                text = "Pagos de ${proveedorSeleccionado?.nombre ?: "..."}",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(8.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(pagos) { pago ->
                    PagoItem(pago)
                }
            }

            Spacer(Modifier.height(8.dp))

            if (proveedorSeleccionado != null) {
                Text("Nuevo pago", style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))

                OutlinedTextField(value = monto, onValueChange = { monto = it }, label = { Text("Monto") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = metodo, onValueChange = { metodo = it }, label = { Text("Método de pago") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = descripcion, onValueChange = { descripcion = it }, label = { Text("Descripción") }, modifier = Modifier.fillMaxWidth())

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {
                        val montoDouble = monto.toDoubleOrNull()
                        if (montoDouble != null) {
                            viewModel.agregarPago(
                                PagoEntity(
                                    proveedorId = proveedorSeleccionado!!.id,
                                    monto = montoDouble,
                                    metodoPago = metodo,
                                    fecha = System.currentTimeMillis(),
                                    descripcion = descripcion.ifBlank { null }
                                )
                            )
                            monto = ""
                            metodo = ""
                            descripcion = ""
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Agregar pago")
                }
            }
        }
    }
}

@Composable
fun PagoItem(pago: PagoEntity) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Monto: $${pago.monto}")
            Text("Método: ${pago.metodoPago}")
            Text("Fecha: ${SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(pago.fecha))}")
            pago.descripcion?.let {
                Text("Detalle: $it")
            }
        }
    }
}
