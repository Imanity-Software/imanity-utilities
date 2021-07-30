package org.imanity.utilities.type;

public class VersionHolderWildcard implements VersionHolder {

    public static final VersionHolderWildcard INSTANCE = new VersionHolderWildcard();

    @Override
    public int getNum() {
        return -1;
    }

    @Override
    public boolean equals(int num) {
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
    public boolean equals(Object o) {
        return o instanceof VersionHolderWildcard;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
