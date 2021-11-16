
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
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

@WebFilter(filterName = "AdminFIlter", servletNames = {"AdminServlet"})
public class AdminFIlter implements Filter {

    

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        //any code before chain.doFilter will be executed before servlet is loaded
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        HttpSession session = httpRequest.getSession();
        int role = (int) session.getAttribute("role");
        
        if (role != 1) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendRedirect("notes");
            return; //very important
        }
        
        //calls up next filter in the chain
        //or load the requested servlet
        chain.doFilter(request, response);
        
        
        //any code after chain.doFilter will be executed after the servlet
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void destroy() {}
    
   
    
}
