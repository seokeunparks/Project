// "Addr"이라는 클래스를 정의합니다.
public class Addr {
	// 주소록의 번호, 이름, 주소, 전화번호 정보를 저장하는 변수들을 선언합니다.
	private int id;
	private String name;
	private String address;
	private String phone;

	// "Addr" 클래스의 생성자를 정의합니다. 객체를 생성할 때 번호, 이름, 주소, 전화번호 정보를 받아 초기화합니다.
	public Addr(int id, String name, String address, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	// 번호 정보를 가져오는 메서드입니다.
	public int getId() {
		return id;
	}
	// 번호 정보를 설정하는 메서드입니다.
	public void setId(int id) {
		this.id = id;
	}
	// 이름 정보를 가져오는 메서드입니다.
	public String getName() {
		return name;
	}
	// 이름 정보를 설정하는 메서드입니다.
	public void setName(String name) {
		this.name = name;
	}
	// 주소 정보를 가져오는 메서드입니다.
	public String getAddress() {
		return address;
	}
	// 주소 정보를 설정하는 메서드입니다.
	public void setAddress(String address) {
		this.address = address;
	}
	// 전화번호 정보를 가져오는 메서드입니다.
	public String getPhone() {
		return phone;
	}
	// 전화번호 정보를 설정하는 메서드입니다.
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
