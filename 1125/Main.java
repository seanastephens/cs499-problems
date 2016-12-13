import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int n;
		while(true) {
			n = in.nextInt();
			if(n==0)break;
			int[][] d = new int[n][n];
			for(int i = 0; i < n; i++) for(int j = 0; j < n; j++) d[i][j] = (i == j) ? 0 : 1001;
			for(int i = 0; i < n; i++) {
				int edges = in.nextInt();
				for(int j = 0; j < edges; j++) {
					int to = in.nextInt();
					d[i][to-1] = in.nextInt();
				}
			}
			for(int k = 0; k < n; k++) {
				for(int i = 0; i < n; i++) {
					for(int j = 0; j < n; j++) {
						d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
					}
				}
			}

			if(hasMax(d)) { System.out.println("disjoint"); continue; }

			int min = Integer.MAX_VALUE;
			int minB = -1;
			for(int i = 0; i < n; i++) {
				int max = Integer.MIN_VALUE;
				for(int j = 0; j < n; j++) {
					max = Math.max(max, d[i][j]);
				}
				if(max < min) { min = max; minB = i; }
			}
			System.out.println((minB+1) + " " + min);


			
			
		}
		
	}

	public static boolean hasMax(int[][] m) {
		for(int j = 0; j < m.length; j++) {
			boolean hasMax = true;
			for(int i = 0; i < m.length; i++) {
				if(m[i][j] < 1001) {
					hasMax = false;
					break;
				}
			}
			if(hasMax) return true;
		}
		return false;
	}
}
