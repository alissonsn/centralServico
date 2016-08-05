package conversores;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import modelo.IReqInfo;
import util.Repositorios;
import entidades.ReqInfo;



@FacesConverter(forClass=ReqInfo.class)
public class ReqInfoConversor implements Converter{

	private Repositorios repositorios = new Repositorios();

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ReqInfo retorno = null;
		IReqInfo  reqInfos = repositorios.getReqInfo();
		if (value != null && !value.equals("")) {
			retorno = reqInfos.porCodigo(new Integer(value));
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
			Integer codigo = ((ReqInfo) value).getCodigo();
			return codigo == null ? "" : codigo.toString();
		}
		return null;
	}

}