package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOIMplementacion implements UsuarioDAO {
	//Obtenemos la conexion como un atributo de la clase
	Connection conexion = Conexion.getConexion();
	@Override
	public List<UsuarioDTO> getUsuario() {
		// TODO Auto-generated method stub
		List<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		UsuarioDTO usuario = null;
		//sentencia sql a ejecutar
		String sql = "select * from alumno";
		try {
			Statement s = conexion.createStatement();
			ResultSet r = s.executeQuery(sql);
			while(r.next()){
				String nombre = r.getString("nombre");
				String dni = r.getString("apellidos");
				usuario = new UsuarioDTO(nombre, dni);
				lista.add(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void addUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		String sql  = "insert into alumno (nombre, apellidos) values (?,?)";
		try {
			PreparedStatement s = conexion.prepareStatement(sql);
			s.setString(1, usuario.getNombre());
			s.setString(2, usuario.getApellidos());
			s.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void eliminarUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		String sql = "delete from alumno where apellido=?";
		try {
			PreparedStatement s = conexion.prepareStatement(sql);
			s.setString(1, usuario.getApellidos());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actualizarUsuario(UsuarioDTO usuario) {
		// TODO Auto-generated method stub
		String sql = "Update alumno set nombre=? where apellido=?";
		try {
			PreparedStatement s = conexion.prepareStatement(sql);
			s.setString(1, usuario.getNombre());
			s.setString(2, usuario.getApellidos());
			s.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
