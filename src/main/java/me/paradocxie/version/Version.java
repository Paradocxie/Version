package me.paradocxie.version;

public class Version {
    private final int majorVersion;
    private final int minorVersion;
    private final int patchVersion;

    public Version(int majorVersion, int minorVersion, int patchVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = patchVersion;
    }

    public Version(int majorVersion, int minorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = minorVersion;
        this.patchVersion = 0;
    }

    public Version(int majorVersion) {
        this.majorVersion = majorVersion;
        this.minorVersion = 0;
        this.patchVersion = 0;
    }

    public static Version fromString(String version) {
        String[] parts = version.split("\\.");
        if (parts.length == 3) {
            return new Version(parseInt(parts[0], 1), parseInt(parts[1], 0), parseInt(parts[2], 0));
        } else if (parts.length == 2) {
            return new Version(parseInt(parts[0], 1), parseInt(parts[1], 0));
        } else if (parts.length == 1) {
            return new Version(parseInt(parts[0], 1));
        }
        return new Version(1);
    }

    public boolean isHigherThan(Version version) {
        if (version == null || this.getMajorVersion() > version.getMajorVersion()) {
            return true;
        } else if (this.getMajorVersion() == version.getMajorVersion()) {
            if (this.getMinorVersion() > version.getMinorVersion()) {
                return true;
            } else if (this.getMinorVersion() == version.getMinorVersion()) {
                return this.getPatchVersion() > version.getPatchVersion();
            }
        }
        return false;
    }

    public static int parseInt(String intString, int fallback) {
        try {
            return Integer.parseInt(intString);
        } catch (NumberFormatException e) {
            return fallback;
        }
    }

    public int getPatchVersion() {
        return patchVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    @Override
    public String toString() {
        return getMajorVersion() + "." + getMinorVersion() + "." + getPatchVersion();
    }
}
