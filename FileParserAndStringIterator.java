package test;

import java.io.*;

public class FileParserAndStringIterator {
	FileReader fr;
	BufferedReader br;
	boolean consumed;
	String nextOutput;

	public FileParserAndStringIterator() {
		try {
			String dir = System.getProperty("user.dir");
			fr = new FileReader(dir + "/src/test/test.txt");
			br = new BufferedReader(fr);
			consumed = true;
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public boolean hasNext() throws IOException{
		if (!consumed) {
			return true;
		}
		
		if ((nextOutput = br.readLine()) != null) {
			consumed = false;
			return true;
		} else {
			consumed = true;
			return false;
		}
	}
	
	public String next() {
		if (consumed) {
			return "";
		}
		
		consumed = true;
		return nextOutput;
	}
	
	public static void main (String[] args) throws IOException {
		FileParserAndStringIterator iterator = new FileParserAndStringIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
