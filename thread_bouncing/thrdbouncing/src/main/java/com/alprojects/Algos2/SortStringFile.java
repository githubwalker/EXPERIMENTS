package com.alprojects.Algos2;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
 public static <T extends Comparable<? super T>> void sort(List<T> list) {
 list.sort(null);
 }*/

/*
 new InputStreamReader(
 new FileInputStream(fileDir), "UTF8")
 */

public class SortStringFile {

	public static String sortFiles(String filename) throws IOException {
		// FileReader fr = new FileReader( filename );
		// BufferedReader bfr = new BufferedReader( fr );

		InputStreamReader inputStreamReader = new InputStreamReader(
				new FileInputStream(filename), "UTF16");

		BufferedReader bfr = new BufferedReader(inputStreamReader);

		String strLine = null;
		ArrayList<String> strs = new ArrayList<String>();

		try {
			while ((strLine = bfr.readLine()) != null) {
				strs.add(strLine);
			}
		} finally {
			bfr.close();
		}

		// strs.sort(c);
		//
		Collections.sort(strs);
		for (String str : strs)
			System.out.println(str);

		return "";
	}

	/*
	 * public static void Sort4bytesNumbers( String inFilename, String
	 * outFileName ) throws IOException { FileReader fr = new
	 * FileReader(inFilename); // BufferedReader bfr = new BufferedReader( fr );
	 * final int bufsize = 1024 * 32; char [] buffer = new char[bufsize];
	 * 
	 * int nBytesRead = 0;
	 * 
	 * while ( (nBytesRead = fr.read( buffer )) != 0 ) {
	 * 
	 * }
	 * 
	 * // fr.re fr.close(); }
	 */

	// public static int readInt_helper( DataInputStream )
	public static int convert(int ch) {
		return ch < 0 ? 0 : ch;
	}

	public static String sortAndSave(int[] buffer, int size) throws IOException {
		if (size <= 0)
			return "";

		Arrays.sort(buffer, 0, size);
		// BufferedWriter bw = null;
		// DataOutputStream dostream = new DataOutputStream( new FileOutputStream(file) );
		DataOutputStream dostream = null;
		
		File temp = null;

		try {
			temp = File.createTempFile("TMP", "bin");
			// bw = new BufferedWriter(new FileWriter(temp));
			dostream = new DataOutputStream( new FileOutputStream(temp) );
			for (int i = 0; i < size; i++)
				dostream.writeInt(buffer[i]);
		} finally {
			dostream.close();
		}

		return temp.getAbsolutePath();
	}

	// http://habrahabr.ru/post/132465/

	public static void Sort4bytesNumbers2(String inFilename, String outFileName)
			throws IOException {
		// FileReader fr = new FileReader(inFilename);
		// BufferedReader bfr = new BufferedReader( fr );
		// BinaryReader br = new BinaryReader();

		// InputStream in = new DataInputStream(new BufferedInputStream(new
		// FileInputStream(inFilename), 1024));

		DataInputStream inp = new DataInputStream(new FileInputStream(
				inFilename));
		// BufferedOutputStream outp = new BufferedOutputStream( new FileOutputStream(outFileName));

		ArrayList<String> tempfilenames = new ArrayList<String>();

		try {
			// in.readInt();

			final int bufferSize = 1024 * 1;

			int[] buffer = new int[bufferSize];

			boolean bEof = false;
			int buffer_pos = 0;

			while (!bEof) {
				int ch1 = inp.read();
				int ch2 = inp.read();
				int ch3 = inp.read();
				int ch4 = inp.read();

				bEof = (ch1 < 0) && (ch2 < 0) && (ch3 < 0) && (ch4 < 0);
				if (!bEof) {
					ch1 = convert(ch1);
					ch2 = convert(ch2);
					ch3 = convert(ch3);
					ch4 = convert(ch4);

					int loaded = (ch1 << 24) + (ch2 << 16) + (ch3 << 8)
							+ (ch4 << 0);

					if (buffer_pos >= bufferSize) {
						tempfilenames.add(sortAndSave(buffer, buffer_pos));
						buffer_pos = 0;
					}

					buffer[buffer_pos++] = loaded;
				}
			}

			if (buffer_pos >= 0) {
				tempfilenames.add( sortAndSave(buffer, buffer_pos) );
				buffer_pos = 0;
			}
		} finally {
			inp.close();
		}

	}
}
