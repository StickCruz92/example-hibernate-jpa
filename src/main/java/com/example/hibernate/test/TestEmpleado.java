package com.example.hibernate.test;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.example.hibernate.model.Autor;
import com.example.hibernate.model.Direccion;
import com.example.hibernate.model.Empleado;
import com.example.hibernate.model.Libro;

public class TestEmpleado {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Percistencia");

	public static void main(String[] args) {
		EntityManager man = emf.createEntityManager();
		
		try {
        man.getTransaction().begin();
		
		Libro l1 = new Libro();
		l1.setNombre("JPA e Hibernate");
		man.persist(l1);
		
		Autor a1 = new Autor("Stick", "Colombia");
		a1.addLibro(l1);
		System.out.println("Libros escritos (pre-save) " +a1.getLibros().size());
		man.persist(a1);
		
		man.getTransaction().commit();
		
		imprimirTodoAutorManyToOne(a1.getCodigo());
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		man.close();
	}
		
	}

	private static void InsertarOneToMany() {
		EntityManager man = emf.createEntityManager();
		
		try {
			
			man.getTransaction().begin();
			 
             Autor autor = new Autor("Pablo pérez", "España");
             Autor autor2 = new Autor("Selena Gomez", "Mexicana");
             Autor autor3 = new Autor("Miguel Angel", "Italiano");
             man.persist(autor);
             man.persist(autor2);
             man.persist(autor3);
             
             System.out.println("Obtener código de la persistencia del autor 1: " + autor.getCodigo());
             
             man.persist(new Libro("Programar es facíl", autor)); 
             man.persist(new Libro("Como luchar contra la pereza", autor2));  
             man.persist(new Libro("Como ganar mas dinero", autor2));  
             man.persist(new Libro("Aprender ingles en 5 minutos", autor3)); 
             man.persist(new Libro("Como reparar un IPHONE", autor3)); 
             man.persist(new Libro("Como hacer más amigos", autor3)); 
			
			man.getTransaction().commit();
			
			imprimirTodoAutorManyToOne(autor.getCodigo());
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			man.close();
		}
	}

	private static void imprimirTodoAutorManyToOne(Long idAutor) {
		
         EntityManager man = emf.createEntityManager();
			
			 Autor autor = man.find(Autor.class, idAutor);
			 List<Libro> libros =  autor.getLibros();
			 if (libros.size() > 0) {
				 man.close();
				 for (Libro libro : libros) {
					 System.out.println("*"+ libro.toString());
				 }
				 System.out.println(autor);
				 
			 } else 
			 {
				 System.out.println("no hay datos");
			 }
			 man.close();
	}

	private static void insertarOneToOne() {
		EntityManager man = emf.createEntityManager();
		
		try {
			Empleado emp = new Empleado("Juan", "Pepito", LocalDate.now());
			emp.setDireccion(new Direccion("Calle 123","Springfield","Springfield","EEUU"));
			man.getTransaction().begin();
			man.persist(emp);
			man.getTransaction().commit();
			imprimirTodo();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			man.close();
		}
	}

	private static void insertaEmpleadoFechaJava8() {
		EntityManager man = emf.createEntityManager();

		try {
			// Java 8 -> java.time.LocalDate
			Empleado emp = new Empleado("Juan", "Pepito", LocalDate.now());

			man.getTransaction().begin();
			man.persist(emp);
			man.getTransaction().commit();
			imprimirTodo();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			man.close();
		}
	}

	private static void actulizarDatoPersitencia() {
		EntityManager man = emf.createEntityManager();

		/*Calendar fecha = new GregorianCalendar();
		int ano = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
        */
		//Empleado emp = new Empleado("Pepe", "Jose", new GregorianCalendar(ano, mes, dia));
		Empleado emp = new Empleado("Juan", "Pepito", LocalDate.now());

		man.getTransaction().begin();
		man.persist(emp);
		man.getTransaction().commit();
		
		imprimirTodo();
		
		man = emf.createEntityManager();
		man.getTransaction().begin();
		emp.setNombre("Ana");
		man.merge(emp); // ->  persite para modificar el objecto en la persistencia
		man.getTransaction().commit();
		man.close();
		imprimirTodo();
	}

	private static void actualizarFila() {
		EntityManager man = emf.createEntityManager();

		try {
			// insertarInicial();
			man.getTransaction().begin();
			Empleado empleado = man.find(Empleado.class, 13L);
			empleado.setNombre("Carlos3");
			empleado.setApellido("López");
			man.getTransaction().commit();
			imprimirTodo();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			man.close();
		}
	}

	private static void insertarInicial() {

		EntityManager man = emf.createEntityManager();

		try {
			Calendar fecha = new GregorianCalendar();

			// Obtenemos el valor del año, mes, día,
			int ano = fecha.get(Calendar.YEAR);
			int mes = fecha.get(Calendar.MONTH);
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			Empleado emp = new Empleado("Juan", "Pepito", LocalDate.now());
			//Empleado emp = new Empleado("Stick", "Pepito", new GregorianCalendar(ano, mes, dia));

			man.getTransaction().begin();
			man.persist(emp);
			man.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			man.close();
		}
	}

	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		EntityManager man = emf.createEntityManager();
		
		try {
			List<Empleado> emplados = (List<Empleado>) man.createQuery("From Empleado").getResultList();
			System.out.println("La cantidad de empleados es: " + emplados.size());
			for (Empleado empleado : emplados) {
				System.out.println(empleado.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			man.close();
		}
	}

}
