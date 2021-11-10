package com.yedam.app.reserve;

import java.sql.Date;

public class Reserve {

	private String userId;
	private String reserveType;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReserveType() {
		return reserveType;
	}

	public void setReserveType(String reserveType) {
		this.reserveType = reserveType;
	}
	
	@Override
	public String toString() {
		return "회원 아이디 : " + userId + " , 좌석 타입 : " + reserveType; 
	}

}
