
import crud.entidades.Departamento;

/**
 * La enumeración {@code Categoria} representa las diferentes categorías
 * laborales asignadas a los trabajadores, cada una vinculada a un
 * {@link Departamento} específico.
 * <p>
 * Cada constante de esta enumeración define una categoría de trabajo y se
 * asocia con un departamento particular, lo que permite clasificar y gestionar
 * roles de forma organizada en el sistema.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Categoria categoria = Categoria.RECEPCION_PAQUETES;
 *     Departamento dept = categoria.getDepartamento();
 * </pre>
 * </p>
 *
 * @see Departamento
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

    /**
     * Departamento asociado a la categoría.
     */
    private final Departamento departamento;

    /**
     * Constructor de la enumeración {@code Categoria}.
     *
     * @param departamento el departamento asociado a esta categoría.
     */
    Categoria(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Retorna el departamento asociado a la categoría.
     *
     * @return el {@link Departamento} correspondiente a esta categoría.
     */
    public Departamento getDepartamento() {
        return departamento;
    }
}
