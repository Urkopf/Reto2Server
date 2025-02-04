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
 * Entidad que representa un cliente en el sistema.
 * <p>
 * Esta entidad se mapea a la tabla <strong>cliente</strong> del esquema
 * <strong>reto2</strong>
 * y extiende de la entidad {@link Usuario}, heredando sus propiedades. Además,
 * define atributos específicos para clientes como el sector, el descuento y el
 * teléfono.
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Cliente cliente = new Cliente();
 *     cliente.setSector("Retail");
 *     cliente.setDescuento(10.5);
 *     cliente.setTelefono("555-1234");
 * </pre>
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
     * Sector al que pertenece el cliente.
     */
    private String sector;

    /**
     * Porcentaje de descuento aplicable al cliente.
     */
    private double descuento;

    /**
     * Número de teléfono de contacto del cliente.
     */
    private String telefono;

    /**
     * Constructor por defecto.
     */
    public Cliente() {
    }

    /**
     * Retorna el sector del cliente.
     *
     * @return el sector del cliente.
     */
    public String getSector() {
        return sector;
    }

    /**
     * Asigna el sector al cliente.
     *
     * @param sector el sector a asignar.
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * Retorna el descuento aplicable al cliente.
     *
     * @return el descuento del cliente.
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Asigna el descuento al cliente.
     *
     * @param descuento el descuento a asignar.
     */
    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    /**
     * Retorna el número de teléfono del cliente.
     *
     * @return el teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el número de teléfono al cliente.
     *
     * @param telefono el teléfono a asignar.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Calcula el código hash basado en el identificador heredado de la entidad
     * Usuario.
     *
     * @return el código hash calculado.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    /**
     * Compara el objeto actual con otro objeto para determinar igualdad.
     * <p>
     * La comparación se basa en el identificador único heredado de la entidad
     * Usuario.
     * </p>
     *
     * @param object el objeto con el que se realiza la comparación.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
     * Retorna una representación en forma de cadena del cliente.
     *
     * @return una cadena que contiene el identificador del cliente.
     */
    @Override
    public String toString() {
        return "crud.entidades.Cliente[ id=" + getId() + " ]";
    }

}
