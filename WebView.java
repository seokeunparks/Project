// 필요한 패키지를 가져옵니다.
import java.util.ArrayList;

// "WebView"라는 클래스를 정의합니다.
public class WebView {

	// "printAddr"라는 메서드를 정의합니다. 이 메서드는 주소록 목록을 웹 브라우저에 출력하는 역할을 합니다.
	public void printAddr(ArrayList<Addr> addrList) {

		// "웹 브라우저에 출력"이라는 문장을 화면에 출력합니다.
		System.out.println("웹 브라우저에 출력");

		// 주소록 목록의 각 항목을 순회하면서 화면에 출력합니다.
		for(int i = 0; i < addrList.size(); i++) {
			// 각 주소록 항목의 시작을 알리는 구분선을 출력합니다.
			System.out.println("========= 주소록 목록 =========");

			// 주소록의 번호를 출력합니다.
			System.out.println("번호 : " + addrList.get(i).getId());
			// 주소록의 이름을 출력합니다.
			System.out.println("이름 : " + addrList.get(i).getName());
			// 주소록의 주소를 출력합니다.
			System.out.println("주소 : " + addrList.get(i).getAddress());
			// 주소록의 전화번호를 출력합니다.
			System.out.println("번호 : " + addrList.get(i).getPhone());

			// 각 주소록 항목의 끝을 알리는 구분선을 출력합니다.
			System.out.println("=============================");
		}
	}
}
