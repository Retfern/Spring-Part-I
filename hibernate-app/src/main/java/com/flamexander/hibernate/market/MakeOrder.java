package com.flamexander.hibernate.market;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MakeOrder {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                  .configure("configs/market/hibernate.cfg.xml")
                  .buildSessionFactory();

        Session session = null;
        try {
            // Получить все товары для всех юзеров
            session = factory.getCurrentSession();
            session.beginTransaction();
            List<Users> users = session.createQuery("SELECT s FROM Users s").getResultList();
            for (Users u : users) {
                System.out.println(u.getName() + " Products");
                for (Orders o : u.getOrders()) {
                    for (OrderDetails d : o.getOrderDetails()) {
                        System.out.println(d.getProduct().getTitle() + ": price " +d.getPrice() + " number " + d.getNumber());
                    }
                }
            }
            session.getTransaction().commit();
            System.out.println("-----------------------------------");
            // Какие клиенты купили товар с ID=1
            session = factory.getCurrentSession();
            session.beginTransaction();
            Products product = session.createQuery("SELECT s FROM Products s WHERE s.id = :id", Products.class)
                      .setParameter("id", 1L)
                      .getSingleResult();
            System.out.println(product.getTitle());
            for (OrderDetails d : product.getOrderDetails()) {
                System.out.println(d.getOrder().getUser());
            }
            session.getTransaction().commit();

            //Добавление нового товара
            System.out.println("-----------------------------------");
            session = factory.getCurrentSession();
            session.beginTransaction();
            Products newProduct = new Products("Dragon statue", 100000);
            session.save(newProduct);
            System.out.println("New Product: " + newProduct);
            session.getTransaction().commit();

            System.out.println("-----------------------------------");
            // Удаление товара по ID
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.delete(session.get(Products.class, 9L));
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println(session.get(Products.class, 9L));
            session.getTransaction().commit();

            //Добавление нового пользователя
            System.out.println("-----------------------------------");
            session = factory.getCurrentSession();
            session.beginTransaction();
            Users newUser = new Users("Petrow P.");
            session.save(newUser);
            System.out.println("New User: " + newUser);
            session.getTransaction().commit();

            session = factory.getCurrentSession();
            session.beginTransaction();
            Users user = session.createQuery("SELECT s FROM Users s WHERE s.name = :name", Users.class)
                      .setParameter("name", "Petrow P.")
                      .getSingleResult();
            session.delete(session.get(Users.class, user.getId()));
            session.getTransaction().commit();

        } finally {
            factory.close();
            if (session != null) {
                session.close();
            }
        }
    }
}
