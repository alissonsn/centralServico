package filtro;

import java.io.IOException;

import javax.faces.context.FacesContext;
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

import modelo.PessoaWifiDAOImpl;
import controller.PessoaWifiController;



@WebFilter(urlPatterns="/site/dns/body/")
public class FiltroSegurancaDns implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession sessao = httpRequest.getSession(false);
		if (sessao.getAttribute("usuarioDns") == null && sessao.getAttribute("senhaDns") == null){
			chain.doFilter(request, response);
			 } else {
				 httpResponse.sendRedirect("http://localhost:8080/centralServico/site/dns/login.xhtml");
        }
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
