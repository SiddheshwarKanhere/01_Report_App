package in.kanhere.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.kanhere.entity.CitizenPlan;
import in.kanhere.request.SearchRequest;
import in.kanhere.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response)throws Exception {
		response.setContentType("application/pdf");
		response.addHeader("Content-disposition", "attachment;filename=plans.pdf");
		service.exportPdf(response);
		
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		response.addHeader("Content-disposition", "attachment;filename=plans.xls");
		service.exportExcel(response);
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest search, Model model) {
		
		System.out.println(search);
		List<CitizenPlan> plans = service.search(search);
		
		model.addAttribute("plans", plans);
		
		init(model);
		
		return "index";
	}
	
	@GetMapping("/")
	public String indexPages(Model model) {
		
		//SearchRequest searchObj = new SearchRequest();
		model.addAttribute("search", new SearchRequest());
		init(model);
		
		return "index";
	}

	private void init(Model model) {
		model.addAttribute("names", service.getPlanName());
		model.addAttribute("status", service.getPlanStatuses());
	}
	

}
