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
		gridPane.add(new Label("/"), 1, 0);
		gridPane.add(txt_input2, 2, 0);
		
		gridPane.add(lMessage, 0, 1);
		
		Button btn_calculate = new Button("Calculate");
		gridPane.add(btn_calculate, 3, 3);
		Button btn_reset = new Button("Reset");
		gridPane.add(btn_reset, 3, 4);
		
		btn_calculate.setOnAction(action->{
			try {
				int number1 = Integer.parseInt(txt_input1.getText());
				int number2 = Integer.parseInt(txt_input2.getText());
				
				double solution = number1 / number2;
				
				lMessage.setText("The solution is:" + solution + ".");
				} 
			catch (ArithmeticException | NumberFormatException e)
			{	if(e instanceof ArithmeticException)
				{ lMessage.setText("Must not divide by zero.."); } 
				else if (e instanceof NumberFormatException)
				{ lMessage.setText("Parsing went wrong"); }
			}
			catch(Exception e)
			{ lMessage.setText("Oops something else went wrong" + e + "is problem.. ");}
		});
		
		btn_reset.setOnAction(e->{
			txt_input1.setText("");
			txt_input2.setText("");
			lMessage.setText("");
		});
		
		Scene scene = new Scene(gridPane);
		window.setScene(scene);
		window.show();
	}
}
