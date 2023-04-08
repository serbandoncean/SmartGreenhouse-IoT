package com.example.smartgreenhouse.controllers;

import com.example.smartgreenhouse.dtoresponses.EntryDto;
import com.example.smartgreenhouse.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;


@Controller
public class EntryController {

    private final String TOO_HOT = new String("\n!!The temperature is too high!!\n");
    private final String TOO_COLD = new String("\n!!The temperature is too low!!\n");
    private final String GOOD_TEMPERATURE = new String("\nThe temperature is good\n");
    private final String TOO_ARID = new String("!!The moisture is too low!!\n");
    private final String TOO_HUMID = new String("!!The moisture is too high!!\n");
    private final String GOOD_MOISTURE = new String("The moisture level is good\n");
    @Autowired
    private EntryService entryService;


    @PostMapping("/addEntry")
    public String addEntry(@ModelAttribute("entryDto") EntryDto entryDto, Model model) {
        entryDto.setDate(LocalDateTime.now());
        EntryDto entryDtoCreated = entryService.addEntry(entryDto);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        if (entryDtoCreated != null) {
            model.addAttribute("response", "Entry added:\nDate: " + entryDto.getDate().format(myFormatObj) + "\nTemperature: " + entryDto.getTemperature() + "\nMoisture: " + entryDto.getMoisture());
        } else {
            model.addAttribute("response", "!!!Entry already exists !!!");
        }
        return "succes";
    }


    @GetMapping("/mostRecent")
    public String getMostRecent(Model model) {
        List<EntryDto> entryDtos = entryService.getAll();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        if (entryDtos == null) {
            model.addAttribute("response", "!!!Nu exista ingregistrari!!");
        }
        Collections.sort(entryDtos, Collections.reverseOrder());
        EntryDto selected = entryDtos.get(0);
        String response = new String("Most recent entry:\nDate: " + selected.getDate().format(myFormatObj) + "\nTemperature: " + selected.getTemperature() + " °C \nMoisture: " + selected.getMoisture() + "%");
        if (selected.getTemperature() > 30) {
            response = response + TOO_HOT;
        } else if (selected.getTemperature() < 20) {
            response = response + TOO_COLD;

        } else {
            response = response + GOOD_TEMPERATURE;
        }

        if (selected.getMoisture() > 80) {
            response = response + TOO_HUMID;
        } else if (selected.getMoisture() < 50) {
            response = response + TOO_ARID;
        } else {
            response = response + GOOD_MOISTURE;
        }
        model.addAttribute("response", response);
        return "succes";
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        List<EntryDto> entryDtos = entryService.getAll();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        if (entryDtos == null) {
            model.addAttribute("response", "!!!Nu exista ingregistrari!!");
        }
        Collections.sort(entryDtos, Collections.reverseOrder());
        EntryDto selected = entryDtos.get(0);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Entries total: " + entryDtos.size() + "\n\n");
        for (EntryDto entryDto : entryDtos) {
            stringBuilder.append("Date: " + entryDto.getDate().format(myFormatObj) + "\nTemperature: " + entryDto.getTemperature() + " °C \nMoisture: " + entryDto.getMoisture() + "%\n\n");
        }
        model.addAttribute("response", stringBuilder.toString());
        return "succes";
    }
}
