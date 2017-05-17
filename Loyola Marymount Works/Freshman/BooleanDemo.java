public class BooleanDemo {

	public static void main(String[] args) {
		
		//System.out.println(true || true && false);
		//System.out.println(true || true && false);

		boolean a = false;
		boolean b = true;
		boolean c = true;

		System.out.println(b || c && a);
		System.out.println((b || c) && a);
	
	}

}