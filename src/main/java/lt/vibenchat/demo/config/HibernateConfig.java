package lt.vibenchat.demo.config;

import lt.vibenchat.demo.pojo.*;
import org.hibernate.cfg.Configuration;

public class HibernateConfig {
    public static Configuration initConfiguration() {
        Configuration configuration = new Configuration();
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/vibe");
        configuration.setProperty("hibernate.connection.username", "dev");
        configuration.setProperty("hibernate.connection.password", "password");
        configuration.setProperty("hibernate.connection.autocommit", "true");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");

        configuration.addAnnotatedClass(ChatMessage.class);
        configuration.addAnnotatedClass(UserMusic.class);
        configuration.addAnnotatedClass(RoomUser.class);
        configuration.addAnnotatedClass(Music.class);
        configuration.addAnnotatedClass(Room.class);
        configuration.addAnnotatedClass(User.class);

        return configuration;
    }
}
