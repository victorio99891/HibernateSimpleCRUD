package com.wiktor.main;

import com.wiktor.etities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteUser {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();
        try {
            session.beginTransaction();

            session.createQuery("delete from User u where id=5").executeUpdate();

            User user = session.get(User.class, 6);
            session.delete(user);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
        }


    }


    private static void displayUsers(List<User> userList) {
        for (User user : userList) {
            System.out.println(user.toString());
        }
    }
}
