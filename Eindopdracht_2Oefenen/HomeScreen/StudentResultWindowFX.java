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
	
	public TableView<Expertise> myTable;
	
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
		
        TableColumn<Expertise, String> vakNaamCol = new TableColumn<Expertise, String>("Vakken");
        vakNaamCol.setCellValueFactory(new PropertyValueFactory<Expertise, String>("vakNaam"));
        myTable.getColumns().add(vakNaamCol);
        
        TableColumn<Expertise, Double> p1Col = new TableColumn<Expertise, Double>("P1");
        p1Col.setCellValueFactory(new PropertyValueFactory<Expertise, Double>("p1"));
        p1Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p1Col.setOnEditCommit(new EventHandler<CellEditEvent<Expertise, Double>>() {
            @Override
            public void handle(CellEditEvent<Expertise, Double> t) {
            	
            	for(Expertise v : student.vakkenLijst)
            	{
            		if(v.equals(((Expertise) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
            		{
            			v.setP1((Double)t.getNewValue());
            		}
            	}
            }
        });
        myTable.getColumns().add(p1Col);
        
        TableColumn<Expertise, Double> p2Col = new TableColumn<Expertise, Double>("P2");
        p2Col.setCellValueFactory(new PropertyValueFactory<Expertise, Double>("p2"));
        p2Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p2Col.setOnEditCommit(new EventHandler<CellEditEvent<Expertise, Double>>() {
            @Override
            public void handle(CellEditEvent<Expertise, Double> t) {
            	            	
            	for(Expertise v : student.vakkenLijst)
            	{
            		if(v.equals(((Expertise) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
            		{
            			v.setP2((Double)t.getNewValue());
            		}
            	}
            }
        });
        myTable.getColumns().add(p2Col);
        
        TableColumn<Expertise, Double> p3Col = new TableColumn<Expertise, Double>("P3");
        p3Col.setCellValueFactory(new PropertyValueFactory<Expertise, Double>("p3"));
        p3Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p3Col.setOnEditCommit(new EventHandler<CellEditEvent<Expertise, Double>>() {
            @Override
            public void handle(CellEditEvent<Expertise, Double> t) {
            	
            	for(Expertise v : student.vakkenLijst)
            	{
            		if(v.equals(((Expertise) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
            		{
            			v.setP3((Double)t.getNewValue());
            		}
            	}
            }
        }
    );  
        myTable.getColumns().add(p3Col);
                
        
        TableColumn<Expertise, Double> p4Col = new TableColumn<Expertise, Double>("P4");
        p4Col.setCellValueFactory(new PropertyValueFactory<Expertise, Double>("p4"));
        p4Col.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        p4Col.setOnEditCommit(new EventHandler<CellEditEvent<Expertise, Double>>() {
            @Override
            public void handle(CellEditEvent<Expertise, Double> t) {
            	
            	for(Expertise v : student.vakkenLijst)
            	{
            		if(v.equals(((Expertise) t.getTableView().getItems().get(t.getTablePosition().getRow()))))
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
