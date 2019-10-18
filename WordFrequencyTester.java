
import java.util.ArrayList;
import java.util.List;

//HANNAH FISHER

public class WordFrequencyTester {
	
	//READTEXT FUNCTION
	//given a "file_name" of type String
	//read the file and add every word to a list
	//return a list of all the words in the entire file
	private static List<String> read_text(String file_name){ 
		List<String> word_list = new ArrayList<String>(); 
		SimpleFile file = new SimpleFile(file_name);
		for (String line : file){
			for (String word: line.split(" ")){
				word = word.trim();
				if (word.length() > 0){
					word_list.add(word);
				}
			}
		}
		return word_list;
	}
	
	//UNIGRAM FUNCTION
	private static void run_unigram(String file_name, int num_display_common, int num_display_random){
		System.out.println("UNIGRAM");
		WordTable word_table = new WordTable();
		List<String> word_list = read_text(file_name);
		System.out.println("Reading " + file_name);
		for (String word: word_list){ //for each word in the list, add it to the map
			word_table.recordWord(word);
		}
		word_table.processEntries();
		System.out.println("MOST COMMON WORDS: ");
		word_table.displayCommonWords(num_display_common, 1);
		System.out.println("RANDOMLY GENERATED TEXT: ");
		for (int i = 0; i < num_display_random; i += 1){
			WordEntry word_entry = word_table.randomEntry();
			System.out.print(word_entry.get_word() + " ");
		}
		System.out.println();
		System.out.println();
	}
	
	//BIGRAM FUNCTION
	private static void run_bigram(String file_name, int num_display_common, int num_display_random){
		System.out.println("BIGRAM");
		WordTable word_table = new WordTable();
		List<String> word_list = read_text(file_name);
		System.out.println("Reading " + file_name);
		String prev_word = null;
		for (String word: word_list){ 
			word_table.recordWord(word);
			if (prev_word != null){
				word_table.findEntry(prev_word).get_word_table().recordWord(word);
			}
			prev_word = word;
		}
		word_table.processEntries();
		System.out.println("MOST COMMON WORDS: ");
		word_table.displayCommonWords(num_display_common, 2); 
		System.out.println("RANDOMLY GENERATED TEXT: ");
		WordEntry word_on = word_table.randomEntry(); //gonna end up not even printing this word, but thats cool
		for (int i = 0; i < num_display_random; i += 1){
			WordEntry next_random_word = word_on.get_word_table().randomEntry();
			WordEntry good_next_random_word = word_table.findEntry(next_random_word.get_word());
			System.out.print(good_next_random_word.get_word() + " ");
			word_on = good_next_random_word; 
		} 
		System.out.println();
		System.out.println();
	}
	
	//TRIGRAM FUNCTION
	private static void run_trigram(String file_name, int num_display_common, int num_display_random){
		System.out.println("TRIGRAM");
		WordTable word_table = new WordTable();
		List <String> word_list = read_text(file_name);
		System.out.println("Reading " + file_name);
		String prev_word = null;
		String prev_prev_word = null;
		for (String word: word_list){
			word_table.recordWord(word);
			if (prev_word != null){
				word_table.findEntry(prev_word).get_word_table().recordWord(word); 
			}
			if (prev_prev_word != null ){
				word_table.findEntry(prev_prev_word).get_word_table().findEntry(prev_word).get_word_table().recordWord(word);
			}
			prev_prev_word = prev_word;
			prev_word = word;
		}
		word_table.processEntries();
		System.out.println("MOST COMMON WORDS: ");
		word_table.displayCommonWords(num_display_common, 3);
		System.out.println("RANDOMLY GENERATED TEXT: ");
		WordEntry word_on = word_table.randomEntry();
		WordEntry next_word_on = word_on.get_word_table().randomEntry();
		for (int i = 0; i < num_display_random; i += 1){
			WordEntry next_random_word = next_word_on.get_word_table().randomEntry(); 
			System.out.print(next_random_word.get_word() + " ");
			word_on = word_table.findEntry(next_word_on.get_word());
			next_word_on = word_on.get_word_table().findEntry(next_random_word.get_word());
		}
		System.out.println();
		System.out.println();
	} 
	
	
	//N GRAM FUNCTION
	private static void run_n_gram(String file_name, int num_display_common, int num_display_random, int degree){
		System.out.println(degree + " GRAM");
		WordTable word_table = new WordTable();
		List <String> word_list = read_text(file_name);
		System.out.println("Reading " + file_name);
		int current_index = 0;
		for (String word : word_list){
			word_table.recordWord(word);
			WordEntry word_on = word_table.findEntry(word);
			for (int i = 1; i < degree ; i += 1){
				if (current_index + i < word_list.size()){
					String next_word = word_list.get(current_index + i);
					word_on.get_word_table().recordWord(next_word);
					word_on = word_on.get_word_table().findEntry(next_word);
				}
			}
			current_index += 1;
		}
		word_table.processEntries();
		word_table.displayCommonWords(num_display_common, degree);
		
		//DISPLAY RANDOM WORDS (BUMMER 'CAUSE I HAVEN'T WRITTEN THIS)

	}

	public static void main(String[] args) {
		//Available text files:
			// shakespeare.txt
			// state-of-the-union-1790-2006.txt
		//Available functions:
			//run_unigram(file_name, num_display_common, num_display_random);
			//run_bigram(file_name, num_display_common, num_display_random);
			//run_trigram(file_name, num_display_common, num_display_random);
			//run_n_gram(file_name, num_display_common, num_display_random, degree);
			//NOTE: n_gram function does not contain functional display random words code
		
		run_unigram("shakespeare.txt", 25, 10);
		run_bigram("shakespeare.txt", 5, 500);
		run_trigram("state-of-the-union-1790-2006.txt", 3, 200); 
		run_n_gram("shakespeare.txt", 5, 100, 5);
		
	}

}

