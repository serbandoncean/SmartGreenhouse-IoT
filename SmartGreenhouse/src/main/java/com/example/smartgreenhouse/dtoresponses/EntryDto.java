package com.example.smartgreenhouse.dtoresponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Clasa Dto pentru clasa Entry
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntryDto implements Comparable<EntryDto> {
    private LocalDateTime date = null;
    private Double temperature;
    private Double moisture;

    @Override
    public int compareTo(EntryDto e) {
        return this.getDate().compareTo(e.getDate());
    }
}
