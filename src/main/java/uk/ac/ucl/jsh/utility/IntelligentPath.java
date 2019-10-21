package uk.ac.ucl.jsh.utility;

import uk.ac.ucl.jsh.Descriptor;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntelligentPath {
    private static Path homeDirectory = Paths.get(System.getProperty("user.home"));
    private static String fileSeparator = System.getProperty("file.separator");
    private static Descriptor systemType = fileSeparator.equals("\\") ? Descriptor.Windows : Descriptor.Unix;

    public static Path getPath(String str, Path currentDirectory) throws RuntimeException {
        switch (str) {
            case "~":
                return homeDirectory;
            case ".":
                return currentDirectory;
            case "..":
                return currentDirectory.getParent();
        }
        Path uPath;
        if (systemType == Descriptor.Windows) {
            uPath =  getPathOnWindows(str, currentDirectory);
        } else {
            uPath = getPathOnUnix(str, currentDirectory);
        }

        if (Files.exists(uPath)) {
            return uPath;
        } else {
            throw new RuntimeException("File/Path does not exist!");
        }
    }

    private static Path getPathOnWindows(String str, Path currentDirectory) {
        String windowsStartRegex = "([A-Za-z]:\\\\)";
        Pattern regex = Pattern.compile(windowsStartRegex);
        Matcher regexMatcher = regex.matcher(str);
        if (regexMatcher.find()) {
             return Paths.get(str);
        } else {
            return Paths.get(currentDirectory.toAbsolutePath().toString() + fileSeparator + str);
        }
    }

    private static Path getPathOnUnix(String str, Path currentDirectory) {
        if (str.startsWith("/")) {
            return Paths.get(str);
        } else if (str.startsWith("./")) {
            return Paths.get(currentDirectory.toAbsolutePath().toString() + fileSeparator + str.substring(1));
        } else if (str.startsWith("../")) {
            return Paths.get(currentDirectory.getParent().toAbsolutePath().toString() + fileSeparator + str.substring(2));
        } else {
            return Paths.get(currentDirectory.toAbsolutePath().toString() + fileSeparator + str);
        }
    }
}
