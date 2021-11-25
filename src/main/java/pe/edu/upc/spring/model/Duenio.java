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
import javax.validation.constraints.Size;

@Entity
@Table(name="Duenio")
public class Duenio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDuenio;
	
	@NotEmpty(message = "Debe ingresar su nombre*")
	@Column(name="nameDuenio", length=20, nullable=false)
	private String nameDuenio;

	@NotEmpty(message = "Debe ingresar su apellido*")
	@Column(name="nameApellido", length=20, nullable=false)
	private String nameApellido;
	
	@Size(min = 8, max = 8)
	@NotEmpty(message = "Debe ingresar su DNI*")
	@Column(name="numDNI", length=8, nullable=false)
	private String numDNI;
	
	@Column(name="NCorreo", length=20, nullable=false)
	private String NCorreo;
	
	@NotEmpty(message = "Debe ingresar su número de celular*")
	@Column(name="numCelular", length=9, nullable=false)
	private String numCelular;
	
	@NotEmpty(message = "Debe ingresar su dirección*")
	@Column(name="Direccion", length=30, nullable=false)
	private String TDireccion;
	
	@ManyToOne
	@JoinColumn(name="idDistrito", nullable=false)
	private Distrito Distrito;
	
	@NotEmpty(message = "Ingrese un nombre de usuario")
	@Column(name="username", nullable=false)
	private String username;
	
	@NotEmpty(message = "Ingrese una contraseña")
	@Column(name="password", nullable=false)
	private String password;

	public Duenio() {
		super();
	}

	public Duenio(int idDuenio, @NotEmpty(message = "Debe ingresar su nombre*") String nameDuenio,
			@NotEmpty(message = "Debe ingresar su apellido*") String nameApellido,
			@Size(min = 8, max = 8) @NotEmpty(message = "Debe ingresar su DNI*") String numDNI, String nCorreo,
			@NotEmpty(message = "Debe ingresar su número de celular*") String numCelular,
			@NotEmpty(message = "Debe ingresar su dirección*") String tDireccion,
			pe.edu.upc.spring.model.Distrito distrito,
			@NotEmpty(message = "Ingrese un nombre de usuario") String username,
			@NotEmpty(message = "Ingrese una contraseña") String password) {
		super();
		this.idDuenio = idDuenio;
		this.nameDuenio = nameDuenio;
		this.nameApellido = nameApellido;
		this.numDNI = numDNI;
		NCorreo = nCorreo;
		this.numCelular = numCelular;
		TDireccion = tDireccion;
		Distrito = distrito;
		this.username = username;
		this.password = password;
	}

	public int getIdDuenio() {
		return idDuenio;
	}

	public void setIdDuenio(int idDuenio) {
		this.idDuenio = idDuenio;
	}

	public String getNameDuenio() {
		return nameDuenio;
	}

	public void setNameDuenio(String nameDuenio) {
		this.nameDuenio = nameDuenio;
	}

	public String getNameApellido() {
		return nameApellido;
	}

	public void setNameApellido(String nameApellido) {
		this.nameApellido = nameApellido;
	}

	public String getNumDNI() {
		return numDNI;
	}

	public void setNumDNI(String numDNI) {
		this.numDNI = numDNI;
	}

	public String getNCorreo() {
		return NCorreo;
	}

	public void setNCorreo(String nCorreo) {
		NCorreo = nCorreo;
	}

	public String getNumCelular() {
		return numCelular;
	}

	public void setNumCelular(String numCelular) {
		this.numCelular = numCelular;
	}

	public String getTDireccion() {
		return TDireccion;
	}

	public void setTDireccion(String tDireccion) {
		TDireccion = tDireccion;
	}

	public Distrito getDistrito() {
		return Distrito;
	}

	public void setDistrito(Distrito distrito) {
		Distrito = distrito;
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