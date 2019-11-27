public class sieve {
	public int upperBound;
	int[][] sequence;
	String sequenceString = "";
	String primeString = "";
	
	public void createSequence() {
		sequence = new int[upperBound][2];
		int j=0;
		for (int i=2; i < upperBound+1; i++) {
			sequence[j][0] = i;
			j++;
		}
		for (int i=0; i < upperBound-1; i++) {
			System.out.print(sequence[i][0] + ", ");
		}
		System.out.println();
	}
	public void crossOutMultiples() {
		int i=0;
		int k;
		while(i<upperBound-1) {
			if(sequence[i][1]==0) {
				sequence[i][1]=2;
				k = sequence[i][0];
				for (int j=i; j < upperBound-2;) {
					 j++;
					if(sequence[j][0]%k==0) {
						sequence[j][1]=1;
					}
				}
			}
			else i++;
		}
	}
}
