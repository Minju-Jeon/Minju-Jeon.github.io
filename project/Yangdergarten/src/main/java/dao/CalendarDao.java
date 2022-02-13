package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.spi.CalendarNameProvider;

import dto.Calendar;
import utils.ConnectionHelper;

public class CalendarDao {

	public List<Calendar> findAllEvents() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Calendar> events = new ArrayList<Calendar>();

		try {
			conn = ConnectionHelper.getConnection("oracle");

			String sql = "select ID, TITLE, STARTDATE, ENDDATE from CALENDAR";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while(rs.next()) {
				Calendar calendar = new Calendar();
				calendar.setId(rs.getInt("ID"));
				calendar.setTitle(rs.getString("TITLE"));
				calendar.setStartDate(rs.getDate("STARTDATE").toString());
				calendar.setEndDate(rs.getDate("ENDDATE").toString());
				events.add(calendar);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return events;
	}
	
	public int saveEvent(Calendar cal) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int resultrow=0;
		
		try {
			conn = ConnectionHelper.getConnection("oracle");
//			오류나면 오라클이라서 그른거라.. 쿼리 다시 ㅠㅠ 해야행
			String sql = "insert into CALENDAR(id,title,startdate,enddate) values(1,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cal.getTitle());
			pstmt.setString(2, cal.getStartDate());
			pstmt.setString(3, cal.getEndDate());
			
			resultrow = pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("saveEvent : "+e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return resultrow;
	}

	public int deleteAllEvent() {
		Connection conn=null;
		PreparedStatement pstmt=null;
		int resultrow=0;
		try {
			conn = ConnectionHelper.getConnection("oracle");

			String sql = "delete from CALENDAR where 1=1";
			pstmt = conn.prepareStatement(sql);
			resultrow = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("saveEvent : "+e.getMessage());
		}finally {
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return resultrow;
	}
}
