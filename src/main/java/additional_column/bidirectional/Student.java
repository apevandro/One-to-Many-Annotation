package additional_column.bidirectional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student {

	@Id
	@Column(name = "StudentId")
	private int studentId;

	@Column(name = "Name")
	private String name;

	@Column(name = "Address")
	private String address;

	@Column(name = "City")
	private String city;

	@ManyToOne
	@JoinColumn(name = "CourseId")
	private Course course;

	public Student() {}

	public Student(int studentId, String name, String address, String city) {
		this.studentId = studentId;
		this.name = name;
		this.address = address;
		this.city = city;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
    public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public boolean equals(Object obj) {
		// Object identity. 
		// Two object references are identical if they point to the same memory location. 
		if (this == obj) {
			return true;
		}
		
		if (!(obj instanceof Student)) {
			return false;
		}
		
		final Student other = (Student) obj;

		// Database identity.
		if (!(other.getStudentId() == studentId)) {
			return false;
		}

		// Object equality, sometimes also referred to as equivalence.
		// Equivalence means that two different (nonidentical) objects have the same value.		
		if (!(other.getName().equals(name) &&
		          other.getAddress().equals(address) &&
		              other.getCity().equals(city))) {
		    return false;
		}
		
		return true;
    }
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (studentId + name + address + city).hashCode();
		return result;
    }

}