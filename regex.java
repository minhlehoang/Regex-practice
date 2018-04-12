package PA09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class regex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String p_title;
		String p_price;
		String output_string = "";
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			// Get the selected file
			java.io.File file = fileChooser.getSelectedFile();
			// Create a Scanner for the file
			Scanner input = null;
			try {
				input = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int i = 0;
			Pattern pat = Pattern.compile("(.*)Suggested Retail Price(.*)");
			Pattern pat_price = Pattern.compile("Suggested Retail Price:(.*?)\"");
			Pattern pat_title = Pattern.compile("title=\"(.*?)\"");
			// Read text from the file			
			while (input.hasNext()) {
				String line = input.nextLine();
				Matcher matcher = pat.matcher(line);
				if(matcher.find()) {
					i++;
					//System.out.println(line);
					Matcher matcher_price = pat_price.matcher(line);
					Matcher matcher_title = pat_title.matcher(line);
					if(matcher_title.find() && matcher_price.find()) {
						String productname = matcher_title.group().substring(matcher_title.group().indexOf("\"") + 1, matcher_title.group().length() - 1);
						String productprice = matcher_price.group().substring(0, matcher_price.group().length() -1);
						output_string += "Product #" + i + ": " + productname + "\n" + productprice + "\n";
						//System.out.println(productname + "\n" + productprice);
						try {
							File file_output = new File("outputfile.txt");
							FileWriter fileWriter = new FileWriter(file_output);
							fileWriter.write(output_string);
							fileWriter.flush();
							fileWriter.close();
							
						} catch (IOException e) {
							e.printStackTrace();
						}
						
					}
				}				
			}
			JOptionPane.showMessageDialog(null, output_string);
			input.close(); // Close the file
		}
	    else {
	        JOptionPane.showMessageDialog(null,"No file selected");
	    }
	}
}
