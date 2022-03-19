package io.github.flyhero.springboot.plus.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.stream.Collectors;

/**
 * @author WangQingFei(qfwang666 @ 163.com)
 * @date 2022/3/19 15:22
 */
public class FileUtils {
    public static void appendText(String fileName, String text) {

        try {
            FileWriter writer = new FileWriter(fileName, true);
            writer.write(text);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件所有内容到字符串
     *
     * @param filePath resources下的文件路径
     * @return
     */
    public static String readToString(String filePath) {
        Resource resource = new ClassPathResource(filePath);
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(resource.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader br = new BufferedReader(isr);
        return br.lines().collect(Collectors.joining("\n"));
    }

    /**
     * 创建一个文件
     *
     * @param filePath 指定路径下的文件
     * @param txt      文件内容
     */
    public static void createNewFile(String filePath, String txt) {
        File file = new File(filePath);
        File fileDir = new File(file.getParent());
        if (!fileDir.exists()) {
            new File(file.getParent()).mkdirs();
        }
        try {
            file.createNewFile();
            Writer fw = new FileWriter(file.getAbsolutePath());
            fw.write(txt);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
