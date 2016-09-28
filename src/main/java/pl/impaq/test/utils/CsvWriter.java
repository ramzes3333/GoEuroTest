package pl.impaq.test.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Arrays;

/**
 * Created by andrew on 28.09.16.
 */
public class CsvWriter {

    private static final String DEFAULT_ENCODING = "UTF-8";

    private OutputStream outputStream;
    private Writer writer;

    private String csvFileName;
    private String separator;

    public CsvWriter(String csvFileName) {
        PropertiesLoader propertiesLoader = PropertiesLoader.getInstance();
        this.csvFileName = csvFileName;
        this.separator = propertiesLoader.getPropertyValue("separator");
    }

    public void openFile() {

        try {
            outputStream = new FileOutputStream(csvFileName, false);
            writer = new BufferedWriter(new OutputStreamWriter(outputStream, DEFAULT_ENCODING));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeLine(String[] elements) {
        if(ArrayUtils.isEmpty(elements)) {
            return;
        }

        if(writer == null) {
            throw new RuntimeException("You must first open file");
        }

        try {
            StringBuilder sb = new StringBuilder();
            Arrays.stream(elements)
                    .forEach(x -> sb.append(x).append(separator));

            String line = sb.subSequence(0, sb.length()-separator.length()).toString();

            writer.write(line);
            writer.write("\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void flush() {
        try {
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
