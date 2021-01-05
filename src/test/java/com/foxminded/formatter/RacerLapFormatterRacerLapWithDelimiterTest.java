package com.foxminded.formatter;

import com.foxminded.racer.RacerLap;
import com.foxminded.util.RacerLapBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RacerLapFormatterRacerLapWithDelimiterTest {

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

        List<RacerLap> racerLapList = new ArrayList<>();
        RacerLapBuilder racerLapBuilder = new RacerLapBuilder();
        racerLapList.add(racerLapBuilder.build("LHM", "Lewis Hamilton", "MERCEDES", LocalTime.of(0, 1, 12, 659000000)));
        racerLapList.add(racerLapBuilder.build("FAM", "Fernando Alonso", "MCLAREN RENAULT", LocalTime.of(0, 1, 12, 657000000)));
        racerLapList.add(racerLapBuilder.build("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", LocalTime.of(0, 1, 12, 658000000)));
        racerLapList.add(racerLapBuilder.build("SVF", "Sebastian Vettel", "FERRARI", LocalTime.of(0, 1, 4, 415000000)));
        racerLapList.add(racerLapBuilder.build("KRF", "Kimi Raikkonen", "FERRARI", LocalTime.of(0, 1, 12, 655000000)));

        RacerLapFormatterRacerLapWithDelimiter racerLapFormatterRacerLapWithDelimiter =
                new RacerLapFormatterRacerLapWithDelimiter(mockRacerLapFormatter, 3);
        List<String> actual = racerLapFormatterRacerLapWithDelimiter.format(racerLapList);

        assertEquals(expected, actual);
    }

    private void initMockRacerLapFormatter(){
        List<String> result = new ArrayList<>();
        result.add("1. Sebastian Vettel | FERRARI                   | 01:04.415");
        result.add("2. Kimi Raikkonen   | FERRARI                   | 01:12.655");
        result.add("3. Fernando Alonso  | MCLAREN RENAULT           | 01:12.657");
        result.add("4. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.658");
        result.add("5. Lewis Hamilton   | MERCEDES                  | 01:12.659");

        when(mockRacerLapFormatter.format(Mockito.anyList())).thenReturn(result);
    }
}
