/**
 * @file Aquest script gestiona el mecanisme de reserva i anul·lació al front de l'aplicació.
 * 
 * GET /reservas/{YYYY-MM-DD}  - recupera les activitats en format JSON del dia seleccionat, només en futur
 * 
 * POST /reserva/alta/ - Genera una reserva 
 * 
 * DELETE /reserva/baixa/{idReserva} - Anul·la la reserva especificada
 * 
 * 
 * 
 * @author Albert Garcia Llorca
 */

import formatTime from './horari.js';
import { modal, showModal, showNotification, WEBROOT } from './tools.js';

/**
 * Usuari de prova i els seus gossos
 */
localStorage.setItem('id', 1);
let gossos_usuari = null;

/**
 * Establim la data actual segons el client que ha realitzat la petició.
 */
const DATA_ACTUAL = Date.now();//data actual en milisegons
const DIA_EN_MILISEGONS = 86400000;

/**
 * Número de dies per anticipat que es pot reservar.
 * @type {String}
 */
const NUM_DIES_ANTICIPAT = 14;

/**
 * Establim constants de la nostra API per tenir una gestió centralitzada en cas de canvis.
 */
const GET_HORARI = WEBROOT + "/reservas";
const POST_RESERVA = WEBROOT + "/reserva/alta";
const DELETE_RESERVA = WEBROOT + "/reserva/baixa/";
const GET_GOSSOS_CLIENT = WEBROOT + "/gossos/client/" + localStorage.getItem('id');

/**
 * Carreguem els gossos de l'usuari
 */
$.getJSON(GET_GOSSOS_CLIENT,
    (results) => {
        //Desa els resultats
        gossos_usuari = results;
    },
);//.catch((error) => console.log(error));

/**
 * Declarem com a variable el contenidor a on aniran els resultats rebuts del servidor a cada petició.
 */
let calendari = null;

/**
 * Variable amb els resultats de les consultes a BD.
 */
let resultats = [];

/**
 * Un cop carregat el DOM, assignem calendari allà on s'ha de generar.
 */
$().ready(() => {
    calendari = $("#bookingSchedule");
    //Executa trucades inicials
    getBookableActivities();
    $(document).ajaxComplete(() => {
        if (resultats.length == NUM_DIES_ANTICIPAT) {
            showBookableActivities(resultats);
        }
    });
});



/**
 * @function getBookableActivities
 * Genera una petició GET al servidor per recuperar totes les activitats que tenen lloc
 * des d'avui (segons data del client amb DATA_ACTUAL) a 14 dies endavant.
 * Qualsevol activitat que té lloc al passat (segons hora del servidor), no serà retornada
 * a la vista.
 * Desa els resultats en un array @see {@link resultats} que serà utilitzat per {@link showBookableActivities}.
 * Amb els resultats, popula un acordió amb les dades de l'activitat i un botó per reservar si 
 * l'usuari no ha reservat, i un d'anul·lar si l'usuari ja té una reserva activa. Tenint en compte
 * que un usuari pot tenir més d'un gos registrat i que poden fer activitats diferents,
 * els dos botons han de poder coexistir a la vista.
 * El clic dels botons ha de desplegar un modal per seleccionar un dels gossos de l'usuari i efectuar
 * la reserva, el mateix per a l'anul·lació.
 */
function getBookableActivities() {
    //Elimina resultats anteriors i mostra missatge d'espera
    calendari.html("");
    $("<p>Carregant activitats, sisplau espera...</p>").appendTo(calendari);

    let baseDate = new Date(DATA_ACTUAL);
    let dateQueried = baseDate.toISOString().split("T")[0];

    for (let i = 0; i < NUM_DIES_ANTICIPAT; i++) {
        $.getJSON(GET_HORARI + "/" + dateQueried,
            (results) => {
                //Desa els resultats
                resultats[i] = results;
            },
        ).catch((error) => console.log(error));

        dateQueried = new Date(baseDate.valueOf() + (DIA_EN_MILISEGONS * (i + 1))).toISOString().split("T")[0];


    }
}

/**
 * @function showBookableActivities
 * Genera elements HTML per ordre @see {@link createAccordionItem} i els afegeix al DOM en base als resultats obtinguts de les peticions al servidor de getBookableActivities
 * @param {Array} results Array d'objectes JSON amb les activitats de cada dia. La clau és la data de l'activitat en milisegons, el valor cada
 * activitat.
 * 
 */
function showBookableActivities(results) {
    results.sort();
    //Neteja resultats a la vista
    calendari.html("");
    let i = 0;
    results.forEach((element) => {
        if (element.length > 0) {
            createAccordionItem(new Date(element[0]["h_fecha"]), element, "AI" + i);
        }
        i++;
    });
}

/**
 * @function createAccordionItem
 * Genera un element HTML d'acordió per al sistema de reserves.
 * @param {Date} data Data la qual serà la que tinguin lloc les activitats
 * @param {JSON} activitiesData Col·lecció d'activitats en format JSON.
 * @param {String} id ID dels elements HTML generats
 */
function createAccordionItem(data, activitiesData, id) {
    //Capçalera
    $('<a href="#' + id + '" data-bs-toggle="collapse" data-bs-target="#' + id + '" class="nav-link px-0 align-middle"> <i class="fa-solid fa-square-caret-down"></i>    <span class="ms-1 d-none d-sm-inline">' + determineDay(data) + ' ' + data.getDate() + '/' + (data.getMonth() + 1) + '</span></a>').appendTo(calendari);

    //Top llista
    $('<ul class="collapse nav flex-column ms-1" id="' + id + '">    <li class="w-100"> <div class="headers row container"><div class="col-2">HORA</div>    <div class="col-2">DURADADA</div>    <div class="col-3">ACTIVITAT</div>    <div class="col-3">DISPONIBILITAT</div></div></li> </ul>').appendTo(calendari);

    //Genera continguts
    if (activitiesData.length > 0) {
        activitiesData.forEach((element) => {
            createBookable(element, data, id);
        });
    } else {
        $("<p>No hi ha activitats programades.</p>").appendTo("#" + id);
    }
}

/**
 * @function createBookable
 * Genera un element HTML per a l'activitat passada per paràmetre en format JSON.
 * Afegeix event listener de click per mostrar detall de l'activitat.
 * Afegeix event listener als botons de reservar i anul·lar per executar les funcions corresponents.
 * @param {json} activityData La informació continguda dins de l'objecte de l'activitat.
 * @param {Date} data (milisegons) Data de l'activitat
 * @param {String} id ID del UL pare
 * 
 */
function createBookable(activityData, data, id) {
    data = data.valueOf();
    //Capçalera llista
    $('<li class="w-100">    <div class="row container" id="act' + data + activityData.a_id + '"> </div>   </li>').appendTo("#" + id);
    //Info activitat
    let activitat = $("<div class=\"col-2\">" + formatTime(activityData.h_hora) + "</div> <div class=\"col-2\">" + activityData.h_tiempo_actividad + " min.</div> <div class=\"activity col-3\">" + activityData.a_nombre + "</div> <div class=\"col-3\">" + activityData.h_plazas_ocupadas + "\/" + activityData.n_participantes_max + "</div> ").appendTo("#act" + data + activityData.a_id);
    //Accions
    let botoReservar = $("<div class=\"col-1\"> Reservar </div>").appendTo("#act" + data + activityData.a_id);
    let botoAnular = $("<div class=\"col-1\"> Anul·lar </div>").appendTo("#act" + data + activityData.a_id);

    activitat.click(() => {
        //TODO
        //showActivityDetail(activityData.a_id);
    });
    botoReservar.click(() => {
        //TODO test API call
        let contingut = "<p>Selecciona gos per reservar</p>";//TODO llista dels gossos de l'usuari loginat que esta reservant. Selecciona els que vol i confirma
        let peu = $("<button>Reservar</button>");
        let gossos = createDogList(gossos_usuari);
        contingut += gossos;
        peu.click(() => createBooking({ "idCliente": localStorage.getItem('id'), "idActivitat": activityData.a_id, "fecha": activityData.h_fecha, "hora": activityData.h_hora }, $(':checked')));
        let titol = "Reserva activitat";
        showModal($(contingut), peu, titol);
    });
    botoAnular.click(() => {
        //TODO test API call
        let contingut = $("<p>Aquesta acció és irreversible.</p>");//TODO llista de les reserves existents per a aquesta activitat dels gossos de l'usuari loginat que vol anular. Selecciona els que vol i confirma
        let peu = $("<button>Anular</button>");
        //Exemple TODO obtenir les reserves de l'usuari loginat
        let ids = [5];
        peu.click(() => deleteBooking(ids));
        let titol = "ATENCIÓ!";
        showModal(contingut, peu, titol);
    });
}

/**
 * @function createBooking
 * Crea una trucada al servidor per crear una reserva per a cada gos seleccionat per l'usuari al diàleg modal de confirmació.
 * @param {JSON} dadesActivitat Dades de l'activitat a reservar
 * @param {HTMLCollection} gossos Checkboxes seleccionats 
 * 
 */
function createBooking(dadesActivitat, gossos) {
    //Obtenim els ids de gossos per als quals reservar    
    let ids_gossos = [];
    for (let i = 0; i < gossos.length; i++) {
        ids_gossos.push(gossos[i].name);
    }
    //Per cada gos, efectuem una reserva
    ids_gossos.forEach((el) => {
        dadesActivitat["idGos"] = el;
        $.post({
            url: POST_RESERVA,
            data: JSON.stringify(dadesActivitat),
            contentType: 'application/json',
            success: (results) => {
                modal.hide();
                showNotification(results, { "201": "Reserva creada correctament!", "200": "Reserva creada correctament!", "204": "Reserva creada correctament!" })
            },
            error: (error) => {
                console.log(error);
                modal.hide();
                showNotification(error, { "0": "Error a l'efectuar la reserva. Operació no realitzada. Contacta amb l'administrador." }
                );
            }
        });
    });


}

/**
 * @function deleteBooking
 * Crea una trucada al servidor per eliminar una reserva per a cada ID de reserva seleccionat per l'usuari al diàleg modal de confirmació.
 * @param {Array} ids Llista d'IDs de les reserves seleccionades per eliminar.
 * 
 */
function deleteBooking(ids) {
    ids.forEach((element) => {
        $.ajax(DELETE_RESERVA + element, {
            type: 'DELETE',
            success: (results) => {
                modal.hide();
                showNotification(results, { "201": "Reserva '+ element +' eliminada correctament!", "200": "Reserva '+ element +' eliminada correctament!", "204": "Reserva '+ element +' eliminada correctament!" });
            },
            error: (error) => {
                console.log(error);
                modal.hide();
                showNotification(error, { "0": "Error a l'efectuar l'anul·lació. Operació no realitzada. Contacta amb l'administrador." }
                );
            }
        });
    });

}

/**
 * @function determineDay
 * Determina el dia de la setmana i en genera un string que és retornat.
 * @param {Date} data Data per la qual ens cal saber el dia de la setmana.
 * @returns {String} Retorna un string amb el dia de la setmana.
 */
function determineDay(data) {
    let dia = new Date(data).getDay();
    switch (dia) {
        case 0:
            return "Diumenge";
            break;
        case 1:
            return "Dilluns";
            break;
        case 2:
            return "Dimarts";
            break;
        case 3:
            return "Dimecres";
            break;
        case 4:
            return "Dijous";
            break;
        case 5:
            return "Divendres";
            break;
        case 6:
            return "Dissabte";
            break;
    }
}

/**
 * @function createDogItem
 * Crea un element HTML amb la informació d'un dels gossos de l'usuari.
 * @param {JSON} info Informació del gos obtinguda del servidor
 * @returns {String} Element HTML de llista en format String, per ser convertit a HTML posteriorment
 */
function createDogItem(info) {
    let gos = '<div> <input type="checkbox" id="' + info.nombre + '" name="' + info.id + '"> <label for="' + info.id + '">' + info.nombre + '</label></div>';
    return gos;
}

/**
 * @function createDogList
 * Agrega els elements HTML en string de gossos de l'usuari i els remet al següent mètode, normalment afegir-los
 * al cos del modal mitjançant {@link showModal}.
 * @param {JSON} gossos Conjunt de gossos de l'usuari
 */
function createDogList(gossos) {
    let llista = "";
    if (gossos != null) {
        gossos.forEach((element) => {
            if (element != null) {
                llista += createDogItem(element);
            }
        });
    }

    return llista;

}