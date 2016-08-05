package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.IAtividade;
import util.Repositorios;
import entidades.Atividade;



@FacesConverter(forClass=Atividade.class)
public class AtividadeConversor implements Converter{
	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Atividade atividade = null;
		IAtividade atividades = repositorios.getAtividade();
		if (value != null && !value.equals("")) {
			atividade = atividades.porCodigo(new Integer(value));
			if (atividade == null) {
				String descricaoErro = "Estado não existe";
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, descricaoErro, descricaoErro);
				throw new ConverterException(message);
			}
		}
		return atividade;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Integer codigo = ((Atividade) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;

	}

}
