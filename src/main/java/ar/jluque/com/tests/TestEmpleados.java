package ar.jluque.com.tests;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.jluque.com.model.Direccion;
import ar.jluque.com.model.Empleado;

public class TestEmpleados {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	public static void main(String[] args) {
		
		EntityManager man = emf.createEntityManager();

		Empleado e1 = new Empleado(10L, "Homero", "Simpson", LocalDate.of(1985, 8, 12));
		e1.setDireccion(new Direccion(15L, "Av Siempre Viva 123", "Springfield", "Spring", "EEUU"));
		
		man.getTransaction().begin(); 
		man.persist(e1);
		man.getTransaction().commit();
		man.close();
		
		imprimirTodo();
	}

	@SuppressWarnings("unused")
	private static void insertInicial() {
		EntityManager man = emf.createEntityManager();

		Empleado e1 = new Empleado(10L, "Luque", "Julio", LocalDate.of(1985, 8, 12));
		Empleado e2 = new Empleado(25L, "Luis", "Martinez", LocalDate.of(1985, 8, 12));

		man.getTransaction().begin();
		man.persist(e1);
		man.persist(e2);
		e1.setApellido("Dominguez");
		man.getTransaction().commit();
		man.close();
	}

	@SuppressWarnings("unchecked")
	private static void imprimirTodo() {
		EntityManager man = emf.createEntityManager();
		man.getTransaction().begin();
		List<Empleado> empleadosList = man.createQuery("from Empleado").getResultList();
		System.out.println("Esta es la base de datos :" + empleadosList.size() + "empleados");

		for (Empleado empleado : empleadosList) {
			System.out.println(empleado);

		}
		man.getTransaction().commit();
		man.close();

	}
}
