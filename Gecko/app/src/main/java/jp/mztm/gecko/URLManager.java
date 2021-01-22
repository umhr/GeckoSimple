package jp.mztm.gecko;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class URLManager {
    public String defaultURL = "about:buildconfig";
    public String home = "about:buildconfig";
    public String develop = "file:///android_asset/develop.html";
    public File file;

    public URLManager(File path){
        String filename ="homeurl.txt";
        file = new File(path, filename);
        read();
    }

    //[Android] 外部ストレージにファイルを保存する WRITE_EXTERNAL_STORAGE
    //https://akira-watson.com/android/external-storage-file.html
    public void write(String str){
        try(FileOutputStream fileOutputStream =
                    new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            BufferedWriter bw =
                    new BufferedWriter(outputStreamWriter);) {
            bw.write(str);
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(){
        String str = defaultURL;

        try(FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(inputStreamReader); ) {
            String lineBuffer;
            while( (lineBuffer = reader.readLine()) != null ) {
                str = lineBuffer ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        home = str;
    }
}
