package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.UrlPath;

import java.io.IOException;

@WebServlet("/")
public class LocaleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var language = req.getParameter("lang");
        req.getSession().setAttribute("lang", language);
        var prevPage = req.getHeader("referer");
        var page=prevPage!=null?prevPage: UrlPath.LOGIN;
        resp.sendRedirect(page+"?lang="+language);
    }
}
