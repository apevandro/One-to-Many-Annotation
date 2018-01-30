package additional_column.bidirectional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@Column(name = "CourseId")
    private int courseId;
	
	@Column(name = "Name")
    private String name;
	
	@Column(name = "TotalCredits")
    private int totalCredits;

	@OneToMany(mappedBy = "course", orphanRemoval = true)
	@Cascade(CascadeType.SAVE_UPDATE)
    private Set<Student> students = new HashSet<Student>();

	public Course() {}

    public Course(int courseId, String name, int totalCredits) {
    	this.courseId = courseId; 
        this.name = name;
        this.totalCredits = totalCredits;
    }

	public int getCourseId() {
		return courseId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTotalCredits() {
		return totalCredits;
	}

	public void setTotalCredits(int totalCredits) {
		this.totalCredits = totalCredits;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public void addStudent(Student student) {
		getStudents().add(student);
		student.setCourse(this);
	}

}