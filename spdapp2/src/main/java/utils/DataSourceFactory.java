package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DataSourceFactory {
	
	private static final String DB_PROPERTIES = "db.properties";

	public static DataSource getDataSource() {
		Properties properties = new Properties();
		FileInputStream inputStream = null;
		MysqlDataSource mySQLDataSource = null;
		try {
			inputStream = new FileInputStream(DB_PROPERTIES);
			properties.load(inputStream);
			mySQLDataSource = new MysqlDataSource();
			mySQLDataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
			mySQLDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
			mySQLDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return mySQLDataSource;
	}

}
