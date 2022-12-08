/**
 * @file Script que gestiona accions al front per generar autenticació d'usuaris i desconnexió.
 * @author Albert Garcia Llorca
 */

import { showNotification, WEBROOT } from './tools.js';

//Variables de camp
let mail;
let pass;

/**
 * API calls and destinations
 */
const POST_LOGIN = WEBROOT + "/login/valida";
const GET_LOGOUT = WEBROOT + "/login/logout";
const RED_ADMIN = "./dogManagement.html";
const RED_USER = "./bookingManagement.html";
const RED_HOME = "./index.html";

/**
 * Quan el DOM està carregat, assigna els camps de formulari a les variables de camp.
 */
$().ready(() => {
    mail = $('#inputName');
    pass = $('#inputPassword');
    $('#contactButton').click(() => sendLogin());
    $('#signout').click(()=>{
        logout();
    });
});

/**
 * @function redirect
 * Aquesta funció redirigeix l'usuari en funció dels seus privilegis.
 * @param {String} isAdmin String de control true o false
 */
function redirect(isAdmin) {
    if (isAdmin == "true") {
        window.location.replace(RED_ADMIN);
    } else if (isAdmin == "false") {
        window.location.replace(RED_USER);
    } else {//Logged out
        window.location.replace(RED_HOME);
    }
}

/**
 * @function checkPermission
 * Aquesta funció comprova per a quin nivell d'usuari està especificada la pàgina que s'intenta visualitzar.
 */
function checkPermission(){
    if (LEVEL == "admin" && (localStorage.getItem("isAdmin") == "false" || localStorage.getItem("isAdmin") ==undefined)){
        window.location.replace(RED_HOME);
    }
}

/**
 * @function sendLogin
 * Envia dades introduïdes per l'usuari al servidor per autenticar-se a l'aplicació.
 * Desa dades mínimes retornades pel servidor al local storage per poder referir-se
 * a l'usuari en altres parts de l'aplicació.
 * 
 */
function sendLogin() {
    let userData = {
        "mail": mail.val(),
        "pass": pass.val(),
    };

    //Si falta algun camp obligatori, no enviar
    if (userData.mail == "" || userData.pass == "") {
        showNotification(new Response(null, { status: 418 }), { "418": "Cal omplir tots els camps per autenticar-se" });
        return;
    }

    $.post({
        url: POST_LOGIN,
        data: JSON.stringify(userData),
        contentType: 'application/json',
        success: (result, status, jqxhr) => {
            mail.val("");
            pass.val("");
            let r = result.split('#');
            localStorage.setItem('id', r[0]);
            localStorage.setItem('mail', r[1]);
            localStorage.setItem('isAdmin', r[2]);

            if (r[0] != -1) {//Si el login és correcte
                showNotification(jqxhr, { "200": "Benvingut, " + localStorage.getItem('mail') + "!" });
                redirect(r[2]);

            } else {//Si el login no és correcte
                localStorage.removeItem('id');
                localStorage.removeItem('mail');
                localStorage.removeItem('isAdmin');
                showNotification(jqxhr, { "200": "Credencials incorrectes, revisa les dades." });
            }

        },
        error: (error) => {
            console.log(error);
            showNotification(error, { "0": "Error a l'autenticar-se. Revisa les dades i prova-ho novament." });
        },
    });
}

/**
 * @function logout
 * Envia senyal de desconnexió de l'usuari al servidor per deixar d'estar autenticat.
 */
export function logout() {
    $.ajax({
        url: GET_LOGOUT,
        method: 'GET',
        success: (result, status, jqxhr) => {
            mail.val("");
            pass.val("");
            localStorage.removeItem('id');
            localStorage.removeItem('mail');
            localStorage.removeItem('isAdmin');
            showNotification(jqxhr, { "200": "Fins la propera! Estàs desconnectat." });
            redirect(null);
        },
        error: (error) => {
            console.log(error);
            showNotification(error, { "0": "Error al desconnectar-se. Prova-ho novament." });
        },
    });
    //Neteja local storage
    localStorage.removeItem('id');
    localStorage.removeItem('mail');
    localStorage.removeItem('isAdmin');
}

