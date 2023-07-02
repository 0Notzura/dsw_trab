package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public void insert(Usuario usuario) {

        String sql = "INSERT INTO Usuario (nome, login, senha, papel) VALUES (?, ?, ?, ?)";
        String sql2 = "INSERT INTO Cliente (email, telefone, senha, sexo, cpf, nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            PreparedStatement statement2 = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel());
            statement.executeUpdate();

            statement.close();

            statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, usuario.getCliente().getEmail());
            statement2.setString(2, usuario.getCliente().getTelefone());
            statement2.setString(3, usuario.getCliente().getSenha());
            statement2.setString(4, usuario.getCliente().getSexo());
            statement2.setInt(5, usuario.getCliente().getCpf());
            statement2.setString(6, usuario.getCliente().getNascimento());

            statement2.executeUpdate();

            statement2.close();


            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * from Usuario u, Cliente c where u.CLIENTE_ID = c.ID ";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                Long cliente_ID = resultSet.getLong(6);
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");            
                String sexo = resultSet.getString("sexo");
                int cpf = resultSet.getInt("cpf");
                String nascimento = resultSet.getString("nascimento");
                Cliente cliente = new Cliente(cliente_ID, email, telefone, senha, sexo, cpf, nascimento);
                
                Usuario usuario = new Usuario(id, nome, login, senha, papel, cliente);
                listaUsuarios.add(usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void delete(Usuario usuario) {
        String sql = "DELETE FROM Usuario where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE Usuario SET nome = ?, login = ?, senha = ?, papel = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getLogin());
            statement.setString(3, usuario.getSenha());
            statement.setString(4, usuario.getPapel());
            statement.setLong(5, usuario.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario get(Long id) {
        Usuario usuario = null;

        String sql = "SELECT * from Usuario u, Cliente c  WHERE u.id = ? and u.CLIENTE_ID=c.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String login = resultSet.getString("login");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                Long clinte_id = resultSet.getLong("CLIENTE_ID");
                Cliente cliente = new ClienteDAO().get(clinte_id);

                usuario = new Usuario(id, nome, login, senha, papel, cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    
    public Usuario getbyLogin(String login) {//PROCURA UM OBJ USUARIO NO BANCO DE DADOS,PEGA SEUS DADOS E CRIA UM OBJ USUARIO NO JAVA
        Usuario usuario = null;

        String sql = "SELECT * from Usuario u, Cliente c WHERE u.login = ? and u.CLIENTE_ID=c.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
            	Long id = resultSet.getLong("id");
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                Long clinte_id = resultSet.getLong("CLIENTE_ID");
                Cliente cliente = new ClienteDAO().get(clinte_id);

                usuario = new Usuario(id, nome, login, senha, papel, cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }
    
}