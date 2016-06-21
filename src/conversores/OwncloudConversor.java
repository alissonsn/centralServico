package conversores;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.IOwncloud;
import modelo.IRede;
import modelo.OwncloudDAOImpl;
import modelo.PessoaSSHDAO;
import modelo.PessoaSSHDAOImpl;
import util.Repositorios;
import entidades.Equipe;
import entidades.Owncloud;
import entidades.PessoaSSH;
import entidades.Rede;



@FacesConverter(forClass=Owncloud.class)
public class OwncloudConversor implements Converter{

	

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Owncloud retorno = null;
		IOwncloud iOwncloud =  new OwncloudDAOImpl();
		if (value != null && !value.equals("")) {
			try {
				retorno = iOwncloud.poruid(value);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (retorno == null) {
			String descricaoErro = "Estado não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
		}

		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			String codigo = ((Owncloud) value).getUid();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}