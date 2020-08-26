package com.foxminded.racer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface RacerLapsFactory {
    List<RacerLap> create(String abbreviationFileName, String startTimeFileName, String endTimeFileName)throws IOException, URISyntaxException;
}
