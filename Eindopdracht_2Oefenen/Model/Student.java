import java.io.Serializable;
import javafx.collections.ObservableList;

public class Student extends Person implements Serializable {
	
	public Class_Student myCourse;
	public ObservableList<Vak> vakkenLijst;
	
	public ObservableList<Vak> getVakkenLijst() {
		return vakkenLijst;
	}

	public void setVakkenLijst(ObservableList<Vak> vakkenLijst) {
		this.vakkenLijst = vakkenLijst;
	}
	
	
	public Student(String userName, String password, String firstName, String lastName, String email, Integer age, Class_Student myCourse, ObservableList<Vak> vakkenLijst) {
		super(userName, password, firstName, lastName, email, age);
		// TODO Auto-generated constructor stub
		this.role = Roles.Student;
		this.myCourse = myCourse;
		this.vakkenLijst = vakkenLijst;
	}
	
    public Student(String userName, String password, String firstName, String lastName, String email, Integer age, Class_Student myCourse) {
		super(userName, password, firstName, lastName, email, age);
		// TODO Auto-generated constructor stub
		this.role = Roles.Student;
		this.myCourse = myCourse;
	}
	
	public Student(String firstName){	
		super(firstName);
		// TODO Auto-generated constructor stub
		this.role = Roles.Student;

	}
	
	public Student(String userName, String password, String firstName, String lastName, String email, int age) {
		// TODO Auto-generated constructor stub
		super(userName, password, firstName, lastName, email, age);
		this.role = Roles.Student;
	}

	public Class_Student getMyCourse() {
		return myCourse;
	}


	public void setMyCourse(Class_Student myCourse) {
		this.myCourse = myCourse;
	}
}
