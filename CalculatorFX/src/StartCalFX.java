import javafx.scene.control.Label;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class StartCalFX extends Application{

	public TextField txt_input1 = new TextField();
	public TextField txt_input2 = new TextField();
	public Label lMessage = new Label("My message:");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			launch();
	}
	
	@Override
	public void start(Stage window) throws Exception
	{
		window.setTitle("Simple calculator");
		
		GridPane gridPane = new GridPane();
		gridPane.setPadding(new Insets(10,10,10,10));
		gridPane.setVgap(10);
		gridPane.setHgap(8);

		gridPane.add(txt_input1, 0, 0);
		gridPane.add(new Label("/"), 0, 1);
		gridPane.add(txt_input2, 0, 2);
		
		gridPane.add(lMessage, 1, 3);
		
		Button btn_calculate = new Button("Calculate");
		gridPane.add(btn_calculate, 3, 3);
		Button btn_reset = new Button("Reset");
		gridPane.add(btn_calculate, 3, 4);
		
		btn_calculate.setOnAction(e->{

		});
		
		btn_reset.setOnAction(e->{
			txt_input1.setText("");
			txt_input2.setText("");
		});
		
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
	}
}
