/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import crud.ejb.IGestorEntidadesLocal;
import crud.entidades.Pedido;
import crud.excepciones.CreateException;
import crud.excepciones.ReadException;
import crud.excepciones.RemoveException;
import crud.excepciones.UpdateException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("pedido")
public class PedidoFacadeREST {

    @EJB
    private IGestorEntidadesLocal ejb;

    private Logger LOGGER = Logger.getLogger(PedidoFacadeREST.class.getName());

    @POST
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Pedido entity) {
        try {
            LOGGER.log(Level.INFO, "Creando pedido con ID {0}", entity.getId());
            ejb.createPedido(entity);
        } catch (CreateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @PUT
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(Pedido entity) {
        try {
            LOGGER.log(Level.INFO, "Actualizando pedido con ID {0}", entity.getId());

            ejb.updatePedido(entity);
        } catch (UpdateException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Borrando pedido con ID {0}", id);
            Pedido pedido = ejb.findPedido(id);
            ejb.removePedido(pedido);
        } catch (ReadException | RemoveException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pedido find(@PathParam("id") Long id) {
        try {
            LOGGER.log(Level.INFO, "Buscando pedido con ID {0}", id);
            return ejb.findPedido(id);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pedido> findAll() {
        try {
            LOGGER.log(Level.INFO, "Buscando todos los pedidos.");
            return ejb.findAllPedido();
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pedido> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        try {
            LOGGER.log(Level.INFO, "Buscando pedidos desde {0} hasta {1}", new Object[]{from, to});
            List<Pedido> pedidos = ejb.findAllPedido();
            int size = pedidos.size();
            int start = (from != null && from >= 0 && from < size) ? from : 0;
            int end = (to != null && to <= size && to > start) ? to : size;
            return pedidos.subList(start, end);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        try {
            LOGGER.info("Contando la cantidad total de pedidos.");
            int count = ejb.findAllPedido().size();
            return String.valueOf(count);
        } catch (ReadException ex) {
            LOGGER.severe(ex.getMessage());
            throw new InternalServerErrorException(ex.getMessage());
        }
    }

}
