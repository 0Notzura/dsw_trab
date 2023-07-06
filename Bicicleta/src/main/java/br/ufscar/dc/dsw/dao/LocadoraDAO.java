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

        String sql = "INSERT INTO LOCADORA (EMAIL, CNPJ, CIDADE, SENHA, NOME) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement = conn.prepareStatement(sql);
            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getCnpj());
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
                String email = resultSet.getString("email");
                String cnpj = resultSet.getString("cnpj");
                String cidade = resultSet.getString("cidade");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                Locadora locadora = new Locadora(email, cnpj, cidade, senha, nome);
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
        String sql = "DELETE FROM LOCADORA WHERE CNPJ = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locadora.getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }

    public void update(Locadora locadora) {
        String sql = "UPDATE LOCADORA SET EMAIL = ?, CIDADE = ?, SENHA = ?, NOME = ?";
        sql += " WHERE CNPJ = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, locadora.getEmail());
            statement.setString(2, locadora.getCidade());
            statement.setString(3, locadora.getSenha());
            statement.setString(4, locadora.getNome());
            statement.setString(5, locadora.getCnpj());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

   public Locadora getbyLogin(String identifier) {
    Locadora locadora = null;

    String sql = "SELECT * FROM LOCADORA WHERE EMAIL = ? or CNPJ = ?";

    try {
        Connection conn = this.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, identifier);
        statement.setString(2, identifier);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String email = resultSet.getString("email");
            String cnpj = resultSet.getString("cnpj");
            String cidade = resultSet.getString("cidade");
            String senha = resultSet.getString("senha");
            String nome = resultSet.getString("nome");
            locadora = new Locadora(email, cnpj, cidade, senha, nome);
        }

        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return locadora;
}

    public List<Locadora> getAllCidade(String cidade) {
        List<Locadora> listaLocadoras = new ArrayList<>();

        String sql = "SELECT * FROM LOCADORA WHERE CIDADE = ?";

        try {
            // Conectando no banco e realizando consulta
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cidade);
            ResultSet resultSet = statement.executeQuery();

            // Convertendo resultados para a classe interna Locadora
            while (resultSet.next()) {
                String cnpj = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                Locadora locadora = new Locadora(cnpj, nome, email, senha, cidade);
                listaLocadoras.add(locadora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaLocadoras;
    }
}