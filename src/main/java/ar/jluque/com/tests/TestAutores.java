package ar.jluque.com.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ar.jluque.com.model.Autor;
import ar.jluque.com.model.Libro;

public class TestAutores {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	public static void main(String[] args) {

//		crearDatos();

		imprimirTodos();

		buscarPorLibro(3L);

		buscarPorAutor(1L);

	}

	private static void buscarPorLibro(Long id) {
		System.out.println("------ Libro id "+ id + "------ ");
		EntityManager mm = emf.createEntityManager();
		mm.getTransaction().begin();
		Libro libro = mm.find(Libro.class, id);
		System.out.println(libro);
		mm.getTransaction().commit();
		mm.close();
	}

	private static void buscarPorAutor(Long id) {
		System.out.println("------ Autor id "+ id + "------ ");
		EntityManager mm = emf.createEntityManager();
		mm.getTransaction().begin();

		Autor autor = mm.find(Autor.class, id);
		List<Libro> libros = autor.getLibro();
		for (Libro libro : libros) {
			System.out.println(libro);
		}

		mm.getTransaction().commit();
		mm.close();
	}

	private static void crearDatos() {
		System.out.println("------ crearDatos ------ ");
		EntityManager mm = emf.createEntityManager();
		mm.getTransaction().begin();

		Autor a1 = new Autor(1L, "Julio", "Boliviano");
		Autor a2 = new Autor(2L, "Luis", "Argentino");
		Autor a3 = new Autor(3L, "Delfina", "Brasilera");

		mm.persist(a1);
		mm.persist(a2);
		mm.persist(a3);

		mm.persist(new Libro(1L, "No tengo pruebas pero tampoco dudas", a3));
		mm.persist(new Libro(2L, "Dias de furia", a2));
		mm.persist(new Libro(3L, "Repos y persistencia", a1));
		mm.persist(new Libro(4L, "Viajes al Brasil", a2));
		mm.persist(new Libro(5L, "Java II", a1));
		mm.persist(new Libro(6L, "Ajedrez master", a1));

		mm.getTransaction().commit();
		mm.close();
	}

	@SuppressWarnings("unchecked")
	private static void imprimirTodos() {
		System.out.println("------ imprimirTodos ------ ");
		EntityManager mm = emf.createEntityManager();
		mm.getTransaction().begin();
		List<Autor> autores = mm.createQuery("from Autor").getResultList();
		for (Autor autor : autores) {
			System.out.println(autor);
		}

		List<Libro> libros = mm.createQuery("from Libro").getResultList();
		for (Libro libro : libros) {
			System.out.println(libro);
		}
		mm.getTransaction().commit();
		mm.close();
	}

}
