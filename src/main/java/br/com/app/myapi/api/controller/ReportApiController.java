//package br.com.app.myapi.api.controller;
//
//import javax.validation.Valid;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.app.myapi.dao.SQliteManager;
//import br.com.app.myapi.model.Report;
//
//
//public class ReportApiController {
//	
//	private static final Logger log = LogManager.getLogger(ReportApiController.class);
//
//	@PostMapping("/report")
//	public ResponseEntity<Report> getReportBetween(@RequestBody @Valid Report report) {
//		log.info(">getReportBetween()");
//		SQliteManager sq = new SQliteManager();
//		Report resp = sq.getOrderByDateInterval(report);
//		sq.connectionClose();
//		log.info("<getReportBetween()");
//		if (resp != null) return new ResponseEntity<Report>(report, HttpStatus.OK);
//		return new ResponseEntity<Report>(report, HttpStatus.NOT_FOUND);
//	}
//	
//}
