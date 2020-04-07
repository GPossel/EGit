import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class AdjustUserFX_Window extends Application{	
	
	public ObservableList<Person> listOfPersons;
	
	
	public AdjustUserFX_Window(ObservableList<Person> listOfPersons) {
		// TODO Auto-generated constructor stub
		this.listOfPersons = listOfPersons;
	}

	@Override
	public void start(Stage window) throws Exception {
		// TODO Auto-generated method stub	
		
		window.setTitle("Make new user...");
		window.setWidth(410);
		window.toFront();
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10 , 50, 50, 50));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
				
		gridPane.add(new Label("Username: "),  0, 1);
		TextField txt_userName = new TextField();
		gridPane.add(txt_userName, 1, 1);
		
		gridPane.add(new Label("Password: "), 0, 2);
		TextField txt_password = new TextField();
		gridPane.add(txt_password, 1, 2);
		
		gridPane.add(new Label("Voornaam: "),  0, 4);
		TextField txt_voornaam = new TextField();
		gridPane.add(txt_voornaam, 1, 4);
		
		gridPane.add(new Label("Achternaam: "), 0, 5);
		TextField txt_achternaam = new TextField();
		gridPane.add(txt_achternaam, 1, 5);
		
		gridPane.add(new Label("Email: "), 0, 6);
		TextField txt_email = new TextField();
		gridPane.add(txt_email, 1, 6);
		
		gridPane.add(new Label("Age: "), 0, 7);
		TextField txt_age = new TextField();
		gridPane.add(txt_age, 1, 7);
		
		Label lMessage = new Label();
		gridPane.add(lMessage, 0, 8);
		
		
		Button btn_save = new Button("Save");
		btn_save.setOnAction(event -> 	
		{
			String userName = txt_userName.getText();
			String password = txt_password.getText();	
			String firstname = txt_voornaam.getText();
			String lastname = txt_achternaam.getText();		
			String email = txt_email.getText();
			Integer age = 0;

		try {
			if(userName.isBlank() | password.isBlank() | firstname.isBlank() | lastname.isBlank() | email.isBlank())
			{	JOptionPane.showMessageDialog(new JFrame(), "Please insert values for all fields."); }
			else {
					
				try {
				age = Integer.parseInt(txt_age.getText());
				}catch (Exception e)
				{
					JOptionPane.showMessageDialog(new JFrame(), "Please insert age values between 0 and 140");
				}
				
				if (age < 0 || age > 140) {  JOptionPane.showMessageDialog(new JFrame(), "Only Positive Numbers & no Letters Please!"); }								
				else
				{
					Person p = new Person(userName, password, firstname, lastname, email, age);
					listOfPersons.add(p);
					window.close();
				}
			}
		}catch (Exception e)
		{
			JOptionPane.showMessageDialog(new JFrame(), "Please insert values for all fields.");
		}
		});
		
		Button btn_cancel = new Button("Cancel");
		btn_cancel.setOnAction(event -> { window.close(); });
		
		gridPane.add(btn_save, 0, 8);
		gridPane.add(btn_cancel, 3, 8);
		
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		
		window.show();
		
	}

}
