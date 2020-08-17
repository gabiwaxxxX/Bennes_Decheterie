package com.jacla.api;

import com.jacla.api.models.Dumpster;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class UI extends JFrame {
    private JLabel titleLabel;
    private JTable connectionTable;

    protected UI(){
        titleLabel = new JLabel("Gestion des bennes");
        //headers for the table
        String[] columns = new String[] {
                "Name", "Filling","Connection","Ip"
        };


        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
                {"Gabi", 45, true, "192.168.0.7" },
                {"Gabi", 45, true, "192.168.0.7" },
                {"Theo", 45, true, "192.168.0.7" },
        };

        connectionTable = new JTable(data, columns);
        this.setSize(300, 200);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.add(connectionTable);
        this.add(titleLabel);
    }

    public void addDumpster(Dumpster dumpster){

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Api");
    }

}
