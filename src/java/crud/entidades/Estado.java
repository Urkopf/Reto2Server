/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

/**
 * Representa los diferentes estados posibles de un pedido o transacción en el
 * sistema.
 * <p>
 * Esta enumeración define el ciclo de vida de un pedido, desde su preparación
 * hasta su finalización, incluyendo estados intermedios que reflejan diversas
 * situaciones que pueden ocurrir durante el proceso.
 * </p>
 * <ul>
 * <li>{@code PREPARACION} - El pedido se encuentra en proceso de
 * preparación.</li>
 * <li>{@code ENVIADO} - El pedido ha sido enviado al destinatario.</li>
 * <li>{@code CANCELADO} - El pedido ha sido cancelado.</li>
 * <li>{@code INCIDENCIA} - Se ha detectado una incidencia durante el proceso
 * del pedido.</li>
 * <li>{@code COMPLETADO} - El pedido se ha completado exitosamente.</li>
 * </ul>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Estado estadoActual = Estado.PREPARACION;
 * </pre>
 * </p>
 *
 * @author Urko
 */
public enum Estado {
    PREPARACION, ENVIADO, CANCELADO, INCIDENCIA, COMPLETADO
}
