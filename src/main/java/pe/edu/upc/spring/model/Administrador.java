package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Administrador")
public class Administrador implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdmin;
	
	@NotEmpty(message = "Debe ingresar su nombre*")
	@Column(name="nameAdministrador", length=20, nullable=false)
	private String nameAdmin;
	
	@Column(name="NCorreo", length=20, nullable=false)
	private String NCorreo;
	
	@ManyToOne
	@JoinColumn(name="distrito", nullable=false)
	private Distrito distrito;
	
	@NotEmpty(message = "Ingrese un nombre de usuario")
	@Column(name="username", nullable=false)
	private String username;
	
	@NotEmpty(message = "Ingrese una contraseña")
	@Column(name="password", nullable=false)
	private String password;

	public Administrador() {
		super();
	}

	public Administrador(int idAdmin, String nameAdmin, String nCorreo, Distrito distrito,
			@NotEmpty(message = "Ingrese un nombre de usuario") String username,
			@NotEmpty(message = "Ingrese una contraseña") String password) {
		super();
		this.idAdmin = idAdmin;
		this.nameAdmin = nameAdmin;
		NCorreo = nCorreo;
		this.distrito = distrito;
		this.username = username;
		this.password = password;
	}

	public int getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}

	public String getNameAdmin() {
		return nameAdmin;
	}

	public void setNameAdmin(String nameAdmin) {
		this.nameAdmin = nameAdmin;
	}

	public String getNCorreo() {
		return NCorreo;
	}

	public void setNCorreo(String nCorreo) {
		NCorreo = nCorreo;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}

