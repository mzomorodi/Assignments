package edu.asupoly.ser422.client;

import edu.asupoly.ser422.ServiceStub;
import edu.asupoly.ser422.ServiceStub.*;

public class CalcClient {
	private CalcClient() {}
	
	public static void main(java.lang.String[] args) {
		if (args.length != 8) {
		 System.out.println("Must have 8 parameters");
		 System.exit(0);
		}
	 
		try {
			ServiceStub calcStub = new ServiceStub();
			
			Add stubAdd = new ServiceStub.Add();
			stubAdd.setValue1(Double.parseDouble(args[0]));
			stubAdd.setValue2(Double.parseDouble(args[1]));
			
			Subtract stubSub = new ServiceStub.Subtract();
			stubSub.setValue1(Double.parseDouble(args[2]));
			stubSub.setValue2(Double.parseDouble(args[3]));
			
			Multiple stubMul = new ServiceStub.Multiple();
			stubMul.setValue1(Double.parseDouble(args[4]));
			stubMul.setValue2(Double.parseDouble(args[5]));
			
			Divide stubDiv = new ServiceStub.Divide();
			stubDiv.setValue1(Double.parseDouble(args[6]));
			stubDiv.setValue2(Double.parseDouble(args[7]));
			
			AddResponse addResponse = calcStub.add(stubAdd);
			SubtractResponse subResponse = calcStub.subtract(stubSub);
			MultipleResponse mulResponse = calcStub.multiple(stubMul);
			DivideResponse divResponse = calcStub.divide(stubDiv);
			
			System.out.println("Addition response: " + addResponse.getAddResult());
			System.out.println("Subtraction response: " + subResponse.getSubtractResult());
			System.out.println("Multiply response: " + mulResponse.getMultipleResult());
			System.out.println("Divide response: " + divResponse.getDivideResult());
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}