package com.example.brewco.ui.screens.home

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.brewco.data.model.Product
import com.example.brewco.ui.components.CustomBottomNavBar
import com.example.brewco.ui.components.CustomDrawer
import com.example.brewco.ui.components.TopBar
import com.example.brewco.ui.screens.inventory.StockViewModel
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(navHostController: NavHostController, viewModel: StockViewModel = viewModel()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val topProducts by viewModel.topProducts.collectAsState() // Observa los productos con más inventario

    LaunchedEffect(Unit) {
        viewModel.loadTopProducts() // Cargar los 5 productos con más inventario al inicio
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            CustomDrawer(
                navHostController = navHostController,
                onLogoutClick = {
                    navHostController.navigate("splashScreen") {
                        popUpTo(0) // Limpia la pila de navegación
                    }
                })
        },
    ) { }

    Scaffold(
        topBar = {
            TopBar(title = "Inicio", onMenuClick = {
                scope.launch {
                    if (drawerState.isClosed) {
                        drawerState.open()
                    } else {
                        drawerState.close()
                    }
                }
            })
        },
        containerColor = Color.White,
        bottomBar = { CustomBottomNavBar(navHostController) },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn {
                    // Mostrar el gráfico de ingresos y gastos
                    item {
                        IncomeExpenseChart()  // Llama a la función de la nueva gráfica
                    }
                    // Mostrar los productos en la lista

                }

            }
        }
    )
}

@Composable
fun IncomeExpenseChart() {
    val incomeData = listOf(9500, 8700, 8000, 9100, 8500, 9400, 10000)
    val expenseData = listOf(4500, 5200, 4800, 5000, 4700, 5300, 6000)
    val days = listOf("L", "M", "X", "J", "V", "S", "D")

    val maxDataValue = (incomeData + expenseData).maxOf { it }.toFloat()

    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
    ) {  // Agregar un padding de 16dp

        // Dibujar fondo blanco (que cubra toda la gráfica)
        drawRect(
            color = Color.White,
            topLeft = Offset(0f, 0f),
            size = size // Cubre toda la superficie del Canvas
        )

        val chartWidth = size.width
        val chartHeight = size.height * 0.8f
        val leftPadding = 80f
        val rightPadding = 40f
        val barSpacing = (chartWidth - leftPadding - rightPadding) / (incomeData.size - 1)

        // Ejes
        drawLine(
            color = Color.Gray,
            start = Offset(leftPadding, chartHeight),
            end = Offset(chartWidth - rightPadding, chartHeight),
            strokeWidth = 2f
        )

        drawLine(
            color = Color.Gray,
            start = Offset(leftPadding, chartHeight),
            end = Offset(leftPadding, 0f),
            strokeWidth = 2f
        )

        // Líneas y puntos de ingresos
        incomeData.forEachIndexed { index, income ->
            if (index > 0) {
                drawLine(
                    color = Color(0xFF4CAF50), // Verde para ingresos
                    start = Offset(
                        leftPadding + barSpacing * (index - 1),
                        chartHeight - (incomeData[index - 1] / maxDataValue) * chartHeight
                    ),
                    end = Offset(
                        leftPadding + barSpacing * index,
                        chartHeight - (income / maxDataValue) * chartHeight
                    ),
                    strokeWidth = 4f
                )
            }

            // Puntos de ingresos
            drawCircle(
                color = Color(0xFF4CAF50),
                center = Offset(
                    leftPadding + barSpacing * index,
                    chartHeight - (income / maxDataValue) * chartHeight
                ),
                radius = 6f
            )
        }

        // Líneas y puntos de gastos
        expenseData.forEachIndexed { index, expense ->
            if (index > 0) {
                drawLine(
                    color = Color(0xFFF44336), // Rojo para gastos
                    start = Offset(
                        leftPadding + barSpacing * (index - 1),
                        chartHeight - (expenseData[index - 1] / maxDataValue) * chartHeight
                    ),
                    end = Offset(
                        leftPadding + barSpacing * index,
                        chartHeight - (expense / maxDataValue) * chartHeight
                    ),
                    strokeWidth = 4f
                )
            }

            // Puntos de gastos
            drawCircle(
                color = Color(0xFFF44336),
                center = Offset(
                    leftPadding + barSpacing * index,
                    chartHeight - (expense / maxDataValue) * chartHeight
                ),
                radius = 6f
            )
        }

        // Etiquetas en el eje X
        days.forEachIndexed { index, day ->
            drawContext.canvas.nativeCanvas.drawText(
                day,
                leftPadding + barSpacing * index,
                chartHeight + 30f,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.DKGRAY
                    textSize = 32f
                    textAlign = android.graphics.Paint.Align.CENTER
                }
            )
        }

        // Etiquetas en el eje Y
        val step = maxDataValue / 5
        for (i in 0..5) {
            val yValue = step * i
            val yPosition = chartHeight - (yValue / maxDataValue) * chartHeight

            drawContext.canvas.nativeCanvas.drawText(
                yValue.toInt().toString(),
                leftPadding - 20f,
                yPosition + 10f,
                android.graphics.Paint().apply {
                    color = android.graphics.Color.DKGRAY
                    textSize = 28f
                    textAlign = android.graphics.Paint.Align.RIGHT
                }
            )
        }
    }
}
