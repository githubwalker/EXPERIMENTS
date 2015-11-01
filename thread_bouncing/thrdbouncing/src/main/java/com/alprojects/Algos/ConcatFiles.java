package com.alprojects.Algos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ConcatFiles {

	static void concatFiles(String filename1, String filename2, String outFilename) throws IOException {
		BufferedReader rd1 = new BufferedReader( new FileReader(filename1) );
		BufferedReader rd2 = new BufferedReader( new FileReader(filename2) );
		BufferedWriter bw = new BufferedWriter( new FileWriter(outFilename) );
		
		char[] buffer = new char[128]; 
		
		int nlen = 0;  
		
		while ( (nlen = rd1.read( buffer )) >= 0 )
			bw.write(buffer, 0, nlen);
		
		while ( (nlen = rd2.read( buffer )) >= 0 )
			bw.write(buffer, 0, nlen);
		
		rd1.close();
		rd2.close();
		bw.close();
		
		return;
	}
}

