package com.example.hibernate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DIRECCION")
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DIRECCION")
	private Long idDireccion;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "LOCATIDAD")
	private String localidad;
	
	@Column(name = "PROVINCIA")
	private String provincia;
	
	@Column(name = "PAIS")
	private String pais;
	
	@OneToOne(mappedBy = "direccion", fetch = FetchType.LAZY)
	private Empleado empleado;

	
	@Override
	public String toString() {
		return "Direccion [idDireccion=" + idDireccion + ", direccion=" + direccion + ", localidad=" + localidad
				+ ", provincia=" + provincia + ", pais=" + pais + ", empleado=" + empleado.getCodigo() + "]";
	}
	
	public Direccion(String direccion, String localidad, String provincia, String pais) {
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}

	public Direccion(Long idDireccion, String direccion, String localidad, String provincia, String pais) {
		this.idDireccion = idDireccion;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.pais = pais;
	}
}
