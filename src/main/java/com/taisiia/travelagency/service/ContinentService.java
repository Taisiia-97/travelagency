package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.Continent;
import com.taisiia.travelagency.repository.ContinentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContinentService {
    private final ContinentRepository continentRepository;


    public Continent findById(Long id) {
        return continentRepository.getById(id);
    }

    public Continent save(Continent continent) {
        return continentRepository.save(continent);
    }


    @Transactional
    public Continent update(Continent continent, Long id) {
        Continent continentToUpdate = findById(id);
        continentToUpdate.setContinentName(continent.getContinentName());
        return continentToUpdate;
    }


    public void deleteById(Long id) {
        continentRepository.deleteById(id);
    }


    public Continent findByName(String continentName) {
        return continentRepository.findContinentByContinentName(continentName);
    }


    public Page<Continent> getPage(Pageable pageable) {
        return continentRepository.findAll(pageable);
    }
}
