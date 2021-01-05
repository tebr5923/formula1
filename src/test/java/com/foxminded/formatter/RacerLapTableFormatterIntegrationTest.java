package com.foxminded.formatter;

import com.foxminded.parser.RacerParser;
import com.foxminded.parser.TimeParser;
import com.foxminded.racer.DefaultRacerLapsFactory;
import com.foxminded.racer.RacerLap;
import com.foxminded.racer.RacerLapsFactory;
import com.foxminded.reader.ResourceFileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RacerLapTableFormatterIntegrationTest {
    private static final String ABBREVIATIONS = "racer_lap_table_formatter_integration_test/abbreviations.txt";
    private static final String START_LOG = "racer_lap_table_formatter_integration_test/start.log";
    private static final String END_LOG = "racer_lap_table_formatter_integration_test/end.log";

    @Test
    void format_shouldReturnCorrectResult() throws IOException, URISyntaxException {
        List<String> expected = new ArrayList<>();
        expected.add("01. Sebastian Vettel  | FERRARI                   | 01:04.415");
        expected.add("02. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 01:12.013");
        expected.add("03. Valtteri Bottas   | MERCEDES                  | 01:12.434");
        expected.add("04. Lewis Hamilton    | MERCEDES                  | 01:12.460");
        expected.add("05. Stoffel Vandoorne | MCLAREN RENAULT           | 01:12.463");
        expected.add("06. Kimi Raikkonen    | FERRARI                   | 01:12.639");
        expected.add("07. Fernando Alonso   | MCLAREN RENAULT           | 01:12.657");
        expected.add("08. Sergey Sirotkin   | WILLIAMS MERCEDES         | 01:12.706");
        expected.add("09. Charles Leclerc   | SAUBER FERRARI            | 01:12.829");
        expected.add("10. Sergio Perez      | FORCE INDIA MERCEDES      | 01:12.848");
        expected.add("11. Romain Grosjean   | HAAS FERRARI              | 01:12.930");
        expected.add("12. Pierre Gasly      | SCUDERIA TORO ROSSO HONDA | 01:12.941");
        expected.add("13. Carlos Sainz      | RENAULT                   | 01:12.950");
        expected.add("14. Esteban Ocon      | FORCE INDIA MERCEDES      | 01:13.028");
        expected.add("15. Nico Hulkenberg   | RENAULT                   | 01:13.065");
        expected.add("-------------------------------------------------------------");
        expected.add("16. Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 01:13.179");
        expected.add("17. Marcus Ericsson   | SAUBER FERRARI            | 01:13.265");
        expected.add("18. Lance Stroll      | WILLIAMS MERCEDES         | 01:13.323");
        expected.add("19. Kevin Magnussen   | HAAS FERRARI              | 01:13.393");

        RacerLapsFactory racerLapsFactory = new DefaultRacerLapsFactory(new RacerParser(), new TimeParser(), new ResourceFileReader());
        List<RacerLap> racerLapList = racerLapsFactory.create(ABBREVIATIONS, START_LOG, END_LOG);
        FormatterRacerLapWithDelimiter formatterRacerLapWithDelimiter =
                new FormatterRacerLapWithDelimiter(new RacerLapTableFormatter());
        List<String> actual = formatterRacerLapWithDelimiter.format(racerLapList);

        assertEquals(expected, actual);
    }
}
