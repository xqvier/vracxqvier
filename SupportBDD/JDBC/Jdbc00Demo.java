// Accès JDBC
import java.sql.*;

public class Jdbc00Demo {

      static { 
          System.out.println("Je charge la classe Jdbc00Demo");
      }

	public static void main (String args []) throws SQLException { 
// Chargement du pilote Oracle
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver()); 
// Connexion à la base de données
		String url = "MEDIATEUR??:SGBD??:PROTOCOLE??:@MACHINE??.ADRESSE??.local:PORT??:INSTANCE??";
		Connection cnx = DriverManager.getConnection(url, "USER??", "PWD??"); 
// Requête statique
		Statement req = cnx.createStatement (); 
		ResultSet res = stmt.executeQuery ("MAREQUETE??");
// Affichage du résultat
		while (res.next ()) 
			{
			System.out.print (res.getString (1)); 
			System.out.println (res.getTYPE?? (???N???)); 
			}
// Fermeture des espaces alloués
		res.close();
		req.close();
		cnx.close();
	} 

}
