package com.jacla.api;

import com.jacla.api.models.Dumpster;
import com.jacla.api.repositories.DumpsterRepository;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class UI extends JFrame {
    private JPanel contentPanel;
    private JLabel titleLabel;
    private JTable connectionTable;
    private DefaultTableModel model = new DefaultTableModel();
    private DumpsterRepository dumpsterRepository = new DumpsterRepository();

    protected UI(){
        this.contentPanel = new JPanel();
        this.titleLabel = new JLabel("Gestion des bennes");
        this.titleLabel.setVerticalAlignment(SwingConstants.CENTER);

        this.connectionTable = new JTable(model);
        this.model.addColumn("Nom");
        this.model.addColumn("Remplissage");
        this.model.addColumn("Connexion");
        this.model.addColumn("IP");
        for (Dumpster d:dumpsterRepository.getDumpsters()){
            addDumpster(d);
        }

        this.contentPanel.add(titleLabel);
        this.contentPanel.add(connectionTable);
        setContentPane(contentPanel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public void addDumpster(Dumpster dumpster){
        this.model.addRow(new Object[]{dumpster.getName(), dumpster.getFilling(), dumpster.getConnection(), dumpster.getIp() });

    }
}
