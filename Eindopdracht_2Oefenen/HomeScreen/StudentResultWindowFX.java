import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;

public class StudentResultWindowFX extends Application{
	
	public Student student;
	
	StudentResultWindowFX(Student student)
	{
		this.student = student;
	}
	
	public TableView<Vak> myTable;
	
	public void start(Stage window) throws Exception
	{	
		window.setWidth(1000);
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);
		Label title = new Label(student.email + " " + " grades: ");
		title.setStyle("-fx-font: 24 arial;");
		
		gridPane.add(title, 0, 3);	
		
		myTable = new TableView<>(student.vakkenLijst);
		
        TableColumn<Vak, String> vakNaamCol = new TableColumn<Vak, String>("Vakken");
        vakNaamCol.setCellValueFactory(new PropertyValueFactory<Vak, String>("vakNaam"));
        myTable.getColumns().add(vakNaamCol);
        
        TableColumn<Vak, Double> p1Col = new TableColumn<Vak, Double>("P1");
        p1Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p1"));
        p1Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p1Col.setOnEditCommit(new EventHandler<CellEditEvent<Vak, Double>>() {
            @Override
            public void handle(CellEditEvent<Vak, Double> t) {
            	
            	for(Vak v : student.vakkenLijst)
            	{
            		if(v.equals(((Vak) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
            		{
            			v.setP1((Double)t.getNewValue());
            		}
            	}
            }
        });
        myTable.getColumns().add(p1Col);
        
        TableColumn<Vak, Double> p2Col = new TableColumn<Vak, Double>("P2");
        p2Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p2"));
        p2Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p2Col.setOnEditCommit(new EventHandler<CellEditEvent<Vak, Double>>() {
            @Override
            public void handle(CellEditEvent<Vak, Double> t) {
            	            	
            	for(Vak v : student.vakkenLijst)
            	{
            		if(v.equals(((Vak) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
            		{
            			v.setP2((Double)t.getNewValue());
            		}
            	}
            }
        });
        myTable.getColumns().add(p2Col);
        
        TableColumn<Vak, Double> p3Col = new TableColumn<Vak, Double>("P3");
        p3Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p3"));
        p3Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p3Col.setOnEditCommit(new EventHandler<CellEditEvent<Vak, Double>>() {
            @Override
            public void handle(CellEditEvent<Vak, Double> t) {
            	
            	for(Vak v : student.vakkenLijst)
            	{
            		if(v.equals(((Vak) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
            		{
            			v.setP3((Double)t.getNewValue());
            		}
            	}
            }
        }
    );  
        myTable.getColumns().add(p3Col);
                
        
        TableColumn<Vak, Double> p4Col = new TableColumn<Vak, Double>("P4");
        p4Col.setCellValueFactory(new PropertyValueFactory<Vak, Double>("p4"));
        p4Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p4Col.setOnEditCommit(new EventHandler<CellEditEvent<Vak, Double>>() {
            @Override
            public void handle(CellEditEvent<Vak, Double> t) {
            	
            	for(Vak v : student.vakkenLijst)
            	{
            		if(v.equals(((Vak) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
            		{
            			v.setP4((Double)t.getNewValue());
            		}
            	}
          }
        }
    );       
        
        myTable.getColumns().add(p4Col);
        myTable.setEditable(true);
        
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(100);
		gridPane.getColumnConstraints().add(column1);
		

        VBox root = new VBox(myTable);
        gridPane.add(root, 0 ,4);
		
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
	}	
}
