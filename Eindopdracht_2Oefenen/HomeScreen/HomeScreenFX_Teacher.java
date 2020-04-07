import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HomeScreenFX_Teacher extends Application{
		
	public Teacher teacher;	
	public ObservableList<Student> listOfStudents = FXCollections.observableArrayList();
	public ObservableList<Course> myCourses;
	
	public HomeScreenFX_Teacher(Teacher teacher, ObservableList<Person> listOfPersons, ObservableList<Course> Courses) 
	{
		this.teacher = teacher;	
		this.myCourses = Courses;
		
		FilterListToStudents(listOfPersons);
		ConnectCourseToStudents(listOfStudents, Courses);
	}


	public TableView<Student> myTable = new TableView<>();

	
	public void start(Stage window) throws Exception
	{
		window.setTitle("List of courses");
		window.setWidth(1000);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		MenuBar menuBar = new MenuBar();	
		MenuMaker(menuBar, window);
		gridPane.add(menuBar, 0, 0);
		
		Label lbl_name = new Label(teacher.firstname + " " + teacher.lastname + " (" + teacher.age + "), " + teacher.getTeaching_Vak().getVakNaam());
		lbl_name.setStyle("-fx-font: 20 arial;");
		gridPane.add(lbl_name, 0, 1);	
		gridPane.add(new Label("E-mail: " + teacher.email), 0, 2);
		Label title = new Label("Course overview:");
		title.setStyle("-fx-font: 16 arial;");
		gridPane.add(title, 0, 3);
				
			
		ChoiceBox<Course> choiceBox = new ChoiceBox<>(myCourses);	
		choiceBox.setOnAction(e -> RefillMyTable(choiceBox.getValue()));	
		gridPane.add(choiceBox, 0, 6);
		
        TableColumn<Student, String> stu_emailCol = new TableColumn<Student, String>("E-mail");
        stu_emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        myTable.getColumns().add(stu_emailCol);	
        
        TableColumn<Student, String> firstNameCol = new TableColumn<Student, String>("Firstname");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        myTable.getColumns().add(firstNameCol);
        
        TableColumn<Student, String> lastNameCol = new TableColumn<Student, String>("Lastname");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        myTable.getColumns().add(lastNameCol);
		
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(100);
		gridPane.getColumnConstraints().add(column);		
		
        VBox root = new VBox(myTable);
        gridPane.add(root, 0 ,7);	
				
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
	}
	
	public void RefillMyTable(Course newValue)
	{
		listOfStudents = newValue.getCourseFollowers();
		myTable.setItems(listOfStudents);
	}


	public void MenuMaker(MenuBar menuBar, Stage window)
	{		
		Menu aboutMenu = new Menu("My account");
		Menu resultsMenu = new Menu("Student Results");
		Menu logoutMenu = new Menu("Logout");

		MenuItem LogoutItem = new MenuItem("Logout");
		LogoutItem.setOnAction(e-> 
		{  
			try {
			InlogScreenFX logout = new InlogScreenFX();
			logout.start(new Stage());
			window.close();
			} catch (Exception ex)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Unable to load screen.");	
			}	
		});
		
		MenuItem resultItem = new MenuItem("Show results");
		resultItem.setOnAction(g-> { 
			if(myTable.getSelectionModel().getSelectedItem() == null)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Please select a student first.");		
			}
			else 
			{				
				try {	
				StudentResultWindowFX Student_Results = new StudentResultWindowFX(myTable.getSelectionModel().getSelectedItem());
				Student_Results.start(new Stage());
					} catch (Exception e)
					{
						JOptionPane.showMessageDialog(new JFrame(), "Unable to load screen.");	
					}	
			}
			
		});
		logoutMenu.getItems().add(LogoutItem);
		resultsMenu.getItems().add(resultItem);
		menuBar.getMenus().addAll(logoutMenu, aboutMenu, resultsMenu);
	}
	
	
	private void ConnectCourseToStudents(ObservableList<Student> listOfStudents,

			ObservableList<Course> courses) {
		
		for (Student stu : listOfStudents)
		{
			for(Course cour : courses)
			{
				if(stu.myCourse.getCourse_Name().equals(cour.getCourse_Name()))
				{
					cour.addStudentToCourse(stu);
				}	
			}
		}		
	}

	private void FilterListToStudents(ObservableList<Person> listOfPersons) {
		// Filter the students that are in the listofPersons.
		for (Person p : listOfPersons)
		{
			if(p instanceof Student)
			{
				listOfStudents.add((Student) p);	
			}		
		}
				
	}

}
