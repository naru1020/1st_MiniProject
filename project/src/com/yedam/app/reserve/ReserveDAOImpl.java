package com.yedam.app.reserve;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.java.app.login.User;

public class ReserveDAOImpl extends DAO implements ReserveDAO {

	// 사용할 sql문 정리
	private final String INSERT = "INSERT INTO reservelist VALUES(?,?)";
	private final String SELECT_ONE = "SELECT * FROM reservelist WHERE id = ?";
	private final String SELECT_ALL = "SELECT * FROM reservelist";
	private final String DELETE = "DELETE FROM reservelist WHERE id = ?";
	private final String WAITING = "SELECT id FROM reservelist WHERE type = '예매대기중'";
	private final String UPDATE = "UPDATE reservelist SET type = (SELECT type FROM reservelist WHERE id = ?) WHERE type = '예매대기중'";

	// 싱글톤
	private static ReserveDAO instance = new ReserveDAOImpl();

	public static ReserveDAO getInstance() {
		return instance;
	}

	// 예약 기능 - insert
	@Override
	public int insert(Reserve reserve) {

		int result = 0;

		try {
			connect();

			pstmt = conn.prepareStatement(INSERT);
			pstmt.setString(1, reserve.getReserveType());
			pstmt.setString(2, reserve.getUserId());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 예약 조회 기능(단건)
	@Override
	public Reserve selectOne(String userId) {
		Reserve reserve = null;
		try {
			connect();
			pstmt = conn.prepareStatement(SELECT_ONE);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				reserve = new Reserve();
				reserve.setUserId(rs.getString("userId"));
				reserve.setReserveType(rs.getString("reserveType"));
			}
		} catch (SQLException e) {
			System.out.println("조회에 실패했습니다.");
			e.getMessage();
		} finally {
			disconnect();
		}
		return reserve;
	}

	// 예약 조회 기능(전체)
	@Override
	public List<Reserve> selectAll() {
		List<Reserve> list = new ArrayList<>();
		try {
			connect();

			stmt = conn.createStatement();
			rs = stmt.executeQuery(SELECT_ALL);
			while (rs.next()) {
				Reserve reserve = new Reserve();

				reserve.setUserId(rs.getString("userId"));
				reserve.setReserveType(rs.getString("reserveType"));

				list.add(reserve);
			}
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			disconnect();
		}
		return list;
	}

	// 예약 취소 기능
	@Override
	public int delete(String userId) {
		int result = 0;
		try {
			connect();

			pstmt = conn.prepareStatement(DELETE);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}

	// 예약자 있는지 여부 확인

	@Override
	public Reserve waiting(String type) {
		Reserve waiting = null;
		try {
			connect();

			pstmt = conn.prepareStatement(WAITING);
			pstmt.setString(1, type);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				waiting = new Reserve();
				waiting.setUserId(rs.getString("userId"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return waiting;
	}

	// 예약대기자가 있는 경우 - 기존 예약자가 취소할 경우 예약 대기자로 update
	public int update(String userId) {
		int result = 0;
		try {
			connect();

			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.getMessage();
		} finally {
			disconnect();
		}
		return result;
	}

}
