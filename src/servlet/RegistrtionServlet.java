package servlet;

import dto.CreateUserDto;
import exception.ValidationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;

import java.io.IOException;

@WebServlet("/reg")

public class RegistrtionServlet extends HttpServlet {
    private final UserService userService = UserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("reg.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var userDto = CreateUserDto.builder()
                .name(req.getParameter("name"))
                .login(req.getParameter("login"))
                .password(req.getParameter("password"))
                .roleId(Integer.valueOf(req.getParameter("role"))).build();

        try {
            userService.create(userDto);
            resp.sendRedirect("/index.jsp");
        } catch (ValidationException validationException) {
            req.setAttribute("errorList", validationException.getErrorList());
        }


    }
}
