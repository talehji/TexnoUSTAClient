package Screens;

import ConnectionDB.ConnMySql;
import Object.Login;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScreenLogin extends javax.swing.JFrame {

    private final ConnMySql conn;
    private final List<Login> AllLogin;
    private final Login GetLoginParametr;
    private Login GetNewLoginParametr;

    public ScreenLogin() {
        initComponents();
        conn = new ConnMySql();
        AllLogin = conn.LoginFindAll();
        jComboBoxLogin.removeAllItems();
        for (Login l : AllLogin) {
            jComboBoxLogin.addItem(conn.MutexesislerFindByIdMutexesisler(l.idMutexesis).Ad + " " + conn.MutexesislerFindByIdMutexesisler(l.idMutexesis).Soyad);
        }

        try (BufferedReader br = new BufferedReader(new FileReader("Login.txt"))) {

            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                jComboBoxLogin.setSelectedIndex(Integer.parseInt(sCurrentLine));
                jComboBoxLogin.requestFocus();
                jComboBoxLogin.requestFocusInWindow();
            }

        } catch (IOException e) {
        }

        GetLoginParametr = conn.LoginFindByIdLogin((jComboBoxLogin.getSelectedIndex() + 1));

        if (GetLoginParametr.Status.equals("1")) {
            jPassword.setText(GetLoginParametr.Password);
        }

    }

    public void Login() throws FileNotFoundException, UnsupportedEncodingException {
        GetNewLoginParametr = conn.LoginFindByIdLogin((jComboBoxLogin.getSelectedIndex() + 1));
        if (GetNewLoginParametr.Password.equals(jPassword.getText())) {
            if (jCheckBox1.isSelected()) {
                conn.LoginInsertUpdate(new Login(GetNewLoginParametr.idLogin, GetNewLoginParametr.idMutexesis, GetNewLoginParametr.Password, "1"));
            } else {
                conn.LoginInsertUpdate(new Login(GetNewLoginParametr.idLogin, GetNewLoginParametr.idMutexesis, GetNewLoginParametr.Password, "0"));
            }
            ScreenMain d = new ScreenMain(GetNewLoginParametr);
            d.setVisible(true);

            try (PrintWriter writer = new PrintWriter("Login.txt", "UTF-8")) {
                writer.println((GetNewLoginParametr.idLogin - 1));
            }
            this.dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonLogin = new javax.swing.JButton();
        jComboBoxLogin = new javax.swing.JComboBox<>();
        jPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jButtonLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonLogin.setText("Daxil ol");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jComboBoxLogin.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Login");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Parol");

        jCheckBox1.setSelected(true);
        jCheckBox1.setText("Məni xatırla dostum");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jButtonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(63, 63, 63)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jComboBoxLogin, javax.swing.GroupLayout.Alignment.LEADING, 0, 216, Short.MAX_VALUE)))))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox1)
                    .addComponent(jButtonLogin))
                .addGap(50, 50, 50))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        try {
            Login();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(ScreenLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordActionPerformed
        try {
            Login();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(ScreenLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jPasswordActionPerformed

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ScreenLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {
            new ScreenLogin().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBoxLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPassword;
    // End of variables declaration//GEN-END:variables
}
