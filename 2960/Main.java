mport java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	
		while(in.hasNextInt()) {
			int[] S = drop(splitString(in.nextLine()), 1);
			if(S.length == 0) break;

			int t = Integer.parseInt(in.nextLine());

			int[] G = g(S);

			while(t --> 0) {
				int[] K = drop(splitString(in.nextLine()), 1);
				System.out.print(win(K, G) ? "W" : "L");
			}
			System.out.println();
		}
	}

	public static int[] g(int[] S) {
		int[] G = new int[10000 + 1];
		G[0] = 0;
		for(int i = 0; i < G.length; i++) {
			Set<Integer> L = new HashSet<Integer>();
			for(int s : S) {
				if(i - s >= 0) {
					L.add(G[i-s]);
				}
			}

			int min = 0;
			while(L.contains(min)) min++;
			G[i] = min;
		}
		return G;
	}

	public static boolean win(int[] K, int[] G) {
		int g = 0;
		for(int k : K) g ^= G[k];
		return g > 0;
	}

	public static int[] drop(int[] xs, int k) {
		int[] out = new int[Math.max(0, xs.length - k)];
		int i = 0;
		while(k < xs.length) out[i++] = xs[k++];
		return out;
	}

	public static int[] splitString(String s) {
		String[] ss = s.split(" ");
		int xs[] = new int[ss.length];
		for(int i = 0; i < xs.length; i++)
			xs[i] = Integer.parseInt(ss[i]);
		return xs;
	}
}
