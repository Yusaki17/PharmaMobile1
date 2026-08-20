package pe.edu.upeu.pharmamobile.domain.service

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pe.edu.upeu.pharmamobile.data.repository.productosSimulados
import pe.edu.upeu.pharmamobile.domain.model.Producto
import pe.edu.upeu.pharmamobile.domain.result.ResultadoProductos

class ProductoService {

    // PASOS 4 y 5: Operación suspend que retorna un valor único
    suspend fun obtenerProductos(): List<Producto> {
        println(" Iniciando carga de productos...")
        delay(1000) // Simula latencia de red/BD
        println(" Productos cargados: ${productosSimulados.size}")
        return productosSimulados
    }
    // PASOS 9-11: Flow que emite múltiples valores
    fun obtenerProductosFlow(): Flow<List<Producto>> = flow {
        println(" Emisión inicial: lista vacía")
        emit(emptyList<Producto>()) // Estado inicial

        delay(1000)
        println("  Emisión final: ${productosSimulados.size} productos")
        emit(productosSimulados) // Datos reales
    }
    // PASOS 12 a 14: Flow con copy() para simular cambio de stock
    fun observarProductos(): Flow<List<Producto>> = flow {
        emit(emptyList()) // Emisión inicial vacía
        delay(1000)

        // Simulación de actualización dinámica con copy()
        val productosActualizados = productosSimulados.map {
            if (it.id == 1L) it.copy(stock = 90) else it
        }

        emit(productosActualizados)
    }








    // PASOS 15-17: Flow con estados completos (Cargando → Éxito/Error)
    fun cargarProductosConEstado(): Flow<ResultadoProductos> = flow {
        emit(ResultadoProductos.Cargando)
        println("Estado: Cargando")

        try {
            delay(1000)
            // Simula actualización de stock con copy()
            val productosActualizados = productosSimulados.map {
                it.copy(stock = it.stock - 5)
            }
            emit(ResultadoProductos.Exito(productosActualizados))
            println("Estado: Éxito - ${productosActualizados.size} productos")
        } catch (e: Exception) {
            emit(ResultadoProductos.Error(e.message ?: "Error desconocido"))
            println("Estado: Error - ${e.message}")
        }
    }
}