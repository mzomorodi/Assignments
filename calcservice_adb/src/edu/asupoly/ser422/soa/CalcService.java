package edu.asupoly.ser422.soa;

import java.util.concurrent.atomic.AtomicInteger;

public final class CalcService {
    private static final AtomicInteger __theValue = new AtomicInteger(0);
    private static CalcService __theService = new CalcService();
    
    public static CalcService getService() {
	return __theService;
    }
	
    private CalcService() {}
    
    public static final int add(int val) {
	System.out.println("In CalcService ADD " + val);
	return __theValue.addAndGet(val);
    }
    public static final int subtract(int val) {
	System.out.println("In CalcService SUBTRACT " + val);
	return add(-1*val);
    }
    public static final int set(int val) {
	System.out.println("In CalcService SET " + val);
	while(true){
	    int current = __theValue.get();
	    if (__theValue.compareAndSet(current, val)) {
		return val;
	    }
	}
    }
    
    public static final int get() {
	return __theValue.get();
    }
}
