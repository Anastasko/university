package task5;

import java.util.List;

public class Suit {
	
	int min;
	
	int max;
	
	List<Item> list;
	
	Double weight;

	public Suit(int min, int max, List<Item> list, Double weight) {
		super();
		this.min = min;
		this.max = max;
		this.list = list;
		this.weight = weight;
	}
	
	public Double transportPrice(){
		return weight*10;
	}

	public boolean  contain(Item item){
		for(Item i : list){
			if (i.equals(item)){
				return true;
			}
		}
		return false;
	}
	
	public double calcWeight(){
		double w = 0;
		for(Item item : list){
			w+=item.getWeight();
		}
		return w;
	}
	
	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public List<Item> getList() {
		return list;
	}

	public void setList(List<Item> list) {
		this.list = list;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

}
