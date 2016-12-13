import java.io.*;
import java.util.*;

public class Main {

	static ArrayList<Double> chip_xs = new ArrayList<Double>();
	static ArrayList<Double> chip_ys = new ArrayList<Double>();

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		while(s.hasNextDouble()) {
			chip_xs.add(s.nextDouble());
			chip_ys.add(s.nextDouble());
		}

		if(chip_xs.size() == 0) {
			System.out.println(0);
			return;
		}

		// we have at least one chip
		int max_count = 1;
		final double EPS = 0.000001;
		final double RADIUS = 2.5;
		final double DIAMETER = 5.0;
		for(int i = 0; i < chip_xs.size(); i++) {
			for(int j = 0; j < chip_xs.size(); j++) {
				if(i == j) continue;
				if(dist2(i, j) >= DIAMETER * DIAMETER + EPS) continue;

				double x1 = chip_xs.get(i);
				double y1 = chip_ys.get(i);
				double x2 = chip_xs.get(j);
				double y2 = chip_ys.get(j);

				double vx = x2 - x1;
				double vy = y2 - y1;
				double mv2 = vx * vx + vy * vy;

				double nx1 = vy / Math.sqrt(mv2);
				double ny1 = vx / Math.sqrt(mv2);
				double nx2 = -vy / Math.sqrt(mv2);
				double ny2 = vx / Math.sqrt(mv2);

				double k = Math.sqrt(2.5 * 2.5 - mv2 / 4);
				double center_x_1 = x1 + 0.5 * vx + k * nx1;
				double center_x_2 = x1 + 0.5 * vx + k * nx2;
				double center_y_1 = y1 + 0.5 * vy + k * ny1;
				double center_y_2 = y1 + 0.5 * vy + k * ny2;

				for(double[] pair : new double[][] { 
					{ center_x_1, center_y_1}, 
					{ center_x_2, center_y_2}
				}) {
					int count = 0;
					for(int c = 0; c < chip_xs.size(); c++) {
						if(dist2(c, pair[0], pair[1]) < 2.5 * 2.5 + EPS) {
							count++;
						}
					}
					if(count > max_count) {
						max_count = count;
					}
				}
			}
		}
		System.out.println(max_count);
	}

	public static double dist2(int i, double x1, double y1) {
		double x2 = chip_xs.get(i);
		double y2 = chip_ys.get(i);
		return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
	}

	public static double dist2(int i, int j) {
		return dist2(i, chip_xs.get(j), chip_ys.get(j));
	}

}
