package com.taisiia.travelagency.template;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    public Template findTemplateByName(String name) {
        return templateRepository.findTemplateByName(name).orElseThrow(EntityNotFoundException::new);
    }
}
