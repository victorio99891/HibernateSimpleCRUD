package com.wiktor.main;

import com.wiktor.etities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UpdateUser {

    public static void main(String[] args) {

        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();

            // Update using session.get -> by ID
            User user = session.get(User.class, 7);
            user.setName("Yolka");

            // Update using by obtain users to list by query and then change values by using setters
            List<User> usList = session.createQuery("from User u where u.favColor='PINK'").getResultList();
            displayUsers(usList);
            usList.get(0).setFavColor("aquamarine");

            // Single line update query by using executeUpdate
            session.createQuery("update User u set favColor='red' where u.id=7").executeUpdate();

            //Change value by setter after obtain single result
            Object userObject = session.createQuery("from User u where u.id=7").getSingleResult();
            ((User) userObject).setName("Daniel");


            session.getTransaction().commit();
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
