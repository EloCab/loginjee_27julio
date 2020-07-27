package loginjee.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.log4j.Logger;

import loginjee.bean.Usuario;

public class UsuarioDAO {
	
	//DAO Data ACCESS OBJEECT
	//ESTA CLASE, SERÁ INVOCADA POR EL SERVICIO
	
	private final static Logger log = Logger.getLogger("mylog");
	private static final String INSTRUCCION_CONSULTA_USUARIO = "SELECT * FROM hedima.usuarios WHERE (nombre = ?);";
	
	public List<Usuario> obtenerTodos ()
	{
		//AQUÍ METEMOS LO DEL API JDBC : CONNECTION, RESULTSET, ETC.
		return null;
	}
	
	/**
	 * Método que accede a la base de datos y comprueba si el usuario recibido existe
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
					log.debug ("hay un usuario que coincide por el nombre y por la contraseña");
					num_dev = 0;
				} else 
				{
					log.debug ("hay un usuario que coincide por el nombre pero NO por la contraseña");
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
