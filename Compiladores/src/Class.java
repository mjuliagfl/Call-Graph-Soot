
public class Class {
	public static void main(String[] args) {
		A("hello");
		System.out.println("world");
		int sum = B(3,6);
		System.out.println(sum);
	}
	
	public static void A (String s){
		System.out.println(s);
		
		
	}
	public static int B (int a, int b){
		return a + b;
		
	}
}
