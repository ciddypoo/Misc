import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Parser {
	private File file;
	private LinkedHashSet<String> set;
	
	public Parser() throws FileNotFoundException {	
		file = new File("c:\\UDPsignalsLong.txt");
		set = new LinkedHashSet<String>();
	}
	
	 public void writeList () {
		 BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter("c:\\UDPsignalsPrepared.txt"));
			 Iterator it = set.iterator();
			 while(it.hasNext()) {
			     out.write(it.next().toString());
			     out.newLine();
			 }
			 out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 }
 
	 public void prepareList() throws FileNotFoundException, IOException {
			String s = null;
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			    while ((s = br.readLine()) != null) {
			    set.add(s);
			    System.out.println("Added");
			    }
			}
		}
}
