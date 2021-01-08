package com.nat.ValueObjects;

import java.util.Date;

public class PayrollClientMain {

	public static void main(String[] args) {

		 PayrollClient p = new PayrollClient(); //works
		 System.out.println(p.getUserById(23));

//		PayrollClient p2 = new PayrollClient();
//		for(User u : p2.getAllUsers()) {
//			System.out.println(u);
//		}

		// PayrollClient p3 = new PayrollClient();//works
		// p3.deleteUserById(11);

		/*
		 * PayrollClient p4 = new PayrollClient(); p4.createUser(new
		 * User("P.O.BOX MC 3292,Niger", "Accra", "test3@gmail.com", "0001TEST3",
		 * "Senior Associate", true, "Test3", "test3", "756799355644", "Male", new
		 * Date(), new Date(), "Single", "194646554346568", "TEST3T34", "DRV3657546544",
		 * "PASSPORT47655", "SSNIT6876746", "VRTS657587576"));
		 */
	}

}
