package org.imanity.utilities.type;

import lombok.RequiredArgsConstructor;

import java.util.Objects;

@RequiredArgsConstructor
public class VersionHolderNumber implements VersionHolder {

    private final int num;

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public boolean equals(int num) {
        return this.num == num;
    }

    @Override
    public int compareTo(VersionHolder versionHolder) {
        if (versionHolder instanceof VersionHolderWildcard) {
            return 0;
        }
        return this.num - versionHolder.getNum();
    }

    @Override
    public String toString() {
        return this.num + "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersionHolderNumber that = (VersionHolderNumber) o;
        return num == that.num;
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
