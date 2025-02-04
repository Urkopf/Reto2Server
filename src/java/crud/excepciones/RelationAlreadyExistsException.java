/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

/**
 * Excepción que indica que una relación ya existe en el sistema.
 * <p>
 * Esta excepción se lanza cuando se intenta crear o establecer una relación que
 * ya se encuentra registrada, evitando así la duplicación o inconsistencias en
 * la lógica de negocio o en la base de datos.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     if (existeRelacion) {
 *         throw new RelationAlreadyExistsException("La relación ya existe.");
 *     }
 * </pre>
 * </p>
 *
 * @author Ser_090
 */
public class RelationAlreadyExistsException extends Exception {

    /**
     * Crea una nueva instancia de {@code RelationAlreadyExistsException} con el
     * mensaje especificado.
     *
     * @param message el mensaje que describe la causa de la excepción.
     */
    public RelationAlreadyExistsException(String message) {
        super(message);
    }

}
