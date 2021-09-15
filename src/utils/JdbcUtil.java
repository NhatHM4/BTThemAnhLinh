package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static String dburl = "jdbc:sqlserver://DESKTOP-D01NKHU:1433;databaseName=General_Assignment;integratedSecurity=false";
	private static String username = "nhat1";
	private static String password = "1234";

	static {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private static PreparedStatement prepareStatment(String sql, Object... args) throws SQLException {
		Connection con = DriverManager.getConnection(dburl, username, password);
		PreparedStatement statement = con.prepareStatement(sql);
			for (int i = 0; i < args.length; i++) {
				statement.setObject(i + 1, args[i]);
			}

		return statement;
	}

	public static int excuteUpdate(String sql, Object... args) {
		PreparedStatement statement = null;
		try {
			 statement = prepareStatment(sql, args);
			return statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.getConnection().close();
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	public static ResultSet excuteQuery(String sql, Object...args) {
		ResultSet rs = null;
		PreparedStatement statement = null;
		try {
			statement = prepareStatment(sql, args);
			rs = statement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
}
