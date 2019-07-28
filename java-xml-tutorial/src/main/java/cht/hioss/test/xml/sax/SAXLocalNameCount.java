/**
 * 
 */
package cht.hioss.test.xml.sax;

import javax.xml.parsers.*;

import org.xml.sax.*;
import org.xml.sax.helpers.*;

import java.util.*;
import java.io.*;

/**
 * @author lawren
 *
 */
public class SAXLocalNameCount extends DefaultHandler {

	private Hashtable tags;

	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {

		String key = localName;
		Object value = tags.get(key);

		if (value == null) {
			tags.put(key, new Integer(1));
		} else {
			int count = ((Integer) value).intValue();
			count++;
			tags.put(key, new Integer(count));
		}
	}

	@Override
	public void endDocument() throws SAXException {
		Enumeration e = tags.keys();
		while (e.hasMoreElements()) {
			String tag = (String) e.nextElement();
			int count = ((Integer) tags.get(tag)).intValue();
			System.out.println("Local Name \"" + tag + "\" occurs " + count
					+ " times");
		}
	}

	@Override
	public void startDocument() throws SAXException {
		tags = new Hashtable();
	}

	private static String convertToFileURL(String filename) {
		String path = new File(filename).getAbsolutePath();
		if (File.separatorChar != '/') {
			path = path.replace(File.separatorChar, '/');
		}

		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return "file:" + path;
	}

	private static void usage() {
		System.err.println("Usage: SAXLocalNameCount <file.xml>");
		System.err.println("       -usage or -help = this message");
		System.exit(1);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String filename = null;

		for (int i = 0; i < args.length; i++) {
			filename = args[i];
			if (i != args.length - 1) {
				usage();
			}
		}

		if (filename == null) {
			usage();
		}

	}

}
