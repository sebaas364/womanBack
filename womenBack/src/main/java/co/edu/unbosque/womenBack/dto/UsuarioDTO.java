package co.edu.unbosque.womenBack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UsuarioDTO {

	private Integer idUsuario;
	private String documento;
	private String nombres;
	private String apellidos;
	private String correo;
	private String estado;
	private Integer intentosFallidos;
	private Integer idRol;
	private String nombreRol;

	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String contrasenia;

	
	public UsuarioDTO() {
	}

	public UsuarioDTO(Integer idUsuario, String documento, String nombres, String apellidos, String correo,
			String estado, Integer intentosFallidos, Integer idRol, String nombreRol, String contrasenia) {
		super();
		this.idUsuario = idUsuario;
		this.documento = documento;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.correo = correo;
		this.estado = estado;
		this.intentosFallidos = intentosFallidos;
		this.idRol = idRol;
		this.nombreRol = nombreRol;
		this.contrasenia = contrasenia;
	}


	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Integer getIntentosFallidos() {
		return intentosFallidos;
	}

	public void setIntentosFallidos(Integer intentosFallidos) {
		this.intentosFallidos = intentosFallidos;
	}

	public Integer getIdRol() {
		return idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}
}