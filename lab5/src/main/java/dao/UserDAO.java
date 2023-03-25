package dao;

import pojo.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtils;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static UserDAO getInstance(){return new UserDAO();}
    public boolean create(User s){
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(s);
            transaction.commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return true;
    }
    public User read(String id){
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        User s =null;
        try{
            s = session.get(User.class,id);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return s;
    }
    public List<User> readAll(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<User> list = new ArrayList<User>();
        try{
            list = session.createQuery("from User").list();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
        return list;
    }
    public void update(String id){
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            User s = session.get(User.class,id);
//            session.saveOrUpdate(s);
            s.setName("change Name");
            session.update(s);
            transaction.commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
    public void delete(String id){
        Session session = HibernateUtils.getSessionFactory().openSession();
//        Session session = HibernateUtils.getFactory().openSession();
        try{
            Transaction transaction = session.beginTransaction();
            User s =session.find(User.class,id);
            session.remove(s);
            transaction.commit();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            session.close();
        }
    }
}
