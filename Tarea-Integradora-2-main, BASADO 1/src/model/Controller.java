package model;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;

public class Controller {

	private Project[] projects;
	private KnowledgeUnit[] units;
	private Project project;

	public Controller() {

		projects = new Project[10];
		units = new KnowledgeUnit[50];
	
	}
	
	public boolean RegisterProject(String name, String clientName, int projectType,int diaInicio, int mesInicio, int anInicio, int diaFinal, int mesFinal, int anFinal, Double budget, String mensaje, int mesIniPlan, int mesAnaPlan, int mesDisPlan, int mesEjePlan, int mesCiePlan, int mesSyCPlan, int mesIniReal, int mesAnaReal, int mesDisReal, int mesEjeReal, int mesCieReal, int mesSyCReal) {

		ProjectType projectTypeN = ProjectType.POR_DEFINIR;

		Etapa etapa = Etapa.INICIO;

		Calendar fechaProvisional = new GregorianCalendar(diaInicio, mesInicio, anInicio);

		Calendar fechaIni = new GregorianCalendar(diaInicio, mesInicio, anInicio);

		Calendar fechaFini = new GregorianCalendar(diaFinal, mesFinal, anFinal);

		Calendar fechaInicio = fechaProvisional;

		fechaProvisional.add(Calendar.MONTH, mesIniPlan);

		Calendar fechaAnalisis = fechaProvisional;

		fechaProvisional.add(Calendar.MONTH, mesAnaPlan);

		Calendar fechaDiseno = fechaProvisional;

		fechaProvisional.add(Calendar.MONTH, mesDisPlan);

		Calendar fechaEjecucion = fechaProvisional;
		
		fechaProvisional.add(Calendar.MONTH, mesEjePlan);

		Calendar fechaCierre = fechaProvisional;

		fechaProvisional.add(Calendar.MONTH, mesCiePlan);

		Calendar fechaSeguimientoControl = fechaProvisional;

		fechaProvisional.add(Calendar.MONTH, mesSyCPlan);


        if (projectType == 1){

            projectTypeN = ProjectType.DESARROLLO;

        }else if (projectType == 2){

            projectTypeN = ProjectType.MANTENIMIENTO;

        }else{

			projectTypeN = ProjectType.DESPLIEGUE;

		}

        Project newProject = new Project(name, clientName, projectTypeN, fechaIni, fechaFini, budget, mensaje, etapa, fechaInicio, fechaAnalisis, fechaDiseno, fechaEjecucion, fechaCierre, fechaSeguimientoControl, fechaProvisional);

        for(int i = 0; i < projects.length; i++){

            if(projects[i] == null){

                projects[i] = newProject;
                return true;
			}
        }

        return false;
		
	}


	// Date class also has their own before() and after() method
	public String searchProjectsAfterDate(int diaIniBusqueda, int mesIniBusqueda, int anIniBusqueda) throws ParseException {

		String msg = "";

		Calendar fechaComparacion = new GregorianCalendar(diaIniBusqueda, mesIniBusqueda, anIniBusqueda);
		
		for(int i = 0; i < projects.length; i++){
			if(projects[i] != null){

				int val = fechaComparacion.compareTo(projects[i].getInitialDate());

				if (val == -1){

					msg += "\n"+ (i+1) + ". " + projects[i].getProjectInfo();

				}

			}
		}	

		return msg;
	}
	
	// Date class also has their own before() and after() method
	public String searchProjectsBeforeDate(int diaIniBusqueda, int mesIniBusqueda, int anIniBusqueda) throws ParseException {

		String msg = "";

		Calendar fechaComparacion = new GregorianCalendar(diaIniBusqueda, mesIniBusqueda, anIniBusqueda);
		
		for(int i = 0; i < projects.length; i++){
			if(projects[i] != null){

				int val = fechaComparacion.compareTo(projects[i].getFinalDate());

				if (val == 1){

					msg += "\n"+ (i+1) + ". " + projects[i].getProjectInfo();

				}

			}
		}	

		return msg;

	}

	public String culminateProjectState(int id) {

		String msg = "";

		Date fechaActual = new Date();

		msg += projects[id].culminateStageState() + "Fecha de culminación: " + fechaActual;

		return msg;


	}


	public String showProjects() throws ParseException {

		String msg = "";

        for(int i = 0; i < projects.length; i++){

            if(projects[i]!=null){
                msg += "\n"+ (i+1) + ". " + projects[i].getProjectInfo();
            }
        }

        return msg;
	}

	public String registerKnowledgeUnit(int opcion, String id, String description, int type, String name, String cargo, String learnedLessons) {

		String msg = "";

		msg += projects[opcion].registerKnowledgeUnit(id, description, type, name, cargo, learnedLessons, opcion);

		return msg;
	}


	public String approveKnowledgeUnit(int id, int cambio) {

		String msg = "";

		Date fechaActual = new Date();

        msg += project.approveKnowledgeUnit(id, cambio);

		msg += "\nFecha de aprobación" + fechaActual;

		return msg;

	}

	public void publishKnowledgeUnit(int id, String url) {

    	units[id].setPublicacion(Publicacion.PUBLICADA);
		units[id].setUrl(url);

	}

	public String searchKnowledgeUnits(){

        String msg = "";

        for(int i = 0; i < units.length; i++){

            if(units[i]!=null){
                msg += "\n"+ (i+1) + ". " + units[i].getId();
            }
        }

        return msg;

        
    }

	public String getAllKnowledgeUnits() {

		String msg = "";

        for(int i = 0; i < units.length; i++){

            if(units[i]!=null){
                msg += "\n" + units[i].toString();
            }
        }

        return msg;
	}

	public String getAllKnowledgeUnitsType() {

		String msg = "";

        for(int i = 0; i < projects.length; i++){

            if(projects[i]!=null){
                msg += projects[i].getAllKnowledgeUnitsType();
            }
        }

        return msg;
	}

	public String getAllLearnedLessonsKnowledgeUnits(int opcion) {

		String msg = "";

        for(int i = 0; i < projects.length; i++){

            if(projects[i]!=null){
                msg += projects[i].getAllLearnedLessonsKnowledgeUnits(opcion);
            }
        }

        return msg;
	}

	public String getProyectMostKnowledgeUnits() {

		String msg = "";

        for(int i = 0; i < projects.length; i++){

            if(projects[i]!=null){
                msg += projects[i].getProyectMostKnowledgeUnits();
            }
        }

        return msg;
	}
}
