package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Rede;

/*
 * Classe ListaRede, utiliza o metodo BuscaRede da Classe BuscaRede para buscar
 * a rede cadastrada no banco de dados.
 * 
 * */

public class ListaRede {

	public List<Rede> ListaRede(String ip, String mascara) throws SQLException {

		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = null;
		ResultSet r = null;

		Rede rede = new Rede();
		List<Rede> listaRedes = new ArrayList<Rede>();

		try {

			stmt = con.prepareStatement("select * from rede WHERE ip = ?");

			if (ip != null && !ip.isEmpty()) {

				BuscaRede ipv4 = new BuscaRede(ip, mascara);
				stmt.setString(1, ipv4.getNetwork());

				r = stmt.executeQuery();

				String mascarateste = null;

				if (r.next()) {

					mascarateste = r.getString("mascara");

					if (mascarateste.equals(ipv4.getMascara())) {

						rede = new Rede();

						rede.setIp(r.getString("ip"));
						rede.setMascara(r.getString("mascara"));
						rede.setVlan(r.getString("vlan"));
						rede.setLocal(r.getString("local"));
						rede.setGateway(r.getString("gateway"));
						rede.setDhcp(r.getString("dhcp"));
						rede.setIp_externo(r.getString("ip_externo"));
						rede.setporta(r.getString("porta"));

						listaRedes.add(rede);

					} else {
						BuscaRede ipv4_1 = new BuscaRede(ip, mascarateste);
						stmt.setString(1, ipv4_1.getNetwork());

						ResultSet rs = stmt.executeQuery();

						if (rs.next()) {
							rede = new Rede();

							rede.setIp(rs.getString("ip"));
							rede.setMascara(rs.getString("mascara"));
							rede.setVlan(rs.getString("vlan"));
							rede.setLocal(rs.getString("local"));
							rede.setGateway(rs.getString("gateway"));
							rede.setDhcp(rs.getString("dhcp"));
							rede.setIp_externo(rs.getString("ip_externo"));
							rede.setporta(rs.getString("porta"));

							listaRedes.add(rede);

							rs.close();
							stmt.close();
							con.close();
						}
					}
				} else {
					BuscaRede ipv4_2 = new BuscaRede(ip, mascara);

					stmt.setString(1, ipv4_2.getHost());

					ResultSet rp = stmt.executeQuery();

					if (rp.next()) {

						mascarateste = rp.getString("mascara");

						if (mascarateste.equals(ipv4_2.getMascara())) {
							rede = new Rede();

							rede.setIp(rp.getString("ip"));
							rede.setMascara(rp.getString("mascara"));
							rede.setVlan(rp.getString("vlan"));
							rede.setLocal(rp.getString("local"));
							rede.setGateway(rp.getString("gateway"));
							rede.setDhcp(rp.getString("dhcp"));
							rede.setIp_externo(rp.getString("ip_externo"));
							rede.setporta(rp.getString("porta"));

							listaRedes.add(rede);

							rp.close();
							stmt.close();
							con.close();
						} else {
							BuscaRede ipv4_3 = new BuscaRede(ip, mascarateste);

							stmt.setString(1, ipv4_3.getHost());

							ResultSet rsp = stmt.executeQuery();

							if (rsp.next()) {
								rede = new Rede();

								rede.setIp(rsp.getString("ip"));
								rede.setMascara(rsp.getString("mascara"));
								rede.setVlan(rsp.getString("vlan"));
								rede.setLocal(rsp.getString("local"));
								rede.setGateway(rsp.getString("gateway"));
								rede.setDhcp(rsp.getString("dhcp"));
								rede.setIp_externo(rsp.getString("ip_externo"));
								rede.setporta(rsp.getString("porta"));

								listaRedes.add(rede);

								rsp.close();
								stmt.close();
								con.close();
							}
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (r != null)
				r.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		}
		return listaRedes;
	}
}