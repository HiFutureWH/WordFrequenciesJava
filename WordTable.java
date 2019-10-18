
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

//HANNAH FISHER

public class WordTable {
	private Map<String, WordEntry> word_map;
	private List<WordEntry> word_list;

	//CONSTRUCTOR FUNCTION
	//given nothing
	//creates a new WordTable object
	//returns nothing
	public WordTable(){ 
		word_map = new HashMap<String, WordEntry>(); //IS THIS WHAT I WANT?
		word_list = new ArrayList<WordEntry>(); //IS THIS WHAT I WANT?
	}

	//RECORDWORD FUNCTION
	//given a "word" of type String
	//adds the given word to a master word map by editing its WordEntry
	//returns the WordEntry that corresponds to the given word
	public WordEntry recordWord(String word){
		if (word_map.containsKey(word)){
			word_map.get(word).add_freq(); 
		}
		else{
			word_map.put(word, new WordEntry(word));
		}
		return word_map.get(word);
	}

	//FINDENTRY FUNCTION
	//given a "word" of type String
	//does nothing
	//returns the WordEntry that corresponds to the given word
	public WordEntry findEntry(String word){
		return word_map.get(word); //should just return null if get returns null, right?
	}

	//PROCESSENTRIES FUNCTION - RECURSIVE
	//given nothing
	//adds all of the values to a list, sorts them by frequency, and computes their probabilities and cumulative probabilities
	//returns nothing
	public void processEntries(){
		for (WordEntry value : word_map.values()){
			word_list.add(value);
		}
		QuickSort quick_sorter = new QuickSort();
		word_list = quick_sorter.Sort(word_list);
		double total_count = 0;
		for (WordEntry word: word_list){
			total_count += word.get_frequency();
		}
		double prob_sum = 0;
		for (WordEntry word : word_list){
			double word_count = word.get_frequency();
			double prob = word_count / total_count; 
			word.set_prob(prob);
			prob_sum = word.get_prob() + prob_sum;
			word.set_cumulative_prob(prob_sum);
		}
		for (WordEntry word : word_list){
			word.get_word_table().processEntries();
		}
	}

	//RANDOMENTRY FUNCTION
	//given nothing
	//gets a random WordEntry from the list using cumulative probability
	//returns the WordEntry
	public WordEntry randomEntry(){
		Random generator = new Random();
		double random_num = generator.nextDouble();
		for (WordEntry word : word_list){
			if (word.get_cumulative_prob() >= random_num){
				return word;
			}
		}
		return null; //HELP WHAT SHOULD I DO HERE?
	}

	//GET COMMON WORDS (PRIVATE FUNCTION)
	//given a "number" of type int
	//create a list with that number of the most common word entries from the word_list of this object
	//return that list of common word entries 
	private List<WordEntry> get_common_words(int number){
		List<WordEntry> word_entry_list = new ArrayList<WordEntry>(number);
		int num = 0;
		for (WordEntry word : word_list){
			word_entry_list.add(word);
			num += 1;
			if (num >= number){
				break;
			}
		}
		return word_entry_list;
	}
	
	//DISPLAY COMMON WORDS HELPER (PRIVATE FUNCTION) - RECURSIVE
	//given a "number" of type int for how many numbers to display, a "degree" of type int for what degree it is currently on, and a "max degree" of type int for what degree it could be on 
	//displays the ommon words at all the levels by printing them
	//returns nothing
	private void displayCommonWords_helper(int number, int degree, int max_degree){
		if (degree > 0){
			//get the most common word list of the current wordTable
			List<WordEntry> common_words = get_common_words(number);
			//print a word, 
			for (WordEntry word : common_words){
				String thing_to_print = "";
				//gonna subtract degree from max_degree then multiply by spaces
				String spaces_to_print = "";
				for (int i = 0; i <= (max_degree - degree); i += 1){
					spaces_to_print += "   ";
				}
				thing_to_print += spaces_to_print;
				thing_to_print += word;
				if (degree > 1){
					thing_to_print += "\n" + spaces_to_print + "After " + word.get_word() + " : ";
				}
				System.out.println(thing_to_print);
				//WHAT I AM DOING: print spaces, print word, print "after word:" 
				word.get_word_table().displayCommonWords_helper(number, degree - 1, max_degree);
				//^^run the function on the next degree level
			}
		}
	}
	
	//DISPLAY COMMON WORDS FUNCTION - RECURSIVE
	//given a "number" of type int for how many words to display, and a "degree" of type int for the degree of gram to run
	//run the display common words helper function, which takes the same argument twice
	//return nothing
	public void displayCommonWords(int number, int degree){
		displayCommonWords_helper(number, degree, degree);
	}

	//GET VALUES FUNCTION
	//given nothing
	//create a list of all the values in the word map
	//return the list
	public List<WordEntry> get_values(){
		List<WordEntry> list_to_return = new ArrayList<WordEntry>();
		for (WordEntry word_entry : word_map.values()){
			list_to_return.add(word_entry);
		}
		return list_to_return;
	}
}
