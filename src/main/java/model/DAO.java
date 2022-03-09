package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	/** M�dulo de conex�o **/
	
	// Par�metros de conex�o //
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/dbagenda?useTImezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "123@qwe";
	
	// M�todos de conex�o //
	private Connection conectar() {
		Connection con = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** SELECT ITEM **/
	public void buscarContatoPorId(JavaBeans contato) {
		String search = "select * from contatos where id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(search);
			pst.setString(1, contato.getId());
			ResultSet rs = pst.executeQuery();
					
			while(rs.next()) {
				contato.setNome(rs.getString(2));
				contato.setTelefone(rs.getString(3));
				contato.setEmail(rs.getString(4));
			}
			
			con.close();
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD CREATE **/
	public void inserirContato(JavaBeans contato) {
		String create = "insert into contatos(nome, telefone, email) values (?, ?, ?)";
		
		try {
			// Abrir a conex�o
			Connection con = conectar();
			
			// Preparar query para execu��o no banco de dados
			PreparedStatement pst = con.prepareStatement(create);
			
			// Substituir os parametros (?) pelo conte�do das vari�veis JavaBeans
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			
			// Executar a query
			pst.executeUpdate();
			
			con.close();
			
			// Loa�o de repeti��o ser� executado enquanto houver contato
			//white(rs.next()) {
				//String id = rs.getString(1);
			//}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ **/
	public ArrayList<JavaBeans> listarContatos() {
		ArrayList<JavaBeans> contatos = new ArrayList<>();
		String read = "select * from contatos order by nome";
		
		try {
			// Abrir a conex�o
			Connection con = conectar();
			
			// Preparar query para execu��o no banco de dados
			PreparedStatement pst = con.prepareStatement(read);
			
			// Executa e armazena o retorno da query
			ResultSet rs = pst.executeQuery();
						
			// La�o de repeti��o ser� executado enquanto houver contato
			while(rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String telefone = rs.getString(3);
				String email = rs.getString(4);
				
				contatos.add(new JavaBeans(id, nome, telefone, email));
			}
			
			con.close();
			return contatos;
			
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/** CRUD UPDATE **/
	public void atualizarContato(JavaBeans contato) {
		String update = "UPDATE contatos set nome=?, telefone=?, email=? where id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);

			pst.setString(4, contato.getId());
			pst.setString(1, contato.getNome());
			pst.setString(2, contato.getTelefone());
			pst.setString(3, contato.getEmail());
			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD DELETE **/
	public void excluirContato(JavaBeans contato) {
		String delete = "DELETE from contatos where id = ?";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);

			pst.setString(1, contato.getId());			
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
