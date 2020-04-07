import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class InlogScreenFX extends Application{

	public TextField txt_user = new TextField();
	public TextField txt_password = new TextField();
	public Label lMessage = new Label();
	
	public ObservableList<Teacher> ListOfTeachers = FXCollections.observableArrayList(
			new Teacher("Walter", "Welkom", "Walter", "White", "heisenberg@gmail.com", 50, new Vak("Scheikunde"))
			);
	
	public ObservableList<Administrator> ListOfadmins = FXCollections.observableArrayList(
			new Administrator("Guss", "Welkom", "Guss", "Fring", "lospollos@gmail.com", 47));
	
	public Class_Student myCourse1 = new Class_Student("FA1A");	
	public Class_Student myCourse2 = new Class_Student("FA2A");	
	
	public ObservableList<Class_Student> myCourses = FXCollections.observableArrayList(myCourse1, myCourse2);
	
	public ObservableList<Vak> vakken = FXCollections.observableArrayList(
            new Vak("Biology"), new Vak("Math"), new Vak("Informatica"), new Vak("English"));

	public ObservableList<Student> ListOfStudents = FXCollections.observableArrayList(
			new Student("Jesse", "Welkom", "Jesse", "Pinkman", "pinkman@gmail.com", 25, myCourse1, vakken),
			new Student("Skyler", "Welkom", "Skylar", "White", "skylar@annoying.com", 49, myCourse1, vakken),
			new Student("Saul", "Welkom", "Saul", "Goodman", "BetterEmailSaul@gmail.com", 51, myCourse2, vakken),
			new Student("Walter Jr.", "Welkom", "Walter jr.", "White", "Flynn@gmail.com", 16, myCourse2, vakken)
			);
	
	
	public Person p;
	
	public static void main(String[] args) 
	{
		launch();
	}

	@Override
	public void start(Stage window) throws Exception
	{			
		window.setTitle("Login Screen");
		window.setWidth(400);
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10, 10, 10, 10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		gridPane.add(new Label("Username:"),  0, 0);
		
		txt_user.resize(30, 15);
		gridPane.add(txt_user, 1, 0);
		
		gridPane.add(new Label("Password:"), 0, 1);
		
		txt_password.resize(30, 15);
		gridPane.add(txt_password, 1, 1);
		
		lMessage.setMinWidth(150);
		gridPane.add(lMessage, 0, 3);
		
		Button b = new Button("Login");
		gridPane.add(b, 0, 4);

		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
		
		
		b.setOnAction(event-> {			
			if(SecurityCheck(txt_user.getText(), txt_password.getText()))
			{
				lMessage.setText("Login succesfull!");
				Person p = Get_The_Person();
				
				switch(p.role)	
				{ 
				case Student: 
					try {
					Student student = (Student) p;
					HomeScreenFX_Student screen = new HomeScreenFX_Student(student);
					screen.start(new Stage());
				    window.close();
					} catch (Exception e)
					{
						lMessage.setText("Unable to load screen.");	
					}					

					break;
				
				case Teacher: 
					try {	
						Teacher teacher = (Teacher) p;
						HomeScreenFX_Teacher screen = new HomeScreenFX_Teacher(teacher);
						screen.start(new Stage());
						window.close();
							} catch (Exception e)
							{
								lMessage.setText("Unable to load screen.");	
							}
					
					break;
				
				case Admin: 					
					try {	
					Administrator admin = (Administrator) p;
					HomeScreenFX_Admin screen = new HomeScreenFX_Admin(admin);
					screen.start(new Stage());
					window.close();
						} catch (Exception e)
						{
							lMessage.setText("Unable to load screen.");	
						}
				break;
				
				default: 
				
					lMessage.setText("Geen rechten: Neem contact op met de administratie.");
				}
				
			}
			else
			{
				lMessage.setText("Wrong combination.");
				txt_user.clear();
				txt_password.clear();
			}
		});
	}
	
	private Person Get_The_Person() {
		// TODO Auto-generated method stub
		for(Student stu : ListOfStudents)
		{
			if(stu.UserName.equals(txt_user.getText()) && stu.password.equals(txt_password.getText()))
			{			
				return stu;
			}

		}
		
		for(Teacher T : ListOfTeachers)
		{
			if(T.UserName.equals(txt_user.getText()) && T.password.equals(txt_password.getText()))
			{  
				return T; 
			}
		}
		
		for(Administrator Admin : ListOfadmins)
		{
			if(Admin.UserName.equals(txt_user.getText()) && Admin.password.equals(txt_password.getText()))
			{  
				return Admin; 
			}
		}		
		
		return null;
	}

	public Boolean SecurityCheck(String username, String password)
	{
		for(Student stu : ListOfStudents)
		{
			if(stu.UserName.equals(username) && stu.password.equals(password))
			{  
				p = stu;
				return true; 
			}
		}
		
		for(Teacher T : ListOfTeachers)
		{
			if(T.UserName.equals(username) && T.password.equals(password))
			{  
				p = T;
				return true; 
			}
		}
		
		for(Administrator Admin : ListOfadmins)
		{
			if(Admin.UserName.equals(txt_user.getText()) && Admin.password.equals(txt_password.getText()))
			{  
				p = Admin;
				return true;
			}
		}	
	
		return false;
	}
	

}
