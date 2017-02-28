package taskg;

public class Node {
	
	int id;
	private Cluster cluster;
	String name;
	double [] x;
	
	public Node(int id, int n) {
		setCluster(new Cluster(id));
		this.id = id;
		x = new double[n];
	}

	public String toString() {
		return id + ") " + format(name) + x[0] + "\t" + x[1] + "\t" + x[2];
	}

	private String format(String name) {
		while (name.length()<20){
			name += " ";
		}
		return name;
	}

	public void setCluster(Cluster b) {
		this.cluster = b;
		this.cluster.nodes.add(this);
	}
	
	double distanceTo(Node node){
		double sum = 0;
		for(int i=0; i<x.length; ++i){
			double d = x[i] - node.x[i];
			sum += d*d;
		}
		return Math.sqrt(sum);
	}

	public Cluster getCluster() {
		return this.cluster;
	}
	
}
