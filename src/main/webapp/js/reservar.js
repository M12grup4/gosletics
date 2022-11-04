/**
 * @file Aquest script gestiona el mecanisme de reserva i anul·lació al front de l'aplicació.
 * 
 * GET /reservas/{YYYY-MM-DD}  - recupera les activitats en format JSON del dia seleccionat, només en futur
 * 
 * @author Albert Garcia Llorca
 */

import showNotification from './tools.js';

/**
 * Establim la data actual segons el client que ha realitzat la petició.
 */
const DATA_ACTUAL = Date.now();//data actual en milisegons
const DIA_EN_MILISEGONS = 86400000;

/**
 * Establim constants de la nostra API per tenir una gestió centralitzada en cas de canvis.
 */
const WEBROOT = "http://localhost:8080/gosletic";
const GET_HORARI = WEBROOT + "/reservas";

/**
 * Declarem com a variable el contenidor a on aniran els resultats rebuts del servidor a cada petició.
 */
let calendari = null;

/**
 * Un cop carregat el DOM, assignem calendari allà on s'ha de generar.
 */
$().ready(() => {
    calendari = $(".bookingSchedule container");
    //Executa trucades
    showBookableActivities();
});



/**
 * @function showBookableActivities
 * Genera una petició GET al servidor per recuperar totes les activitats que tenen lloc
 * des d'avui (segons data del client amb DATA_ACTUAL) a 14 dies endavant.
 * Qualsevol activitat que té lloc al passat (segons hora del servidor), no serà retornada
 * a la vista.
 * Amb els resultats, popula un acordió amb les dades de l'activitat i un botó per reservar si 
 * l'usuari no ha reservat, i un d'anul·lar si l'usuari ja té una reserva activa. Tenint en compte
 * que un usuari pot tenir més d'un gos registrat i que poden fer activitats diferents,
 * els dos botons han de poder coexistir a la vista.
 * El clic dels botons ha de desplegar un modal per seleccionar un dels gossos de l'usuari i efectuar
 * la reserva, el mateix per a l'anul·lació.
 */
function showBookableActivities() {
    //Elimina resultats anteriors i mostra missatge d'espera
    calendari.html("");
    $("<p>Carregant activitats, sisplau espera...</p>").appendTo(calendari);

    let dateQueried = new Date(DATA_ACTUAL).toISOString().split("T")[0];
    for (let i = 0; i < 14; i++) {
        $.getJSON(GET_HORARI + "/" + dateQueried,
            (results) => {
                calendari.html("");
                //Tracta els resultats obtinguts. Genera un element de l'acordió per al dia.
                //Desa en una col·lecció per tal d'afegir-les de cop al final.
                if (results.length > 0) {
                    createAccordionItem(Date.parse(dateQueried), results);
                };
            }
        ).catch((error) => console.log(error));
        dateQueried = new Date(Date.parse(dateQueried) + DIA_EN_MILISEGONS).toISOString().split("T")[0];
    }
}

/**
 * @function createAccordionItem
 * Genera un element HTML d'acordió per al sistema de reserves.
 * @param {Date} data Data la qual serà la que tinguin lloc les activitats
 * @param {JSON} activitiesData Col·lecció d'activitats en format JSON.
 */
function createAccordionItem(data, activitiesData) {
    //Capçalera
    $('<a href="#' + data + '" data-bs-toggle="collapse" class="nav-link px-0 align-middle "> <i class="fa-solid fa-square-caret-down"></i>    <span class="ms-1 d-none d-sm-inline">' + determineDay(data) + ' ' + data.getDate() + '/'(data.getMonth() + 1) + '</span></a>').appendTo(calendari);

    //Top llista
    $('<ul class="collapse nav flex-column ms-1" id="' + data + '">    <li class="w-100"> <div class="headers row container"><div class="col-2">HORA</div>    <div class="col-2">DURADADA</div>    <div class="col-3">ACTIVITAT</div>    <div class="col-3">DISPONIBILITAT</div></div></li>').appendTo(calendari);

    //Genera continguts
    if (activitiesData.length > 0) {
        activitiesData.forEach((element) => {
            createBookable(element);
        });
    } else {
        $("<p>No hi ha activitats programades.</p>").appendTo(calendari);
    }


    //Tanca llista
    $(' </ul>').appendTo(calendari);

    /*  <a href="#monday" data-bs-toggle="collapse" class="nav-link px-0 align-middle ">
                                 <i class="fa-solid fa-square-caret-down"></i>
                                 <span class="ms-1 d-none d-sm-inline">Dilluns</span>
                             </a>
                             <ul class="collapse nav flex-column ms-1" id="monday">
                                 <li class="w-100">
                                     <div class="headers row container">
                                         <div class="col-2">HORA</div>
                                         <div class="col-2">DURADADA</div>
                                         <div class="col-3">ACTIVITAT</div>
                                         <div class="col-3">DISPONIBILITAT</div>
                                     </div>
                                 </li>
                             </ul> */
}

/**
 * @function createBookable
 * Genera un element HTML per a l'activitat passada per paràmetre en format JSON.
 * Afegeix event listener de click per mostrar detall de l'activitat.
 * Afegeix event listener als botons de reservar i anul·lar per executar les funcions corresponents.
 * @param {json} activityData La informació continguda dins de l'objecte de l'activitat.
 * 
 */
function createBookable(activityData) {
    //Capçalera llista
    $('<li class="w-100">    <div class="row container">').appendTo(calendari);
    //Info activitat
    let activitat = $("<div class=\"col-2\">" + formatTime(activityData.h_hora) + "</div> <div class=\"col-2\">" + activityData.h_tiempo_actividad + " min.</div> <div class=\"activity col-3\">" + activityData.a_nombre + "</div> <div class=\"col-3\">" + activityData.h_plazas_ocupadas + "\/" + activityData.n_participantes_max + "</div> ").appendTo(calendari);
    //Accions
    let botoReservar = $("<div class=\"col-2\"> Reservar </div>").appendTo(calendari);
    let botoAnular = $("<div class=\"col-2\"> Anul·lar </div>").appendTo(calendari);
    //Tanca llista
    $(' </div>   </li>').appendTo(calendari);
    activitat.click(() => {
        //TODO
        //showActivityDetail(activityData.a_id);
    });
    botoReservar.click(() => {
        //TODO
    });
    botoAnular.click(() => {
        //TODO
    });
}

/**
 * @function determineDay
 * Determina el dia de la setmana i en genera un string que és retornat.
 * @param {Date} data Data per la qual ens cal saber el dia de la setmana.
 * @returns {String} Retorna un string amb el dia de la setmana.
 */
function determineDay(data) {
    let dia = data.getDay();
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