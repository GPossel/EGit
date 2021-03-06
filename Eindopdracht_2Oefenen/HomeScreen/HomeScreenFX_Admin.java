import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class HomeScreenFX_Admin extends Application{
		
	public Administrator admin;
	public ObservableList<Person> allAccounts = FXCollections.observableArrayList();

	public HomeScreenFX_Admin(Administrator admin, ObservableList<Person> allAccounts) 
	{
		this.admin = admin;		
		this.allAccounts = allAccounts;
	}

	public void start(Stage window) throws Exception
	{
		window.setTitle("List of Accounts");
		window.setWidth(1000);
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		
		MenuBar menuBar = new MenuBar();	
		MenuMaker(menuBar, window);
		gridPane.add(menuBar, 0, 0);
				
		gridPane.add(new Label("E-mail: " + admin.email), 0, 1);
		
		TableView<Person> myTable = new TableView<>(allAccounts);	
		
		Button removeButton = new Button("Remove account");
		removeButton.setOnAction(g-> { 
			if(myTable.getSelectionModel().getSelectedItem() == null)
			{
				JOptionPane.showMessageDialog(new JFrame(), "Please select a account first.");		
			}
			else 
			{				
				Person p = (Person)myTable.getSelectionModel().getSelectedItem();
				allAccounts.remove(p);
			}
			
		});
		
		gridPane.add(removeButton, 0, 5);
		
		
		Button addButton = new Button("Add new account");
		addButton.setOnAction(e -> {
		
			AdjustUserFX_Window addUserScreen = new AdjustUserFX_Window(allAccounts);
		try {
			addUserScreen.start(new Stage());
			} catch (Exception e1) {
			// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(new JFrame(), "Unable to load screen.");	
			}
		});
		
		
		gridPane.add(addButton, 0 , 6);
		
		Label title = new Label("Your users: ");
		title.setStyle("-fx-font: 16 arial;");
		gridPane.add(title, 0, 7);

					
        TableColumn<Person, String> emailCol = new TableColumn<Person, String>("Email");
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        emailCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>()
        		{ 
        	@Override 
        	public void handle(CellEditEvent<Person, String> event)
        	{
        		Person p = myTable.getSelectionModel().getSelectedItem();
        		p.setEmail(event.getNewValue().toString());
        	}
        		});
        
        
        myTable.getColumns().add(emailCol);	
        
        
        TableColumn<Person, String> passwordCol = new TableColumn<Person, String>("Password");
        passwordCol.setCellValueFactory(new PropertyValueFactory<Person, String>("password"));
        passwordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        passwordCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>()
		{ 
			@Override 
			public void handle(CellEditEvent<Person, String> event)
			{
				Person p = myTable.getSelectionModel().getSelectedItem();
				p.setPassword(event.getNewValue().toString());
			}
		});
        
        myTable.getColumns().add(passwordCol);	
		
        TableColumn<Person, String> firstNameCol = new TableColumn<Person, String>("Firstname");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstname"));
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>()
		{ 
			@Override 
			public void handle(CellEditEvent<Person, String> event)
			{
				Person p = myTable.getSelectionModel().getSelectedItem();
				p.setLastname(event.getNewValue().toString());
			}
		});
        
        myTable.getColumns().add(firstNameCol);	
        
        TableColumn<Person, String> lastNameCol = new TableColumn<Person, String>("Lastname");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastname"));
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>()
		{ 
			@Override 
			public void handle(CellEditEvent<Person, String> event)
			{
				Person p = myTable.getSelectionModel().getSelectedItem();
				p.setLastname(event.getNewValue().toString());
			}
		});
        myTable.getColumns().add(lastNameCol);	
        
        
        TableColumn<Person, Integer> ageCol = new TableColumn<Person, Integer>("Age");
        ageCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("age"));
        ageCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        ageCol.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, Integer>>()
		{ 
			@Override 
			public void handle(CellEditEvent<Person, Integer> event)
			{
				Person p = myTable.getSelectionModel().getSelectedItem();
				p.setAge((Integer)event.getNewValue());
			}
		});
        myTable.getColumns().add(ageCol);	
        
        myTable.setEditable(true);
		
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100);
		gridPane.getColumnConstraints().add(column1);
		
        VBox root = new VBox(myTable);
        gridPane.add(root, 0 , 8);
		
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
