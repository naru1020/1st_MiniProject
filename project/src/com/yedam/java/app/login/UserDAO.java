package com.yedam.java.app.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {
	// Oracle 연결
	private String jdbc_driver = "oracle.jdbc.driver.OracleDriver";
	private String jdbc_url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String connectedId = "hr";
	private String conntectedPwd = "hr";

	// 각 메소드에서 공통 사용 부분 필드로 선언
	protected Connection conn = null;
	protected Statement stmt = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;

	// DB 연결
	public void connect() {
		try {

			// jdbc driver 로딩하기
			Class.forName(jdbc_driver);

			// db 서버 접속
			conn = DriverManager.getConnection(jdbc_url, connectedId, conntectedPwd);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	// 회원가입
	public int join(User user) {
		String SQL = "INSERT INTO member VALUES(?,?,?,?,?)";
		try {
			connect();

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserPhone());
			pstmt.setString(5, user.getUserEmail());

			System.out.println("회원가입에 성공하였습니다.");
			return pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println("회원가입에 실패하였습니다. ");
			System.out.println("사유 :" + e.getMessage());
		}
		return -1;
	}

	// 로그인
	public int login(String userId, String userPassword) {
		String SQL = "SELECT password FROM member WHERE id = ?";
		try {

			connect();

			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).equals(userPassword)) {
					return 1;
				} else {
					return 0;
				}
			}
			return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}

}
