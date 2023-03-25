package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Product;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    public static ProductDAO getInstance(){return new ProductDAO();}

    public boolean create(Product p){
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            session.save(p);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return true;
    }
    public Product read(String id){
        Product p = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        try{
            p = session.find(Product.class,id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return p;
    }
    public List<Product> readAll(){
        Session session = HibernateUtils.getSessionFactory().openSession();

        List<Product> list= new ArrayList<Product>();
        try{
//            Session session = HibernateUtils.getFactory().openSession();
            session.beginTransaction();
            list =  session.createQuery("From Product ",Product.class).list();
            session.getTransaction().commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    return list;
    }
    public void update(String id){
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Product p = session.find(Product.class,id);
            p.setName("change name of product");
            session.update(p);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    public void delete(String id){
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();
            Product p = session.find(Product.class,id);
            session.remove(p);
            transaction.commit();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }

}
