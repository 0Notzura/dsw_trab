package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cliente;

public class ClienteDAO extends GenericDAO {

    public void insert(Cliente cliente) {

        String sql = "INSERT INTO Cliente (email, telefone, senha, sexo, cpf, nascimento) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getSexo());
            statement.setInt(5, cliente.getCpf());
            statement.setString(6, cliente.getNascimento());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cliente> getAll() {

        List<Cliente> listaclientes = new ArrayList<>();

        String sql = "SELECT * from Cliente order by id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String senha = resultSet.getString("senha");
                String sexo = resultSet.getString("sexo");
                int cpf = resultSet.getInt("cpf");
                String nascimento = resultSet.getString("nascimento");
                Cliente cliente = new Cliente(id, email, telefone, senha, sexo, cpf, nascimento);
                listaclientes.add(cliente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaclientes;
    }

    public void delete(Cliente cliente) {
        String sql = "DELETE FROM Cliente where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Cliente cliente) {
        String sql = "UPDATE Cliente SET email = ?, telefone = ?, senha = ?, sexo = ?, cpf = ?";
        sql += ", nascimento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cliente.getEmail());
            statement.setString(2, cliente.getTelefone());
            statement.setString(3, cliente.getSenha());
            statement.setString(4, cliente.getSexo());
            statement.setInt(5, cliente.getCpf());
            statement.setString(6, cliente.getNascimento());
            statement.setLong(7, cliente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente get(Long id) {
        Cliente cliente = null;

        String sql = "SELECT * from Cliente l where l.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String telefone = resultSet.getString("telefone");
                String senha = resultSet.getString("senha");
                String sexo = resultSet.getString("sexo");
                int cpf = resultSet.getInt("cpf");
                String nascimento = resultSet.getString("nascimento");
                cliente = new Cliente(id, email, telefone, senha, sexo, cpf, nascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cliente;
    }
}

        /* 
    public int countByEditora(Long id) {
        int contador = 0;

        String sql = "SELECT count(*) from Livro l where l.EDITORA_ID = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                contador = resultSet.getInt(1);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contador;
    }
}
*/