package mini;

import java.util.ArrayList;
import java.util.Scanner;

public class memberSystem {

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		wav wav = new wav();
		System.out.println("다마고치 키우기");
		memberDao dao = new memberDao();
		img img = new img();
		while (true) {
			System.out.print("1.회원가입 2.로그인 3.종료 >> ");
			int main = sc.nextInt();
			wav.num();
			if (main == 1) {
				System.out.println("◆ 회원가입 ◆");
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
				System.out.println("◆ 로그인 ◆");
				System.out.print("ID입력 : ");
				String id = sc.next();
				System.out.print("PW입력 : ");
				String pw = sc.next();

				memberVO vo = new memberVO(id, pw);
				memberVO info = dao.login(vo);

				if (info != null) {
					System.out.println("로그인 성공!\n");
					System.out.println(info.getId() + "님 환영합니다!");
					wav.haha();

					while (true) {
						System.out.print("\n 1.다마고치 등록 2.다마고치 키우기 3.다마고치 정보확인 4.랭킹 확인 5.종료 >> ");
						int choose = sc.nextInt();
						wav.num();
						if (choose == 1) {
							System.out.println("                   *** 다마고치 등록 ***");
							System.out.print("별명 입력 : ");
							String nick = sc.next();
							memberVO vo1 = new memberVO(nick, id, pw);// 지역변수라 위에도씀
							int cnt = dao.make(vo1);
							if (cnt > 0) {
								System.out.println("                                 ★ " + nick + " ★ 생성");
								img.birth();
								wav.birth();
							} else
								System.out.println("생성 실패...\n");
						} else if (choose == 2) {
							while (true) {
								// 랜덤 출력
								memberVO exp = new memberVO(id);
								if (dao.nickPrint(vo) == null) {
									System.out.println("다마고치 등록부터 해주세요.");
									break;
								}
								if (Integer.parseInt(dao.lv(exp)) >= 3) {
									System.out.println("*******************");
									System.out.println("  이미 취업하셨습니다. ");
									System.out.println("*******************");
									img.success();
									break;
								}
								String[] desire = { "배고파", "졸려", "힘들어", "공부해야지", "아파" };
								int count = 0;
								while (true) {
									count = (int) (Math.random() * 5);
									try {
										Thread.sleep(1500);
									} catch (InterruptedException e) {
									}
									System.out.println("\r\n" + "            ....------....            \r\n"
											+ "        .-//:--:::------::--:+/..      \r\n"
											+ "     .-++:-..               .-://-..   \r\n"
											+ "   .-/:..                       ..-+:.  \r\n"
											+ "  -//-.                           `.:/. \r\n"
											+ " .s:.                               .:s.\r\n"
											+ "  +.                                 .o:\r\n" + "                "
											+ dao.nickPrint(vo) + "는 " + desire[count] + "\r\n"
											+ " .+.                                 .-+\r\n"
											+ " .o+.                               -s-\r\n"
											+ "  .//-.                           .:+-\r\n"
											+ "   .-//..`....                 ..-//.\r\n"
											+ "     .-//+/::/:.          ...--//:-.\r\n"
											+ "        -s.``.:+--..------/+o/-.\r\n"
											+ "       ..:----:::--------...\r\n" + "      -+o+-...\r\n"
											+ "     .//:+/\r\n" + "     .-:-");
									if (count == 0)
										wav.food();
									else if (count == 1)
										wav.sleep();
									else if (count == 2)
										wav.rest();
									else if (count == 3)
										wav.study();
									else if (count == 4)
										wav.sick();
									break;
								}
								System.out.print("1.밥먹기 2.잠자기 3.휴식하기 4.공부하기 5.병원가기 6.종료 >> ");
								String num = sc.next();
								wav.num();

								if (num.equals("1")) {
									// 밥먹는 이미지
									if (count == 0) {
										img.food();
										wav.yes();
										dao.expplus(exp);
									} else {
										img.fail();
										wav.fail();
										dao.expminus(exp);
									}
									System.out.println("\nLV." + Integer.parseInt(dao.lv(exp)) + "\t\t\t\t\texp : "
											+ Integer.parseInt(dao.exp(exp)) + "/100\n");
								} else if (num.equals("2")) {
									// 잠자는 이미지
									if (count == 1) {
										img.sleep();
										wav.yes();
										dao.expplus(exp);
									} else {
										img.fail();
										wav.fail();
										dao.expminus(exp);
									}
									System.out.println("\nLV." + Integer.parseInt(dao.lv(exp)) + "\t\t\t\t\texp : "
											+ Integer.parseInt(dao.exp(exp)) + "/100\n");

								} else if (num.equals("3")) {
									// 휴식 이미지
									if (count == 2) {
										img.rest();
										wav.yes();
										dao.expplus(exp);
									} else {
										img.fail();
										wav.fail();
										dao.expminus(exp);
									}
									System.out.println("\nLV." + Integer.parseInt(dao.lv(exp)) + "\t\t\t\t\texp : "
											+ Integer.parseInt(dao.exp(exp)) + "/100\n");

								} else if (num.equals("4")) {
									// 공부 이미지
									if (count == 3) {
										img.study();
										wav.yes();
										dao.expplus(exp);
									} else {
										img.fail();
										wav.fail();
										dao.expminus(exp);
									}
									System.out.println("\nLV." + Integer.parseInt(dao.lv(exp)) + "\t\t\t\t\texp : "
											+ Integer.parseInt(dao.exp(exp)) + "/100\n");

								} else if (num.equals("5")) {
									// 병원
									if (count == 4) {
										img.hospital();
										wav.yes();
										dao.expplus(exp);
									} else {
										img.fail();
										wav.fail();
										dao.expminus(exp);
									}
									System.out.println("\nLV." + Integer.parseInt(dao.lv(exp)) + "\t\t\t\t\texp : "
											+ Integer.parseInt(dao.exp(exp)) + "/100\n");

								} else if (num.equals("6")) {
									System.out.println("************");
									System.out.println(" 그만키울래 ");
									System.out.println("************");
									wav.bye();
									break;
								} else {
									System.out.println("잘못입력하셨습니다.");
									wav.fail();
								}

								if (Integer.parseInt(dao.exp(exp)) >= 100) {
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
									}
									dao.lvup(exp);
									img.levelUp();
									dao.exp0(exp);
									System.out.println("\nLV." + Integer.parseInt(dao.lv(exp)) + "\t\t\t\texp : "
											+ Integer.parseInt(dao.exp(exp)) + "/100\n");
								}

								if (Integer.parseInt(dao.lv(exp)) >= 3) {
									try {
										Thread.sleep(1000);
									} catch (InterruptedException e) {
									}
									img.success();
									wav.success();
									break;
								}
							}
						} else if (choose == 3) {
							if (dao.nickPrint(vo) == null) {
								System.out.println("다마고치 등록부터 해주세요.");
							} else {
								System.out.println("                     ********************");
								System.out.println("                           정보확인      ");
								System.out.println("                     ********************");
								img.nowStatus();
								dao.checkStatus(vo);
							}
						} else if (choose == 4) { // 다마고치 랭킹확인
							// 랭킹
							System.out.println("                     ********************");
							System.out.println("                           랭킹확인     ");
							System.out.println("                     ********************");
							ArrayList<memberVO> list = dao.Rank();
							for (int i = 0; i < list.size(); i++) {
								System.out.println((i + 1) + "등 " + list.get(i));
							}
						} else if (choose == 5) { // 프로그램종료

							System.out.println("***************************");
							System.out.println("   초기화면으로 돌아갑니다. ");
							System.out.println("***************************");
							wav.bye();
							break;
						} else {
							System.out.println("잘못입력하셨습니다.");
							wav.fail();
						}
					}
				} else
					System.out.println("로그인 실패...\n");
			} else if (main == 3) {
				System.out.println("프로그램을 종료합니다.");
				wav.bye();
				break;
			} else {
				System.out.println("잘못입력하셨습니다.");
				wav.fail();
			}

		}
	}
}