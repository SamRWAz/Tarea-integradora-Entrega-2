package model;

import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.text.ParseException;


public class Project{
	
	private String name;
	private String clientName;
	private ProjectType projectType;
	private Calendar initialDate;
	private Calendar finalDate;
	private double budget;
	private String contactos;

	private Stage[] stages;
	private Stage stage;

	private DateFormat formatter;

	public Project(String name, String clientName, ProjectType projectType, Calendar initialDate, Calendar finalDate, double budget, String contactos, Etapa etapa, Calendar fechaInicio, Calendar fechaAnalisis, Calendar fechaDiseno, Calendar fechaEjecucion, Calendar fechaCierre, Calendar fechaSeguimientoControl, Calendar fechaProvisional){
		
		this.formatter = new SimpleDateFormat("dd/M/yy");

		this.name = name;	
		this.clientName = clientName;
		this.projectType = projectType;
		this.initialDate = initialDate;
		this.finalDate = finalDate;
		this.budget = budget;
		this.contactos = contactos;

		stages = new Stage [6];

		stages[0] = new Stage(Etapa.INICIO, fechaInicio, fechaAnalisis, fechaInicio, fechaAnalisis, State.ACTIVA);
		stages[1] = new Stage(Etapa.ANALISIS, fechaAnalisis, fechaDiseno, fechaAnalisis, fechaDiseno, State.INACTIVA);
		stages[2] = new Stage(Etapa.DISENO, fechaDiseno, fechaEjecucion, fechaDiseno, fechaEjecucion, State.INACTIVA);
		stages[3] = new Stage(Etapa.EJECUCION, fechaEjecucion, fechaCierre, fechaEjecucion, fechaCierre, State.INACTIVA);
		stages[4] = new Stage(Etapa.CIERRE, fechaCierre, fechaSeguimientoControl, fechaCierre, fechaSeguimientoControl, State.INACTIVA);
		stages[5] = new Stage(Etapa.SEGUIMIENTO_CONTROL, fechaSeguimientoControl, fechaProvisional, fechaSeguimientoControl, fechaProvisional, State.INACTIVA);

	}

	public String registerKnowledgeUnit(String id, String description, int type, String name, String cargo, String learnedLessons, int opcion){

		String msg = "";

		int etapa1 = 0;

		for (int i = 0; i < stages.length; i++){

			State state = stages[i].getState();

			if (state == State.ACTIVA){

				etapa1 = i;
				
			}

		}

		if(stages[etapa1].registerKnowledgeUnit(id, description, type, name, cargo, learnedLessons, etapa1, opcion)){

            msg+= "Capsula registrada exitosamente";

        }else{

        	msg +="Memoria llena, no se pudo registrar la capsula";
        }

		return msg;
	}

	public String culminateStageState(){

		String msg = "";

		for (int i = 0; i < stages.length; i++){

			State state = stages[i].getState();
	

			if (state == State.ACTIVA){

				if (i == 0){

					msg += "Etapa Inicio finalizada - estado Inactiva. Etapa Analisis iniciada - estado Activo. " ;
		
		
				}else if (i == 1){
		
					msg += "Etapa Analisis finalizada - estado Inactiva. Etapa Diseno iniciada - estado Activo. " ;
		
		
				}else if (i == 2){
		
					msg += "Etapa Diseno finalizada - estado Inactiva. Etapa Ejecucion iniciada - estado Activo. " ;
		
		
				}else if (i == 3){
		
					msg += "Etapa Ejecucion finalizada - estado Inactiva. Etapa Cierre iniciada - estado Activo. " ;

		
				}else if (i == 4){
		
					msg += "Etapa Cierre finalizada - estado Inactiva. Etapa Seguimiento y Control iniciada - estado Activo. " ;

		
				}else if (i == 5){
		
					msg += "Etapa Seguimiento y Control finalizada - estado Inactiva. Proyecto Finalizado. " ;

		
				}
				stages[i].setState(State.INACTIVA);
				stages[i+1].setState(State.ACTIVA);
			}

		}

		return msg;
	}

	public String getName(){
		return name;
	}
	
	public String getClientName(){
		return clientName;
	}

	public ProjectType getProjectType(){
		return projectType;
	}

	public Calendar getInitialDate(){
		return initialDate;
	}
	
	public String getInitialDateFormated() throws ParseException{
		return formatter.format(this.initialDate.getTime());
	}

	public Calendar getFinalDate(){
		return finalDate;
	}

	public String getFinalDateFormated() throws ParseException{
		return formatter.format(this.finalDate.getTime());
	}		

	public double getBudget(){
		return budget;
	}

	public String getContactos(){
		return contactos;
	}

	public String getProjectInfo() throws ParseException{
		
		return "\nName: " + name + "\nClient: " + clientName + "\nProject Type: " + projectType + "\nInitial Date: " + getInitialDateFormated() + 
		"\nFinal Date: " + getFinalDateFormated() + "\nTotalBudget: " + budget + "\nGerentes encargados: " + contactos + "\nEtapa: " + etapa + ". - ACTIVA\n";
	}

	public String getAllKnowledgeUnitsType() {

		String msg = "";

		for(int i = 0; i < stages.length; i++){

            if(stages[i]!=null){
                msg += stages[i].getAllKnowledgeUnitsType();
            }
        }

		return msg;
	}

	public String getAllLearnedLessonsKnowledgeUnits(int opcion) {

		String msg = "";

            if(stages[opcion]!=null){
                msg += stages[opcion].getAllLearnedLessonsKnowledgeUnits();
            }

        return msg;
	}

	public String getProyectMostKnowledgeUnits(int i, String nombre){

		String msg = "";

		for(int j = 0; j < stages.length; j++){

            if(stages[j]!=null){
                msg += stages[j].getProyectMostKnowledgeUnits(i, nombre);
            }
        }

		return msg;
	}

	public String approveKnowledgeUnit(int id, int cambio) {

		String msg = "";
		
		msg += stage.approveKnowledgeUnit(id, cambio);

		return msg;
	}

	public String searchColaboradorName(String nomBusqueda){

		String msg = "";

		for(int j = 0; j < stages.length; j++){

            if(stages[j]!=null){
                msg += stages[j].searchColaboradorName(nomBusqueda);
            }
        }


		return msg;
	}

	public String publishKnowledgeUnit(int id, String url){

		String msg = "";

		msg += stage.publishKnowledgeUnit(id, url);

		return msg;
	}

	public String searchKnowledgeUnits(){

		String msg = "";

		for(int j = 0; j < stages.length; j++){

			if(stages[j]!=null){
				msg += stages[j].searchKnowledgeUnits();
			}

		}	
		return msg;
	}
}


