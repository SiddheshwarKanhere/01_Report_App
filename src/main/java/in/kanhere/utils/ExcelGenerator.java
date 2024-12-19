package in.kanhere.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.kanhere.entity.CitizenPlan;
import in.kanhere.repo.CitizenPlanRepository;

@Component
public class ExcelGenerator {
	
	
	public void generator(HttpServletResponse response, List<CitizenPlan> plans, File file) throws Exception {
		Workbook workBook = new HSSFWorkbook();

		Sheet sheet = workBook.createSheet("Plans-Data");
		Row headerRow = sheet.createRow(0);

		headerRow.createCell(0).setCellValue("ID");
		headerRow.createCell(1).setCellValue("Citizen Name");
		headerRow.createCell(2).setCellValue("Plan Name");
		headerRow.createCell(3).setCellValue("Plan status");
		headerRow.createCell(4).setCellValue("Plan Start Date");
		headerRow.createCell(5).setCellValue("Plan End Date");
		headerRow.createCell(6).setCellValue("Benefit Amt");

		int dataRowIndex =1;
		
		for (CitizenPlan plan : plans) {
			
			Row dataRow = sheet.createRow(dataRowIndex);
			dataRow.createCell(0).setCellValue(plan.getCitizenId());
			dataRow.createCell(1).setCellValue(plan.getCitizenName());
			dataRow.createCell(2).setCellValue(plan.getPlanName());
			dataRow.createCell(3).setCellValue(plan.getPlanStatus());
			dataRow.createCell(4).setCellValue(plan.getPlanStartDate()+"");
			dataRow.createCell(5).setCellValue(plan.getPlanEndDate()+"");
			if(plan.getBenefitAmt() != null) {
				dataRow.createCell(6).setCellValue(plan.getBenefitAmt());
			}
			dataRow.createCell(6).setCellValue("N/A");
			
			dataRowIndex++;
		}
		
		FileOutputStream fos = new FileOutputStream(file);
		workBook.write(fos);
		fos.close(); 

		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
	}

}
