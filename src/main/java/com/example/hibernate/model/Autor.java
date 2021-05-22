package com.example.hibernate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "AUTOR")
public class Autor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_AUTOR")
	private Long codigo;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "NACIONALIDAD")
	private String nacionalidad;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Libro> libros = new ArrayList<Libro>();


	public Autor(String nombre, String nacionalidad, List<Libro> libros) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.libros = libros;
	}

	public Autor(String nombre, String nacionalidad) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
	}

	public void addLibro(Libro libro) {
		if (!libros.contains(libro)) {
			libros.add(libro);
			libro.setAutor(this);
		}
	}
	
	public void removeLibro(Libro libro) {
		if (libros.contains(libro)) {
			libros.remove(libro);
			libro.setAutor(null);
		}
	}
	
	@Override
	public String toString() {
		return "Autor [codigo=" + codigo + ", nombre=" + nombre + ", nacionalidad=" + nacionalidad + "]";
	}

}
