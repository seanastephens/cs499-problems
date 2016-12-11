import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	

		int n = in.nextInt();
		in.nextLine();
		int[] as = splitString(in.nextLine());

		int m = in.nextInt();
		in.nextLine();
		int[] bs = splitString(in.nextLine());

		assert(n == as.length);
		assert(m == bs.length);

		lics(as, bs);
	}

	public static void lics(int[] as, int[] bs) {

		short[][] longest = new short[as.length + 1][bs.length + 1];
		long[][] max = new long[as.length + 1][bs.length + 1];

		for(int i = 0; i < max.length; i++) max[i][0] = Long.MIN_VALUE;
		for(int i = 0; i < max[0].length; i++) max[0][i] = Long.MIN_VALUE;

		for(int i = 1; i <= as.length; i++) {
			for(int j = 1; j <= bs.length; j++) {
				if(as[i-1] == bs[j-1]) {
					int p = i-1;
					int q = j-1;
					while(max[p][j] >= as[i-1]) p--;
					while(max[i][q] >= bs[j-1]) q--;
					longest[i][j] = (short)(longest[p][q] + 1);
					max[i][j] = as[i-1];
				} else {
					if(longest[i][j-1] < longest[i-1][j]) {
						longest[i][j] = longest[i-1][j];
						max[i][j] = max[i-1][j];
					} else if(longest[i][j-1] > longest[i-1][j]) {
						longest[i][j] = longest[i][j-1];
						max[i][j] = max[i][j-1];
					} else {
						longest[i][j] = longest[i][j-1];
						max[i][j] = Math.min(max[i][j-1], max[i-1][j]);
					}
				}
				System.out.print(max[i][j] + ":" + longest[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println(longest[as.length][bs.length]);
		String out = "";
		int i = as.length;
		int j = bs.length;
		while(i > 0 && j > 0) {
			out = max[i][j] + " " + out;
			int p = i;
			int q = j;
			while(longest[i][q] >= longest[i][j]) q--;
			while(longest[p][j] >= longest[i][j]) p--;

			if(longest[i][q] < longest[p][j]) {
				j = q;
			} else if(longest[i][q] > longest[p][j]) {
				i = p;
			} else {
				j = q;
				i = p;
			}
		}
		System.out.println(out);
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

	public static int[] splitString(String s) {
		String[] ss = s.split(" ");
		int xs[] = new int[ss.length];
		for(int i = 0; i < xs.length; i++)
			xs[i] = Integer.parseInt(ss[i]);
		return xs;
	}
}
