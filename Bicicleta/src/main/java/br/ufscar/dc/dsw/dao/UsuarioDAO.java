package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends GenericDAO {

    public void insert(Usuario usuario) {

        String sql = "INSERT INTO USUARIO (nome, senha, papel, email, telefone, sexo, cpf, nascimento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getSenha());
            statement.setString(3, usuario.getPapel());
            statement.setString(4, usuario.getEmail());
            statement.setString(5, usuario.getTelefone());
            statement.setString(6, usuario.getSexo());
            statement.setInt(7, usuario.getCpf());
            statement.setString(8, usuario.getNascimento());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Usuario> getAll() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * from USUARIO";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                int cpf = resultSet.getInt("cpf");
                String nascimento = resultSet.getString("nascimento");
                Usuario usuario = new Usuario(nome, senha, papel, email, telefone, sexo, cpf, nascimento);
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
        String sql = "DELETE FROM USUARIO where cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, usuario.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Usuario usuario) {
        String sql = "UPDATE USUARIO SET nome = ?, papel = ? , email = ?, telefone = ?, senha = ?, sexo = ?,nascimento=? WHERE CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, usuario.getNome());
            statement.setString(2, usuario.getPapel());
            statement.setString(3, usuario.getEmail());
            statement.setString(4, usuario.getTelefone());
            statement.setString(5, usuario.getSenha());
            statement.setString(6, usuario.getSexo());
            statement.setString(7, usuario.getNascimento());
            statement.setInt(8, usuario.getCpf());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Usuario get(int cpf) {
        Usuario usuario = null;

        String sql = "SELECT * from USUARIO WHERE CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cpf);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                String nascimento = resultSet.getString("nascimento");
                usuario = new Usuario(nome, senha, papel, email, telefone, sexo, cpf, nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuario;
    }

    public Usuario getbyLogin(String email) {// PROCURA UM OBJ USUARIO NO BANCO DE DADOS,PEGA SEUS DADOS E CRIA UM OBJ
                                             // USUARIO NO JAVA
        Usuario usuario = null;

        String sql = "SELECT * from USUARIO WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String senha = resultSet.getString("senha");
                String papel = resultSet.getString("papel");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                int cpf = resultSet.getInt("cpf");
                String nascimento = resultSet.getString("nascimento");
                usuario = new Usuario(nome, senha, papel, email, telefone, sexo, cpf, nascimento);
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