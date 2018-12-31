package cz.zcu.pia.revoloot.web.servlet;


import cz.zcu.pia.revoloot.web.ServletNaming;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(ServletNaming.ERRORS)
public class Error extends AbstractServlet {

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int httpErrorCode = getErrorCode(req);
        String errorMsg = "Http Error Code: "+httpErrorCode;

        switch (httpErrorCode) {
            case 400: {
                errorMsg += " Bad Request";
                break;
            }
            case 401: {
                errorMsg += " Unauthorized";
                break;
            }
            case 404: {
                errorMsg += " Resource not found";
                break;
            }
            case 500: {
                errorMsg += " Internal Server Error";
                break;
            }
        }

        req.setAttribute("errorMsg", errorMsg);
        req.getRequestDispatcher("/WEB-INF/public/errorPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
