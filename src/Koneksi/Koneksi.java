
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
/**
 *
 * @author Hilman
 */
public class Koneksi {
    public Koneksi(){}
    Connection con=null;
    Statement st=null;
    public Connection open(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
    con=DriverManager.getConnection("jdbc:mysql://localhost:3306/appinv","root","");
            return con;
        }catch(SQLException sqle){
            System.out.print("Tidak Ada Koneksi Yang Terbuka");
            return null;
        }catch(Exception e){
            javax.swing.JOptionPane.showMessageDialog(null,"Koneksi Gagal"+e.getMessage());
            System.out.print("Tidak Bisa Membuka Koneksi....."+e.getMessage());
            return null;
        }
    }
 
    public void QUERY2(String data){
        try{
            if(con==null)con=open();
            if(st==null)st=con.createStatement();
            st.executeUpdate(data);
        }catch(SQLException sqle2){}
    }    
        
    
    
    public void QUERY(String data,String option){
        try{
            if(con==null)con=open();
            if(st==null)st=con.createStatement();
            st.executeUpdate(data);
            
            javax.swing.JOptionPane.showMessageDialog(null,"Data Berhasil"+option);
        }catch(SQLException sqle){
            sqle.printStackTrace();
            
            javax.swing.JOptionPane.showMessageDialog(null,"Data Gagal"+option + "Salahnya :" + sqle.getMessage());
        }
    }
}