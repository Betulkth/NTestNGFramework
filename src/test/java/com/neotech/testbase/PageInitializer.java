package com.neotech.testbase;

import com.neotech.pages.AddEmpModalPopUpFields;
import com.neotech.pages.DashboardPageElements;
import com.neotech.pages.LoginPageElements;
import com.neotech.pages.PersonalDetailsPageElements;

public class PageInitializer extends BaseClass {

	public static LoginPageElements login;
	public static DashboardPageElements dash;
	public static AddEmpModalPopUpFields fields;
	public static PersonalDetailsPageElements personalD;

	public static void initialize() {

		login = new LoginPageElements();
		dash = new DashboardPageElements();
		fields = new AddEmpModalPopUpFields();
		personalD = new PersonalDetailsPageElements();

	}

}
