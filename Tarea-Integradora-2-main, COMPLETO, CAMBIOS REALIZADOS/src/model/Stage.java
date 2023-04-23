package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Stage {

    private Etapa etapa;
	private Calendar fechaIniPlan;
	private Calendar fechaFinPlan;
    private Calendar fechaIniReal;
	private Calendar fechaFinReal;
	private State state;
	private int cantidad;
	private int numMax = 0;
	private String proyecto;
	private int numProyecto;
	
	private int tipoTec = 0;
	private int tipoGes = 0;
	private int tipoDom = 0;
	private int tipoExp = 0;

	private KnowledgeUnit[] units;

	private DateFormat formatter;

	public Stage(Etapa etapa, Calendar fechaIniPlan , Calendar fechaFinPlan, Calendar fechaIniReal, Calendar fechaFinReal, State state){
		
		this.formatter = new SimpleDateFormat("dd/M/yy");

        this.etapa = etapa;
		this.fechaIniPlan = fechaIniPlan;
		this.fechaFinPlan = fechaFinPlan;
		this.fechaIniReal = fechaIniReal;
		this.fechaFinReal = fechaFinReal;
		this.state = state;

		units = new KnowledgeUnit[50];

	}

	public boolean registerKnowledgeUnit(String id, String description, int type, String name, String cargo, String learnedLessons, int etapa1, int opcion){

		Type typeKU = Type.TECNICO;

        if (type == 1){

            typeKU = Type.TECNICO;

        }else if(type == 2){

            typeKU = Type.GESTION;

        }else if(type == 3){

            typeKU = Type.DOMINIO;

        }else if(type == 4){

            typeKU = Type.EXPERIENCIAS;
        }

        KnowledgeUnit newUnit = new KnowledgeUnit(id, description, typeKU, name, cargo, learnedLessons, etapa1, opcion);

        for(int i = 0; i < units.length; i++){

            if(units[i] == null){

                units[i] = newUnit;
                return true;
			}
        }

        return false;

	}
	
	public String getAllKnowledgeUnitsType(){

		String msg = "";

		Type typeC = Type.TECNICO;

		for(int i = 0; i < units.length; i++){

            if(units[i]!=null){

				typeC = units[i].getType();

                if(typeC == Type.TECNICO){

					tipoTec += 1;

				}
				if(typeC == Type.GESTION){

					tipoGes += 1;

				}
				if(typeC == Type.DOMINIO){

					tipoDom += 1;

				}
				if(typeC == Type.EXPERIENCIAS){

					tipoExp += 1;

				}
            }
        }

		msg += "\nCapsulas de Tipo Tecnico: " + tipoTec + "\nCapsulas de Tipo Gestion: " + tipoGes + "\nCapsulas de Tipo Dominio: " + tipoDom + "\nCapsulas de Tipo Experiencias: " + tipoExp; 

		return msg;

	}

	public String getAllLearnedLessonsKnowledgeUnits() {

		String msg = "";

		for(int i = 0; i < units.length; i++){

            if(units[i]!=null){

				msg += units[i].getLearnedLessons();

            }
        }

		return msg;

	}

	public String getProyectMostKnowledgeUnits(int i, String nombre){

		String msg = "";

		cantidad = 0;
		proyecto = nombre;

		for(int j = 0; j < units.length; j++){

            if(units[j]!=null){

				cantidad += 1;

            }
        }

		if(cantidad > numMax){

			numMax = cantidad;

			numProyecto = i;

			msg = "\nEl proyecto con más capsulas es: " + nombre + "\nCantidad de capsulas en el proyecto numero " + i + " es igual a: " + numMax; 
		}

		return msg;

	}

	public String searchColaboradorName(String nomBusqueda){

		String msg = "";
		
		String consulta = "";

		for(int j = 0; j < units.length; j++){

            if(units[j]!=null){

				consulta = units[j].getName();

				if (consulta.equals(nomBusqueda)){

					msg = "El colaborador " + nomBusqueda + " ha realizado al menos una capsula";

				}

            }
        }
		

		return msg;
	}

	public String publishKnowledgeUnit(int id, String url){

		String msg = "";

		units[id].setPublicacion(Publicacion.PUBLICADA);
		units[id].setUrl(url);

		msg += "Capsula publicada";

		return msg;

	}

	public String searchKnowledgeUnits(){

		String msg = "";

		for(int j = 0; j < units.length; j++){

			if(units[j]!=null){
				msg += "\n"+ (j+1) + ". " + units[j].getId() + ". " +units[j].getDescription();
			}

		}
		return msg;
	}

	public String approveKnowledgeUnit(int id, int cambio) {

		String msg = "";
		
		if (cambio == 1){

            units[id].setStatus(Status.APROBADO);

        }else{

            units[id].setStatus(Status.NO_APROBADO);

        }

		return msg;
	}

	public Calendar getFechaIniPlan(){
		return fechaIniPlan;
	}
	
	public Calendar getFechaFinPlan(){
		return fechaFinPlan;
	}

	public Calendar getFechaIniReal(){
		return fechaIniReal;
	}

	public Calendar getFechaFinReal(){
		return fechaFinReal;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public Etapa getEtapa(){
		return etapa;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	


    /* 
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.initialDate.getTime());
	}

	public String getFinalDateFormated() throws ParseException{
		return formatter.format(this.finalDate.getTime());
	}
    */		

    /* 
	public String getProjectInfo() throws ParseException{
		
		return "\nName: " + name + "\nClient: " + clientName + "\nProject Type: " + projectType + "\nInitial Date: " + getInitialDateFormated() + 
		"\nFinal Date: " + getFinalDateFormated() + "\nTotalBudget: " + budget + "\nGerentes encargados: " + contactos + "\nEtapa: " + etapa + ". - ACTIVA\n";
	}
    */
}   
