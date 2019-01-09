package cz.zcu.pia.revoloot.web.servlet;

import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.manager.IPager;
import cz.zcu.pia.revoloot.manager.Pager;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public abstract class AbstractServlet extends HttpServlet {

    protected AutowireCapableBeanFactory ctx;

    protected User getLoggedUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

//    protected IPager getPageing(HttpServletRequest request){
//        int pageNum = request.getParameter(FormConfig.PAGE);
//        int pageSize = request.getParameter(FormConfig.PAGE_SIZE);
//
//        IPager pager = new Pager(pageNum, pageSize);
//    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        ctx = context.getAutowireCapableBeanFactory();
        ctx.autowireBean(this);
    }
}
