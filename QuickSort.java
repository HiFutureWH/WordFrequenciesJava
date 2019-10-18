import java.util.List;

//HANNAH FISHER

//this is a quick sorter, which implements the Sorter class

//thanks to Mathus for trying to help me debug, and finding some of my errors, even though we didn't completely fix it
//its ok because it's fixed now

public class QuickSort{
	
	public String toString(){
		return "QUICK SORT";
	}
	
	@SuppressWarnings("rawtypes")
	public List Sort(List given_list){
		if (given_list.size() > 1){
			SortHelper(given_list, 0, given_list.size() - 1);
		}
		return given_list;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static void SortHelper(List given_list, int start, int end){
		Object pivot = given_list.get(start); //set the pivot as the first element
		int counter1 = start - 1; //set the first counter
		int counter2 = end + 1; //set the second counter
		while (counter2 > counter1 ){ //while counters are not crossed or equal 
			counter1 += 1; //moves the first counter by 1
			counter2 -= 1; //moves the second counter by 1
			while ((((Comparable) given_list.get(counter1)).compareTo(pivot) < 0)){ //while thing counter is on is less than pivot and the counter has not gone to the wrong part of the list
				counter1 += 1; //move counter
			}
			while ((((Comparable) given_list.get(counter2)).compareTo(pivot) > 0)){ //while thing counter is on is greater than pivot and the counter has not gone to the wrong part of the list
				counter2 -= 1; //move counter
			}
			if (counter2 > counter1){ //if they are not crossed, do the swap and move
				Object move_to_first = given_list.get(counter2); //gets the thing that counter2 is on, which will be moved to counter 1
				Object move_to_last = given_list.get(counter1); //gets the thing that counter1 is on, which will me moved to counter 2
				given_list.set(counter1, move_to_first); //moves the thing that was on counter 2 to counter 1
				given_list.set(counter2, move_to_last); //moves the thing that was on counter 1 to counter 2
				//now things at the counters have been swapped

			}
		}
		if (counter2 > start){
			SortHelper(given_list, start, counter2); //calls quickSort on the portion of the list in front of the pivot
		}
		if (end > (counter2 + 1)){
			SortHelper(given_list, counter2 + 1, end); //calls quickSort on the portion of the list that is bigger than the pivot
		}
	}

}

