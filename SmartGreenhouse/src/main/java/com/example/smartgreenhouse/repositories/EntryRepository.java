package com.example.smartgreenhouse.repositories;

import com.example.smartgreenhouse.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface EntryRepository extends JpaRepository<Entry, Long> {

    List<Entry> findByMoisture(Double moisture);


    List<Entry> findByTemperature(Double temperature);


    Entry findByDate(LocalDateTime date);
}
