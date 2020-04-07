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
	
	public ObservableList<Expertise> vakkenBETA = FXCollections.observableArrayList(
            new Expertise("Biology"), new Expertise("Math"), new Expertise("Informatica"), new Expertise("English"));
	public ObservableList<Expertise> vakkenALFA = FXCollections.observableArrayList(
            new Expertise("History"), new Expertise("English"), new Expertise("French"), new Expertise("Spanish"));
	
	public Course myCourse1 = new Course("FA1A");	
	public Course myCourse2 = new Course("FA2A");	
	// @ email  // @password
	public ObservableList<Person> allAccounts = FXCollections.observableArrayList(
			new Student("jesse@student.inholland.nl", "Welkom", "Jesse", "Pinkman", 25, myCourse1, vakkenBETA),
			new Student("jane@student.inholland.nl", "Welkom", "Jane", "Margolus", 25, myCourse2, vakkenBETA),
			new Student("flynn@student.inholland.nl", "Welkom", "Walter Jr.", "White", 25, myCourse2, vakkenALFA),
			new Student("combo@student.inholland.nl", "Welkom", "Combo", "D.", 23, myCourse2, vakkenBETA),
			new Teacher("walter@teacher.inholland.nl", "Welkom", "Walter", "White", 53, new Expertise("Chemisty")),
			new Teacher("saul@teacher.inholland.nl", "Welkom", "Saul", "Goodman", 59, new Expertise("Math")),
			new Administrator("guss@administrator.inholland.nl", "Welkom", "Guss", "Fring", 49));
	
	public ObservableList<Course> myCourses = FXCollections.observableArrayList(myCourse1, myCourse2);
	
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
			
			String userLoginEmail = txt_user.getText();
			String userPassword = txt_password.getText();
				
				if (userLoginEmail.matches(".+student.inholland.nl") && SecurityCheck(userLoginEmail, userPassword)) 
				{
					try {							
					Student student = (Student) ConvertPersonToType(userLoginEmail);
					HomeScreenFX_Student screen = new HomeScreenFX_Student(student);
					screen.start(new Stage());
				    window.close();
					} catch (Exception e)
					{
						lMessage.setText("Unable to load screen.");	
					}	
				}

				else if(userLoginEmail.matches(".+teacher.inholland.nl") && SecurityCheck(userLoginEmail, userPassword))
				{
					try {	
						Teacher teacher = (Teacher) ConvertPersonToType(userLoginEmail);
						HomeScreenFX_Teacher screen = new HomeScreenFX_Teacher(teacher, allAccounts, myCourses);
						screen.start(new Stage());
						window.close();
							} catch (Exception e)
							{
								lMessage.setText("Unable to load screen.");	
							}			
				}
				
				else if(userLoginEmail.matches(".+administrator.inholland.nl") && SecurityCheck(userLoginEmail, userPassword))
				{					
					try {	
					Administrator admin = (Administrator) ConvertPersonToType(userLoginEmail);
					HomeScreenFX_Admin screen = new HomeScreenFX_Admin(admin, allAccounts);
					screen.start(new Stage());
					window.close();
						} catch (Exception e)
						{
							lMessage.setText("Unable to load screen.");	
						}
				}
				
				else {	lMessage.setText("Unknown email address.");	}
		});
	}
		
	
	public Person ConvertPersonToType(String text) {
		// TODO Auto-generated method stub
		for(Person p : allAccounts)
		{
			if(p.email.equals(text))
				return p;
		}
		
		return null;
	}


	public Boolean SecurityCheck(String email, String password)
	{
		for(Person p : allAccounts)
		{
			if((p.getEmail().equals(email)) && (p.getPassword().equals(password)))
			{ return true; }
		}
	
		return false;
	}
	

}
