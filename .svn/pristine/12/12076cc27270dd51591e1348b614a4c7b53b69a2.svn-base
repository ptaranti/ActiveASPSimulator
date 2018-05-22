package simulador.objects;

import java.sql.*;

import org.postgis.DriverWrapper;
import org.postgresql.PGConnection;

import simulador.suport.PropertiesLoaderImpl;

/**
 * Singleton para conexao ao banco testlrit (SGBD postgresql)
 * 
 * @author taranti
 * 
 */
public class ConSingletonPgsql {

	private static ConSingletonPgsql singleton = null;
	private PGConnection pgConn;
	private java.sql.Connection conn;

	private ConSingletonPgsql() {

		try {
			/*
			 * Load the JDBC driver and establish a connection.
			 */
			
						
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://"
					+ PropertiesLoaderImpl.findDataBaseIPAdrress()
					+ ":5432/testlrit";

			if(PropertiesLoaderImpl.findDebug()) System.out.println("::::::::::::::::::::::::::::::::::::::::::: \n URL-JDBC: "+url +"\n:::::::::::::::::::::::::::::::::::::::::::");

			conn = DriverManager.getConnection(url, "postgres", "postgres");
			pgConn = (PGConnection) conn;

			/*
			 * Add the geometry types to the connection. Note that you must cast
			 * the connection to the pgsql-specific connection * implementation
			 * before calling the addDataType() method.
			 */
			DriverWrapper.addGISTypes80(pgConn);

			if(PropertiesLoaderImpl.findDebug()) System.out.println("CONECTOR "+pgConn + "OBTIDO" );

		} catch (Exception e) {
			System.out.println("CONECTOR "+pgConn + "NAO DISPONIVEL" );
			e.printStackTrace();
		}
	}

	/**
	 * @return instancia do singleton
	 */
	public static ConSingletonPgsql getInstance() {
		if (singleton == null) {
			singleton = new ConSingletonPgsql();
		}
		return singleton;
	}

	/**
	 * @return conexao com o banco
	 */
	public java.sql.Connection getConn() {
		return conn;
	}

}
