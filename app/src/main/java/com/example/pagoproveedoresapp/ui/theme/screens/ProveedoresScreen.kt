package com.example.pagoproveedoresapp.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagoproveedoresapp.ui.theme.viewmodel.PagoViewModel

@Composable
fun ProveedoresScreen(
    viewModel: PagoViewModel,
    onProveedorClick: (Int) -> Unit,
    onAgregarClick: () -> Unit
) {
    val proveedores by viewModel.proveedores.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Proveedores", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))

        if (proveedores.isEmpty()) {
            // Empty State
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "No proveedores",
                    modifier = Modifier.size(120.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = "No hay proveedores agregados",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                items(proveedores) { proveedor ->
                    ProveedorItem(
                        proveedor = proveedor,
                        onClick = { onProveedorClick(proveedor.id) }
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        // Botón de agregar proveedor siempre visible
        Button(
            onClick = onAgregarClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar proveedor")
        }
    }
}
