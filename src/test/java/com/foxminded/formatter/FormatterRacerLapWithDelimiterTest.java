package com.foxminded.formatter;

import com.foxminded.racer.RacerLap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FormatterRacerLapWithDelimiterTest {

    @Mock
    Formatter<RacerLap> mockRacerLapFormatter;

    @BeforeEach
    void setUp() {
        initMockRacerLapFormatter();
    }

    @Test
    void format_shouldReturnCorrectResult() {
        List<String> expected = new ArrayList<>();
        expected.add("1. Sebastian Vettel | FERRARI                   | 01:04.415");
        expected.add("2. Kimi Raikkonen   | FERRARI                   | 01:12.655");
        expected.add("3. Fernando Alonso  | MCLAREN RENAULT           | 01:12.657");
        expected.add("-----------------------------------------------------------");
        expected.add("4. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.658");
        expected.add("5. Lewis Hamilton   | MERCEDES                  | 01:12.659");

        FormatterRacerLapWithDelimiter formatterRacerLapWithDelimiter =
                new FormatterRacerLapWithDelimiter(mockRacerLapFormatter, 3);
        List<String> actual = formatterRacerLapWithDelimiter.format(new ArrayList<>());

        assertEquals(expected, actual);
    }

    private void initMockRacerLapFormatter() {
        List<String> result = new ArrayList<>();
        result.add("1. Sebastian Vettel | FERRARI                   | 01:04.415");
        result.add("2. Kimi Raikkonen   | FERRARI                   | 01:12.655");
        result.add("3. Fernando Alonso  | MCLAREN RENAULT           | 01:12.657");
        result.add("4. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.658");
        result.add("5. Lewis Hamilton   | MERCEDES                  | 01:12.659");

        when(mockRacerLapFormatter.format(Mockito.anyList())).thenReturn(result);
    }
}
