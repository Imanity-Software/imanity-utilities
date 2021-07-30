package org.imanity.utilities.type;

import lombok.RequiredArgsConstructor;

import java.text.DecimalFormat;
import java.util.Objects;

@RequiredArgsConstructor
public class VersionHolderNumber implements VersionHolder {

    private final int num;
    private final DecimalFormat decimalFormat;

    @Override
    public int getNum() {
        return this.num;
    }

    @Override
    public boolean equals(VersionHolder versionHolder) {
        if (versionHolder instanceof VersionHolderWildcard) {
            return true;
        }
        return this.num == versionHolder.getNum();
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
        return this.decimalFormat != null ? this.decimalFormat.format(this.num) : this.num + "";
    }

    @Override
    public int hashCode() {
        return Objects.hash(num);
    }
}
