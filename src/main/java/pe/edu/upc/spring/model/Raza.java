package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Raza")
public class Raza implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRaza;
	
	@NotEmpty(message = "Debe ingresar un nombre*")
	@Column(name="NRaza", length=20, nullable=false)
	private String NRaza;

	public Raza() {
		super();
	}

	public Raza(int idRaza, String nRaza) {
		super();
		this.idRaza = idRaza;
		NRaza = nRaza;
	}

	public int getIdRaza() {
		return idRaza;
	}

	public void setIdRaza(int idRaza) {
		this.idRaza = idRaza;
	}

	public String getNRaza() {
		return NRaza;
	}

	public void setNRaza(String nRaza) {
		NRaza = nRaza;
	}
}
