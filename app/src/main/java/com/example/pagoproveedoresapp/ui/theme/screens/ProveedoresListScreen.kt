package com.example.pagoproveedoresapp.ui.theme.screens


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagoproveedoresapp.ui.theme.viewmodel.PagoViewModel

@Composable
fun ProveedoresListScreen(
    viewModel: PagoViewModel,
    onProveedorClick: (Int) -> Unit,
    onAgregarClick: () -> Unit
) {
    val proveedores by viewModel.proveedores.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Proveedores", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(proveedores) { proveedor ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { onProveedorClick(proveedor.id) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(text = proveedor.nombre, style = MaterialTheme.typography.titleMedium)
                        Text(text = "CUIT: ${proveedor.cuit}")
                        Text(text = "Rubro: ${proveedor.rubro}")
                        Text(text = "Email: ${proveedor.email}")
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(onClick = onAgregarClick, modifier = Modifier.fillMaxWidth()) {
            Text("Agregar proveedor")
        }
    }
}
