package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import com.novell.ldap.LDAPAttribute;

import entidades.PessoaWifi;

public class Utilitaria {

	public Utilitaria(){

	}

	public Date addHora(Date data, int qtd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.HOUR, qtd);
		return cal.getTime();
	}

	/**
	 * Adiciona quantidade de dias na data.
	 *
	 * @param data
	 * @param qtd
	 * @return
	 */

	public Date addDia(Date data, int qtd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.DAY_OF_MONTH, qtd);
		return cal.getTime();
	}

	/**
	 * Adiciona quantidade de meses na data.
	 *
	 * @param data
	 * @param qtd
	 * @return
	 */
	public Date addMes(Date data, int qtd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);
		cal.add(Calendar.MONTH, qtd);
		return cal.getTime();
	}


	public String DateString(Date data){
		SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String dataFormatada = formatoData.format(data);
		return dataFormatada;
	}

	public Date StringDate(String data){
		SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date dataFormatada = null;
		try {
			dataFormatada = formatoData.parse(data);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataFormatada;
	}

	public Date TimestampDateString(String timestamp) {
		DateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
		formatter.setTimeZone(TimeZone.getTimeZone("(GMT -03:00) Brasilia"));
		Date data = null;
		try {
			data = formatter.parse(timestamp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	public boolean comparaData(Date hoje, Date validade){
		boolean data;
		if (hoje.before(validade)){
			data = true;
		}
		else if (hoje.after(validade))
			data = false;
		else
			data = true;
		return data;
	}

	public Date expiraConta(PessoaWifi pessoaWifi){
		Date validade = this.StringDate(pessoaWifi.getUltimaModificacao());
		if (pessoaWifi.getValidade().equals("cn=turno,ou=policies,dc=ufrn,dc=br")) {
			validade = this.addHora(validade, 6);
		}else if (pessoaWifi.getValidade().equals("cn=dia,ou=policies,dc=ufrn,dc=br")) {
			validade = this.addDia(validade, 1);
		}else if (pessoaWifi.getValidade().equals("cn=semana,ou=policies,dc=ufrn,dc=br")) {
			validade = this.addDia(validade, 7);
		}else if (pessoaWifi.getValidade().equals("cn=mes,ou=policies,dc=ufrn,dc=br")) {
			validade = this.addMes(validade, 1);
		}
		return validade;
	}
}