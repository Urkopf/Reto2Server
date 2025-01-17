/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 2dam
 */
@XmlRootElement
public class RespuestasAPI {

    private int codigoEstado;
    private String mensaje;
    private Object detalles;

    public RespuestasAPI() {
    }

    public RespuestasAPI(int codigoEstado, String mensaje) {
        this.codigoEstado = codigoEstado;
        this.mensaje = mensaje;
    }

    public RespuestasAPI(int codigoEstado, String mensaje, Object detalles) {
        this.codigoEstado = codigoEstado;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Object getDetalles() {
        return detalles;
    }

    public void setDetalles(Object detalles) {
        this.detalles = detalles;
    }


}
