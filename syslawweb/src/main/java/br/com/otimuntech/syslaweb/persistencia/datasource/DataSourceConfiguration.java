package br.com.otimuntech.syslaweb.persistencia.datasource;

import javax.sql.DataSource;

/**
 * Interface que contem a assinatura dos metodos que serao implementados na
 * camada BO do fluxo DataSourceConfiguration.
 * 
 */

public interface DataSourceConfiguration {

        /**
         * Metodo que retorna o dataSource
         * @return DataSource
         */
	DataSource getDataSource();
	
}