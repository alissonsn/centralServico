package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.ISolFalha;
import util.Repositorios;
import entidades.SolFalha;



@FacesConverter(forClass=SolFalha.class)
public class SolFalhaConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		SolFalha solFalha = null;
		ISolFalha solFalhas = repositorios.getSolFalha();
		if (value != null && !value.equals("")) {
			solFalha = solFalhas.porCodigo(new Integer(value));
		if (solFalha == null) {
			String descricaoErro = "Estado não existe";
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
			throw new ConverterException(message);
		}

		}

		return solFalha;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((SolFalha) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}