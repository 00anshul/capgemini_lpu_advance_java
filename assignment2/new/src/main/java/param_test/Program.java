package param_test;

public class Program {
public boolean ispalindrome(String s) {
	StringBuilder rvr = new StringBuilder(s);
	if(rvr.reverse().toString().equals(s)) {
		return true;
	}
	return false;
}
public int add(int a,int b) {
	return a+b;
}
}
