package jdbc_operations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Postgres_with_jdbc {

	private static final String url = "jdbc:postgresql://localhost/JBK";
	private static final String user = "postgres";
	private static final String password = "bhushan";
	private static final Scanner s = new Scanner(System.in);

	private static void addStudent() {

		try {
			Connection c = DriverManager.getConnection(url, user, password);

			String query = "insert into student (name,email,age) values(?,?,?)";

			PreparedStatement ps = c.prepareStatement(query);
			System.out.println("Enter the Name");
			ps.setString(1, s.next());

			System.out.println("Enter the Email");
			ps.setString(2, s.next());

			System.out.println("Enter the Age");
			ps.setInt(3, s.nextInt());

			int n = ps.executeUpdate();

			if (n > 0) {
				System.out.println("Data Inserted Successfully....");
			} else {
				System.out.println("due to some error....");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void updateStudent() {

		try {
			Connection c = DriverManager.getConnection(url, user, password);

			String query = "update student set name=? , email=?, age=? where id=?";

			PreparedStatement ps = c.prepareStatement(query);

			System.out.println("Enter the new Name");
			ps.setString(1, s.next());

			System.out.println("Enter the new Email");
			ps.setString(2, s.next());

			System.out.println("Enter the new Age");
			ps.setInt(3, s.nextInt());

			System.out.println("Enter your Id");
			ps.setInt(4, s.nextInt());

			int n = ps.executeUpdate();

			if (n > 0) {
				System.out.println("Data Updated Successfully.....");
			} else {
				System.out.println("due to some error....");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void deleteStudent() {

		try {
			Connection c = DriverManager.getConnection(url, user, password);

			String query = "delete from student where id=?";

			PreparedStatement ps = c.prepareStatement(query);

			System.out.println("Enter the id..");
			ps.setInt(1, s.nextInt());

			int n = ps.executeUpdate();

			if (n > 0) {
				System.out.println("Data Deleted Successfully....");
			} else {
				System.out.println("due to some error...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void getByIdStudent() {

		try {
			Connection c = DriverManager.getConnection(url, user, password);

			String query = "select * from student where id=?";

			PreparedStatement ps = c.prepareStatement(query);

			System.out.println("Enter the id..");
			ps.setInt(1, s.nextInt());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("Id: " + rs.getInt("id"));
				System.out.println("Name: " + rs.getString("name"));
				System.out.println("Email: " + rs.getString("email"));
				System.out.println("Age: " + rs.getInt("age"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void getAllStudent() {

		try {
			Connection c = DriverManager.getConnection(url, user, password);

			String query = "select * from student";

			PreparedStatement ps = c.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				System.out.println();
				System.out.println("Id: " + rs.getInt(4));
				System.out.println("Name: " + rs.getString(1));
				System.out.println("Email: " + rs.getString(2));
				System.out.println("Age: " + rs.getInt(3));
				System.out.println("------------------------");

			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private static void Exits() {

		String str = "Thank You For Using JDBC crud Operations.....";

		for (int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i));
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
				System.out.println(e);
			}
		}

	}

	public static void main(String[] args) {

		while (true) {

			System.out.println();
			System.out.println();

			System.out.println("      +-----------------------------+");
			System.out.println("      |1.   Add Student             |");
			System.out.println("      |2.   Update Student          |");
			System.out.println("      |3.   Delete Student          |");
			System.out.println("      |4.   Get By ID Student       |");
			System.out.println("      |5.   Get All Student         |");
			System.out.println("      |0.   Exits                   |");
			System.out.println("      +-----------------------------+");

			System.out.println();
			System.out.println();

			String str = s.next();

			if (str.length() > 1) {
				System.out.println("Please Enter valid Number....");
				break;
			}

			switch (str.charAt(0)) {

			case '1':
				addStudent();
				break;
			case '2':
				updateStudent();
				break;
			case '3':
				deleteStudent();
				break;
			case '4':
				getByIdStudent();
				break;
			case '5':
				getAllStudent();
				break;
			case '0':
				Exits();
				s.close();
				return;

			default:
				System.out.println("Invalid Input.....");

			}

		}

	}
}
