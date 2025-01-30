/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

/**
 *
 * @author Ser_090
 */
public class RelationAlreadyExistsException extends Exception {

    public RelationAlreadyExistsException(String message) {
        super(message);
    }

}
