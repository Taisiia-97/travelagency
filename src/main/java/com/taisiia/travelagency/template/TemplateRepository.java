package com.taisiia.travelagency.template;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {

     Optional<Template> findTemplateByName(String name);

}
