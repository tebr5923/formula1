package com.foxminded.formatter;

import com.foxminded.racer.Racer;
import com.foxminded.racer.RacerLap;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RacerLapTableFormatter implements Formatter<RacerLap> {

    @Override
    public List<String> format(List<RacerLap> inputList) {
        List<RacerLap> sortedList = sortByResult(inputList);
        int maxFullNameLength = computeMaxNameLength(sortedList, Racer::getFullName);
        int maxTeamNameLength = computeMaxNameLength(sortedList, Racer::getTeamName);
        int position = 1;
        InnerRacerLapFormatter innerRacerLapFormatter =
                new InnerRacerLapFormatter(sortedList.size(), maxFullNameLength, maxTeamNameLength);
        List<String> stringList = new ArrayList<>();
        for (RacerLap racerLap : sortedList) {
            String string = innerRacerLapFormatter.formatRacerLap(racerLap, position);
            stringList.add(string);
            position++;
        }
        return stringList;
    }

    private List<RacerLap> sortByResult(List<RacerLap> racerLaps) {
        return racerLaps.stream()
                .sorted(Comparator.comparing(RacerLap::getRacerResult))
                .collect(Collectors.toList());
    }

    private int computeMaxNameLength(List<RacerLap> racerLaps, Function<Racer, String> function) {
        return racerLaps.stream()
                .map(RacerLap::getRacer)
                .map(function)
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private class InnerRacerLapFormatter {
        final int maxPosition;
        final int maxFullNameLength;
        final int maxTeamNameLength;

        private InnerRacerLapFormatter(int maxPosition, int maxFullNameLength, int maxTeamNameLength) {
            this.maxPosition = maxPosition;
            this.maxFullNameLength = maxFullNameLength;
            this.maxTeamNameLength = maxTeamNameLength;
        }

        private String formatPosition(int position, int maxPosition) {
            int length = (int) Math.ceil(Math.log10(maxPosition + 0.5));
            String format = "%0" + length + "d%s";
            return String.format(format, position, ".");
        }

        private String formatRacerLap(RacerLap racerLap, int position) {
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
    }
}
