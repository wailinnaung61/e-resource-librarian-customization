/*
 * package dao;
 * 
 * import java.sql.Connection; import java.sql.DriverManager; import
 * java.sql.PreparedStatement; import java.sql.ResultSet; import
 * java.sql.SQLException; import java.sql.Statement;
 * 
 * public class KohaDatabaseConnection {
 * 
 * static Connection conn; static Statement stm; PreparedStatement pst;
 * ResultSet result;
 * 
 * public static void getconnection() throws ClassNotFoundException,
 * SQLException { Class.forName("com.mysql.jdbc.Driver"); Connection
 * con=DriverManager.getConnection(
 * "jdbc:mysql://18.139.117.181:3306/koha_ucspku?serverTimezone=UTC","msis",
 * "m$!s13@4"); stm=con.createStatement(); if(con!=null) {
 * System.out.println("Connection Success"); } } }
 */