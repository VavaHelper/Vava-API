package com.helper.vavahelper.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.helper.vavahelper.models.Maps.Maps;

@Repository
public interface MapsRepository extends JpaRepository<Maps, Long> {

    
}
