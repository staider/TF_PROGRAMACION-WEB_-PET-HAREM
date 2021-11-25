package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Genero")
public class Genero implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGenero;
	
	@Column(name="NGenero", length=40, nullable=false)
	private String NGenero;

	public Genero() {
		super();
	}

	public Genero(int idGenero, String nGenero) {
		super();
		this.idGenero = idGenero;
		NGenero = nGenero;
	}

	public int getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(int idGenero) {
		this.idGenero = idGenero;
	}

	public String getNGenero() {
		return NGenero;
	}

	public void setNGenero(String nGenero) {
		NGenero = nGenero;
	}
	
}
