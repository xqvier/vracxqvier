// Acc�s JDBC
import java.sql.*;

public class Jdbc00Demo {

      static { 
          System.out.println("Je charge la classe Jdbc00Demo");
      }

	public static void main (String args []) throws SQLException { 
// Chargement du pilote Oracle
		DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver()); 
// Connexion � la base de donn�es
		String url = "MEDIATEUR??:SGBD??:PROTOCOLE??:@MACHINE??.ADRESSE??.local:PORT??:INSTANCE??";
		Connection cnx = DriverManager.getConnection(url, "USER??", "PWD??"); 
// Requ�te statique
		Statement req = cnx.createStatement (); 
		ResultSet res = stmt.executeQuery ("MAREQUETE??");
// Affichage du r�sultat
		while (res.next ()) 
			{
			System.out.print (res.getString (1)); 
			System.out.println (res.getTYPE?? (???N???)); 
			}
// Fermeture des espaces allou�s
		res.close();
		req.close();
		cnx.close();
	} 

}
