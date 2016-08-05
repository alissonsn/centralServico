package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.IResponsavel;
import util.Repositorios;
import entidades.Responsavel;



@FacesConverter(forClass=Responsavel.class)
public class ResponsavelConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Responsavel retorno = null;
		IResponsavel responsaveis = repositorios.getResponsavel();
		if (value != null && !value.equals("")) {
			retorno = responsaveis.porCodigo(new Integer(value));
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
			Integer codigo = ((Responsavel) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}