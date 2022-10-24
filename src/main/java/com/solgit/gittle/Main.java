package com.solgit.gittle;

import org.kohsuke.github.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)  throws Exception{
        List<String> files = new ArrayList<>();


        Frame f = new Frame();
        String[] names = System.getProperty("user.dir").split("\\\\");
        String name =names[names.length-2];


        Panel p = new Panel();//패널생성
        Button branchButton = new Button("branch list");
        Button addButton = new Button("git add");
        Button resetButton = new Button("reset");

        Process process = Runtime.getRuntime().exec("cmd /c git status --short");
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = null;
        System.out.println("<OUTPUT>");
        List<Checkbox> checkboxes = new ArrayList<>();
        while ((line = br.readLine()) != null){
            System.out.println(line);
            checkboxes.add(new Checkbox(line));
        }


        for (Checkbox cb : checkboxes) {
            p.add(cb);
            System.out.println(cb.getLabel());
            cb.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    String[] temp = cb.getLabel().split(" ");
                    if (e.getStateChange() == 1) {
                        files.add(temp[temp.length - 1]);
                        System.out.println(files.size() + " add " +  files.get(files.size()-1));
                    } else {
                        System.out.println(files.size()-1 + " remove " +  files.get(files.size()-1));
                        files.remove(temp[temp.length - 1]);

                    }
                }
            });
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Process p;
                StringBuilder sb = new StringBuilder();
                sb.append("cmd /c git add");
                for(String str : files){
                    sb.append(" ").append(str);
                }
                try {
                    p = Runtime.getRuntime().exec(sb.toString());
                    System.out.println(sb.toString());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process p = Runtime.getRuntime().exec("cmd /c git reset");
                    System.out.println("reset");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        branchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Process p = Runtime.getRuntime().exec("cmd /c git branch");
                    BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String line = null;
                    System.out.println("branch list");
                    List<Checkbox> checkboxes = new ArrayList<>();
                    while ((line = br.readLine()) != null){
                        System.out.println(line);
                        //checkboxes.add(new Checkbox(line));
                    }
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        p.add(branchButton);
        p.add(addButton);
        p.add(resetButton);
        f.add(p);

        f.setTitle(name);
        f.setBounds(100,100,500,300);
        f.setVisible(true);
    }
}
