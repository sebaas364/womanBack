package co.edu.unbosque.womenBack.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Integer idCliente;

    @Column(name = "tipo_documento", nullable = false, length = 5)
    private String tipoDocumento;

    @Column(name = "documento", nullable = false, length = 20, unique = true)
    private String documento;

    @Column(name = "nombre", nullable = false, length = 120)
    private String nombre;

    @Column(name = "correo", length = 120)
    private String correo;

    @Column(name = "telefono", length = 20)
    private String telefono;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String tipoDocumento, String documento, String nombre, String correo,
			String telefono) {
		super();
		this.idCliente = idCliente;
		this.tipoDocumento = tipoDocumento;
		this.documento = documento;
		this.nombre = nombre;
		this.correo = correo;
		this.telefono = telefono;
	}

	public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}