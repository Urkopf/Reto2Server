/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

/*
 * Enumeración Departamento
 *
 * <p>Esta enumeración define los diferentes departamentos dentro de una organización, cada uno
 * encargado de funciones específicas para el funcionamiento eficiente del negocio.</p>
 *
 * <p>Los departamentos incluidos son:</p>
 * <ul>
 *   <li><strong>RECEPCION:</strong> Encargado de recibir paquetes.</li>
 *   <li><strong>ALMACENAMIENTO:</strong> Gestiona el almacenamiento temporal de los paquetes.</li>
 *   <li><strong>ENVIO:</strong> Responsable de gestionar los envíos de productos.</li>
 *   <li><strong>LOGISTICA:</strong> Organiza las rutas y el transporte.</li>
 *   <li><strong>ATENCION_CLIENTE:</strong> Resuelve consultas y problemas de los clientes.</li>
 *   <li><strong>FACTURACION:</strong> Maneja la facturación y los pagos.</li>
 *   <li><strong>IT:</strong> Brinda soporte técnico en tecnología de la información.</li>
 *   <li><strong>RECURSOS_HUMANOS:</strong> Gestiona el personal y la contratación.</li>
 *   <li><strong>MARKETING:</strong> Encargado de promociones y relaciones públicas.</li>
 *   <li><strong>SEGURIDAD:</strong> Asegura la protección de paquetes y empleados.</li>
 *   <li><strong>LEGAL:</strong> Maneja asuntos legales y regulatorios.</li>
 *   <li><strong>OPERACIONES:</strong> Encargado de las operaciones diarias.</li>
 *   <li><strong>CONTROL_CALIDAD:</strong> Verifica la calidad del servicio.</li>
 * </ul>
 *
 * <p>Cada constante enumera un departamento con su respectiva descripción de funciones.</p>
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
