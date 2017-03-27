package fr.iutinfo.skeleton.api;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RequeteBDD {
	public static void createBDD() {
		String personnes = "CREATE TABLE users (uid INTEGER PRIMARY KEY AUTOINCREMENT, nom VARCHAR(100), prenom VARCHAR(100), token VARCHAR(250), groupe INTEGER, rang INTEGER NOT NULL  DEFAULT 1);";
		String tps = "CREATE TABLE tps (tpid INTEGER PRIMARY KEY AUTOINCREMENT, titre VARCHAR(100), categorie VARCHAR(100), chemin VARCHAR(100));";
		String exercices = "CREATE TABLE exercices (eid INTEGER PRIMARY KEY AUTOINCREMENT, titre VARCHAR(100));";
		String tests = "CREATE TABLE tests (tid INTEGER PRIMARY KEY AUTOINCREMENT, titre VARCHAR(100), reussi BOOLEAN);";

		String users_tps = "CREATE TABLE users_tps (uid INTEGER NOT NULL, tpid INTEGER NOT NULL, FOREIGN KEY(uid) REFERENCES users(uid), FOREIGN KEY(tpid) REFERENCES tps(tpid));";
		String tps_exercices = "CREATE TABLE tps_exercices (tpid INTEGER NOT NULL, eid INTEGER NOT NULL, FOREIGN KEY(tpid) REFERENCES tps(tpid), FOREIGN KEY(eid) REFERENCES exercices(eid));";
		String exercices_tests = "CREATE TABLE exercices_tests (eid INTEGER NOT NULL, tid INTEGER NOT NULL, FOREIGN KEY(eid) REFERENCES exercices(eid), FOREIGN KEY(tid) REFERENCES tests(tid));";
		
		
		try {
			Statement stmt = BDD.getInstance().createStatement();
			stmt.execute(personnes);
			stmt.execute(tps);
			stmt.execute(exercices);
			stmt.execute(tests);
			stmt.execute(users_tps);
			stmt.execute(tps_exercices);
			stmt.execute(exercices_tests);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static List<Utilisateur> getUser(String nom, String prenom) {
		List<Utilisateur> users = new ArrayList<>();
		try {
			PreparedStatement prpstmt = BDD.getInstance().prepareStatement("SELECT * FROM users WHERE nom = ? AND prenom = ?");
			prpstmt.setString(1, nom);
			prpstmt.setString(2, prenom);
			ResultSet rs = prpstmt.executeQuery();
			
			while (rs.next()) {
				users.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static List<Utilisateur> getAllUsers() {
		List<Utilisateur> users = new ArrayList<>();
		try {
			String query = "SELECT id nom, prenom FROM users ORDER BY nom, prenom";
			Statement stmt = BDD.getInstance().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				users.add(new Utilisateur(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
	
	public static boolean insert(String nom, String prenom, int groupe, int rang) {
		String sql = "INSERT INTO users(nom, prenom, groupe, rang) VALUES(?, ?, ?, ?)";

		try (PreparedStatement pstmt = BDD.getInstance().prepareStatement(sql)) {
			pstmt.setString(1, nom);
			pstmt.setString(2, prenom);
			pstmt.setInt(3, groupe);
			pstmt.setInt(4, rang);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
}
