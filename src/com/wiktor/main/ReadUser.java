package com.wiktor.main;


import com.wiktor.etities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Scanner;

public class ReadUser {

    public static void main(String[] args) {


        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .buildSessionFactory();


        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();

            List<User> userList = session.createQuery("from User").getResultList();
            System.out.println("All user: ");
            displayUsers(userList);
            System.out.println();

            userList = session.createQuery("from User u where u.name = 'Andrzej'").getResultList();
            System.out.println("User with name \"Andrzej\": ");
            displayUsers(userList);
            System.out.println();


            userList = session.createQuery("from User u where u.id=3").getResultList();
            System.out.println("User with id \"5\": ");
            displayUsers(userList);
            System.out.println();

            userList = session.createQuery("from User u where u.id=1 or u.id=6").getResultList();
            System.out.println("User with id \"1 or 6\": ");
            displayUsers(userList);
            System.out.println();


            userList = session.createQuery("from User u where favColor like 'm%'").getResultList();
            System.out.println("User color starts with \"m\": ");
            displayUsers(userList);
            System.out.println();


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
