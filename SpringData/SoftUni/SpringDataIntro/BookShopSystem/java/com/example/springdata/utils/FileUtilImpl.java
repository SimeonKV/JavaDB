package com.example.springdata.utils;

import com.sun.jdi.Field;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Component
public class FileUtilImpl implements FileUtil {
    @Override
    public String[] readFileContent(String filePath) throws IOException {
        File file = new File(filePath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Set<String> result = new LinkedHashSet<>();
        String line = bufferedReader.readLine();

        while(line != null){
            if(!line.equals("")){
                result.add(line);
            }

            line = bufferedReader.readLine();
        }

        return result.toArray(String[]::new);
    }
}
