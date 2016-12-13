public class Main {

	public static void main(String[] args) {
		int[] D = new int[40];
		int[] A = new int[40];

		D[0] = 1;
		A[1] = 1;

		for(int i = 2; i < A.length; i++) {
			D[i] = D[i-2] + 2 * A[i-1];
			A[i] = D[i-1] + A[i-2];
		}

		Scanner s = new Scanner(System.in);
		int n;
		while((n = s.nextInt()) != -1) {
			System.out.println(D[n]);
		}

	}
}
