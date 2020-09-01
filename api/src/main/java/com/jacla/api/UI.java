package com.jacla.api;

import com.jacla.api.models.Dumpster;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {
    private JPanel contentPanel;
    private JLabel titleLabel;
    private JTable connectionTable;
    private DefaultTableModel model = new DefaultTableModel();
    private JButton startBtn;

    protected UI() {
        contentPanel = new JPanel();
        contentPanel = new JPanel();
        titleLabel = new JLabel("Gestion des bennes");
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);

        startBtn = new JButton("Start");

        connectionTable = new JTable(model);
        model.addColumn("Nom");
        model.addColumn("Remplissage");
        model.addColumn("Connexion");
        model.addColumn("IP");
        model.addColumn("Initialisation à vide");
        model.addColumn("Initialisation à plein");


        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RestTemplate restTemplate = new RestTemplate();
                String url = "http://localhost:8080/bennes";
                Dumpster[] response = restTemplate.getForObject(url, Dumpster[].class);

                for (Dumpster d:response) {
                    addDumpster(d);
                }
                startBtn.setVisible(false);
                contentPanel.add(connectionTable);
                contentPanel.updateUI();
            }
        });

        contentPanel.add(titleLabel);
        contentPanel.add(startBtn);
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void addDumpster(Dumpster dumpster){
        this.model.addRow(new Object[]{dumpster.getName(), dumpster.getFilling(), dumpster.isConnection(), dumpster.getIp(), dumpster.isEmptyInitialisation(), dumpster.isFullInitialisation() });
    }
}
