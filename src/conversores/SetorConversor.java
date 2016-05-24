package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.ISetor;
import util.Repositorios;
import entidades.Rede;
import entidades.Setor;



@FacesConverter(forClass=Setor.class)
public class SetorConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Setor retorno = null;
		ISetor setores = repositorios.getSetor();
		if (value != null && !value.equals("")) {
			retorno = setores.porCodigo(new Integer(value));
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
			Integer codigo = ((Setor) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}