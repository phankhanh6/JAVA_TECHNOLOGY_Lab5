package servlet;

import dao.ProductDAO;
import pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name="homeServlet",value="/homeServlet")
public class HomeServlet  extends HttpServlet{
    private  ProductDAO productDAO = ProductDAO.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("product", productDAO.readAll());
        req.getRequestDispatcher("home.jsp").forward(req,resp);
//        resp.sendRedirect("home.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int i=0;
        String message ="";
        String name = req.getParameter("name");
        String price = req.getParameter("price");
        if(req.getParameter("action").equals("delete")){
            String id = req.getParameter("id");
            productDAO.delete(id);
            req.getRequestDispatcher("home.jsp").forward(req,resp);
        }
        if(name.equals("")){
            message = "Vui long nhap ten san pham";
            i=1;
        }
        if(price.equals("")){
            message="Vui long nhap gia san pham";
            i=1;
        }
        if(i!=0)
            req.setAttribute("message",message);
        else {
            List<Product> list = null;
            list = productDAO.readAll();
            String id = String.valueOf(list.size());
            double numberprice = Double.parseDouble(price);
            Product product = new Product(id, name, numberprice);
            productDAO.create(product);
            req.setAttribute("product", product);
        }
        req.getRequestDispatcher("home.jsp").forward(req,resp);
//        resp.sendRedirect("home.jsp");

    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        productDAO.delete(id);
        req.getRequestDispatcher("home.jsp").forward(req,resp);
//        resp.sendRedirect("home.jsp");

    }
}
