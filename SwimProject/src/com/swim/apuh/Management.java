package com.swim.apuh;

import com.swim.apuh.members.MemberDAO;
import com.swim.apuh.programs.ProgramDAO;
//서브프로그램
public class Management {

	protected MemberDAO mDAO = MemberDAO.getInstance();
	protected ProgramDAO pDAO = ProgramDAO.getInstance();
	
	public void run() {
		
		while (true) {
			menuPrint();
			
			int menuNo = menuSelect();
			
			if(menuNo == 1) {
				new 
			}
			
		}
	}
	
}

