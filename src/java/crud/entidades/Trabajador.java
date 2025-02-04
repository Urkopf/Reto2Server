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

/*
 * Esta clase representa la entidad Trabajador dentro del sistema, que hereda de la clase Usuario.
 *
 * <p>Un objeto Trabajador almacena la información específica de un usuario que es empleado dentro
 * de la organización, incluyendo detalles como el departamento y la categoría a la que pertenece.</p>
 *
 * <p>Esta entidad está mapeada a la tabla 'trabajador' del esquema 'reto2' en la base de datos.</p>
 *
 * <p>Consulta nombrada disponible:</p>
 * <ul>
 *   <li><strong>findAllTrabajador:</strong> Recupera todos los trabajadores registrados.</li>
 * </ul>
 *
 * <p>Esta clase está anotada como una entidad JPA y utiliza una clave primaria compartida con la
 * clase Usuario mediante la anotación {@link PrimaryKeyJoinColumn}.</p>
 *
 * <p>Los campos {@link #departamento} y {@link #categoria} están mapeados a sus respectivas columnas
 * en la tabla 'trabajador'. Ambos campos utilizan enumeraciones para definir los valores posibles,
 * especificados mediante la anotación {@link Enumerated} con tipo {@link EnumType#STRING}.</p>
 *
 * <p>Esta clase proporciona métodos estándar de acceso a datos como getters y setters para todos los
 * campos, así como métodos sobrescritos para {@link #hashCode()}, {@link #equals(Object)} y
 * {@link #toString()} para facilitar la comparación y representación de instancias de
 * {@code Trabajador}.</p>
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
     * Categoría del trabajador dentro de la organización.
     */
    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    /**
     * Constructor vacío de la clase Trabajador.
     */
    public Trabajador() {
    }

    /**
     * Obtiene el departamento al que pertenece el trabajador.
     *
     * @return El departamento del trabajador.
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Asigna el departamento al que pertenece el trabajador.
     *
     * @param departamento El nuevo departamento del trabajador.
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Obtiene la categoría del trabajador dentro de la organización.
     *
     * @return La categoría del trabajador.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Asigna la categoría del trabajador dentro de la organización.
     *
     * @param categoria La nueva categoría del trabajador.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Calcula el código hash del Trabajador.
     *
     * @return El código hash calculado.
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    /**
     * Compara este objeto con otro para verificar si son iguales.
     *
     * @param object El objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Trabajador)) {
            return false;
        }
        Trabajador other = (Trabajador) object;
        return (this.getId() != null || other.getId() == null) && (this.getId() == null || this.getId().equals(other.getId()));
    }

    /**
     * Retorna una representación en cadena del Trabajador.
     *
     * @return Una cadena que representa al Trabajador, incluyendo su
     * identificador único.
     */
    @Override
    public String toString() {
        return "crud.entidades.Trabajador[ id=" + getId() + " ]";
    }

}
