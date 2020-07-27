const URL_LISTAR = "http://localhost:8080/loginjee/listarUsuario"
var xhr = new XMLHttpRequest();
function servidorlistarusuario()
{
	xhr.open ("GET", URL_LISTAR);
	xhr.onreadystatechange = respuestalistarusuario;//programar el callback
    xhr.send();
    //console.log ("la respuesta del soervidor ha sido ..");

}

function respuestalistarusuario ()
{
	//TODO EJERCICIO FINDE OPCIONAL:
			//HACER QUE SE PUEDA DIFERENCIAR SI EL USARIO EXISTE O SI HA  INTRODUCIDO MAL
			//LA CONTRASEÑA --> REFINAR EL 204 

	 console.log("consultaEstado invocada estado =  " + xhr.readyState);
     if (xhr.readyState == 4) {
          if (xhr.status == 200) {
             alert ("La respuesta del servidor ha llegado");
			 location = "menu.html";
           }else if (xhr.status == 204) {
			 alert ("Credenciales incorrectas");
		   }else if (xhr.status == 403) {
			 alert ("Password incorrecta");
		   }else if (xhr.status == 500){
			  alert ("Ha habido un error; inténtelo más tarde");
	       }
    }
}