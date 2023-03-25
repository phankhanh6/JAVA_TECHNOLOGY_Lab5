package utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import pojo.Product;
import pojo.User;

public class HibernateUtils {

    private static final SessionFactory sessionFactory;

    private HibernateUtils() {
        super();
    }

    static {
        Configuration configuration = new Configuration();
        //configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Product.class);
        configuration.addAnnotatedClass(User.class);
        sessionFactory = buildSessionFactory();
    }

    private static SessionFactory buildSessionFactory() {
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder() //
                .configure() // Load hibernate.cfg.xml from resource folder by default
                .build();
        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        getSessionFactory().close();
    }
}

//public class HibernateUtils{
//    private static final SessionFactory FACTORY = buildSessionFactory();
//    private static SessionFactory buildSessionFactory() {
//        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder() //
//                .configure() // Load hibernate.cfg.xml from resource folder by default
//                .build();
//        Metadata metadata = new MetadataSources(serviceRegistry).getMetadataBuilder().build();
//        return metadata.getSessionFactoryBuilder().build();
//    }
//    public static SessionFactory getFactory() {
//        return FACTORY;
//    }
//    public static void close(){
//        getFactory().close();
//    }
//
//}

