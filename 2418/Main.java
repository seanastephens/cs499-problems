import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Map<String, Integer> m = new HashMap<String, Integer>();
		int total = 0;
		while(in.hasNextLine()) {
			total++;
			String s = in.nextLine();
			m.put(s, (m.get(s) != null ? m.get(s) : 0) + 1);
		}
		List<String> keys = new ArrayList<String>(m.keySet());
		Collections.sort(keys);
		for(String k : keys) {
			System.out.printf("%s %.4f\n", k, (m.get(k) * 100.0) / total);
		}
	}
}
