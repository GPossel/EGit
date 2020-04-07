import java.io.Serializable;

public class Teacher extends Person implements Serializable{
	
	public Expertise teaching_Vak;

	public Teacher(String email, String password, String firstname, String lastname, Integer age, Expertise teaching_Vak) {
		super(email, password, firstname, lastname, age);
		this.teaching_Vak = teaching_Vak;
	}

	public Expertise getTeaching_Vak() {
		return teaching_Vak;
	}

	public void setTeaching_Vak(Expertise teaching_Vak) {
		this.teaching_Vak = teaching_Vak;
	}
}
