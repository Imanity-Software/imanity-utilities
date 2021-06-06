package org.imanity.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Version implements Comparable<Version> {

    private static final Pattern VERSION_PATTERN = Pattern.compile("(?<year>[0-9]{4,})\\.(?<month>[0-9]{2,})\\.(?<release>[0-9]) (?<lts>(?:LTS\\s)*)BUILD (?<build>[0-9])");

    public static Version parseVersion(String version) {
        final Matcher matcher = VERSION_PATTERN.matcher(version);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Incorrect version format: " + version);
        }

        return Version.builder()
                .year(Integer.parseInt(matcher.group("year")))
                .month(Integer.parseInt(matcher.group("month")))
                .releaseId(Integer.parseInt(matcher.group("release")))
                .lts(!matcher.group("lts").isEmpty())
                .buildId(Integer.parseInt(matcher.group("build")))
                .build();
    }

    private static final DecimalFormat
            FOUR_DIGITS_FORMAT = new DecimalFormat("0000"),
            TWO_DIGITS_FORMAT = new DecimalFormat("00");

    private int year, month, releaseId, buildId;
    private boolean lts;

    @Override
    public String toString() {
        return FOUR_DIGITS_FORMAT.format(this.year) + "." + TWO_DIGITS_FORMAT.format(this.month) + "." + this.releaseId + (this.lts ? " LTS " : " ") + TWO_DIGITS_FORMAT.format(this.buildId);
    }

    public boolean isAbove(Version version) {
        return this.compareTo(version) > 0;
    }

    public boolean isBelow(Version version) {
        return this.compareTo(version) < 0;
    }

    public boolean isIdenticalRelease(Version version) {
        return version.year == this.year && version.month == this.month && version.releaseId == this.releaseId && version.lts == this.lts;
    }

    @Override
    public int compareTo(Version version) {
        if (this.equals(version)) {
            return 0;
        }

        if (this.year != version.getYear()) {
            return this.year - version.getYear();
        }

        if (this.month != version.getMonth()) {
            return this.month - version.getMonth();
        }

        if (this.releaseId != version.getReleaseId()) {
            return this.releaseId - version.getReleaseId();
        }

        if (this.lts != version.isLts()) {
            if (this.lts) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        return this.buildId - version.getReleaseId();
    }
}
