package com.carlos.usuarios.beusers.clases;
import java.io.File;
import java.time.LocalDateTime;

public class FileUtil {

    public static String generateFileName(String extension) {
        LocalDateTime now = LocalDateTime.now();
        String fileName = String.format("%04d%02d%02d%02d%02d%02d%s",
                now.getYear(),
                now.getMonthValue(),
                now.getDayOfMonth(),
                now.getHour(),
                now.getMinute(),
                now.getSecond(),
                extension);
        return fileName;
    }

    public static String generateFilePath(String lastFolder, String fileName) {
        String directory = "C:\\Users\\tradi\\OneDrive\\Documentos\\trading\\front\\files\\";
        return directory + lastFolder + "\\" + fileName;
    }

    public static void deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            file.delete();
        }
    }
}
