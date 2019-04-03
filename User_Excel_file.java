
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sun.javafx.geom.Rectangle;

public class User_Excel_file {

	public void Write_infile(Object[][] o, String name_file) {

		Workbook workbook = null;

		if (name_file.endsWith("xlsx")) {
			workbook = new XSSFWorkbook();

		} else if (name_file.endsWith("xls")) {
			workbook = new HSSFWorkbook();

		} else {

		}
		if (workbook != null) {
			Sheet sheet = workbook.createSheet("Countries");
			for (int i = 0; i < o.length; i++) {
				Row row = sheet.createRow(i);
				for (int j = 0; j < o[0].length; j++) {
					Cell cell = row.createCell(j);
					cell.setCellValue(String.valueOf(o[i][j]));

				}

			}

			try {
				FileOutputStream fos = new FileOutputStream(name_file);
				workbook.write(fos);
				fos.close();
			} catch (Exception e) {
			}

		}

	}

	public List<Object> read_file(String name_file) throws Exception {
		List<Object>  l = new ArrayList<Object>();
		Workbook workbook = null;
		FileInputStream fis = new FileInputStream(name_file);
		if (name_file.toLowerCase().endsWith("xlsx")) {
			workbook = new XSSFWorkbook(fis);
		} else if (name_file.toLowerCase().endsWith("xls")) {
			workbook = new HSSFWorkbook(fis);
		}
		if (workbook != null) {
			int numberOfSheets = workbook.getNumberOfSheets();
			Object[][] val;
			int x = 0;
			int y = 0;
			for (int i = 0; i < 1/* numberOfSheets */; i++) {
				Sheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rowIterator = sheet.iterator();

				while (rowIterator.hasNext()) {
					List<Object>  l2 =  new ArrayList<Object>();
					Row row = rowIterator.next();
					x++;
					Iterator<Cell> cellIterator = row.cellIterator();
					y = 0;

					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						try {

							if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
								l2.add(cell.getStringCellValue());
							} else {
								l2.add(cell.getNumericCellValue());
							}

						} catch (Exception e) {

						}
						y++;

					}
					l.add(l2);

					
				}
				System.out.println("Read Line Id = "+i);
			}

		}
		return l;

	}
/*
	public static void main(String[] king) {

		User_Excel_file f = new User_Excel_file();
		try {
			Liste l = f.read_file("/home/kinghoussem/Desktop/Untitled 1.xls");
			for (int i = 1; i < l.length(); i++) {
				Liste l2 = (Liste) l.getE(i);
				l2.aff();
			}

		} catch (Exception e) {
			System.out.println("erre");
		}

	}
*/
}
