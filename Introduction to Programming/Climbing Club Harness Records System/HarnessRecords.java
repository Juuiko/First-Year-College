
import java.util.ArrayList;
import java.util.List;

public class HarnessRecords {

	List<Harness> harnessRecords;
	
	HarnessRecords(){
		harnessRecords = new ArrayList<>();
	}
	
	HarnessRecords(String[] make, int[] modelNumber, int[] timesUsed, String[] instructor, boolean[] onLoan, String[] loanee){
		Harness harness;
		for(int i=0; i<make.length; i++) {
			harness = new Harness(make[i], modelNumber[i], timesUsed[i], instructor[i], onLoan[i], loanee[i]);
			harnessRecords.add(harness);
		}
	}
	
	public boolean isEmpty(){
		if(harnessRecords.size()<=0) return true;
		else return false;
	}
	
	public void addHarness (Harness harness){
		harnessRecords.add(harness);
	}
	
	public Harness findHarness(String make, int modelNumber){
		for(int i=0; i<harnessRecords.size(); i++) {
			if(harnessRecords.get(i).getModelNumber()==modelNumber && 
				harnessRecords.get(i).getMake().equals(make)) return harnessRecords.get(i);
		}
		return null;
	}
	
	public Harness checkHarness (String make, int modelNumber, String instructor) {
		Harness checkedHarness = findHarness(make, modelNumber);
		if(checkedHarness!=null&&checkedHarness.isHarnessOnLoan()!=true) {
			checkedHarness.checkHarness(instructor);
			return checkedHarness;
		}
		return null;
	}
	
	public Harness loanHarness (String loanee) {
		for(int i=0; i<harnessRecords.size(); i++) {
			if(harnessRecords.get(i).canHarnessBeLoaned()==true) {
				harnessRecords.get(i).loanHarness(loanee);
				return harnessRecords.get(i);
			}
		}
		return null;
	}
	
	public Harness returnHarness (String make, int modelNumber) {
		Harness harness = findHarness(make, modelNumber);
		if(harness!=null) {
			harness.returnHarness();
			return harness;
		}
		return null;
	}
	
	public Harness removeHarness (String make, int modelNumber) {
		Harness harness = findHarness(make, modelNumber);
		if(harness!=null) {
			harnessRecords.remove(harness);
			return harness;
		}
		else return null;
	}
}
