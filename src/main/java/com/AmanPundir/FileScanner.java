package com.AmanPundir;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileScanner {

    public static List<File> findJSFiles(String repoPath) {

        List<File> jsFiles = new ArrayList<>();

        File root = new File(repoPath);

        scan(root, jsFiles);

        return jsFiles;
    }

    private static void scan(File file, List<File> jsFiles) {

        if (file == null) return;

        if (file.isDirectory()) {

            File[] children = file.listFiles();

            if (children != null) {

                for (File child : children) {
                    scan(child, jsFiles);
                }

            }

        } else {

            String name = file.getName();

            if (name.endsWith(".js") || name.endsWith(".ts")) {

                jsFiles.add(file);
            }

        }
    }
}