package com.foxminded.formatter;

import com.foxminded.racer.Racer;
import com.foxminded.racer.RacerLap;
import com.foxminded.table.RacerLapTable;
import com.foxminded.table.Row;
import com.foxminded.table.Table;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class RacerLapTableFormatter implements Formatter<RacerLap> {
    public static final LocalTime DEFAULT_TIME = LocalTime.MIDNIGHT;
    private static final int FIRST_ROUND_QUALIFIED_POSITION = 15;
    private final int qualifiedPosition;

    public RacerLapTableFormatter() {
        this(FIRST_ROUND_QUALIFIED_POSITION);
    }

    public RacerLapTableFormatter(int qualifiedPosition) {
        this.qualifiedPosition = qualifiedPosition;
    }

    @Override
    public Iterator<String> formatTable(Table<RacerLap> inputTable) {
        List<RacerLap> sortedList = inputTable.getListValue()
                .stream()
                .sorted(Comparator.comparing(RacerLap::getRacerResult))
                .collect(Collectors.toList());
        Table<RacerLap> sortedTable = new RacerLapTable(sortedList);
        int maxFullNameLength = sortedTable.getMaxCellLength(1, String.class);
        int maxTeamNameLength = sortedTable.getMaxCellLength(2, String.class);
        List<String> stringList = new ArrayList<>();
        int maxStringLength = 0;
        int position = 1;
        // method
        for (Row<RacerLap> row : sortedTable) {
            String string = formatPosition(position, sortedTable.getNumberOfRows())
                    + formatRow(row, maxFullNameLength, maxTeamNameLength);
            stringList.add(string);
            maxStringLength = Math.max(maxStringLength, string.length());
            position++;
        }
        String delimiter = repeatChar('-', maxStringLength);
        return new DelimiterStingIterator(stringList, delimiter);
    }

    private String formatRow(Row<RacerLap> row, int maxFullNameLength, int maxTeamNameLength) {
        String format = " %-" + maxFullNameLength + "s |" + " %-" + maxTeamNameLength + "s |" + " %s";
        return String.format(format,
                row.getCell(1, String.class),
                row.getCell(2, String.class),
                row.getCell(3, LocalTime.class).format(DateTimeFormatter.ofPattern("mm:ss.SSS")));
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

//----------------------------------------------------------------------
    @Override
    public List<String> format(List<RacerLap> inputList) {
        List<RacerLap> sortedList = inputList.stream().sorted(Comparator.comparing(RacerLap::getRacerResult)).collect(Collectors.toList());
        RacerLap emptyRacerLap = new RacerLap(new Racer("empty", "", ""), DEFAULT_TIME);
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
            maxStringLength = Math.max(maxStringLength, string.length());
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
