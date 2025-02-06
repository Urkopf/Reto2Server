/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

/**
 * Excepción utilizada para indicar errores ocurridos durante el proceso de
 * actualización de una entidad o recurso en el sistema.
 * <p>
 * Esta excepción se lanza en situaciones donde no es posible modificar o
 * actualizar un recurso, ya sea por restricciones de integridad, problemas de
 * concurrencia u otros motivos que impidan que la actualización se realice
 * correctamente.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     try {
 *         // Código que intenta actualizar una entidad
 *     } catch (UpdateException ex) {
 *         // Manejo del error de actualización
 *         System.err.println("Error al actualizar la entidad: " + ex.getMessage());
 *     }
 * </pre>
 * </p>
 *
 * @author 2dam
 */
public class UpdateException extends Exception {

    /**
     * Crea una nueva instancia de {@code UpdateException} sin mensaje
     * detallado.
     */
    public UpdateException() {
    }

    /**
     * Crea una nueva instancia de {@code UpdateException} con un mensaje
     * detallado.
     *
     * @param msg el mensaje que describe la causa de la excepción.
     */
    public UpdateException(String msg) {
        super(msg);
    }

}
