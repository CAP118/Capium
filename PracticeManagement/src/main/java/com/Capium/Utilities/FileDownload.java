package com.Capium.Utilities;

import java.io.File;

public class FileDownload {

    // Default download folder
    private static final String DEFAULT_DOWNLOAD_DIR = System.getProperty("user.home") + "/Downloads";

    /**
     * Verify if a file with the given extension is downloaded in the default directory.
     * @param extension e.g. "pdf", "xlsx", "csv"
     * @param timeoutSeconds how long to wait for the file
     * @return true if found, false otherwise
     */
    public static boolean isFileDownloaded(String extension, int timeoutSeconds) {
        return isFileDownloaded(DEFAULT_DOWNLOAD_DIR, extension, timeoutSeconds);
    }

    /**
     * Verify if a file with the given extension is downloaded in the given directory.
     * @param directory directory path to check
     * @param extension e.g. "pdf", "xlsx", "csv"
     * @param timeoutSeconds how long to wait for the file
     * @return true if found, false otherwise
     */
    public static boolean isFileDownloaded(String directory, String extension, int timeoutSeconds) {
        File dir = new File(directory);
        long waitUntil = System.currentTimeMillis() + (timeoutSeconds * 1000);

        while (System.currentTimeMillis() < waitUntil) {
            File[] files = dir.listFiles((d, name) -> name.toLowerCase().endsWith("." + extension));
            if (files != null && files.length > 0) {
                // Find latest file
                File latestFile = files[0];
                for (File file : files) {
                    if (file.lastModified() > latestFile.lastModified()) {
                        latestFile = file;
                    }
                }
                return true; 
            }

            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return false; // Not found
    }
}
