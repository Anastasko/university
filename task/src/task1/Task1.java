package task1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Task1 {

	public static void Критерій_Вальда(Matrix A) {
		System.out.println("\n" + new Object() {
		}.getClass().getEnclosingMethod().getName() + ":");

		List<Integer> minInRows = new ArrayList<>();
		for (int row = 0; row < A.n; ++row) {
			minInRows.add(A.minInRow(row));
		}
		int best = 0;
		for (int row = 1; row < A.n; ++row) {
			if (minInRows.get(row) > minInRows.get(best)) {
				best = row;
			}
		}
		System.out.println("Вибрати альтернативу №" + (best + 1) + " з гарантованим виграшем " + minInRows.get(best));
	}

	private static void Максимаксний_критерій(Matrix A) {
		System.out.println("\n" + new Object() {
		}.getClass().getEnclosingMethod().getName() + ":");

		List<Integer> minInRows = new ArrayList<>();
		for (int row = 0; row < A.n; ++row) {
			minInRows.add(A.maxInRow(row));
		}
		int best = 0;
		for (int row = 1; row < A.n; ++row) {
			if (minInRows.get(row) > minInRows.get(best)) {
				best = row;
			}
		}
		System.out.println("Вибрати альтернативу №" + (best + 1) + " з ризикованим виграшем " + minInRows.get(best));
	}

	private static void Критерій_Гурвіца(Matrix A, double a) {
		System.out.println("\n" + new Object() {
		}.getClass().getEnclosingMethod().getName() + " (a=" + a + "):");

		List<Double> inRows = new ArrayList<>();
		for (int row = 0; row < A.n; ++row) {
			double value = (1 - a) * A.minInRow(row) + a * A.maxInRow(row);
			inRows.add(value);
		}
		int best = 0;
		for (int row = 1; row < A.n; ++row) {
			if (inRows.get(row) > inRows.get(best)) {
				best = row;
			}
		}
		System.out.println("Вибрати альтернативу №" + (best + 1) + " з очікуваним виграшем " + inRows.get(best));
	}

	private static void Критерій_Лапласа(Matrix A) {
		System.out.println("\n" + new Object() {
		}.getClass().getEnclosingMethod().getName() + ":");

		List<Double> averageInRows = new ArrayList<>();
		for (int row = 0; row < A.n; ++row) {
			double value = A.sumInRow(row) / A.n;
			averageInRows.add(value);
		}
		int best = 0;
		for (int row = 1; row < A.n; ++row) {
			if (averageInRows.get(row) > averageInRows.get(best)) {
				best = row;
			}
		}
		System.out.println("Вибрати альтернативу №" + (best + 1) + " з середнім виграшем " + averageInRows.get(best));
	}

	private static void Критерій_Байєса_Лапласа(Matrix A) {
		System.out.println("\n" + new Object() {
		}.getClass().getEnclosingMethod().getName() + ":");
		List<Double> funcInRows = new ArrayList<>();
		double [] p = new double[A.n];
		Random rand = new Random();
		for (int column = 0; column < A.n; ++column) {
			p[rand.nextInt(A.n)]+=1.0/A.n;
		}
		for (int column = 0; column < A.n; ++column) {
			System.out.print(String.format( "%.1f", p[column]) + " ");
		}
		System.out.println();
		for (int row = 0; row < A.n; ++row) {
			double sum = 0;
			for(int j=0; j<A.n; ++j){
				sum += A.get(row, j)*p[j];
			}
			funcInRows.add(sum);
		}
		int best = 0;
		for (int row = 1; row < A.n; ++row) {
			if (funcInRows.get(row) > funcInRows.get(best)) {
				best = row;
			}
		}
		System.out.println("Вибрати альтернативу №" + (best + 1) + " з середнім виграшем " + funcInRows.get(best));
	}
	

	private static void Критерій_Ходжеса_Лемана(Matrix A, double b) {
		System.out.println("\n" + new Object() {
		}.getClass().getEnclosingMethod().getName() + ":");
		List<Double> funcInRows = new ArrayList<>();
		double [] p = new double[A.n];
		Random rand = new Random();
		for (int column = 0; column < A.n; ++column) {
			p[rand.nextInt(A.n)]+=1.0/A.n;
		}
		for (int column = 0; column < A.n; ++column) {
			System.out.print(String.format( "%.1f", p[column]) + " ");
		}
		System.out.println();
		for (int row = 0; row < A.n; ++row) {
			double sum = 0;
			for(int j=0; j<A.n; ++j){
				sum += A.get(row, j)*p[j];
			}
			funcInRows.add((1-b) * A.minInRow(row) + b * sum);
		}
		int best = 0;
		for (int row = 1; row < A.n; ++row) {
			if (funcInRows.get(row) > funcInRows.get(best)) {
				best = row;
			}
		}
		System.out.println("Вибрати альтернативу №" + (best + 1) + " з виграшем " + funcInRows.get(best));
	}
	

	private static void Критерій_Севіджа(Matrix A) {
		System.out.println("\n" + new Object() {
		}.getClass().getEnclosingMethod().getName() + ":");
		Matrix R = new Matrix(A.n);
		for(int i=0; i<A.n; ++i)
			for(int j=0; j<A.n; ++j)
				R.set(i, j, A.maxInColumn(j) - A.get(i, j));
		System.out.println("R:");
		R.show();
		List<Integer> rInRows = new ArrayList<>();
		for (int row = 0; row < R.n; ++row) {
			rInRows.add(R.maxInRow(row));
		}
		int best = 0;
		for (int row = 1; row < R.n; ++row) {
			if (rInRows.get(row) < rInRows.get(best)) {
				best = row;
			}
		}
		System.out.println("Вибрати альтернативу №" + (best + 1) + " зі жалем не більшим за " + rInRows.get(best));
	}



	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("task1.txt"));

		int n = scanner.nextInt();
		Matrix A = new Matrix(n);

		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				int x = scanner.nextInt();
				A.set(i, j, x);
			}
		}

		System.out.println("A:");
		A.show();
		
		Критерій_Вальда(A);
		Максимаксний_критерій(A);
		Критерій_Гурвіца(A, 0.7); // оптиміст
		Критерій_Гурвіца(A, 0.2); // песиміст
		Критерій_Лапласа(A);
		Критерій_Байєса_Лапласа(A);
		Критерій_Ходжеса_Лемана(A, 0.5);
		Критерій_Севіджа(A);
		
		
	}

}
