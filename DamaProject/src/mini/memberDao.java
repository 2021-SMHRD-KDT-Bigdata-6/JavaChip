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
			String sql = "insert into dama1 values(?, 1, 0, ?)";

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
	public void nickPrint(memberVO vo) {
		getConn();
		String sql = "select nick from dama where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getString("nick"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

	// ����ġ
	public void expplus(memberVO vo) {
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

	public void expminus(memberVO vo) {
		int cnt = 0;
		try {
			getConn();
			String sql = "update dama1 set exp = exp - 20 where id = ?";
			psmt = conn.prepareStatement(sql);
			psmt.executeUpdate();
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

	 public void lvup(memberVO vo) {
		 getConn();
		 String sql = "update dama1 set lv = lv + 1 where id = ?";
		 // �ٽ� ����
		 
		 
		 
		 
	 }

//	public int make(memberVO vo) {  // �ٸ���ġ ���� ��� 
//		int cnt = 0;
//		try {
//			getConn();
//			String sql = "insert into dama values(?, 1, 0, ?)";
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, vo.getNick());
//			psmt.setString(2, vo.getId());
//			cnt = psmt.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		finally {
//			close();
//		}
//	}

	public void exp0(memberVO vo) {
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

	public void randomDesire() { // �����屸 ���
		String[] desire = { "�����", "����", "�ǰ���", "�����ؾ���", "����" };
		int count = 0;
		while (true) {
			count = (int) (Math.random() * 5); // 1-5��?
			System.out.println(desire[count]);
			break;
		}
	}

	// ��ŷ
	// public ArrayList<memberVO> Rank() {

	public ArrayList<memberVO> Rank() { // ��ŷ ���
		ArrayList<memberVO> list = new ArrayList<memberVO>();
		getConn();
		String sql = "select id, nick, lv, exp from dama1 order by lv desc, exp desc";

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

	public ArrayList<memberVO> select() {
		ArrayList<memberVO> list = new ArrayList<memberVO>();
		getConn();
		String sql = "select id, nick, lv, exp from dama1";

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

	public void checkStatus(memberVO vo) { // ���� ���
		getConn();
		String sql = "select id,nick,lv,exp from dama where id = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				System.out.print("id : " + rs.getString("id") + "\t");
				System.out.print("���� : " + rs.getString("nick") + "\t");
				System.out.print("���� : " + rs.getString("lv") + "\t");
				System.out.println("����ġ : " + rs.getString("exp"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
	}

}
