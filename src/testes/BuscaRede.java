package testes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BuscaRede {
	 int numeroIPbase;
	 int mascarabase;
	 String host;
	 String network;
	 String mascarateste;
	
	public BuscaRede(String ipString, String mascara){
		
		String[] partesIp = ipString.split("\\.");
		
		int i =24;
		numeroIPbase = 0;
		for(int n = 0; n < partesIp.length; n++){
			int valor = Integer.parseInt(partesIp[n]);
			numeroIPbase += valor << i;
			i -= 8;
		}
		
		StringBuffer sb = new StringBuffer(15);
		
		for (int shift = 24; shift > 0; shift -= 8) {

            sb.append(Integer.toString((numeroIPbase >>> shift) & 0xff));

            sb.append('.');
        }
        sb.append(Integer.toString(numeroIPbase & 0xff));
        
    		
		String[] partesmasc = mascara.split("\\.");
		
		int j = 24;
		mascarabase = 0;
		for(int n = 0; n < partesmasc.length; n++){
			int valor = Integer.parseInt(partesmasc[n]);
			mascarabase += valor << j;
			j -= 8;
		}
	}
	
	
	public String getNetwork() {
        StringBuffer sbb = new StringBuffer(15);

        for (int shift = 24; shift > 0; shift -= 8) {

            // process 3 bytes, from high order byte down.
            sbb.append(Integer.toString(((numeroIPbase & mascarabase) >>> shift) & 0xff));

            sbb.append('.');
        }
        sbb.append(Integer.toString((numeroIPbase & mascarabase) & 0xff));

        return sbb.toString();
    }
	
	public String getHost() {
        StringBuffer sbh = new StringBuffer(15);

        for (int shift = 24; shift > 0; shift -= 8) {

            // process 3 bytes, from high order byte down.
            sbh.append(Integer.toString((((numeroIPbase & mascarabase) + 1) >>> shift) & 0xff));

            sbh.append('.');
        }
        sbh.append(Integer.toString(((numeroIPbase & mascarabase) + 1) & 0xff));

        return sbh.toString();
    }
	
	public String getMascara() {
        StringBuffer sbm = new StringBuffer(15);

        for (int shift = 24; shift > 0; shift -= 8) {

            // process 3 bytes, from high order byte down.
            sbm.append(Integer.toString((mascarabase >>> shift) & 0xff));

            sbm.append('.');
        }
        sbm.append(Integer.toString(mascarabase & 0xff));

        return sbm.toString();
    }
	
	public static void main(String[] args) throws SQLException {
		String mascarateste = null;
		String ip = "10.4.112.250"; 
		
		
		Connection con = new ConnectionFactory().getConnection();
		PreparedStatement stmt = con.prepareStatement("select * from rede WHERE ip = ?");
		
		BuscaRede ipv4 = new BuscaRede(ip, "255.255.252.0");
		
		stmt.setString(1, ipv4.getNetwork());
		
		ResultSet r = stmt.executeQuery();
		
		while(r.next()){
			
			mascarateste = r.getString("mascara");
			
			if(mascarateste == ipv4.getMascara()){
				
				System.out.println("Network: " + ipv4.getNetwork());
				System.out.println("Host: " + ipv4.getHost());
				
				System.out.println("IP:" + r.getString("ip"));
				System.out.println("Mascara:" + r.getString("mascara"));
				System.out.println("Vlan:" + r.getString("vlan"));
				System.out.println("Local:" + r.getString("local"));
				System.out.println("Gateway:" + r.getString("gateway"));
				System.out.println("DHCP:" + r.getString("dhcp"));
				System.out.println("Ip externo:" + r.getString("ip_externo"));
				System.out.println("Porta:" + r.getString("porta"));
			}
		}
		
		
		if(mascarateste == null){
			mascarateste = "255.255.252.0";
		}
		
		BuscaRede ipv4_1 = new BuscaRede( ip, mascarateste);
		
		stmt.setString(1, ipv4_1.getNetwork());
		
		ResultSet rp = stmt.executeQuery();
		
		if(rp.next()){
			
			System.out.println("Network: " + ipv4_1.getNetwork());
			System.out.println("Host: " + ipv4_1.getHost());
			
			System.out.println("IP:" + rp.getString("ip"));
			System.out.println("Mascara:" + rp.getString("mascara"));
			System.out.println("Vlan:" + rp.getString("vlan"));
			System.out.println("Local:" + rp.getString("local"));
			System.out.println("Gateway:" + rp.getString("gateway"));
			System.out.println("DHCP:" + rp.getString("dhcp"));
			System.out.println("Ip externo:" + rp.getString("ip_externo"));
			System.out.println("Porta:" + rp.getString("porta"));
		}
		
		
		BuscaRede ipv4_2 = new BuscaRede(ip, "255.255.252.0");
		
		stmt.setString(1, ipv4_2.getHost());
		
		ResultSet rh = stmt.executeQuery();
		
		while(rh.next()){
			
			mascarateste = rh.getString("mascara");
			
			if(mascarateste == ipv4_2.getMascara()){
				
				System.out.println("Network: " + ipv4_2.getNetwork());
				System.out.println("Host: " + ipv4_2.getHost());
				
				System.out.println("IP:" + rh.getString("ip"));
				System.out.println("Mascara:" + rh.getString("mascara"));
				System.out.println("Vlan:" + rh.getString("vlan"));
				System.out.println("Local:" + rh.getString("local"));
				System.out.println("Gateway:" + rh.getString("gateway"));
				System.out.println("DHCP:" + rh.getString("dhcp"));
				System.out.println("Ip externo:" + rh.getString("ip_externo"));
				System.out.println("Porta:" + rh.getString("porta"));
			}
		}
		
		
		BuscaRede ipv4_3 = new BuscaRede(ip, mascarateste);
		
		stmt.setString(1, ipv4_3.getHost());
		
		ResultSet rhp = stmt.executeQuery();
		
		if(rhp.next()){
			
			System.out.println("Network: " + ipv4_3.getNetwork());
			System.out.println("Host: " + ipv4_3.getHost());
			
			System.out.println("IP:" + rhp.getString("ip"));
			System.out.println("Mascara:" + rhp.getString("mascara"));
			System.out.println("Vlan:" + rhp.getString("vlan"));
			System.out.println("Local:" + rhp.getString("local"));
			System.out.println("Gateway:" + rhp.getString("gateway"));
			System.out.println("DHCP:" + rhp.getString("dhcp"));
			System.out.println("Ip externo:" + rhp.getString("ip_externo"));
			System.out.println("Porta:" + rhp.getString("porta"));
		}
		
		//else{
		//	System.out.println("Nenhum Registro Encontrado!");
		//}
		
		r.close();
		rp.close();
		rh.close();
		rhp.close();
		stmt.close();
		con.close(); 
		
	}
}
