package jdbc;

import java.util.List;

public class TestBD {
	public static void main(String[] args) {
		UsuarioDAOIMplementacion uImp = new UsuarioDAOIMplementacion();
		List<UsuarioDTO> lista = uImp.getUsuario();
		System.out.println(lista);
		UsuarioDTO u = new UsuarioDTO("Diego", "Torres");
		
		Conexion.desconectar();
		
	}
	
}
