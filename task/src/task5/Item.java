package task5;

public class Item {
	
	private String name;
	
	private Double weight;
	
	private Integer price;

	public Item(String name, Double weight, Integer price) {
		super();
		this.name = name;
		this.weight = weight;
		this.price = price;
	}

	public Double transportPrice(){
		return 10*weight;
	}
	
	public Integer priceAbroad(){
		return price + 2;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

}
