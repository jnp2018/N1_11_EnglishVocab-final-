/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnglishVocab_test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author kiet
 */
public class MainTCP {

    public static void main(String[] args) throws IOException, Exception {
        GiaoDien gd = new GiaoDien("English Vocab");
        gd.showGiao();
        gd.Xuly();  
    }
}
