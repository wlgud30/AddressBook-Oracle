package com.java.phonebook;

import java.util.List;
import java.util.Scanner;

public interface PhoneBookDao {
	
	public List<PhoneBookVO> getList();
	//phone_book의 리스트를 가져옴
	
	public List<PhoneBookVO> search(String key);
	//phone_book 등록된 전화번호부에 key 라는 글자가 포함되어있는 이름의 유저들 정보 출력
	
	public boolean insert(PhoneBookVO vo);
	//phone_book 새로운 전화번호를 추가
	
	//public boolean update(PhoneBookVO vo);
	//phone_book 아이디 정보를 통해 해당 아이디의 정보를 업데이트
	
	public boolean delete(Long id);
	//phone_book 아이디정보를 통해 row 삭제
	
	public void listView(List<PhoneBookVO> list);
	//phone_book 리스트를 출력
	
	public PhoneBookVO addressInfo(Scanner sc);
	//phone_book의 insert 작업을위해 PhoneBookVO 의 객체를 만듦
}
