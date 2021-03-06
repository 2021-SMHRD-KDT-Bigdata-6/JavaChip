package mini;

import java.util.ArrayList;
import java.util.Scanner;

public class memberSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("다마고치 키우기");
		memberDao dao = new memberDao();

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
			} 
			else if (main == 2) {
				System.out.println("--로그인--");
				System.out.print("ID입력 : ");
				String id = sc.next();
				System.out.print("PW입력 : ");
				String pw = sc.next();
				
				memberVO vo = new memberVO(id, pw);
				memberVO info = dao.login(vo);
				
				if(info != null) {
					System.out.println("로그인 성공!");
					System.out.println(info.getId() + "님 환영합니다!");
					
					while (true) {
						System.out.print("1.다마고치 등록 2.다마고치 키우기 3.다마고치 정보확인 4.랭킹 확인 5.종료 >> ");
						int choose = sc.nextInt();
						if (choose == 1) {
							System.out.println("--다마고치 등록--");
							System.out.print("NICK입력 : ");
							String nick = sc.next();
						
							memberVO vo1 = new memberVO(nick, id, pw);

							int cnt = dao.make(vo1);

							if (cnt > 0)
								System.out.println(nick + "생성!");
							else
								System.out.println("생성 실패...");
						}
						else if (choose == 2) {
							while(true) {
								// 랜덤 출력
								System.out.print("1.밥먹기 2.잠자기 3.휴식하기 4.공부하기 5.종료 >> ");
								String num = sc.next();
								
								if(num.equals("1")) {
									//밥먹는 이미지
									
									
								}
								else if(num.equals("2")) {
									//잠자는 이미지
									
								}
								else if(num.equals("3")) {
									//휴식 이미지
									
								}
								else if(num.equals("4")) {
									//공부 이미지
									
								}
								else if(num.equals("5")) 
									break;
								else
									System.out.println("잘못입력하셨습니다.");
							}
						}
						else if (choose == 3) {
							
						}
						else if (choose == 4) {
							// 랭킹
							System.out.println("--랭킹--");
							ArrayList<memberVO> list = dao.selectAll();
							for (int i = 0; i < list.size(); i++) {
								System.out.println(list.get(i));
							}
						}
						else if (choose == 5) {
							System.out.println("종료");
							break;
							//종료
						}
						else
							System.out.println("잘못입력하셨습니다.");
					}
				}
				
				else
					System.out.println("로그인 실패...");
				
			}
			else if (main == 3) {
				System.out.println("종료합니다.");
				break;
			}
			else
				System.out.println("잘못입력하셨습니다.");



		}

	}
}
