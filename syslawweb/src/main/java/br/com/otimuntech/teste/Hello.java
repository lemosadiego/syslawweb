package br.com.otimuntech.teste;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.otimuntech.syslaweb.persistencia.datasource.DataSourceConfiguration;



@RequestScoped
@ManagedBean
public class Hello {
	
	 private DataSourceConfiguration dataSourceConfiguration;
	
	@PostConstruct
	public void init(){
		System.out.println(" Bean executado! ");
		
		try {
			Connection connection = dataSourceConfiguration.getDataSource().getConnection();
			System.out.println(connection.isClosed());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getMessage(){
		return "Hello World JSF!";
	}

}



