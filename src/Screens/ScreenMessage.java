/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Screens;

import ConnectionDB.ConnMySql;
import Object.ChatService;
import Object.GroupChat;
import Object.Login;
import Object.Mutexesisler;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public final class ScreenMessage extends javax.swing.JDialog {

    private final ConnMySql conn;
    private List<GroupChat> FillTheTable;
    private Mutexesisler AddMutexesis;

    /**
     * Creates new form ScreenMessage
     *
     * @param parent
     * @param modal
     * @param l
     */
    Login l;

    public ScreenMessage(java.awt.Frame parent, boolean modal, Login l) {
        super(parent, modal);
        initComponents();
        conn = new ConnMySql();
        this.l = l;
        Refresh();
    }

    public void Refresh() {

        FillTheTable = conn.GroupChatFindAll();

        FillTheTableUmumi();
    }

    private void FillTheTableUmumi() {
        DefaultTableModel tmodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tmodel.addColumn("ID");
        tmodel.addColumn("Ad Soyad");

        jTable1.setAutoResizeMode(jTable1.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        jTable1.setRowHeight(50);
        jTable1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        jTable1.setModel(tmodel);

        FillTheTable.stream().forEach((b) -> {
            if (b.idMembers == l.idMutexesis) {
                tmodel.insertRow(jTable1.getRowCount(), new Object[]{
                    b.idGroupChat,
                    conn.MutexesislerFindByIdMutexesisler(b.idServer).Ad + " " + conn.MutexesislerFindByIdMutexesisler(b.idServer).Soyad
                });
            }
        });
    }

    private void FillEditorPane() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        String id = model.getValueAt(index, 0).toString();

        List<ChatService> SelectChat = conn.ChatServiceFindBySetName("idGroup", id);

        String Chat = "";
        SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");

        for (ChatService c : SelectChat) {
//            Chat = Chat+"\n" + c.Text + " \n" + c.Date + "\n\n";
            Chat = Chat + "<tr><td width=\"100%\">" + c.Text + "</td></tr>";

            Chat = Chat + "<tr><td width=\"100%\">" + d.format(c.Date) + "</td></tr>";
        }
        jEditorPane1.setContentType("text/html");
        String data = "<style>\n"
                + "table {\n"
                + "font-family: arial, sans-serif;\n"
                + "border-collapse: collapse;\n"
                + "width: 100%;\n"
                + "}\n"
                + "\n"
                + "td, th {\n"
                + "border: 1px solid #dddddd;\n"
                + "text-align: left;\n"
                + "padding: 3px;\n"
                + "}\n"
                + "\n"
                + "tr:nth-child(even) {\n"
                + "background-color: #dddddd;\n"
                + "}\n"
                + "</style>"
                + "<table style=\"width:100%\">"
                + Chat
                //                    + "<tr><td>Ad</td><td>" + ScreenChange.Ad + "</td></tr>"
                + "</table>";
        jEditorPane1.setText(data);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jEditorPane2 = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Söhbət aç");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jEditorPane1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jScrollPane3.setViewportView(jEditorPane1);

        jEditorPane2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jEditorPane2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jEditorPane2KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jEditorPane2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 882, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 664, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        AllMutexesisler d = new AllMutexesisler(null, rootPaneCheckingEnabled);
//        d.setVisible(rootPaneCheckingEnabled);
//        AddMutexesis = d.SelectedMutexesis;
//        if (d.SelectedMutexesis != null) {
//            conn.GroupChatInsertUpdate(new GroupChat(0, l.idMutexesis, d.SelectedMutexesis.idmutexesis, "1"));
//        }
//        Refresh();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        FillEditorPane();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jEditorPane2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jEditorPane2KeyReleased
        java.util.Date date = new java.util.Date();
        long t = date.getTime();
        java.sql.Date sqlDate = new java.sql.Date(t);
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int index = jTable1.getSelectedRow();
        String id = model.getValueAt(index, 0).toString();

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            conn.ChatServiceInsertUpdate(new ChatService(0, Integer.parseInt(id), jEditorPane2.getText(), sqlDate));
            jEditorPane2.setText(null);
            FillEditorPane();
        }
    }//GEN-LAST:event_jEditorPane2KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JEditorPane jEditorPane2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
