import java.util.Scanner;

public class Zadanie2Translit {

	static char[] alfKirAll = { 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п',
			'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д',
			'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш',
			'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', ' ', ',', '.', '!', '-' };

	static String[] alfTransLat = { "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "ii", "k", "l", "m", "n", "o",
			"p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "y", " ", "eh", "ju", "ja", "A", "B", "V",
			"G", "D", "E", "E", "Zh", "Z", "I", "II", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "Ts",
			"Ch", "Sh", "Sch", "", "Y", "", "Eh", "Ju", "Ja", " ", ",", ".", "!", "-" };

	static char[] alfLatin = { 'a', 'b', 'v', 'g', 'd', 'e', 'z', 'i', 'k', 'l', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u',
			'f', 'h', 'y', ' ', ',', '.', '!', '-' };
	static char[] alfKirl = { 'а', 'б', 'в', 'г', 'д', 'е', 'з', 'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у',
			'ф', 'х', 'ы', ' ', ',', '.', '!', '-' };

	public static void main(String[] args) {
		
		//Scanner scan = new Scanner(System.in); // для эклипса
		Scanner scan = new Scanner(System.in , "Cp866"); //для консоли с кодировкой

		// Транслитерация на латиницу
		System.out.println("Enter a string in Cyrillic:");
		String str = scan.nextLine();
		StringBuilder resLat = new StringBuilder(str.length() * 3);
		for (int i = 0; i < str.length(); i++) {
			for (int j = 0; j < alfKirAll.length; j++) {
				if (str.charAt(i) == alfKirAll[j]) {
					int index = j;
					resLat.append(alfTransLat[index]);
					break;
				}
			}
		}
		System.out.println("Result:" + resLat);

		// Транслитерация на кириллицу
		System.out.println("Enter a string in a Latin alphabet:");
		String strLat = scan.nextLine();
		StringBuilder resKir = new StringBuilder(strLat.length());
		int lenStr = strLat.length();

		for (int i = 0; i < lenStr; i++) {
			char symb = strLat.charAt(i);
			boolean reg = Character.isLowerCase(symb);
			symb = Character.toLowerCase(symb);

			if (i != (lenStr - 1)) { // проверка на последний элемент
				switch (symb) {
				case ('j'):
					switch (Character.toLowerCase(strLat.charAt(i + 1))) {
					case 'a':
						resKir.append(setReg('я', reg));
						i++;
						break;
					case 'u':
						resKir.append(setReg('ю', reg));
						i++;
						break;
					default:
						resKir.append(setReg('г', reg));
						break;
					}
					break;
				case ('t'):
					if (Character.toLowerCase(strLat.charAt(i + 1)) == 's') {
						resKir.append(setReg('ц', reg));
						i++;
					} else
						search(symb, resKir, reg);
					break;
				case ('i'):
					if (Character.toLowerCase(strLat.charAt(i + 1)) == 'i') {
						resKir.append(setReg('й', reg));
						i++;
					} else {
						search(symb, resKir, reg);
					}
					break;
				case ('c'):
					if (Character.toLowerCase(strLat.charAt(i + 1)) == 'h') {
						resKir.append(setReg('ч', reg));
						i++;
					} else
						search(symb, resKir, reg);
					break;
				case ('e'):
					if (Character.toLowerCase(strLat.charAt(i + 1)) == 'h') {
						resKir.append(setReg('э', reg));
						i++;
					} else
						search(symb, resKir, reg);
					break;
				case ('z'):
					if (Character.toLowerCase(strLat.charAt(i + 1)) == 'h') {
						resKir.append(setReg('ж', reg));
						i++;
					} else
						search(symb, resKir, reg);
					break;
				case ('s'):
					if (Character.toLowerCase(strLat.charAt(i + 1)) == 'h') {
						resKir.append(setReg('ш', reg));
						i++;
					} else if (i != (lenStr - 2)) {
						if (Character.toLowerCase(strLat.charAt(i + 1)) == 'c'& Character.toLowerCase(strLat.charAt(i + 2)) == 'h') {
							resKir.append(setReg('щ', reg));
							i = i + 2;
						}else
							search(symb, resKir, reg);break;
					} else
						search(symb, resKir, reg);
					break;
				default:
					search(symb, resKir, reg);
					break;
				}
			} else {
				search(symb, resKir, reg);
			}
		}
		System.out.println("Результат:" + resKir);
		scan.close();
	}

	//поиск по массиву символов
	public static void search(char symb, StringBuilder str, boolean reg) {
		for (int j = 0; j < alfLatin.length; j++) {
			if (symb == alfLatin[j]) {
				int index = j;
				str.append(setReg(alfKirl[index], reg));
			}
		}
	}

	//установление регистра
	public static char setReg(char symb, boolean lowercase) {
		if (lowercase == false) {
			symb = Character.toUpperCase(symb);
		}
		return symb;
	}
}
