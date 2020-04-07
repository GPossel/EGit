import java.io.Serializable;
import javafx.collections.ObservableList;

public class Student extends Person implements Serializable {
	
	public Class_Student myCourse;
	public ObservableList<Vak> vakkenLijst;


	public Student(String email, String password, String firstname, String lastname, Integer age,
			Class_Student myCourse, ObservableList<Vak> vakkenLijst) {
		super(email, password, firstname, lastname, age);
		this.myCourse = myCourse;
		this.vakkenLijst = vakkenLijst;
	}


	public Class_Student getMyCourse() {
		return myCourse;
	}


	public void setMyCourse(Class_Student myCourse) {
		this.myCourse = myCourse;
	}


	public ObservableList<Vak> getVakkenLijst() {
		return vakkenLijst;
	}


	public void setVakkenLijst(ObservableList<Vak> vakkenLijst) {
		this.vakkenLijst = vakkenLijst;
	}

	
}
