/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

/*
 * Enumeración Categoria
 *
 * <p>Esta enumeración define diferentes categorías que están asociadas a departamentos específicos
 * dentro de una organización.</p>
 *
 * <p>Cada categoría enumera un tipo de función o responsabilidad dentro de la empresa, vinculada
 * a un departamento particular.</p>
 *
 * <p>Las categorías y sus respectivos departamentos son:</p>
 * <ul>
 *   <li><strong>RECEPCION_PAQUETES:</strong> Asociada al departamento de Recepción.</li>
 *   <li><strong>REGISTRO_PAQUETES:</strong> Asociada al departamento de Recepción.</li>
 *   <li><strong>GESTION_INVENTARIO:</strong> Asociada al departamento de Almacenamiento.</li>
 *   <li><strong>MANEJO_ZONAS_CARGA:</strong> Asociada al departamento de Almacenamiento.</li>
 *   <li><strong>PREPARACION_ENVIOS:</strong> Asociada al departamento de Envío.</li>
 *   <li><strong>SEGUIMIENTO_ENTREGAS:</strong> Asociada al departamento de Envío.</li>
 *   <li><strong>PLANIFICACION_RUTAS:</strong> Asociada al departamento de Logística.</li>
 *   <li><strong>COORDINACION_TRANSPORTES:</strong> Asociada al departamento de Logística.</li>
 *   <li><strong>SOPORTE_TECNICO:</strong> Asociada al departamento de Atención al Cliente.</li>
 *   <li><strong>RESOLUCION_RECLAMACIONES:</strong> Asociada al departamento de Atención al Cliente.</li>
 *   <li><strong>COBROS:</strong> Asociada al departamento de Facturación.</li>
 *   <li><strong>GESTION_IMPUESTOS:</strong> Asociada al departamento de Facturación.</li>
 *   <li><strong>INFRAESTRUCTURA:</strong> Asociada al departamento de IT.</li>
 *   <li><strong>DESARROLLO_SOFTWARE:</strong> Asociada al departamento de IT.</li>
 *   <li><strong>CONTRATACION:</strong> Asociada al departamento de Recursos Humanos.</li>
 *   <li><strong>GESTION_PERSONAL:</strong> Asociada al departamento de Recursos Humanos.</li>
 *   <li><strong>PROMOCIONES:</strong> Asociada al departamento de Marketing.</li>
 *   <li><strong>RELACIONES_PUBLICAS:</strong> Asociada al departamento de Marketing.</li>
 *   <li><strong>MONITOREO_SEGURIDAD:</strong> Asociada al departamento de Seguridad.</li>
 *   <li><strong>GESTION_RIESGOS:</strong> Asociada al departamento de Seguridad.</li>
 *   <li><strong>ASUNTOS_LEGALES:</strong> Asociada al departamento Legal.</li>
 *   <li><strong>CUMPLIMIENTO_NORMATIVO:</strong> Asociada al departamento Legal.</li>
 *   <li><strong>GESTION_OPERATIVA:</strong> Asociada al departamento de Operaciones.</li>
 *   <li><strong>SUPERVISION_PROCESOS:</strong> Asociada al departamento de Operaciones.</li>
 *   <li><strong>INSPECCION:</strong> Asociada al departamento de Control de Calidad.</li>
 *   <li><strong>ANALISIS_MEJORA:</strong> Asociada al departamento de Control de Calidad.</li>
 * </ul>
 *
 * <p>Cada constante enumera una categoría con su respectivo departamento asociado.</p>
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

    /**
     * Obtiene el departamento asociado a la categoría.
     *
     * @return el departamento asociado
     */
    public Departamento getDepartamento() {
        return departamento;
    }
}
