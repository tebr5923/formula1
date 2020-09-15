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

class RacerLapFormatterIntegrationTest {

    @Test
    void format_shouldReturnCorrectResult() throws IOException, URISyntaxException {
        List<String> expected = new ArrayList<>();
        expected.add(" 1. Sebastian Vettel  | FERRARI                   | 01:04.415");
        expected.add(" 2. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 01:12.013");
        expected.add(" 3. Valtteri Bottas   | MERCEDES                  | 01:12.434");
        expected.add(" 4. Lewis Hamilton    | MERCEDES                  | 01:12.460");
        expected.add(" 5. Stoffel Vandoorne | MCLAREN RENAULT           | 01:12.463");
        expected.add(" 6. Kimi Raikkonen    | FERRARI                   | 01:12.639");
        expected.add(" 7. Fernando Alonso   | MCLAREN RENAULT           | 01:12.657");
        expected.add(" 8. Sergey Sirotkin   | WILLIAMS MERCEDES         | 01:12.706");
        expected.add(" 9. Charles Leclerc   | SAUBER FERRARI            | 01:12.829");
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
        List<RacerLap> racerLapList = racerLapsFactory.create("abb.txt", "st.log", "en.log");
        RacerLapFormatter racerLapFormatter = new RacerLapFormatter();
        List<String> actual = racerLapFormatter.format(racerLapList);

        assertEquals(expected, actual);
    }
}
