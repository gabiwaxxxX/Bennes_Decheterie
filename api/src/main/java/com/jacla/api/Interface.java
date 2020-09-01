package com.jacla.api;

import com.jacla.api.models.Dumpster;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Interface extends JFrame {
    private JPanel contentPane;
    private JPanel buttonPane;
    private JLabel titleLabel;
    private JTable connectionTable;
    private DefaultTableModel model = new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private JButton startBtn;
    private JButton emptyInitBtn;
    private JButton fullInitBtn;
    private JButton bracingBtn;
    private JButton deleteBtn;
    private final static String url = "http://localhost:8080/bennes";
    private RestTemplate restTemplate = new RestTemplate();
    private Dumpster[] dumpsters;


    protected Interface() {
        setGUI();
    }

    private void setGUI(){
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        buttonPane = new JPanel();
        buttonPane.setLayout(new GridLayout());

        titleLabel = new JLabel("Gestion des bennes");
        titleLabel.setVerticalAlignment(SwingConstants.CENTER);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        connectionTable = new JTable(model);
        connectionTable.setSize(1000,500);
        model.addColumn("Id");
        model.addColumn("Nom");
        model.addColumn("Remplissage");
        model.addColumn("Connexion");
        model.addColumn("IP");
        model.addColumn("Initialisation à vide");
        model.addColumn("Initialisation à plein");
        connectionTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteBtn.setEnabled(isDeleteAble((String)connectionTable.getValueAt(connectionTable.getSelectedRow(), 0)));
            }
        });

        setButtons();

        buttonPane.add(emptyInitBtn);
        buttonPane.add(fullInitBtn);
        buttonPane.add(bracingBtn);
        buttonPane.add(deleteBtn);
        contentPane.add(titleLabel, BorderLayout.PAGE_START);
        contentPane.add(startBtn, BorderLayout.CENTER);

        setContentPane(contentPane);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setSize(640, 390);
        setVisible(true);
    }


    private void setButtons(){
        emptyInitBtn = new JButton("Initialiser à vide");
        fullInitBtn = new JButton("Initialiser à plein");
        bracingBtn = new JButton("Apparaillage");
        deleteBtn = new JButton("Supprimer");
        deleteBtn.setEnabled(false);
        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { deleteDumpster((String)connectionTable.getValueAt(connectionTable.getSelectedRow(), 0)); }
        });
        startBtn = new JButton("Start");
        startBtn.setEnabled(false);
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
                contentPane.remove(startBtn);
                contentPane.add(new JScrollPane(connectionTable), BorderLayout.CENTER);
            }
        });
    }

    private void refreshTable(){
        model.setRowCount(0);
        dumpsters = restTemplate.getForObject(url, Dumpster[].class);
        for (Dumpster d:dumpsters) {
            addDumpster(d);
        }
        startBtn.setVisible(false);
        contentPane.updateUI();
    }

    private boolean isDeleteAble(String id){
        for (Dumpster d: dumpsters){
            if (d.getId() == id){
                return !d.isConnected();
            }
        }
        return false;
    }

    private void addDumpster(Dumpster dumpster){
        this.model.addRow(new Object[]{dumpster.getId() ,dumpster.getName(), dumpster.getFilling(), dumpster.isConnected(), dumpster.getIp(), dumpster.isEmptyInitialised(), dumpster.isFullInitialised() });
    }

    private void deleteDumpster(String id){
        Map< String, String > params = new HashMap< String, String >();
        params.put("id", id);
        restTemplate.delete(url+"/{id}",params);
        refreshTable();
        deleteBtn.setEnabled(false);
    }

    public void start(){
        startBtn.setEnabled(true);
        buttonPane.setVisible(true);
        contentPane.add(buttonPane, BorderLayout.PAGE_END);
    }


}