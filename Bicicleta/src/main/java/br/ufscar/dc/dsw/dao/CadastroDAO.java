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

    public void insert(Cadastros cadastro) {

        String sql = "INSERT INTO Cadastros (dia, hora, cliente_id, locadora_id, usuario_id) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;

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

        String sql = "SELECT * from Cadastros c where c.USUARIO_ID = ? order by c.ID";

        try {
        	Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setLong(1, usuario.getId());
            ResultSet resultSet = statement.executeQuery(); 
            
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String dia = resultSet.getString("dia");
                String hora = resultSet.getString("hora");
                Long ClienteID = resultSet.getLong("cliente_id");
                Long LocadoraID = resultSet.getLong("locadora_id");

                Cliente cliente = new ClienteDAO().get(ClienteID);      
                Locadora locadora = new LocadoraDAO().get(LocadoraID);                  
                Cadastros compra = new Cadastros(id, dia, hora, cliente,locadora, usuario);
                listacadastros.add(compra);
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