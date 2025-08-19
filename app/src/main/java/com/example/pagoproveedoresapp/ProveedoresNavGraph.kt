package com.example.pagoproveedoresapp


import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.pagoproveedoresapp.ui.theme.screens.ProveedorDetailScreen
import com.example.pagoproveedoresapp.ui.theme.screens.ProveedoresListScreen
import com.example.pagoproveedoresapp.ui.theme.screens.ProveedoresScreen
import com.example.pagoproveedoresapp.ui.theme.viewmodel.PagoViewModel

sealed class Screens(val route: String) {
    object Proveedores : Screens("proveedores")
    object ProveedorDetail : Screens("proveedor_detail/{proveedorId}") {
        fun createRoute(proveedorId: Int) = "proveedor_detail/$proveedorId"
    }
}

@Composable
fun ProveedoresNavGraph(viewModel: PagoViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.Proveedores.route
    ) {
        // Lista de proveedores
        composable(Screens.Proveedores.route) {
            ProveedoresScreen(
                viewModel = viewModel,
                onProveedorClick = { proveedorId ->
                    navController.navigate(Screens.ProveedorDetail.createRoute(proveedorId))
                },
                onAgregarClick = {
                    navController.navigate(Screens.ProveedorDetail.createRoute(0)) // 0 = nuevo proveedor
                }
            )
        }

        // Detalle de proveedor
        composable(
            route = Screens.ProveedorDetail.route,
            arguments = listOf(navArgument("proveedorId") { type = NavType.IntType })
        ) { backStackEntry ->
            val proveedorId = backStackEntry.arguments?.getInt("proveedorId") ?: 0
            ProveedorDetailScreen(
                viewModel = viewModel,
                proveedorId = proveedorId,
                onBack = { navController.popBackStack() }
            )
        }
    }
}