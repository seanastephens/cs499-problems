import java.util.*;

public class Main {

	static HashMap<Character,TreeSet<String>> in;
	static HashMap<Character,TreeSet<String>> out;

	public static void main(String[] args) {
		Scanner sin = new Scanner(System.in);	
		int ts = Integer.parseInt(sin.nextLine());
		while(ts --> 0) {
			int w = Integer.parseInt(sin.nextLine());
			in = new HashMap<Character,TreeSet<String>>();
			out = new HashMap<Character,TreeSet<String>>();
			for(char c : "qwertyuiopasdfghjklzxcvbnm".toCharArray()) {
				in.put(c, new TreeSet<String>(new LastFirst()));
				out.put(c, new TreeSet<String>(new LastFirst()));
			}

			while(w --> 0) {
				String s = sin.nextLine();
				out.get(s.charAt(0)).add(s);
				in.get(s.charAt(s.length() - 1)).add(s);
			}
			cycle();
		}
	}

	static class LastFirst implements Comparator<String> {
		public int compare(String a, String b) {
			if(a.charAt(a.length() - 1) != b.charAt(b.length() - 1)) {
				return a.charAt(a.length() - 1) - b.charAt(b.length() - 1);
			}
			return a.compareTo(b);
		}
	}

	public static void cycle() {
		Set<Character> odds = new HashSet<Character>();
		Set<Character> zeroes = new HashSet<Character>();
		Character start = null;
		Character end = null;
		for(char c : in.keySet()) {
			if(in.get(c).size() > out.get(c).size()) {
				if(end != null) { System.out.println("***"); return; }
				end = c;
				odds.add(c);
			}
			if(in.get(c).size() < out.get(c).size()) {
				if(start != null) { System.out.println("***"); return; }
				start = c;
				odds.add(c);
			}
			if(out.get(c).size() == 0) zeroes.add(c);
		}
		if(odds.size() != 0 && odds.size() != 2) {
			System.out.println("***");
			return;
		}

		Set<Character> starts = out.keySet();
		for(char c : zeroes) starts.remove(c);

		char c = odds.size() > 0 ? start : Collections.min(starts);
		List<String> path = new LinkedList<String>();	
		while(out.get(c) != null && out.get(c).size() > 0) {
			String next = out.get(c).pollFirst();
			path.add(next);
			c = next.charAt(next.length() - 1);
		}

		ListIterator<String> iter = path.listIterator(0);
		while(iter.hasNext()) {
			String cur = iter.next();
			List<String> cycle;
			while((cycle = cycleAt(cur.charAt(0))).size() > 0) {
				iter.previous();
				for(String s : cycle) iter.add(s);
				iter.next();
			}
		}

		for(char d : out.keySet()) if(out.get(d).size() > 0) { System.out.println("***"); return; }

		for(int i = 0; i < path.size(); i++)
			System.out.print((i > 0 ? "." : "") + path.get(i));
		System.out.println();
	}

	public static List<String> cycleAt(char c) {
		char cc = c;
		List<String> path = new ArrayList<String>();	
		if(out.get(c).size() == 0) return path;
		do {
			String next = out.get(c).pollFirst();
			path.add(next);
			c = next.charAt(next.length() - 1);
		} while(out.get(c).size() > 0 && cc != c);
		return path;
	}
}
