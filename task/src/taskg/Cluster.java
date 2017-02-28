package taskg;

import java.util.ArrayList;
import java.util.List;

public class Cluster {

	int id;
	
	List<Node> nodes = new ArrayList<>();
	
	public Cluster(int id){
		this.id = id;
	}
	
	static void merge(Cluster a, Cluster b) {
		a.nodes.forEach(node -> {
			node.setCluster(b);
		});
		a.nodes.clear();
	}
	
	@Override
	public String toString() {
		final StringBuilder string = new StringBuilder();
		nodes.stream().sorted((a,b) -> a.id - b.id).forEach(node -> {
			string.append(node.toString() + "\n");
		});
		return string.toString();
	}

	static double distance(Cluster a, Cluster b){
		double result = a.nodes.get(0).distanceTo(b.nodes.get(0));
		for(Node nodeA : a.nodes)
			for(Node nodeB : b.nodes)
				result = Math.min(result, nodeA.distanceTo(nodeB));
		return result;
	}
	
}
