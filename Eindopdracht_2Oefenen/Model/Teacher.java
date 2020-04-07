import java.io.Serializable;

public class Teacher extends Person implements Serializable{
	
	public Vak teaching_Vak;

	public Teacher(String email, String password, String firstname, String lastname, Integer age, Vak teaching_Vak) {
		super(email, password, firstname, lastname, age);
		this.teaching_Vak = teaching_Vak;
	}

	public Vak getTeaching_Vak() {
		return teaching_Vak;
	}

	public void setTeaching_Vak(Vak teaching_Vak) {
		this.teaching_Vak = teaching_Vak;
	}
}
