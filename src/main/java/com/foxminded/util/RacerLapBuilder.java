package com.foxminded.util;

import com.foxminded.racer.Racer;
import com.foxminded.racer.RacerLap;

import java.time.LocalTime;

public class RacerLapBuilder {
    public RacerLap build(String abbreviation, String fullName, String teamName, LocalTime racerResult) {
        return new RacerLap(new Racer(abbreviation, fullName, teamName), racerResult);
    }
}
