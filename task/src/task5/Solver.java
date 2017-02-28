package task5;

import java.util.ArrayList;
import java.util.List;

public class Solver {

	List<Item> items = new ArrayList<>();
	List<Suit> suits = new ArrayList<>();
	List<Integer> temp = new ArrayList<>();
	public double p;

	public List<Integer> getTemp() {
		return temp;
	}

	public void setTemp(List<Integer> temp) {
		this.temp = temp;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Suit> getSuits() {
		return suits;
	}

	public void setSuits(List<Suit> suits) {
		this.suits = suits;
	}

	Item findItemByName(String name) {
		for (Item item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

	private Suit findSuitByT(int t) {
		for (Suit suit : suits) {
			if (suit.getMin() <= t && t <= suit.getMax()) {
				return suit;
			}
		}
		return null;
	}
	
	double func(int i, int j){
		Suit home = suits.get(i);
		double total = -home.transportPrice();
		Suit back = findSuitByT(temp.get(j));
		for(Item item : back.getList()){
			if (!home.contain(item)){
				total -= item.priceAbroad();
			}
		}
		return total;
	}

	public void solve() {
		System.out.println();
		double[][] u = new double[suits.size()][temp.size()];
		for (int i = 0; i < suits.size(); ++i) {
			for (int j = 0; j < temp.size(); ++j) {
				u[i][j] = func(i, j);
			}
		}
		double [] E = new double[suits.size()];
		for(int i=0; i<suits.size(); ++i){
			for(int j=0; j<temp.size(); ++j){
				E[i] += u[i][j] * this.p;
			}
			System.out.println("E[" + (i+1) + "]=" + String.format( "%.2f", E[i]) + " у.о.");
		}
		int best = 0;
		for(int i= 1;i<E.length; ++i){
			if (E[i] > E[best]){
				best = i;
			}
		}
		String str = "";
		for(Item item : suits.get(best).list){
			str += item.getName() + " ";
		}
		System.out.println("Вибираємо №" + (best+1) + ": " + str);
	}

}
