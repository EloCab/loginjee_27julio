package loginjee.servicio;

import java.util.List;

import org.apache.log4j.Logger;

import loginjee.bean.Usuario;
import loginjee.persistencia.UsuarioDAO;

public class UsuarioService {
	// TENDEREMOS QUE TENER TODOS LOS MÉTODOS RELATIVOS AL TRABAJO CON USUARIOS
	// ESTA CLASE SERÁ INVOCADA POR EL SERVLET
	// Y ESTA CLASE, INVOCARÁ A LA PERSISTENCIA (EN CASO DE SER NECESARIO)

	private final static Logger log = Logger.getLogger("mylog");

	public Usuario obtenerUsuario(int id_usuario) throws Exception {
		Usuario usuario = null;
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		usuario = usuarioDAO.leerUsuarioBD(id_usuario);

		return usuario;
	}

	public List<Usuario> listarUsuarios() throws Exception {
		List<Usuario> lUsuarios = null;
		UsuarioDAO usuarioDAO = new UsuarioDAO();

		lUsuarios = usuarioDAO.obtenerTodos();

		return lUsuarios;
	}

	public boolean nombreDisponible(String nombre) throws Exception {
		boolean disponible = false;

		List<Usuario> lu = null;

		lu = this.listarUsuarios();//sería más optimo hacer una consulta por nombre en usuarioDAO
		if (lu != null && lu.size() > 0) {
			boolean encontrado = false;
			int pos = 0;
			Usuario usuario_aux = null;
			do {
				usuario_aux = lu.get(pos);
				if (usuario_aux.getNombre().equals(nombre)) {
					encontrado = true;
				} else {
					pos = pos + 1;
				}

			} while (!(encontrado) && (pos < lu.size()));
			disponible = !encontrado;
		} else {
			disponible = true;
		}

		return disponible;
	}

	/**
	 * Método que comprueba si el usuario recibido existe en el sistema
	 * 
	 * @param usuario el usuario que queremos comprobar
	 * @return 0 si existe 1 si falla el pwd (pero existe el nombre) 2 no existe (no
	 *         coincide ni nombre ni pwd)
	 * @throws Exception si hubo fallo con la base de datos
	 */

	public int existeUsuario(Usuario usuario) throws Exception {
		int num_dev = -1;
		UsuarioDAO usuarioDAO = null;

		log.debug("Entramos en servicio existeUsuario");

		usuarioDAO = new UsuarioDAO();
		num_dev = usuarioDAO.existeUsuarioBD(usuario);

		log.debug("Salimos en servicio existeUsuario valor devuelto = " + num_dev);

		return num_dev;

	}

	public void altaUsuario(Usuario usuario) throws Exception {
		UsuarioDAO usuarioDAO = null;

		usuarioDAO = new UsuarioDAO();
		usuarioDAO.altaUsuarioBD(usuario);
		log.debug("Salimos del servicio altaUsuario ");

	}

}