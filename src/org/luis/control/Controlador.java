package org.luis.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.luis.dao.AlumnosDao;
import org.luis.model.Alumnos;

@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controlador() {
	}

	private final String PREFIX = "/WEB-INF/vistas/";
	private String NEXT_COMPONENT;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		AlumnosDao alumnoDao = new AlumnosDao();

		// Recojo datos del formulario
		String name = request.getParameter("name");
		String apel = request.getParameter("apel");
		String dni1 = request.getParameter("dni");
		String tel = request.getParameter("tel");	
		String db = request.getParameter("CreateDB");
	
		// ********************************
	/* LA BASE DE DATOS, al crearla Dá un nullPointerException así que lo arreglaré cuando tenga un minuto 
	 * if (db != null) {
			
				try {
					alumnoDao.createDB();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					alumnoDao.createTable();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}*/

	
		Map<Integer, Alumnos> listaAlumnos = new HashMap<Integer, Alumnos>();

		// INSERTAR

		Alumnos al = new Alumnos();

		if ((name != null) && (apel != null) && (dni1 != null) && (tel != null)) {
			int dni = Integer.parseInt(dni1);
			 al = new Alumnos(name, apel, dni, tel);
			try {
				alumnoDao.insertar(al);
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

		// ELIMINAR POR DNI
		String eliminarByDni = request.getParameter("eliminarDni");
		String deleteRegistro = request.getParameter("borrarRegistro");
		 int dni = 0;
		if ((eliminarByDni != null) && (deleteRegistro != null)) {
			dni = Integer.parseInt(eliminarByDni);
			al = new Alumnos(name, apel, dni, tel);
		} else {
			System.out.println("No se ha eliminado ningún registro");
		}
		try {

			alumnoDao.eliminar(dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ELIMINAR TODOS LOS REGISTROS
		String deleteAll = request.getParameter("deleteAll");
		if (deleteAll != null)
			try {
				alumnoDao.deleteAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		// ****************

		// Añadimos el alumno a la lista
		listaAlumnos.put(0, al);
		// Dejo la lista en el request
		request.setAttribute("listaAlumnos", listaAlumnos);

		NEXT_COMPONENT = PREFIX + "ShowAlumnos.jsp";
		// Pasar el control del flujo web a otro componente
		RequestDispatcher rd = request.getRequestDispatcher(NEXT_COMPONENT);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

	}
}
