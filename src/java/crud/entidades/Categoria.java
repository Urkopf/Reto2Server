/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

/**
 *
 * @author Urko
 */
public enum Categoria {
    RECEPCION_PAQUETES(Departamento.RECEPCION),
    REGISTRO_PAQUETES(Departamento.RECEPCION),
    GESTION_INVENTARIO(Departamento.ALMACENAMIENTO),
    MANEJO_ZONAS_CARGA(Departamento.ALMACENAMIENTO),
    PREPARACION_ENVIOS(Departamento.ENVIO),
    SEGUIMIENTO_ENTREGAS(Departamento.ENVIO),
    PLANIFICACION_RUTAS(Departamento.LOGISTICA),
    COORDINACION_TRANSPORTES(Departamento.LOGISTICA),
    SOPORTE_TECNICO(Departamento.ATENCION_CLIENTE),
    RESOLUCION_RECLAMACIONES(Departamento.ATENCION_CLIENTE),
    COBROS(Departamento.FACTURACION),
    GESTION_IMPUESTOS(Departamento.FACTURACION),
    INFRAESTRUCTURA(Departamento.IT),
    DESARROLLO_SOFTWARE(Departamento.IT),
    CONTRATACION(Departamento.RECURSOS_HUMANOS),
    GESTION_PERSONAL(Departamento.RECURSOS_HUMANOS),
    PROMOCIONES(Departamento.MARKETING),
    RELACIONES_PUBLICAS(Departamento.MARKETING),
    MONITOREO_SEGURIDAD(Departamento.SEGURIDAD),
    GESTION_RIESGOS(Departamento.SEGURIDAD),
    ASUNTOS_LEGALES(Departamento.LEGAL),
    CUMPLIMIENTO_NORMATIVO(Departamento.LEGAL),
    GESTION_OPERATIVA(Departamento.OPERACIONES),
    SUPERVISION_PROCESOS(Departamento.OPERACIONES),
    INSPECCION(Departamento.CONTROL_CALIDAD),
    ANALISIS_MEJORA(Departamento.CONTROL_CALIDAD);

    private final Departamento departamento;

    Categoria(Departamento departamento) {
        this.departamento = departamento;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}
