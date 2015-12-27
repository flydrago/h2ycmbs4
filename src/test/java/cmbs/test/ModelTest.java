package cmbs.test;


public class ModelTest {
	
	private int a = getA();
	
	private static int b = getB();
	
	static{
		
		System.out.println("执行static块!");
	}
	
	public ModelTest() {
		
		System.out.println("执行构造函数！");
	}
	
	private int getA(){
		
		System.out.println("执行getA()方法！");
		return 10;
	}
	
	private static int getB(){
		
		System.out.println("执行getB()方法！");
		return 20;
	}
	
	
	private void doSomeThing(){
		
		
		try {
			
			System.out.println(" try start!");
			
			if (getA()>1 || getB()>1) {
				
				System.out.println("||判断通过！");
			}
			
			if (getA()>1 && getB()>1) {
				
				System.out.println("&&执行通过");
			}
			
			System.out.println(" try end!");
			return;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("异常捕获！");
		}finally{
			
			System.out.println("执行finally！");
		}
	}
	
	public static void main(String[] args) {
		
		ModelTest modelTest = new ModelTest();
		//modelTest.doSomeThing();
	}
}
