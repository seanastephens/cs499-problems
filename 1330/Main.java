import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tests = in.nextInt();
		for(int test = 0; test < tests; test++) {
			int n = in.nextInt();
			int[] nodes = new int[n+1];
			for(int i = 0; i < n-1; i++) {
				int parent = in.nextInt();
				int child = in.nextInt();
				nodes[child] = parent;
			}
			int a = in.nextInt();
			int b = in.nextInt();
			while(nodes[a] != 0) {
				int temp = a;
				a = nodes[a];
				nodes[temp] = 0;
			}

			while(nodes[b] != 0) {
				b = nodes[b];
			}
			System.out.println(b);
		}
	}
}
