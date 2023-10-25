// 필요한 패키지와 클래스를 가져옵니다.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

// "DBUtil"이라는 클래스를 정의합니다. 이 클래스는 데이터베이스와의 연결 및 작업을 담당합니다.
public class DBUtil {

	// 데이터베이스 연결과 관련된 변수들을 선언합니다.
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	// 데이터베이스 연결 정보를 저장합니다.
	String url = "jdbc:mysql://localhost:3306/b1?serverTimezone=UTC";
	String user = "root";
	String pass = "";

	// "DBUtil" 클래스의 생성자입니다. 객체가 생성될 때 데이터베이스 연결을 초기화합니다.
	public DBUtil() {
		conn = getConnection();
	}

	// 데이터베이스 연결을 생성하고 반환하는 메서드입니다.
	public Connection getConnection() {

		Connection conn = null;

		try {
			// 1. MySQL 드라이버를 로드합니다.
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2. 데이터베이스 연결을 생성합니다.
			conn = DriverManager.getConnection(url, user, pass);

		} catch(Exception e) {
			// 연결 중 문제가 발생하면 오류 메시지를 출력합니다.
			System.out.println("DB 작업중 문제 발생");
			e.printStackTrace();
		}

		return conn;
	}

	// 주소록에 새로운 주소를 추가하는 메서드입니다.
	public void insertAddress(String name, String address, String phone) {

		try {
			stmt = conn.createStatement();

			// 주소록에 추가할 SQL 쿼리를 작성합니다.
			String sql = "INSERT INTO t_address\r\n"
					+ "SET `name` = '" + name + "',\r\n"
					+ "address = '" + address + "',\r\n"
					+ "phone = '" + phone + "'";

			// SQL 쿼리를 실행하여 주소록에 추가합니다.
			stmt.executeUpdate(sql);

		} catch(Exception e) {
			// 작업 중 문제가 발생하면 오류 메시지를 출력합니다.
			System.out.println("ADD DB작업중 문제 발생!!");
			e.printStackTrace();
		}
	}

	// 데이터베이스에서 모든 주소록 목록을 가져오는 메서드입니다.
	public ArrayList<Addr> getAddresses() {

		ArrayList<Addr> AddrList = new ArrayList<>();

		try {
			stmt = conn.createStatement();

			// 모든 주소록을 가져올 SQL 쿼리를 작성합니다.
			String sql = "SELECT *\r\n"
					+ "FROM t_address";

			// SQL 쿼리를 실행하여 결과를 가져옵니다.
			rs = stmt.executeQuery(sql);

			// 결과를 순회하며 주소록 목록에 추가합니다.
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");

				Addr a1 = new Addr(id, name, address, phone);
				AddrList.add(a1);
			}
		} catch(Exception e) {
			// 작업 중 문제가 발생하면 오류 메시지를 출력합니다.
			System.out.println("list DB 작업중 문제 발생!!");
			e.printStackTrace();
		}

		return AddrList;
	}

	// 주소록의 정보를 수정하는 메서드입니다.
	public void updateAddress(int id, String name, String address, String phone) {
		try {
			stmt = conn.createStatement();

			// 주소록을 수정할 SQL 쿼리를 작성합니다.
			String sql = "UPDATE t_address\r\n"
					+ "SET `name` = '" + name + "',\r\n"
					+ "address = '" + address + "',\r\n"
					+ "phone = '" + phone + "'\r\n"
					+ "WHERE id = " + id;

			// SQL 쿼리를 실행하여 주소록을 수정합니다.
			stmt.executeUpdate(sql);

		} catch(Exception e) {
			// 작업 중 문제가 발생하면 오류 메시지를 출력합니다.
			System.out.println("ADD DB작업중 문제 발생!!");
			e.printStackTrace();
		}
	}

	// 주소록을 삭제하는 메서드입니다.
	public void deleteAddress(int id) {
		try {
			stmt = conn.createStatement();

			// 주소록을 삭제할 SQL 쿼리를 작성합니다.
			String sql = "DELETE FROM t_address\r\n"
					+ "WHERE id = " + id;

			// SQL 쿼리를 실행하여 주소록을 삭제합니다.
			stmt.executeUpdate(sql);

		} catch(Exception e) {
			// 작업 중 문제가 발생하면 오류 메시지를 출력합니다.
			System.out.println("ADD DB작업중 문제 발생!!");
			e.printStackTrace();
		}
	}
}