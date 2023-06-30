package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Cadastros;
import br.ufscar.dc.dsw.domain.Cliente;
import br.ufscar.dc.dsw.domain.Locadora;

import br.ufscar.dc.dsw.domain.Usuario;

public class CadastroDAO extends GenericDAO {
    public Cadastros get(Integer cpf, String cnpj, String dia) {
    Cadastros cadastro = null;
    
    String sql = "SELECT * FROM Cadastros WHERE cliente_id = (SELECT ID FROM CLIENTE WHERE CPF = ?) " +
            "AND locadora_id = (SELECT ID FROM LOCADORA WHERE CNPJ = ?) " +
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
            Long clienteID = resultSet.getLong("cliente_id");
            Long locadoraID = resultSet.getLong("locadora_id");
            Long usuarioID = resultSet.getLong("usuario_id");

            Cliente cliente = new ClienteDAO().get(clienteID);
            Locadora locadora = new LocadoraDAO().get(locadoraID);
            Usuario usuario = new UsuarioDAO().get(usuarioID);
            cadastro = new Cadastros(id, dia, hora, cliente, locadora, usuario);
        }

        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    return cadastro;
}

    public void insert(Cadastros cadastro) {// FAZ A INSERÇÃO DE UM NOVO CADASTRO NO BANCO DE DADOS

        String sql = "INSERT INTO Cadastros (dia, hora, cliente_id, locadora_id, usuario_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ;

            statement = conn.prepareStatement(sql);
            statement.setString(1, cadastro.getDia());
            statement.setString(2, cadastro.getHora());
            statement.setLong(3, cadastro.getCliente().getId());
            statement.setLong(4, cadastro.getLocadora().getId());
            statement.setLong(5, cadastro.getUsuario().getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Cadastros> getAll(Usuario usuario) {

        List<Cadastros> listacadastros = new ArrayList<>();

        String sql = "SELECT * FROM Cadastros c WHERE c.usuario_id = ? ORDER BY c.ID";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, usuario.getId());//PREENCHE O PRIMEIRO ? DA QUERRY
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String dia = resultSet.getString("dia");
                String hora = resultSet.getString("hora");
                Long clienteID = resultSet.getLong("cliente_id");
                Long locadoraID = resultSet.getLong("locadora_id");

                Cliente cliente = new ClienteDAO().get(clienteID);
                Locadora locadora = new LocadoraDAO().get(locadoraID);
                Cadastros cadastro = new Cadastros(id, dia, hora, cliente, locadora, usuario);
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