package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entidades.IpResponsavel;
import entidades.Responsavel;
import modelo.IIpResponsavel;
import util.Repositorios;



@FacesConverter(forClass=IpResponsavel.class)
public class IpResponsavelConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		IpResponsavel retorno = null;
		IIpResponsavel ipresponsaveis = repositorios.getIpResponsavel();
		if (value != null && !value.equals("")) {
			retorno = ipresponsaveis.porCodigo(new Integer(value));
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
			Integer codigo = ((IpResponsavel) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}