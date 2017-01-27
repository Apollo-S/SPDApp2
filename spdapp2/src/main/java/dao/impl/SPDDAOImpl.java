package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import dao.SPDDAO;
import entity.SPD;

public class SPDDAOImpl extends Repository<SPD> {

	public SPDDAOImpl() {
		super(SPD.class);
	}
	
}

	
