import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BTree {

	public int data;
	public BTree left, right, parent;

	public BTree(BTree parent) {
		this.parent = parent;
	}

	public static BTree build(List<Integer> list) {
		BTree root = new BTree(null);
		BTree cur = root;
		for (Integer i : list) {
			cur.data = i;
			while (cur != null && (cur.data == 0 || cur.right != null)) {
				cur = cur.parent;
			}
			if (cur != null) {
				BTree next;
				if (cur.left == null) {
					next = cur.left = new BTree(cur);
				} else {
					next = cur.right = new BTree(cur);
				}
				cur = next;
			}
		}
		return root;
	}

	public void show() {
		System.out.print(data + " ");
		if (left != null)
			left.show();
		if (right != null)
			right.show();
	}
}

public class Task {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("input.txt"));
		List<Integer> list = new ArrayList<>();
		while (scanner.hasNext()) {
			list.add(scanner.nextInt());
		}
		BTree tree = BTree.build(list);
		tree.show();
	}

}
