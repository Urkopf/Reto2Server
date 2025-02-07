/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * Esta clase representa la entidad base Usuario en el sistema.
 *
 * <p>Un objeto Usuario contiene la información básica de un usuario registrado en el sistema,
 * incluyendo detalles como correo electrónico, contraseña, nombre, dirección y estado de activo.</p>
 *
 * <p>Esta entidad está mapeada a la tabla 'usuario' del esquema 'reto2' en la base de datos.</p>
 *
 * <p>Consultas nombradas disponibles:</p>
 * <ul>
 *   <li><strong>findAllUsuario:</strong> Recupera todos los usuarios registrados.</li>
 *   <li><strong>inicioSesion:</strong> Verifica el inicio de sesión basado en el correo y la contraseña.</li>
 *   <li><strong>cambiar:</strong> Busca un usuario por correo y contraseña para realizar cambios.</li>
 *   <li><strong>recuperar:</strong> Recupera un usuario por su correo electrónico.</li>
 *   <li><strong>existeCorreo:</strong> Verifica la existencia de un correo electrónico en la base de datos.</li>
 *   <li><strong>esCliente:</strong> Recupera un cliente por su identificador.</li>
 *   <li><strong>esTrabajador:</strong> Recupera un trabajador por su identificador.</li>
 * </ul>
 *
 * <p>Esta clase está anotada como una entidad JPA con estrategia de herencia JOINED, permitiendo
 * extenderla para modelar entidades específicas como Cliente y Trabajador.</p>
 *
 * <p>Los campos {@link #id}, {@link #correo} y {@link #contrasena} están mapeados a sus respectivas
 * columnas en la tabla 'usuario'. El campo {@link #id} se genera automáticamente utilizando
 * estrategia {@link GenerationType#AUTO}.</p>
 *
 * <p>Esta clase proporciona métodos estándar de acceso a datos como getters y setters para todos los
 * campos, así como métodos sobrescritos para {@link #hashCode()}, {@link #equals(Object)} y
 * {@link #toString()} para facilitar la comparación y representación de instancias de {@code Usuario}.</p>
 *
 * <p>Además, esta clase define un conjunto de campos adicionales específicos que pueden ser extendidos
 * por las clases hijas para agregar más detalles según el tipo de usuario (Cliente o Trabajador).</p>
 *
 * @author Urko
 */
@Entity
@Table(name = "usuario", schema = "reto2")
@NamedQueries({
    @NamedQuery(name = "findAllUsuario", query = "SELECT a FROM Usuario a")
    ,
    @NamedQuery(name = "inicioSesion", query = "SELECT a FROM Usuario a WHERE a.correo = :correo AND a.contrasena= :contrasena")
    ,
    @NamedQuery(name = "cambiar", query = "SELECT a FROM Usuario a WHERE a.correo = :correo AND a.contrasena= :contrasena")
    ,
    @NamedQuery(name = "recuperar", query = "SELECT a FROM Usuario a WHERE a.correo = :correo")
    ,
    @NamedQuery(name = "existeCorreo", query = "SELECT COUNT(a) FROM Usuario a WHERE a.correo = :correo")
    ,
    @NamedQuery(name = "esCliente", query = "SELECT a FROM Cliente a WHERE a.id = :id")
    ,
    @NamedQuery(name = "esTrabajador", query = "SELECT a FROM Trabajador a WHERE a.id = :id")
})
@Inheritance(strategy = InheritanceType.JOINED)
@XmlRootElement
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "usuario_id")
    private Long id;

    /**
     * CIF del usuario.
     */
    private String cif;

    /**
     * Correo electrónico único del usuario.
     */
    @Column(unique = true)
    private String correo;

    /**
     * Contraseña del usuario.
     */
    @Column(unique = true)
    private String contrasena;

    /**
     * Nombre completo del usuario.
     */
    private String nombre;

    /**
     * Calle de residencia del usuario.
     */
    private String calle;

    /**
     * Ciudad de residencia del usuario.
     */
    private String ciudad;

    /**
     * Código postal de residencia del usuario.
     */
    private String codPostal;

    /**
     * Estado de activo del usuario.
     */
    private Boolean activo;

    /**
     * Constructor vacío de la clase Usuario.
     */
    public Usuario() {
    }

    // Getters y Setters
    /**
     * Obtiene el identificador único del usuario.
     *
     * @return El identificador único del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único del usuario.
     *
     * @param id El nuevo identificador único del usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el CIF del usuario.
     *
     * @return El CIF del usuario.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Asigna el CIF del usuario.
     *
     * @param cif El nuevo CIF del usuario.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Obtiene el correo electrónico único del usuario.
     *
     * @return El correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo electrónico único del usuario.
     *
     * @param correo El nuevo correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Obtiene la contraseña del usuario.
     *
     * @return La contraseña del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Asigna la contraseña del usuario.
     *
     * @param contrasena La nueva contraseña del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Obtiene el nombre completo del usuario.
     *
     * @return El nombre completo del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre completo del usuario.
     *
     * @param nombre El nuevo nombre completo del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la calle de residencia del usuario.
     *
     * @return La calle de residencia del usuario.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Asigna la calle de residencia del usuario.
     *
     * @param calle La nueva calle de residencia del usuario.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Obtiene la ciudad de residencia del usuario.
     *
     * @return La ciudad de residencia del usuario.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Asigna la ciudad de residencia del usuario.
     *
     * @param ciudad La nueva ciudad de residencia del usuario.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Obtiene el código postal de residencia del usuario.
     *
     * @return El código postal de residencia del usuario.
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Asigna el código postal de residencia del usuario.
     *
     * @param codPostal El nuevo código postal de residencia del usuario.
     */
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    /**
     * Obtiene el estado de activo del usuario.
     *
     * @return {@code true} si el usuario está activo, {@code false} de lo
     * contrario.
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Asigna el estado de activo del usuario.
     *
     * @param activo El nuevo estado de activo del usuario.
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Calcula el código hash del Usuario.
     *
     * @return El código hash calculado.
     */
    public void ocultarContraseña() {
        this.contrasena = null;
    }
  
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
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
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * Retorna una representación en cadena del objeto Usuario.
     *
     * @return Una cadena que representa al objeto Usuario, incluyendo su
     * identificador único.
     */
    @Override
    public String toString() {
        return "crud.entidades.Usuario[ id=" + id + " ]";
    }

}
