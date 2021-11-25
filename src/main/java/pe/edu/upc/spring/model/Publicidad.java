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
@Table(name="Publicidad")
public class Publicidad implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPublicidad;
	
	@NotEmpty(message = "Debe ingresar el anuncio*")
	@Column(name="Anuncio", length=90, nullable=false)
	private String TAnuncio;
	
	@ManyToOne
	@JoinColumn(name="idEmprendimiento", nullable=false)
	private Emprendimiento Empre;
	
	@ManyToOne
	@JoinColumn(name="idPlan", nullable=false)
	private Plan Plan;

	public Publicidad() {
		super();
	}

	public Publicidad(int idPublicidad, String tAnuncio, Emprendimiento empre, pe.edu.upc.spring.model.Plan plan) {
		super();
		this.idPublicidad = idPublicidad;
		TAnuncio = tAnuncio;
		Empre = empre;
		Plan = plan;
	}

	public int getIdPublicidad() {
		return idPublicidad;
	}

	public void setIdPublicidad(int idPublicidad) {
		this.idPublicidad = idPublicidad;
	}

	public String getTAnuncio() {
		return TAnuncio;
	}

	public void setTAnuncio(String tAnuncio) {
		TAnuncio = tAnuncio;
	}

	public Emprendimiento getEmpre() {
		return Empre;
	}

	public void setEmpre(Emprendimiento empre) {
		Empre = empre;
	}

	public Plan getPlan() {
		return Plan;
	}

	public void setPlan(Plan plan) {
		Plan = plan;
	}
	
}
