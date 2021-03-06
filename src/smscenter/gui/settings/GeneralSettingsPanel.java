/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smscenter.gui.settings;

import java.util.Properties;

/**
 *
 * @author chtheis
 */
public class GeneralSettingsPanel extends SettingsPanel {

    GeneralSettings generalSettings;
    
    /**
     * Creates new form GeneralSettingsPanel
     */
    public GeneralSettingsPanel() {
        initComponents();
    }
    
    
    @Override
    public void loadProperties(Properties props) {
        generalSettings = Settings.readGeneralSettings(props);
        
        updateIntervalSpinner.setValue(generalSettings.getUpdateInterval());
        updateDelaySpinner.setValue(generalSettings.getUpdateDelay());
        inboundIntervalSpinner.setValue(generalSettings.getInboundInterval());
        outboundIntervalSpinner.setValue(generalSettings.getOutboundInterval());
        sendAsyncCheckbox.setSelected(generalSettings.isSendModeAsync());
        deleteAfterReceiveCheckbox.setSelected(generalSettings.isDeleteAfterProcessing());
        reminderTimeSpinner.setValue(generalSettings.getReminderTime());
        reminderTimeNextDaySpinner.setValue(generalSettings.getReminderTimeNextDay());
        ReminderCuttoffSpinner.setValue(generalSettings.getReminderCutoff());
    }
    
    
    @Override
    public void writeProperties(Properties props) {
        generalSettings.setUpdateInterval((Integer) updateIntervalSpinner.getValue());
        generalSettings.setUpdateDelay((Integer) updateDelaySpinner.getValue());
        generalSettings.setInboundInterval((Integer) inboundIntervalSpinner.getValue());
        generalSettings.setOutboundInterval((Integer) outboundIntervalSpinner.getValue());
        generalSettings.setSendModeAsync(sendAsyncCheckbox.isSelected());
        generalSettings.setDeleteAfterProcessing(deleteAfterReceiveCheckbox.isSelected());
        generalSettings.setReminderTime((Integer) reminderTimeSpinner.getValue());
        generalSettings.setReminderTimeNextDay((Integer) reminderTimeNextDaySpinner.getValue());
        generalSettings.setReminderCutoff((Integer) ReminderCuttoffSpinner.getValue());
        
        Settings.writeGeneralSettings(generalSettings, props);
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
        inboundIntervalSpinner = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        outboundIntervalSpinner = new javax.swing.JSpinner();
        sendAsyncCheckbox = new javax.swing.JCheckBox();
        deleteAfterReceiveCheckbox = new javax.swing.JCheckBox();
        jLabel3 = new javax.swing.JLabel();
        updateIntervalSpinner = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        updateDelaySpinner = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        reminderTimeSpinner = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        reminderTimeNextDaySpinner = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        ReminderCuttoffSpinner = new javax.swing.JSpinner();

        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("smscenter/gui/resources/SMSCenter"); // NOI18N
        jLabel1.setText(bundle.getString("Inbound interval:")); // NOI18N

        inboundIntervalSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10));

        jLabel2.setText(bundle.getString("Outbound interval:")); // NOI18N

        outboundIntervalSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 10));

        sendAsyncCheckbox.setText(bundle.getString("Send async")); // NOI18N

        deleteAfterReceiveCheckbox.setText(bundle.getString("Delete after receive")); // NOI18N

        jLabel3.setText(bundle.getString("Update interval:")); // NOI18N

        jLabel4.setText(bundle.getString("Update delay:")); // NOI18N

        jLabel7.setText("Reminder time (m):");

        jLabel8.setText("Reminder time next day (m):");

        jLabel9.setText("Reminder cuttoff time (m):");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(inboundIntervalSpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(updateIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reminderTimeSpinner)
                            .addComponent(ReminderCuttoffSpinner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(outboundIntervalSpinner)
                            .addComponent(updateDelaySpinner, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                            .addComponent(reminderTimeNextDaySpinner)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(sendAsyncCheckbox)
                        .addGap(309, 309, 309)
                        .addComponent(deleteAfterReceiveCheckbox, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ReminderCuttoffSpinner, inboundIntervalSpinner, reminderTimeSpinner, updateIntervalSpinner});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {outboundIntervalSpinner, reminderTimeNextDaySpinner, updateDelaySpinner});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(updateIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(updateDelaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inboundIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(outboundIntervalSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(reminderTimeSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(reminderTimeNextDaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ReminderCuttoffSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendAsyncCheckbox)
                    .addComponent(deleteAfterReceiveCheckbox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSpinner ReminderCuttoffSpinner;
    private javax.swing.JCheckBox deleteAfterReceiveCheckbox;
    private javax.swing.JSpinner inboundIntervalSpinner;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner outboundIntervalSpinner;
    private javax.swing.JSpinner reminderTimeNextDaySpinner;
    private javax.swing.JSpinner reminderTimeSpinner;
    private javax.swing.JCheckBox sendAsyncCheckbox;
    private javax.swing.JSpinner updateDelaySpinner;
    private javax.swing.JSpinner updateIntervalSpinner;
    // End of variables declaration//GEN-END:variables
}
