package join_table.unidirectional.app;

import org.hibernate.Session;
import org.hibernate.Transaction;

import join_table.unidirectional.Course;
import join_table.unidirectional.Student;

import hibernate.utils.HibernateUtil;

public class App {

	public static void main(String[] args) {
		App app = new App();
		
		app.insertion();    // insertion can also be done using insertion.txt file commands
                            // do not forget to update mapping resource in hibernate.cfg.xml file
	
		//app.loadCourse();
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
	
	public void deleteCourse() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
				
		String hql = "select c from Course c where c.name like 'Engenharia Civil'";
		Course course = (Course)session.createQuery(hql).getSingleResult();

		session.delete(course);

		transaction.commit();
		session.close();
	}

}