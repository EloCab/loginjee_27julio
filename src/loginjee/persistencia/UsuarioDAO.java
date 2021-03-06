package loginjee.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import loginjee.bean.Usuario;

public class UsuarioDAO {
	
	//DAO Data ACCESS OBJEECT
	//ESTA CLASE, SER� INVOCADA POR EL SERVICIO
	
	private final static Logger log = Logger.getLogger("mylog");
	private static final String INSTRUCCION_CONSULTA_USUARIO = "SELECT * FROM hedima.usuarios WHERE (nombre = ?);";
	private static final String INSTRUCCION_CONSULTA_TODOS_USUARIO = "SELECT * FROM hedima.usuarios;";
	private static final String INSTRUCCION_CONSULTA_USUARIO_POR_ID = "SELECT * from hedima.usuarios where idusuarios = ?;";
	private static final String INSTRUCCION_INSERCION_USUARIO = "INSERT INTO hedima.usuarios (nombre, password) VALUES (?, ?);";
	
	
	public Usuario leerUsuarioBD (int id) throws Exception
	{
		Usuario usuario = null;
		Connection connection = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try{
			String consulta_SQL = INSTRUCCION_CONSULTA_USUARIO_POR_ID;
			connection = BaseDeDatos.getConnection();
			st = connection.prepareStatement(consulta_SQL);
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if (rs.next())
			{
				usuario = new Usuario(rs);
			}
		}catch (Exception e)
		{
			e.printStackTrace();
			throw e;
		} finally{
			BaseDeDatos.liberarRecursos(connection, st, rs);
		}
		return usuario;
	}
	
	public void altaUsuarioBD (Usuario usuario) throws Exception
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			connection = BaseDeDatos.getConnection();
			preparedStatement = connection.prepareStatement(INSTRUCCION_INSERCION_USUARIO);
			preparedStatement.setString(1, usuario.getNombre());
			preparedStatement.setString(2, usuario.getPwd());
			int resultado = preparedStatement.executeUpdate();// siempre executeUpdate para INSERTAR; DELETE; o UPDATE para SELECT executeQuery()
			log.debug("resultado insertar = " + resultado);
			
		}catch (Exception e) {
			// TODO: handle exception
			log.error("Error en la insercion de usuario " + usuario , e);
			throw e;
		}finally {
			BaseDeDatos.liberarRecursos(connection, preparedStatement, null);
		}
	}
	
	public List<Usuario> obtenerTodos () throws Exception
	{
		List<Usuario> lista_usuarios = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		Usuario usuario_aux = null;
		
		
			try {
				
				connection = BaseDeDatos.getConnection();
				statement = connection.createStatement();
				resultSet = statement.executeQuery(INSTRUCCION_CONSULTA_TODOS_USUARIO);
				
				lista_usuarios = new ArrayList<Usuario>();//creo la lista vac�a
				while (resultSet.next()) {
					usuario_aux = new Usuario(resultSet);
					lista_usuarios.add(usuario_aux);
				}
				
			}catch (Exception e) {
				log.error("Error al acceder a la base de datos", e);
				throw e;
			}finally {
				BaseDeDatos.liberarRecursos(connection, statement, resultSet);
			
			}
		
		return lista_usuarios;
		
	}
	
	/**
	 * M�todo que accede a la base de datos y comprueba si el usuario recibido existe
	 * @param usuario el usuario que queremos comprobar
	 * @return 0 si existe 1 si falla el pwd (pero existe el nombre) 2 no existe (no coincide ni nombre ni pwd)
	 * @throws Exception si hubo fallo con la base de datos
	 */
	public int  existeUsuarioBD (Usuario usuario) throws Exception
	{
		int num_dev = -1;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		

		try {
			 log.debug("Entramos en existeUsuarioBD");
			 connection = BaseDeDatos.getConnection();
			 ps = connection.prepareStatement(UsuarioDAO.INSTRUCCION_CONSULTA_USUARIO);
			 ps.setString(1, usuario.getNombre());
			 rs = ps.executeQuery();
			 if (rs.next())
			 {
				log.debug("hay un usuario que coincide por el nombre");
				Usuario usuario2 = new Usuario(rs);
				System.out.println("USUARIO RECUPERADO DE LA BD " + usuario2);
				if (usuario2.getPwd().equals(usuario.getPwd()))
				{
					log.debug ("hay un usuario que coincide por el nombre y por la contrase�a");
					num_dev = 0;
				} else 
				{
					log.debug ("hay un usuario que coincide por el nombre pero NO por la contrase�a");
					num_dev = 1;
				}
				
				//status = HttpsURLConnection.HTTP_OK;//is deprecated DEPRECADO
			} else 
			{
				log.debug("El usuario NO existe CON ESE NOMBRE");
				num_dev = 2;
			}
			
		}catch (Exception e) {
			log.error("Error al acceder a la base de datos", e);
			throw e;
			
		}
		finally {
			BaseDeDatos.liberarRecursos(connection, ps, rs);
			
		}
		
		log.debug("valor devuleto " + num_dev);
		
		return num_dev;
	}

}