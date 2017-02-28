package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Task2 {

	static boolean AbetterThenB(int a, int b, String method) {
		int a1 = a / 10;
		int a2 = a % 10;

		int b1 = b / 10;
		int b2 = b % 10;

		if (method.equals("Парето")) {
			return a1 >= b1 && a2 >= b2;
		} else if (method.equals("Слейтера")) {
			return a1 > b1 && a2 > b2;
		} else {
			throw new RuntimeException("method ?? ");
		}
	}

	static int[] a = new int[60];
	static int[] b = new int[60];

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(new File("task2.txt"));

		for (int i = 0; i < 60; ++i) {
			a[i] = scanner.nextInt();
			// System.out.println(a[i]);
		}

		solve("Парето");
		solve("Слейтера");

	}

	private static void solve(String method) {

		System.out.println("Метод " + method);

		int i = 0;
		int j;
		int n = 60;
		for (int k = 0; k < n; ++k) {
			b[k] = 1;
		}

		while (i < n - 1) {
			j = i;
			while (true) {
				++j;
				while (j < n && AbetterThenB(a[i], a[j], method)) {
					b[j] = 0;
					++j;
				}
				if (j >=n){
					break;
				}
				if (AbetterThenB(a[j], a[i], method)) {
					b[i] = 0;
					break;
				}
			}
			++i;
		}

		for (int k = 0; k < n; ++k) {
			if (b[k] == 1) {
				System.out.println(a[k]);
			}
		}

	}

}
