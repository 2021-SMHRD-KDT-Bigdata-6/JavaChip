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

	private void getConn() {
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

	public int join(memberVO vo) {
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
			e.printStackTrace();
		}
		finally {
			close();
		}
		return cnt;
	}
	
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
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			close();
		}

		return info;
	}
	
	

	public int update(memberVO vo) {
		int cnt = 0;

		try {
			getConn();

			String sql = "update member set pw = ?, nick = ?, phone = ?  where id = ?";
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, vo.getPw());
			psmt.setString(2, vo.getNick());
			psmt.setString(3, vo.getPhone());
			psmt.setString(4, vo.getId());

			cnt = psmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}
		return cnt;
	}

	public void exp(memberVO vo) {
		int cnt = 0;

		try {
			getConn();

			String sql = "update member set exp + 30";
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, vo.getExp());

			cnt = psmt.executeUpdate();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}

		finally {
			close();
		}
	}
	public ArrayList<memberVO> selectAll() {
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

	public int delete(memberVO vo) {
		int cnt = 0;
		try {
			getConn();

			String sql = "delete from big_member where id = ? and pw = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			psmt.setString(2, vo.getPw());
			cnt = psmt.executeUpdate();
		} catch (SQLException e) {
			// exception을 추적하면서 문제가 발생하는지 출력 어디서
			e.printStackTrace();
		} finally {
			close();
		}
		return cnt;
	}
}
