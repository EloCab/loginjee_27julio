package loginjee.controlador;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import loginjee.bean.Usuario;
import loginjee.servicio.UsuarioService;

/**
 * Servlet implementation class ListarUsuariosJSON
 */
@WebServlet("/ListarUsuariosJSP")
public class ListarUsuariosJSP extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final static Logger log = Logger.getLogger("mylog");

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListarUsuariosJSP() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("LLAMANDO A PEDIR USUARIOS");
		UsuarioService usuarioService = new UsuarioService();
		List<Usuario> lu = null;
		try {
			lu = usuarioService.listarUsuarios();
			//PASAR lu "beans" a UN JSP
			request.setAttribute("lu", lu);
			//request.getRequestDispatcher("listausuarios.jsp").forward(request, response);
			request.getRequestDispatcher("listausuarios2.jsp").forward(request, response);
			
			/*
			// PASAR A JSON LU.
			if ((lu != null) && (lu.size() > 0)) //AND CORTOCIRCUito
			{
				log.debug("La lista trae usuarios " + lu);
				Gson gson = new Gson();
				String lu_json = gson.toJson(lu);// pasamos de lista
				// MANDARLO EN BODY.
				response.getWriter().append(lu_json);
				// STATUS 200.
				response.setStatus(HttpURLConnection.HTTP_OK);
				// TIPO MIME application/json
				response.setContentType("application/json");
			} else {
				response.setStatus(HttpURLConnection.HTTP_NO_CONTENT);
			}*/
			// STATUS 204 NO CONTENT si la cosa fue buen, pero la lista est� vac�a

		} catch (Exception e) {
			// TODO: handle exception
			log.error("Error recuperando la lista de usarios", e);
			// STATUS 500;
			response.setStatus(HttpURLConnection.HTTP_INTERNAL_ERROR);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
	}

}