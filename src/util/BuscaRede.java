package util;

import java.sql.Connection;

/*
 * Classe BuscaRede servirá para calcular o Network e o Host de uma rede ao receber
 * o Ip e a Mascara da rede.
 * 
 * */

public class BuscaRede {
	int numeroIPbase;
	int mascarabase;
	String host;
	String network;
	String mascarateste;
	String ip;
	String mascara;
	private Connection connection;

	public BuscaRede(String ipString, String mascara) {

		String[] partesIp = ipString.split("\\.");

		int i = 24;
		numeroIPbase = 0;
		for (int n = 0; n < partesIp.length; n++) {
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
		for (int n = 0; n < partesmasc.length; n++) {
			int valor = Integer.parseInt(partesmasc[n]);
			mascarabase += valor << j;
			j -= 8;
		}
	}

	public String getNetwork() {
		StringBuffer sbb = new StringBuffer(15);

		for (int shift = 24; shift > 0; shift -= 8) {

			sbb.append(Integer
					.toString(((numeroIPbase & mascarabase) >>> shift) & 0xff));

			sbb.append('.');
		}
		sbb.append(Integer.toString((numeroIPbase & mascarabase) & 0xff));

		return sbb.toString();
	}

	public String getHost() {
		StringBuffer sbh = new StringBuffer(15);

		for (int shift = 24; shift > 0; shift -= 8) {

			sbh.append(Integer
					.toString((((numeroIPbase & mascarabase) + 1) >>> shift) & 0xff));

			sbh.append('.');
		}
		sbh.append(Integer.toString(((numeroIPbase & mascarabase) + 1) & 0xff));

		return sbh.toString();
	}

	public String getMascara() {
		StringBuffer sbm = new StringBuffer(15);

		for (int shift = 24; shift > 0; shift -= 8) {

			sbm.append(Integer.toString((mascarabase >>> shift) & 0xff));

			sbm.append('.');
		}
		sbm.append(Integer.toString(mascarabase & 0xff));

		return sbm.toString();
	}
}