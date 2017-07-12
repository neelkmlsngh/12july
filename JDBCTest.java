package neel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.Callable;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.PreparedStatement;

public class JDBCTest {
	public static String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static String DATABASE_URL = "jdbc:mysql://172.17.174.105/prashant";
	public static String USER_NAME = "root";
	public static String PASSWORD = "root";
	// public final String INSERT = "";
	// public final String UPDATE = "";
	// public final String GET_ALL_PERSON = "";
	// public final String GET_PERSON = "";

	public static void main(String[] args) {
		Connection connection = null;
		try {
			connection = getConnection();
			System.out.println(connection);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		insert(new Person("Akash", 23, Gender.MALE));
		insert(new Person("Akas", 21, Gender.MALE));
		insert(new Person("Akki", 27, Gender.MALE));
		insert(new Person("Akku", 29, Gender.MALE));
		delete(new Person("Akas", 0, null));
	}

	public static void insert(final Person person) {
		Connection connection = null;
		Statement statement = null;
		final String INSERT = "INSERT INTO PERSON VALUES (\'" + person.getName() +"'," + person.getAge() + ",'M')";
		System.out.println(INSERT);
		try {
			connection = getConnection();
			statement = connection.createStatement();
			statement.execute(INSERT);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	
	
	/*public static void inserts(final Person person) {
		Connection connection = null;
		Statement statement = null;
		String quer="INSERT INTO PERSON VALUES"+person.getName()+person.getAge()+"M";
		//PreparedStatement p=connection.prepareStatement(quer);
	}*/
	public static void insrt(final Person person) {
		try {
			Connection cn=getConnection();
			/*CallableStatement state=cn.prepareCall("{call sum4(?,?)}");
			state.setInt(1, 2);
			state.setString(2, "Amit");
			state.executeQuery();*/
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void update(final String name) {
		final String UPDATE = "UPDATE TEST SET NAME = NewName WHERE NAME = " + name + "";
		Connection connection = null;
		Statement statement = null;
		int updateResult = 0;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			updateResult = statement.executeUpdate(UPDATE);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static Person get(final String personName) {
		final String GET_PERSON = "SELECT * FROM PERSON WHERE NAME =" + personName;

		Connection connection = null;
		Statement statement = null;
		int updateResult = 0;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			updateResult = statement.executeUpdate(GET_PERSON);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static Person getAllPerson() {
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM TEST");
			printPersons(resultSet);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception when fetching all person");
		}

		return null;

	}

	public static void delete(final Person person) {
		Connection connection = null;
		Statement statement = null;
		boolean resultSet = false;
		try {
			connection = getConnection();
			statement = connection.createStatement();
			String de="DELETE  FROM PERSON WHERE NAME =" + person.getName();
			//resultSet = statement.execute("DELETE  FROM PERSON WHERE NAME =" + person.getName());
			System.out.println(resultSet);
			System.out.println(de);

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception when fetching all person");
		} finally {
			if (connection != null) {

			}
		}

		return;

	}

	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER_CLASS);
		Connection connection = DriverManager.getConnection(DATABASE_URL, USER_NAME, PASSWORD);
		return connection;
	}

	public static void close(final Connection connection) throws SQLException {
		if (connection != null) {
			connection.close();
		}
	}

	private static void printPersons(final ResultSet resultSet) {
		if (resultSet == null) {
			System.out.println("No record fetched.");
			return;
		}
		// TODO:
		System.out.println(resultSet);
	}

}