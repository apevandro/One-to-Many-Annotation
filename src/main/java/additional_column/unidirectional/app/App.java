package additional_column.unidirectional.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import additional_column.unidirectional.Course;
import additional_column.unidirectional.Student;

import hibernate.utils.HibernateUtil;

public class App {

	public static void main(String[] args) {
		App app = new App();

		app.insertion();       // insertion can also be done using insertion.txt file commands
                               // do not forget to update mapping resource in hibernate.cfg.xml file

		//app.loadCourse();
		//app.deleteStudent();
		//app.deleteCourse();
	}

	public void insertion() {

		Student student1 = new Student(111, "Edvaldo Carlos Silva", "Av. Sao Carlos, 186", "Sao Carlos-SP");
	    Student student2 = new Student(112, "Joao Benedito Scapin", "R. Jose Bonifacio, 70", "Sao Carlos-SP");
	    Student student3 = new Student(113, "Carol Antonia Silveira", "R. Luiz Camoes, 120", "Ibate-SP");

	    Course course = new Course(2142, "Engenharia Civil", 1500);

		course.getStudents().add(student1);
		course.getStudents().add(student2);
		course.getStudents().add(student3);

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		session.save(course);  // cascade
        transaction.commit();
		session.close();
	}

	public void loadCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Course course = (Course) session.load(Course.class, 2142);
		System.out.println("Course name: " + course.getName());

		course.getStudents().stream()
		                    .forEach(student -> System.out.println("Student name: " + student.getName()));

		session.close();
	}

	public void deleteStudent() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Student student = (Student)session.load(Student.class, 113);
		session.delete(student);

		transaction.commit();
		session.close();
	}

	public void deleteCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();

		Course course = (Course) session.load(Course.class, 2142);
		session.delete(course);  // cascade. delete-orphan

		transaction.commit();
		session.close();
	}

}