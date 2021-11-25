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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Encuentro")
public class Encuentro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEncuentro;
	
	@ManyToOne
	@JoinColumn(name="idMascota", nullable=false)
	private Mascota mascota;
	
	@ManyToOne
	@JoinColumn(name="idDuenio", nullable=false)
	private Duenio duenio;
	
	@NotNull(message = "La fecha es obligatoria*")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaEncuentro", nullable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaEncuentro;
	
	@DecimalMin("0.00")
	@Column(name="Precio", length=10, nullable=false)
	private double precio;
	

	public Encuentro() {
		super();
	}


	public Encuentro(int idEncuentro, Mascota mascota, Duenio duenio, Date fechaEncuentro, double precio) {
		super();
		this.idEncuentro = idEncuentro;
		this.mascota = mascota;
		this.duenio = duenio;
		this.fechaEncuentro = fechaEncuentro;
		this.precio = precio;
	}


	public int getIdEncuentro() {
		return idEncuentro;
	}


	public void setIdEncuentro(int idEncuentro) {
		this.idEncuentro = idEncuentro;
	}


	public Mascota getMascota() {
		return mascota;
	}


	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}


	public Duenio getDuenio() {
		return duenio;
	}


	public void setDuenio(Duenio duenio) {
		this.duenio = duenio;
	}


	public Date getFechaEncuentro() {
		return fechaEncuentro;
	}


	public void setFechaEncuentro(Date fechaEncuentro) {
		this.fechaEncuentro = fechaEncuentro;
	}


	public double getPrecio() {
		return precio;
	}


	public void setPrecio(double precio) {
		this.precio = precio;
	}

}
