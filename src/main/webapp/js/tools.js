/**
 * @file Fitxer d'eines d'ajuda reutilitzables en altres scripts.
 * @author Albert Garcia Llorca
 */

/**
 * @func showNotification
 * Mostra notificacions als usuaris en funció de respostes obtingudes del servidor.
 * @param resposta El contingut de la resposta generada pel servidor
 * @param missatges Objecte que encapsula missatges en funció dels codis retornats pel servidor -> 2XX: OK; 201: Recurs creat; 4XX: Error en el client, recurs no trobat; 5XX: Error en el servidor, petició no completada, etc.
 *  
 */
export default function showNotification(resposta, missatges) {
    //TODO processar la resposta i generar notificacions
    let catalegMissatges = {
        "500": "Error intern del servidor. Operació no completada",
        "511": "Autenticació de xarxa és necessària per accedir. Operació no completada",
        "501": "Mètode no implementat. Operació no completada",
        "502": "Resposta del servidor invàlida. Operació no completada",
        "503": "Servidor no disponible. Operació no completada",
        "400": "Petició errònia o mal formada, no es pot continuar",
        "401": "Cal autenticar-se per poder obtenir una resposta del servidor",
        "403": "Accés prohibit al recurs, permisos insuficients",
        "404": "No es pot trobar el recurs sol·licitat",
        "405": "Mètode no permès pel servidor. Operació no completada",
    };

    $.extend(catalegMissatges, missatges);

    //Afegim un cas genèric per a codis no previstos
    if(catalegMissatges[resposta.status] == undefined){
        catalegMissatges[resposta.status] = "El servidor ha respost amb "+resposta.status+". Contacta amb l'administrador del web per a més informació";
    }

    let cosNotificacio = catalegMissatges[resposta.status];
    //Crear notificació
    $('<div class="toast-container position-fixed bottom-0 end-0 p-3">     <div id="notificacio" class="toast" role="alert" aria-live="assertive" aria-atomic="true">     <div class="toast-header">        <i class="fa-solid fa-circle-info p-1"></i>        <strong class="me-auto">Notificació</strong>        <small></small>        <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Tanca"></button>      </div>      <div class="toast-body">       '+ cosNotificacio +'      </div>    </div>  </div>').appendTo("body");
    //Inicialitzar la notificació al JS de Bootstrap
    const notificacio = document.getElementById('notificacio');
    const toast = new bootstrap.Toast(notificacio);
    toast.show();

}

