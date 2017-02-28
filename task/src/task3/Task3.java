package task3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Task3 {

	enum AlgoKind {
		NFA, // (Next Fit Algorithm) – алгоритм «заповнення наступного»
		FFA, // (First Fit Algorithm) – алгоритм «заповнення першого, що підходить»;
		WFA, // (Worst Fit Algorithm) – алгоритм заповнення «найменш повного»;
		BFA // (Best Fit Algorithm) – алгоритм заповнення «найкращого».
	}

	private static Output process(final List<Cargo> inputCargoes, AlgoKind algo, boolean withSort) {

		Output output = new Output();
		Weight.comparisons = 0;

		List<Cargo> cargoes = new ArrayList<>();
		for (Cargo cargo : inputCargoes) {
			cargoes.add(cargo.clone());
		}
		if (withSort) {
			Collections.sort(cargoes);
		}
		Container container = null;
		List<Container> containers = new ArrayList<>();
		switch (algo) {
		case NFA:
			for (Cargo cargo : cargoes) {
				if (container == null || !cargo.canFitInto(container)) {
					container = Container.emptyContainer();
					containers.add(container);
				}
				cargo.putInto(container);
			}
			break;
		case FFA:
			for (Cargo cargo : cargoes) {
				if (container == null) {
					container = Container.emptyContainer();
					containers.add(container);
				}
				if (cargo.canFitInto(container)) {
					cargo.putInto(container);
					continue;
				}
				if (!withSort) {
					for (int i = 0; i < containers.size() - 1; ++i) {
						if (cargo.canFitInto(containers.get(i))) {
							cargo.putInto(containers.get(i));
							break;
						}
					}
				}
				if (!cargo.isPut()) {
					container = Container.emptyContainer();
					containers.add(container);
					cargo.putInto(container);
				}
			}
			break;
		case WFA:
			for (Cargo cargo : cargoes) {
				if (container == null) {
					container = Container.emptyContainer();
					containers.add(container);
				}
				if (cargo.canFitInto(container)) {
					cargo.putInto(container);
					continue;
				}
				Container mostFreeContainer = null;
				if (!withSort) {
					for (int i = 0; i < containers.size() - 1; ++i) {
						if (mostFreeContainer == null || containers.get(i).getCurrentWeight()
								.lessThanOrEqual(mostFreeContainer.getCurrentWeight())) {
							mostFreeContainer = containers.get(i);
						}
					}
				}
				if (mostFreeContainer != null && cargo.canFitInto(mostFreeContainer)) {
					cargo.putInto(mostFreeContainer);
				}
				if (!cargo.isPut()) {
					container = Container.emptyContainer();
					containers.add(container);
					cargo.putInto(container);
				}
			}
			break;
		case BFA:
			for (Cargo cargo : cargoes) {
				if (container == null) {
					container = Container.emptyContainer();
					containers.add(container);
				}
				if (cargo.canFitInto(container)) {
					cargo.putInto(container);
					continue;
				}
				Container mostFreeContainer = null;
				if (!withSort) {
					for (int i = 0; i < containers.size() - 1; ++i) {
						if (!cargo.canFitInto(containers.get(i))) {
							continue;
						}
						if (mostFreeContainer == null || containers.get(i).getCurrentWeight()
								.lessThanOrEqual(mostFreeContainer.getCurrentWeight())) {
							mostFreeContainer = containers.get(i);
						}
					}
				}
				if (mostFreeContainer != null) {
					cargo.putInto(mostFreeContainer);
				}
				if (!cargo.isPut()) {
					container = Container.emptyContainer();
					containers.add(container);
					cargo.putInto(container);
				}
			}
			break;
		}

		output.setResult(containers.size());
		output.setComparisons(Weight.comparisons);
		return output;
	}

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("task3.txt"));
		int containerMax = scanner.nextInt();
		System.out.println("Розмір контейнеру: " + containerMax);
		Container.MAX_WEIGHT = new Weight(containerMax);

		int N = scanner.nextInt();
		System.out.println("Кількість предметів: " + N);

		List<Cargo> cargoes = new ArrayList<>();
		for (int i = 0; i < N; ++i) {
			int number = scanner.nextInt();
			Weight weight = new Weight(number);
			cargoes.add(new Cargo(weight));
		}
		scanner.close();

		boolean[] sort = { false, true };

		System.out.println();
		System.out.print("\t      ");
		for (AlgoKind algo : AlgoKind.values()) {
			System.out.print("      " + algo);
		}
		System.out.println();		
		for (Boolean withSort : sort) {
			System.out.print("Сортування -" + (withSort ? " так:  " : "  ні:   "));
			for (AlgoKind algo : AlgoKind.values()) {
				Output output = process(cargoes, algo, withSort);
				System.out.print(output.getResult() + "(" + output.getComparisons() + ")  ");

			}
			System.out.println();
		}
		
		int sum = 0;
		for (Cargo cargo : cargoes) {
			sum += cargo.getWeight().getValue();
		}
		System.out.println();
		System.out.println("Нижня межа: " + upperDiv(sum, Container.MAX_WEIGHT.getValue()));

	}

	private static int upperDiv(int a, int b) {
		return a % b == 0 ? a / b : a / b + 1;
	}

}
