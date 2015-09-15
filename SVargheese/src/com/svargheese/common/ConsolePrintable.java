package com.svargheese.common;

import java.util.List;

public abstract class ConsolePrintable {

	/**
	 * Returns list containing lines, each line has columns represented by
	 * String array. First line is header and all other lines should contain
	 * String arrays of length same as headers.
	 * 
	 * @return List of String array. First line is header.
	 */
	protected abstract List<String[]> getPrintableLines();

	/**
	 * Converts List of String array to string which can be printed on console.
	 * 
	 * @return
	 */
	public String toConsole() {

		List<String[]> lines = getPrintableLines();

		/* Validation */
		if (lines == null || lines.size() == 0 || lines.get(0) == null) {
			throw new IllegalArgumentException(
					"getPrintableLines cannot return null and should return list with headers.");
		}

		int colSize = lines.get(0).length;
		int[] colWidth = new int[colSize];

		for (String[] cols : lines) {

			if (cols.length != colSize) {

				throw new IllegalArgumentException(
						"All lines should have same number of columns as headers.");
			}

			for (int c = 0; c < cols.length; c++) {
				cols[c] = cols[c] == null ? "null" : cols[c];
				if (cols[c].length() > colWidth[c]) {
					colWidth[c] = cols[c].length();
				}
			}
		}

		/* Formatting and converting to ASCII table. */
		StringBuilder rowDivider = new StringBuilder();
		StringBuilder rowFormatter = new StringBuilder();
		for (int i : colWidth) {
			for (int j = 0; j < i + 1; j++) {
				rowDivider.append("-");
			}
			rowFormatter.append("|%" + i + "s");
		}
		rowDivider.append("\n");
		rowFormatter.append("|\n");

		StringBuilder sb = new StringBuilder();
		for (String[] line : lines) {
			sb.append(rowDivider);
			sb.append(String.format(rowFormatter.toString(), line[0], line[1],
					line[2], line[3]));
		}
		sb.append(rowDivider);

		return sb.toString();
	}
}
