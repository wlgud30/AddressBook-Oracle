package com.java.phonebook;

public class PhoneBookView {

	public void phoneBookViewStart() {
		System.out.println("********************************");
		System.out.println("*       전화번호 관리 프로그램       *");
		System.out.println("********************************");

	}

	public void phoneBookViesMenu() {
		System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
		System.out.println("--------------------------------");
		System.out.println(">메뉴 번호 : ");
	}

	public void phoneBookViewEnd() {
		System.out.println("********************************");
		System.out.println("*           감사합니다            *");
		System.out.println("********************************");
	}

	public void phoneBookViewEr() {
		System.out.println("[다시 입력해주세요.]");
	}

	public void phoneBookViewList() {
		System.out.println("<1. 리스트>");
	}

	public void phoneBookViewInsert() {
		System.out.println("<2. 등록>");
	}

	public void phoneBookViewDelete() {
		System.out.println("<3. 삭제>");
		System.out.print(">번호 : ");
	}

	public void phoneBookViewSearch() {
		System.out.println("<4. 검색>");
		System.out.print(">이름 : ");
	}
	

}
