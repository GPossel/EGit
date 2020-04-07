import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadingStudent{	
	
	public ObservableList<Student> ListOfStudents = FXCollections.observableArrayList();
	
	
	public ReadingStudent()
	{}
	
	public ObservableList<Student> ReadListOfStudents()
	{
		try
		{
			// bestand is aangemaakt en te vinden in C:\Users\gentl\eclipse-workspace\Eindopdracht
		FileInputStream fi  = new FileInputStream("students.ser");
		ObjectInputStream oi = new ObjectInputStream(fi);

		Object obj;
		while((obj = oi.readObject()) != null) {
			ListOfStudents.add((Student) obj);
		}

		oi.close();
		fi.close();
				
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ListOfStudents;

	}
	
}
