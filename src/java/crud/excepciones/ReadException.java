/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

/**
 * Excepción utilizada para indicar errores ocurridos durante el proceso de
 * lectura o recuperación de datos en el sistema.
 * <p>
 * Esta excepción se utiliza en la capa de persistencia o en la lógica de
 * negocio para notificar fallos específicos al intentar leer un recurso.
 * Permite proporcionar un mensaje detallado que ayude a identificar la causa
 * del error.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     try {
 *         // Código que intenta leer datos
 *     } catch (ReadException ex) {
 *         // Manejo del error de lectura
 *         System.err.println("Error al leer la entidad: " + ex.getMessage());
 *     }
 * </pre>
 * </p>
 *
 * @author 2dam
 */
public class ReadException extends Exception {

    /**
     * Crea una nueva instancia de {@code ReadException} sin mensaje detallado.
     */
    public ReadException() {
    }

    /**
     * Crea una nueva instancia de {@code ReadException} con un mensaje
     * detallado.
     *
     * @param msg el mensaje que describe la causa de la excepción.
     */
    public ReadException(String msg) {
        super(msg);
    }
}
