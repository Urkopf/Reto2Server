/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

/**
 * Enumeración que representa los diferentes departamentos dentro del sistema.
 * <p>
 * Cada constante define un departamento específico con funciones particulares,
 * tales como la recepción, almacenamiento, envío, logística, atención al
 * cliente, facturación, soporte técnico, recursos humanos, marketing,
 * seguridad, asuntos legales, operaciones y control de calidad.
 * </p>
 * <p>
 * Los departamentos se describen de la siguiente forma:
 * <ul>
 * <li>{@code RECEPCION} - Departamento encargado de recibir paquetes.</li>
 * <li>{@code ALMACENAMIENTO} - Departamento encargado de almacenar
 * temporalmente los paquetes.</li>
 * <li>{@code ENVIO} - Departamento que gestiona los envíos.</li>
 * <li>{@code LOGISTICA} - Departamento que organiza las rutas y el
 * transporte.</li>
 * <li>{@code ATENCION_CLIENTE} - Departamento encargado de resolver consultas y
 * problemas de los clientes.</li>
 * <li>{@code FACTURACION} - Departamento que maneja la facturación y los
 * pagos.</li>
 * <li>{@code IT} - Departamento de tecnología de la información para soporte
 * técnico.</li>
 * <li>{@code RECURSOS_HUMANOS} - Departamento que gestiona el personal y
 * contratación.</li>
 * <li>{@code MARKETING} - Departamento encargado de promociones y relaciones
 * públicas.</li>
 * <li>{@code SEGURIDAD} - Departamento que asegura la protección de los
 * paquetes y empleados.</li>
 * <li>{@code LEGAL} - Departamento que maneja asuntos legales y
 * regulatorios.</li>
 * <li>{@code OPERACIONES} - Departamento encargado de las operaciones
 * diarias.</li>
 * <li>{@code CONTROL_CALIDAD} - Departamento que verifica la calidad del
 * servicio.</li>
 * </ul>
 * </p>
 *
 * @author Urko
 */
public enum Departamento {
    RECEPCION, // Departamento encargado de recibir paquetes.
    ALMACENAMIENTO, // Departamento encargado de almacenar temporalmente los paquetes.
    ENVIO, // Departamento que gestiona los envíos.
    LOGISTICA, // Departamento que organiza las rutas y el transporte.
    ATENCION_CLIENTE, // Departamento encargado de resolver consultas y problemas de los clientes.
    FACTURACION, // Departamento que maneja la facturación y los pagos.
    IT, // Departamento de tecnología de la información para soporte técnico.
    RECURSOS_HUMANOS, // Departamento que gestiona el personal y contratación.
    MARKETING, // Departamento encargado de promociones y relaciones públicas.
    SEGURIDAD, // Departamento que asegura la protección de los paquetes y empleados.
    LEGAL, // Departamento que maneja asuntos legales y regulatorios.
    OPERACIONES, // Departamento encargado de las operaciones diarias.
    CONTROL_CALIDAD     // Departamento que verifica la calidad del servicio.
}
