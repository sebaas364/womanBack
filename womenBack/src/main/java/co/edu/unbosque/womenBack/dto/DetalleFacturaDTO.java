package co.edu.unbosque.womenBack.dto;

import java.math.BigDecimal;

public class DetalleFacturaDTO {

	private Integer idDetalle;
	private Integer idProducto;
	private String nombreProducto;
	private Integer cantidad;
	private BigDecimal precioUnitario;
	private BigDecimal valorImpuesto;
	private BigDecimal subtotal;

	public DetalleFacturaDTO() {
	}
	

	public DetalleFacturaDTO(Integer idDetalle, Integer idProducto, String nombreProducto, Integer cantidad,
			BigDecimal precioUnitario, BigDecimal valorImpuesto, BigDecimal subtotal) {
		super();
		this.idDetalle = idDetalle;
		this.idProducto = idProducto;
		this.nombreProducto = nombreProducto;
		this.cantidad = cantidad;
		this.precioUnitario = precioUnitario;
		this.valorImpuesto = valorImpuesto;
		this.subtotal = subtotal;
	}


	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public BigDecimal getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(BigDecimal precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public BigDecimal getValorImpuesto() {
		return valorImpuesto;
	}

	public void setValorImpuesto(BigDecimal valorImpuesto) {
		this.valorImpuesto = valorImpuesto;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
}