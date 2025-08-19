package com.example.pagoproveedoresapp.ui.theme.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pagoproveedoresapp.data.model.ProveedorEntity

@Composable
fun ProveedorItem(
    proveedor: ProveedorEntity,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text("Nombre: ${proveedor.nombre}")
            Text("CUIT: ${proveedor.cuit}")
            Text("Rubro: ${proveedor.rubro}")
            Text("Email: ${proveedor.email}")
        }
    }
}
