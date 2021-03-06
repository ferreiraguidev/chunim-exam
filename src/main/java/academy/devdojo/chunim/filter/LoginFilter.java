package academy.devdojo.chunim.filter;

import academy.devdojo.chunim.bean.LoginBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {


    @Inject
    private LoginBean loginBean;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        HttpServletResponse rs = (HttpServletResponse) servletResponse;

        String url = rq.getRequestURL().toString();
        System.out.println(url);

        if (url.contains("/restricted") && loginBean.getAdmin() != null) {
            rs.sendRedirect(rq.getServletContext().getContextPath() + "/chunimHome.xhtml");

        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}

