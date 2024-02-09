
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		if(str.length() == 0){
			return "";
		}
		else{
			return str.substring(1, str.length());
		}
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase();
		int a = word1.length();
		int b = word2.length();
		if(b==0){return a;}
		// redundant else
		else{
			if(a==0){return b;}
			// redundant else
			else{
				if(word1.charAt(0) == word2.charAt(0)){
				return levenshtein(tail(word1), tail(word2));}
				// redundant else
				else{
					int i = levenshtein(tail(word1), word2);
					int j = levenshtein(word1, tail(word2));
					int k = levenshtein(tail(word1), tail(word2));
					return 1 + Math.min(i, Math.min(j, k));
				}
			
		}
	}
	/* redundant else means that when you do return, the function stops,
 	so the only way to continue the function run is if the condition was false wich is what else stands for.
  	so there is no need for the else.	
   	when you see so many if-else inside if-else, this should be a red flag that it could be done better.
   	*/

	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for(int i = 0; i < dictionary.length; i++){
			String str = in.readLine();
			dictionary[i] = str;
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = 100000;
		String output = "";
		for(int i = 0; i < dictionary.length; i++){
			int j = levenshtein(word, dictionary[i]);
			if(j < min){
				min = j;
				output = dictionary[i];
			}

		}
		
		if(min > threshold){
			return word;
		}
		else{
			return output;
		}
	}

}
