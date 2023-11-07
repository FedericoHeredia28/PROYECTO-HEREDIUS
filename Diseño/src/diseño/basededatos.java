/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package diseño;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author feder
 */
public class basededatos {
    
    String url = "jdbc:mysql://localhost:3306/";
    String user="root";   
    String password = "";
    String driver= "com.mysql.cj.jdbc.Driver";
    Connection cx;

    public Connection Conectar(String bd) {
        try {
            Class.forName(driver);
            cx=DriverManager.getConnection(url+bd, user, password);
            System.out.println("Se conecto");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("No se conecto");
            Logger.getLogger(basededatos.class.getName()).log(Level.SEVERE, null, ex);
        
        }
      return cx;  
    }
    
   public void desconectar() {
        try {
            cx.close();
            System.out.println("Se desconecto");
        } catch (SQLException ex) {
            Logger.getLogger(basededatos.class.getName()).log(Level.SEVERE, null, ex);
        }
       
   
   }
     public void insertar_clientes(int ID_Cliente, String Email,int DNI,String Telefono, String Nombre, String Direccion){
       try{
       PreparedStatement st = cx.prepareStatement("INSERT INTO clientes VALUES (?, ?, ?, ?, ?, ?)");
       st.setInt(1, ID_Cliente);
       st.setString(2, Email);
       st.setInt(3, DNI);
       st.setString(4, Telefono);
       st.setString(5, Nombre);
       st.setString(6, Direccion);
       st.execute();
       } catch (SQLException e) {System.out.println(e);}
   }
     
     public void borrar_cliente(int ID_Client) {
         try{
             PreparedStatement st = cx.prepareStatement("DELETE FROM clientes WHERE ID_Cliente = ?");
             st.setInt(1, ID_Client);
             st.execute();
         } catch(SQLException e) {System.out.println(3);}
     }
     
     public void borrar_ordendeventa(int ID_Ordv) {
         try {
             PreparedStatement st = cx.prepareStatement("DELETE FROM ordendeventa WHERE ID_OdV = ?");
             st.setInt(1, ID_Ordv);
             st.execute();
         } catch (SQLException e) {System.out.println(e);}
     }
     
      public void añadir_ordendeventa(int ID_Client, String Fecha){ 
           try {
               PreparedStatement st = cx.prepareStatement("INSERT INTO ordendeventa(ID_Cliente, Fecha) VALUES ( ?, ? )");
               st.setInt(1, ID_Client);
               st.setString(2, Fecha);
               st.execute();
           }  catch (SQLException e) {System.out.println(e);}
       }
      
       public void borrar_ordendedigi(int ID_Ordd) {
         try {
             PreparedStatement st = cx.prepareStatement("DELETE FROM ordendedigitalizacion WHERE ID_OdD = ?");
             st.setInt(1, ID_Ordd);
             st.execute();
         } catch (SQLException e) {System.out.println(e);}
     }
       
       
       
       public void añadir_ordenD(int ID_Cliente, String Fecha) {
           try {
                PreparedStatement st = cx.prepareStatement("INSERT INTO ordendedigitalizacion (ID_Cliente, Fecha) VALUES (?, ?)");
               st.setInt(1, ID_Cliente);
               st.setString(2, Fecha);
               st.execute();
           } catch (SQLException e) {System.out.println(e);}
       }
       
       
       
        public int getSelectedID(int ID, int id_orden) {
           try { 
               Statement crear = cx.createStatement();
               ResultSet rs = crear.executeQuery("Select s.ID_Sistema from ordendeventa as o inner join sistema as s on s.ID_OdV = o.ID_OdV where s.ID_OdV = " + id_orden);
               while (rs.next()) {
                   ID = rs.getInt(1);
               }
           } catch (SQLException e) {System.out.println(e);}
           return ID;
       }
          
        
        
         public int getLastID(int ID) {
           try { 
               Statement crear = cx.createStatement();
               ResultSet rs = crear.executeQuery("Select max(ID_OdV) from ordendeventa ");
               while (rs.next()) {
                   ID = rs.getInt(1);
               }
           } catch (SQLException e) {System.out.println(e);}
           return ID;
       }
        
        
        
        public void borrar_sistema(int SistemID) {
            try {
              PreparedStatement st = cx.prepareStatement("DELETE FROM sistema WHERE ID_Sistema = ?");
             st.setInt(1, SistemID);
             st.execute();
         } catch (SQLException e) {System.out.println(e);}
        }
       
       
      
       
  
               
       public void añadir_sistema(int ID_Ordv, int ID_Client, String Nombre, String Descripcion) {
           try {
               int ID_Oddv = 0;
           PreparedStatement st = cx.prepareStatement("INSERT INTO sistema (ID_OdV, ID_Cliente, Nombre, Descripcion) VALUES ( ?, ?, ?, ? )");
               st.setInt(1, getLastID(ID_Oddv));
               st.setInt(2, ID_Client);
               st.setString(3, Nombre);
               st.setString(4, Descripcion);
               st.execute();
           }  catch (SQLException e) {System.out.println(e);}
       }
       
        public void borrar_facturaV(int facturaID) {
            try {
              PreparedStatement st = cx.prepareStatement("DELETE FROM facturaov WHERE ID_Factura = ?");
             st.setInt(1, facturaID);
             st.execute();
         } catch (SQLException e) {System.out.println(e);}
        }
       public int getFacturaOv(int ID, int factura) {
           try { 
               Statement crear = cx.createStatement();
               ResultSet rs = crear.executeQuery("Select f.ID_Factura from facturaov as f inner join sistema as s on s.ID_OdV = f.ID_OdV where s.ID_OdV = " + factura);
               while (rs.next()) {
                   ID = rs.getInt(1);
               }
           } catch (SQLException e) {System.out.println(e);}
           return ID;
       }
       
       public void añadir_facturaV(int ID_OrdV, int ID_Cliente, String Fecha, int Monto){
           try{
               int ID_Oddv = 0;
            PreparedStatement st = cx.prepareStatement("INSERT INTO facturaov (ID_OdV,ID_Cliente, Fecha, Monto_total) VALUES (?, ?, ?, ?)");
               st.setInt(1, getLastID(ID_Oddv));
               st.setInt(2, ID_Cliente);
               st.setString(3, Fecha);
               st.setInt(4, Monto);
               st.execute();
           }  catch (SQLException e) {System.out.println(e);}
       }
       
       
       
       
       
       
       public void añadir_facturaD(int ID_OdD, int ID_Cliente, String Fecha, int Monto_total) {
             try {
                PreparedStatement st = cx.prepareStatement("INSERT INTO facturaod (ID_OdD, ID_Cliente, Fecha, Monto_total) VALUES (?, ?, ?, ?)");
               st.setInt(1, ID_OdD);
               st.setInt(2, ID_Cliente);
               st.setString(3, Fecha);
               st.setInt(4, Monto_total);
               st.execute();
           } catch (SQLException e) {System.out.println(e);}
       }
       
       
       
       public void borrar_facturaD(int facturaID) {
            try {
              PreparedStatement st = cx.prepareStatement("DELETE FROM facturaod WHERE ID_Factura = ?");
             st.setInt(1, facturaID);
             st.execute();
         } catch (SQLException e) {System.out.println(e);}
       }
       
       
       public int getLastID_OD(int ID) {
           try { 
               Statement crear = cx.createStatement();
               ResultSet rs = crear.executeQuery("Select max(ID_OdD) from ordendedigitalizacion");
               while (rs.next()) {
                   ID = rs.getInt(1);
               }
           } catch (SQLException e) {System.out.println(e);}
           return ID;
       }
       
       
       
            public int getFacturaOD(int ID, int id_orden) {
           try { 
               Statement crear = cx.createStatement();
               ResultSet rs = crear.executeQuery("Select f.ID_Factura from facturaod as f inner join ordendedigitalizacion as o on f.ID_OdD = o.ID_OdD where f.ID_OdD = " + id_orden);
               while (rs.next()) {
                   ID = rs.getInt(1);
               }
           } catch (SQLException e) {System.out.println(e);}
           return ID;
       }
       
       
       
       public void Lista_clientes(JTable tabla) {
           try {
               Statement ma = cx.createStatement();
               ResultSet rs = ma.executeQuery("Select * from clientes");
               
               while(rs.next()) {
                   String IDS = String.valueOf(rs.getInt("ID_Cliente"));
                   String Mail = rs.getString("Email");
                   String DNI = String.valueOf(rs.getInt("DNI"));
                   String Telefono = rs.getString("Telefono");
                   String Nombre = rs.getString("Nombre");
                   String Direccion = rs.getString("Direccion");
                   String cliente [] = {IDS, Mail, DNI, Telefono, Nombre, Direccion};
                   DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
                   modelo.addRow(cliente);
               }
               
         } catch (SQLException e) {System.out.println(e);}
       }
       
       public void Lista_ordenesdeventa(JTable tabla) {
           try {
               Statement ma = cx.createStatement();
               ResultSet rs = ma.executeQuery("Select * from ordendeventa");
               
               while(rs.next()) {
                   String OdV = String.valueOf(rs.getInt("ID_OdV"));
                   String ID_C = String.valueOf(rs.getInt("ID_Cliente"));
                   String Fecha = rs.getString("Fecha");
                   String cliente [] = {OdV, ID_C, Fecha};
                   DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
                   modelo.addRow(cliente);
               }
               
         } catch (SQLException e) {System.out.println(e);}
       }
       
       public void Lista_ordenesdedigi(JTable tabla) {
           try {
               Statement ma = cx.createStatement();
               ResultSet rs = ma.executeQuery("Select * from ordendedigitalizacion");
               
               while(rs.next()) {
                   String OdV = String.valueOf(rs.getInt("ID_OdD"));
                   String ID_C = String.valueOf(rs.getInt("ID_Cliente"));
                   String Fecha = rs.getString("Fecha");
                   String cliente [] = {OdV, ID_C, Fecha};
                   DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
                   modelo.addRow(cliente);
               }
               
         } catch (SQLException e) {System.out.println(e);}
       }
              
               
        public void Lista_facturasOd(JTable tabla) {
           try {
               Statement ma = cx.createStatement();
               ResultSet rs = ma.executeQuery("Select * from facturaod");
               
               while(rs.next()) {
                   String IDF = String.valueOf(rs.getInt("ID_Factura"));
                   String OdD = String.valueOf(rs.getInt("ID_OdD"));
                   String ID_C = String.valueOf(rs.getInt("ID_Cliente"));
                   String Fecha = rs.getString("Fecha");
                   String Monto = String.valueOf(rs.getInt("Monto_total"));
                   String cliente [] = {IDF, OdD, ID_C, Fecha, Monto};
                   DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
                   modelo.addRow(cliente);
               }
               
         } catch (SQLException e) {System.out.println(e);}
       }
         
        public void Lista_facturasOv(JTable tabla) {
           try {
               Statement ma = cx.createStatement();
               ResultSet rs = ma.executeQuery("Select * from facturaov");
               
               while(rs.next()) {
                   String IDF = String.valueOf(rs.getInt("ID_Factura"));
                   String OdV = String.valueOf(rs.getInt("ID_OdV"));
                   String ID_C = String.valueOf(rs.getInt("ID_Cliente"));
                   String Fecha = rs.getString("Fecha");
                   String Monto = String.valueOf(rs.getInt("Monto_total"));
                   String cliente [] = {IDF, OdV, ID_C, Fecha, Monto};
                   DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
                   modelo.addRow(cliente);
               }
               
         } catch (SQLException e) {System.out.println(e);}
       }
        public void Lista_sistemas(JTable tabla) {
           try {
               Statement ma = cx.createStatement();
               ResultSet rs = ma.executeQuery("Select * from sistema");
               
               while(rs.next()) {
                   String IDS = String.valueOf(rs.getInt("ID_Sistema"));
                   String OdV = String.valueOf(rs.getInt("ID_OdV"));
                   String ID_C = String.valueOf(rs.getInt("ID_Cliente"));
                   String Nombre = rs.getString("Nombre");
                   String Desc = rs.getString("Descripcion");
                   String cliente [] = {IDS, OdV, ID_C, Nombre, Desc};
                   DefaultTableModel modelo = (DefaultTableModel)tabla.getModel();
                   modelo.addRow(cliente);
               }
               
         } catch (SQLException e) {System.out.println(e);}
       }
}





   