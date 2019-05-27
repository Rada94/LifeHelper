package com.doctores.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ReadDoc
 */
@WebServlet("/ConsultaDoc")
public class ConsultaDoc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html charset='utf-8'");
		
		Properties props=new Properties();
		String fileName="config.properties";
		InputStream inputStream=getClass().getClassLoader().getResourceAsStream(fileName);
		
		if(inputStream!=null)
		{
			props.load(inputStream);
		}
		else
		{
			throw new FileNotFoundException("Property file "+fileName+" not found");
		}
	
		
		String url=props.getProperty("urlServer");
		String usuario=props.getProperty("user");
		String password=props.getProperty("pass");
		String driver=props.getProperty("driver");
		String sql=props.getProperty("ReadDoc");
		
		PrintWriter salida = response.getWriter();

		
		Connection conn=null;
		PreparedStatement pstmnt =null;
		ResultSet rs=null;
		int id_doctor=0;
		id_doctor=Integer.parseInt(request.getParameter("id_doctor"));


		
		try {
			//Se instancia el driver
			Class.forName(driver).newInstance();
			//se abre la conexion a la base de datos
			conn= DriverManager.getConnection(url,usuario,password);
			//se apunta el objeto statement que nos sirve para ejecutar comandos en la base de datos (se crea la consolo de comandos que apuntan a esa conexion)
			pstmnt = conn.prepareStatement(sql);
			pstmnt.setInt(1, id_doctor);
			rs= pstmnt.executeQuery();
			
			
			
			
			
			
			
			
			
			salida.append("<table>");
			
			salida.append("<td> Id doctor </td>");
			salida.append("<td> Nombre  </td>");
			salida.append("<td> Apellidos </td>");
			salida.append("<td> Cedula profesional</td>");
			salida.append("<td> Sexo </td>");
			salida.append("<td> Domicilio </td>");
			salida.append("<td> Telefono </td>");
			salida.append("<td> Email</td>");
			salida.append("<td> Pass: </td>");


			
			salida.append("</tr>");
			salida.append("</table");

			while(rs.next()) {
				
				salida.append("<table>");
				salida.append("<tr>");
					salida.append("<td>"+rs.getInt("id_doctor")+"</td>");
					salida.append("<td>"+rs.getString("nombre_doctor")+"</td>");
	
					salida.append("<td>"+rs.getString("apellidos_doctor")+"</td>");
					salida.append("<td>"+rs.getInt("cedula_doctor")+"</td>");
					
					salida.append("<td>"+rs.getString("genero_doctor")+"</td>");
					salida.append("<td>"+rs.getString("domicilio_doctor")+"</td>");
					
					salida.append("<td>"+rs.getInt("telefono_doctor")+"</td>");
					salida.append("<td>"+rs.getString("email_doctor")+"</td>");
					salida.append("<td>"+rs.getString("pass_doctor")+"</td>");

				
				salida.append("</tr>");
			salida.append("</table");

				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			
			try {
				rs.close();
				pstmnt.close();
				conn.close();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		salida.close();
	}

}