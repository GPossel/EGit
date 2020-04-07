import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ToDoList extends Application
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			launch();
	}
	
	public ObservableList<Item> list = FXCollections.observableArrayList(
			new Item("feed cat", true),
			new Item("Play a game", false));
	
	@Override
	public void start(Stage window) throws Exception
	{
		window.setTitle("To Do List");
		
		MenuBar menuBar = new MenuBar();	
		MenuMaker(menuBar);
		
		TableView<Item> myTable = new TableView<>();
		TableViewMaker(myTable);
		
		VBox root = new VBox(menuBar, myTable);
		
		FileMaker(list);
		FileReader();
		
	//	root.getChildren().addAll();
		Scene scene = new Scene(root, 300, 250);
		window.setScene(scene);
		window.show();
	}
	
	
	private void FileReader() {
		// TODO Auto-generated method stub
		try {
			FileInputStream fos = new FileInputStream("C:\\Users\\gentl\\git\\repository\\CalculatorFX");
			ObjectInputStream io = new ObjectInputStream(fos);
			
			// Read objects
			Item i1 = (Item) io.readObject();
			Item i2 = (Item) io.readObject();

			//
			list.add(i1);
			list.add(i2);
	//		System.out.println(i1.toString());
	//		System.out.println(i2.toString());

			io.close();
			fos.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private void FileMaker(ObservableList<Item> list) 
	{
		// TODO Auto-generated method stub
		try (FileOutputStream fos = new FileOutputStream(new File("items.txt"));
				ObjectOutputStream oos = new ObjectOutputStream(fos);) 
		{
				for (Item item : list) 
				{				oos.writeObject(item.toString());				}
				
				fos.close();
				oos.close();
				} catch (IOException e) 
				{ 				e.printStackTrace(); }
				// print the whole exception stack				
	}
	public void TableViewMaker(TableView<Item> myTable) {
		TableColumn<Item, String> colName = new TableColumn<Item, String>();
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn<Item, Boolean> colBool = new TableColumn<Item, Boolean>();
		colBool.setCellValueFactory(new PropertyValueFactory<>("done"));
		
		myTable.getColumns().add(colName);
		myTable.getColumns().add(colBool);
	//	myTable.getColumns().addAll(colName, colBool);

		myTable.setItems(list);
	}

	public void MenuMaker(MenuBar menuBar)
	{		
		Menu fileMenu = new Menu("File");
		Menu aboutMenu = new Menu("About");
		MenuItem loadItem = new MenuItem("Load...");
	//	loadItem.setOnAction(e-> {});
		MenuItem saveItem = new MenuItem("Save...");
	//	saveItem.setOnAction(a-> {});
		MenuItem aboutItem = new MenuItem("About");
	//	aboutItem.setOnAction(g-> {});
		fileMenu.getItems().addAll(loadItem, saveItem);
		aboutMenu.getItems().add(aboutItem);
		menuBar.getMenus().addAll(fileMenu, aboutMenu);
	}
	
}
