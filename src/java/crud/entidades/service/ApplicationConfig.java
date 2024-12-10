/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades.service;

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
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(crud.entidades.service.PedidoFacadeREST.class);
        resources.add(service.AlmacenFacadeREST.class);
        resources.add(service.ArticuloFacadeREST.class);
        resources.add(service.ClienteFacadeREST.class);
        resources.add(service.PedidoArticuloFacadeREST.class);
        resources.add(service.PedidoFacadeREST.class);
        resources.add(service.TrabajadorFacadeREST.class);
        resources.add(service.UsuarioFacadeREST.class);
    }

}
