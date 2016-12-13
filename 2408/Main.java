import java.util.*;

public class Main{
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		String x;
		Map<String, List<String>> mappy = new HashMap<String, List<String>>();
		while(in.hasNext()){
			x = in.next();
			String sorted = stringSort(x);
			if(!mappy.containsKey(sorted)) mappy.put(sorted, new ArrayList<String>());
			
			mappy.get(sorted).add(x);
		}
		List<List<String>> groups = new ArrayList<List<String>>(mappy.values());
		for(List<String> group : groups) Collections.sort(group);

		Collections.sort(groups, new Comparator<List<String>>(){
			public int compare(List<String> a, List<String> b){ 
				return a.size() != b.size() ? b.size() - a.size() : a.get(0).compareTo(b.get(0));
			}		
		 });

		for (int i = 0; i < Math.min(5, groups.size()); i++){
			System.out.printf("Group of size %d: ", groups.get(i).size());
			String last = "";
			for(String se : groups.get(i)){
				if(last.equals(se)) continue;
				System.out.print(se + " ");	
				last = se;
			}
			System.out.println(".");
		}
		
	}

	public static String stringSort(String a ){
		char[] w = a.toCharArray();
		Arrays.sort(w);
		return new String(w);
	}
}
