package com.mojafirma.stopper;

import javax.swing.*;

public class Main_Stopper {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Stopper stopper = new Stopper();
                stopper.setVisible(true);
            }
        });
    }
}
