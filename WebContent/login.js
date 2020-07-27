/**
 * 
 */

//todo OBTENER LOS DATOS DEL FORMULARIO LOGIN Y PWD INTRODUCIDOS
//POR EL CLIENTE
//Y ENVIARLOS AL SERVIDOR, PARA COMPROBAR SI ESE USUARIO EXISTE
//O ESTÁ REGISTRADO
const URL_LOGIN = "http://localhost:8080/loginjee/Login"
const URL_RELATIVA_LOGIN = "/loginjee/Login"

//https://www.bing.com/search?q=realm+adrid&cvid=6da69e504f24467c997e6a1210719880&FORM=ANNTA1&PC=U531
//SERVICIO ES SINÓNIMO DE Servlet en JAVA
var xhr = new XMLHttpRequest();
function servidorlogin()
{
	console.log("Entrando al servidor");
	var name = document.getElementById("nombre").value;
	var password = document.getElementById("pwd").value;
	
	var usuario = {nombre:name,pwd:password};
	
	var jsonusuario = JSON.stringify(usuario);
	console.log(jsonusuario);
	//si en vio info dsede el cliente al servidor debe usar post
	
	
	//TODO usar AJAX para enviar al servidor los datos
//	xhr.open ("POST", URL_LOGIN);
	xhr.open ("POST", URL_RELATIVA_LOGIN);
	xhr.onreadystatechange = respuestaLogin;//programar el callback
    xhr.send(jsonusuario);
    //console.log ("la respuesta del soervidor ha sido ..");

	
}

function respuestaLogin ()
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