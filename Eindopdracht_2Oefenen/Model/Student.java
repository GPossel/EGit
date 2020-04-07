import java.io.Serializable;
import javafx.collections.ObservableList;

public class Student extends Person implements Serializable {
	
	public Course myCourse;
	public ObservableList<Expertise> vakkenLijst;


	public Student(String email, String password, String firstname, String lastname, Integer age,
			Course myCourse, ObservableList<Expertise> vakkenLijst) {
		super(email, password, firstname, lastname, age);
		this.myCourse = myCourse;
		this.vakkenLijst = vakkenLijst;
	}


	public Course getMyCourse() {
		return myCourse;
	}


	public void setMyCourse(Course myCourse) {
		this.myCourse = myCourse;
	}


	public ObservableList<Expertise> getVakkenLijst() {
		return vakkenLijst;
	}


	public void setVakkenLijst(ObservableList<Expertise> vakkenLijst) {
		this.vakkenLijst = vakkenLijst;
	}

	
}
