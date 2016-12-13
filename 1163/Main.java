import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		int[][] vals = new int[101][101];
		for(int row = 0; row < n; row++) {
			for(int col = 0; col <= row; col++) {
				vals[row][col] = s.nextInt();
			}
		}

		while(n > 0) {
			n--;
			for(int i = 0; i <= n; i++) {
				vals[n][i] += Math.max(vals[n+1][i], vals[n+1][i+1]);
			}
		}

		System.out.println(vals[0][0]);

	}
}
