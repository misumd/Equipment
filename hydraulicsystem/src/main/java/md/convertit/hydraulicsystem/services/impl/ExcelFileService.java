package md.convertit.hydraulicsystem.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.junit.Test;

import md.convertit.hydraulicsystem.domain.Equipment;
import md.convertit.hydraulicsystem.services.FileService;

public class ExcelFileService implements FileService {

	public void saveAll(List<Equipment> equipments, String path) throws Exception {

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Equipment");

		Row row1 = sheet.createRow(0);
		Cell cell1 = row1.createCell(0);
		cell1.setCellValue("ID");
		Cell cell2 = row1.createCell(1);
		cell2.setCellValue("NAME");
		Cell cell3 = row1.createCell(2);
		cell3.setCellValue("DESCRIPTION");
		Cell cell4 = row1.createCell(3);
		cell4.setCellValue("TAG");
		Cell cell5 = row1.createCell(4);
		cell5.setCellValue("PRICE");
		Cell cell6 = row1.createCell(5);
		cell6.setCellValue("IN STOCK");

		for (int i = 0; i < equipments.size(); i++) {
			Row row = sheet.createRow(i + 1);
			Cell cell = row.createCell(1);
			cell.setCellValue(equipments.get(i).getName());
			cell = row.createCell(2);
			cell.setCellValue(equipments.get(i).getDescription());
			cell = row.createCell(3);
			cell.setCellValue(equipments.get(i).getTag());
			cell = row.createCell(4);
			cell.setCellValue(equipments.get(i).getPrice());
			cell = row.createCell(5);
			cell.setCellValue(equipments.get(i).isInStock());
		}

		try {
			FileOutputStream out = new FileOutputStream(new File(path));
			workbook.write(out);
			out.close();
			System.out.println("Excel written successfully..");

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Equipment> readAll(String path) throws Exception {
		List<Equipment> listEquipments = new ArrayList<>();
		FileInputStream file = new FileInputStream(path);

		HSSFWorkbook workbook = new HSSFWorkbook(file);

		HSSFSheet sheet = workbook.getSheetAt(0);

		Iterator<Row> rowIterator = sheet.iterator();

		while (rowIterator.hasNext()) {
			Row nextRow = rowIterator.next();
			if (!(nextRow.getRowNum() == 0)) {

				Iterator<Cell> cellIterator = nextRow.cellIterator();
				Equipment aEquipment = new Equipment();
				while (cellIterator.hasNext()) {
					Cell nextCell = cellIterator.next();
					int columnIndex = nextCell.getColumnIndex();

					switch (columnIndex) {
					case 1:
						aEquipment.setName((String) getCellValue(nextCell));
						break;
					case 2:
						aEquipment.setDescription((String) getCellValue(nextCell));
						break;
					case 3:
						aEquipment.setTag((String) getCellValue(nextCell));
						break;
					case 4:
						aEquipment.setPrice((Double) getCellValue(nextCell));
						break;
					case 5:
						aEquipment.setInStock((Boolean) getCellValue(nextCell));
						break;

					}

				}

				listEquipments.add(aEquipment);
			}
		}

		file.close();

		return listEquipments;
	}

	private Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();

		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();

		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}
}
