import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while(true) {
			int w;
			if((w = in.nextInt()) == -1) break;

			List<VLine> walls = new ArrayList<VLine>();
			List<Point> points = new ArrayList<Point>();
			points.add(new Point(0,5));
			for(int i = 0; i < w; i++) {
				double x = in.nextDouble();
				double y1 = in.nextDouble();
				double y2 = in.nextDouble();
				double y3 = in.nextDouble();
				double y4 = in.nextDouble();
				walls.add(new VLine(x, 0, y1));
				walls.add(new VLine(x, y2, y3));
				walls.add(new VLine(x, y4, 10));
				points.add(new Point(x, y1));
				points.add(new Point(x, y2));
				points.add(new Point(x, y3));
				points.add(new Point(x, y4));
			}
			points.add(new Point(10,5));

			double[][] d = new double[points.size()][points.size()];
			for(int i = 0; i < points.size(); i++) {
				for(int j = 0; j < points.size(); j++) {
					if(points.get(i).x >= points.get(j).x) continue;
					boolean flag = true;
					for(VLine v : walls) {
						if(intersects(new Line(points.get(i).x, points.get(i).y, points.get(j).x, points.get(j).y), v)) flag = false;
					}
					if(flag) d[i][j] = dist(points.get(i), points.get(j));
				}
			}

			System.out.printf("%.2f\n", shortestDist(d));	
		}
	}

	static double shortestDist(double[][] d) {
		double[] costs = new double[d.length];
		for(int i = 0; i < costs.length; i++) costs[i] = Double.MAX_VALUE;

		Set<Integer> done = new HashSet<Integer>();
		done.add(0);
		costs[0] = 0;
		updateCost(costs, d, 0, done);
		while(!done.contains(d.length - 1)) {
			double min = Double.MAX_VALUE;
			int pickMe = -1;
			for(int i = 0; i < costs.length; i++) {
				if(!done.contains(i) && costs[i] < min) {
					min = costs[i];
					pickMe = i;
				}
			}
			updateCost(costs, d, pickMe, done);
			done.add(pickMe);
		}
		return costs[costs.length - 1];
	}

	static void updateCost(double[] costs, double[][] d, int i, Set<Integer> done) {
		for(int j = 0; j < costs.length; j++) {
			if(!done.contains(j) && d[i][j] > 0 && costs[i] + d[i][j] < costs[j])
				costs[j] = costs[i] + d[i][j];
		}
	}

	static double dist(Point a, Point b) {
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

	static class Point {
		double x, y;
		public Point(double x, double y) { this.x = x; this.y = y; }
	}

	static class Line {
		double x1, y1, x2, y2;
		public Line(double x1, double y1, double x2, double y2) {
			if(x1 < x2) {
				this.x1 = x1;
				this.y1 = y1;
				this.x2 = x2;
				this.y2 = y2;
			} else if(x1 > x2) {
				this.x1 = x2;
				this.y1 = y2;
				this.x2 = x1;
				this.y2 = y1;
			} else {
				throw new RuntimeException();
			}
		}
	}

	static class VLine {
		double x, y1, y2;
		public VLine(double x, double y1, double y2) {
			this.x = x;
			this.y1 = y1;
			this.y2 = y2;
		}
	}

	static boolean intersects(Line a, VLine b) {
		if(a.x1 > b.x || a.x2 < b.x) return false;	

		double slope = (a.y2 - a.y1) / (a.x2 - a.x1);
		double interp = a.y1 + (b.x - a.x1) * slope;
		return b.y1 < interp && interp < b.y2;
	}
}
