import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tests = in.nextInt();
		in.nextLine();
		for(int test = 0; test < tests; test++) {
			String s = in.nextLine();
			Node x = parse(s);
			String o = bfs(x);
			System.out.println(new StringBuilder(o).reverse().toString());
		}
	}

	static class Node {
		Node(Node first, Node second, char c) {
			this.first = first;
			this.second = second;
			this.c = c;
		}
		Node first;
		Node second;
		char c;
	}

	public static Node parse(String s) {
		Stack<Node> stack = new Stack<Node>();
		for(char next : s.toCharArray()) {
			if('a' <= next && next <= 'z') {
				stack.push(new Node(null, null, next));
			} else {
				stack.push(new Node(stack.pop(), stack.pop(), next));
			}
		}
		return stack.pop();
	}

	public static String bfs(Node root) {
		Queue<Node> q = new ArrayDeque<Node>();
		StringBuilder s = new StringBuilder();
		q.add(root);
		while(q.size() > 0) {
			Node next = q.remove();
			s.append(next.c);
			if(next.first != null) {
				q.add(next.second);
				q.add(next.first);
			}
		}
		return s.toString();
	}
}
