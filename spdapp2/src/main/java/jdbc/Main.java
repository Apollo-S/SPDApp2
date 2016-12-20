//package jdbc;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class Main {
//
//	public static void main(String[] args) throws SQLException {
//
//		try (Connection conn = DBUtil.getConnection();
//			 Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
//			 ResultSet rs = stmt.executeQuery("SELECT * FROM spd");) {
//			rs.last();
//			System.out.println("Number of rows: " + rs.getRow());
//
//		} catch (SQLException e) {
//			DBUtil.processException(e);
//		}
//	}
//
//}
