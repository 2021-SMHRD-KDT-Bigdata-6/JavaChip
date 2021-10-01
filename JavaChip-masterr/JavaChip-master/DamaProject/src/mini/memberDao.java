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

	// ����
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

	// ��������...
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

	// �α���
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

	// ȸ������ ���� ����
	public int join(memberVO vo) { // ȸ������
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

	// �ٸ���ġ ĳ���� ����
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
			System.out.println("�̹� �����ϼ̽��ϴ�.");
		} finally {
			close();
		}
		return cnt;
	}

	// ����
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

	// ����ġ ����
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

	// ����ġ ����
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

	// ������
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

	// ������ �� ����ġ �ʱ�ȭ
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

	// �����屸 ���
	public void randomDesire() {
		String[] desire = { "�����", "����", "�ǰ���", "�����ؾ���", "����" };
		int count = 0;
		while (true) {
			count = (int) (Math.random() * 5); // 1-5��?
			System.out.println(desire[count]);
			break;
		}
	}

	// ��ŷ ���
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

	// ���� ���
	public void checkStatus(memberVO vo) {
		getConn();
		String sql = "select id,nick,lv,exp from dama where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				System.out.print("id : " + rs.getString("id") + "       \t");
				System.out.print("���� : " + rs.getString("nick") + "          \t");
				System.out.print("���� : " + rs.getString("lv") + "             \t");
				System.out.println("����ġ : " + rs.getString("exp") + "         \t");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

}