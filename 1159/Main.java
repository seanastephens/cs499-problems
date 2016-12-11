import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	
		int n = in.nextInt();
		in.nextLine();
		String s = in.nextLine();
		assert(n == s.length());
		System.out.println(n - lcs(s, reverse(s)));
	}

	public static int lcs(String a, String b) {
		short[][] m = new short[a.length() + 1][b.length() + 1];
		for(int i = 1; i <= a.length(); i++) {
			for(int j = 1; j <= b.length(); j++) {
				if(a.charAt(i-1) == b.charAt(j-1)) {
					m[i][j] = (short)(m[i-1][j-1]+1);
				} else {
					m[i][j] = (short)(Math.max(m[i][j-1], m[i-1][j]));
				}
			}
		}
		return m[a.length()][b.length()];
	}

	public static String reverse(String s) {
		char[] cs = s.toCharArray();
		for(int i = 0; i < s.length() / 2; i++) {
			cs[i] ^= cs[s.length() - 1 - i];
			cs[s.length() - 1 - i] ^= cs[i];
			cs[i] ^= cs[s.length() - 1 - i];
		}
		return new String(cs);
	}
}
