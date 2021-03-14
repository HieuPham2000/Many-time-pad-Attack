package common;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	private static final int MIN_LEN = 83;
	
	/*
	 * Phân tách bản mã (dạng chuỗi hệ 16) theo từng cụm 2 ký tự (tương đương 8 bit)
	 * Trả về List chứa phần tử là số hệ 10 tương ứng
	 * VD: Input là "315c" => phân tách thành "31" và "5c" => chuyển về hệ 10 => lưu vào List
	 */
	public static List<Integer> hexStringToListIntegers(String cyphertext) {
		
		List<Integer> listIntegers = new ArrayList<Integer>();
		
		for(int i = 0; i < cyphertext.length(); i += 2) {
			// Viết "" ở vị trí đầu tiên => chương trình hiểu là phép cộng String
			String subString = "" + cyphertext.charAt(i) + cyphertext.charAt(i+1);
			Integer element = Integer.parseInt(subString, 16);
			listIntegers.add(element);
		}
		return listIntegers;
	}
	
	
	
	/*
	 * Đầu vào là List chứa mã ASCII của các ký tự
	 * Chuyển mã ASCII sang ký tự chữ cái [a-zA-Z] nếu được
	 */
	public static List<Character> listIntegersToListCharacters(List<Integer> listIntegers) {
		List<Character> listCharacters = new ArrayList<Character>();
		for(int i: listIntegers) {
			
			if((i >= 'A' && i <= 'Z') || (i >= 'a' && i <= 'z')) {
				listCharacters.add((char)i);
			} else {
				listCharacters.add('*');
			}
			
		}
		return listCharacters;
	}
	
	
	/*
	 * Chuyển List<Integer> chứa mã ASCII sang String
	 */
	public static String listIntegerstoText(List<Integer> listIntegers) {
		StringBuffer res = new StringBuffer("");
		for(int i: listIntegers) {
			res.append((char)i);
		}
		return res.toString();
	}
	/*
	 * Chuyển List<Character> sang String
	 */
	public static String listCharacterstoText(List<Character> listCharacters) {
		StringBuffer res = new StringBuffer("");
		for(char i: listCharacters) {
			res.append(i);
		}
		return res.toString();
	}
	
	
	/*
	 * XOR 2 bản mã (bản mã trước đó đã chuyển về List<Integer>)
	 * XOR từ đầu đến cuối
	 * Lấy theo độ dài List ngắn nhất
	 */
	public static List<Integer> listXOR(List<Integer> list1, List<Integer> list2) {
		
		int len = Math.min(list1.size(), list2.size());
		
		List<Integer> res = new ArrayList<Integer>();
		
		for(int i = 0; i < len; i++) {
			res.add(list1.get(i) ^ list2.get(i));
		}
		
		return res;
	}
	
	
	/*
	 * Chuyển plain text sang dạng List<Integer>
	 * Mỗi phần tử của List có giá trị là mã ASCII (hệ 10) tương ứng của ký tự trong plain text
	 */
	public static List<Integer> textToListIntegers(String plaintext) {
		
		List<Integer> listIntegers = new ArrayList<Integer>();
		
		for(int i = 0; i < plaintext.length(); i++) {
			listIntegers.add((int)plaintext.charAt(i));
		}
		
		return listIntegers;
	}
	
	
	/*
	 * Tìm key (XOR plaintext với cyphertext)
	 * Giả định: plaintext dạng text, cyphertext dạng chuỗi hexa
	 * Key trả về dạng List<Integer> (chuỗi hexa đã được phân tách và chuyển về hệ 10)
	 */
	public static List<Integer> findKey(String plaintext, String cyphertext) {
		List<Integer> listPlainText = textToListIntegers(plaintext);
		List<Integer> listCypherText = hexStringToListIntegers(cyphertext);
		
		List<Integer> key = listXOR(listPlainText, listCypherText);
		return key;
	}
	
	
	/*
	 * Tìm vị trí của các ký tự space
	 */
	public static List<Integer> findListSpaceIndex(List<Integer> list[], int index) {
		List<Integer> listSpaceIndex = new ArrayList<Integer>();
		
		int len = Utils.MIN_LEN;
		int countCharacterArray[] = new int[len];
		for(int i = 0; i < len; ++i) {
			countCharacterArray[i] = 0;
		}
		
		for(int i = 1; i <= 11; ++i) {
			// XOR
			if(i == index) {
				continue;
			}
			
			for(int j = 0; j < len; ++j) {
				int tmp = list[i].get(j) ^ list[index].get(j);
				if((tmp >= 'A' && tmp <= 'Z') || (tmp >= 'a' && tmp <= 'z')) {
					countCharacterArray[j] ++;
				}
			}			
		}
		
	
		for(int i = 0; i < len; ++i) {
			if(countCharacterArray[i] >= 6) {
				listSpaceIndex.add(i);
			}
		}
		
		return listSpaceIndex;
	}
}
