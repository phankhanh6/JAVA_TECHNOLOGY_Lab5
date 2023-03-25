package servlet;

import dao.UserDAO;
import org.apache.commons.validator.routines.EmailValidator;
import pojo.Product;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@WebServlet(name="signupServlet",value="/signupServlet")
public class SignupServlet extends HttpServlet {
    public String message ="";
    private UserDAO userDAO = UserDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("signup.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i=1;
        String fullname = req.getParameter("fullname");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String confirm_password = req.getParameter("password-after");
        if(fullname.equals("") || email.equals("") || username.equals("") || password.equals("") ||  confirm_password.equals("")){
            message ="Vui long nhap day du thong tin";
        }
        if(EmailValidator.getInstance().isValid(email)){
            message ="Vui long nhap email hop le";
        }
        if(password.length() > 6){
            message = "Mat khau phai co it nhat 6 ki tu";
        }
        if(!password.equals(confirm_password)){
            message ="Mat khau khong khop";
        }else{
            i=0;
        }
        List<User> list = null;
        list =userDAO.readAll();
        String id = String.valueOf(list.size());
//        User user = new User(id,fullname,email,username,password);
//        userDAO.create(user);
        userDAO.create(new User(id,fullname,email,username,password));
        if(i!=1)
            resp.sendRedirect("index.jsp");
        else {
            req.setAttribute("message", message);
            req.getRequestDispatcher("signup.jsp").forward(req, resp);
        }

    }

//    public static boolean patternMatches(String emailAddress, String regexPattern) {
//        return Pattern.compile(regexPattern)
//                .matcher(emailAddress)
//                .matches();
//    }
}
