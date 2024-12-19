package in.kanhere.service;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;

import in.kanhere.entity.CitizenPlan;
import in.kanhere.repo.CitizenPlanRepository;
import in.kanhere.request.SearchRequest;
import in.kanhere.utils.EmailUtils;
import in.kanhere.utils.ExcelGenerator;
import in.kanhere.utils.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private CitizenPlanRepository planRepo;

	@Autowired
	private ExcelGenerator excelGenerator;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public List<String> getPlanName() {
		return planRepo.getPlanNames();
	}

	@Override
	public List<String> getPlanStatuses() {
		return planRepo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {

		CitizenPlan entity = new CitizenPlan();

		if (request.getPlanName() != null && !"".equals(request.getPlanName())) {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != null && !"".equals(request.getPlanStatus())) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (request.getGender() != null && !"".equals(request.getGender())) {
			entity.setGender(request.getGender());
		}
		if (request.getStartDate() != null && !"".equals(request.getStartDate())) {
			String stratDate = request.getStartDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate localDate = LocalDate.parse(stratDate, formatter);
			entity.setPlanStartDate(localDate);
		}

		if (request.getEndDate() != null && !"".equals(request.getEndDate())) {
			String endDate = request.getEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

			LocalDate localDate = LocalDate.parse(endDate, formatter);
			entity.setPlanEndDate(localDate);
		}

		return planRepo.findAll(Example.of(entity));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception {
		File f = new File("Plans.xls");

		List<CitizenPlan> plans = planRepo.findAll();
		excelGenerator.generator(response, plans, f);

		String subject = "Testing Email";
		String body = "<h1> Testing Email Body </h1>";
		String to = "siddheshwarkanhere3@gmail.com";

		emailUtils.sendEmail(subject, body, to, f);

		f.delete();

		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		File f = new File("Plans.pdf");

		List<CitizenPlan> plans = planRepo.findAll();
		pdfGenerator.generator(response, plans, f);

		String subject = "Testing Email";
		String body = "<h1> Testing Email Body </h1>";
		String to = "siddheshwarkanhere3@gmail.com";

		emailUtils.sendEmail(subject, body, to, f);

		f.delete();

		return true;

	}

}
