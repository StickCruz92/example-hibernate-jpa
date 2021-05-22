package com.example.hibernate.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.hibernate.model.Empleado;

public class EmpleadoTest {

	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	//@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*emf = Persistence.createEntityManagerFactory("Percistencia");
		manager = emf.createEntityManager();
		
		
		List<Empleado> emplados = (List<Empleado>) manager.createQuery("From Emplado").getResultList();
		System.out.println("La cantidad de empleados es: " + emplados.size());
		*/
	}

}
