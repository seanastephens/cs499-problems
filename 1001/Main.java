import java.math.BigInteger;
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);	
		while(s.hasNextDouble()) {
			double R = s.nextDouble();
			int n = s.nextInt();
			System.out.println(exp(R, n));
		}
	}

	public static String exp(double R, int n) {
		R *= 10000;
		BigInteger b = new BigInteger(""+(int)R);
		b = b.pow(n);
		String s = b.toString();

		s = s.substring(0, Math.max(s.length() - 4 * n, 0)) +
		    "." +
		    repeat("0", 4*n - s.length()) + 
		    s.substring(Math.max(0, s.length() - 4 * n));

		while(s.length() > 0) {
			if(s.charAt(s.length() - 1) == '.') {
				s = s.substring(0, s.length() - 1);
				break;
			} else if(s.charAt(s.length() - 1) == '0') {
				s = s.substring(0, s.length() - 1);
			} else {
				break;
			}
		}
		return s.length() > 0 ? s : "0";
	}

	public static String repeat(String s, int n) {
		String r = "";
		while(n --> 0) {
			r += s;
		}
		return r;
	}
}
