package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.sqlite.SQLiteConfig;

public class PruebaConexion {
	public static final String DB_URL = "jdbc:sqlite:/home/matinal/sqlite/libro";
	public static final String DRIVER = "org.sqlite.JDBC";
	public static void main(String[] args) {
		try {
			Class.forName(DRIVER);
			//Vamos a permitir la integridad referencial en sqlite
			//Debemos establecer PRAGMA fereing_keys=ON
			SQLiteConfig config = new SQLiteConfig();
			config.enforceForeignKeys(true);
			
			Connection conexion = DriverManager.getConnection(DB_URL, config.toProperties());
			System.out.println("Conectado a la base de datos");
			
			//Thread.sleep(3000); //Simulo operaciones con la BBDD 3s
			//Vamos a consultar al tabla libroque tiene de campos:
			//nombre, autor, idcategoria
			//Creamos la consulta como Striing
			String sql = "select * from alumno";
			Statement statement = conexion.createStatement();
			
			ResultSet resultado = statement.executeQuery(sql);
			
			String nombre, apellidos;
			int id;
			while(resultado.next()){
				id = resultado.getInt("id");
				nombre = resultado.getString("nombre");
				apellidos = resultado.getString("apellidos");
				System.out.printf("%s %s %d\n",nombre, apellidos, id);
			}
			//vamos a cambiar el nombre del id 2
			sql = "update alumno set nombre = 'David' where nombre='Pedro'";
			int filaAfectadas = statement.executeUpdate(sql);
			System.out.println("Filas afectadas: "+filaAfectadas);
			
			sql = "select * from alumno where id=?";
			PreparedStatement preparedStatement = conexion.prepareStatement(sql);
			for (int i = 1 ; i<6 ; i++){
				preparedStatement.setInt(1, i);
				resultado = preparedStatement.executeQuery();
				while(resultado.next()){
					nombre = resultado.getString("nombre");
					apellidos = resultado.getString("apellidos");
					System.out.printf("%15s %15s%n",nombre,apellidos);
				}
				
			}
			
			//vamos a agrupar sentencias sql usando Batch
			sql = "insert into alumno (nombre, apellidos) values (?,?)";
			preparedStatement = conexion.prepareStatement(sql);
			preparedStatement.setString(1, "Alberto");
			preparedStatement.setString(2, "Garcia");
			preparedStatement.addBatch();
			sql = "update alumno set nombre=? where id = ?";
			preparedStatement = conexion.prepareStatement(sql);
			preparedStatement.setString(1, "David");
			preparedStatement.setInt(2, 5);
			preparedStatement.addBatch();
			int[] afectados = preparedStatement.executeBatch();
			
			//vamos a hacer transacciones
			//Primero preparamos la BBDD
			
			conexion.setAutoCommit(false);
			String sql1 = "insert into alumno (nombre, apellidos) values ('Joaquin', 'Gutierrez')";
			String sql2 = "insert into alumno (nombre, apellidos) values ('Andres', 'Garcia')";
			statement.execute(sql1);
			statement.execute(sql2);
			
			conexion.commit();
			conexion.close();
			System.out.println("Desconexion a la BBDD");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
