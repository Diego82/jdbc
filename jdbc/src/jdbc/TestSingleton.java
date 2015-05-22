package jdbc;

public class TestSingleton {
	public static void main(String[] args) {
		
		Singleton s1 = Singleton.getOBJETO_UNICO();
		Singleton s2 = Singleton.getOBJETO_UNICO();
		System.out.println(s1.equals(s2));
		
	}
}
class Singleton {
	private static Singleton OBJETO = new Singleton();
	//CONSTRUCTOR PRIVADO
	private Singleton(){};
	/**
	 * 
	 * @return the OBJETO
	 */
	public static Singleton getOBJETO_UNICO(){
		return OBJETO;
	}
}