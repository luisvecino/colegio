package org.luis.model;


import java.util.concurrent.atomic.AtomicInteger;

public class Alumnos {
	
	private int id = 0;
	private String nombreA;
	private String apellidosA;
	private int dni;
	private String tel;
	private static AtomicInteger ID_GENERATOR = new AtomicInteger(100);
	
	public Alumnos() {
	}

	// M�todo constructor
	public Alumnos(String nombreA, String apellidosA, int dni, String tel) {
		this.id = ID_GENERATOR.getAndIncrement();
		this.nombreA = nombreA;
		this.apellidosA = apellidosA;
		this.dni = dni;
		this.tel = tel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	// GETTERS Y SETTERS
	public String getNombreA() {
		return nombreA;
	}

	public void setNombreA(String nombreA) {
		this.nombreA = nombreA;
	}

	public String getApellidosA() {
		return apellidosA;
	}

	public void setApellidosA(String apellidosA) {
		this.apellidosA = apellidosA;
	}

	public int getDni() {
		return dni;
	}

	public int setDni(int dni) {
		return this.dni = dni;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Alumnos [nombreA=" + nombreA + ", apellidosA=" + apellidosA + ", dni=" + dni + ", tel=" + tel + "]";
	}
}

