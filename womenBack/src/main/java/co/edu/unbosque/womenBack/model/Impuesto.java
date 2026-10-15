package co.edu.unbosque.womenBack.model;

import java.math.BigDecimal;

import jakarta.persistence.*;

@Entity
@Table(name = "impuesto")
public class Impuesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_impuesto")
	private Integer idImpuesto;

	@Column(name = "nombre", nullable = false, length = 30)
	private String nombre;

	@Column(name = "tarifa", nullable = false, precision = 5, scale = 2)
	private BigDecimal tarifa;

	public Impuesto() {
	}
	
	public Impuesto(Integer idImpuesto, String nombre, BigDecimal tarifa) {
		super();
		this.idImpuesto = idImpuesto;
		this.nombre = nombre;
		this.tarifa = tarifa;
	}


	public Integer getIdImpuesto() {
		return idImpuesto;
	}

	public void setIdImpuesto(Integer idImpuesto) {
		this.idImpuesto = idImpuesto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal getTarifa() {
		return tarifa;
	}

	public void setTarifa(BigDecimal tarifa) {
		this.tarifa = tarifa;
	}
}