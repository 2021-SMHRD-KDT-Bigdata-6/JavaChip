package mini;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class memberDao {

	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;

	// 연결
	private void getConn() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			String db_url = "jdbc:oracle:thin:@project-db-stu.ddns.net:1524:xe";
			String db_id = "cgi_5_4";
			String db_pw = "smhrd4";
			conn = DriverManager.getConnection(db_url, db_id, db_pw);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 연결해제...
	private void close() {
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

	// 로그인
	public memberVO login(memberVO vo) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return info;
	}

	// 회원가입 성공 여부
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
		} finally {
			close();
		}
		return cnt;
	}

	// 다마고치 캐릭터 생성
	public int make(memberVO vo) {
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
		} finally {
			close();
		}
		return cnt;
	}

	// 별명
	public String nickPrint(memberVO vo) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return nick;
	}

	// 경험치 증가
	public void expplus(memberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update dama set exp = exp + 50 where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 경험치 감소
	public void expminus(memberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update dama set exp = exp - 50 where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	public String exp(memberVO vo) {
		int a = 0;
		try {
			getConn();

			String sql = "select exp from dama where id = ?";
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

	public String lv(memberVO vo) {
		int a = 0;
		try {
			getConn();

			String sql = "select lv from dama where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				return rs.getString("lv");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	// 레벨업
	public void lvup(memberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update dama set lv = lv + 1 where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// 레벨업 시 경험치 초기화
	public void exp0(memberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update dama set exp = 0 where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());

			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
		} finally {
			close();
		}
	}

	// 랜덤욕구 출력
	public void randomDesire() {
		String[] desire = { "배고파", "졸려", "피곤해", "공부해야지", "아파" };
		int count = 0;
		while (true) {
			count = (int) (Math.random() * 5); // 1-5개?
			System.out.println(desire[count]);
			break;
		}
	}

	// 랭킹 출력
	public ArrayList<memberVO> Rank() {
		ArrayList<memberVO> list = new ArrayList<memberVO>();
		getConn();
		String sql = "select id, nick, lv, exp from dama order by lv desc, exp desc";

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				String id = rs.getString("id")+"     \t";
				String nick = rs.getString("nick")+"   \t";
				String lv = rs.getString("lv")+"    \t";
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

	// 상태 출력
	public void checkStatus(memberVO vo) {
		getConn();
		String sql = "select id,nick,lv,exp from dama where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				System.out.print("id : " + rs.getString("id") + "       \t");
				System.out.print("별명 : " + rs.getString("nick") + "          \t");
				System.out.print("레벨 : " + rs.getString("lv") + "             \t");
				System.out.println("경험치 : " + rs.getString("exp") + "         \t");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

}