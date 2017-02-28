package task5;

public class StringUtils {
	
	static String format(String x) {
		while (x.length() < 18) {
			x += ' ';
		}
		return x;
	}
	
	static String createString(char c, int n){
		String s = "";
		for(int i=0; i<n; ++i){
			s+=c;
		}
		return s;
	}
	
}
