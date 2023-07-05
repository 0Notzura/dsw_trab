package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cadastros;
import br.ufscar.dc.dsw.domain.Locadora;

import br.ufscar.dc.dsw.domain.Usuario;

public class CadastroDAO extends GenericDAO {
    public Cadastros get(Integer cpf, String cnpj, String dia) {
        Cadastros cadastro = null;

        String sql = "SELECT * FROM Cadastros WHERE CPF = ? " +
                "AND cnpj = ? " +
                "AND dia = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, cpf);
            statement.setString(2, cnpj);
            statement.setString(3, dia);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String hora = resultSet.getString("hora");
                String email = resultSet.getString("email");
                Integer usuarioCPF = resultSet.getInt("CPF");
                Locadora locadora = new LocadoraDAO().getbyLogin(email);
                Usuario usuario = new UsuarioDAO().get(usuarioCPF);
                cadastro = new Cadastros(id, dia, hora, locadora, usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cadastro;
    }

    public void insert(Cadastros cadastro) {
        String sql = "INSERT INTO Cadastros (dia, hora, cpf, cnpj) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, cadastro.getDia());
            statement.setString(2, cadastro.getHora());
            statement.setInt(3, cadastro.getUsuario().getCpf());
            statement.setString(4, cadastro.getLocadora().getCnpj());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Cadastros> getPorLocadora(String cnpj) {
        List<Cadastros> listacadastros = new ArrayList<>();

        String sql = "SELECT * FROM Cadastros WHERE cnpj = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                String dia = resultSet.getString("DIA");
                String hora = resultSet.getString("HORA");
                int cpf =Integer.parseInt( resultSet.getString("CPF"));
                Locadora locadora = new LocadoraDAO().getbyLogin(cnpj);
                Usuario usuario = new UsuarioDAO().get(cpf);
                Cadastros cadastro = new Cadastros(id, dia, hora, locadora, usuario);
                listacadastros.add(cadastro);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listacadastros;
    }
    public List<Cadastros> getAll(Usuario usuario) {
        List<Cadastros> listacadastros = new ArrayList<>();

        String sql = "SELECT * FROM Cadastros WHERE CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, usuario.getCpf());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("ID");
                String dia = resultSet.getString("DIA");
                String hora = resultSet.getString("HORA");
                String cnpj = resultSet.getString("CNPJ");
                Locadora locadora = new LocadoraDAO().getbyLogin(cnpj);
                Cadastros cadastro = new Cadastros(id, dia, hora, locadora, usuario);
                listacadastros.add(cadastro);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listacadastros;
    }

}