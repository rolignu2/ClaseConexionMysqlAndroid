//package nombre de tu paquete


import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    
   
    private Connection conexion;
    private Statement consulta;
    
    private final String  User = "usuario";
    private final String Password = "contraseña";
    private final String Bdd = "base de datos";
    private final String server = "Nombre servidor eje( servidor.hostinger.bdd.a)";
    
    
    public Conexion() throws SQLException, 
            ClassNotFoundException, 
            InstantiationException, 
            IllegalAccessException {
        permisos();
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        this.conexion = (Connection) DriverManager.getConnection("jdbc:mysql://" + server +"/" + Bdd, User, Password);
		
    }
	
	private void Permisos()
	{
			/*Codigo para obtener permisos mysql y android*/
		if (android.os.Build.VERSION.SDK_INT > 9) {
		    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		    StrictMode.setThreadPolicy(policy);
		}
	}
    
    @Override
    protected void finalize() throws Throwable
    {
        super.finalize();
    }
    
    public Connection Get_conexion()
    {
        return this.conexion;
    }
    
    
    public void AbrirConexion() throws SQLException
    {
        if(this.conexion.isClosed())
        	this.conexion = (Connection) DriverManager.getConnection("jdbc:mysql://" + server +"/" + Bdd, User, Password);
    }
    
    public void CerrarConexion() throws SQLException
    {
       if(!this.conexion.isClosed()) {
            this.conexion.close();
        } 
    }
    
    public ResultSet Get_Consulta(String sql) throws SQLException
    {
        this.consulta = (Statement) this.conexion.createStatement();
        ResultSet rs = this.consulta.executeQuery (sql);
        return rs;
    }


    public int Get_consulta_count(String sql) throws SQLException
    {
        this.consulta = (Statement) this.conexion.createStatement();
        ResultSet Est = this.consulta.executeQuery(sql);
        int cont =0;
        while(Est.next())
            cont++;
        return cont;
    }
    
     public boolean Get_Consulta_update(String sql) throws SQLException
    {
        this.consulta = (Statement) this.conexion.createStatement();
        int Est = this.consulta.executeUpdate(sql);
        return Est >=1;
    }
    
   
    
}
