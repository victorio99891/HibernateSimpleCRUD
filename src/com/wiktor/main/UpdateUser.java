package com.wiktor.main;

import com.wiktor.etities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UpdateUser {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            User user = session.get(User.class, 7);
            user.setName("Yolka");

            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }


}
