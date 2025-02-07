/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

/**
 * Excepción utilizada para indicar errores ocurridos durante el proceso de
 * creación de una entidad u objeto.
 * <p>
 * Esta excepción se utiliza en la capa de persistencia o en la lógica de
 * negocio para notificar fallos específicos al intentar crear un recurso.
 * Permite proporcionar un mensaje detallado que ayude a identificar la causa
 * del error.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     try {
 *         // Código que intenta crear una entidad
 *     } catch (CreateException ex) {
 *         // Manejo del error de creación
 *         System.err.println("Error al crear la entidad: " + ex.getMessage());
 *     }
 * </pre>
 * </p>
 *
 * @author 2dam
 */
public class CreateException extends Exception {

    /**
     * Crea una nueva instancia de {@code CreateException} sin mensaje
     * detallado.
     */
    public CreateException() {
    }

    /**
     * Crea una nueva instancia de {@code CreateException} con un mensaje
     * detallado.
     *
     * @param msg el mensaje que describe la causa de la excepción.
     */
    public CreateException(String msg) {
        super(msg);
    }

}
