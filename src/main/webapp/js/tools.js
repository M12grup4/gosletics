/**
 * @file Fitxer d'eines d'ajuda reutilitzables en altres scripts.
 * @author Albert Garcia Llorca
 */

/**
 * @func showNotification
 * Mostra notificacions als usuaris en funció de respostes obtingudes del servidor.
 * @param resposta El contingut de la resposta generada pel servidor
 * @param missatges Objecte que encapsula missatges en funció dels codis retornats pel servidor -> 2XX: OK; 201: Recurs creat; 4XX: Error en el client, recurs no trobat; 5XX: Error en el servidor, petició no completada
 *  
 */
export default function showNotification(resposta, missatges) {
    //TODO processar la resposta i generar notificacions
    let cosNotificacio = resposta;
    //Crear notificació
    $('<div class="toast-container position-fixed bottom-0 end-0 p-3">     <div id="notificacio" class="toast" role="alert" aria-live="assertive" aria-atomic="true">     <div class="toast-header">        <i class="fa-solid fa-circle-info p-1"></i>        <strong class="me-auto">Notificació</strong>        <small></small>        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Tanca"></button>      </div>      <div class="toast-body">       '+ cosNotificacio +'      </div>    </div>  </div>').appendTo("body");
    //Inicialitzar la notificació al JS de Bootstrap
    const notificacio = document.getElementById('notificacio');
    const toast = new bootstrap.Toast(notificacio);
    toast.show();

}

