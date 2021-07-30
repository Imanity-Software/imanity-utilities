package org.imanity.utilities;

import lombok.*;
import org.imanity.utilities.type.VersionHolder;
import org.imanity.utilities.type.VersionHolderWildcard;

import java.text.DecimalFormat;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Version implements Comparable<Version> {

    private static final Pattern VERSION_PATTERN = Pattern.compile("(?<year>[0-9*]{4,})\\.(?<month>[0-9*]{1,2})\\.(?<release>[0-9*]+) (?<lts>(?:LTS\\s)*)BUILD (?<build>[0-9*]+)");

    public static Version parseVersion(String version) {
        final Matcher matcher = VERSION_PATTERN.matcher(version);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Incorrect version format: " + version);
        }

        return Version.builder()
                .year(VersionHolder.fromString(matcher.group("year"), FOUR_DIGITS_FORMAT))
                .month(VersionHolder.fromString(matcher.group("month"), TWO_DIGITS_FORMAT))
                .releaseId(VersionHolder.fromString(matcher.group("release"), null))
                .lts(!matcher.group("lts").isEmpty())
                .buildId(VersionHolder.fromString(matcher.group("build"), null))
                .build();
    }

    public static final DecimalFormat
            FOUR_DIGITS_FORMAT = new DecimalFormat("0000"),
            TWO_DIGITS_FORMAT = new DecimalFormat("00");

    private VersionHolder year, month, releaseId, buildId;
    private boolean lts;

    @Override
    public String toString() {
        return this.year.toString() + "." + this.month.toString() + "." + this.releaseId.toString() + (this.lts ? " LTS " : " ") + "BUILD " + this.buildId.toString();
    }

    public String toReleaseString() {
        return this.year.toString() + "." + this.month.toString() + "." + this.releaseId.toString() + (this.lts ? " LTS" : "");
    }

    public boolean hasWildcard() {
        return this.year instanceof VersionHolderWildcard || this.month instanceof VersionHolderWildcard || this.releaseId instanceof VersionHolderWildcard || this.buildId instanceof VersionHolderWildcard;
    }

    public boolean isAbove(Version version) {
        return this.compareTo(version) > 0;
    }

    public boolean isBelow(Version version) {
        return this.compareTo(version) < 0;
    }

    public boolean isIdenticalRelease(Version version) {
        return version.year.equals(this.year) && version.month.equals(this.month) && version.releaseId.equals(this.releaseId) && version.lts == this.lts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version = (Version) o;
        return lts == version.lts && year.equals(version.getYear()) && month.equals(version.getMonth()) && releaseId.equals(version.getReleaseId()) && buildId.equals(version.getBuildId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, releaseId, buildId, lts);
    }

    @Override
    public int compareTo(Version version) {
        if (this.equals(version)) {
            return 0;
        }

        if (!this.year.equals(version.getYear())) {
            return this.year.compareTo(version.getYear());
        }

        if (!this.month.equals(version.getMonth())) {
            return this.month.compareTo(version.getMonth());
        }

        if (!this.releaseId.equals(version.getReleaseId())) {
            return this.releaseId.compareTo(version.getReleaseId());
        }

        if (this.lts != version.isLts()) {
            if (this.lts) {
                return Integer.MAX_VALUE;
            } else {
                return Integer.MIN_VALUE;
            }
        }

        return this.buildId.compareTo(version.getBuildId());
    }
}
