public class Administrator extends Person{

	public Administrator(String userName, String password, String firstName, String lastName, String email, Integer age) {
		super(userName, password, firstName, lastName, email, age);
		// TODO Auto-generated constructor stub
		this.role = Roles.Admin;
	}
	
	public void Change_Student(Student student)
	{
		
		
	}

	@Override
	public String toString() {
		return "Aministrator [UserName=" + UserName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", age=" + age + ", role=" + role + ", getUserName()="
				+ getUserName() + ", getPassword()=" + getPassword() + ", getFirstName()=" + getFirstName()
				+ ", getLastName()=" + getLastName() + ", getEmail()=" + getEmail() + ", getRole()=" + getRole()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	
}
