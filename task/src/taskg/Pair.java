package taskg;

public class Pair<T> {

	T one;
	T two;
	
	public boolean isUndefined() {
		return one == null && two == null;
	}
	
}
