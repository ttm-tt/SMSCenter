/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smscenter.gui;

import java.awt.Component;
import java.awt.Font;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import smscenter.database.Competition;
import smscenter.database.Database;
import smscenter.database.Group;

/**
 *
 * @author chtheis
 */
public class GroupsPanel extends BasePanel {

    /**
     * Creates new form GroupsPanel
     */
    public GroupsPanel(Database database) {
        super(database);
        initComponents();
        myInitComponents();
        competitionComboBoxActionPerformed(null);
    }
    
    private void myInitComponents() {
        // Warum auch immer, aber post-creation code wird nicht akzeptiert
        // Render Zeile entsprechend von Auto, Manual und Enabled
        groupsTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                // Reset values before changing them
                setBackground(null);
                setForeground(null);

                java.awt.Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                
                boolean manual = (Boolean) table.getModel().getValueAt(row, 3);
                boolean enabled = (Boolean) table.getModel().getValueAt(row, 4);
                
                if (isSelected) {
                    // Nothing
                } else if (manual && !enabled) {
                    // Manual && !Enabled
                    comp.setBackground(java.awt.Color.RED);
                } else if (manual && enabled) {
                    // Manual && Enabled
                    comp.setBackground(java.awt.Color.GREEN);
                } else if (!manual && !enabled) {
                    // !Manual && !Enabled
                    comp.setForeground(java.awt.Color.GRAY);
                    comp.setFont(comp.getFont().deriveFont(Font.ITALIC));
                }

                return comp;
            }
        });  
        
        // Check / Cross statt einer Checkbox in den boolean values
        groupsTable.setDefaultRenderer(Boolean.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(
                    javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                
                String s = ((Boolean) value) ? "\u2713" : "\u2717";
                
                return table.getDefaultRenderer(String.class).getTableCellRendererComponent(table, s, isSelected, hasFocus, row, column);
            }
        });
    }

    @Override
    public void update() {
        if (competitionComboBox.getModel().getSize() == 0) {
            competitionComboBox.setModel(new CompetitionComboBoxModel());
            if (competitionComboBox.getModel().getSize() > 0) {
                competitionComboBox.setSelectedIndex(0);
                updateGroups();
            }
        } else {
            updateGroups();
        }
    }
    
    private void updateGroups() {
        List<Integer[]> selectedGroups = database.getGroups();
        Map<Integer, Integer[]> map = new java.util.HashMap<>();
        for (Integer[] data : selectedGroups)
            map.put(data[1], data);
        
        Competition cp = (Competition) competitionComboBox.getSelectedItem();
        Group[] groups = database.getGroups(cp);
        List<Object[]> list = new java.util.ArrayList<>();
        for (Group gr : groups) {
            list.add(new Object[] {gr.grName, gr.grDesc, gr.grStage, map.get(gr.grID)[3] != 0, map.get(gr.grID)[4] != 0, map.get(gr.grID)[2] != 0, gr.grID});
        }
        
        updateRows(list, groupsTable);
        
        for (Integer[] data : selectedGroups) {
            // !Sent && Enabled
            // Manual is ignored, we want to send it for all groups which are enabled
            if (data[2] == 0 && data[4] != 0) {
                database.setGroupSent(data[1]);
                database.addUpdateScheduleGroup(data[1]);
                database.addUpdateResultGroup(data[1]);
                database.addUpdatePositionGroup(data[1]);                
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

        competitionComboBox = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jLabel1 = new javax.swing.JLabel();
        autoButton = new javax.swing.JButton();
        enableButton = new javax.swing.JButton();
        disableButton = new javax.swing.JButton();

        competitionComboBox.setModel(new CompetitionComboBoxModel());
        competitionComboBox.setRenderer(new javax.swing.DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                JList<?> list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus)
            {
                String str = value == null ? null : ((Competition) value).cpDesc + " (" + ((Competition) value).cpName + ")";
                return super.getListCellRendererComponent(list, str, index, isSelected, cellHasFocus);
            }
        });
        competitionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                competitionComboBoxActionPerformed(evt);
            }
        });

        groupsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Description", "Stage", "Manual", "Enabled", "Sent"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        groupsTable.setFillsViewportHeight(true);
        jScrollPane1.setViewportView(groupsTable);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("smscenter/gui/resources/SMSCenter"); // NOI18N
        if (groupsTable.getColumnModel().getColumnCount() > 0) {
            groupsTable.getColumnModel().getColumn(0).setHeaderValue(bundle.getString("Name")); // NOI18N
            groupsTable.getColumnModel().getColumn(1).setHeaderValue(bundle.getString("Description")); // NOI18N
            groupsTable.getColumnModel().getColumn(2).setHeaderValue(bundle.getString("Stage")); // NOI18N
        }

        jLabel1.setText(bundle.getString("Selection:")); // NOI18N

        autoButton.setText("Auto");
        autoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                autoButtonActionPerformed(evt);
            }
        });

        enableButton.setText(bundle.getString("Check")); // NOI18N
        enableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enableButtonActionPerformed(evt);
            }
        });

        disableButton.setText(bundle.getString("Clear")); // NOI18N
        disableButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disableButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(competitionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(autoButton)
                                .addGap(18, 18, 18)
                                .addComponent(enableButton)
                                .addGap(18, 18, 18)
                                .addComponent(disableButton)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(competitionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(enableButton)
                    .addComponent(disableButton)
                    .addComponent(autoButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
        
    private void competitionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_competitionComboBoxActionPerformed
        groupsTable.getSelectionModel().clearSelection();
        updateGroups();
    }//GEN-LAST:event_competitionComboBoxActionPerformed

    private void enableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enableButtonActionPerformed
        final int[] rows = groupsTable.getSelectedRows();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                String message = ResourceBundle.getBundle("smscenter.gui.resources.SMSCenter").getString("Do you want to send notification SMS?");
                String title = ResourceBundle.getBundle("smscenter.gui.resources.SMSCenter").getString("Send SMS Notification");
                boolean sendSMS = JOptionPane.showConfirmDialog(GroupsPanel.this, message, title, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

                for (int i = 0; i < rows.length; i++) {
                    int grID = ((Integer) groupsTable.getModel().getValueAt(rows[i], 6));
                    database.setGroupEnabled(grID);
                    
                    // If messages shall be sent clear the Sent flag
                    // Sending is done in the main loop
                    // Else set the Sent flag, no past messages will be sent again
                    if (sendSMS)
                        database.clearGroupSent(grID);
                    else
                        database.setGroupSent(grID);
                }            
            }            
        }).start();

    }//GEN-LAST:event_enableButtonActionPerformed

    private void disableButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disableButtonActionPerformed
        final int[] rows = groupsTable.getSelectedRows();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < rows.length; i++) {
                    groupsTable.getModel().setValueAt(Boolean.FALSE, rows[i], 0);        
                    int grID = ((Integer) groupsTable.getModel().getValueAt(rows[i], 6));
                    database.setGrougDisabled(grID);
                }                
            }            
        }).start();

    }//GEN-LAST:event_disableButtonActionPerformed

    private void autoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_autoButtonActionPerformed
        final int[] rows = groupsTable.getSelectedRows();
        
        new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < rows.length; i++) {
                    groupsTable.getModel().setValueAt(Boolean.FALSE, rows[i], 0);        
                    int grID = ((Integer) groupsTable.getModel().getValueAt(rows[i], 6));
                    database.setGroupAuto(grID);
                }                
            }            
        }).start();

    }//GEN-LAST:event_autoButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton autoButton;
    private javax.swing.JComboBox competitionComboBox;
    private javax.swing.JButton disableButton;
    private javax.swing.JButton enableButton;
    private final javax.swing.JTable groupsTable = new javax.swing.JTable();
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private class CompetitionComboBoxModel extends javax.swing.DefaultComboBoxModel {

        public CompetitionComboBoxModel() {
            super(database.getCompetitions());
        }
    }
}