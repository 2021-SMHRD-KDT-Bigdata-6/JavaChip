package mini;

import java.util.ArrayList;
import java.util.Scanner;

public class memberSystem {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("�ٸ���ġ Ű���");
		memberDao dao = new memberDao();

		while (true) {
			System.out.print("1.ȸ������ 2.�α��� 3.���� >> ");
			int main = sc.nextInt();
			if (main == 1) {
				System.out.println("--ȸ������--");
				System.out.print("ID�Է� : ");
				String id = sc.next();
				System.out.print("PW�Է� : ");
				String pw = sc.next();

				memberVO vo = new memberVO(id, pw);

				int cnt = dao.join(vo);

				if (cnt > 0)
					System.out.println("ȸ������ ����!");
				else
					System.out.println("ȸ������ ����...");
			} 
			else if (main == 2) {
				System.out.println("--�α���--");
				System.out.print("ID�Է� : ");
				String id = sc.next();
				System.out.print("PW�Է� : ");
				String pw = sc.next();
				
				memberVO vo = new memberVO(id, pw);
				memberVO info = dao.login(vo);
				
				if(info != null) {
					System.out.println("�α��� ����!");
					System.out.println(info.getId() + "�� ȯ���մϴ�!");
					
					while (true) {
						System.out.print("1.�ٸ���ġ ��� 2.�ٸ���ġ Ű��� 3.�ٸ���ġ ����Ȯ�� 4.��ŷ Ȯ�� 5.���� >> ");
						int choose = sc.nextInt();
						if (choose == 1) {
							System.out.println("--�ٸ���ġ ���--");
							System.out.print("NICK�Է� : ");
							String nick = sc.next();
						
							memberVO vo1 = new memberVO(nick, id, pw);

							int cnt = dao.make(vo1);

							if (cnt > 0)
								System.out.println(nick + "����!");
							else
								System.out.println("���� ����...");
						}
						else if (choose == 2) {
							while(true) {
								// ���� ���
								System.out.print("1.��Ա� 2.���ڱ� 3.�޽��ϱ� 4.�����ϱ� 5.���� >> ");
								String num = sc.next();
								
								if(num.equals("1")) {
									//��Դ� �̹���
									
									
								}
								else if(num.equals("2")) {
									//���ڴ� �̹���
									
								}
								else if(num.equals("3")) {
									//�޽� �̹���
									
								}
								else if(num.equals("4")) {
									//���� �̹���
									
								}
								else if(num.equals("5")) 
									break;
								else
									System.out.println("�߸��Է��ϼ̽��ϴ�.");
							}
						}
						else if (choose == 3) {
							
						}
						else if (choose == 4) {
							// ��ŷ
							System.out.println("--��ŷ--");
							ArrayList<memberVO> list = dao.selectAll();
							for (int i = 0; i < list.size(); i++) {
								System.out.println(list.get(i));
							}
						}
						else if (choose == 5) {
							System.out.println("����");
							break;
							//����
						}
						else
							System.out.println("�߸��Է��ϼ̽��ϴ�.");
					}
				}
				
				else
					System.out.println("�α��� ����...");
				
			}
			else if (main == 3) {
				System.out.println("�����մϴ�.");
				break;
			}
			else
				System.out.println("�߸��Է��ϼ̽��ϴ�.");



		}

	}
}
