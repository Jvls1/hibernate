package hibernate;

import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Properties;

public class QueryStudentDemo {

    public static void main(String[] args) {

        Properties properties = new Properties();

        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/student_tracker");
        properties.setProperty("dialect", "org.hibernate.dialect.PostgreSQL10Dialect");
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "admin");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.show_sql", "true");


        SessionFactory factory = new Configuration().addProperties(properties).addAnnotatedClass(Student.class).buildSessionFactory();

        Session session = factory.openSession();

        try {
            session.beginTransaction();

            List<Student> students = session.createQuery("from Student").getResultList();
            showStudents(students);

            students = session.createQuery("from Student s where s.lastName = 'joestar4'").getResultList();
            System.out.println("last name joestar4");
            showStudents(students);

            students = session.createQuery("from Student s where s.lastName = 'joestar4' OR s.firstName = 'jojo'").getResultList();
            System.out.println("last name joestar 4 or first name jojo ");
            showStudents(students);
//
            students = session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();
            System.out.println("email like %gmail.com");
            showStudents(students);

            session.getTransaction().commit();
        } finally {
            factory.close();
        }

    }

    private static void showStudents(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }
}
