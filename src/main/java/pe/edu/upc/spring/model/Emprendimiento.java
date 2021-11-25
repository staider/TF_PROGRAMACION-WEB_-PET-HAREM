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
@Table(name="Emprendimiento")
public class Emprendimiento implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEmpre;
	
	@NotEmpty(message = "Debe ingresar un nombre*")
	@Column(name="nameEmprendimiento", length=20, nullable=false)
	private String NEmpre;
	
	@Size(min = 11, max = 11)
	@NotEmpty(message = "Debe ingresar el número de RUC*")
	@Column(name="numRUC", length=11, nullable=false)
	private String numRUC;
	
	@NotEmpty(message = "Debe ingresar la dirección*")
	@Column(name="direccion", length=20, nullable=false)
	private String TDireccion;
	
	@ManyToOne
	@JoinColumn(name="idDistrito", nullable=false)
	private Distrito Distrito;

	public Emprendimiento() {
		super();
	}

	public Emprendimiento(int idEmpre, String nEmpre, String numRUC, String tDireccion,
			pe.edu.upc.spring.model.Distrito distrito) {
		super();
		this.idEmpre = idEmpre;
		NEmpre = nEmpre;
		this.numRUC = numRUC;
		TDireccion = tDireccion;
		Distrito = distrito;
	}

	public int getIdEmpre() {
		return idEmpre;
	}

	public void setIdEmpre(int idEmpre) {
		this.idEmpre = idEmpre;
	}

	public String getNEmpre() {
		return NEmpre;
	}

	public void setNEmpre(String nEmpre) {
		NEmpre = nEmpre;
	}

	public String getNumRUC() {
		return numRUC;
	}

	public void setNumRUC(String numRUC) {
		this.numRUC = numRUC;
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

}
