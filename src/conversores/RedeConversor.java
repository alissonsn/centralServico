package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.IRede;
import util.Repositorios;
import entidades.Rede;



@FacesConverter(forClass=Rede.class)
public class RedeConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Rede retorno = null;
		IRede redes = repositorios.getRede();
		if (value != null && !value.equals("")) {
			retorno = redes.porCodigo(new Integer(value));
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
			Integer codigo = ((Rede) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}