package br.com.otimuntech.syslaweb.persistencia.datasource;

import javax.sql.DataSource;

public class SyslawWebDataSource implements DataSourceConfiguration {

	private DataSource dataSource;
	
	/**
	 * Metodo que retorna a dataSource
	 * @return DataSource <code>dataSource</code>
	 */
	public DataSource getDataSource() {
	    return  dataSource;
	}
	
}

