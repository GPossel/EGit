import javax.swing.JOptionPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Course {

	public ObservableList<Student> courseFollowers = FXCollections.observableArrayList();
	public String course_Name = new String();
	
	public Course()
	{	}
	
	public Course(String course_Name)
	{
		courseFollowers = FXCollections.observableArrayList();
		this.course_Name = course_Name;
	}
	
	public Course(ObservableList<Student> studentList, String course_Name)
	{
		this.courseFollowers = studentList;
		this.course_Name = course_Name;
	}
	
	public String getCourse_Name() {
		return course_Name;
	}

	public void setCourse_Name(String course_Name) {
		this.course_Name = course_Name;
	}

	public ObservableList<Student> getCourseFollowers() {
		return courseFollowers;
	}
	
	public void setCourse_CourseFollowers(ObservableList<Student> listStudents) {
		courseFollowers = listStudents;
	}
	
	public void deleteStudentFromCourse(Student student)
	{
		courseFollowers.remove(student);
	}
	
	public void addStudentToCourse(Student student)
	{
		if(!courseFollowers.contains(student))
		{
			courseFollowers.add(student);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Student already in crouse!");	
		}
	}
}
