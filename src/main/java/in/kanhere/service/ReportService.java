package in.kanhere.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import in.kanhere.entity.CitizenPlan;
import in.kanhere.request.SearchRequest;

public interface ReportService {
	
	public List<String> getPlanName();
	
	public List<String> getPlanStatuses();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
	
	

}
