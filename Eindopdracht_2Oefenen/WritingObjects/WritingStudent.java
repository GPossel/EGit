import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class WritingStudent 
{
	public Student student;
	
	public Class_Student myCourse1 = new Class_Student("FA1A");	
	public Class_Student myCourse2 = new Class_Student("FA2A");	
	
	public ObservableList<Class_Student> myCourses = FXCollections.observableArrayList(myCourse1, myCourse2);
	
	public ObservableList<Vak> vakken = FXCollections.observableArrayList(
            new Vak("Biology"), new Vak("Math"), new Vak("Informatica"), new Vak("English"));

	public ObservableList<Student> ListOfStudents = FXCollections.observableArrayList(
			new Student("Jesse", "Welkom", "Jesse", "Pinkman", "pinkman@gmail.com", 25, myCourse1, vakken),
			new Student("Skyler", "Welkom", "Skylar", "White", "skylar@annoying.com", 49, myCourse1, vakken),
			new Student("Saul", "Welkom", "Saul", "Goodman", "BetterEmailSaul@gmail.com", 51, myCourse2, vakken),
			new Student("Walter Jr.", "Welkom", "Walter jr.", "White", "Flynn@gmail.com", 16, myCourse2, vakken)
			);
	
	public WritingStudent()
	{
		for(Student stu : ListOfStudents)
		{
			if(stu.myCourse.equals(myCourse1))
			{
				myCourse1.addStudentToCourse(stu);
			}
			else
			{
				myCourse2.addStudentToCourse(stu);
			}
		}
		
	}
	
		public void WriteStudentToTXT()
		{		
			FileOutputStream f = null;
			ObjectOutputStream o = null;

			
			try {
				// bestand is aangemaakt en te vinden in C:\Users\gentl\eclipse-workspace\Eindopdracht
				f = new FileOutputStream(new File("students.ser"));
				o = new ObjectOutputStream(f);
	
				for(Student stu : ListOfStudents)
				{
					// Write objects to file
					o.writeObject(stu);
				}
				
				o.close();
				f.close();
	
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			} catch (IOException e) {
				System.out.println("Error initializing stream");
			} 
			
		}
}


