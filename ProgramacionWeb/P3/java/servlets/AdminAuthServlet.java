
package servlets;

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

import beans.CustomerBean;

/**
 * Servlet Filter implementation class AdminAuthServlet
 */

@WebFilter("/AdminAuthServlet")
public class AdminAuthServlet implements Filter {
	
	@Override
    public void destroy(){
		
	}
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(true);
        String requestPath = request.getRequestURI();
   		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
   		boolean isStaticResource = request.getRequestURI().contains("/css/") || request.getRequestURI().contains("/js/") 
   				|| request.getRequestURI().contains("/error/") || request.getRequestURI().contains("sql.properties") 
   				|| request.getRequestURI().contains("web.xml") || request.getRequestURI().contains("index.jsp") 
   				|| request.getRequestURI().contains("/img/");
        if (isStaticResource) { 
            chain.doFilter(request, response);
        }
        else {
         	Boolean customerNull = (customerBean == null);
         	Boolean gotoIndex = requestPath.contains("LoginServlet") || requestPath.contains("RegisterServlet")  || request.getRequestURI().contains("registerView.jsp");
         	if(gotoIndex) {
        		chain.doFilter(req, res);
         	}
         	else {
         		if(customerNull || customerBean.getEmailUser().equals("")) {
                	response.sendRedirect(request.getContextPath() + "/index.jsp");
            	}
                else {
                		Boolean isAdmin = customerBean.getRol().equals("Admin");
                    	if (needsAuthAdmin(requestPath) && !isAdmin) {
                        	response.sendRedirect(request.getContextPath() + "/P3-Alt/error/notAuth.jsp"); // No permission
                    	} else {
                    		chain.doFilter(req, res); // Logged-in user is admin, so just continue request.
                    	} 
                	}
                }
         	}
        }
	
	public boolean needsAuthAdmin(String url) {
        String[] validNonAuthenticationUrls = { "registerView.jsp","home.jsp","index.jsp", "headerNav.jsp", "userHome.jsp", 
        		"userDataHome.jsp", "homeEspUser.jsp", "allEspsView.jsp", "allCriticasView.jsp", "newCritica.jsp", "showCriticasEsp.jsp", 
        		"DeleteCriticaServlet", "DeleteUserServlet", "DislikeCriticaServlet", "GetAllEsps", "GetAllEspsDoneServlet",
        		"LikeCriticaServlet", "LoginServlet", "LogOutServlet", "ModifyUserDataServlet", "NewCriticaServlet", 
        		"RegisterServlet", "SearchCriticaServlet", "SearchEspServlet", "SearchMultServlet","SearchTempServlet",
        		"SearchPuntServlet", "UserDataServlet", "modifyUser.jsp", "deleteUser.jsp", "registerView.jsp", "SearchAllEspsUser",
        		"GetAllCritsServlet", "RegisterServlet"};
        for(String validUrl : validNonAuthenticationUrls) {
            if (url.contains(validUrl)) {
                return false;
            }
        }
        return true;
    }
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
}
