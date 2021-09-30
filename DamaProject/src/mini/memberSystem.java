package mini;

import java.util.ArrayList;
import java.util.Scanner;

public class memberSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("다마고치 키우기");
		memberDao dao = new memberDao();
		img1 img = new img1();
		while (true) {
			System.out.print("1.회원가입 2.로그인 3.종료 >> ");
			int main = sc.nextInt();
			if (main == 1) {
				System.out.println("--회원가입--");
				System.out.print("ID입력 : ");
				String id = sc.next();
				System.out.print("PW입력 : ");
				String pw = sc.next();

				memberVO vo = new memberVO(id, pw);

				int cnt = dao.join(vo);

				if (cnt > 0)
					System.out.println("회원가입 성공!");
				else
					System.out.println("회원가입 실패...");
			} else if (main == 2) {
				System.out.println("--로그인--");
				System.out.print("ID입력 : ");
				String id = sc.next();
				System.out.print("PW입력 : ");
				String pw = sc.next();

				memberVO vo = new memberVO(id, pw);
				memberVO info = dao.login(vo);

				if (info != null) {
					System.out.println("로그인 성공!");
					System.out.println(info.getId() + "님 환영합니다!");

					while (true) {
						System.out.print("1.다마고치 등록 2.다마고치 키우기 3.다마고치 정보확인 4.랭킹 확인 5.종료 >> ");
						int choose = sc.nextInt();
						if (choose == 1) {
							System.out.println("■■다마고치 등록■■");
							System.out.print("NICK입력 : ");
							String nick = sc.next();

							memberVO vo1 = new memberVO(nick, id, pw);

							int cnt = dao.make(vo1);

							if (cnt > 0) {
								System.out.println(nick + "생성!");
								img.birth();
							} else
								System.out.println("생성 실패...");
						} else if (choose == 2) {
							while (true) {
								// 랜덤 출력
								String[] desire = {"배고파", "졸려","피곤해","공부해야지"}; 
							    int count=0;
							    while (true) {
							       count = (int)(Math.random()*4); // 1-5개? 
							       System.out.println(desire[count]);
							       break;
							    }
								memberVO exp = new memberVO(id);

								System.out.print("1.밥먹기 2.잠자기 3.휴식하기 4.공부하기 5.종료 >> ");
								String num = sc.next();

								if (num.equals("1")) {
									// 밥먹는 이미지
									img.food();
									if (count == 0)
										dao.expplus(exp);
									else
										dao.expminus(exp);

								} else if (num.equals("2")) {
									// 잠자는 이미지
									img.sleep();
									if (count == 1)
										dao.expplus(exp);
									else
										dao.expminus(exp);

								} else if (num.equals("3")) {
									// 휴식 이미지
									img.rest();
									if (count == 2)
										dao.expplus(exp);
									else
										dao.expminus(exp);

								} else if (num.equals("4")) {
									// 공부 이미지
									img.study();
									if (count == 3)
										dao.expplus(exp);
									else
										dao.expminus(exp);

								} else if (num.equals("5"))
									break;
								else
									System.out.println("잘못입력하셨습니다.");
//								if(dao.exp(vo) < 100) {
//									dao.lvup(exp);
//								}
								System.out.println(dao.exp(vo));
							}
						} else if (choose == 3) {
							System.out.println("■■■정보확인■■");
							dao.checkStatus(vo);
						} else if (choose == 4) {
							// 랭킹
							System.out.println("--랭킹--");
							ArrayList<memberVO> list = dao.Rank();
							for (int i = 0; i < list.size(); i++) {
								System.out.println((i + 1) + "등 " + list.get(i));
							}
						} else if (choose == 5) {
							System.out.println("종료");
							break;
							// 종료
						} else
							System.out.println("잘못입력하셨습니다.");
					}
				} else
					System.out.println("로그인 실패...");

			} else if (main == 3) {
				System.out.println("종료합니다.");
				break;
			} else
				System.out.println("잘못입력하셨습니다.");
		}

	}
}
