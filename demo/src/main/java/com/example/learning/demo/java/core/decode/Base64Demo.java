package com.example.learning.demo.java.core.decode;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import javax.imageio.stream.FileImageOutputStream;
import java.io.*;
import java.util.Base64;
import java.util.Objects;

/**
 * @author liyan
 */
public class Base64Demo {

    public static void main(String[] args) {
        File jsonTxt = new File( Objects.requireNonNull(Base64Demo.class.getClassLoader().getResource("ib64.txt")).getPath());
        //读取txt中的json数据
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonTxt))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JSONObject jsonObject = JSON.parseObject(content.toString());
        String image = jsonObject.getString("image");
        File imgFile = new File("ib64.jpg");
        try (FileImageOutputStream fios = new FileImageOutputStream(imgFile)) {
            byte[] decode = Base64.getDecoder().decode(image);
            fios.write(decode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
