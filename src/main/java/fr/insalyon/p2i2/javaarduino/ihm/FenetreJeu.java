/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insalyon.p2i2.javaarduino.ihm;

import java.awt.Color;

/**
 *
 * @author bkermani
 */
public class FenetreJeu extends javax.swing.JFrame {
    
    /**
     * Creates new form FenetreJeu
     */
    FenetreAccueil fa;  
    
    public FenetreJeu() {
        
        initComponents();
        PartitionPanel p = new PartitionPanel(this);
        p.setBounds(30,90,1000,274);
        p.setForeground(Color.black);
        this.add(p);
        
        this.setLocationRelativeTo(null); 
        this.setResizable(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nomMusique = new javax.swing.JLabel();
        label = new javax.swing.JLabel();
        nomArtiste = new javax.swing.JLabel();
        difficulte = new javax.swing.JLabel();
        tempo = new javax.swing.JLabel();
        valeurDifficulte = new javax.swing.JLabel();
        valeurTempo = new javax.swing.JLabel();
        reessayer = new javax.swing.JButton();
        partition = new javax.swing.JPanel();
        timer = new javax.swing.JLabel();
        valTimer = new javax.swing.JLabel();
        menu = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        nomMusique.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nomMusique.setText("        Nom Musique");

        label.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        label.setText("par");

        nomArtiste.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        nomArtiste.setText("Nom Artiste");

        difficulte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        difficulte.setText("Difficulté:");

        tempo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tempo.setText("Tempo : ");

        valeurDifficulte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        valeurDifficulte.setText("Difficulté ici");

        valeurTempo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        valeurTempo.setText("Tempo ici");

        reessayer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        reessayer.setText("Réessayer");

        partition.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        timer.setText("Compte à Rebours:");

        valTimer.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        valTimer.setText("0:00");

        menu.setText("Retour au menu");
        menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tempo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valeurTempo)
                                .addGap(321, 321, 321)
                                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(nomArtiste, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(difficulte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(valeurDifficulte)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(nomMusique, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111)))
                        .addComponent(timer)
                        .addGap(56, 56, 56)
                        .addComponent(valTimer)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(reessayer, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(partition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reessayer)
                            .addComponent(timer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(valTimer)
                            .addComponent(nomMusique))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menu)
                        .addGap(280, 280, 280))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(difficulte)
                            .addComponent(valeurDifficulte))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(nomArtiste, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(label))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(tempo, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(valeurTempo)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(partition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuActionPerformed
        // TODO add your handling code here: 
        this.dispose();
        fa = new FenetreAccueil();
        fa.setVisible(true);
        
    }//GEN-LAST:event_menuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreJeu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FenetreJeu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel difficulte;
    private javax.swing.JLabel label;
    private javax.swing.JButton menu;
    private javax.swing.JLabel nomArtiste;
    private javax.swing.JLabel nomMusique;
    private javax.swing.JPanel partition;
    private javax.swing.JButton reessayer;
    private javax.swing.JLabel tempo;
    private javax.swing.JLabel timer;
    private javax.swing.JLabel valTimer;
    private javax.swing.JLabel valeurDifficulte;
    private javax.swing.JLabel valeurTempo;
    // End of variables declaration//GEN-END:variables
}