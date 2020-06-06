package ar.jluque.com.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LIBROS")
public class Libro {

	@Id
	@Column(name = "LIBRO_ID")
	private Long id;

	@Column(name = "NOMBRE")
	private String nombre;

//	Relaciones ---------------------
	@ManyToOne (fetch=FetchType.LAZY)
	@JoinColumn(name = "AUTOR_ID")
	private Autor autor;

	
	public Libro() {

	}

	public Libro(long id, String nombre, Autor autor) {
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + "]";
	}

}
