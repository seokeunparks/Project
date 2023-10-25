import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;

// DB연동 설정파일 (jdbc: mariadb / framework: Mybatis)
public class DBUtil {

	// jdbc 사용자 정보 입력
	String url = "jdbc:mariadb://127.0.0.1:3306/java_selfpj_thisisjava"; // addr_prj: 데이터가 저장될 스키마
	String user = "root"; // 아이디
	String pass = "12345"; // 비밀번호
	SqlSessionFactory sqlSessionFactory; // mybatis SqlSessionFactory 필드 생성


	// db 연동 확인 메소드. 최초 실행 메소드
	public void init(){
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);

			// SqlSessionFactory 빌드하기
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		} catch (Exception e) {
			System.out.println("MyBatis 설정 파일 가져오는 중 문제 발생!!");
			e.printStackTrace(); // printStackTrace() : 예외가 발생하면 예외 족적(발자취)을 남긴다.
		}

	}

	// 게시글 작성 메소드 (CREATE)
	public void insertBoard(String btitle, String bcontent, String bwriter) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		BoardVO boardVO = new BoardVO(btitle, bcontent, bwriter);
		mapper.insertBoard(boardVO);


		session.commit(); // update, delete, insert
	}

	// 게시판 목록 출력 메소드 (READ)
	public ArrayList<BoardVO> getBoard(){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		ArrayList<BoardVO> boardVOList = mapper.getBoard();

		return boardVOList;
	}

	// 입력한 bno의 게시글을 읽는 메소드 (READ)
	public ArrayList<BoardVO> readBoard(int bno){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		ArrayList<BoardVO> boardVOList = mapper.readBoard(bno);

		return boardVOList;
	}

	// 게시글 수정 메소드 (UPDATE)
	public void updateBoard(int bno, String btitle, String bcontent, String bwriter){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		BoardVO boardVO = new BoardVO(bno, btitle, bcontent, bwriter);
		mapper.updateBoard(boardVO);

		session.commit();
	}

	// 게시글 삭제 메소드 (DELETE)
	public void deleteBoard(int bno) {
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		mapper.deleteBoard(bno);

		session.commit(); // update, delete, insert
	}

	// 게시글 전체 삭제 메소드 (DELETE)
	public void clearBoard(){
		SqlSession session = sqlSessionFactory.openSession();
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		mapper.clearBoard();

		session.commit();
	}

	// 게시판 틀 출력 메소드
	public void homeBoard(){
		System.out.println();
		System.out.println("[게시판 목록]");
		System.out.println("-----------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-40s%-40s\n", "no", "writer", "date", "title"); // ??? 형식문자열. %-6s : 6자리 정수, 오른쪽 빈자리 공백
		System.out.println("-----------------------------------------------------------------");
	}

	// 메인메뉴 출력 메소드
	public void mainMenu(){
		System.out.println("-----------------------------------------------------------------");
		System.out.println("메인 메뉴 : 1.create | 2.read | 3.clear | 4.exit");
		System.out.print("메뉴 선택 : ");
	}

	// OK 서브메뉴 메소드
	public void okSubMenu(){
		System.out.println("-----------------------------------------------------------------");
		System.out.println("보조 메뉴 : 1.Ok | 2.Cancel");
		System.out.print("메뉴 선택 : ");
	}

	// read의 서브메뉴 메소드
	public void readSubMenu(){
		System.out.println("-----------------------------------------------------------------");
		System.out.println("보조 메뉴 : 1.Update | 2.Delete | 3.List");
		System.out.print("메뉴 선택 : ");
	}

	// 종료 메소드
	public void exit(){
		System.out.println();
		System.out.println("게시판 기능을 종료합니다. 이용해주셔서 감사합니다.");
		System.exit(0);
	}

	// 게시판 리스트 출력 형식 메소드
	public void printNowBoard(ArrayList<BoardVO> boardVOList) {

		for(int i = 0; i < boardVOList.size(); i++) {
			// 게시판 목록 형식문자열. 자리맞추기
			System.out.printf("%-6s%-10s%-40s%-40s\n",
					boardVOList.get(i).getBno(),
					boardVOList.get(i).getBwriter(),
					boardVOList.get(i).getBdate(),
					boardVOList.get(i).getBtitle());
		}
	}

	// read의 게시글 출력 형식 메소드
	public void readBnoBoard(ArrayList<BoardVO> boardVOList){
			System.out.println("#######################################");
			System.out.println("번호: " + boardVOList.get(0).getBno());
			System.out.println("제목: " + boardVOList.get(0).getBtitle());
			System.out.println("내용: " + boardVOList.get(0).getBcontent());
			System.out.println("작성자: " + boardVOList.get(0).getBwriter());
			System.out.println("날짜: " + boardVOList.get(0).getBdate());
			System.out.println("#######################################");
	}
}
