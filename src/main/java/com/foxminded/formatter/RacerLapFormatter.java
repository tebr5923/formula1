package com.foxminded.formatter;

import com.foxminded.racer.Racer;
import com.foxminded.racer.RacerLap;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RacerLapFormatter implements Formatter<RacerLap> {
    private static final int FIRST_ROUND_QUALIFIED_POSITION = 15;
    private final int qualifiedPosition;

    public RacerLapFormatter() {
        this(FIRST_ROUND_QUALIFIED_POSITION);
    }

    public RacerLapFormatter(int qualifiedPosition) {
        this.qualifiedPosition = qualifiedPosition;
    }

    @Override
    public List<String> format(List<RacerLap> inputList) {
        inputList.sort(Comparator.comparing(RacerLap::getRacerResult));
        RacerLap emptyRacerLap = new RacerLap(new Racer("empty", "", ""), LocalTime.now());
        int maxFullNameLength = inputList.stream()
                .max(Comparator.comparing(racerLap -> racerLap.getRacer().getFullName().length()))
                .orElse(emptyRacerLap)
                .getRacer()
                .getFullName()
                .length();
        int maxTeamNameLength = inputList.stream()
                .max(Comparator.comparing(racerLap -> racerLap.getRacer().getTeamName().length()))
                .orElse(emptyRacerLap)
                .getRacer()
                .getTeamName()
                .length();
        int maxStringLength = 0;
        int position = 1;
        List<String> stringList = new ArrayList<>();
        for (RacerLap racerLap : inputList) {
            String string = formatRacerLap(racerLap, position, maxFullNameLength, maxTeamNameLength);
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

    private String formatRacerLap(RacerLap racerLap, int position, int maxFullNameLength, int maxTeamNameLength) {
        String fullName = String.format("%s %s", racerLap.getRacer().getFullName(), repeatChar(' ', maxFullNameLength - racerLap.getRacer().getFullName().length()));
        String teamName = String.format("%s %s", racerLap.getRacer().getTeamName(), repeatChar(' ', maxTeamNameLength - racerLap.getRacer().getTeamName().length()));
        return String.format("%s %s| %s| %s",
                formatPosition(position),
                fullName,
                teamName,
                racerLap.getRacerResult().format(DateTimeFormatter.ofPattern("mm:ss.SSS"))
        );
    }

    private String formatPosition(int position) {
        String positionAsString = position + ".";
        if (position <= 9) {
            positionAsString = " " + positionAsString;
        }
        return positionAsString;
    }

    private String repeatChar(char ch, int times) {
        char[] chars = new char[times];
        Arrays.fill(chars, ch);
        return new String(chars);
    }
}
