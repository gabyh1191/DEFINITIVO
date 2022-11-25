package DAO;

import java.sql.Statement;

import Implementacion.*;

public class FactoryDAO {
	public static Statement sent;
	
	public static FutbolistaDAO getFutbolistaDAO() {
		return new FutbolistaImp(sent);
	}

	public static PaisDAO getPaisDAO() {
		return new PaisImp(sent);
	}
	
	public static SedeDAO getSedeDAO() {
		return new SedeImp(sent);
	}
}
