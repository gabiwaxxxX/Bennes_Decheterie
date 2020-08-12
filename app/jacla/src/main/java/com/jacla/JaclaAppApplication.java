package com.jacla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.swing.JFrame;

@SpringBootApplication
public class JaclaAppApplication extends JFrame {
	
	/*public JaclaAppApplication() {
        initUI();
    }

    private void initUI() {
        setTitle("Simple example");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }*/

	public static void main(String[] args) {
        //Fenetre window = new Fenetre();
		SpringApplication.run(JaclaAppApplication.class, args);
	}

}
