package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.sqlite.SQLiteConfig;

public class Conexion {
	public static Connection conexion = null;
	private Conexion(){
	}
	public static Connection getConexion(){
		String nombreBD = "jdbc:sqlite:/home/matinal/sqlite/libro";
		SQLiteConfig config = new SQLiteConfig();
		config.enforceForeignKeys(true);
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection(nombreBD, config.toProperties());
			System.out.println("Conexion establecida");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
	}
	public static void desconectar(){
		if (conexion!=null) {
			try {
				conexion.close();
				System.out.println("Desconexion establecida");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
