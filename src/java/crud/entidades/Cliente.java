/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa un cliente dentro del sistema.
 * <p>
 * Esta entidad hereda de {@link Usuario} y contiene información adicional
 * específica de un cliente, como el sector, el descuento y el teléfono de
 * contacto. Se mapea a la tabla <strong>cliente</strong> del esquema
 * <strong>reto2</strong> en la base de datos.
 * </p>
 *
 * <p>
 * Consulta nombrada disponible:
 * <ul>
 * <li><strong>findAllCliente:</strong> Recupera todos los registros de
 * clientes.</li>
 * </ul>
 * </p>
 *
 * @author Urko
 */
@Entity
@Table(name = "cliente", schema = "reto2")
@NamedQuery(name = "findAllCliente", query = "SELECT a FROM Cliente a")
@PrimaryKeyJoinColumn(name = "usuario_id")
@XmlRootElement
public class Cliente extends Usuario {

    /**
     * Sector o industria al que pertenece el cliente.
     */
    private String sector;

    /**
     * Porcentaje de descuento que tiene asignado el cliente.
     */
    private double descuento;

    /**
     * Número de teléfono de contacto del cliente.
     */
    private String telefono;

    /**
     * Constructor vacío.
     */
    public Cliente() {
    }

    /**
     * Obtiene el sector al que pertenece el cliente.
     *
     * @return el sector del cliente.
     */
    public String getSector() {
        return sector;
    }

    /**
     * Asigna el sector al que pertenece el cliente.
     *
     * @param sector el sector a asignar.
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * Obtiene el porcentaje de descuento del cliente.
     *
     * @return el porcentaje de descuento.
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Asigna el porcentaje de descuento del cliente.
     *
     * @param descuento el porcentaje de descuento a asignar.
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /**
     * Obtiene el número de teléfono de contacto del cliente.
     *
     * @return el número de teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el número de teléfono de contacto del cliente.
     *
     * @param telefono el número de teléfono a asignar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Calcula el código hash del cliente utilizando el identificador de
     * usuario.
     *
     * @return el código hash del cliente.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    /**
     * Compara este objeto con otro para determinar si son iguales.
     * <p>
     * Dos objetos de tipo {@code Cliente} se consideran iguales si sus
     * identificadores de usuario son iguales.
     * </p>
     *
     * @param object el objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /**
     * Devuelve una representación en cadena del cliente.
     *
     * @return una cadena que representa al cliente.
     */
    @Override
    public String toString() {
        return "crud.entidades.Cliente[ id=" + getId() + " ]";
    }

}
