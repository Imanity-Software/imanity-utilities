package org.imanity.utilities.type;

public interface VersionHolder {

    int getNum();

    boolean equals(int num);

    int compareTo(VersionHolder versionHolder);

    String toString();

    static VersionHolder fromString(String s) {
        if (s.equals("*")) {
            return VersionHolderWildcard.INSTANCE;
        }
        return new VersionHolderNumber(Integer.parseInt(s));
    }

}
