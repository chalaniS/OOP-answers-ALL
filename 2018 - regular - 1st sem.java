listing 1
//q1

//Item.java
public abstract class Item {
	private int itemNo;
	private String description;
	private float unitPrice;
	
	public Item(int itemNo, String description, float unitPrice) {
		super();
		this.itemNo = itemNo;
		this.description = description;
		this.unitPrice = unitPrice;
	}
	
	public void display() {
		System.out.println("Item no : " +itemNo);
		System.out.println("Description : " +description);
		System.out.println("Unit price  : " +unitPrice);
	}
}

//Book.java
public class Book extends Item{
	private String publisher;
	private String category;
	private int pages;
	
	public Book(int itemNo, String description, float unitPrice, String publisher, String category, int pages) {
		super(itemNo, description, unitPrice);
		this.publisher = publisher;
		this.category = category;
		this.pages = pages;
	}
	
	public void display() {
		super.display();
		System.out.println("Publisher : " +publisher);
		System.out.println("Category : " +category);
		System.out.println("Pages : " +pages);
	}
}

//Car.java
public class Car extends Item{
	private String model;
	private String type;
	
	public Car(int itemNo, String description, float unitPrice, String model, String type) {
		super(itemNo, description, unitPrice);
		this.model = model;
		this.type = type;
	}
	
	public void display() {
		super.display();
		System.out.println("Model : " +model);
		System.out.println("Type : " +type);
	}
}

//MainApp.java
public class MainApp {

	public static void main(String[] args) {
		ArrayList<Item> al = new ArrayList<Item>();
		
		Book b1 = new Book(101, "A good book", 250, "Sadeepa", "Children", 250);
		Book b2 = new Book(102, "A science book", 400, "Nasa", "Scifi", 500);
		
		al.add(b1);
		al.add(b2);
		
		Car c1 = new Car(201, "A luxury car", 2300000, "Toyota", "SUV");
		Car c2 = new Car(202, "A offroad vehicle", 9990000, "Range rover", "SUV");
		
		al.add(c1);
		al.add(c2);
		
		for(Item i : al) {
			i.display();
			System.out.println();
		}
	}

}

listing 2
//q2 - a

//CountDown.java
public class CountDown extends Thread{
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			System.out.println(i + 1);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

//CalcSum.java
public class CalcSum implements Runnable{
	public void run() {
		int sum = 0;
		
		for(int i = 1; i <= 100000; i++) {
			sum = sum + i;
		}
		
		System.out.println("Answer : " +sum);
		System.out.println(Thread.currentThread().getName());
	}
}

//MainThreadApp.java
public class MainThreadApp {
	public static void main(String args[]) {
		CountDown cd = new CountDown();
		
		Thread t1 = new Thread(new CalcSum());
		Thread t2 = new Thread(new CalcSum());
		
		t1.setName("Black");
		t2.setName("White");
		
		cd.start();
		
		try {
			cd.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t1.start();
		t2.start();
		
	}
}
 
listing 3
//q2 - b

//Calculation.java
public class Calculation {
	private double ans = 1;

	public double getAns() {
		return ans;
	}

	public void Factorial(int start, int end) {
		for(int i = start; i <= end; i++) {
			ans = ans * i;
		}
	}
}

//ParallelThread.java
public class ParallelThread extends Thread{
	Calculation myCalc;
	int start;
	int end;
	
	public ParallelThread(Calculation myCalc, int start, int end) {
		super();
		this.myCalc = myCalc;
		this.start = start;
		this.end = end;
	}
	
	public void run() {
		myCalc.Factorial(start, end);
	}
	
}

//MainThreadApp.java
public class MainThreadApp {

	public static void main(String[] args) throws InterruptedException {
		Calculation c = new Calculation();
		
		ParallelThread p1 = new ParallelThread(c, 1, 10);
		ParallelThread p2 = new ParallelThread(c, 11, 20);
		ParallelThread p3 = new ParallelThread(c, 21, 30);
		ParallelThread p4 = new ParallelThread(c, 31, 40);
		
		p1.start();
		p1.join();
		
		p2.start();
		p2.join();
		
		p3.start();
		p3.join();
		
		p4.start();
		p4.join();
		
		System.out.println("" +c.getAns());
		
	}
}

listing 4
//q3

//MarksException.java
public class MarksException extends Exception{
	private float marks;

	public MarksException(float marks) {
		super();
		this.marks = marks;
	}

	public float getMarks() {
		return marks;
	}
}

//Student.java
public class Student {
	private int id;
	private String name;
	private int noOfSubjects;
	private float marks[];
	
	Scanner sc = new Scanner(System.in);
	
	public Student(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public float inputMarks(int index) throws MarksException {
		System.out.print("Enter mark " +index+ " :");
		float mark = sc.nextFloat();
		
		if(mark < 0 || mark > 100) {
			throw new MarksException(mark);
		}
		
		else {
			return mark;
		}
	}
	
	public void input(){
		
		try {
			System.out.print("No of subjects : ");
			
			this.noOfSubjects = sc.nextInt();
			this.marks = new float[noOfSubjects];
			
			for(int i = 0; i < noOfSubjects; i++) {
				marks[i] = inputMarks(i + 1);
			}
			
		}catch(InputMismatchException e1) {
			e1.printStackTrace();
			System.out.println("Input mismatch exception handled");
			
		}catch(MarksException e2) {
			e2.printStackTrace();
			System.out.println("Marks exception handled");
		}

	}
	
	public float getAverage() {
		float average = 0;
		float total = 0;
		
		try {
			for(int i = 0; i < noOfSubjects; i++) {
				total = total + marks[i];
			}
			
			average = total / noOfSubjects;
			
		}catch(ArithmeticException e) {
			e.printStackTrace();
			System.out.println("Arithmetic exception handled");
		}
		
		return average;
	}
}

//MainApp.java
public class MainApp {

	public static void main(String[] args){
		Student s = new Student(101, "Imal");
		
		s.input();
		System.out.println("Average : " +s.getAverage());
	}
}

listing 5
//q4 - a

//CreditCard.java
public class CreditCard {
	private static CreditCard uniqueInstance;
	
	private CreditCard() {
		
	}
	
	public static CreditCard getInstance() {
		if(uniqueInstance == null) {
			uniqueInstance = new CreditCard();
		}
		return uniqueInstance;
	}
	
	public boolean validate(String cardno, int code) {
		if(cardno.length() == 16 && code % 3 == 0) {
			return true;
		}
		else {
			return false;
		}
	}
}

//MainApp.java
public class MainApp {

	public static void main(String[] args) {
		CreditCard c1 = CreditCard.getInstance();
		CreditCard c2 = CreditCard.getInstance();

		System.out.println("" +c1.validate("1234567891234567", 6));
		System.out.println("" +c2.validate("1234567891234567", 7));
		
		System.out.println("" +c1.equals(c2));
	}

}

listing 6
//q4 - b

//Oven.java
public class Oven {
	private String name;

	public Oven(String name) {
		super();
		this.name = name;
	}
	
	public void on() {
		System.out.println(name +" oven is on");
	}
	
	public void off() {
		System.out.println(name +" oven is off");
	}
}

//GarageGate.java
public class GarageGate {
	private String discription;

	public GarageGate(String discription) {
		super();
		this.discription = discription;
	}
	
	public void open() {
		System.out.println(discription +" is open");
	}
	
	public void close() {
		System.out.println(discription +" is close");
	}
}

//Command.java
public interface Command {
	public void execute();
}

//OvenOn.java
public class OvenOn implements Command{

	Oven o;
	
	public OvenOn(Oven o) {
		super();
		this.o = o;
	}

	public void execute() {
		o.on();
	}
	
}

//OvenOff.java
public class OvenOff implements Command{
	
	Oven o;
	
	public OvenOff(Oven o) {
		super();
		this.o = o;
	}

	public void execute() {
		o.off();
	}

}

//GarageGateOpen.java
public class GarageGateOpen implements Command{
	GarageGate g;
	
	public GarageGateOpen(GarageGate g) {
		super();
		this.g = g;
	}

	public void execute() {
		g.open();
	}

}

//GarageGateClose.java
public class GarageGateClose implements Command{
	GarageGate g;
		
	public GarageGateClose(GarageGate g) {
		super();
		this.g = g;
	}

	public void execute() {
		g.close();
	}
}

//MobileUI.java
public class MobileUI {
	Command c[];

	public MobileUI() {
		this.c = new Command[6];
	}
	
	public void setCommand(int index, Command cmdObj) {
		c[index] = cmdObj;
	
	}
	
	public void commandPressed(int index) {
		c[index].execute();
	}
}

//MainApp.java
public class MainApp {

	public static void main(String[] args) {
		MobileUI m = new MobileUI();
		Oven mainOven = new Oven("mainOven");
		GarageGate garageGate = new GarageGate("garageGate");
		
		OvenOn onMainOven = new OvenOn(mainOven);
		OvenOff offMainOven = new OvenOff(mainOven);
		GarageGateOpen openGarageGate = new GarageGateOpen(garageGate);
		GarageGateClose closeGarageGate = new GarageGateClose(garageGate);
		
		m.setCommand(0, onMainOven);
		m.setCommand(1, offMainOven);
		m.setCommand(2, openGarageGate);
		m.setCommand(3, closeGarageGate);
		
		m.commandPressed(0);
		m.commandPressed(1);
		m.commandPressed(2);
		m.commandPressed(3);
	}

}