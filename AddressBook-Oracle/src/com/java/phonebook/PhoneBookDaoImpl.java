package com.java.phonebook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class PhoneBookDaoImpl implements PhoneBookDao {
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##KJH", "1234");
		} catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패!");
		} 
		
		return conn;
	}

	@Override
	public List<PhoneBookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<PhoneBookVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT id,name,hp,tel FROM phone_book ORDER BY id";
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				PhoneBookVO vo = new PhoneBookVO(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public List<PhoneBookVO> search(String key) {
		List<PhoneBookVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();
			
			String sql = "SELECT id,name,hp,tel FROM phone_book WHERE name LIKE ? ORDER BY id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+key+"%");
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				PhoneBookVO vo = new PhoneBookVO(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getString(4));
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL,?,?,?)";
		int insertedCount = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			insertedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		return insertedCount==1;
	}
/*
	@Override
	public boolean update(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "UPDATE phone_book SET name =? , hp = ? , tel = ? WHERE id = ?";
		int updatedCount = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			pstmt.setLong(4, vo.getId());
			
			updatedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		return updatedCount==1;
	}
*/
	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "DELETE FROM phone_book WHERE id = ?";
		int deletedCount = 0;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e ) {
				e.printStackTrace();
			}
		}
		return deletedCount==1;
	}

	@Override
	public void listView(List<PhoneBookVO> list) {
		Iterator<PhoneBookVO> it = list.iterator();

		while (it.hasNext()) {
			PhoneBookVO vo = it.next();
			System.out.println(vo);
		}
		System.out.println();
		
	}

	@Override
	public PhoneBookVO addressInfo(Scanner sc) {
		System.out.print(">이름 : ");
		String name = sc.next();
		System.out.print(">휴대전화 : ");
		String hp = sc.next();
		System.out.print(">집전화 : ");
		String tel = sc.next();
		PhoneBookVO vo = new PhoneBookVO(name, hp, tel);
		return vo;
	}

}
