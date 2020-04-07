import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
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
		window.setWidth(650);
		window.toFront();
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10 , 50, 50, 50));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		gridPane.add(new Label("Email: "), 0, 1);
		TextField txt_email = new TextField();
		gridPane.add(txt_email, 1, 1);
		
		ComboBox<String> comboBox = new ComboBox<>();
		comboBox.setItems(FXCollections.observableArrayList("@student.inholland.nl", "@teacher.inholland.nl", "@administrator.inholland.nl"));
        
		// If you choose a teacher as email value: get a profession
		Label lbl_expertise = new Label("Field of expertise");
		TextField txt_expertise = new TextField();
		txt_expertise.setPrefWidth(100);
		lbl_expertise.setVisible(false);
		txt_expertise.setVisible(false);
		gridPane.add(lbl_expertise, 0, 6);
		gridPane.add(txt_expertise, 1, 6);
		
		// If you choose a student as email value: put student in class
		Label lbl_course = new Label("Name of class:");
		TextField txt_course = new TextField();
		txt_course.setPrefWidth(100);
		lbl_course.setVisible(false);
		txt_course.setVisible(false);
		gridPane.add(lbl_course, 0, 6);
		gridPane.add(txt_course, 1, 6);
		
		comboBox.setOnAction(event -> {
        String cbox_Item = comboBox.getSelectionModel().getSelectedItem().toString();
        
			if(cbox_Item != null)
			{					
				if(cbox_Item.matches(".+teacher.inholland.nl"))
				{									
					lbl_expertise.setVisible(true);
					txt_expertise.setVisible(true);	
					lbl_course.setVisible(false);
					txt_course.setVisible(false);

				}
				else if(cbox_Item.matches(".+student.inholland.nl"))
				{
					lbl_course.setVisible(true);
					txt_course.setVisible(true);
					lbl_expertise.setVisible(false);
					txt_expertise.setVisible(false);
				}
				else
				{
					lbl_expertise.setVisible(false);
					txt_expertise.setVisible(false);
					lbl_course.setVisible(false);
					txt_course.setVisible(false);
				}	
			}
		}		
        );				
		
		gridPane.add(comboBox, 2, 1);
		
		gridPane.add(new Label("Password: "), 0, 2);
		TextField txt_password = new TextField();
		txt_password.setPrefWidth(100);
		gridPane.add(txt_password, 1, 2);
		
		gridPane.add(new Label("Firstname: "),  0, 3);
		TextField txt_voornaam = new TextField();
		txt_voornaam.setPrefWidth(100);
		gridPane.add(txt_voornaam, 1, 3);
		
		gridPane.add(new Label("Lastname: "), 0, 4);
		TextField txt_achternaam = new TextField();
		txt_achternaam.setPrefWidth(100);
		gridPane.add(txt_achternaam, 1, 4);
		
		gridPane.add(new Label("Age: "), 0, 5);
		TextField txt_age = new TextField();
		txt_age.setPrefWidth(100);
		gridPane.add(txt_age, 1, 5);
		
		Label lMessage = new Label();
		gridPane.add(lMessage, 0, 7);
		
		
		Button btn_save = new Button("Save");
		btn_save.setOnAction(event -> 	
		{
			String email = txt_email.getText();
			String password = txt_password.getText();	
			String firstname = txt_voornaam.getText();
			String lastname = txt_achternaam.getText();		
			Integer age = 0;

		try {
			if(password.isBlank() | firstname.isBlank() | lastname.isBlank() | email.isBlank())
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
					String cbox_Item = comboBox.getSelectionModel().getSelectedItem().toString();
					
					if(cbox_Item.matches(".+teacher.inholland.nl"))
					{
						email += "@teacher.inholland.nl";
						Vak newProfession = new Vak(txt_expertise.toString());
						Teacher teacher = new Teacher(email, password, firstname, lastname, age, newProfession);
						listOfPersons.add(teacher);
					}
					else if (cbox_Item.matches(".+student.inholland.nl"))
					{
						email += "@student.inholland.nl";
						Course newCourse = new Course(txt_course.getText());
						Student student = new Student(email, password, firstname, lastname, age, newCourse, null);
						listOfPersons.add(student);
					}
					else if(cbox_Item.matches(".+administrator.inholland.nl"))
					{
						email += "@administrator.inholland.nl";
						Administrator admin = new Administrator(email, password, firstname, lastname, age);
						listOfPersons.add(admin);
					}
					else {
					Person p = new Person(email, password, firstname, lastname, age);
					listOfPersons.add(p);
					}
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
