package jdbc;

public class UsuarioDTO {
	private String nombre;
	private String apellidos;
	/**
	 * @param nombre
	 * @param dni
	 */
	public UsuarioDTO(String nombre, String dni) {
		this.nombre = nombre;
		this.apellidos = dni;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the dni
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setApellidos(String dni) {
		this.apellidos = dni;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UsuarioDTO [nombre=" + nombre + ", dni=" + apellidos + "]";
	}
	
}
