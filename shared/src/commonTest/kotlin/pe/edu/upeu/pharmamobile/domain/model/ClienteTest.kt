package pe.edu.upeu.pharmamobile.domain.model

import kotlin.test.Test
import kotlin.test.assertEquals

class ClienteTest{

    @Test
    fun probarCliente() {
        val cliente = Cliente(
            id = 1L,
            nombre = "Farmacia Nuevas Vida",
            correo = "ventas@central.pe",
            telefono = "987654321"
        )
        val resultado = cliente.obtenerTelefono()

        assertEquals(
            expected = "987654321",
            actual = resultado

        )
    }
}

