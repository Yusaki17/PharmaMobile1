package pe.edu.upeu.pharmamobile.domain.service

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import pe.edu.upeu.pharmamobile.domain.result.ResultadoProductos
import kotlin.test.Test
import kotlin.test.assertTrue

class ProductoServiceTest {

    private val service = ProductoService()

    @Test
    fun testObtenerProductosSuspend() = runBlocking {
        println("\n=== TEST SUSPEND ===")
        val productos = service.obtenerProductos()
        assertTrue(productos.isNotEmpty())
        println("Test Suspend: ${productos.size} productos obtenidos")
    }

    @Test
    fun testObtenerProductosFlow() = runBlocking {
        println("\n=== TEST FLOW ===")
        val flujo = service.obtenerProductosFlow()
        val resultado = flujo.first { it.isNotEmpty() }
        assertTrue(resultado.isNotEmpty())
        println("Test Flow: ${resultado.size} productos recibidos")
    }

    @Test
    fun testCargarProductosConEstado() = runBlocking {
        println("\n=== TEST FLOW CON ESTADOS ===")
        val estados = service.cargarProductosConEstado()

        // Verifica estado inicial
        val primerEstado = estados.first()
        assertTrue(primerEstado is ResultadoProductos.Cargando)
        println("Estado inicial: Cargando")

    }
}