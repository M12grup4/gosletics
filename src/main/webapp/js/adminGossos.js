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
 * @function createDog
 * Aquesta funció recull la informació del formulari de creació de gos i l'envia com a petició POST al servidor per a la creació
 * del gos.
 * Comprova que les dades dels camps siguin correctes i les saneja.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function createDog(){}

/**
 * @function updateDog
 * Aquesta funció recull la informació present del gos, la popula als camps del formulari de creació de gos, i en enviar
 * el formulari, actualitza les dades del gos amb les que consten al formulari enviat amb PUT.
 * @param int {id} ID del gos target a la BD.
 * Comprova que les dades dels camps siguin correctes i les saneja.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
 function updateDog(id){}

 /**
 * @function deleteDog
 * Aquesta funció elimina de manera definitiva un gos de la base de dades mitjançant DELETE.
 * @param int {id} ID del gos target a la BD.
 * Mostra missatge de confirmació en funció de la resposta obtinguda pel servidor mitjançant la funció {@link showNotification}.
 */
function deleteDog(id){}