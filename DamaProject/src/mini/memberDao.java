package mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

public class memberDao {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	private void getConn() { // 연결 
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String db_url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "cgi_5_4";
			String db_pw = "smhrd4";
			conn = DriverManager.getConnection(db_url, db_id, db_pw);
					
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void close() {  // 닫기 
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int join(memberVO vo) { // 회원가입
		int cnt = 0;
		try {
			getConn();
			String sql = "insert into member values(?, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return cnt;
	}
	
	public memberVO login(memberVO vo) { // 로그인 
		memberVO info = null;
		try {
			getConn();
			String sql = "select * from member where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			rs = psmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("id");
				String pw = rs.getString("pw");
				info = new memberVO(id, pw);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return info;
	}
	
	public int make(memberVO vo) {  // 다마고치 별명 등록 (캐릭터 생성) 
		int cnt = 0;
		try {
			getConn();
			String sql = "insert into dama values(?, 1, 0, ?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getNick());
			psmt.setString(2, vo.getId());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("이미 생성하셨습니다.");
			e.printStackTrace();
		}
		finally {
			close();
		}
		return cnt;
	}

	public void randomDesire () { // 랜덤욕구 출력
		String[] desire = {"배고파", "졸려","피곤해","공부해야지","아파"}; 
		int count=0;
		while (true) {
			count = (int)(Math.random()*5); // 1-5개? 
			System.out.println(desire[count]);
			break;
		}
	}
	
	public String nickPrint (memberVO vo) { // 별명 출력 
		getConn();
		String sql = "select nick from dama where id = ?";
		String nick = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				nick = rs.getString("nick");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
		return nick;
	}
	
	public void expplus(memberVO vo) {  // 욕구 = 경험치 +20 
	      int cnt = 0;
	      try {
	         getConn();
	         String sql = "update dama1 set exp = exp + 20 where id = ?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, vo.getId());
	         cnt = psmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	   }
	
   public void expminus(memberVO vo) { // 욕구 = 경험치 -20 

      int cnt = 0;
      try {
         getConn();
         String sql = "update dama1 set exp = exp - 20 where id = ?";
         psmt = conn.prepareStatement(sql);
         psmt.setString(1, vo.getId());
         cnt = psmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         close();
      }
   }
   
   public String exp(memberVO vo) {  // 경험치 
	      int a = 0;
	      try {
	         getConn();

	         String sql = "select exp from dama1 where id = ?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, vo.getId());
	         rs = psmt.executeQuery();
	         if (rs.next()) {
	            return rs.getString("exp");
	         }

	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	      return null;
	   }
   
   public void exp0(memberVO vo) {   // 경험치 초기화 
	      int cnt = 0;
	      try {
	         getConn();

	         String sql = "update dama1 set exp = 0 where id = ?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, vo.getId());

	         cnt = psmt.executeUpdate();
	      } catch (SQLException e) {
	      } finally {
	         close();
	      }
	   }

   public void lvup(memberVO vo) {  // 레벨업 ! 
	      int cnt = 0;
	      try {
	         getConn();
	         String sql = "update dama1 set lv = lv + 1 where id = ?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, vo.getId());
	         cnt = psmt.executeUpdate();
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         close();
	      }
	   }

	
	public ArrayList<memberVO> Rank() {  // 랭킹 출력 
		ArrayList<memberVO> list = new ArrayList<memberVO>();
		getConn();
		String sql = "select id, nick, lv, exp from dama order by lv desc, exp desc";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id");
				String nick = rs.getString("nick");
				String lv = rs.getString("lv");
				int exp = rs.getInt("exp");

				memberVO vo = new memberVO(id, nick, lv, exp);
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}
	
	public void checkStatus (memberVO vo)  {  // 상태 출력 
		getConn();
		String sql = "select id,nick,lv,exp from dama where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				System.out.print("id : " + rs.getString("id")+ "\t");
				System.out.print(" 별명 : " + rs.getString("nick")+ "\t");
				System.out.print(" 레벨 : " + rs.getString("lv")+ "\t");
				System.out.println(" 경험치 : " + rs.getString("exp"));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}
	}
	
}
	

	
