package com.mojafirma.stopper;
//9. (Poziom 1) Zaimplementuj (wykorzystuj¹c bibliotekê Swing) stoper, który co sekundê zaktualizuje wyœwietlany czas.
// Wykorzystaj w¹tek i metodê Thread.sleep(). Do wyœwietlania czasu stopera mo¿esz u¿yæ np. JLabel.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Stopper extends JFrame {

    int hours = 0;
    int minutes = 0;
    int seconds = 0;
    boolean clockNotStopped = false;

    public Stopper() throws HeadlessException {
        initialisation();
    }

    public void initialisation() {
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Stopper");

        JLabel timeShow = new JLabel((String.format("%02d",hours) + ":" + String.format("%02d",minutes) + ":" + String.format("%02d",seconds)));
        JButton startButton = new JButton("START");
        JButton stopButton = new JButton("STOP");
        JPanel mainPanel = new JPanel();
        mainPanel.add(timeShow);
        mainPanel.add(startButton);
        mainPanel.add(stopButton);
        getContentPane().add(mainPanel);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockNotStopped = false;
                Thread thread1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!clockNotStopped) {
                            while (seconds < 60) {
                                seconds++;
                                timeShow.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                                if(clockNotStopped){
                                    break;
                                }
                                if (seconds == 59) {
                                    seconds = 0;
                                    if (minutes < 60) {
                                        minutes++;
                                        timeShow.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                                        if (minutes == 59) {
                                            minutes = 0;
                                            if (hours < 25) {
                                                hours++;
                                                timeShow.setText(String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
                                            } else {
                                                seconds = 0;
                                                minutes = 0;
                                                hours = 0;
                                            }
                                        }
                                    }
                                }

                            }

                        }
                    }
                });
                    thread1.start();

                }

        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clockNotStopped = true;
                timeShow.setText(timeShow.getText());


            }
        });
    }
}