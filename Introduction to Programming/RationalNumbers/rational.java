
public class rational {
	int numerator;
	int denominator;
	
	rational(int num, int den){
		numerator=num;
		denominator=den;
	}
	
	rational(int num){
		numerator=num;
		denominator=1;		
	}
	
	public rational rationalAdd(rational num1) {
		int den = num1.denominator*denominator;
		int num = num1.numerator*denominator+numerator*num1.denominator;
		rational answer = new rational(num,den);
		return answer;
	}
	
	public rational rationalSub(rational num1) {
		int den = num1.denominator*denominator;
		int num = num1.numerator*denominator-numerator*num1.denominator;
		rational answer = new rational(num,den);
		return answer;
	}
	
	public rational rationalMul(rational num1) {
		int num = num1.numerator*numerator;
		int den = num1.denominator*denominator;
		rational answer = new rational(num,den);
		return answer;
	}
	
	public rational rationalDiv(rational num1) {
		int num = num1.numerator*denominator;
		int den = num1.denominator*numerator;		
		rational answer = new rational(num,den);
		return answer;
	}
	
	public boolean rationalEqu(rational num1) {
		int r1=numerator*num1.denominator;
		int r2=denominator*num1.numerator;
		if(r1==r2) return true;
		else return false;
	}
	
	public boolean rationalIsLessThan(rational num1) {
		int r1=numerator*num1.denominator;
		int r2=denominator*num1.numerator;
		if(r1>r2) return true;
		return false;
	}
	
	public rational rationalSimplify() {
		int gcd = rationalGCD(numerator,denominator);
		numerator /= gcd;
		denominator /= gcd;
		rational answer = new rational(numerator,denominator);
		return answer;
	}
	
	public int rationalGCD(int a, int b) {
		int temp;
        while(b != 0)
        {
            temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
	
	public String rationalToString() {
		return numerator+"/"+denominator;
	}
}
