/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

/**
 * Excepción utilizada para indicar errores ocurridos durante el proceso de
 * eliminación de una entidad o recurso en el sistema.
 * <p>
 * Esta excepción se lanza en situaciones donde no es posible remover un
 * recurso, ya sea por restricciones de integridad, dependencias existentes u
 * otros motivos que impidan una eliminación correcta.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     try {
 *         // Código que intenta eliminar una entidad
 *     } catch (RemoveException ex) {
 *         // Manejo del error de eliminación
 *         System.err.println("Error al eliminar la entidad: " + ex.getMessage());
 *     }
 * </pre>
 * </p>
 *
 * @author 2dam
 */
public class RemoveException extends Exception {

    /**
     * Crea una nueva instancia de {@code RemoveException} sin mensaje
     * detallado.
     */
    public RemoveException() {
    }

    /**
     * Crea una nueva instancia de {@code RemoveException} con un mensaje
     * detallado.
     *
     * @param msg el mensaje que describe la causa de la excepción.
     */
    public RemoveException(String msg) {
        super(msg);
    }

}
