package lt.vibenchat.demo.config;

import org.hibernate.SessionFactory;

public class HibernateDaoManagerHelper {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = HibernateConfig.initConfiguration().buildSessionFactory();
        }

        return sessionFactory;
    }
}
