package sew.ai.utils;

import com.github.romankh3.image.comparison.ImageComparisonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.FileManager;
import sew.ai.helpers.exceptions.FileException;

import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Map;

import static java.util.Map.entry;
import static sew.ai.config.Configuration.operatingSystem;

public class FileUtil {
    static String str = null;
    private static final Logger log = LogManager.getLogger(FileManager.class);
    private static final String IMAGE_DIRECTORY = "logs" + File.separator + "images";

    private FileUtil() {
    }

    /**
     * Take the path of a javascript file in linux format and converts it to load on any OS.
     * (E.G. "src/main/resources/scripts/DragDrop.js")
     * It returns the javascript file contents as a String for execution.
     *
     * @param path String The path to find the javascript file.
     * @return String The javascript file contents.
     * @throws IOException if the file cannot be read
     */
    public static String loadJavascript(String path) throws IOException {
        path = convertPathSeparators(path);
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    /**
     * Returns the absolute path to the file searching the root directory of the project
     * and any sub directories.
     *
     * @param fileName String the full (exact) name of file to be found
     * @return File the path of the file found
     */
    public static File findFilePath(String fileName) {
        File result = searchDirectory(new File("src" + File.separator), fileName);
        if (result == null) {
            String errorMessage = StringUtil.format("Failed to locate the {} file. " +
                    "Please ensure the file exists in the src directory or its subdirectories.", fileName);
            throw new FileException(errorMessage, new FileNotFoundException(), new File(fileName));
        }
        return result;
    }

    /**
     * Returns a File handler to a file if it is found in the given directory or any
     * sub-directories.
     *
     * @param directory File the directory to start the search
     * @param fileName  String the full name of the file with extension to find
     * @return File the file that is found, null if nothing is found
     */
    protected static File searchDirectory(File directory, String fileName) {
        log.trace("Searching directory {}", directory.getAbsoluteFile());
        File searchResult = null;
        if (directory.canRead()) {
            for (File temp : directory.listFiles()) {
                if (temp.isDirectory()) {
                    searchResult = searchDirectory(temp, fileName);
                }
                else {
                    if (fileName.equals(temp.getName()))
                        searchResult = temp.getAbsoluteFile();
                }
                if (searchResult != null) {
                    break;
                }
            }
        }
        else {
            throw new FileException(new AccessDeniedException("Access denied."), directory);
        }
        return searchResult;
    }

    /**
     * Returns a valid class path for instantiating a java class given a class name.
     *
     * @param className String the name of the class (case sensitive)
     * @return String the path to the class that can be used to create an object
     */
    public static String getClassPath(String className) {
        try {
            String filePath = findFilePath(className + ".java").getPath();
            String returnValue = StringUtils.removeEnd(filePath, ".java")
                    .replace(File.separator, ".");
            return StringUtils.substringAfter(returnValue, "java.");
        } catch (FileException fe) {
            return null;
        }
    }

    /**
     * Reads an image from disk using the path passed with a File Object
     *
     * @param filePath File the file object that refers to the location of the file
     * @return BufferedImage the image file read from disk
     */
    public static BufferedImage readImage(File filePath) {
        return ImageComparisonUtil.readImageFromResources(filePath.getAbsolutePath());
    }

    public static String convertPathSeparators(String path) {
        return path.replace("/", File.separator);
    }

    public static String sanitizeString(String toSanitize) {
        return toSanitize.replaceAll("[^a-zA-Z0-9\\.\\-]",
                "_");
    }

    /**
     * Replaces Microsoft Windows special path shortcuts with their system environment equivalent within a given path
     * <br><br>
     * <b>Supported Path Shortcuts:</b>
     * <ul>
     * <li>%localappdata% <i>- Ex: C:\Users\USERNAME\AppData\Local</i></li>
     * <li>%appdata% <i>- Ex: C:\Users\USERNAME\AppData\Roaming</i></li>
     * <li>%USERPROFILE% <i>- Ex: C:\Users\USERNAME</i></li>
     * </ul>
     *
     * @param pathToProcess String path to replace windows special folder shortcuts
     * @return String the path string with windows special folder shortcuts replaced with their environment
     * equivalent path
     */
    public static String winSpecialFolderConverter(String pathToProcess) {
        String originalPath = pathToProcess;
        final String DETECTED_OS = operatingSystem();
        final Map<String, String> WINDOWS_SPECIAL_FOLDERS = Map.ofEntries(
                entry("%appdata%", System.getenv("APPDATA")),
                entry("%localappdata%", System.getenv("LOCALAPPDATA")),
                entry("%USERPROFILE%", System.getenv("USERPROFILE"))
        );
        if (DETECTED_OS.equals("windows")) {
            for (Map.Entry<String, String> entry : WINDOWS_SPECIAL_FOLDERS.entrySet()) {
                pathToProcess = StringUtils.replaceIgnoreCase(pathToProcess, entry.getKey(), entry.getValue());
                if (!pathToProcess.equals(originalPath)) {
                    break;
                }
            }
        }
        else {
            String unsupportedMessage = StringUtil.format("Currently only windows operating systems" +
                    " are supported for special folders. Your detected operating system: {}", DETECTED_OS);
            throw new FileException(unsupportedMessage, new File(pathToProcess));
        }
        return convertPathSeparators(pathToProcess);
    }

    /**
     * This method is used to get the line as a string from the file containing given
     * string.
     *
     * @param filePath
     * @param text
     * @return
     */
    public static String getLineAsStringFromFile(String filePath, String text) {
        File file = new File(filePath);
        BufferedReader br = null;
        if (file.length() == 0)
            System.out.println("File is empty!!!");
        else {
            System.out.println("File is not empty!!!");
            try {
                br = new BufferedReader(new FileReader(file));
                while ((str = br.readLine()) != null) {
                    if (str.contains(text)) {
                        break;
                    }
                    System.out.println(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    /**
     * This method is used to clear the content of the given file.
     *
     * @param filePath
     */
    public static void fileContentClear(String filePath) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
    }

    public static void main(String args[]) {
        /*String sCurrentUsersDir = System.getProperty("user.dir");
        String sFilepath = sCurrentUsersDir + File.separator + "src" + File.separator + "test" + File.separator + "java"
                + File.separator + "data" + File.separator + "mailcontent" + File.separator + "out.txt";
        FileUtil.fileContentClear(sFilepath);*/
        System.out.println(getFileWithPartialName("Usage"));
    }

    /**
     * This method used to read the text from text file
     *
     * @param filepath
     * @return
     * @throws IOException
     * @author Anil.Kumar2
     */
    public String readTextFileAsString(String filepath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
        // delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();
        String content = stringBuilder.toString();
        return content;
    }

    public static File getFile(String fileName) {
        if (FileUtil.class.getClassLoader().getResourceAsStream(fileName) != null) {
            InputStream resourceAsStream = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
            File file = new File(fileName, "");
            try {
                org.apache.commons.io.FileUtils.copyInputStreamToFile(resourceAsStream, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return file;
        }
        else {
            return new File(fileName);
        }
    }

    public static void createDirectoryIfNotExist(File directory) {
        if (!directory.exists()) {
            File dir = new File("./" + directory);
            dir.mkdirs();
        }
    }

    public static void copyFileToDirectory(File file, File directory) throws IOException {
        createDirectoryIfNotExist(directory);
        org.apache.commons.io.FileUtils.copyFileToDirectory(file, directory, true);
    }

    public static void forceDelete(File file) {
        file.delete();
    }

    public static void deleteFiles(String filePath) {
        try {
            File directory = new File(filePath);
            // Get all files in directory
            File[] files = directory.listFiles();
            for (File file : files) {
                file.delete();
                // Delete each file
                if (file.exists()) {
                    // Failed to delete file
                    System.out.println("Failed to delete " + file);
                }
            }
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileWithPartialName(String partialFileName) {
        String file = null;
        // Example directory on dos system
        Path dir = Paths.get(System.getProperty("user.dir") + File.separator + "Downloads" + File.separator);
        /**
         *
         * Create a new DirectoryStream for the above path.
         *
         * List all files within this directory that begin
         * with the letters A or B i.e "[AB)]*"
         *
         */
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, partialFileName + "*")) {
            // Print all the files to output stream
            for (Path p : stream) {
                file = String.valueOf(p.getFileName());
                //System.out.println(p.getFileName());
            }
        } catch (Exception e) {
            System.out.println("problems locating directory");
        }
        return file;
    }
    public static void deleteFile(String filePath){
        try {
            File directory = new File(filePath);
            // Get all files in directory
            File[] files = directory.listFiles();
            for (File file : files) {
                file.delete();
                // Delete each file
                if (file.exists()) {
                    // Failed to delete file
                    System.out.println("Failed to delete " + file);
                }
            }
            Thread.sleep(4000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
