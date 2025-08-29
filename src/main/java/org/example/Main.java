package org.example;

import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = null;

        try {

            session = sf.openSession();
            System.out.println("Hibernate Session başarıyla açıldı!");
            System.out.println("Bağlantı başarılı. Hibernate kurulumu tamamlandı.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Hibernate session açılırken bir hata oluştu.");
        } finally {
            if (session != null) {

                session.close();
                System.out.println("Session kapatıldı.");
            }
            HibernateUtil.shutdown();
        }
    }
}
