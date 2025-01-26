package com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.service;

import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.ReportDetailsEntity;
import com.CardioSurgeryIllustrator.CardioSurgeryIllustrator.domain.performance.entity.ReportEntity;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class PdfReportGeneratorService {
    public byte[] generatePdfReport(List<ReportEntity> reports, LocalDateTime dateInit, LocalDateTime dateEnd) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.setMargins(20, 20, 20, 20);

            document.add(
                    new Paragraph("Relatório de Desempenho")
                            .setBold()
                            .setFontSize(18)
                            .setTextAlignment(TextAlignment.CENTER)
                            .setMarginBottom(20)
            );

            String period = "Período: " + dateInit.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " - " + dateEnd.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            document.add(
                    new Paragraph(period)
                            .setFontSize(12)
                            .setTextAlignment(TextAlignment.RIGHT)
                            .setMarginBottom(10)
            );

            for (ReportEntity report : reports) {
                document.add(
                        new Paragraph("Quiz: " + report.getTitle())
                                .setBold()
                                .setFontSize(14)
                                .setMarginBottom(5)
                );
                document.add(
                        new Paragraph("Pontuação: " + report.getScore())
                                .setFontSize(12)
                                .setMarginBottom(10)
                );

                Table table = new Table(new float[]{3, 5, 1, 1, 3});
                table.setWidth(UnitValue.createPercentValue(100));

                table.addHeaderCell(
                        new Cell().add(new Paragraph("Pergunta").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY)
                );
                table.addHeaderCell(
                        new Cell().add(new Paragraph("Alternativas").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY)
                );
                table.addHeaderCell(
                        new Cell().add(new Paragraph("Alternativa Correta").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY)
                );
                table.addHeaderCell(
                        new Cell().add(new Paragraph("Resposta do Usuário").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY)
                );
                table.addHeaderCell(
                        new Cell().add(new Paragraph("Resultado").setBold()).setBackgroundColor(ColorConstants.LIGHT_GRAY)
                );

                int questionNumber = 1;
                for (ReportDetailsEntity detail : report.getReportDetailsEntities()) {
                    table.addCell(new Paragraph(questionNumber + ". " + detail.getQuestion().getProblem()));

                    String alternatives = "A) " + detail.getQuestion().getAlternativeA() + "\n" +
                            "B) " + detail.getQuestion().getAlternativeB() + "\n" +
                            "C) " + detail.getQuestion().getAlternativeC() + "\n" +
                            "D) " + detail.getQuestion().getAlternativeD();
                    table.addCell(new Paragraph(alternatives));

                    table.addCell(new Paragraph(String.valueOf(detail.getQuestion().getAnswer())));

                    table.addCell(new Paragraph(String.valueOf(detail.getAnswerUser())));

                    Cell resultCell = new Cell().add(new Paragraph(detail.getGotItRight()));
                    if ("Resposta correta".equals(detail.getGotItRight())) {
                        resultCell.setBackgroundColor(ColorConstants.GREEN);
                    } else {
                        resultCell.setBackgroundColor(ColorConstants.RED);
                    }
                    table.addCell(resultCell);

                    questionNumber++;
                }

                document.add(table);
                document.add(new Paragraph("\n"));
            }

            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar PDF", e);
        }
    }
}

