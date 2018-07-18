package org.luis.model;

public class Profesores {
	private String nombreP;
	private String apellidosP;
	private String materia;
	
	// CONSTRUCTOR
	public Profesores(String nombreP, String apellidosP, String materia) {
		super();
		this.nombreP = nombreP;
		this.apellidosP = apellidosP;
		this.materia = materia;
	}
	
	
	// Getters and Setters
	public String getNombre() {
		return nombreP;
	}
	public void setNombre(String nombre) {
		this.nombreP = nombre;
	}
	public String getApellidos() {
		return apellidosP;
	}
	public void setApellidos(String apellidos) {
		this.apellidosP = apellidos;
	}
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}

}
