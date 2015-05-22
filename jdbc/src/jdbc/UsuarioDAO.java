package jdbc;

import java.util.List;

public interface UsuarioDAO {
	List<UsuarioDTO> getUsuario();
	void addUsuario(UsuarioDTO usuario);
	void eliminarUsuario(UsuarioDTO usuario);
	void actualizarUsuario(UsuarioDTO usuario);
}
