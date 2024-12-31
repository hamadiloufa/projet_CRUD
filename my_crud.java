import java.sql.*;
import java.util.*;

public class my_crud {

	public static void main(String[] args) {
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "JDBC:mysql://localhost:3306/crud_db"; // crud_db nom du base de donnée
		String USER = "root"; // nom d'utilisateur par defaut
		String PASS = ""; // blank --> mot de passe par defaut
		
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
					
			String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255), PRIMARY KEY (id))"; // Users est le nom de notre table avec une colonne id, name, and email, id est notre primary key.
			
			stmt.executeUpdate(query); 
			
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			
			System.out.println("1. Add User");
			System.out.println("2. Read User");
			System.out.println("3. Update User");
			System.out.println("4. Delete User");
			
			System.out.println("Enter Choice: ");
			String choice = scan.nextLine();
			
			switch(choice) {
			case "1":

				// Ajouter user
				System.out.print("enter user name: ");		
				String name = scan.nextLine();
				
				System.out.print("Enter user email: ");
				String email = scan.nextLine();
				
				query = "INSERT INTO users (name, email) VALUES ('"+name+"','"+email+"')";
				
				stmt.executeUpdate(query);
				
				break;
			case "2":
				//Lire User
				System.out.print("Enter user id: ");
				int id = scan.nextInt();
				
				query = "Select * FROM users WHERE id = "+id;
				
				ResultSet rs = stmt.executeQuery(query);
				
				if(rs.next()) {
					System.out.print("ID: " +rs.getInt("id"));
					System.out.print("Name: " +rs.getString("name"));
					System.out.print("Email: " +rs.getString("email"));
				}else {
					System.out.print("User not found!");
				}
				
				break;
			case "3":
				System.out.print("Enter user id: ");
				id = scan.nextInt();
				
				scan.nextLine(); // Sauter une ligne
				
				System.out.print("enter new user name: ");		
				name = scan.nextLine();
				
				System.out.print("Enter new user email: ");
				email = scan.nextLine();
				
				query = "UPDATE users SET name = '"+name+"', email = '"+email+"' WHERE id = " + id;
				
				int result = stmt.executeUpdate(query);
				
				if(result > 0) {
					System.out.print("User updated Successfully!");
				}else {
					System.out.print("User not found!");
				}
				
				break;
			case "4" :
				id = scan.nextInt();
				
				query = "Delete FROM users WHERE id = " + id;
				
				result = stmt.executeUpdate(query);
				
				if(result > 0) {
					System.out.print("User deleted Successfully!");
				}else {
					System.out.print("User not found!");
				}
				
				break;
		}
			
					
			stmt.close();		
			conn.close();
			
			
		}catch(Exception e) {
			System.out.print("Error: "+e.getMessage());
		}
	} 

}
