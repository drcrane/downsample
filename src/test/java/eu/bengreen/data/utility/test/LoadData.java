package eu.bengreen.data.utility.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple class to load a simple two column CSV, not really recommeded in production as it does not check anything!
 * @author Benjamin Green
 *
 */
public class LoadData {
	public static Number[][] loadData(String filename) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(filename));
		try {
			List<Number[]> res = new ArrayList<Number[]>();
			String line;
			while ((line = br.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("#")) {
					continue;
				}
				int pos = line.indexOf(",");
				String ts = line.substring(0, pos);
				String val = line.substring(pos+1);
				Long lts = Long.parseLong(ts);
				Double dval = Double.parseDouble(val);
				res.add(new Number[] { lts, dval } );
			}
			return res.toArray(new Number[res.size()][]);
		} finally {
			br.close();
		}
	}
}
