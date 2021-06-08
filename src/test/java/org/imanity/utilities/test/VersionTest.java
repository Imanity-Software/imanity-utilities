package org.imanity.utilities.test;

import org.imanity.utilities.Version;
import org.junit.Assert;
import org.junit.Test;

import java.util.TreeSet;

public class VersionTest {

    @Test
    public void checkYear() {
        Version lowerVersion = Version.parseVersion("2021.07.1 LTS BUILD 1");
        Version newerVersion = Version.parseVersion("2022.07.1 LTS BUILD 1");

        Assert.assertTrue(newerVersion.isAbove(lowerVersion));
    }

    @Test
    public void checkMonth() {
        Version lowerVersion = Version.parseVersion("2021.07.1 LTS BUILD 1");
        Version newerVersion = Version.parseVersion("2021.08.1 LTS BUILD 1");

        Assert.assertTrue(newerVersion.isAbove(lowerVersion));
    }

    @Test
    public void checkVersion() {
        Version lowerVersion = Version.parseVersion("2021.07.1 LTS BUILD 1");
        Version newerVersion = Version.parseVersion("2021.07.2 LTS BUILD 1");

        Assert.assertTrue(newerVersion.isAbove(lowerVersion));
    }

    @Test
    public void checkBuildVersion() {
        Version lowerVersion = Version.parseVersion("2021.07.1 LTS BUILD 1");
        Version newerVersion = Version.parseVersion("2021.07.1 LTS BUILD 2");

        Assert.assertTrue(newerVersion.isAbove(lowerVersion));
    }

    @Test
    public void checkLTS() {
        Version lowerVersion = Version.parseVersion("2021.07.1 BUILD 1");
        Version newerVersion = Version.parseVersion("2021.07.1 LTS BUILD 1");

        Assert.assertTrue(newerVersion.isAbove(lowerVersion));
    }

    @Test
    public void checkIdenticalRelease() {
        Version lowerVersion = Version.parseVersion("2021.07.1 LTS BUILD 1");
        Version newerVersion = Version.parseVersion("2021.07.1 LTS BUILD 2");

        Assert.assertTrue(newerVersion.isIdenticalRelease(lowerVersion));
    }

    @Test
    public void checkToString() {
        Version version = Version.parseVersion("2021.07.1 LTS BUILD 22");

        Assert.assertEquals(version.toString(), "2021.07.1 LTS BUILD 22");
    }

    @Test
    public void checkToReleaseString() {
        Version version = Version.parseVersion("2021.07.1 LTS BUILD 22");

        Assert.assertEquals(version.toReleaseString(), "2021.07.1 LTS");
    }

    @Test
    public void checkTreeMap() {
        Version lowerVersion = Version.parseVersion("2021.07.1 LTS BUILD 1");
        Version newerVersion = Version.parseVersion("2021.07.1 LTS BUILD 2");

        TreeSet<Version> treeSet = new TreeSet<>();
        treeSet.add(newerVersion);
        treeSet.add(lowerVersion);

        Assert.assertEquals(newerVersion, treeSet.last());
    }

}
