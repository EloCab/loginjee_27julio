//EL NOMBRE DEL USUARIO TIENE QUE SER MINIMO DE 2
//LA PWD DEL USUARIO TIENE QUE SER DE AL MENOS 2

//TODO validar formulario 
function nombreCorrecto (nombre)
{
	let correcto = false;
		
		correcto = (nombre.length >=2);
	
	return correcto;
}

function pwdCorrecto (pwd)
{
	let correcto = false;
		
		correcto = (pwd.length >=2);
	
	return correcto;
}

function activarBotonEnvio()
{
	let boton = document.getElementById("botonalta");
	boton.disabled = false;
}

function desactivarBotonEnvio()
{
	let boton = document.getElementById("botonalta");
	boton.disabled = true;
}

function modificadoFormulario() 
{
	let nuevo_nombre = document.getElementById("nombre").value;
	let nuevo_pwd = document.getElementById("pwd").value;
	
	if (nombreCorrecto(nuevo_nombre)&&(pwdCorrecto(nuevo_pwd)))
		{
			activarBotonEnvio();
		} else {
			desactivarBotonEnvio();
		}
	
	//mostrando errores personalizados
	/*let nombre_correcto =  false;
	let pwd_correcto =  false;
	
	if (nombreCorrecto(nuevo_nombre))
		{
			nombre_correcto = true;
		} else {
			mostrarErrorNombre();
		}
	if (pwdCorrecto (nuevo_pwd))
	{
		pwd_correcto = true;
	} else {
		mostrarErrorPwd();
	}
	 
	if (nombre_correcto&&ppwd_correcto)
		{
		activarBotonEnvio();
		} else {
			desactivarBotonEnvio();
		}
	*/
}