/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

/**
 *
 * @author 2dam
 */
public class UpdateException extends Exception {

    public UpdateException() {
    }

    public UpdateException(String msg) {
        super(msg);
    }

}
