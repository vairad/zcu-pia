package cz.zcu.pia.revoloot.web.servlet;

import cz.zcu.pia.revoloot.entities.Product;
import cz.zcu.pia.revoloot.entities.User;
import cz.zcu.pia.revoloot.manager.IProductManager;
import cz.zcu.pia.revoloot.utils.ITuringGenerator;
import cz.zcu.pia.revoloot.web.FormConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public abstract class AbstractServlet extends HttpServlet {

    protected AutowireCapableBeanFactory ctx;

    @Autowired
    private ITuringGenerator turingGenerator;

    @Autowired
    private IProductManager productManager;

    protected void loadProducts(HttpServletRequest request) {
        List<Product> productList = productManager.getAllAvailableProducts();
        request.setAttribute("productList", productList);
    }

    protected User getLoggedUser(){
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected void prepareTuringQuestion(HttpServletRequest request){
        String uuid = turingGenerator.generateQuestion();
        request.getSession().setAttribute(FormConfig.TURING_ID, uuid);
        request.setAttribute("turingAsk", turingGenerator.getQuestionRepresentation(uuid));
    }

    protected boolean checkTuringQuestion(HttpServletRequest request){
        String answer = request.getParameter(FormConfig.TURING);
        String uuid = (String) request.getSession().getAttribute(FormConfig.TURING_ID);
        return turingGenerator.validateAnswer(answer, uuid);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext context = WebApplicationContextUtils
                .getWebApplicationContext(getServletContext());
        ctx = context.getAutowireCapableBeanFactory();
        ctx.autowireBean(this);
    }
}
