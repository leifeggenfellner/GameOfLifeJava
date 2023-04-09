package game.gameoflife;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

/**
 * The FileReadWriteTest class contains JUnit test cases for testing the save
 * and load functionality of
 * a FileHandler class.
 */
public class FileReadWriteTest {
    /**
     * This is a JUnit test case that checks if a 2D array is saved to a file
     * correctly.
     * 
     * @param tempDir A temporary directory that is created by JUnit for the
     *                duration of the test. It
     *                is used as the location to save the file being tested.
     */
    @Test
    void testSaveFile(@TempDir File tempDir) throws IOException {
        int[][] grid = { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 } };
        String fileName = "testSaveFile.txt";
        String expectedContent = "0,1,0\n1,0,1\n0,1,0\n";
        String filePath = tempDir.getAbsolutePath() + File.separator + fileName;
        FileHandler fileHandler = new FileHandler();
        fileHandler.saveFile(filePath, grid);
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder content = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        assertTrue(new File(filePath).exists());
        assertEquals(expectedContent, content.toString());
    }

    /**
     * This is a JUnit test case that checks if a file is loaded correctly and its
     * contents are
     * converted into a 2D integer array.
     * 
     * @param tempDir A temporary directory that is created by JUnit for the
     *                duration of the test. It
     *                is used to create a temporary file for testing the file
     *                loading functionality.
     */
    @Test
    void testLoadFile(@TempDir File tempDir) throws IOException {
        int[][] expectedGrid = { { 0, 1, 0 }, { 1, 0, 1 }, { 0, 1, 0 } };
        String fileName = "testLoadFile.txt";
        String filePath = tempDir.getAbsolutePath() + File.separator + fileName;
        String fileContent = "0,1,0\n1,0,1\n0,1,0\n";
        File file = new File(filePath);
        FileWriter writer = new FileWriter(file);
        writer.write(fileContent);
        writer.close();
        FileHandler fileHandler = new FileHandler();
        int[][] grid = fileHandler.loadFile(filePath);
        assertTrue(Arrays.deepEquals(expectedGrid, grid));
    }
}