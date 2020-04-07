public class Vak 
{
	private String vakNaam = new String(); 
	public Double p1;
	public Double p2;
	public Double p3;
	public Double p4;
	
	
	
	public Vak(String vakNaam) {
		super();
		this.vakNaam = vakNaam;
	}

	

	public Vak(String vakNaam, Double p1, Double p2, Double p3, Double p4) {
		super();
		this.vakNaam = vakNaam;
	}




	public String getVakNaam() {
		return vakNaam;
	}


	public void setVakNaam(String vakNaam) {
		this.vakNaam = vakNaam;
	}


	public Double getP1() {
		return p1;
	}


	public void setP1(Double p1) {
		this.p1 = p1;
	}


	public Double getP2() {
		return p2;
	}


	public void setP2(Double p2) {
		this.p2 = p2;
	}


	public Double getP3() {
		return p3;
	}



	public void setP3(Double p3) {
		this.p3 = p3;
	}



	public Double getP4() {
		return p4;
	}



	public void setP4(Double p4) {
		this.p4 = p4;
	}
	

	
	
	
	
	
}
