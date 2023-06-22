package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Locadora;

public class LocadoraDAO extends GenericDAO {

    public void insert(Locadora locadora) {

        String sql = "INSERT INTO Locadora (email, cnpj, cidade, senha, nome) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getCNPJ());
            statement.setString(3, locadora.getCidade());
            statement.setString(4, locadora.getSenha());
            statement.setString(5, locadora.getNome());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Locadora> getAll() {

        List<Locadora> listalocadoras = new ArrayList<>();

        String sql = "SELECT * from Locadora";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                Locadora locadora = new Locadora(id, email,cnpj,cidade,senha, nome);
                listalocadoras.add(locadora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listalocadoras;
    }

   public void delete(Locadora locadora) {
        String sql = "DELETE FROM Locadora where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, locadora.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }


    public void update(Locadora locadora) {
        String sql = "UPDATE Locadora SET email = ?, cnpj = ?, cidade = ?, senha = ?, nome = ?";
        sql += " WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getCNPJ());
            statement.setString(3, locadora.getCidade());
            statement.setString(4, locadora.getSenha());
            statement.setString(5, locadora.getNome());
            statement.setLong(6, locadora.getId());
            
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Locadora get(Long id) {
        Locadora locadora = null;
        
        String sql = "SELECT * from Locadora where id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                locadora = new Locadora(id,email, cnpj,cidade,senha, nome);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return locadora;
    }
}