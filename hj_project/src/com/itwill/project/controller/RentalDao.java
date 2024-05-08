package com.itwill.project.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.itwill.project.model.RentalInfo;
import com.itwill.project.model.Rental;

import oracle.jdbc.OracleDriver;

import static com.itwill.project.model.Rental.Entity.COL_CREATED_TIME;
import static com.itwill.project.model.Rental.Entity.COL_MODIFIED_TIME;
import static com.itwill.project.jdbc.OracleJdbc.PASSWORD;
import static com.itwill.project.jdbc.OracleJdbc.URL;
import static com.itwill.project.jdbc.OracleJdbc.USER;
import static com.itwill.project.model.Rental.Entity.*;
import static com.itwill.project.model.RentalInfo.Entity.*;


// DAO(Data Access Object)
public class RentalDao {
	//----> singleton
	private static RentalDao instance = null;
	
	private RentalDao() {
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	};
	public static RentalDao getInstance() {
		if(instance == null) {
			instance = new RentalDao();
		}
		
		return instance;
	}
	//----> singleton
	
	/**
	 * CRUD 메서드들에서 사용했던 리소스들을 해제
	 * @param conn Connection 객체
	 * @param stmt Statement 객체
	 * @param rs ResultSet 객체
	 */
	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
				if (rs != null) rs.close();
				if (stmt != null) stmt.close();
				if (conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * CRUD 메서드들에서 사용했던 리소스들을 해제
	 * @param conn Connection 객체
	 * @param stmt Statement 객체
	 * @param rs ResultSet 객체
	 */
	private void closeResources(Connection conn, Statement stmt) {
		closeResources(conn, stmt, null);
	}
	
	//ResultSet에서 각 컬럼의 값들을 읽어서 Rental 타입 객체를 생성하고 리턴
	private Rental makeRentalFromResultSet(ResultSet rs) throws SQLException {
		int id = rs.getInt(COL_ID);
		String name = rs.getString(COL_NAME);
		String email = rs.getString(COL_EMAIL);
		String password = rs.getString(COL_PASSWORD);
		String content = rs.getString(COL_CONTENT);
		String genre = rs.getString(COL_GENRE);
		LocalDateTime createdTime = rs.getTimestamp(COL_CREATED_TIME).toLocalDateTime();
		LocalDateTime modifiedTime = rs.getTimestamp(COL_MODIFIED_TIME).toLocalDateTime();	
		String approval = rs.getString(COL_APPROVAL);
		
		Rental rental = new Rental(id, name, email, password, content, genre, createdTime, modifiedTime, approval);
		
		return rental;
	}
	
	//ResultSet에서 각 컬럼의 값들을 읽어서 RentalInfo 타입 객체를 생성하고 리턴
	private RentalInfo makeRentalInfoFromResultSet(ResultSet rs) throws SQLException {
		String date = rs.getString(COL_DATE);
		String time = rs.getString(COL_TIME);
		
		RentalInfo rentalInfo = new RentalInfo(date, time);
		
		return rentalInfo;		
	}
	
	// read() 메서드에서 사용할 SQL문장
	private static final String SQL_SELECT_ALL = String.format("select * from %s order by %s", TBL_RENTALS, COL_ID);
	
	/**
	 * 데이터베이스 테이블 RENTALS 테이블에서 모든 행을 검색해서
	 * ID의 오름차순으로 정렬된 리스트를 반환
	 * 테이블에 행이 없는 경우 빈 리스트를 리턴
	 * @return Rental을 원소로 갖는 ArrayList.
	 */
	public List<Rental> read() {
		List<Rental> result = new ArrayList<Rental>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_ALL);
			rs = stmt.executeQuery();
			
			while (rs.next()) {
				Rental rental = makeRentalFromResultSet(rs);
				result.add(rental);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return result;
	}
	
	// readInfo() 메서드에서 사용할 SQL 문장.
	private static final String SQL_INSERT_INFO = String.format(
			"select * from %s",
			TBL_RENTAL_INFO);
	
	/**
	 * 데이터베이스 테이블 RENTAL_INFO 테이블에서 모든 레코드(행)를 검색해서
	 * ID(고유키)의 오름차순으로 정렬된 리스트를 반환.
	 * 테이블에 행이 없는 경우 빈 리스트를 리턴.
	 * @return RentlaInfo를 원소로 갖는 ArrayList
	 */
	public List<RentalInfo> readInfo() {
		List<RentalInfo> result = new ArrayList<RentalInfo>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_INSERT_INFO);
			rs = stmt.executeQuery();
			
			while (rs.next() ) {
				RentalInfo rentalInfo = makeRentalInfoFromResultSet(rs);
				result.add(rentalInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		
		return result;
	}
	
	// 아이디로 검색하기
		private static final String SQL_SELECT_INFO_BY_ID = String.format(
				"select %s, %s from %s where %s = ?",
				COL_DATE, COL_TIME, TBL_RENTAL_INFO, COL_ID_INFO);
		
		/**
		 * RENTALS 테이블의 고유키 id를 전달받아서, 해당 RentalInfo 객체를 리턴.
		 * @param id 검색하기 위한 고유키.
		 * @return 아이디로 검색한 RentalInfo리스트. 고유키에 해당하는 행이 없는 경우 null을 리턴.
		 */
		public List<RentalInfo> readInfo(int id) {
			List<RentalInfo> result = new ArrayList<RentalInfo>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_INFO_BY_ID);
				int index = 0;
				stmt.setInt(1, id);
				rs = stmt.executeQuery();
				
				while (rs.next() ) {
					RentalInfo rentalInfo = makeRentalInfoFromResultSet(rs);
					result.add(rentalInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			
			return result;
		}
	
	
	// create(Rental rental) 메서드에서 사용할 SQL:
	// insert into rentals (name, email, password, content, genre) values (?, ?, ?, ?, ?)
	private static final String SQL_INSERT = String.format(
			"insert into %s (%s, %s, %s, %s, %s) values (?, ?, ?, ?, ?)", 
			TBL_RENTALS, COL_NAME, COL_EMAIL, COL_PASSWORD, COL_CONTENT, COL_GENRE);
	
	/**
	 * 데이터베이스에 RENTALS 테이블에 행을 삽입.
	 * @param rentals 테이블에 삽입할 정보를 가지고 있는 객체
	 * @return 테이블에 삽입된 행의 개수.
	 */
	public int create(Rental rental) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_INSERT);
			stmt.setString(1, rental.getName());
			stmt.setString(2, rental.getEmail());
			stmt.setString(3, rental.getPassword());
			stmt.setString(4, rental.getContent());
			stmt.setString(5, rental.getGenre());
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		
		return result;
	}
	
	// createRentalInfo메서드에서 사용할 SQL:
		// insert into rentals (name, email, password, content, genre) values (?, ?, ?, ?, ?)
		private static final String SQL_INSERT_RENTALINFO = String.format(
				"insert into %s values (?, ?, (select max(%s) from %s))", 
				TBL_RENTAL_INFO, COL_ID, TBL_RENTALS);
		
		/**
		 * 데이터베이스에 RENTALS 테이블에 행을 삽입.
		 * @param rentals 테이블에 삽입할 정보를 가지고 있는 객체
		 * @return 테이블에 삽입된 행의 개수.
		 */
		public int createRentalInfo(RentalInfo rentalInfo) {
			int result = 0;
			
			Connection conn = null;
			PreparedStatement stmt = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_INSERT_RENTALINFO);
				stmt.setString(1, rentalInfo.getDate());
				stmt.setString(2, rentalInfo.getTime());
				result = stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt);
			}
			
			return result;
		}
	// searchEmailPw 메서드에서 사용할 SQL:
	// select * from rentals where email = ? and password = ?;
	private static final String SQL_SELECT_BY_EMAILPW = String.format(
			"select * from %s where %s = ? and %s = ?", 
			TBL_RENTALS, COL_EMAIL, COL_PASSWORD);
	
	/** 
	 * 이메일과 패스워드 같은 값 검색하기
	 * @param 이메일
	 * @param 패스워드
	 * @return 검색결과 리스트. 검색 결과 없으면 빈 리스트.
	 */
	public List<Rental> searchEmailPw(String email, String pw) {
		List<Rental> result = new ArrayList<Rental>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_EMAILPW);
			stmt.setString(1, email);
			stmt.setString(2, pw);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Rental rental = makeRentalFromResultSet(rs);
				result.add(rental);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
		
	}
	
	// 이메일에 검색 결과가 있는 결과:
	// select * rentals where email = ?
	private static final String SQL_SELECT_BY_EMAIL = String.format(
			"select * from %s where %s = ?", 
			TBL_RENTALS, COL_EMAIL);
	
	// 비밀번호에 검색 결과가 있는 결과:
	// select * rentals where password = ?
	private static final String SQL_SELECT_BY_PASSWORD = String.format(
			"select * from %s where %s = ?",
			TBL_RENTALS, COL_PASSWORD);
	
	
	/**
	 * 이메일 검색하기.
	 * 검색 타입과 검색어 전달받아서 해당 SQL문장을 실행하고 그 결과를 리턴.
	 * @param keyword 검색 문자열.
	 * @return 검색 결과 리스트. 검색 결과 없으면 빈 리스트.
	 */
	public List<Rental> searchEmail(String keyword) {
		List<Rental> result = new ArrayList<Rental>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String searchKeyword = keyword;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_EMAIL);
			stmt.setString(1, searchKeyword);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Rental rental = makeRentalFromResultSet(rs);
				result.add(rental);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
		
	}
	
	/**
	 * 비밀번호 검색하기.
	 * 검색 타입과 검색어 전달받아서 해당 SQL문장을 실행하고 그 결과를 리턴.
	 * @param keyword 검색 문자열.
	 * @return 검색 결과 리스트. 검색 결과 없으면 빈 리스트.
	 */
	public List<Rental> searchPW(String keyword) {
		List<Rental> result = new ArrayList<Rental>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String searchKeyword = keyword;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_PASSWORD);
			stmt.setString(1, searchKeyword);
			rs = stmt.executeQuery();
			if (rs.next()) {
				Rental rental = makeRentalFromResultSet(rs);
				result.add(rental);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
		
	}
	
	// 아이디(PK)로 검색하기:
	private static final String SQL_SELECT_BY_ID = String.format(
			"select * from %s where %s = ?", 
			TBL_RENTALS, COL_ID);
	
	/**
	 * RENTALS 테이블의 고유키 id를 전달받아서, 해당 Rental 객체를 리턴.
	 * @param id 검색하기 위한 고유키.
	 * @return 테이블에서 검색한 Rental 객체. 고유키에 해당하는 행이 없는 경우 null을 리턴.
	 */
	public Rental read(int id) {
		Rental rental = null;
		
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                rental = makeRentalFromResultSet(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(conn, stmt, rs);
        }
        
        return rental;
	}
	
	// delete(String id) 메서드에서 사용할 SQL: delete from rentals where id = ?
	private static final String SQL_DELETE_RENTALS = String.format(
			"delete from %s where %s = ?",
			TBL_RENTALS, COL_ID);
	
	/** 테이블에서 RENTALS에서 고유키 id에 해당하는 레코드를 삭제.
	 * @param id 삭제하려는 레코드의 고유키
	 * @return 테이블에서 삭제된 행의 개수.
	 */
	public int delete(int id) {
		int result = 0;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		 
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_DELETE_RENTALS);
			stmt.setInt(1, id);
			result = stmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt);
		}
		
		
		return result;
	}
	
	// deleteInfo(String date, String time) 메서드에서 사용할 SQL: delete from rental_info where d = ? and tt = ?
		private static final String SQL_DELETE_RENTAL_INFO = String.format(
				"delete from %s where %s = ? and %s = ?",
				TBL_RENTAL_INFO, COL_DATE, COL_TIME);
		
		/** 테이블에서 RENTALS에서 고유키 id에 해당하는 레코드를 삭제.
		 * @param id 삭제하려는 레코드의 고유키
		 * @return 테이블에서 삭제된 행의 개수.
		 */
		public int deleteInfo(String date, String time) {
			int result = 0;
			
			Connection conn = null;
			PreparedStatement stmt = null;
			 
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_DELETE_RENTAL_INFO);
				stmt.setString(1, date);
				stmt.setString(2, time);
				result = stmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt);
			}
			
			
			return result;
		}
	
	// maxId 메서드에서 사용할 SQL: select max(id) from rentals
	private static final String SQL_MAXID = String.format(
			"select * from %s where %s >= (select max(%s) from %s)", 
			TBL_RENTAL_INFO, COL_ID_INFO, COL_ID, TBL_RENTALS);
	
	/**
	 * 테이블 RentalInfo에서 id 최대값 검색
	 * @return id 최대값 
	 */
	public RentalInfo maxId() {
		RentalInfo rental = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_MAXID);
			rs = stmt.executeQuery();
			if (rs.next()) {
				rental = makeRentalInfoFromResultSet(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
				
		return rental;
	}
	
	// 날짜 전달받은 값이랑 같은 경우:
	// select * from rental_info where dd = ? order by date
	private static final String SQL_SELECT_BY_DATE= String.format(
			"select * from %s where %s = ? order by %s",
			TBL_RENTAL_INFO, COL_DATE, COL_DATE);
	
	/**
	 * 날짜 검색하기
	 * 날짜 전달받아서, 해당 SQL 문장을 실행하고 그 결과를 리턴.
	 * @param date 연도, 월, 일
	 * @return 검색 결과 리스트. 검색 결과가 없으면 빈 리스트.
	 */
	public List<RentalInfo> searchDate(String dd) {
		List<RentalInfo> result = new ArrayList<RentalInfo>();
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			stmt = conn.prepareStatement(SQL_SELECT_BY_DATE);
			stmt.setString(1, dd);
			rs = stmt.executeQuery();
			while (rs.next()) {
				RentalInfo rentalInfo = makeRentalInfoFromResultSet(rs);
				result.add(rentalInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources(conn, stmt, rs);
		}
		return result;
	}
	
	// 시간 전달받은 값이랑 같은 경우:
		// select * from rental_info where tt = ? order by date
		private static final String SQL_SELECT_BY_TIME= String.format(
				"select * from %s where %s = ? order by %s",
				TBL_RENTAL_INFO, COL_TIME, COL_DATE);
		
		/**
		 * 시간 검색하기
		 * 시간 전달받아서, 해당 SQL 문장을 실행하고 그 결과를 리턴.
		 * @param time 오전, 오후, 야간
		 * @return 검색 결과 리스트. 검색 결과가 없으면 빈 리스트.
		 */
		public List<RentalInfo> searchTime(String tt) {
			List<RentalInfo> result = new ArrayList<RentalInfo>();
			
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_BY_TIME);
				stmt.setString(1, tt);
				rs = stmt.executeQuery();
				while (rs.next()) {
					RentalInfo rentalInfo = makeRentalInfoFromResultSet(rs);
					result.add(rentalInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			return result;
		}
		
		// 아이디로 날짜, 시간 검색하기
		// select * from rental_info where id in (select id from rentals where email = ? and password = ?);
		private static final String SQL_SELECT_DATE_TIME = String.format(
				"select * from %s where %s in (select %s from %s where %s = ? and %s = ?) order by %s",
				TBL_RENTAL_INFO, COL_ID_INFO, COL_ID, TBL_RENTALS, COL_EMAIL, COL_PASSWORD, COL_ID_INFO);

		/**
		 * 이메일, 비밀번호 전달받아서, 해당 SQL 문장을 실행하고 그 결과를 리턴.
		 * 
		 * @param id 이메일, 비밀번호 일치하면 그 값의 아이디
		 * @return 검색 결과 리스트. 검색 결과가 없으면 빈 리스트.
		 */
		public List<RentalInfo> searchDateTime(String email, String password) {
			List<RentalInfo> result = new ArrayList<RentalInfo>();

			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;

			try {
				conn = DriverManager.getConnection(URL, USER, PASSWORD);
				stmt = conn.prepareStatement(SQL_SELECT_DATE_TIME);
				stmt.setString(1, email);
				stmt.setString(2, password);
				rs = stmt.executeQuery();
				while (rs.next()) {
					RentalInfo rentalInfo = makeRentalInfoFromResultSet(rs);
					result.add(rentalInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				closeResources(conn, stmt, rs);
			}
			return result;
		}
	
		private static final String SQL_UPDATE = String.format(
				"update %s "
				+ "set %s = ?, %s = ?, %s = systimestamp "
				+ "where %s = ?", 
				TBL_RENTALS, COL_CONTENT, COL_APPROVAL, COL_MODIFIED_TIME, COL_ID);
		
		/** 
		 * Rentals 테이블 업데이트.
		 * @param rentals 업데이트할 id(고유키), 내용을 가지고 있는 객체
		 * @return 업데이트한 행의 개수.
		 */
		
		public int update(Rental rental) {
			int result = 0;
			
			Connection conn = null;
	        PreparedStatement stmt = null;
	        try {
	            conn = DriverManager.getConnection(URL, USER, PASSWORD);
	            stmt = conn.prepareStatement(SQL_UPDATE);
	            stmt.setString(1, rental.getContent());
	            stmt.setString(2, rental.getApproval());
	            stmt.setInt(3, rental.getId());
	            result = stmt.executeUpdate();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            closeResources(conn, stmt);
	        }
	       
	        return result;
			
		}
		
		


}
