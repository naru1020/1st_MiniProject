package com.yedam.app.reserve;

import java.util.List;

public interface ReserveDAO {
	// 예약 기능
	public int insert(Reserve reserve);

	// 예약 조회
	public Reserve selectOne(String userId);

	// 예약 전체 조회
	public List<Reserve> selectAll();

	// 예약 취소
	public int delete(String userId);

	// 예약 대기
	// 예약 기능 + type을 대기자(W)
	public int update(String userId);
}
