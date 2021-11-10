package com.yedam.java.app;

import java.util.Scanner;

import com.yedam.java.app.common.Frame;
import com.yedam.java.app.login.User;
import com.yedam.java.app.login.UserDAO;

public class AppMain {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		UserDAO dao = new UserDAO();
		User user = null;
		String userId = null;
		Frame frame = null;

		System.out.println("대구콘서트하우스 예매 페이지입니다.");

		System.out.println("====================================");
		System.out.println("=== 1. 회원가입  2. 로그인   9. 종료 ===");
		System.out.println("====================================");
		System.out.println("선택>>");
		int selected = scanner.nextInt();

		if (selected == 1) {
			// 회원가입
			user = new User();

			System.out.println("아이디>>");
			user.setUserId(scanner.next());

			System.out.println("패스워드>>");
			user.setUserPassword(scanner.next());

			System.out.println("이름>>");
			user.setUserName(scanner.next());

			System.out.println("연락처>>");
			user.setUserPhone(scanner.next());

			System.out.println("이메일>>");
			user.setUserEmail(scanner.next());

			System.out.println(user);
			dao.join(user);

		} else if (selected == 2) {
			// 로그인
			dao = new UserDAO();

			System.out.println("아이디>>");
			userId = scanner.next();
			System.out.println("패스워드>>");
			String userPassword = scanner.next();

			int result = dao.login(userId, userPassword);
			if (result == 1) {
				System.out.println("로그인에 성공하였습니다.");

				frame = new ReserveFrame();
				frame.run(userId);

			} else if (result == 0) {
				System.out.println("비밀번호가 틀렸습니다. 다시 시도하세요.");
			} else if (result == -2) {
				System.out.println("존재하지 않는 아이디입니다. 다시 시도하세요.");
			} else if (result == -1) {
				System.out.println("데이터베이스 오류입니다. 관리자에게 문의하세요.");
			}
		} else if (selected == 9) {
			System.out.println("프로그램을 종료합니다.");
		}
	}
}
