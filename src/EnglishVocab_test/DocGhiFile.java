/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnglishVocab_test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author kiet
 */
public class DocGhiFile {

    public static boolean ghiFile(ArrayList<String> dsData, String path) {
        try {
            FileOutputStream fos = new FileOutputStream(path, true);
            OutputStreamWriter os = new OutputStreamWriter(fos, "UTF-8");
            BufferedWriter ws = new BufferedWriter(os);
            for (String data : dsData) {
                ws.write(data);
                ws.newLine();
            }
            ws.close();
            os.close();
            fos.close();
            return true;

        } catch (Exception e) {

            e.printStackTrace();
        }
        return false;
    }

    public static ArrayList<String> docFile(String path) {
        ArrayList<String> dsData = new ArrayList<String>();
        try {
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader is = new InputStreamReader(fis, "UTF-8");
            BufferedReader rs = new BufferedReader(is);
            String line = rs.readLine();
            while (line != null) {
                if (line.length() > 0) {
                    dsData.add(line);
                }
                line = rs.readLine();
            }
            rs.close();
            is.close();
            fis.close();
            return dsData;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }

}
