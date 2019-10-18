//HANNAH FISHER

public class WordEntry implements Comparable<WordEntry>{
	private String word; 
	private int frequency;
	private double prob;
	private double cumulative_prob;
	private WordTable word_table;
	
	//CONSTRUCTOR FUNCTION
	//given a "word" of type String
	//sets all the fields
	//returns nothing
	public WordEntry(String word){ 
		this.word = word;
		this.frequency = 1;
		this.prob = 0; //set in process entries
		this.cumulative_prob = 0; //set in process entries
		this.word_table = new WordTable(); 
	}
	
	//HERE ARE THE STUPID FUNCTIONS FOR THE PRIVATE FIELDS
	public String get_word(){
		return word;
	}
	public int get_frequency(){
		return frequency;
	}
	public double get_prob(){
		return prob;
	}
	public double get_cumulative_prob(){
		return cumulative_prob;
	}
	public void add_freq(){
		frequency += 1;
	}
	public void set_prob(double num){
		prob = num;
	}
	public void set_cumulative_prob(double num){
		cumulative_prob = num;
	}
	public WordTable get_word_table(){
		return word_table;
	}
	
	//TOSTRING FUNCTION
	//given nothing
	//does nothing
	//returns a string with all the important fields of this object
	public String toString(){
		return ('"' + word + '"' + " occurs " + frequency + " times. Probability: " + prob + " Cumulative probability: " + cumulative_prob);
	}

	//COMPARETO FUNCTION
	//given another word entry
	//compares the frequency of given word entry to self word entry
	//returns a negative integer if self > given, a positive integer if self < given and a 0 if self = given
	public int compareTo(WordEntry given_word_entry) { 
		if (this.frequency == given_word_entry.frequency){ //can i use double equals becuase its ints??
			return 0;
		}
		else if (this.frequency < given_word_entry.frequency){
			return 1; 
		}
		else {
			return -1;
		}
	}
	
}
