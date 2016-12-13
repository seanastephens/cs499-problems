public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);	
		while(true) {
			int x = in.nextInt();
			if(x == 0) break;
			System.out.println(x <= 2 ? "Alice" : "Bob");
		}
	}
}
