function revisar(elemento) {
    if (elemento.value == "") {
        elemento.className = 'error';
    } else {
        elemento.className = 'form-input';
    }
}

function revisarEmail(elemento) {
    if (elemento.value != "") {
        var dato = elemento.value;
        var expresion = /^([a-zA-Z0-9_.-])+@(([a-zA-z0-9-])+.)+([a-zA-Z0-9-]{2,4})+$/;
        if (!expresion.test(dato)) {
            elemento.className = 'error';
            return false;
        } else {
            elemento.className = 'form-input';
            return true;
        }
    }
}

function revisarFecha(elemento) {
    if (elemento.value != "") {
        var dato = elemento.value;
        var expresion = /[0-3][0-9][/][0-1][0-9][/][0-2][0-9][0-9][0-9]/;
        if (!expresion.test(dato)) {
            elemento.className = 'error';
            return false;
        } else {
            elemento.className = 'form-input';
            return true;
        }
    }
}
function revisarTelefono(elemento) {
    if (elemento.value != "") {
        var dato = elemento.value;
        var expresion = /[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]/;
        if (!expresion.test(dato)) {
            elemento.className = 'error';
            return false;
        } else {
            elemento.className = 'form-input';
            return true;
        }
    }
}
function revisarCodigo(elemento) {
    if (elemento.value != "") {
        var dato = elemento.value;
        var expresion = /[0-9][0-9][0-9][0-9][0-9]/;
        if (!expresion.test(dato)) {
            elemento.className = 'error';
            return false;
        } else {
            elemento.className = 'form-input';
            return true;
        }
    }
}
function revisarPasswords(pass1, pass2) {
    if (pass1 != pass2) {
        pass1.className = 'error';
        pass2.className = 'error';
        return false;
    } else {
        pass1.className = 'form-input';
        pass2.className = 'form-input';
        return true;
    }
}

function validar(form) {
    if (form.nombre.value === "") { //Si este campo está vacío
        alert('No has escrito tu nombre.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if (form.apellido.value === "") { //Si este campo está vacío
        alert('No has escrito el apellido.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if (form.nick.value === "") { //Si este campo está vacío
        alert('No has escrito tu login.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if (form.password.value === "") { //Si este campo está vacío
        alert('No has escrito tu contraseña.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if ((form.paswordRep.value === "") || (!revisarPasswords(form.password.value, form.paswordRep.value))) { //Si este campo está vacío
        alert('No has repetido bien tu contraseña.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if ((form.nacimiento.value === "") || (revisarFecha(form.nacimiento.value))) { //Si este campo está vacío
        alert('No has escrito tu fecha de nacimiento o es erronea.\nEjemplo: dd/mm/aaaa'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if (form.telefono.value === "") { //Si este campo está vacío
        alert('No has escrito tu telefono.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if ((form.email.value === "") || (revisarEmail(form.email.value))) { //Si este campo está vacío
        alert('No has escrito tu e-Mail o es incorrecto.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if ((form.telefono.value === "") || (revisarTelefono(form.telefono.value))) { //Si este campo está vacío
        alert('No has escrito bien tu telefono.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if (form.direccion.value === "") { //Si este campo está vacío
        alert('No has escrito tu direccion.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if ((form.codigo.value === "") || (revisarCodigo(form.codigo.value))) { //Si este campo está vacío
        alert('No has escrito bien tu codigo postal.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if (form.ciudad.value === "") { //Si este campo está vacío
        alert('No has escrito tu ciudad.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    if (form.provincia.value === "") { //Si este campo está vacío
        alert('No has escrito tu provincia.'); // Mensaje a mostrar
        return false; //devolvemos un valor negativo
    }
    return true; // Si esta todo bien, devolvemos Ok, positivo
}