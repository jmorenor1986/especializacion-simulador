package presentacion;

import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class Vista extends javax.swing.JFrame {

    private Controlador control;
    private javax.swing.JLabel lblCeldas[][];
    private final Modelo modelo;
    private JMenuItem nuevoLienzo;
    private JMenuItem deshacer;

    public Vista(Modelo aThis) {
        modelo = aThis;
        initComponents();
        creaMenuLateral();
        asignarEventosMenuLateral();
        iniciarComponentesPersonalizados();
        asignarEventosCanvas();

    }

    private void iniciarComponentesPersonalizados() {
        nuevoLienzo = new JMenuItem("Nuevo");
        deshacer = new JMenuItem("Deshacer");
        nuevoLienzo.addActionListener(getControl());
        deshacer.addActionListener(getControl());
        jMenu1.add(nuevoLienzo);
        jMenu1.add(deshacer);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lienzo = new java.awt.Canvas();
        panel1 = new java.awt.Panel();
        jLabel1 = new javax.swing.JLabel();
        labelLog = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lienzo.setBackground(new java.awt.Color(24, 19, 14));

        panel1.setBackground(new java.awt.Color(9, 4, 4));

        jLabel1.setForeground(new java.awt.Color(241, 234, 234));
        jLabel1.setText("Log");

        labelLog.setForeground(new java.awt.Color(254, 254, 254));
        labelLog.setText("inicio log");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(labelLog, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelLog, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
        );

        jMenu1.setText("Archivo");
        menu.add(jMenu1);

        jMenu2.setText("Ayuda");
        menu.add(jMenu2);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lienzo, javax.swing.GroupLayout.PREFERRED_SIZE, 884, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lienzo, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JLabel labelLog;
    private java.awt.Canvas lienzo;
    private javax.swing.JMenuBar menu;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables

    private void creaMenuLateral() {
        int posX = 53;
        int posY = 52;
        int separacion = 5;
        int lado = 48;
        lblCeldas = new JLabel[5][2];
        int contador = 1;
        for (int fil = 0; fil < 4; fil++) {
            for (int col = 0; col < 2; col++) {
                lblCeldas[fil][col] = new JLabel();
                lblCeldas[fil][col].setName("objeto" + contador);
                lblCeldas[fil][col].setBackground(Color.WHITE);
                lblCeldas[fil][col].setIcon(new javax.swing.ImageIcon(getClass().getResource("/recursos/menu/objeto" + contador + ".jpg")));
                lblCeldas[fil][col].setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
                getContentPane().add(lblCeldas[fil][col]);
                posX = 0 + col * (lado + separacion);
                posY = 0 + fil * (lado + separacion);
                lblCeldas[fil][col].setBounds(posX, posY, lado, lado);
                contador++;
            }
        }
    }

    private void asignarEventosMenuLateral() {
        for (int fil = 0; fil < 4; fil++) {
            for (int col = 0; col < 2; col++) {
                lblCeldas[fil][col].addMouseListener(getControl());
            }
        }
    }

    private void asignarEventosCanvas() {
        lienzo.addMouseListener(getControl());
        nuevoLienzo.addMouseListener(getControl());
    }

    public Controlador getControl() {
        if (control == null) {
            control = new Controlador(this);
        }
        return control;
    }

    public JLabel[][] getLblCeldas() {
        return lblCeldas;
    }

    public void setLblCeldas(JLabel[][] lblCeldas) {
        this.lblCeldas = lblCeldas;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public JLabel getLabelLog() {
        return labelLog;
    }

    public Canvas getLienzo() {
        return lienzo;
    }

}
