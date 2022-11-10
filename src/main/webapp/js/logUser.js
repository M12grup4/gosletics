/**
 * @file Script que gestiona accions al front per generar autenticació d'usuaris i desconnexió.
 * @author Albert Garcia Llorca
 */

import { showNotification, WEBROOT } from './tools.js';

//Variables de camp
let mail;
let pass;

/**
 * API calls
 */
const GET_LOGIN = WEBROOT + "/login/valida";
//const GET_LOGOUT = WEBROOT + "";

/**
 * Quan el DOM està carregat, assigna els camps de formulari a les variables de camp
 */
$().ready(() => {
    mail = $('#mail');
    pass = $('#pass');
});

/**
 * @function sendLogin
 * Envia dades introduïdes per l'usuari al servidor per autenticar-se a l'aplicació.
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
        url: GET_LOGIN,
        data: JSON.stringify(userData),
        method: 'GET',
        contentType: 'application/json',
        success: (result, status, jqxhr) => {
            mail.val("");
            pass.val("");
            showNotification(jqxhr, { "200": "Benvingut, " + userData.mail + "!" });
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
function logout() {
    $.ajax({
        url: GET_LOGOUT,
        method: 'GET',
        contentType: 'application/json',
        success: (result, status, jqxhr) => {
            mail.val("");
            pass.val("");
            showNotification(jqxhr, { "200": "Fins la propera! Estàs desconnectat." });
        }, 
        error: (error) => {
            console.log(error);
            showNotification(error, { "0": "Error al desconnectar-se. Prova-ho novament." });
        },
    });
 }

