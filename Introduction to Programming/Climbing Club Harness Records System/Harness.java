
public class Harness {
	
	public static final int MAX_HARNESS_USES_BEFORE_CHECK = 25;
	
	private String make;
	private int modelNumber;
	private int timesUsed;
	private String instructor;
	private boolean onLoan;
	private String loanee;
	
	Harness(String make, int modelNumber, int timesUsed, String instructor, boolean onLoan, String loanee){
		this.setMake(make);
		this.setModelNumber(modelNumber);
		this.timesUsed=timesUsed;
		this.instructor=instructor;
		this.onLoan=onLoan;
		if (onLoan==true) this.loanee=loanee;
		else this.loanee="Climbing Club";
	}
	
	Harness(String make, int modelNumber, String instructor){
		this.setMake(make);
		this.setModelNumber(modelNumber);
		timesUsed=0;
		this.instructor=instructor;
		onLoan=false;
		loanee="Climbing Club";		
	}
	
	public void checkHarness(String instructor) {
		if(onLoan==false) {
			this.instructor=instructor;
			timesUsed=0;
		}
	}
	
	public boolean isHarnessOnLoan() {
		return onLoan;
	}
	
	public boolean canHarnessBeLoaned() {
		if(onLoan==false && timesUsed<MAX_HARNESS_USES_BEFORE_CHECK) return true;
		else return false;
	}
	
	public void loanHarness(String loanee){
		if(canHarnessBeLoaned()==true) {
			timesUsed++;
			onLoan=true;
			this.loanee=loanee;
		}
	}
	
	public void returnHarness() {
		onLoan=false;
		loanee="Climbing Club";
	}
	
	public String toString() {
		return "Make: " + getMake() + "; Model Number: " + getModelNumber() + "; Times Used: " + timesUsed +
					"; Last Checked by: " + instructor + "; Loaned to: " + loanee;
	}

	public int getModelNumber() {
		return modelNumber;
	}

	public void setModelNumber(int modelNumber) {
		this.modelNumber = modelNumber;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}
	
}
