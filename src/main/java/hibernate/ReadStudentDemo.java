package hibernate;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class ReadStudentDemo {

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

        Session session = factory.getCurrentSession();

        try {
            Student student = new Student("jojo11", "joestar11", "jojo@joestar.com");
            session.beginTransaction();

            session.save(student);

            session.getTransaction().commit();

            System.out.println("saved");
            System.out.println("student id: " + student.getId());

            session = factory.getCurrentSession();
            session.beginTransaction();

            Student theStudent = session.get(Student.class, student.getId());
            System.out.println(theStudent);

            session.getTransaction().commit();

        } finally {
            factory.close();
        }

    }
}
