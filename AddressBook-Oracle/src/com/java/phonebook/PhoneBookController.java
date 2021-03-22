package com.java.phonebook;

import java.util.Scanner;

public class PhoneBookController {

	protected void phoneBook() {
		
		PhoneBookDao dao= new PhoneBookDaoImpl();
		PhoneBookView pbView = new PhoneBookView();
				
		pbView.phoneBookViewStart();
		Scanner sc = new Scanner(System.in);
		while (true) {
			pbView.phoneBookViesMenu();

			switch (sc.nextInt()) {
			case 1:
				dao.listView(dao.getList());
				continue;
			case 2:
				pbView.phoneBookViewInsert();
				if(dao.insert(dao.addressInfo(sc))) {
					System.out.println("[등록되었습니다.]\n");
				}else {
					pbView.phoneBookViewEr();
				}
				continue;
			case 3:
				pbView.phoneBookViewDelete();
				if(dao.delete(sc.nextLong())) {
					System.out.println("[삭제되었습니다.]\n");
				}else {
					pbView.phoneBookViewEr();
				}
				continue;
			case 4:
				pbView.phoneBookViewSearch();
				dao.listView(dao.search(sc.next()));
				continue;
			case 5:
				pbView.phoneBookViewEnd();
				break;
			default:
				pbView.phoneBookViewEr();
				continue;
			}
			sc.close();
			break;
		}

	}

	/*
	 * 테스트용 강지형,010-2209-8728,02-2123-0910 일지형,010-2209-8728,02-2123-0910
	 * 이지형,010-2209-8728,02-2123-0910 삼지형,010-2209-8728,02-2123-0910
	 */

}
