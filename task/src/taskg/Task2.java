package taskg;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Task2 {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("input.txt"));
		Task2 task2 = new Task2();
		task2.readInput(scanner);
		task2.solve();
		task2.writeOutput();
	}

	private void writeOutput() {
		for(Cluster cluster : clusters()){
			System.out.println(cluster.toString());
		}
	}

	private void solve() {
		System.out.println();
		Set<Cluster> clusters = clusters();
		while (clusters.size() > 2) {
			Pair<Cluster> pair = findNearest(clusters);
			System.out.println("з'єднання кластерів " + pair.one.id + " та " + pair.two.id + " (відстань=" + Cluster.distance(pair.one,  pair.two) + ")");
			Cluster.merge(pair.one, pair.two);
			String info = "";
			for(Node node : nodes){
				info += node.getCluster().id + " ";
			}
			System.out.println("-> Результат: " + info);
			clusters = clusters();
		}
		System.out.println();
	}

	private Pair<Cluster> findNearest(Set<Cluster> clusters) {
		Pair<Cluster> result = new Pair<>();
		for (Cluster a : clusters)
			for (Cluster b : clusters) {
				if (a.equals(b))
					continue;
				if (result.isUndefined() || Cluster.distance(a, b) < Cluster.distance(result.one, result.two)){
					result.one = a;
					result.two = b;
				}
			}
		return result;
	}

	private Set<Cluster> clusters() {
		return nodes.stream().map(node -> node.getCluster()).collect(Collectors.toSet());
	}

	List<Node> nodes = new ArrayList<>();

	private void readInput(Scanner scanner) {
		int n = scanner.nextInt();
		scanner.nextLine();
		System.out.println(n);
		for (int i = 0; i < n; ++i) {
			Node node = new Node(i, 3);
			String line = scanner.nextLine();
			List<String> argx = toList(line);
			node.name = argx.get(0);
			node.x[0] = Double.parseDouble(argx.get(1));
			node.x[1] = Double.parseDouble(argx.get(2));
			node.x[2] = Double.parseDouble(argx.get(3));
			System.out.println(node.toString());
			nodes.add(node);
		}
	}

	List<String> toList(String line) {
		List<String> list = new ArrayList<>();
		String buffer = "";
		line = line + "\t";
		for (int i = 0; i < line.length(); ++i) {
			if (line.charAt(i) != '\t') {
				buffer += line.charAt(i);
			} else {
				if (buffer.length() > 0) {
					list.add(buffer);
				}
				buffer = "";
			}
		}
		return list;
	}

}
