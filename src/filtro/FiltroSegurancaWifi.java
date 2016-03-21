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



@WebFilter(urlPatterns="/site/wifi/body/")
public class FiltroSegurancaWifi implements Filter {

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
		if (sessao.getAttribute("usuarioWifi") == null || sessao.getAttribute("senhaWifi") == null){
                httpResponse.sendRedirect("http://localhost:8080/centralServico/site/wifi/login.xhtml");
			 } else {
				 chain.doFilter(request, response);
        }
	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
