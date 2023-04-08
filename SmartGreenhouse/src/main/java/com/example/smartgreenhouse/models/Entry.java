package com.example.smartgreenhouse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * clasa pentru Entry
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entries")
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime date;
    private Double temperature;
    private Double moisture;

    @Override
    public String toString() {
        return date.toString() + " " + temperature + " " + moisture;
    }
}
