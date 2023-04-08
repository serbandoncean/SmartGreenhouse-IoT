package com.example.smartgreenhouse.services;

import com.example.smartgreenhouse.dtoresponses.EntryDto;
import com.example.smartgreenhouse.models.Entry;
import com.example.smartgreenhouse.repositories.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EntryService {
    @Autowired
    EntryRepository entryRepository;


    public EntryDto addEntry(EntryDto entryDto) {
        Entry entry = entryRepository.findByDate(entryDto.getDate());
        if (entry != null) return null;
        Entry entry1 = new Entry();
        entry1.setTemperature(entryDto.getTemperature());
        entry1.setMoisture(entryDto.getMoisture());
        entry1.setDate(entryDto.getDate());
        entryRepository.save(entry1);
        return entryDto;
    }

    public List<EntryDto> getAll() {
        List<Entry> entries = entryRepository.findAll();
        if (entries.size() == 0) {
            return null;
        }
        List<EntryDto> entryDtos = new ArrayList<EntryDto>();
        for (Entry entry : entries) {
            EntryDto entryDto = new EntryDto();
            entryDto.setDate(entry.getDate());
            entryDto.setMoisture(entry.getMoisture());
            entryDto.setTemperature(entry.getTemperature());
            entryDtos.add(entryDto);
        }
        return entryDtos;
    }

}
