package task5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Task5 {

	private static Solver solver = new Solver();
	private static Scanner scanner;

	private static void readItems() {
		int n = scanner.nextInt();
		System.out.println("НАЗВА             Вага\t\tЦіна");
		System.out.println("-------------------------------------");
		for (int i = 0; i < n; ++i) {
			String name = scanner.next();
			Double weight = scanner.nextDouble();
			Integer price = scanner.nextInt();
			Item item = new Item(name, weight, price);
			System.out.println(StringUtils.format(item.getName()) + item.getWeight() + "\t\t" + item.getPrice());
			solver.getItems().add(item);
		}
	}
	
	private static void readSuits() {
		System.out.println("\n" + StringUtils.format("T,◦") + StringUtils.format("Головний убір")
				+ StringUtils.format("Верхній одяг") + StringUtils.format("Рукавиці") + StringUtils.format("Штани")
				+ StringUtils.format("Взуття") + StringUtils.format("Вага,кг"));
		System.out.println(StringUtils.createString('-', 18 * 6 + 8));
		scanner.nextLine();
		int m = Integer.valueOf(scanner.nextLine());
		for (int j = 0; j < m; ++j) {
			String t = scanner.next();
			System.out.print(StringUtils.format(t));
			List<String> names = new ArrayList<>();
			for (int k = 0; k < 5; ++k) {
				String name = scanner.next();
				System.out.print(StringUtils.format(name));
				if (name.equals("–")) {
					continue;
				}
				names.add(name);
			}
			Double weight = scanner.nextDouble();
			scanner.nextLine();
			System.out.println(weight);
			Integer min = Integer.valueOf(t.substring(0, t.indexOf("..")));
			Integer max = Integer.valueOf(t.substring(t.indexOf("..") + 2));
			Suit suit = new Suit(min, max, names.stream().map(name -> {
				return solver.findItemByName(name);
			}).collect(Collectors.toList()), weight);
			solver.getSuits().add(suit);
			if (Math.abs(suit.calcWeight() - weight) > 0.0001) {
				System.err.println("bad weight");
			}
		}
	}
	
	private static int readTemperature() {
		int n = scanner.nextInt();
		System.out.println("");
		for(int i=0; i<n; ++i){
			int temp = scanner.nextInt();
			System.out.print(temp + "  ");
			solver.getTemp().add(temp);
		}
		return n;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		scanner = new Scanner(new File("task5.txt"));

		readItems();
		readSuits();
		int n = readTemperature();
		
		solver.p = 1.0/n;

		solver.solve();

	}



}
