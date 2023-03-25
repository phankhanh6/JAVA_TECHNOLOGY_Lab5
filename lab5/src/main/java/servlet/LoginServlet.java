package servlet;

import dao.UserDAO;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="loginServlet",value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    public String message ="";
    private UserDAO userDAO = UserDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkbox = req.getParameter("remember");
//        System.out.println(username);
//        System.out.println(password);
//        System.out.println(checkbox);
        int a = 1;
        List<User> list = null;
        list = userDAO.readAll();

        if(username.trim().equals("") || password.trim().equals("")){
            message="Vui long dien day du thong tin";
        }else {

            if (checkUsername(list, username) == false) {
                //show message -> sign up
//            System.out.println("Account does not exist");
                message = "Account does not exist";
            } else {
                //nhap password
                //nhap pwd sai -> show message
                if (checkPassword(list, username, password) == false) {
                    message = "Password invalid";
//                System.out.println("Password invalid");
                } else {//nhap pwd dung
                    if (checkbox != "") {
                        Cookie cookieUsername = new Cookie("username", username);
                        Cookie cookiePassword = new Cookie("password", password);
                        cookieUsername.setMaxAge(60 * 60 * 24 * 30);
                        cookiePassword.setMaxAge(60 * 60 * 24 * 30);
                    }
                    a=0;
//                message = "Success";
//                System.out.println("success");
//                    resp.sendRedirect("home.jsp");
                }

            }
        }
        if(a!=1) {
            req.setAttribute("username", username);
            req.getRequestDispatcher("home.jsp").forward(req,resp);
//            resp.sendRedirect("home.jsp");
        }else{
            req.setAttribute("message",message);
            req.getRequestDispatcher("index.jsp").forward(req,resp);
        }
    }
    private boolean checkUsername(List<User> list, String username){
        for (User u : list){
            if(u.getUsername() != null){
                if(u.getUsername().equals(username))
                    return true;
            }
        }
        return false;
    }
    private boolean checkPassword(List<User> list,String username,String password){
        for (User u : list){
            if(username.equals(u.getUsername())){
                if(u.getPassword().equals(password))
                    return true;
            }
        }
        return false;
    }
}
