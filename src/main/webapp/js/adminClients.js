/** 
 * @file Aquest script controla la gestió de clients realitzada per l'administrador.
 * Establirem gestions per a l'alta, modificació i baixa de clients del centre.
 * API Endpoints proposats (a partir de l'arrel):
 * 
 * Alta de client:
 * POST /clients/alta
 * 
 * Modificació de client:
 * PUT /clients/modif/
 * 
 * Baixa de client:
 * DELETE /clients/baixa/{idClient}
 * 
 * Recull info de client a modificar:
 * GET /clients/{idClient}
 * 
 * Executa cerca de clients:
 * GET /clients/dni/{dni}
 * 
 * @author Albert Garcia Llorca
 */

import { showNotification, WEBROOT } from './tools.js';
import { logout, checkPermission } from './logUser.js';

/**
 * Variable que recull el contenidor a on aniran els clients resultat de la consulta.
 */
let resultatConsulta = null;

//Botó alta client
const botoAlta = $("#contactButtonAlta");

//Comprova nivell de permisos
const LEVEL = "admin";
checkPermission(LEVEL);


/**
 * Accions a realitzar quan el DOM s'hagi carregat.
 */
$().ready(() => {
    botoAlta.click(() => {
        createClient();
    });

    resultatConsulta = $("#results");

    $('#cercaButton').click(() => {
        queryClients($('#qry').val());
    });

    $('#signout').click(() => {
        logout();
    });
    $('#loggedUser').html(localStorage.getItem('mail'));
    $('#greeting').html("Hola, " + localStorage.getItem('mail'));
});

/**
 * Constants trucades API.
 */
const POST_ALTA = WEBROOT + "/clients/alta";
const PUT_MODIF = WEBROOT + "/clients/modif";
const DELETE_BAIXA = WEBROOT + "/clients/baixa/";
const GET_CLIENT = WEBROOT + "/clients/";
const GET_CLIENT_NOM = WEBROOT + "/clients/dni/";

/**
 * @function createClient
 * Aquesta funció recull la informació del formulari de creació de client i l'envia com a petició POST al servidor per a la creació
 * del client.
 * Comprova que les dades dels camps siguin presents.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function createClient() {
    let clientData = {
        "nombre": $("#inputName").val(),
        "apellido1": $("#inputSurnameFirst").val(),
        "apellido2": $("#inputSurnameSecond").val(),
        "fecha_nacimiento": $("#inputBirth").val(),
        "dni": $("#inputDNI").val(),
        "email": $("#inputMail").val(),
        "calle": $("#inputStreet").val(),
        "numero": $("#inputStreetNumber").val(),
        "piso": $("#inputStreetFloor").val(),
        "cp": $("#inputStreetCode").val(),
        "poblacion": $("#inputStreetTown").val(),
        "pass": $("#inputPass").val(),
    };

    //Si falta algun camp obligatori, no enviar
    if (clientData.nombre == "" || clientData.apellido1 == "" || clientData.dni == "" || clientData.fecha_nacimiento == "" || clientData.email == "" || clientData.calle == "" || clientData.numero == "" || clientData.piso == "" || clientData.cp == "" || clientData.poblacion == "" || clientData.pass == "") {
        showNotification(new Response(null, { status: 418 }), { "418": "Cal omplir tots els camps obligatoris. Operació no finalitzada" });
        return;
    }

    $.post({
        url: POST_ALTA,
        data: JSON.stringify(clientData),
        method: 'POST',
        contentType: 'application/json',
        success: (result, status, jqxhr) => {
            $("#inputName").val("");
            $("#inputSurnameFirst").val("");
            $("#inputSurnameSecond").val("");
            $("#inputBirth").val("");
            $("#inputDNI").val("");
            $("#inputMail").val("");
            $("#inputStreet").val("");
            $("#inputStreetNumber").val("");
            $("#inputStreetFloor").val("");
            $("#inputStreetCode").val("");
            $("#inputStreetTown").val("");
            $("#inputPass").val("");
            showNotification(jqxhr, { "200": "Client " + clientData.nombre + " " + clientData.apellido1 + " creat correctament!", "201": "Client " + clientData.nombre + " " + clientData.apellido1 + " creat correctament!", "204": "Client " + clientData.nombre + " " + clientData.apellido1 + " creat correctament!" });
        },
        error: (error) => {
            console.log(error);
            showNotification(error, { "0": "Error a l'efectuar l'alta. Operació no realitzada. Contacta amb l'administrador." });
        },
    });
}

/**
 * @function updateClient
 * Aquesta funció recull la informació present del client, la popula als camps del formulari de creació de client, i en enviar
 * el formulari, actualitza les dades del client amb les que consten al formulari enviat amb PUT.
 * @param int {id} ID del client target a la BD.
 * Comprova que les dades dels camps siguin correctes i les saneja.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function updateClient(id) {
    //Neteja els botons del formulari
    $(".buttonDiv").html("");
    //Obtenir les dades del gos a modificar
    $.getJSON(GET_CLIENT + id,
        (results) => {
            //Popula els camps d'alta amb la informació rebuda
            $("#inputName").val(results.nombre);
            $("#inputSurnameFirst").val(results.apellido1);
            $("#inputSurnameSecond").val(results.apellido2);
            $("#inputBirth").val(results.fecha_nacimiento);
            $("#inputDNI").val(results.dni);
            $("#inputMail").val(results.email);
            $("#inputStreet").val(results.calle);
            $("#inputStreetNumber").val(results.numero);
            $("#inputStreetFloor").val(results.piso);
            $("#inputStreetCode").val(results.cp);
            $("#inputStreetTown").val(results.poblacion);
            $("#inputPass").val(results.pass);
        },
    );

    //Genera botó d'actualització
    let actualitza = $('<button type="button"    class="contactButton"    id="actualitzaButton">Actualitza</button>');
    actualitza.click(() => {
        let updatedClient = {
            "id": id,
            "nombre": $("#inputName").val(),
            "apellido1": $("#inputSurnameFirst").val(),
            "apellido2": $("#inputSurnameSecond").val(),
            "fecha_nacimiento": $("#inputBirth").val(),
            "dni": $("#inputDNI").val(),
            "email": $("#inputMail").val(),
            "calle": $("#inputStreet").val(),
            "numero": $("#inputStreetNumber").val(),
            "piso": $("#inputStreetFloor").val(),
            "cp": $("#inputStreetCode").val(),
            "poblacion": $("#inputStreetTown").val(),
            "pass": $("#inputPass").val(),
        };
        $.ajax({
            url: PUT_MODIF,
            method: 'PUT',
            data: JSON.stringify(updatedClient),
            contentType: 'application/json',
            success: (results, status, jqxhr) => {
                $("#inputName").val("");
                $("#inputSurnameFirst").val("");
                $("#inputSurnameSecond").val("");
                $("#inputBirth").val("");
                $("#inputDNI").val("");
                $("#inputMail").val("");
                $("#inputStreet").val("");
                $("#inputStreetNumber").val("");
                $("#inputStreetFloor").val("");
                $("#inputStreetCode").val("");
                $("#inputStreetTown").val("");
                $("#inputPass").val("");
                showNotification(jqxhr, { "201": "Client " + updatedClient.dni + " modificat correctament!", "204": "Client " + updatedClient.dni + " modificat correctament!", "200": "Client " + updatedClient.dni + " modificat correctament!" });
                $('#actualitzaButton').remove();
                //Neteja botons
                $(".buttonDiv").html("");
                //Afegeix boto alta
                botoAlta.appendTo(".buttonDiv");
                botoAlta.click(() => {
                    createClient();
                });

            },
            error: (error) => {
                console.log(error);
                showNotification(error, { "0": "Error a l'efectuar la modificació. Operació no realitzada. Contacta amb l'administrador." });
            }
        },);
    });
    actualitza.appendTo(".buttonDiv");
}

/**
 * @function deleteClient
 * Aquesta funció elimina de manera definitiva un client de la base de dades mitjançant DELETE.
 * @param int {id} ID del client target a la BD.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function deleteClient(id) {
    $.ajax(DELETE_BAIXA + id, {
        method: 'DELETE',
        success: (result, status, jqxhr) => {
            showNotification(jqxhr, { "201": "Client eliminat correctament!", "200": "Client eliminat correctament!", "204": "Client eliminat correctament!" });
        },
        error: (error) => {
            console.log(error);
            showNotification(error, { "0": "Error a l'efectuar la baixa. Operació no realitzada. Contacta amb l'administrador." })
        },
    });
}

/**
 * @function createClientItem
 * Genera un element de la taula de consulta de clients a partir de la informació rebuda pel servidor.
 * @param {JSON} clients Llistat de clients retornat pel servidor.
 */
function createClientItem(clients) {
    clients.forEach((element) => {
        let client = $('<tr class="output" id="' + element.id + '">        <td>' + element.id + '</td>        <td>' + element.nombre + '</td>        <td>' + element.apellido1 + '</td>    <td>' + element.apellido2 + '</td>    <td>' + element.fecha_nacimiento + '</td>  <td>' + element.dni + '</td>  <td>' + element.email + '</td>  <td>' + element.calle + '</td> <td>' + element.numero + '</td><td>' + element.piso + '</td> <td>' + element.cp + '</td><td>' + element.poblacion + '</td><td class="action" id="updateButton' + element.id + '">Modifica</td>        <td class="action" id="bajaButton' + element.id + '">Baja</td>    </tr>');
        client.appendTo(resultatConsulta);

        $("#updateButton" + element.id + "").click(() => {
            updateClient(element.id);
        });
        $("#bajaButton" + element.id + "").click(() => {
            deleteClient(element.id);
        });
    });
}

/**
 * @function queryClients
 * Demana al servidor una relació de clients que continguin la combinació de caràcters introduïda per l'usuari al nom.
 * Crida a {@link createClientItem} per generar elements a la vista amb els resultats.
 * @param {String} qry Input de l'usuari a cercar.
 * 
 */
function queryClients(qry) {
    //Reseteja taula
    resultatConsulta.html('<tr class="headers">    <td>ID</td>    <td>NOM</td>    <td>COGNOM 1</td>    <td>COGNOM 2</td> <td>DATA NAIX.</td>  <td>DNI</td> <td>EMAIL</td> <td>CARRER</td><td>NÚMERO</td> <td>PIS</td><td>CODI POSTAL</td> <td>POBLACIÓ</td><td>ACCIONES</td>    <td></td></tr>');
    //Executa consulta
    $.getJSON(GET_CLIENT_NOM + qry,
        (results) => {
            createClientItem(results);
        }
    ).catch((error) => {
        console.log(error);
        showNotification(error, { "0": "Error a l'efectuar la consulta." });
    }
    );
}