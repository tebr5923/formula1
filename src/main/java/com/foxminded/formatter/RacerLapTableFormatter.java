package com.foxminded.formatter;

import com.foxminded.racer.Racer;
import com.foxminded.racer.RacerLap;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RacerLapTableFormatter implements Formatter<RacerLap> {
    public static final LocalTime DEFAULT_TIME = LocalTime.MIDNIGHT;

    @Override
    public List<String> format(List<RacerLap> inputList) {
        List<RacerLap> sortedList = sortByResult(inputList);
        int maxFullNameLength = computeMaxNameLength(sortedList, Racer::getFullName);
        int maxTeamNameLength = computeMaxNameLength(sortedList, Racer::getTeamName);
        int position = 1;
        List<String> stringList = new ArrayList<>();
        for (RacerLap racerLap : sortedList) {
            String string = formatRacerLap(racerLap, position, sortedList.size(), maxFullNameLength, maxTeamNameLength);
            stringList.add(string);
            position++;
        }
        return stringList;
    }

    private String formatRacerLap(RacerLap racerLap, int position, int maxPosition, int maxFullNameLength, int maxTeamNameLength) {
        String fullNameFormat = "%-" + maxFullNameLength + "s ";
        String fullName = String.format(fullNameFormat, racerLap.getRacer().getFullName());
        String teamNameFormat = "%-" + maxTeamNameLength + "s ";
        String teamName = String.format(teamNameFormat, racerLap.getRacer().getTeamName());
        return String.format("%s %s| %s| %s",
                formatPosition(position, maxPosition),
                fullName,
                teamName,
                racerLap.getRacerResult().format(DateTimeFormatter.ofPattern("mm:ss.SSS"))
        );
    }

    private List<RacerLap> sortByResult(List<RacerLap> racerLaps) {
        return racerLaps.stream()
                .sorted(Comparator.comparing(RacerLap::getRacerResult))
                .collect(Collectors.toList());
    }

    private int computeMaxNameLength(List<RacerLap> racerLaps, Function<Racer, String> function) {
        RacerLap emptyRacerLap = new RacerLap(new Racer("empty", "", ""), DEFAULT_TIME);
        return function.apply(racerLaps.stream()
                .max(Comparator.comparing(racerLap -> function.apply(racerLap.getRacer()).length()))
                .orElse(emptyRacerLap)
                .getRacer())
                .length();
    }

    private String formatPosition(int position, int maxPosition) {
        int length = (int) Math.ceil(Math.log10(Math.abs(maxPosition) + 0.5));
        String format = "%0" + length + "d%s";
        return String.format(format, position, ".");
    }
}
