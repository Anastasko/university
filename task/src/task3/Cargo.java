package task3;

public class Cargo implements Comparable<Cargo>{
	
	private boolean isPut;
	
	private Weight weight;

	public Cargo(Weight weight) {
		setWeight(weight);
		setPut(false);
	}
	
	public Cargo clone(){
		return new Cargo(getWeight());
	}

	public boolean isPut() {
		return isPut;
	}

	public void setPut(boolean isPut) {
		this.isPut = isPut;
	}

	@Override
	public int compareTo(Cargo o) {
		return this.getWeight().compareTo(o.getWeight());
	}
	
	public boolean canFitInto(Container container){
		return this.getWeight().plus(container.getCurrentWeight())
				.lessThanOrEqual(Container.MAX_WEIGHT);
	}
	
	public void putInto(Container container){
		Weight newWeight = container.getCurrentWeight().plus(this.getWeight());
		container.setCurrentWeight(newWeight);
		setPut(true);
	}

	public Weight getWeight() {
		return weight;
	}

	public void setWeight(Weight weight) {
		this.weight = weight;
	}
	

}
