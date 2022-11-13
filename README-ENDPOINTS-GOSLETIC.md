ActividadesController

	Llista totes les activitats de Goslètics
@RequestMapping(value = "/actividades",method = RequestMethod.GET)

	Consulta del detall d'una activitat
@RequestMapping(value = "/actividades/{id}", method = RequestMethod.GET)
	
	Llista l'horari de les activitats d'un dia determinat
@RequestMapping(value = "/horario/{data}", method = RequestMethod.GET)

	Llista totes lesactivitats disponibles a partir d'un dia i hora
@RequestMapping(value = "/reservas/{data}", method = RequestMethod.GET)

	Llista tots els clients
@RequestMapping(method = RequestMethod.GET, value = "/clientes")

	Retorna la informació d'un client determinat
@RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)

GosController

	Alta de gos
     	@RequestMapping(value = "/gossos/alta", method = RequestMethod.POST)

       Modificació de les dades d'un gos
	@RequestMapping(value = "/gossos/modif", method = RequestMethod.PUT)

	Baixa de gos determinat {idGos}
	@RequestMapping(value = "/gossos/baixa/{gosid}", method = RequestMethod.DELETE)

	Consulta de tots els gossos registrats
	@RequestMapping(value = "/gossos", method = RequestMethod.GET)

	Consulta d'un gos determinat per {idGos}
	@RequestMapping(value = "/gossos/{idPerros}", method = RequestMethod.GET)

	Consulta dels gossos que té un client determinat per {idClient}
	@RequestMapping(value = "/gossos/client/{idClient}", method = RequestMethod.GET)

	Consulta d'un gos determinat per {nomGos}
	@RequestMapping(value = "/gossos/{nomGos}", method = RequestMethod.GET)


LoginController

	Valida un usuari des del formulari de Login
	@RequestMapping(value = "/login/valida", method =  RequestMethod.POST)

ReservaController
		
	Alta de Reserva
	@RequestMapping(value = "/reserva/alta", method = RequestMethod.POST)
	
	Anul·lació de Reserva per {idReserva}
	@RequestMapping(value = "/reserva/baixa/{reservaid}", method = RequestMethod.DELETE)

	Consulta de Reserves actuals de Goslèctics	
	@RequestMapping(value = "/reserva", method = RequestMethod.GET)

	Consulta d'una Reserva determinada per {idReserva}
	@RequestMapping(value = "/reserva/{idReserva}", method = RequestMethod.GET)