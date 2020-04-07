
public class Item {

	public String name;
	public Boolean done;
	
	public Item(String name, Boolean done) {
		super();
		this.name = name;
		this.done = done;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return " Item [name=" + name + ", done=" + done + "] ";
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}
	
	
}
