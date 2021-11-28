package com.taisiia.travelagency.mailservice;

import com.taisiia.travelagency.template.Template;
import com.taisiia.travelagency.template.TemplateService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Map;

@Service
@AllArgsConstructor
public class MailContentBuilder {
    private final ITemplateEngine iTemplateEngine;
    private final TemplateService templateService;


    public String generateContent(Map<String, Object> values, String templateName) {
        Template template = templateService.findTemplateByName(templateName);
        Context context = new Context();
        context.setVariables(values);
        return iTemplateEngine.process(template.getBody(), context);
    }
}
