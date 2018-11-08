/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnglishVocab_test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author kiet
 */
public class TCPServer extends Thread {
//    ArrayList<>
    Socket s = null;
    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> dsData = new ArrayList<String>();
    private static char arr[];
    private static Random rd = new Random();
    private static boolean name = false;

    public TCPServer() {
    }

    public TCPServer(Socket sk) {
        s = sk;
    }

    public static void main(String[] args) throws Exception {
        do {
            System.out.println("1. Add question file");
            System.out.println("2. Start game server");
            System.out.println("3. Exit");
            int chon = new Scanner(System.in).nextInt();
            switch (chon) {
                case 1:
                    ArrayList<String> arr1;
                    arr1 = DocGhiFile.docFile("file");
                    boolean kq = DocGhiFile.ghiFile(arr1, "Vocab");
                    if (kq == true) {
                        System.out.println("File added successfully!");
                    } else {
                        System.out.println("File not existed or wrong filename.");
                    }
                    break;
                case 2:
                    System.out.println("Server status: Waiting for players...");
                    ServerSocket server = new ServerSocket(1000);
                    for (int i = 0; i < 2; i++) {
                        Socket socket = server.accept();
                        TCPServer tcp = new TCPServer(socket);
                        tcp.setName("Player: " + i);
                        System.out.println(tcp.getName() + " connected!");
                        tcp.start();

                    }
                    break;
                case 3:
                    System.exit(0);
                    break;

            }
        } while (true);
    }

    @Override
    public void run() {
        int so = 0;
        int dem = 0;
        int tong = 0;
        ArrayList<String> ds;
        ds = DocGhiFile.docFile("Vocab");
        Collections.shuffle(ds);
        try {
            DataInputStream in = new DataInputStream(s.getInputStream());
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            int chon = in.read();
            System.out.println(chon);
            switch (chon) {
                case 0:
                    for (int i = 0; i < 20; i++) {
                        tong = tong + 1;
                        out.writeUTF(Dem(ds.get(i)));
                        System.out.println("Server sent: " + Dem(ds.get(i)));
                        out.flush();
                        String line = in.readUTF();
                        System.out.println("Server received: " + line);
                        if (line.equalsIgnoreCase(ds.get(i))) {
                            so++;
                            dem = dem + 1;
                            out.writeUTF("Correct!");
                            out.write(dem);
                            out.flush();
                        } else {
                            out.writeUTF("Wrong.");
                            dem = dem - 1;
                            if (dem < 0) {
                                dem = 0;
                            }
                            out.write(dem);
                            out.flush();
                        }
                        out.writeUTF(so + "/" + tong);
                        out.flush();
                        if (i == 19) {
                            s.close();
                            in.close();
                            out.close();
                        }

                    }
                    break;
                case 1:
                    for (int i = 20; i < 45; i++) {
                        tong = tong + 1;
                        out.writeUTF(Dem(ds.get(i)));
                        System.out.println("Server sent: " + Dem(ds.get(i)));
                        out.flush();
                        String line = in.readUTF();
                        System.out.println("Server received: " + line);
                        if (line.equalsIgnoreCase(ds.get(i))) {
                            so++;
                            dem = dem + 1;
                            out.writeUTF("Correct!");
                            out.write(dem);
                            out.flush();
                        } else {
                            out.writeUTF("Wrong.");
                            dem = dem - 1;
                            if (dem < 0) {
                                dem = 0;
                            }
                            out.write(dem);
                            out.flush();
                        }
                        out.writeUTF(so + "/" + tong);
                        out.flush();
                        if (i == 44) {
                            s.close();
                            in.close();
                            out.close();
                        }

                    }
                    break;
                case 2:
                    for (int i = 45; i < 75; i++) {
                        tong = tong + 1;
                        out.writeUTF(Dem(ds.get(i)));
                        System.out.println("Server sent: " + Dem(ds.get(i)));
                        out.flush();
                        String line = in.readUTF();
                        System.out.println("Server received: " + line);
                        if (line.equalsIgnoreCase("Exit")) {
                            break;
                        } else {
                            if (line.equalsIgnoreCase(ds.get(i))) {
                                so++;
                                dem = dem + 1;
                                out.writeUTF("Correct!");
                                out.write(dem);
                                out.flush();
                            } else {
                                out.writeUTF("Wrong.");
                                dem = dem - 1;
                                if (dem < 0) {
                                    dem = 0;
                                }
                                out.write(dem);
                                out.flush();
                            }
                            out.writeUTF(so + "/" + tong);
                            out.flush();
                            if (i == 74) {
                                s.close();
                                in.close();
                                out.close();
                            }

                        }
                    }
                    break;
            }

        } catch (Exception e) {
        }

    }

    private String Dem(String s1) {
        StringBuilder sc = new StringBuilder();
        arr = s1.toCharArray();
        ArrayList<Integer> v = new ArrayList<Integer>();
        for (int i = 0; i < s1.length();) {
            int iNew = rd.nextInt(s1.length());
            if (!v.contains(iNew)) {
                v.add(iNew);
                arr[i] = s1.charAt(iNew);
                sc.append(arr[i] + "  ");
                i++;
            }
        }
        s1 = sc.toString();
        return s1;
    }

}
