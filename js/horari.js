/**
 * Aquest script controla l'enviament de peticions per part de l'usuari des del client i la recepció d'informació del servidor al client i l'organitza
 * per a ser visualitzada a la interfície d'usuari. 
 */

/**
 * Establim la data actual segons el host que ha realitzat la petició.
 * Obtenim també el dia de la setmana corresponent (Diumenge 0 - Dissabte 6).
 */
const DATA_ACTUAL = Date.now();//data actual en milisegons
const DIA_SETMANA = new Date(DATA_ACTUAL).getDay();

/**
 * Establim constants de la nostra API per tenir una gestió centralitzada en cas de canvis.
 */
const WEBROOT = "http://127.0.0.1";
const GET_HORARI = WEBROOT + "/actividades";

/**
 * Obtenim del DOM el contenidor a on aniran els resultats rebuts del servidor a cada petició.
 * Obtenim del DOM l'element (inicialment amagat) a on van els detalls de l'activitat.
 */
let resultats = null;
let detall = null;
$().ready(() => {
    resultats = $("#results");
    detall = $('#detail');
    assignDateToButtons();
});


/**
 * Genera una petició GET al servidor per recuperar totes les activitats que tenen lloc
 * en cert dia de la setmana, basat en la selecció de l'usuari per mitjà de la botonera 
 * amb els dies de la setmana vigent a la constant DATA_ACTUAL.
 * Amb els resultats, genera un element HTML nou que s'afegeix als resultats que l'usuari
 * veurà a la vista.
 * @param {String} selectedDate Data seleccionada per l'usuari en format YYYY-MM-DD 
 */
function showActivitiesOnDate(selectedDate) {
    //Elimina resultats anteriors i mostra missatge d'espera
    resultats.html("");
    $("<p>Carregant horaris, sisplau espera...</p>").appendTo(resultats);

    let llistat = null;
    $.getJSON(GET_HORARI,
        { dia: selectedDate },
        (data) => {
            assignDateToButtons();
            //TODO handle results - generate as many HTML elements as necessary and save them in a collection "llistat"
        })
        .catch((error) => console.log(error))
        .done((llistat) => {
            //Attach the generated results to the DOM
            resultats.html("");
            $("<p>Col·lecció d'elements HTML</p>").appendTo(resultats);
        });

}

/**
 * Genera una petició GET al servidor per obtenir la descripció de l'activitat
 * seleccionada per l'usuari. L'activitat seleccionada per l'usuari es passa com
 * a paràmetre al mètode i és l'ID de l'activitat a la base de dades.
 * Retorna un element HTML que s'afegeix al contenidor amb ID detall del DOM.
 * @param {String} selectedActivity ID de l'activitat seleccionada per l'usuari
 * @returns {HTMLElement}
 */
function showActivityDetail(selectedActivity) {
    //Elimina text anterior
    detall.html("");
    $("<p>Carregant informació, sisplau espera...</p>").appendTo(detall);

    let info = null;
    $.getJSON(GET_HORARI,
        { activitat: selectedActivity },
        (data) => {
            //TODO handle results - generate new HTML element and save to "llistat"
        })
        .catch((error) => console.log(error))
        .done((info) => {
            //Attach the generated results to the DOM
            detall.html("");
            $("<p>" + info.descripcion + "</p>").appendTo(detall);
            detall.css('display', 'inline-block');
        });

}

/**
 * Genera un element HTML per a l'activitat passada per paràmetre en format JSON.
 * Aquest element es podrà afegir al DOM posteriorment.
 * @param {json} activityData La informació continguda dins de l'objecte de l'activitat.
 * @returns {HTMLElement} Element HTML per afegir
 */
function createActivity(activityData) {
    //TODO
}

/**
 * Afegeix un atribut nou a cada botó HTML de dia de la setmana del DOM
 * en funció de la setmana en què ens trobem. Aquest atribut servirà com a paràmetre
 * a les peticions GET. Format: YYYY-MM-DD.
 */
function assignDateToButtons() {
    //Determinem quin dia és diumenge
    const DIA_EN_MILISEGONS = 86400000;
    let diumenge = new Date(DATA_ACTUAL - (DIA_SETMANA * DIA_EN_MILISEGONS));

    //Afegim els atributs de dia a cada botó i n'actualitzem l'etiqueta.
    //Afegim 24h entre dia i dia. Com que el primer diumenge és el de la setmana anterior,
    //sumarem un dia més per començar, i al final també per al diumenge de la
    //setmana vigent.
    diumenge.setTime(diumenge.getTime() + DIA_EN_MILISEGONS);

    $('.Monday').attr('dia', diumenge.toISOString().split("T")[0]);
    $('.Monday').html("DL " + diumenge.getDate() + "/" + diumenge.getMonth());
    diumenge.setTime(diumenge.getTime() + DIA_EN_MILISEGONS);

    $('.Tuesday').attr('dia', diumenge.toISOString().split("T")[0]);
    $('.Tuesday').html("DT " + diumenge.getDate() + "/" + diumenge.getMonth());
    diumenge.setTime(diumenge.getTime() + DIA_EN_MILISEGONS);

    $('.Wednesday').attr('dia', diumenge.toISOString().split("T")[0]);
    $('.Wednesday').html("DC " + diumenge.getDate() + "/" + diumenge.getMonth());
    diumenge.setTime(diumenge.getTime() + DIA_EN_MILISEGONS);

    $('.Thursday').attr('dia', diumenge.toISOString().split("T")[0]);
    $('.Thursday').html("DJ " + diumenge.getDate() + "/" + diumenge.getMonth());
    diumenge.setTime(diumenge.getTime() + DIA_EN_MILISEGONS);

    $('.Friday').attr('dia', diumenge.toISOString().split("T")[0]);
    $('.Friday').html("DV " + diumenge.getDate() + "/" + diumenge.getMonth());
    diumenge.setTime(diumenge.getTime() + DIA_EN_MILISEGONS);

    $('.Saturday').attr('dia', diumenge.toISOString().split("T")[0]);
    $('.Saturday').html("DS " + diumenge.getDate() + "/" + diumenge.getMonth());
    diumenge.setTime(diumenge.getTime() + DIA_EN_MILISEGONS);

    $('.Sunday').attr('dia', diumenge.toISOString().split("T")[0]);
    $('.Sunday').html("DG " + diumenge.getDate() + "/" + diumenge.getMonth());

    //Assignem els comportaments de cada botó per al dia que representen
    assignBehaviourToDayButtons();
}

/**
 * Assigna els comportaments de click a cada botó de dia de la setmana
 * per tal de fer la petició GET corresponent.
 */
function assignBehaviourToDayButtons() {
    $('.Monday').click(() => { showActivitiesOnDate($('.Monday').attr('dia')) });
    $('.Tuesday').click(() => { showActivitiesOnDate($('.Tuesday').attr('dia')) });
    $('.Wednesday').click(() => { showActivitiesOnDate($('.Wednesday').attr('dia')) });
    $('.Thursday').click(() => { showActivitiesOnDate($('.Thursday').attr('dia')) });
    $('.Friday').click(() => { showActivitiesOnDate($('.Friday').attr('dia')) });
    $('.Saturday').click(() => { showActivitiesOnDate($('.Saturday').attr('dia')) });
    $('.Sunday').click(() => { showActivitiesOnDate($('.Sunday').attr('dia')) });
}