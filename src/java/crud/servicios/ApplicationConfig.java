/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.servicios;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author 2dam
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(crud.servicios.AlmacenFacadeREST.class);
        resources.add(crud.servicios.ArticuloFacadeREST.class);
        resources.add(crud.servicios.ClienteFacadeREST.class);
        resources.add(crud.servicios.PedidoArticuloFacadeREST.class);
        resources.add(crud.servicios.PedidoFacadeREST.class);
        resources.add(crud.servicios.TrabajadorFacadeREST.class);
        resources.add(crud.servicios.UsuarioFacadeREST.class);
    }

}
