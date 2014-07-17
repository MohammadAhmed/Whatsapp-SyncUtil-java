/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.yazeed44.syncutil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author yazeed44
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    
    private String result = "";
      private boolean isWindows = true;
  private  File importFile;
    private boolean chooseFile = false;
  private  String numbers;
    private boolean export = true;
    private boolean debug = false;
    
    private String username,password;
    private String command ="";
    private String mode,context;
    
    private Double time;
    public Main() {
        initComponents();
        initVars();
       
       isWindows = SyncUtil.initIsWindows(Main.this);
        this.setTitle("WhatsApi Sync util (Java)");
    }
    
    
final ItemListener listener = new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
               
                if(((JCheckBox)e.getItem()).getText().equals(exportCheck.getText())){
                    if(!export){
                        export = true;
                        return;
                    }
                    export = false;
                }
                else {
                    if (debug){
                    debug = false;
                    return;
                }
                     debug = true;
                }
            }
            
        };
    
  
    private void initVars(){
        this.txtRadioBtn.addActionListener(new Listener());
        txtRadioBtn.setActionCommand(txtRadioBtn.getText());
        
        numberRadioBtn.addActionListener(new Listener());
        numberRadioBtn.setActionCommand(numberRadioBtn.getText());
        
        ButtonGroup group = new ButtonGroup();
        group.add(txtRadioBtn);
        group.add(numberRadioBtn);
        
        exportCheck.setActionCommand(exportCheck.getText());
        exportCheck.addItemListener(listener);
        debugCheck.setActionCommand(debugCheck.getText());
        debugCheck.addItemListener(listener);
    
    }
    
    class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           if (e.getActionCommand().equals(txtRadioBtn.getText())){
               final JFileChooser fc = new JFileChooser();
               fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
               
               fc.setAcceptAllFileFilterUsed(false);
               
               if(!chooseFile){
                   int returnVal = fc.showOpenDialog(Main.this);
                   
                   if (returnVal == JFileChooser.APPROVE_OPTION){
                       importFile = fc.getSelectedFile();
                       chooseFile = true;
                       
                       numbers = null;
                       result = "";
                       JOptionPane.showMessageDialog(Main.this, "you choosed  :  "+ "\n" + importFile.getPath()
                               +"\n" + "numbers count = " + SyncUtil.getNumberCount(importFile) +"\n" +"it's preferable if the number count is high to choose"
                               + " a high thread number" +"\n" +"Example : 1000 number 10 threads , so every thread sync 100 number" + "\n" +"this way reduce the ban "
                               + "rate !!"
                               , "info", JOptionPane.INFORMATION_MESSAGE);
                   }
               }
               
               else if (chooseFile) {
                   numbers = null;
                   importFile = null;
                   result = "";
                   int returnVal = fc.showOpenDialog(Main.this);
                   
                   if (returnVal == JFileChooser.APPROVE_OPTION){
                       importFile = fc.getSelectedFile();
                       chooseFile = true;
                       
                       JOptionPane.showMessageDialog(Main.this, "you choosed  :  "+ "\n" + importFile.getPath()
                               +"\n" + "numbers count = " + SyncUtil.getNumberCount(importFile) +"\n" +"it's preferable if the number count is high to choose"
                               + " a high thread number" +"\n" +"Example : 1000 number 10 threads , so every thread sync 100 number" + "\n" +"this way reduce the ban "
                               + "rate !!"
                               , "info", JOptionPane.INFORMATION_MESSAGE);
                   }
               }
           }
           
           else if (e.getActionCommand().equals(numberRadioBtn.getText())){
              numbers =  JOptionPane.showInputDialog(Main.this, "Enter the numbers seperated by space :"
                      + "\n" + "Example : 96653493848 96654473728", "Enter the numbers", JOptionPane.QUESTION_MESSAGE);
              importFile = null;
           }
           
           
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtRadioBtn = new javax.swing.JRadioButton();
        numberRadioBtn = new javax.swing.JRadioButton();
        timeTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        threadsTxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        usernameTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        passwordTxt = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        contextCombo = new javax.swing.JComboBox();
        modeCombo = new javax.swing.JComboBox();
        debugCheck = new javax.swing.JCheckBox();
        exportCheck = new javax.swing.JCheckBox();
        syncBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setText("Whatsapp Sync Util");

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Numbers input method : ");

        txtRadioBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txtRadioBtn.setText("through txt file");

        numberRadioBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        numberRadioBtn.setText("type the numbers");

        timeTxt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        timeTxt.setText("0.5");

        jLabel3.setText("Time between Threads (seconds)");

        jLabel9.setText("threads :");

        threadsTxt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        threadsTxt.setText("0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtRadioBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(numberRadioBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(threadsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(23, 23, 23))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(timeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(20, 20, 20)
                .addComponent(txtRadioBtn)
                .addGap(18, 18, 18)
                .addComponent(numberRadioBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(threadsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(timeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jLabel4.setText("User name (your phone number)");

        jLabel5.setText("your whatsapp password");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(usernameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(passwordTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel6.setText("Options");

        jLabel7.setText("Context");

        jLabel8.setText("Mode");

        contextCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "registration", "background", "interactive" }));

        modeCombo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "full", "delta" }));

        debugCheck.setText("debug");
        debugCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugCheckActionPerformed(evt);
            }
        });

        exportCheck.setSelected(true);
        exportCheck.setText("export checked numbers");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(contextCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(debugCheck))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(exportCheck)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(modeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel8))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(debugCheck)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(contextCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(modeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addComponent(exportCheck)
                .addGap(24, 24, 24))
        );

        syncBtn.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        syncBtn.setText("Sync");
        syncBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                syncBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(52, 52, 52)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(syncBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(syncBtn)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void debugCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugCheckActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debugCheckActionPerformed

    private void syncBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_syncBtnActionPerformed
        // TODO add your handling code here:
        long start = System.currentTimeMillis();
        username = usernameTxt.getText();
        password = passwordTxt.getText();
        mode = (String)modeCombo.getSelectedItem();
        context = (String)contextCombo.getSelectedItem();
        result = "";//reset
        time = Double.parseDouble(timeTxt.getText());
        int threads = Integer.parseInt(threadsTxt.getText());
        
        if(!isVarsInitalized())
            return;
        
        if(numbers == null && importFile != null){
        numbers = SyncUtil.readFile(importFile);
        }
         
        int numbersCount = SyncUtil.getNumberCount(numbers);
         
        command = SyncUtil.createCommand(username, password, mode, debug+"", context, isWindows);
        
        int threadsNumber = 0;
         
        
        if (numbersCount > 0){
//            threadsNumber = (int) (numbersCount * 0.01);
threadsNumber = threads;
        }
        
      final String commands[] = SyncUtil.getCommandWithNumbersSeperated(command, threadsNumber, numbers);
       
      
        for(int i = 0; i <= threadsNumber;i++){
            final int iClone = i;
            
            Thread thread = new Thread(new Runnable(){

                @Override
                public void run() {
                    String subResult = SyncUtil.getResult(commands[iClone]);
                    
                    result += subResult;
                    
                }
                
            });
            thread.setName("Thread " + iClone);
            thread.start();
            try {
                thread.join();
                Thread.sleep((long)(time * 1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }     
      
       SyncUtil.showResult(result, start, this);
      
    }//GEN-LAST:event_syncBtnActionPerformed

    private boolean isVarsInitalized(){
        
        if (username == null||username.length() == 0){
            JOptionPane.showMessageDialog(this, "you didn't type a username !!", "ERROR !!", JOptionPane.ERROR_MESSAGE);
            return false;
            
        }
        
        if (password == null ||password.length() == 0){
            JOptionPane.showMessageDialog(this, "you didn't type a password !!", "ERROR !!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
         if(numbers == null&&importFile ==null){
            JOptionPane.showMessageDialog(this, "You didn't put numbers !!", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }
         
        
        return true;
        
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
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox contextCombo;
    private javax.swing.JCheckBox debugCheck;
    private javax.swing.JCheckBox exportCheck;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JComboBox modeCombo;
    private javax.swing.JRadioButton numberRadioBtn;
    private javax.swing.JTextField passwordTxt;
    private javax.swing.JButton syncBtn;
    private javax.swing.JTextField threadsTxt;
    private javax.swing.JTextField timeTxt;
    private javax.swing.JRadioButton txtRadioBtn;
    private javax.swing.JTextField usernameTxt;
    // End of variables declaration//GEN-END:variables
}
