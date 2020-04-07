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
	
	public ReadingStudent reading = new ReadingStudent();
	public ObservableList<Student> listOfStudentsFromTXT = reading.ReadListOfStudents();
	public ObservableList<Student> ListOfStudentsFromClass = FXCollections.observableArrayList();

	
	public ObservableList<Student> ListOfStudentsFromVAR = FXCollections.observableArrayList(
			new Student("Jane", "Welkom", "Jane", "Margolus", "", 23),
			new Student("Combo", "Welkom", "Combo", "D.", "", 27));

	
	
	public HomeScreenFX_Teacher(Teacher teacher) 
	{
		this.teacher = teacher;		
	}

	public TableView<Student> myTable = new TableView<>();

	
	public void start(Stage window) throws Exception
	{
		window.setTitle("List of Grades");
		window.setWidth(1000);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		MenuBar menuBar = new MenuBar();	
		MenuMaker(menuBar, window);
		gridPane.add(menuBar, 0, 0);
				
		gridPane.add(new Label("Voornaam: " + teacher.firstName),  0, 1);
		gridPane.add(new Label("Achternaam: " + teacher.lastName), 0, 2);
		gridPane.add(new Label("Username: " + teacher.UserName), 0, 3);
		gridPane.add(new Label("E-mail: " + teacher.email), 0, 4);
			
		WritingStudent stu = new WritingStudent();
		ChoiceBox<Class_Student> choiceBox = new ChoiceBox<Class_Student>(stu.myCourses);
		
		choiceBox.setOnAction(e -> RefillMyTable(choiceBox.getValue()));
		
        TableColumn<Student, String> firstNameCol = new TableColumn<Student, String>("First name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("firstName"));
        myTable.getColumns().add(firstNameCol);		
        
        TableColumn<Student, String> lastNameCol = new TableColumn<Student, String>("Last name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("lastName"));
        myTable.getColumns().add(lastNameCol);		
        
        TableColumn<Student, String> stu_emailCol = new TableColumn<Student, String>("E-mail");
        stu_emailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        myTable.getColumns().add(stu_emailCol);		
		
		ColumnConstraints column = new ColumnConstraints();
		column.setPercentWidth(100);
		gridPane.getColumnConstraints().add(column);		
		
        VBox root = new VBox(myTable);
        gridPane.add(root, 0 ,7);	
			
		Label title = new Label("Your courses: ");
		title.setStyle("-fx-font: 14 arial;");
		gridPane.add(title, 0, 5);
		
		gridPane.add(choiceBox, 0, 6);
		
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
	}
	
	public void RefillMyTable(Class_Student newValue)
	{
		ListOfStudentsFromClass = newValue.getCourseFollowers();
		myTable.setItems(ListOfStudentsFromClass);
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
	
}
