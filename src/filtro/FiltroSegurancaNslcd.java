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

@WebFilter(urlPatterns="/site/nslcd/body/")
public class FiltroSegurancaNslcd implements Filter {

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
		try {
			 
			//Atributo de requisição http
	       HttpServletRequest req = (HttpServletRequest) request;
	       //Atributo de resposta de requisição http
	       HttpServletResponse res = (HttpServletResponse) response;
	       //Atributo de sessao de requisição http
	       HttpSession ses = req.getSession(false);
	       //String da requisição http
	       String reqURI = req.getRequestURI();
	       //Comparando se os atributos de sessão estão nulos, se não forem o sistema libera o acesso para a pagina solicitada
			//senão redireciona o usuario para pagina de login do sistema.
	       if (  (ses != null && ses.getAttribute("usuarioNslcd") != null && ses.getAttribute("senhaNslcd") != null)
	                                  || reqURI.indexOf("/site/nslcd/body/") >= 0 && reqURI.contains("javax.faces.resource") )
	              chain.doFilter(request, response);
	       else   
	       	//Usuario não tem sessao aberta ainda, portanto o servidor redireciona ele para a pagina de login.
	              res.sendRedirect(req.getContextPath() + "/site/nslcd/login.xhtml");  
	 }
	catch(Throwable t) {
	    System.out.println( t.getMessage());
	}
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
