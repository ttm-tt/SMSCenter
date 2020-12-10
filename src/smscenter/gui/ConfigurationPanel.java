/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package smscenter.gui;

import java.util.Properties;
import smscenter.gui.settings.DatabaseSettingsPanel;
import smscenter.gui.settings.GatewaySettingsPanel;
import smscenter.gui.settings.GeneralSettingsPanel;

/**
 *
 * @author chtheis
 */
public class ConfigurationPanel extends javax.swing.JPanel {

    public void loadProperties(Properties props) {
        // Load Properties of panels
        generalSettingsPanel.loadProperties(props);
        databaseSettingsPanel.loadProperties(props);
        gatewaySettingsPanel.loadProperties(props);
    }
    
    
    public void writeProperties(Properties props) {
        // Write properties of panels
        generalSettingsPanel.writeProperties(props);
        databaseSettingsPanel.writeProperties(props);
        gatewaySettingsPanel.writeProperties(props);
    }
    
    
    /**
     * Creates new form Configuration
     */
    public ConfigurationPanel() {
        initComponents(); 
        
        generalSettingsPanel = new GeneralSettingsPanel();
        databaseSettingsPanel = new DatabaseSettingsPanel();
        gatewaySettingsPanel = new GatewaySettingsPanel();
        
        jTabbedPane1.add("General", generalSettingsPanel);
        jTabbedPane1.add("Database", databaseSettingsPanel);
        jTabbedPane1.add("Gateways", gatewaySettingsPanel);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables

    private GeneralSettingsPanel generalSettingsPanel;
    private DatabaseSettingsPanel databaseSettingsPanel;
    private GatewaySettingsPanel gatewaySettingsPanel;
}