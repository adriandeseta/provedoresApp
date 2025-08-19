package com.example.pagoproveedoresapp.ui.theme.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagoproveedoresapp.data.model.PagoEntity
import com.example.pagoproveedoresapp.data.model.ProveedorEntity
import com.example.pagoproveedoresapp.ui.theme.viewmodel.PagoViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProveedorDetailScreen(
    viewModel: PagoViewModel,
    proveedorId: Int,
    onBack: () -> Unit
) {
    val proveedores by viewModel.proveedores.collectAsState()
    val proveedor = proveedores.find { it.id == proveedorId }

    var nombre by remember { mutableStateOf(proveedor?.nombre ?: "") }
    var cuit by remember { mutableStateOf(proveedor?.cuit ?: "") }
    var rubro by remember { mutableStateOf(proveedor?.rubro ?: "") }
    var email by remember { mutableStateOf(proveedor?.email ?: "") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = if (proveedorId == 0) "Nuevo Proveedor" else "Editar Proveedor",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = cuit, onValueChange = { cuit = it }, label = { Text("CUIT") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = rubro, onValueChange = { rubro = it }, label = { Text("Rubro") }, modifier = Modifier.fillMaxWidth())
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(16.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(
                onClick = {
                    if (proveedorId == 0) {
                        viewModel.agregarProveedor(
                            ProveedorEntity(nombre = nombre, cuit = cuit, rubro = rubro, email = email)
                        )
                    } else {
                        proveedor?.let {
                            viewModel.editarProveedor(
                                it.copy(nombre = nombre, cuit = cuit, rubro = rubro, email = email)
                            )
                        }
                    }
                    onBack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(if (proveedorId == 0) "Agregar" else "Actualizar")
            }

            if (proveedorId != 0) {
                OutlinedButton(
                    onClick = {
                        proveedor?.let { viewModel.eliminarProveedor(it) }
                        onBack()
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

        Spacer(Modifier.height(16.dp))

        Button(onClick = onBack, modifier = Modifier.fillMaxWidth()) {
            Text("Volver")
        }
    }
}
