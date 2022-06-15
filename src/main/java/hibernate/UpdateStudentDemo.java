package hibernate;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class UpdateStudentDemo {

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/student_tracker");
        properties.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "admin");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.current_session_context_class", "thread");

        SessionFactory factory = new Configuration().addProperties(properties).addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.openSession();

        try {
            int studentId = 1;

            session.beginTransaction();

            Student theStudent = session.get(Student.class, studentId);
            System.out.println(theStudent);

            System.out.println("updating");
            theStudent.setFirstName("Jotaro");

            session.getTransaction().commit();
            System.out.println(theStudent);

            session = factory.getCurrentSession();
            session.beginTransaction();

            //update without where, very dangerous
            session.createQuery("update Student set email='JoJo@Joestar.com'").executeUpdate();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }

    }
}
