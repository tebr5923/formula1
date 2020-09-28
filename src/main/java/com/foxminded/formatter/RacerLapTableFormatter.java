package com.foxminded.formatter;

import com.foxminded.racer.Racer;
import com.foxminded.racer.RacerLap;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RacerLapTableFormatter implements Formatter<RacerLap> {
    private static final int FIRST_ROUND_QUALIFIED_POSITION = 15;
    private final int qualifiedPosition;

    public RacerLapTableFormatter() {
        this(FIRST_ROUND_QUALIFIED_POSITION);
    }

    public RacerLapTableFormatter(int qualifiedPosition) {
        this.qualifiedPosition = qualifiedPosition;
    }

    @Override
    public List<String> format(List<RacerLap> inputList) {
        List<RacerLap> sortedList = inputList.stream().sorted(Comparator.comparing(RacerLap::getRacerResult)).collect(Collectors.toList());
        RacerLap emptyRacerLap = new RacerLap(new Racer("empty", "", ""), LocalTime.now());
        int maxFullNameLength = sortedList.stream()
                .max(Comparator.comparing(racerLap -> racerLap.getRacer().getFullName().length()))
                .orElse(emptyRacerLap)
                .getRacer()
                .getFullName()
                .length();
        int maxTeamNameLength = sortedList.stream()
                .max(Comparator.comparing(racerLap -> racerLap.getRacer().getTeamName().length()))
                .orElse(emptyRacerLap)
                .getRacer()
                .getTeamName()
                .length();
        int maxStringLength = 0;
        int position = 1;
        List<String> stringList = new ArrayList<>();
        for (RacerLap racerLap : sortedList) {
            String string = formatRacerLap(racerLap, position, sortedList.size(), maxFullNameLength, maxTeamNameLength);
            stringList.add(string);
            if (maxStringLength < string.length()) {
                maxStringLength = string.length();
            }
            position++;
        }
        if (qualifiedPosition <= stringList.size()) {
            stringList.add(qualifiedPosition, repeatChar('-', maxStringLength));
        } else {
            stringList.add(repeatChar('-', maxStringLength));
        }

        return stringList;
    }

    private String formatRacerLap(RacerLap racerLap, int position, int maxPosition, int maxFullNameLength, int maxTeamNameLength) {
        String fullName = String.format("%s %s", racerLap.getRacer().getFullName(), repeatChar(' ', maxFullNameLength - racerLap.getRacer().getFullName().length()));
        String teamName = String.format("%s %s", racerLap.getRacer().getTeamName(), repeatChar(' ', maxTeamNameLength - racerLap.getRacer().getTeamName().length()));
        return String.format("%s %s| %s| %s",
                formatPosition(position, maxPosition),
                fullName,
                teamName,
                racerLap.getRacerResult().format(DateTimeFormatter.ofPattern("mm:ss.SSS"))
        );
    }

    private String formatPosition(int position, int maxPosition) {
        int length = (int) Math.ceil(Math.log10(Math.abs(maxPosition) + 0.5));
        String format = "%0" + length + "d%s";
        return String.format(format, position, ".");
    }

    private String repeatChar(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }
}
