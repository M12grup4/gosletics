/** 
 * @file Aquest script controla la gestió de gossos realitzada per l'administrador.
 * Establirem gestions per a l'alta, modificació i baixa de gossos d'un usuari.
 * API Endpoints proposats (a partir de l'arrel):
 * 
 * Alta de gos:
 * POST /gossos/alta
 * 
 * Modificació de gos:
 * PUT /gossos/modif/{idGos}
 * 
 * Baixa de gos:
 * DELETE /gossos/baixa/{idGos}
 * 
 * @author Albert Garcia Llorca
*/

import showNotification from './tools.js';

/**
 * Accions a realitzar quan el DOM s'hagi carregat.
 */
$().ready(() => {
    $('#contactButton').click(() => {
        createDog()
    });
});

/**
 * Constants trucades API.
 */
const WEBROOT = "http://localhost:8080/gosletic";
const POST_ALTA = WEBROOT + "/gossos/alta";
const PUT_MODIF = WEBROOT + "/gossos/modif/";
const DELETE_BAIXA = WEBROOT + "/gossos/baixa/";

/**
 * @function createDog
 * Aquesta funció recull la informació del formulari de creació de gos i l'envia com a petició POST al servidor per a la creació
 * del gos.
 * Comprova que les dades dels camps siguin presents.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function createDog() {
    let dogData = {
        "nombre": $("#inputName").val(),
        "raza": $("#inputBreed").val(),
        "id_cliente": $("#inputOwner").val(),
        "fecha_nacimiento": $("#inputBirth").val(),
        "observaciones": $("#textAreaComments").val(),
    }
    //Si falta algun camp obligatori, no enviar
    if (dogData.nombre == "" || dogData.raza == "" || dogData.id_cliente == "" || dogData.fecha_nacimiento == "") {
        showNotification(new Response(null, { status: 418 }), { "418": "Cal omplir tots els camps obligatoris. Operació no finalitzada" });
        return;
    }

    $.post(POST_ALTA, dogData, (result) => {
        $("#inputName").val("");
        $("#inputBreed").val("");
        $("#inputOwner").val("");
        $("#inputBirth").val("");
        $("#textAreaComments").val("");
        showNotification(result, {"201": "Gos "+dogData.nombre+" de l'usuari "+dogData.id_cliente+" creat correctament!"});
    }).catch((error) => {console.log(error);showNotification(error, {"0": "Error a l'efectuar l'alta. Operació no realitzada. Contacta amb l'administrador."})});
}

/**
 * @function updateDog
 * Aquesta funció recull la informació present del gos, la popula als camps del formulari de creació de gos, i en enviar
 * el formulari, actualitza les dades del gos amb les que consten al formulari enviat amb PUT.
 * @param int {id} ID del gos target a la BD.
 * Comprova que les dades dels camps siguin correctes i les saneja.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function updateDog(id) { }

/**
* @function deleteDog
* Aquesta funció elimina de manera definitiva un gos de la base de dades mitjançant DELETE.
* @param int {id} ID del gos target a la BD.
* Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
*/
function deleteDog(id) { }