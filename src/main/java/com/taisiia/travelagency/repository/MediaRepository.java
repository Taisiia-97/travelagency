package com.taisiia.travelagency.repository;

import com.taisiia.travelagency.domain.dao.MediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity,Long> {
}
