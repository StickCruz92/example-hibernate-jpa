package com.example.hibernate.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class PruebaJDBC {

	public static void main(String[] args) {
	
		String jdbcUrl="jdbc:mysql://localhost:3306/pruebashibernate?useSSL=false";
		String usuario = "root";
		String pass = "123456";
		
		try {
			System.out.println("Intentanto conectar con la BBDD: "+ jdbcUrl);
			Connection miConex = DriverManager.getConnection(jdbcUrl, usuario, pass); 
			System.out.println("Conexion exitosa: " + miConex);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
