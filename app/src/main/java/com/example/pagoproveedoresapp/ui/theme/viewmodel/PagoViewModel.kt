package com.example.pagoproveedoresapp.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pagoproveedoresapp.data.model.PagoEntity
import com.example.pagoproveedoresapp.data.model.ProveedorEntity
import com.example.pagoproveedoresapp.repository.PagoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PagoViewModel @Inject constructor(
    private val repository: PagoRepository
) : ViewModel() {

    val proveedores: StateFlow<List<ProveedorEntity>> =
        repository.getAllProveedores()
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Estado observable de pagos por proveedor seleccionado
    private val _selectedProveedorId = kotlinx.coroutines.flow.MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val pagosPorProveedor: StateFlow<List<PagoEntity>> = _selectedProveedorId
        .flatMapLatest { proveedorId ->
            proveedorId?.let {
                repository.getPagosByProveedor(it)
            } ?: kotlinx.coroutines.flow.flowOf(emptyList())
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun seleccionarProveedor(proveedorId: Int) {
        _selectedProveedorId.value = proveedorId
    }

    // Funciones para agregar, actualizar y borrar proveedores y pagos

    fun agregarProveedor(proveedor: ProveedorEntity) = viewModelScope.launch {
        repository.insertProveedor(proveedor)
    }

    fun agregarPago(pago: PagoEntity) = viewModelScope.launch {
        repository.insertPago(pago)
    }

    // Similar para update y delete (podemos agregar si querés)

}
