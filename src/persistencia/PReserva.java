package persistencia;

import entidade.Copias;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Hugo Henrique
 */

public class PReserva {

	public void alterar(int id, String data, Boolean reserva) throws SQLException {
		Connection cnn = util.Conexao.getConexao();
		cnn.setAutoCommit(false);
		try {
			String sql = "UPDATE copias SET data_reserva = ?, reservada = ? WHERE id = ?";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setString(1, data);
			ps.setBoolean(2, reserva);
			ps.setInt(3, id);
			ps.execute();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		}
		cnn.close();
	}

	public void cancelarReservasVencidas(Timestamp data) throws SQLException {
		Connection cnn = util.Conexao.getConexao();
		cnn.setAutoCommit(false);
		try {
			String sql = "UPDATE copias SET data_reserva = null, reservada = false WHERE data_reserva <= ?";
			PreparedStatement ps = cnn.prepareStatement(sql);
			ps.setTimestamp(1, data);
			ps.execute();
			cnn.commit();
		} catch (Exception e) {
			cnn.rollback();
			e.printStackTrace();
		}
		cnn.close();
	}

	public List<Copias> listar() throws SQLException{
		String sql = "SELECT * FROM copias WHERE reservada = true and data_reserva notnull";
		Connection cnn = util.Conexao.getConexao();
		PreparedStatement ps = cnn.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		List<Copias>lista = new ArrayList<>();
		while(rs.next()) {
			Copias retorno = new Copias();
			retorno.setId(rs.getInt("id"));
			retorno.setFilmeId(rs.getInt("filme_id"));
			retorno.setDisponivel(rs.getBoolean("disponivel"));
			retorno.setReservada(rs.getBoolean("reservada"));
			retorno.setDataReserva(rs.getDate("data_reserva"));
			lista.add(retorno);
		}
		rs.close();
		cnn.close();

		return lista;
	}
}
