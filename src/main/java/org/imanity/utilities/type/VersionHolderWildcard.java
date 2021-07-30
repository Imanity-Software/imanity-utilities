package org.imanity.utilities.type;

public class VersionHolderWildcard implements VersionHolder {

    public static final VersionHolderWildcard INSTANCE = new VersionHolderWildcard();

    @Override
    public int getNum() {
        return -1;
    }

    @Override
    public boolean equals(VersionHolder versionHolder) {
        return true;
    }

    @Override
    public int compareTo(VersionHolder versionHolder) {
        return 0;
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
