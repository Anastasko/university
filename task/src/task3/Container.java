package task3;

public class Container implements Comparable<Container> {

	public static Weight MAX_WEIGHT;
	
	private Weight currentWeight;
	
	public static Container emptyContainer(){
		return new Container();
	}
	
	public Container() {
		setCurrentWeight(new Weight(0));
	}

	@Override
	public int compareTo(Container o) {
		return this.getCurrentWeight().compareTo(o.getCurrentWeight());
	}

	public Weight getCurrentWeight() {
		return currentWeight;
	}
	
	public void setCurrentWeight(Weight weight) {
		this.currentWeight = weight;
	}

}
