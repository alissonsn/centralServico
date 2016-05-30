package filtro;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Classe FiltroSegurancaSSH que implementa a segurança de acesso ao sistema de acesso externo. 
*
* @author silas
*
*/

@WebFilter(urlPatterns="/site/remoto/body/")
public class FiltroSegurancaSSH implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/** Metodo que implementa a politica de acesso na rede wifivisitantes.
	 *	@param ServletRequest, ServletResponse, FilterChain, requisição http, resposta a requisição
	 *  e filtro são respectivamente os parametros a ser utilizados.   
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//Atributo de requisição http
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//Atributo de resposta de requisição http
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//Atributo de sessao de requisição http
		HttpSession sessao = httpRequest.getSession(false);
		//Comparando se os atributos de sessão estão nulos, se não forem o sistema libera o acesso
				// senão redireciona o usuario para pagina inicial do sistema.
		if (sessao.getAttribute("usuarioSSH") != null && sessao.getAttribute("senhaSSH") != null){
			//Aplica a politica 
			chain.doFilter(request, response);
			 } else {
				//Redirencionando o usuario para a pagina inicial do sistema
				 httpResponse.sendRedirect("http://snmp.info.ufrn.br:8080/centralServico/index.xhtml");
        }
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
