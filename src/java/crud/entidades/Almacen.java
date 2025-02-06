/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Clase que representa un Almacén en el sistema.
 * <p>
 * Esta entidad se mapea a la tabla <strong>almacen</strong> del esquema
 * <em>reto2</em>
 * y almacena información relacionada con la ubicación y capacidad de un
 * almacén.
 * </p>
 * <p>
 * Se definen las siguientes consultas nombradas:
 * <ul>
 * <li><strong>findAllAlmacen</strong>: Recupera todos los almacenes.</li>
 * <li><strong>findAlmacenesByArticuloId</strong>: Recupera aquellos almacenes
 * que contienen un artículo específico, identificándose mediante el parámetro
 * <code>:articulo_id</code>.</li>
 * </ul>
 * </p>
 *
 * @author Urko
 */
@Entity
@Table(name = "almacen", schema = "reto2")
@NamedQueries({
    @NamedQuery(name = "findAllAlmacen", query = "SELECT a FROM Almacen a")
    ,
    @NamedQuery(name = "findAlmacenesByArticuloId",
            query = "SELECT a FROM Almacen a JOIN a.articulos ar WHERE ar.id = :articulo_id")
})
@XmlRootElement
public class Almacen implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del almacén.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "almacen_id")
    private Long id;

    /**
     * Dirección física del almacén.
     */
    private String direccion;

    /**
     * Población o localidad en la que se ubica el almacén.
     */
    private String poblacion;

    /**
     * Provincia en la que se encuentra el almacén.
     */
    private String provincia;

    /**
     * País en el que se localiza el almacén.
     */
    private String pais;

    /**
     * Espacio disponible en el almacén (por ejemplo, en metros cuadrados).
     */
    private double espacio;

    /**
     * Identificador del artículo para operaciones transitorias.
     * <p>
     * Este campo no se persiste en la base de datos.
     * </p>
     */
    @Transient // Este campo no será persistido en la base de datos
    private Long articuloId;

    /**
     * Conjunto de artículos asociados al almacén.
     * <p>
     * Representa la relación de muchos a muchos con la entidad
     * {@link Articulo}.
     * </p>
     */
    @ManyToMany(mappedBy = "almacenes", fetch = FetchType.EAGER)
    private Set<Articulo> articulos = new HashSet<>();

    /**
     * Constructor por defecto.
     */
    public Almacen() {
    }

    /**
     * Obtiene el identificador único del almacén.
     *
     * @return el identificador del almacén.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único del almacén.
     *
     * @param id el identificador a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la dirección física del almacén.
     *
     * @return la dirección del almacén.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna la dirección física del almacén.
     *
     * @param direccion la dirección a asignar.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la población o localidad en la que se encuentra el almacén.
     *
     * @return la población del almacén.
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Asigna la población o localidad del almacén.
     *
     * @param poblacion la población a asignar.
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Obtiene la provincia en la que se encuentra el almacén.
     *
     * @return la provincia del almacén.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Asigna la provincia del almacén.
     *
     * @param provincia la provincia a asignar.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Obtiene el país donde se localiza el almacén.
     *
     * @return el país del almacén.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Asigna el país donde se localiza el almacén.
     *
     * @param pais el país a asignar.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtiene el espacio disponible en el almacén.
     *
     * @return el espacio del almacén.
     */
    public double getEspacio() {
        return espacio;
    }

    /**
     * Asigna el espacio disponible en el almacén.
     *
     * @param espacio el espacio a asignar.
     */
    public void setEspacio(double espacio) {
        this.espacio = espacio;
    }

    /**
     * Obtiene el conjunto de artículos asociados al almacén.
     *
     * @return el conjunto de artículos.
     */
    @XmlTransient
    public Set<Articulo> getArticulos() {
        return articulos;
    }

    /**
     * Asigna el conjunto de artículos asociados al almacén.
     *
     * @param articulos el conjunto de artículos a asignar.
     */
    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }

    /**
     * Obtiene el identificador del artículo para uso transitorio.
     *
     * @return el identificador del artículo.
     */
    public Long getArticuloId() {
        return articuloId;
    }

    /**
     * Asigna el identificador del artículo para uso transitorio.
     *
     * @param articuloId el identificador a asignar.
     */
    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    /**
     * Calcula el código hash del almacén en función de su identificador.
     *
     * @return el valor del código hash.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     * <p>
     * Dos objetos de tipo {@code Almacen} se consideran iguales si sus
     * identificadores son iguales.
     * </p>
     *
     * @param object el objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Almacen)) {
            return false;
        }
        Almacen other = (Almacen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en cadena del almacén.
     *
     * @return una cadena que representa al almacén.
     */
    @Override
    public String toString() {
        return "crud.entidades.Almacen[ id=" + id + " ]";
    }

}
