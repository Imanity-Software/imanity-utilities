package org.imanity.utilities.type;

import java.text.DecimalFormat;

public interface VersionHolder {

    int getNum();

    boolean equals(VersionHolder versionHolder);

    int compareTo(VersionHolder versionHolder);

    String toString();

    static VersionHolder fromString(String s, DecimalFormat decimalFormat) {
        if (s.equals("*")) {
            return VersionHolderWildcard.INSTANCE;
        }
        return new VersionHolderNumber(Integer.parseInt(s), decimalFormat);
    }

}
