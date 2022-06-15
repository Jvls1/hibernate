package hibernate;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class DeleteStudentDemo {

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

//            Student theStudent = session.get(Student.class, studentId);
//            System.out.println(theStudent);
//            session.delete(theStudent);

            System.out.println("deleting student id = 2");
            session.createQuery("delete from Student where id=2").executeUpdate();

            session.getTransaction().commit();

        } finally {
            factory.close();
        }

    }
}
