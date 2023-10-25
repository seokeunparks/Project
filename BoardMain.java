import java.util.ArrayList;
import java.util.Scanner;

// Board.java에서 정의한 함수를 기반으로 실행될 것 작성
public class BoardMain {

	public static void main(String[] args) {

		// 생성자
		DBUtil db = new DBUtil(); // 실행 기지에 사용될 변수 db
		DBUtil nowBoard = new DBUtil(); // 게시판 출력에 사용될 변수 nowBoard
		Scanner scan = new Scanner(System.in); // 사용자 입력 값에 사용될 변수 scan

		// Mybatis 연동 확인. 최초 실행
		db.init();

		// 게시판 틀 출력
		db.homeBoard();

		// 게시판 목록 출력
		ArrayList<BoardVO> boardList = db.getBoard();
		nowBoard.printNowBoard(boardList);

		// 메인메뉴 출력
		db.mainMenu();


		while(true) {
			// 지역변수 선언
			String cmd = scan.nextLine(); // 사용자가 Enter를 입력했을 때 값을 cmd에 대입

			// 종료
			if (cmd.equals("4")) {
				db.exit();


			}
			// 게시판 작성 기능 실행(CREATE)
			else if (cmd.equals("1") || cmd.equals("create")) {
				System.out.println("[새 게시물 입력]");
				System.out.print("제목: ");
				// 사용자가 입력한 제목 값을 btitle 변수에 대입
				String btitle = scan.nextLine();
				System.out.print("내용: ");
				// 사용자가 입력한 내용 값을 bcontent 변수에 대입
				String bcontent = scan.nextLine();
				System.out.print("작성자: ");
				// 사용자가 입력한 작성자 값을 bwriter 변수에 대입
				String bwriter = scan.nextLine();

				// OK 하위 메뉴 출력
				db.okSubMenu();

				// switch-case문에서 사용자 Enter 입력값을 받을 변수 createSubMenuNo 정의
				String createSubMenuNo = scan.nextLine();

				// CREATE의 OK 하위 메뉴 실행
				switch (createSubMenuNo) {
					case "1":
						// DB를 연동한 CREATE 함수 실행 (파라미터 btitle, bcontent, bwriter가 필요하다)
						db.insertBoard(btitle, bcontent, bwriter);
						System.out.println("[게시물 등록 완료]");
						break;

					case "2":
						System.out.println("[게시물 등록 취소]");
						break;
				}

				// 게시판 최신화
				boardList = db.getBoard();

				// 게시판 틀 출력
				db.homeBoard();

				// 게시판 목록 출력
				nowBoard.printNowBoard(boardList);

				// 메인 메뉴 출력
				db.mainMenu();

			} 
			// 2.read를 입력했을 때 수행
			else if(cmd.equals("2")){
				System.out.println("[게시물 읽기]");
				System.out.print("bno: ");

				// 사용자 입력 값(bno)를 bno 변수에 대입
				int bno = Integer.parseInt(scan.nextLine());
				// DB를 연동한 READ 함수 실행 (파라미터 bno가 필요하다.)
				// READ 함수 실행 결과를 boardList 변수에 대입
				boardList = db.readBoard(bno);
				// boardList를 화면에 출력할 함수 실행 (형식문자열 함수)
				db.readBnoBoard(boardList);

				// read 함수의 서브메뉴 출력
				db.readSubMenu();
				// switch-case문에서 사용자 Enter 입력값을 받을 변수 readSubMenuNo 정의
				String readSubMenuNo = scan.nextLine();

				// READ의 하위 메뉴 시작
				switch (readSubMenuNo){
					// 1. Update 를 입력했을 때 수행
					case "1" :
						System.out.println("[수정 내용 입력]");
						System.out.print("제목 : ");
						// 사용자가 입력한 제목 값을 btitle 변수에 대입
						String btitle = scan.nextLine();
						System.out.print("내용 : ");
						// 사용자가 입력한 내용 값을 bcontent 변수에 대입
						String bcontent = scan.nextLine();
						System.out.print("작성자 : ");
						// 사용자가 입력한 작성자 값을 bwriter 변수에 대입
						String bwriter = scan.nextLine();

						// OK 하위 메뉴 출력
						db.okSubMenu();
						// switch-case문에서 사용자 Enter 입력값을 받을 변수 createSubMenuNo 정의
						String createSubMenuNo = scan.nextLine();

						switch (createSubMenuNo) {
							// 1.OK 했을 때 update 실행
							case "1":
								// DB를 연동한 Update 함수 실행 (파라미터: bno, btitle, bcontent, bwriter)
								db.updateBoard(bno, btitle, bcontent, bwriter);
								System.out.println("[게시물 수정 완료]");
								break;

							case "2":
								// 2.Cancle 했을 때 READ 함수 벗어나기
								System.out.println("[게시물 수정 취소]");
								break;
						}
						break;

					// 2. Delete 를 입력했을 때 수행
					case "2" :
						System.out.println("정말로 " + bno + "번째 게시물을 삭제하시겠습니까?");
						// OK 하위 메뉴 출력
						db.okSubMenu();
						// switch-case문에서 사용자 Enter 입력값을 받을 변수 deleteSubMenuNo 정의
						String deleteSubMenuNo = scan.nextLine();
						switch (deleteSubMenuNo) {
							// 1.OK를 입력했을 때 수행
							case "1":
								// DB를 연동한 DELETE 수행
								db.deleteBoard(bno);
								System.out.println(bno + "번째 게시물 삭제 완료");
								break;

							// 2.Cancle을 입력했을 때 수행
							case "2":
								System.out.println("게시물 삭제 취소");
								break;
						}
						// 2.read를 벗어나는 break;
						break;

					// 3.List를 입력했을 때 수행
					case "3" :
						break;
				}

				// 게시판 최신화
				boardList = db.getBoard();

				// 게시판 틀 출력
				db.homeBoard();

				// 게시판 목록 출력
				nowBoard.printNowBoard(boardList);

				// 메인 메뉴 출력
				db.mainMenu();

			}
			// 3. Clear 입력 시 전체 삭제 아래 수행
			else if (cmd.equals("3")) {

				// ok 하위 메뉴 출력
				db.okSubMenu();
				// switch-case문에서 사용자 Enter 입력값을 받을 변수 createSubMenuNo 정의
				String createSubMenuNo = scan.nextLine();

				switch (createSubMenuNo) {
					// 1. OK 입력 시 수행
					case "1":
						// DB를 연동한 DELETE
						db.clearBoard();
						System.out.println("[게시물 전체 삭제 완료]");
						break;

					case "2":
						System.out.println("[게시물 전체 삭제 취소]");
						break;
				}
				// 게시판 최신화
				boardList = db.getBoard();

				// 게시판 틀 출력
				db.homeBoard();

				// 게시판 목록 출력
				nowBoard.printNowBoard(boardList);

				// 메인 메뉴 출력
				db.mainMenu();
			}
		}
	}
}