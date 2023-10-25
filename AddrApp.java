// 필요한 패키지와 클래스를 가져옵니다.
import java.util.ArrayList;
import java.util.Scanner;

// "AddrApp"이라는 클래스를 정의합니다.
public class AddrApp {

	// 메인 메서드를 정의합니다. 프로그램이 실행될 때 이 메서드가 먼저 호출됩니다.
	public static void main(String[] args) {

		// 데이터베이스와 연결하기 위한 객체를 생성합니다.
		DBUtil db = new DBUtil();
		// 사용자로부터 입력을 받기 위한 스캐너 객체를 생성합니다.
		Scanner scan = new Scanner(System.in);

		// 무한 반복문을 시작합니다. 사용자가 "exit" 명령어를 입력할 때까지 계속 실행됩니다.
		while(true) {
			// 사용자에게 명령어를 입력하라는 메시지를 출력합니다.
			System.out.print("명령어를 입력해주세요 : ");
			// 사용자로부터 입력받은 명령어를 저장합니다.
			String cmd = scan.nextLine();

			// 사용자가 "exit"를 입력하면 반복문을 종료합니다.
			if(cmd.equals("exit")) {
				break;

				// 사용자가 "add"를 입력하면 주소록을 추가하는 과정을 시작합니다.
			} else if(cmd.equals("add")) {
				// 주소록 등록 과정을 알리는 메시지를 출력합니다.
				System.out.println("========= 주소록 등록 =========");
				// 사용자에게 이름, 주소, 전화번호를 입력받습니다.
				System.out.print("이름 : ");
				String name = scan.nextLine();
				System.out.print("주소 : ");
				String address = scan.nextLine();
				System.out.print("전화번호 : ");
				String phone = scan.nextLine();

				// 입력받은 정보를 데이터베이스에 저장합니다.
				db.insertAddress(name, address, phone);

				// 주소록 등록 완료 메시지를 출력합니다.
				System.out.println("주소록 등록 완료.");
				System.out.println("============================");

				// 사용자가 "list"를 입력하면 주소록 목록을 출력합니다.
			} else if(cmd.equals("list")) {

				// 데이터베이스에서 주소록 목록을 가져옵니다.
				ArrayList<Addr> addrList = db.getAddresses();
				// 웹 뷰 객체를 생성하여 주소록 목록을 출력합니다.
				WebView wv = new WebView();
				wv.printAddr(addrList);

				// 사용자가 "update"를 입력하면 주소록을 수정하는 과정을 시작합니다.
			} else if(cmd.equals("update")) {
				// 수정할 주소록의 번호를 입력받습니다.
				System.out.print("몇번 주소록을 수정하시겠습니까 : ");
				int id = Integer.parseInt(scan.nextLine());
				// 주소록 수정 과정을 알리는 메시지를 출력합니다.
				System.out.println("========= 주소록 수정 =========");
				// 사용자에게 이름, 주소, 전화번호를 입력받습니다.
				System.out.print("이름 : ");
				String name = scan.nextLine();
				System.out.print("주소 : ");
				String address = scan.nextLine();
				System.out.print("전화번호 : ");
				String phone = scan.nextLine();

				// 입력받은 정보로 데이터베이스의 주소록을 수정합니다.
				db.updateAddress(id, name, address, phone);

				// 주소록 수정 완료 메시지를 출력합니다.
				System.out.println("주소록 수정 완료.");
				System.out.println("============================");

				// 사용자가 "delete"를 입력하면 주소록을 삭제하는 과정을 시작합니다.
			} else if(cmd.equals("delete")) {
				// 삭제할 주소록의 번호를 입력받습니다.
				System.out.print("몇번 주소록을 삭제하시겠습니까 : ");
				int id = Integer.parseInt(scan.nextLine());
				// 입력받은 번호의 주소록을 데이터베이스에서 삭제합니다.
				db.deleteAddress(id);
				// 주소록 삭제 완료 메시지를 출력합니다.
				System.out.println(id + "번 주소록이 삭제되었습니다.");
				System.out.println("==============================");
			}
		}
	}
}
