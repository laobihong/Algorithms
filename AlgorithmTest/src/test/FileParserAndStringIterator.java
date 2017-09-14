package test;

import java.io.*;

/**
 * About BufferedReader: Reads text from a character-input stream, buffering characters so as to provide for the efficient reading of characters, arrays, and lines.
The buffer size may be specified, or the default size may be used. The default is large enough for most purposes.

In general, each read request made of a Reader causes a corresponding read request to be made of the underlying character or byte stream. It is therefore advisable to wrap a BufferedReader around any Reader whose read() operations may be costly, such as FileReaders and InputStreamReaders. For example,

 BufferedReader in
   = new BufferedReader(new FileReader("foo.in"));
 
will buffer the input from the specified file. Without buffering, each invocation of read() or readLine() could cause bytes to be read from the file, converted into characters, and then returned, which can be very inefficient.
 *
 */
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
