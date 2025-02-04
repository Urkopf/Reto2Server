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

/**
 * Representa la entidad Usuario en el sistema.
 * <p>
 * Esta entidad se mapea a la tabla <strong>usuario</strong> del esquema
 * <strong>reto2</strong>
 * y define las propiedades básicas de un usuario, tales como credenciales,
 * datos personales y dirección. Además, utiliza la estrategia de herencia
 * {@link InheritanceType#JOINED} para permitir que entidades especializadas
 * como {@code Cliente} y {@code Trabajador} extiendan sus atributos.
 * </p>
 * <p>
 * Se definen varias consultas nombradas ({@code NamedQuery}) para operaciones
 * comunes:
 * <ul>
 * <li>{@code findAllUsuario}: Recupera todos los usuarios.</li>
 * <li>{@code inicioSesion}: Valida el inicio de sesión de un usuario basado en
 * su correo y contraseña.</li>
 * <li>{@code cambiar}: Consulta para cambiar datos basándose en el correo y
 * contraseña.</li>
 * <li>{@code recuperar}: Recupera un usuario por su correo.</li>
 * <li>{@code existeCorreo}: Verifica si un correo ya existe en el sistema.</li>
 * <li>{@code esCliente}: Verifica si un usuario es de tipo Cliente.</li>
 * <li>{@code esTrabajador}: Verifica si un usuario es de tipo Trabajador.</li>
 * </ul>
 * </p>
 * <p>
 * Ejemplo de uso:
 * <pre>
 *     Usuario usuario = new Usuario();
 *     usuario.setCorreo("usuario@ejemplo.com");
 *     usuario.setContrasena("password123");
 *     // Asignar otros atributos según sea necesario...
 * </pre>
 * </p>
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
     * Código de Identificación Fiscal (CIF) del usuario.
     */
    private String cif;

    /**
     * Correo electrónico único del usuario.
     */
    @Column(unique = true)
    private String correo;

    /**
     * Contraseña única del usuario.
     */
    @Column(unique = true)
    private String contrasena;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Calle del domicilio del usuario.
     */
    private String calle;

    /**
     * Ciudad del domicilio del usuario.
     */
    private String ciudad;

    /**
     * Código postal del domicilio del usuario.
     */
    private String codPostal;

    /**
     * Indica si el usuario se encuentra activo.
     */
    private Boolean activo;

    /**
     * Constructor por defecto.
     */
    public Usuario() {
    }

    /**
     * Retorna el identificador único del usuario.
     *
     * @return el id del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Asigna el identificador único del usuario.
     *
     * @param id el id a asignar.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retorna el CIF del usuario.
     *
     * @return el CIF.
     */
    public String getCif() {
        return cif;
    }

    /**
     * Asigna el CIF del usuario.
     *
     * @param cif el CIF a asignar.
     */
    public void setCif(String cif) {
        this.cif = cif;
    }

    /**
     * Retorna el correo electrónico del usuario.
     *
     * @return el correo.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo electrónico del usuario.
     *
     * @param correo el correo a asignar.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna la contraseña del usuario.
     *
     * @return la contraseña.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Asigna la contraseña del usuario.
     *
     * @param contrasena la contraseña a asignar.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Retorna el nombre del usuario.
     *
     * @return el nombre.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Asigna el nombre del usuario.
     *
     * @param nombre el nombre a asignar.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna la calle del usuario.
     *
     * @return la calle.
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Asigna la calle del usuario.
     *
     * @param calle la calle a asignar.
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Retorna la ciudad del usuario.
     *
     * @return la ciudad.
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Asigna la ciudad del usuario.
     *
     * @param ciudad la ciudad a asignar.
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Retorna el código postal del usuario.
     *
     * @return el código postal.
     */
    public String getCodPostal() {
        return codPostal;
    }

    /**
     * Asigna el código postal del usuario.
     *
     * @param codPostal el código postal a asignar.
     */
    public void setCodPostal(String codPostal) {
        this.codPostal = codPostal;
    }

    /**
     * Retorna el estado de actividad del usuario.
     *
     * @return {@code true} si el usuario está activo, de lo contrario
     * {@code false}.
     */
    public Boolean getActivo() {
        return activo;
    }

    /**
     * Asigna el estado de actividad del usuario.
     *
     * @param activo el estado a asignar.
     */
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    /**
     * Calcula el código hash basado en el identificador único del usuario.
     *
     * @return el código hash.
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
     * La comparación se realiza en base al identificador único del usuario.
     * </p>
     *
     * @param object el objeto a comparar.
     * @return {@code true} si los objetos son iguales; de lo contrario,
     * {@code false}.
     */
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
     * Retorna una representación en forma de cadena del usuario.
     *
     * @return una cadena que contiene el identificador del usuario.
     */
    @Override
    public String toString() {
        return "crud.entidades.Usuario[ id=" + id + " ]";
    }

}
