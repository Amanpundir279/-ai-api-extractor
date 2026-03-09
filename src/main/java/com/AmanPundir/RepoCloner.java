package com.AmanPundir;

import org.eclipse.jgit.api.Git;
import java.io.File;

public class RepoCloner {

    public static String cloneRepo(String repoUrl) {

        String localPath = "cloned-repo";
        File repoDir = new File(localPath);

        try {

            if (repoDir.exists()) {
                System.out.println("Repository already exists. Skipping clone.");
                return localPath;
            }

            System.out.println("Cloning repository...");

            Git.cloneRepository()
                    .setURI(repoUrl)
                    .setDirectory(repoDir)
                    .call();

            System.out.println("Repository cloned successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return localPath;
    }
}