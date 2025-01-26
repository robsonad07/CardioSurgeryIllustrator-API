package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.controllers;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.PerformanceEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.ReportEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.service.PdfReportGeneratorService;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.useCases.CreatePerformanceUseCase;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.useCases.GetReportUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/performance")
public class PerformanceController {
    @Autowired
    private CreatePerformanceUseCase createPerformanceUseCase;

    @Autowired
    private GetReportUseCase getReportUseCase;

    @Autowired
    private PdfReportGeneratorService pdfReportGenerator;

    @PostMapping("/create")
    public ResponseEntity<Object> createPerformance(@RequestBody PerformanceEntity performance) {
        try {
            PerformanceEntity response = createPerformanceUseCase.execute(performance);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get-report/{id}")
    public ResponseEntity<Object> getReport(
            @PathVariable UUID id,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {
        try {
            LocalDateTime newStartDate = LocalDateTime.parse(startDate);
            LocalDateTime newEndDate = LocalDateTime.parse(endDate);
            List<ReportEntity> reports = getReportUseCase.execute(id, newStartDate, newEndDate);
            byte[] response = pdfReportGenerator.generatePdfReport(reports, newStartDate, newEndDate);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
