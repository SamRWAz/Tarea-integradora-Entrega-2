package model;

public class KnowledgeUnit {

	private String id;
	private String description;
	private Type type;
	private String name;
	private String cargo;
	private String learnedLessons;
	private Status status;
    private Publicacion publicacion;
    private String url;
	private int etapa;
	private int proyecto;


	public KnowledgeUnit(String id, String description, Type type, String name, String cargo, String learnedLessons, int etapa, int proyecto) {

		this.id = id;
		this.description = description;
		this.type = type;
		this.name = name;
		this.cargo = cargo;
		this.learnedLessons = learnedLessons;
		this.status = Status.POR_DEFINIR;
        this.publicacion = Publicacion.SIN_PUBLICAR;
        this.url = "";
		this.etapa = etapa;
		this.proyecto = proyecto;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getLearnedLessons() {
		return learnedLessons;
	}

	public void setLearnedLessons(String learnedLessons) {
		this.learnedLessons = learnedLessons;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

    public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

    public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getEtapa() {
		return etapa;
	}

	public void setEtapa(int etapa) {
		this.etapa = etapa;
	}

	public int getProyecto() {
		return proyecto;
	}

	public void setProyecto(int proyecto) {
		this.proyecto = proyecto;
	}

	public String toString() {

		String msg = "";

		msg = "\nId: " + id + "\nDescripcion: " + description + "\nTipo: " + type + "\nNombre del Colabor: " + name + "\nCargo del colaborador" + cargo + "\nAprendizaje o lección aprendida: " + learnedLessons + "\nEstado: " + status + "\nEstado de Publicacion: " + publicacion + "\nURL de Publicacion: " + url;

		return msg; 
	}
    
}