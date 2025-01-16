/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.excepciones;

import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Sergio
 */
@Provider
public class ManejadorGlobarExcepciones implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {

        RespuestasAPI respuestaError = null;
        Response.Status status = null;

        if (exception instanceof InternalServerErrorException) {
            //Manejo de excepciones
            status = Response.Status.INTERNAL_SERVER_ERROR;
            respuestaError = new RespuestasAPI(
                    status.getStatusCode(),
                    "Error del servidor interno",
                    exception.getMessage());
        } else if (exception instanceof CreateException) {
            status = Response.Status.BAD_REQUEST;
            respuestaError = new RespuestasAPI(
                    status.getStatusCode(),
                    "Error al crear el articulo",
                    exception.getMessage());
        } else if (exception instanceof UpdateException) {

        } else if (exception instanceof RemoveException) {

        } else if (exception instanceof ReadException) {

        } else {
            status = Response.Status.INTERNAL_SERVER_ERROR;
            respuestaError = new RespuestasAPI(
                    status.getStatusCode(),
                    "Error inesperado",
                    exception.getMessage());
        }

        return Response
                .status(status)
                .entity(respuestaError)
                .build();
    }


}
