package pe.edu.upc.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="Plan")
public class Plan implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPlan;
	
	@NotEmpty(message = "Ingrese el nombre del plan*")
	@Column(name="NPlan", length=30, nullable=false)
	private String NPlan;
	
	@Column(name="Costo", length=10, nullable=false)
	private double Costo;
	
	@NotNull(message = "La fecha es obligatoria")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaInicio")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaInicio;
	
	@Future(message = "La fecha debe estar en el futuro")
	@NotNull(message = "La fecha es obligatoria")
	@Temporal(TemporalType.DATE)
	@Column(name="fechaFin")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fechaFin;

	public Plan() {
		super();
	}

	public Plan(int idPlan, String nPlan, double costo, Date fechaInicio, Date fechaFin) {
		super();
		this.idPlan = idPlan;
		NPlan = nPlan;
		Costo = costo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	@PrePersist
	public void prePersist() {
		fechaInicio = new Date();
	}
	
	public int getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}

	public String getNPlan() {
		return NPlan;
	}

	public void setNPlan(String nPlan) {
		NPlan = nPlan;
	}

	public double getCosto() {
		return Costo;
	}

	public void setCosto(double costo) {
		Costo = costo;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
