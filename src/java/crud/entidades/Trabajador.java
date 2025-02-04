/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Representa un Trabajador en el sistema.
 * <p>
 * Esta entidad hereda de la clase {@code Usuario} e incluye atributos
 * específicos para un trabajador, como el departamento y la categoría. Se mapea
 * a la tabla
 * <strong>trabajador</strong> en el esquema <strong>reto2</strong>.
 * </p>
 *
 * @author Urko
 */
@Entity
@Table(name = "trabajador", schema = "reto2")
@NamedQuery(name = "findAllTrabajador", query = "SELECT a FROM Trabajador a")
@PrimaryKeyJoinColumn(name = "usuario_id")
@XmlRootElement
public class Trabajador extends Usuario {

    /**
     * Departamento al que pertenece el trabajador.
     */
    @Column(name = "departamento")
    @Enumerated(EnumType.STRING)
    private Departamento departamento;

    /**
     * Categoría del trabajador.
     */
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    /**
     * Constructor por defecto.
     */
    public Trabajador() {
    }

    /**
     * Retorna el departamento del trabajador.
     *
     * @return el departamento del trabajador.
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Asigna el departamento al trabajador.
     *
     * @param departamento el departamento a asignar.
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Retorna la categoría del trabajador.
     *
     * @return la categoría del trabajador.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Asigna la categoría al trabajador.
     *
     * @param categoria la categoría a asignar.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Calcula el código hash basado en el identificador del usuario.
     *
     * @return el código hash del trabajador.
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
     * La comparación se basa en el identificador heredado de la clase
     * {@code Usuario}.
     * </p>
     *
     * @param object el objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    /**
     * Retorna una representación en cadena del trabajador.
     *
     * @return una cadena que contiene el identificador del trabajador.
     */
    @Override
    public String toString() {
        return "crud.entidades.Trabajador[ id=" + getId() + " ]";
    }

}
