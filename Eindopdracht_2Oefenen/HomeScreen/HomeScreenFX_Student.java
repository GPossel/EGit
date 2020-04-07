import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
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
import javafx.util.Callback;


public class HomeScreenFX_Student extends Application{
		
	public Student student;
		
	public HomeScreenFX_Student(Student student) 
	{
			this.student = student;			
	}

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

		Label lbl_name = new Label(student.firstname + " " + student.lastname + " (" + student.age + ")");
		lbl_name.setStyle("-fx-font: 20 arial;");
		gridPane.add(lbl_name, 0, 1);	
		gridPane.add(new Label("E-mail: " + student.email), 0, 2);
		Label title = new Label("Your grades: ");
		title.setStyle("-fx-font: 16 arial;");
		gridPane.add(title, 0, 5);
		
		ObservableList<Vak> vakkenlijst = student.getVakkenLijst();
		
		TableView<Vak> myTable = new TableView<>(vakkenlijst);	
		
        TableColumn<Vak, String> vakNaamCol = new TableColumn<Vak, String>("Vakken");
        vakNaamCol.setCellValueFactory(new PropertyValueFactory<Vak, String>("vakNaam"));
        myTable.getColumns().add(vakNaamCol);
        
        TableColumn<Vak, Double> p1Col = new TableColumn<Vak, Double>("P1");
        p1Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p1"));
        myTable.getColumns().add(p1Col);
        
        TableColumn<Vak, Double> p2Col = new TableColumn<Vak, Double>("P2");
        p2Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p2"));
        myTable.getColumns().add(p2Col);
        
        TableColumn<Vak, Double> p3Col = new TableColumn<Vak, Double>("P3");
        p3Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p3"));
        myTable.getColumns().add(p3Col);
        
        TableColumn<Vak, Double> p4Col = new TableColumn<Vak, Double>("P4");
        p4Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p4"));
        myTable.getColumns().add(p4Col);				
        
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100);
		gridPane.getColumnConstraints().add(column1);
		

        VBox root = new VBox(myTable);
        gridPane.add(root, 0 , 6);
		
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
	}


	public void MenuMaker(MenuBar menuBar, Stage window)
	{		
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

		logoutMenu.getItems().add(LogoutItem);
		menuBar.getMenus().addAll(logoutMenu);
	}
	
}
