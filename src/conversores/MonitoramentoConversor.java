package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import entidades.Equipe;
import entidades.Monitoramento;
import modelo.IEquipe;
import modelo.IMonitoramento;
import util.Repositorios;

@FacesConverter(forClass=Monitoramento.class)
public class MonitoramentoConversor implements Converter{
	private Repositorios repositorios = new Repositorios();
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Monitoramento retorno = null;
		IMonitoramento monitoramentos = repositorios.getMonitoramento();
		if (value != null && !value.equals("")) {
			retorno = monitoramentos.porCodigo(new Integer(value));
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
			Integer codigo = ((Monitoramento) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}
