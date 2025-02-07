/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

/*
 * Enumeración Estado
 *
 * <p>Esta enumeración define los diferentes estados posibles para un proceso o entidad dentro de
 * un sistema.</p>
 *
 * <p>Los estados incluidos son:</p>
 * <ul>
 *   <li><strong>PREPARACION:</strong> Estado inicial de preparación para un proceso o entidad.</li>
 *   <li><strong>ENVIADO:</strong> Estado que indica que el proceso o entidad ha sido enviado.</li>
 *   <li><strong>CANCELADO:</strong> Estado que indica que el proceso o entidad ha sido cancelado.</li>
 *   <li><strong>INCIDENCIA:</strong> Estado que indica que el proceso o entidad tiene una incidencia.</li>
 *   <li><strong>COMPLETADO:</strong> Estado que indica que el proceso o entidad ha sido completado.</li>
 * </ul>
 *
 * <p>Cada constante enumera un estado con su respectiva descripción de su significado.</p>
 *
 * @author Urko
 */
public enum Estado {
    PREPARACION, ENVIADO, CANCELADO, INCIDENCIA, COMPLETADO
}
