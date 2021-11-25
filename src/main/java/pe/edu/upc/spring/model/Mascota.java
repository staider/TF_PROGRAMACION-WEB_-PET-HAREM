package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Mascota")
public class Mascota implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idMascota;
	
	@NotEmpty(message = "Debe ingresar un nombre*")
	@Column(name="NMascota", length=20, nullable=false)
	private String NMascota;
	
	@NotNull(message = "La fecha es obligatoria*")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaNacimiento", nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDatePet;
	
	@OneToOne
	@JoinColumn(name="idGenero", nullable=false)
	private Genero Genero;
	
	@DecimalMin("0.00")
	@Column(name="peso", length=4, nullable=false)
	private double peso;
	
	@Column(name="tamanio", length=4, nullable=false)
	private int tamanio;
	
	@ManyToOne
	@JoinColumn(name="idDuenio", nullable=false)
	private Duenio duenio;
	
	@ManyToOne
	@JoinColumn(name="idRaza", nullable=false)
	private Raza raza;
	
	@ManyToOne
	@JoinColumn(name="idCertificado", nullable=false)
	private Certificado certificado;
	
	@ManyToOne
	@JoinColumn(name="idCertificado2", nullable=false)
	private Certificado certificado2;
	
	@ManyToOne
	@JoinColumn(name="idCertificado3", nullable=false)
	private Certificado certificado3;

	public Mascota() {
		super();
	}

	public Mascota(int idMascota, @NotEmpty(message = "Debe ingresar un nombre*") String nMascota,
			@NotNull(message = "La fecha es obligatoria*") Date birthDatePet, pe.edu.upc.spring.model.Genero genero,
			@DecimalMin("0.00") double peso, int tamanio, Duenio duenio, Raza raza, Certificado certificado,
			Certificado certificado2, Certificado certificado3) {
		super();
		this.idMascota = idMascota;
		NMascota = nMascota;
		this.birthDatePet = birthDatePet;
		Genero = genero;
		this.peso = peso;
		this.tamanio = tamanio;
		this.duenio = duenio;
		this.raza = raza;
		this.certificado = certificado;
		this.certificado2 = certificado2;
		this.certificado3 = certificado3;
	}

	public int getIdMascota() {
		return idMascota;
	}

	public void setIdMascota(int idMascota) {
		this.idMascota = idMascota;
	}

	public String getNMascota() {
		return NMascota;
	}

	public void setNMascota(String nMascota) {
		NMascota = nMascota;
	}

	public Date getBirthDatePet() {
		return birthDatePet;
	}

	public void setBirthDatePet(Date birthDatePet) {
		this.birthDatePet = birthDatePet;
	}

	public Genero getGenero() {
		return Genero;
	}

	public void setGenero(Genero genero) {
		Genero = genero;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}

	public Duenio getDuenio() {
		return duenio;
	}

	public void setDuenio(Duenio duenio) {
		this.duenio = duenio;
	}

	public Raza getRaza() {
		return raza;
	}

	public void setRaza(Raza raza) {
		this.raza = raza;
	}

	public Certificado getCertificado() {
		return certificado;
	}

	public void setCertificado(Certificado certificado) {
		this.certificado = certificado;
	}

	public Certificado getCertificado2() {
		return certificado2;
	}

	public void setCertificado2(Certificado certificado2) {
		this.certificado2 = certificado2;
	}

	public Certificado getCertificado3() {
		return certificado3;
	}

	public void setCertificado3(Certificado certificado3) {
		this.certificado3 = certificado3;
	}

}
