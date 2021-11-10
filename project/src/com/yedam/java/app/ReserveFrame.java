package com.yedam.java.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.yedam.app.reserve.DAO;
import com.yedam.app.reserve.Reserve;
import com.yedam.app.reserve.ReserveDAO;
import com.yedam.app.reserve.ReserveDAOImpl;
import com.yedam.java.app.common.Frame;
import com.yedam.java.app.login.UserDAO;

public class ReserveFrame extends DAO implements Frame {
	// 필드
	Scanner scanner = new Scanner(System.in);
	UserDAO dao = new UserDAO();
	ReserveDAO rdao = ReserveDAOImpl.getInstance();
	String userId;

	int vipSeat = 5;
	int sSeat = 5;
	int aSeat = 5;
	int bSeat = 5;

	// 메소드
	// 실행 메소드
	public void run(String userId) {
		this.userId = userId;

		while (true) {
			menuPrint();
			int menuNo = menuSelect();

			if (menuNo == 1) {
				// 공연 예매
				concertReserve();
			} else if (menuNo == 2) {
				// 예약 조회
				selectOne();
			} else if (menuNo == 3) {
				// 전체 조회
				selectAll();
			} else if (menuNo == 4) {
				// 취소
				cancelReserve();
			} else if (menuNo == 5) {

			} else if (menuNo == 9) {
				end();
				break;
			} else {
				System.out.println("존재하지 않는 메뉴입니다. 다시 선택하세요.");
				break;
			}
		}
	}

	// 메뉴를 출력하는 메소드
	void menuPrint() {
		System.out.println("");
		System.out.println("==================================================================================");
		System.out.println("== 1. 공연 예약  2. 예약 조회  3. 예약 전체 조회  4. 예약 취소  5. 예약 대기  9. 프로그램 종료 ==");
		System.out.println("==================================================================================");
		System.out.println("선택>>");
	}

	// 메뉴를 선택하는 메소드
	int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("메뉴 선택에 실패하였습니다. 다시 시도하세요.");
		}
		return menuNo;
	}

	// 종료
	void end() {
		System.out.println("프로그램을 종료합니다.");
	}

	// 공연 예약
	void concertReserve() {
		try {
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡ<좌석 현황>ㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println(" VIP석 잔여 (" + vipSeat + ")석 ");
			System.out.println(" S석 잔여 (" + sSeat + ")석    ");
			System.out.println(" A석 잔여 (" + aSeat + ")석    ");
			System.out.println(" B석 잔여 (" + bSeat + ")석    ");
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			System.out.println();
			System.out.println("예매하고자 하는 좌석 타입에 맞는 번호를 입력하세요.");
			System.out.println("VIP석<1번>  S석<2번>  A석<3번>  B석<4번>");
			System.out.println();
			System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
			int inputSeat = scanner.nextInt();

			switch (inputSeat) {
			case 1:
				if (vipSeat < 1) {
					System.out.println("매진된 좌석입니다. 예매 대기 서비스를 이용하려면 5번을 선택하세요.");
				} else {
					Reserve resv = new Reserve();
					resv.setReserveType("VIP");
					resv.setUserId(userId);
					int result = rdao.insert(resv);
					System.out.println(resv + " 건의 예매가 성공적으로 완료되었습니다.");
					vipSeat--;
				}
				break;

			case 2:
				if (sSeat < 1) {
					System.out.println("매진된 좌석입니다. 예매 대기 서비스를 이용하려면 5번을 선택하세요.");
				} else {
					Reserve resv = new Reserve();
					resv.setReserveType("S");
					resv.setUserId(userId);
					int result = rdao.insert(resv);
					sSeat--;
					System.out.println(resv + " 건의 예매가 성공적으로 완료되었습니다.");
					System.out.println("S석 잔여 : " + sSeat);
				}
				break;

			case 3:
				if (aSeat < 1) {
					System.out.println("매진된 좌석입니다. 예매 대기 서비스를 이용하려면 5번을 선택하세요.");
				} else {
					Reserve resv = new Reserve();
					resv.setReserveType("A");
					resv.setUserId(userId);
					int result = rdao.insert(resv);
					System.out.println(resv + " 건의 예매가 성공적으로 완료되었습니다.");
					aSeat--;
				}
				break;

			case 4:
				if (bSeat < 1) {
					System.out.println("매진된 좌석입니다. 예매 대기 서비스를 이용하려면 5번을 선택하세요.");
				} else {
					Reserve resv = new Reserve();
					resv.setReserveType("B");
					resv.setUserId(userId);
					int result = rdao.insert(resv);
					System.out.println(resv + " 건의 예매가 성공적으로 완료되었습니다.");
					bSeat--;
				}
				break;
			}
		} catch (Exception e) {
			System.out.println("오류가 발생했습니다. 다시 시도해주세요.");
			System.out.println("사유 : " + e.getMessage());
		}
	}

	// 전체조회
	void selectAll() {
		if (userId.equals("admin")) {
			List<Reserve> list = rdao.selectAll();
			for (Reserve resv : list) {
				System.out.println(resv);
			}
		} else {
			System.out.println("관리자 전용 기능입니다. 관리자로 로그인하세요.");
		}
	}

	// 단건조회
	void selectOne() {
		Reserve resv = rdao.selectOne(userId);
		System.out.println(resv);
	}

	// 삭제
	void cancelReserve() {
		// reservelist의 type에 "W"가 있다면 대기자 이름으로 update, 그게 아니면 delete.
		System.out.println("정말로 예매를 취소하시겠습니까?");
		System.out.println("취소하려면 1번을 눌러주세요.");
		int selectedNo = scanner.nextInt();

		if (selectedNo == 1) {
			if (waitingIsOnTheList("예매대기중")) {
				int result = rdao.update(userId);
				int result1 = rdao.delete(userId);
			} else {
				int result = rdao.delete(userId);
				System.out.println(result + "건의 예매가 취소되었습니다.");
			}
		} else {
			System.out.println("예매 취소를 종료합니다.");
		}
	}

	// 예약 대기??
	void insert() {
		if (waitingIsOnTheList("예매대기중")) {
			System.out.println("죄송합니다. 이미 대기자가 존재합니다. 대기는 한 공연당 한 명만 가능합니다.");
			return;
		} else {
			Reserve resv = new Reserve();
			resv.setUserId(userId);
			resv.setReserveType("예매대기중");

			int result = rdao.insert(resv);
			System.out.println(resv + " 건의 예매 대기가 성공적으로 완료되었습니다.");
		}
	}

	// 대기자 있는지 여부 확인
	boolean waitingIsOnTheList(String type) {
		Reserve waiting = rdao.selectOne(type);
		if (waiting == null) {
			return false;
		} else {
			return true;
		}
	}
}
