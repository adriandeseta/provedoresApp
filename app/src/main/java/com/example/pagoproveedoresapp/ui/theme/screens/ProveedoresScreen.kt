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

@Composable
fun ProveedoresScreen(viewModel: PagoViewModel) {
    val proveedores by viewModel.proveedores.collectAsState()
    val pagos by viewModel.pagosPorProveedor.collectAsState()

    var proveedorSeleccionado by remember { mutableStateOf<ProveedorEntity?>(null) }

    Row(modifier = Modifier.fillMaxSize()) {
        // Lista de Proveedores
        LazyColumn(
            modifier = Modifier.weight(1f).padding(8.dp)
        ) {
            items(proveedores) { proveedor ->
                Text(
                    text = proveedor.nombre,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            proveedorSeleccionado = proveedor
                            viewModel.seleccionarProveedor(proveedor.id)
                        }
                        .padding(8.dp)
                )
            }
        }

        // Lista de Pagos para el proveedor seleccionado
        Column(modifier = Modifier.weight(1f).padding(8.dp)) {
            Text(
                text = "Pagos de ${proveedorSeleccionado?.nombre ?: "..." }",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(Modifier.height(8.dp))
            LazyColumn {
                items(pagos) { pago ->
                    PagoItem(pago)
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
            Text("Fecha: ${java.text.SimpleDateFormat("dd/MM/yyyy").format(java.util.Date(pago.fecha))}")
            pago.descripcion?.let {
                Text("Detalle: $it")
            }
        }
    }
}
