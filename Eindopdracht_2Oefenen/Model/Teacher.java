import java.io.Serializable;

public class Teacher extends Person implements Serializable{
	
	public Vak teaching_Vak;
	
	public Teacher(String userName, String password, String firstName, String lastName, String email, Integer age) {
		super(userName, password, firstName, lastName, email, age);
		// TODO Auto-generated constructor stub
		this.role = Roles.Teacher;
	}
	
	public Teacher(String userName, String password, String firstName, String lastName, String email, Integer age, Vak vak) {
		super(userName, password, firstName, lastName, email, age);
		// TODO Auto-generated constructor stub
		this.role = Roles.Teacher;
		this.teaching_Vak = vak;
	}
}
