package task1;

public class Matrix {

	public int n;
	private int [][] a;
	
	public Matrix(int n){
		this.n = n;
		a = new int[n][n];
	}
	
	public int get(int i, int j){
		return a[i][j];
	}
	
	public void set(int i, int j, int x){
		a[i][j] = x;
	}
	
	public void show(){
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				System.out.print(a[i][j] + " ");				
			}
			System.out.println();
		}
	}
	
	public int minInRow(int row){
		int min = 0;
		for(int j=1; j<n; ++j){
			if (a[row][j] < a[row][min]){
				min = j;
			}
		}
		return a[row][min];
	}
	
	public int maxInRow(int row){
		int max = 0;
		for(int j=1; j<n; ++j){
			if (a[row][j] > a[row][max]){
				max = j;
			}
		}
		return a[row][max];
	}
	
	public int sumInRow(int row){
		int sum = 0;
		for(int j=0; j<n; ++j){
			sum += a[row][j];
		}
		return sum;
	}
	
	public int maxInColumn(int column){
		int max = 0;
		for(int i=0; i<n; ++i){
			if (a[i][column] > a[max][column]){
				max = i;
			}
		}
		return a[max][column];
	}
	
}
