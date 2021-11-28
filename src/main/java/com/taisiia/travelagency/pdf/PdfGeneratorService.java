package com.taisiia.travelagency.pdf;

import com.lowagie.text.DocumentException;
import com.taisiia.travelagency.domain.dao.TourOrder;
import com.taisiia.travelagency.service.OrderConfirmationService;
import com.taisiia.travelagency.template.TemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PdfGeneratorService {
    private final TemplateEngine templateEngine;
    private final ITemplateEngine iTemplateEngine;
    private final OrderConfirmationService orderConfirmationService;
    private final TemplateService templateService;

    @Value("${file.pdfLocation}")
    private String path;

    public String generatePdf(TourOrder order) {

        String fullPathWithName = path + File.separator + String.format("file_%s.pdf", UUID.randomUUID().toString());
        try {
            String html = parseThymeleafTemplate(generatePdfModel(order));
            generatePdfFromHtml(html, fullPathWithName);
            orderConfirmationService.saveConfirmation(order, fullPathWithName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fullPathWithName;
    }

    private PdfDataDto generatePdfModel(TourOrder order) {
        return PdfDataDto.builder()
                .userName(order.getUser().getFirstName())
                .userSurname(order.getUser().getLastName())
                .countryNameFrom(order.getTour().getFromAirPort().getAddress().getCity().getCountry().getCountryName())
                .cityNameFrom(order.getTour().getFromAirPort().getAddress().getCity().getCityName())
                .countryNameTo(order.getTour().getPurposeAirport().getAddress().getCity().getCountry().getCountryName())
                .cityNameTo(order.getTour().getPurposeAirport().getAddress().getCity().getCityName())
                .departureDate(order.getOrderDate().toString())
                .hotelName(order.getTour().getHotel().getHotelName())
                .totalPrice(order.getTotalPriceWithoutDiscount())
                .totalDiscount(order.getTotalDiscount())
                .price(order.getTotalPrice())
                .build();

    }

    private void generatePdfFromHtml(String html, String outputFolder) throws IOException, DocumentException {
        OutputStream outputStream = new FileOutputStream(outputFolder);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
    }

    private String parseThymeleafTemplate(PdfDataDto dataModel) {
        Context context = new Context();
        context.setVariable("data", dataModel);
        String body = templateService.findTemplateByName("pdf_order").getBody();
        return iTemplateEngine.process(body, context);
    }
}
