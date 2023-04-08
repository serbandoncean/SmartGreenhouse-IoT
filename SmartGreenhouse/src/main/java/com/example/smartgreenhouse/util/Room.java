package com.example.smartgreenhouse.util;

import com.example.smartgreenhouse.models.Entry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * clasa Room ce ajuta la transferul datelor de pe backend pe frontend
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    private Entry entry1;
    private Entry entry2;
}
