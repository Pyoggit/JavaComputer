package kr.co.address_map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class AddressMain {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        HashMap<String, Address> list = new HashMap<>();
        boolean exitFlag = false;

        while (!exitFlag) {
            showMenu();
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case Menu.INPUT:
                    int type = inputType();
                    insertAddress(list, type);
                    break;
                case Menu.SEARCH:
                    System.out.print("찾을 사람의 우편번호를 입력하세요: ");
                    String zipcodeToSearch = sc.nextLine();
                    Address addr = searchAddress(list, zipcodeToSearch);
                    System.out.println((addr != null) ? (addr) : ("찾는 우편번호가 없습니다."));
                    break;
                case Menu.DELETE:
                    System.out.print("삭제할 우편번호를 입력하세요: ");
                    String zipcodeToDelete = sc.nextLine();
                    boolean deleteFlag = deleteAddress(list, zipcodeToDelete);
                    System.out.println((deleteFlag) ? ("삭제 성공") : ("삭제 실패: 우편번호 확인 필요"));
                    break;
                case Menu.UPDATE:
                    System.out.print("수정할 우편번호를 입력하세요: ");
                    String zipcodeToUpdate = sc.nextLine();
                    updateData(list, zipcodeToUpdate);
                    break;
                case Menu.PRINT:
                    printAddress(list);
                    break;
                case Menu.SORT:
                    sortAddress(list);
                    break;
                case Menu.REVERSESORT:
                    reverseSortAddress(list);
                    break;
                case Menu.EXIT:
                    System.out.println("프로그램 종료");
                    exitFlag = true;
                    break;
                default:
                    break;
            }
        }
        System.out.println("프로그램 종료");
    }

    public static void showMenu() {
        System.out.println("고객의 배송지 설정_Set");
        System.out.println("1. 주소 입력");
        System.out.println("2. 주소 검색");
        System.out.println("3. 주소 삭제");
        System.out.println("4. 주소 수정");
        System.out.println("5. 주소 출력");
        System.out.println("6. 주소 정렬");
        System.out.println("7. 주소 역정렬");
        System.out.println("8. 프로그램 종료");
        System.out.print("선택: ");
    }

    public static int inputType() {
        System.out.println("1. 일반 주소, 2. 회사 주소");
        System.out.print("선택: ");
        return sc.nextInt();
    }

    public static void insertAddress(HashMap<String, Address> list, int type) {
        String name = makeName();
        String city = makeCity();
        String detailedAddress = makeDetailedAddress();
        String zipcode = makeZipcode();
        String companyName = makeCompanyName();
        
        switch (type) {
            case 1:
                Address address = new Address(name, city, detailedAddress, zipcode);
                if (list.put(zipcode, address) != null) {
                    System.out.println("이미 존재하는 우편번호입니다.");
                }
                System.out.println(address);
                break;
            case 2:
                CompanyAddress companyAddress = new CompanyAddress(name, city, detailedAddress, zipcode, companyName);
                if (list.put(zipcode, companyAddress) != null) {
                    System.out.println("이미 존재하는 우편번호입니다.");
                }
                System.out.println(companyAddress);
                break;
        }
    }

    public static Address searchAddress(HashMap<String, Address> list, String zipcode) {
        return list.get(zipcode);
    }

    public static boolean deleteAddress(HashMap<String, Address> list, String zipcode) {
        return list.remove(zipcode) != null;
    }

    public static void updateData(HashMap<String, Address> list, String zipcode) {
        Address address = list.get(zipcode);
        if (address != null) {
            System.out.println("1. 이름");
            System.out.println("2. 지역");
            System.out.println("3. 상세 주소");
            System.out.println("4. 우편번호");
            System.out.println("수정할 필드를 선택하세요:");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("새로운 이름: ");
                    address.setName(sc.nextLine());
                    break;
                case 2:
                    System.out.print("새로운 도시: ");
                    address.setCity(sc.nextLine());
                    break;
                case 3:
                    System.out.print("새로운 상세 주소: ");
                    address.setDetailedAddress(sc.nextLine());
                    break;
                case 4:
                    System.out.print("새로운 우편번호: ");
                    String newZipcode = sc.nextLine();
                    list.remove(zipcode);
                    address.setZipcode(newZipcode);
                    list.put(newZipcode, address);
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
            System.out.println("주소 수정 완료: " + address);
        } else {
            System.out.println("해당 우편번호로 등록된 주소가 없습니다.");
        }
    }

    public static void printAddress(HashMap<String, Address> addressList) {
        Set<String> keys = addressList.keySet();
        for (String key : keys) {
            System.out.println(addressList.get(key));
        }
    }

    public static void sortAddress(HashMap<String, Address> list) {
        List<Address> arrayList = new ArrayList<>(list.values());
        Collections.sort(arrayList);
        for (Address data : arrayList) {
            System.out.println(data);
        }
    }

    public static void reverseSortAddress(HashMap<String, Address> list) {
        List<Address> arrayList = new ArrayList<>(list.values());
        Collections.sort(arrayList, (a1, a2) -> a2.getZipcode().compareTo(a1.getZipcode()));
        for (Address data : arrayList) {
            System.out.println(data);
        }
    }
    
	public static String makeName() {
		List<String> 성 = Arrays.asList("김", "이", "박", "최", "정", "강", "조", "윤", "장", "임", "한", "오", "서", "신", "권", "황",
				"안", "송", "류", "전", "홍", "고", "문", "양", "손", "배", "조", "백", "허", "유", "남", "심", "노", "정", "하", "곽", "성",
				"차", "주", "우", "구", "신", "임", "나", "전", "민", "유", "진", "지", "엄", "채", "원", "천", "방", "공", "강", "현", "함",
				"변", "염", "양", "변", "여", "추", "노", "도", "소", "신", "석", "선", "설", "마", "길", "주", "연", "방", "위", "표", "명",
				"기", "반", "왕", "금", "옥", "육", "인", "맹", "제", "모", "장", "남", "탁", "국", "여", "진", "어", "은", "편", "구",
				"용");
		List<String> 이름 = Arrays.asList("가", "강", "건", "경", "고", "관", "광", "구", "규", "근", "기", "길", "나", "남", "노", "누",
				"다", "단", "달", "담", "대", "덕", "도", "동", "두", "라", "래", "로", "루", "리", "마", "만", "명", "무", "문", "미", "민",
				"바", "박", "백", "범", "별", "병", "보", "빛", "사", "산", "상", "새", "서", "석", "선", "설", "섭", "성", "세", "소", "솔",
				"수", "숙", "순", "숭", "슬", "승", "시", "신", "아", "안", "애", "엄", "여", "연", "영", "예", "오", "옥", "완", "요", "용",
				"우", "원", "월", "위", "유", "윤", "율", "으", "은", "의", "이", "익", "인", "일", "잎", "자", "잔", "장", "재", "전", "정",
				"제", "조", "종", "주", "준", "중", "지", "진", "찬", "창", "채", "천", "철", "초", "춘", "충", "치", "탐", "태", "택", "판",
				"하", "한", "해", "혁", "현", "형", "혜", "호", "홍", "화", "환", "회", "효", "훈", "휘", "희", "운", "모", "배", "부", "림",
				"봉", "혼", "황", "량", "린", "을", "비", "솜", "공", "면", "탁", "온", "디", "항", "후", "려", "균", "묵", "송", "욱", "휴",
				"언", "령", "섬", "들", "견", "추", "걸", "삼", "열", "웅", "분", "변", "양", "출", "타", "흥", "겸", "곤", "번", "식", "란",
				"더", "손", "술", "훔", "반", "빈", "실", "직", "흠", "흔", "악", "람", "뜸", "권", "복", "심", "헌", "엽", "학", "개", "롱",
				"평", "늘", "늬", "랑", "얀", "향", "울", "련");
		Collections.shuffle(성);
		Collections.shuffle(이름);
		return 성.get(0) + 이름.get(0) + 이름.get(1);
	}

	public static String makeCity() {
		List<String> cities = Arrays.asList("서울", "경기도", "강원도", "충청도", "경상도", "전라도", "제주도");
		Collections.shuffle(cities);
		return cities.get(0);
	}

	public static String makeDetailedAddress() {
		int randomStreetNumber = (int) (Math.random() * 100) + 1;
		return randomStreetNumber + "번지";
	}

	public static String makeZipcode() {
		return String.valueOf((int) (Math.random() * (90000) + 10000));
	}

	public static String makeCompanyName() {
		String[] companies = { "삼성", "LG", "네이버", "카카오", "구글", "애플" };
		return companies[(int) (Math.random() * companies.length)];
	}
}