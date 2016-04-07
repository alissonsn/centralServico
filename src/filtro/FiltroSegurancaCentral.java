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


@WebFilter(urlPatterns="/site/central/body/")
public class FiltroSegurancaCentral implements Filter {

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
		if (sessao.getAttribute("usuarioCentral") != null && sessao.getAttribute("senhaCentral") != null){
			chain.doFilter(request, response);
			 } else {
				 httpResponse.sendRedirect("http://snmp.info.ufrn.br:8080/centralServico/index.xhtml");
				 //httpResponse.sendRedirect("http://177.20.144.247:8080/centralServico/site/central/login.xhtml");
        }
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
