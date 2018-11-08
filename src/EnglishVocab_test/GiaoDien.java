/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EnglishVocab_test;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 *
 * @author kiet
 */
public class GiaoDien extends JFrame {

    int vt = 0;

    JTextField textGui, textNhap;

    JLabel textNhan, textKQ, textKQ1, textKQ2, textKQ3;
    JButton jbnThoat, jbnGui, jbnStart;
    DataInputStream in;
    DataOutputStream out;
    private final Socket s;
    JPanel bnStart, pnNhan, pnGui, pnSumit, pnKetQua, pnKetQua1, pnKetQua2, pnKetQua3, bncombo, pnNhap;
    JComboBox cbo;

    public GiaoDien(String tieude) throws IOException, Exception {
        super(tieude);
        s = new Socket("192.168.137.206", 1000);
        in = new DataInputStream(s.getInputStream());
        out = new DataOutputStream(s.getOutputStream());
        addControls();
        addEvents();

    }

    String line;
    String line1;
    String lin;
    int dem;
    String line2;

    public void Xuly() throws Exception {
        line = in.readUTF();
        textNhan.setText(line);
    }

    public void addEvents() throws IOException {
        cbo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vt = cbo.getSelectedIndex();
            }
        });

        jbnGui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    line1 = textGui.getText();
                    out.writeUTF(line1);
                    out.flush();
                    lin = in.readUTF();
                    dem = in.read();
                    line2 = in.readUTF();
                    textKQ.setText(lin);
                    textKQ1.setText(dem + " ");
                    textKQ2.setText(line2);
                    textGui.setText("");
                    Xuly();
                } catch (IOException ex) {

                } catch (Exception ex) {

                }

            }
        });
        jbnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        jbnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    out.write(vt);
                    out.flush();
                } catch (IOException ex) {

                }
                bnStart.setVisible(false);
                bncombo.setVisible(false);
                pnNhap.setVisible(false);
                pnNhan.setVisible(true);
                pnGui.setVisible(true);
                pnSumit.setVisible(true);
                pnKetQua.setVisible(true);
                pnKetQua1.setVisible(true);
                pnKetQua2.setVisible(true);
                pnKetQua3.setVisible(true);
                textKQ3.setText(textNhap.getText());

            }
        });

    }

    public void addControls() {
        Container con = getContentPane();
        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        con.add(pnMain);

        bnStart = new JPanel();
        bnStart.setLayout(new FlowLayout());
        jbnStart = new JButton("Start");
        bnStart.add(jbnStart);
        pnMain.add(bnStart);

        bncombo = new JPanel();
        cbo = new JComboBox();
        bncombo.setLayout(new FlowLayout());
        JLabel lblCom = new JLabel("Difficulty:");
        cbo.addItem("Beginner");
        cbo.addItem("Intermediate");
        cbo.addItem("Advanced");
        bncombo.add(lblCom);
        bncombo.add(cbo);
        pnMain.add(bncombo);

        pnNhap = new JPanel();
        pnNhap.setLayout(new FlowLayout());
        JLabel lblNhap = new JLabel("Player name: ");
        textNhap = new JTextField(15);
        pnNhap.add(lblNhap);
        pnNhap.add(textNhap);
        pnMain.add(pnNhap);

        pnNhan = new JPanel();
        pnNhan.setLayout(new FlowLayout());
        JLabel lblNhan = new JLabel("Scrambled word: ");
        textNhan = new JLabel();
        pnNhan.add(lblNhan);
        pnNhan.add(textNhan);
        pnMain.add(pnNhan);

        pnGui = new JPanel();
        pnGui.setLayout(new FlowLayout());
        JLabel lblGui = new JLabel("Answer: ");
        textGui = new JTextField(15);
        pnGui.add(lblGui);
        pnGui.add(textGui);
        pnMain.add(pnGui);

        pnSumit = new JPanel();
        pnSumit.setLayout(new FlowLayout());
        jbnGui = new JButton("Submit");
        jbnThoat = new JButton("Exit");
        pnSumit.add(jbnGui);
        pnSumit.add(jbnThoat);
        pnMain.add(pnSumit);

        pnKetQua = new JPanel();
        pnKetQua.setLayout(new FlowLayout());
        JLabel lblKQ = new JLabel("Result:");
        textKQ = new JLabel();
        pnKetQua.add(lblKQ, LEFT_ALIGNMENT);
        pnKetQua.add(textKQ);
        pnMain.add(pnKetQua);

        pnKetQua1 = new JPanel();
        pnKetQua1.setLayout(new FlowLayout());
        JLabel lblKQ1 = new JLabel("Score :");
        textKQ1 = new JLabel();
        pnKetQua1.add(lblKQ1, LEFT_ALIGNMENT);
        pnKetQua1.add(textKQ1);
        pnMain.add(pnKetQua1);

        pnKetQua2 = new JPanel();
        pnKetQua2.setLayout(new FlowLayout());
        JLabel lblKQ2 = new JLabel("Answered: ");
        textKQ2 = new JLabel();
        pnKetQua2.add(lblKQ2, LEFT_ALIGNMENT);
        pnKetQua2.add(textKQ2);
        pnMain.add(pnKetQua2);

        pnKetQua3 = new JPanel();
        pnKetQua3.setLayout(new FlowLayout());
        JLabel lblKQ3 = new JLabel("Name :");
        textKQ3 = new JLabel();
        pnKetQua3.add(lblKQ3, LEFT_ALIGNMENT);
        pnKetQua3.add(textKQ3);
        pnMain.add(pnKetQua3);
    }

    public void showGiao() {
        this.setSize(400, 300);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        pnNhan.setVisible(false);
        pnGui.setVisible(false);
        pnSumit.setVisible(false);
        pnKetQua.setVisible(false);
        pnKetQua1.setVisible(false);
        pnKetQua2.setVisible(false);
        pnKetQua3.setVisible(false);
    }

}
