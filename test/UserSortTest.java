import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserSortTest {
	public static void main(String[] args){
		String filename = "BX-Book-Ratings.csv";
		//Create an ArrayList of strings for users
		ArrayList<String> Users = new ArrayList<String>();
				File file = new File(filename); //TODO: read about file
				try {
					@SuppressWarnings("resource")
					BufferedReader br = new BufferedReader(new FileReader(file));
					String line;
					try {
						while((line = br.readLine()) != null){
							//Take all three columns in the dataset
							String[] values = line.split(";");
							Users.add(values[0] + ";" + values[1] + ";" + values[2]);
						}
						//Call userSort to sort the file
						userSort(Users);
						//Check to see if the files are properly sorted
						for (String u: Users)
							System.out.println(u);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	//Takes the ArrayList generated by the main method as input
	//Sorts ISBNs and ratings according to increasing userID
	public static void userSort(ArrayList<String>list) throws FileNotFoundException{
		Collections.sort(list, new Comparator<String>(){
			@Override
			public int compare(String o1, String o2) {
				// TODO Auto-generated method stub
				//Take the first string, split according to ";", and store in an arraylist
				String[] values = o1.split(";");
				//Take the second string and do the same thing
				String[] values2 = o2.split(";");
				//Compare strings according to their integer values
				return Integer.valueOf(values[0].replace("\"", "")).compareTo(Integer.valueOf(values2[0].replace("\"", "")));
			}
		});
		//Output sorted ArrayList to a text file
		@SuppressWarnings("resource")
		PrintStream out = new PrintStream(new FileOutputStream("SortedUsers.txt"));
			for(int i = 0; i < list.size(); i++){
				out.println(list.get(i));
			}
	}
}
