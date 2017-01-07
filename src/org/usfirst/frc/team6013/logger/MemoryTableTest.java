package org.usfirst.frc.team6013.logger;

public class MemoryTableTest {

	public static void main(String[] args) {
		MemoryTable table = new MemoryTable();
		
		System.out.println("Boolean Tests");
		table.putBoolean("TestBool", true);
		System.out.println("True: " + table.getBoolean("TestBool"));
		System.out.println("True: " + table.getBoolean("TestBool", false));
		
		System.out.println("\nInteger Tests");
		table.putInt("TestInt", 3);
		System.out.println("3: " + table.getInt("TestInt"));
		System.out.println("3: " + table.getInt("TestInt", 0));
		
		System.out.println("\nBoolean Array Primitive Tests");
		boolean[] boolPrimativeList = new boolean[] { true, false, true};
		table.putBooleanArray("boolPrimativeList", boolPrimativeList);
		System.out.println("true, false, true,: " + listToCsv(table.getBooleanArray("boolPrimativeList")));
		System.out.println("true, false, true,: " + listToCsv(table.getBooleanArray("boolPrimativeList",new boolean[] {false, false, false})));
		
		System.out.println("\nBoolean Array Class Test");
		Boolean[] boolClassList = new Boolean[] { false, true, false};
		table.putBooleanArray("boolClassList", boolClassList);
		System.out.println("false, true, false,: " + listToCsv(table.getBooleanArray("boolClassList",new Boolean[] {false, false, false})));

		System.out.println("\nDouble Tests");
		table.putDouble("TestDouble", 3.0);
		System.out.println("3.0: " + table.getDouble("TestDouble"));
		System.out.println("3.0: " + table.getDouble("TestDouble", 0.0));
		
		System.out.println("\nString Tests");
		table.putString("TestString", "Hello");
		System.out.println("Hello: " + table.getString("TestString"));
		System.out.println("Hello: " + table.getString("TestString", "Bye"));
		
		System.out.println("\nString Array Tests");
		String[] strList = new String[]{"Hello", "World"};
		table.putStringArray("TestStringArray", strList);
		System.out.println("Hello, World,: " + listToCsv(table.getStringArray("TestStringArray")));
		System.out.println("Hello, World,: " + listToCsv(table.getStringArray("TestStringArray", new String[]{"Bye", "Bye"})));
		
		System.out.println("\nNumber Array Primitive Tests");
		double[] numberPrimativeList = new double[] { 1.0, 2.0, 3.0};
		table.putNumberArray("NumberPrimativeList", numberPrimativeList);
		System.out.println("1.0, 2.0, 3.0,: " + listToCsv(table.getNumberArray("NumberPrimativeList")));
		System.out.println("1.0, 2.0, 3.0,: " + listToCsv(table.getNumberArray("NumberPrimativeList",new double[] { 0.0, 0.0, 0.0})));
		
		System.out.println("\nNumber Array Class Test");
		Double[] numberClassList = new Double[] { 1.0, 2.0, 3.0};
		table.putNumberArray("NumberClassList", numberClassList);
		System.out.println("1.0, 2.0, 3.0,: " + listToCsv(table.getNumberArray("NumberClassList",new Double[] { 0.0, 0.0, 0.0})));
		
		System.out.println("\nMissing Key Tests");
		System.out.println("True: " + table.getBoolean("UnknownBool",true));
		System.out.println("3: " + table.getInt("UnknownInt", 3));
		System.out.println("true, false, true,: " + listToCsv(table.getBooleanArray("UnknownBoolPrimativeList",new boolean[] {true, false, true})));
		System.out.println("false, true, false,: " + listToCsv(table.getBooleanArray("UnknownBoolClassList",new Boolean[] {false, true, false})));
		System.out.println("3.0: " + table.getDouble("UnknownDouble", 3.0));
		System.out.println("Hello: " + table.getString("UnknownString", "Hello"));
		System.out.println("Hello, World,: " + listToCsv(table.getStringArray("UnknownStringArray", new String[]{"Hello", "World"})));
		System.out.println("1.0, 2.0, 3.0,: " + listToCsv(table.getNumberArray("UnknownNumberPrimativeList",new double[] { 1.0, 2.0, 3.0})));
		System.out.println("1.0, 2.0, 3.0,: " + listToCsv(table.getNumberArray("UnknownNumberClassList",new double[] { 1.0, 2.0, 3.0})));
	}
	
	public static String listToCsv(boolean[] list) {
		StringBuilder sb = new StringBuilder();
		
		for(Object item : list) {
			sb.append(item);
			sb.append(", ");
		}
		
		return sb.toString();
	}
	
	public static String listToCsv(double[] list) {
		StringBuilder sb = new StringBuilder();
		
		for(Object item : list) {
			sb.append(item);
			sb.append(", ");
		}
		
		return sb.toString();
	}
	
	public static <T> String listToCsv(T[] list) {
		StringBuilder sb = new StringBuilder();
		
		for(Object item : list) {
			sb.append(item);
			sb.append(", ");
		}
		
		return sb.toString();
	}
}
