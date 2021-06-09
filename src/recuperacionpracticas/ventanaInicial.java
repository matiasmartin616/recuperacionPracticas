/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recuperacionpracticas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.TimerTask;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import javax.swing.JOptionPane;


/**
 *
 * @author mst-m
 */

public class ventanaInicial extends javax.swing.JFrame implements ActionListener {
carta[] arrayCartas;
carta carta1, carta2;
Timer tiempoCarta;
Timer cronometro;
int finJuego = 0;
TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                segundos++;
                jLabelTiempo.setText("Tiempo: " + segundos);
            }
        };
int segundos = 0;
boolean puedeDescubrir = true;
    /**
     * Creates new form NewJFrame
     */
    public ventanaInicial() {
        initComponents();
        this.setLocationRelativeTo(null);
        setSize(600,400);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBot = new javax.swing.JPanel();
        jPanelTop = new javax.swing.JPanel();
        jLabelTiempo = new javax.swing.JLabel();
        jButtonStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelBot.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanelBotLayout = new javax.swing.GroupLayout(jPanelBot);
        jPanelBot.setLayout(jPanelBotLayout);
        jPanelBotLayout.setHorizontalGroup(
            jPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 473, Short.MAX_VALUE)
        );
        jPanelBotLayout.setVerticalGroup(
            jPanelBotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        getContentPane().add(jPanelBot, java.awt.BorderLayout.CENTER);

        jPanelTop.setBackground(new java.awt.Color(255, 255, 153));
        jPanelTop.add(jLabelTiempo);

        jButtonStart.setText("Iniciar");
        jButtonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonStartActionPerformed(evt);
            }
        });
        jPanelTop.add(jButtonStart);

        getContentPane().add(jPanelTop, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void actionPerformed (ActionEvent e){
        
        
        if(puedeDescubrir == true){
            if(carta1 == null){
                carta1 = (carta) e.getSource();
                carta1.setDescubierta(true);
            }  
            else {
                carta2 = (carta) e.getSource();
                carta2.setDescubierta(true);
                puedeDescubrir = false;
                
                if(carta1.getCodigoCarta() == carta2.getCodigoCarta()){
                    System.out.println("Has acertado");
                    carta1 = carta2 = null;
                    puedeDescubrir = true;
                    finJuego++;
                    
                    if (finJuego == 8){
                        String nombre = JOptionPane.showInputDialog("Has ganado introduce tu nombre");
                        JOptionPane.showMessageDialog(null, "Enhorabuena " + nombre + " has ganado. Tus resultados han sido almacenados en la base de datos");
                    }
                }
                
                else{
                    System.out.println("Has fallado");
                    TimerTask tt = new TimerTask() {

                        @Override
                        public void run() {
                            carta1.setDescubierta(false);
                            carta2.setDescubierta(false);
                            carta1 = carta2 = null;
                            puedeDescubrir = true;
                        }
                    };
                    tiempoCarta = new Timer();
                    tiempoCarta.schedule(tt, 1000);
                }   
            }
        } 
    }
    private void cronometro(){
        cronometro = new Timer();
        cronometro.scheduleAtFixedRate(tt, 0, 1000);
    }
    
    
    private void jButtonStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonStartActionPerformed
        iniciar();
        this.paintAll(this.getGraphics());
        
    }//GEN-LAST:event_jButtonStartActionPerformed
    
    private void iniciar(){
        jPanelBot.removeAll();
        iniciarTablero();
        cronometro();
    }
    
    private void iniciarTablero(){
        int x = 4, y = 4;
        this.jPanelBot.setLayout(new java.awt.GridLayout(x, y));
        arrayCartas = crearArray();
        //arrayCartas = desordenarArray(arrayCartas);
        for (int i = 0; i < arrayCartas.length; i++) {
             arrayCartas[i].addActionListener(this);
             arrayCartas[i].setVisible(true);
             this.jPanelBot.add(arrayCartas[i]);
        } 
    }
    private static carta[] crearArray(){
        carta listaCartas[] = new carta[16];
        int cont = 0;
        for(int i=0; i < listaCartas.length; i+=2){
              listaCartas[i] = new carta(cont); 
              listaCartas[i+1] = new carta(cont);
              cont++;
        }
        
        return listaCartas;
    }

    //no funciona
    private static carta[] desordenarArray(carta[] array){
            List<carta> intList = Arrays.asList(array);
            Collections.shuffle(intList);
            intList.toArray(array);
        return array;
    }
    
    private float calcularPuntuacion(int tiempo){
        float puntuacion = 0;
        
        return puntuacion;
    }
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
            java.util.logging.Logger.getLogger(ventanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaInicial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaInicial().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonStart;
    private javax.swing.JLabel jLabelTiempo;
    private javax.swing.JPanel jPanelBot;
    private javax.swing.JPanel jPanelTop;
    // End of variables declaration//GEN-END:variables

    
}
