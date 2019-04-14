package edu.asupoly.ser422.lab4.client;

import java.util.*;
import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub;
import edu.asupoly.ser422.lab4.adb.Lab4ServiceStub.*;

public class TestClient {
	private TestClient() {}
	
	public static void main(String[] args) {
		
		try {
			Lab4ServiceStub lab4Stub = new Lab4ServiceStub();
			GetSubjectsResponse gsResponse = lab4Stub.getSubjects(
				new Lab4ServiceStub.GetSubjects());
			String[] subjects = gsResponse.get_return();
			System.out.println(Arrays.toString(subjects));
		} catch (Throwable t) {
			System.out.println(t);
		}
	}
}