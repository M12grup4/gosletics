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
 * Recull info de gos a modificar:
 * GET /gossos/{idGos}
 * 
 * Executa cerca de gossos:
 * GET /gossos/nomGos/{nomGos}
 * 
 * @author Albert Garcia Llorca
 */

import { showNotification, WEBROOT } from './tools.js';

/**
 * Variable que recull el contenidor a on aniran els gossos resultat de la consulta.
 */
let resultatConsulta = null;

/**
 * Accions a realitzar quan el DOM s'hagi carregat.
 */
$().ready(() => {
    $('#contactButton').click(() => {
        createDog();
    });

    resultatConsulta = $("#results");

    $('#cercaButton').click(() => {
        queryDogs($('#qry').val());
    })
});

/**
 * Constants trucades API.
 */
const POST_ALTA = WEBROOT + "/gossos/alta";
const PUT_MODIF = WEBROOT + "/gossos/modif/";
const DELETE_BAIXA = WEBROOT + "/gossos/baixa/";
const GET_GOS = WEBROOT + "/gossos/";
const GET_GOS_NOM = WEBROOT + "/gossos/nomGos/";

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
        "peso": $("#inputWeight").val(),
        "sexo": $("#inputSex").val(),
        "observaciones": $("#textAreaComments").val()
    };

    //Si falta algun camp obligatori, no enviar
    if (dogData.nombre == "" || dogData.raza == "" || dogData.id_cliente == "" || dogData.fecha_nacimiento == "" || dogData.sexo == "" || dogData.peso == "") {
        showNotification(new Response(null, { status: 418 }), { "418": "Cal omplir tots els camps obligatoris. Operació no finalitzada" });
        return;
    }

    $.post({
        url: POST_ALTA,
        data: JSON.stringify(dogData),
        method: 'POST',
        contentType: 'application/json',
        success: (result, status, jqxhr) => {
            $("#inputName").val("");
            $("#inputBreed").val("");
            $("#inputOwner").val("");
            $("#inputBirth").val("");
            $("#inputWeight").val("");
            $("#inputSex").val("");
            $("#textAreaComments").val("");
            showNotification(jqxhr, { "200": "Gos " + dogData.nombre + " de l'usuari " + dogData.id_cliente + " creat correctament!" });
        }, 
        error: (error) => {
            console.log(error);
            showNotification(error, { "0": "Error a l'efectuar l'alta. Operació no realitzada. Contacta amb l'administrador." });
        },
    });
}

/**
 * @function updateDog
 * Aquesta funció recull la informació present del gos, la popula als camps del formulari de creació de gos, i en enviar
 * el formulari, actualitza les dades del gos amb les que consten al formulari enviat amb PUT.
 * @param int {id} ID del gos target a la BD.
 * Comprova que les dades dels camps siguin correctes i les saneja.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function updateDog(id) {
    //Tanquem el formulari d'alta
    let formulari = new bootstrap.Collapse.getInstance("#dogSubscription");
    formulari.hide();
    $.getJSON(GET_GOS + id,
        (results) => {
            //Popula els camps d'alta amb la informació rebuda
            $("#inputName").val(results.nombre);
            $("#inputBreed").val(results.raza);
            $("#inputOwner").val(results.id_cliente);
            $("#inputBirth").val(results.fecha_nacimiento);
            $("#inputWeight").val(results.peso);
            $("#inputSex").val(results.sexo);
            $("#textAreaComments").val(results.observaciones);
        },
    );
    //Amb la informació plasmada, obrim el formulari d'alta
    $(document).ajaxComplete(() => formulari.show());
    //Desactiva el botó d'alta
    $("#contactButton").prop("disabled", true);
    //Genera botó d'actualització
    let actualitza = $('<button type="button"    class="contactButton"    id="actualitzaButton">Actualitza</button>');
    actualitza.click(() => {
        $.ajax(PUT_MODIF + id, {
            method: 'PUT',
            contentType: 'application/json',
            success: (results, status, jqxhr) => {
                $("#inputName").val("");
                $("#inputBreed").val("");
                $("#inputOwner").val("");
                $("#inputBirth").val("");
                $("#inputWeight").val("");
                $("#inputSex").val("");
                $("#textAreaComments").val("");
                showNotification(jqxhr, { "201": "Gos " + dogData.nombre + " de l'usuari " + dogData.id_cliente + " modificat correctament!", "204": "Gos " + dogData.nombre + " de l'usuari " + dogData.id_cliente + " modificat correctament!", "200": "Gos " + dogData.nombre + " de l'usuari " + dogData.id_cliente + " modificat correctament!" });
                $('#actualitzaButton').remove();
                $("#contactButton").prop("disabled", false);
            },
            error: (error) => {
                console.log(error);
                showNotification(error, { "0": "Error a l'efectuar la modificació. Operació no realitzada. Contacta amb l'administrador." })
            }
        },);
    });
    actualitza.appendTo(".buttonDiv");
}

/**
 * @function deleteDog
 * Aquesta funció elimina de manera definitiva un gos de la base de dades mitjançant DELETE.
 * @param int {id} ID del gos target a la BD.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function deleteDog(id) {
    $.ajax(DELETE_BAIXA + id, {
        method: 'DELETE',
        success: (result, status, jqxhr) => {
            showNotification(jqxhr, { "201": "Gos eliminat correctament!", "200": "Gos eliminat correctament!", "204": "Gos eliminat correctament!" });
        },
        error: (error) => {
            console.log(error);
            showNotification(error, { "0": "Error a l'efectuar la baixa. Operació no realitzada. Contacta amb l'administrador." })
        },
    });
}

/**
 * @function createDogItem
 * Genera un element de la taula de consulta de gossos a partir de la informació rebuda pel servidor.
 * @param {JSON} gossos Llistat de gossos retornat pel servidor.
 */
function createDogItem(gossos) {
    gossos.forEach((element) => {
        let gos = $('<div class="row container" id="' + element.id + '">        <div class="col-1">' + element.id + '</div>        <div class="col-2">' + element.nombre + '</div>        <div class="col-2">' + element.raza + '</div>        <div class="col-3">' + element.idCliente + '</div>        <div class="col-2"><button type="button" class="contactButton" id="updateButton">Modifica</button></div>        <div class="col-2"><button type="button" class="contactButton" id="bajaButton">Baja</button>    </div>');
        $("#" + element.id + " td button #updateButton").click(() => {
            updateDog(element.id);
        });
        $("#" + element.id + " td button #bajaButton").click(() => {
            deleteDog(element.id);
        });
        gos.appendTo(resultatConsulta);
    });
}

/**
 * @function queryDogs
 * Demana al servidor una relació de gossos que continguin la combinació de caràcters introduïda per l'usuari al nom.
 * Crida a {@link createDogItem} per generar elements a la vista amb els resultats.
 * @param {String} qry Input de l'usuari a cercar.
 * 
 */
function queryDogs(qry) {
    //Reseteja taula
    resultatConsulta.html('<div class="headers row">    <div class="col-1">ID</div>    <div class="col-2">NOM</div>    <div class="col-2"RAÇA</div>    <div class="col-3">PROPIETARI</div>    <div class="col-4">ACCIONS</div></div>');
    //Executa consulta
    $.getJSON(GET_GOS_NOM + qry,
        (results) => {
            createDogItem(results);
        }
    ).catch((error) => {
        console.log(error);
        showNotification(error, { "0": "Error a l'efectuar la consulta." });
    }
    );
}