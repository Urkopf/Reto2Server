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
 * Representa un almacén en el sistema.
 * <p>
 * La entidad almacena la información del almacén, incluyendo dirección,
 * población, provincia, país y el espacio disponible. Además, mantiene la
 * relación con los artículos que contiene a través de una relación de muchos a
 * muchos.
 * </p>
 * <p>
 * Se mapea a la tabla <code>almacen</code> en el esquema <code>reto2</code>.
 * </p>
 * <p>
 * Consultas nombradas:
 * <ul>
 * <li>{@code findAllAlmacen}: Recupera todos los almacenes.</li>
 * <li>{@code findAlmacenesByArticuloId}: Recupera los almacenes asociados a un
 * artículo específico.</li>
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
     * Dirección del almacén.
     */
    private String direccion;

    /**
     * Población en la que se encuentra el almacén.
     */
    private String poblacion;

    /**
     * Provincia en la que se ubica el almacén.
     */
    private String provincia;

    /**
     * País donde se localiza el almacén.
     */
    private String pais;

    /**
     * Espacio disponible en el almacén.
     */
    private double espacio;

    /**
     * Identificador temporal de un artículo (no persistido en la base de
     * datos).
     */
    @Transient // Este campo no será persistido en la base de datos
    private Long articuloId;

    /**
     * Conjunto de artículos asociados al almacén.
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
     * @return El identificador del almacén.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único del almacén.
     *
     * @param id El nuevo identificador del almacén.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la dirección del almacén.
     *
     * @return La dirección del almacén.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del almacén.
     *
     * @param direccion La nueva dirección del almacén.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la población en la que se encuentra el almacén.
     *
     * @return La población del almacén.
     */
    public String getPoblacion() {
        return poblacion;
    }

    /**
     * Establece la población del almacén.
     *
     * @param poblacion La nueva población del almacén.
     */
    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * Obtiene la provincia donde se ubica el almacén.
     *
     * @return La provincia del almacén.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     * Establece la provincia del almacén.
     *
     * @param provincia La nueva provincia del almacén.
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     * Obtiene el país donde se localiza el almacén.
     *
     * @return El país del almacén.
     */
    public String getPais() {
        return pais;
    }

    /**
     * Establece el país del almacén.
     *
     * @param pais El nuevo país del almacén.
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Obtiene el espacio disponible en el almacén.
     *
     * @return El espacio del almacén.
     */
    public double getEspacio() {
        return espacio;
    }

    /**
     * Establece el espacio disponible en el almacén.
     *
     * @param espacio El nuevo espacio del almacén.
     */
    public void setEspacio(double espacio) {
        this.espacio = espacio;
    }

    /**
     * Obtiene el conjunto de artículos asociados al almacén.
     *
     * @return Un conjunto de objetos {@link Articulo}.
     */
    @XmlTransient
    public Set<Articulo> getArticulos() {
        return articulos;
    }

    /**
     * Establece el conjunto de artículos asociados al almacén.
     *
     * @param articulos El nuevo conjunto de artículos.
     */
    public void setArticulos(Set<Articulo> articulos) {
        this.articulos = articulos;
    }

    /**
     * Obtiene el identificador temporal del artículo (no persistido).
     *
     * @return El identificador temporal del artículo.
     */
    public Long getArticuloId() {
        return articuloId;
    }

    /**
     * Establece el identificador temporal del artículo (no persistido).
     *
     * @param articuloId El nuevo identificador temporal del artículo.
     */
    public void setArticuloId(Long articuloId) {
        this.articuloId = articuloId;
    }

    /**
     * Calcula el valor hash del almacén basado en su identificador.
     *
     * @return El valor hash del almacén.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Compara este almacén con otro objeto para determinar si son iguales.
     * <p>
     * Dos almacenes se consideran iguales si tienen el mismo identificador.
     * </p>
     *
     * @param object El objeto a comparar.
     * @return {@code true} si los objetos son iguales, {@code false} en caso
     * contrario.
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
     * Genera una representación en cadena del almacén.
     *
     * @return Una cadena que representa el almacén.
     */
    @Override
    public String toString() {
        return "crud.entidades.Almacen[ id=" + id + " ]";
    }

}
