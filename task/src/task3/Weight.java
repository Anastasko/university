package task3;

public class Weight implements Comparable<Weight>{

	public static int comparisons = 0;
	
	private int value;

	public Weight(int value) {
		setValue(value);
	}

	@Override
	public int compareTo(Weight o) {
		++comparisons;
		return this.getValue() - o.getValue();
	}
	
	public Weight plus(Weight weight){
		return new Weight(this.getValue() + weight.getValue());
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public boolean lessThanOrEqual(Weight weight) {
		return this.compareTo(weight) <= 0;
	}
	
}
