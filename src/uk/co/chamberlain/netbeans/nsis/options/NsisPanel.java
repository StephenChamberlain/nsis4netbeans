/*
 * NSIS 4 NetBeans
 * Copyright (C) 2016 Stephen Chamberlain
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.co.chamberlain.netbeans.nsis.options;

import javax.swing.JFileChooser;

final class NsisPanel extends javax.swing.JPanel {
    
    private final NsisOptionsPanelController controller;

    NsisPanel(NsisOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        nsisFileChooser = new javax.swing.JFileChooser();
        nsisHome = new javax.swing.JTextField();
        nsisHomeBrowse = new javax.swing.JButton();
        nsisHomeLabel = new javax.swing.JLabel();
        nsisVerbosityLabel = new javax.swing.JLabel();
        nsisVerbosity = new javax.swing.JComboBox<>();
        filler = new javax.swing.JPanel();

        nsisFileChooser.setDialogTitle(org.openide.util.NbBundle.getMessage(NsisPanel.class, "NsisPanel.nsisFileChooser.dialogTitle")); // NOI18N
        nsisFileChooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);

        setLayout(new java.awt.GridBagLayout());

        nsisHome.setText(org.openide.util.NbBundle.getMessage(NsisPanel.class, "NsisPanel.nsisHome.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(nsisHome, gridBagConstraints);

        org.openide.awt.Mnemonics.setLocalizedText(nsisHomeBrowse, org.openide.util.NbBundle.getMessage(NsisPanel.class, "NsisPanel.nsisHomeBrowse.text")); // NOI18N
        nsisHomeBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nsisHomeBrowseActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(nsisHomeBrowse, gridBagConstraints);

        nsisHomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        org.openide.awt.Mnemonics.setLocalizedText(nsisHomeLabel, org.openide.util.NbBundle.getMessage(NsisPanel.class, "NsisPanel.nsisHomeLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(nsisHomeLabel, gridBagConstraints);

        nsisVerbosityLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        org.openide.awt.Mnemonics.setLocalizedText(nsisVerbosityLabel, org.openide.util.NbBundle.getMessage(NsisPanel.class, "NsisPanel.nsisVerbosityLabel.text")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(nsisVerbosityLabel, gridBagConstraints);

        nsisVerbosity.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No output", "Errors only", "Warnings and errors", "Info, warnings, and errors", "All output" }));
        nsisVerbosity.setSelectedIndex(2);
        nsisVerbosity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nsisVerbosityActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        add(nsisVerbosity, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(filler, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void nsisHomeBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nsisHomeBrowseActionPerformed
        final int result = nsisFileChooser.showDialog(this, "Ok");
        if (result == JFileChooser.APPROVE_OPTION) {
            nsisHome.setText(nsisFileChooser.getSelectedFile().getAbsolutePath());
            controller.changed();
        }
    }//GEN-LAST:event_nsisHomeBrowseActionPerformed

    private void nsisVerbosityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nsisVerbosityActionPerformed
        controller.changed();
    }//GEN-LAST:event_nsisVerbosityActionPerformed

    void load() {
        nsisHome.setText(NsisOptionsManager.getNsisHome());
        nsisVerbosity.setSelectedIndex(NsisOptionsManager.getNsisVerbosity());
    }

    void store() {
        NsisOptionsManager.setNsisHome(nsisHome.getText());
        NsisOptionsManager.setNsisVerbosity(nsisVerbosity.getSelectedIndex());
    }

    boolean valid() {
        /*
        TODO: FIX: option pane is shown recursively; looks like this method keeps 
        getting called? Find out how 'valid' works...
        
        final File makensisExeFile = new File(
                NbPreferences.forModule(NsisPanel.class).get("nsis.home", "")
                + System.getProperty("file.separator")
                + MAKENSIS_EXE_NAME);

        if (makensisExeFile.exists()) {
            return true;
        }

        JOptionPane.showMessageDialog(
                this,
                "'makensis.exe' not detected in this directory; please select the installation location of NSIS!",
                "NSIS Home Error",
                JOptionPane.ERROR_MESSAGE);

        return false;
        */

        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel filler;
    private javax.swing.JFileChooser nsisFileChooser;
    private javax.swing.JTextField nsisHome;
    private javax.swing.JButton nsisHomeBrowse;
    private javax.swing.JLabel nsisHomeLabel;
    private javax.swing.JComboBox<String> nsisVerbosity;
    private javax.swing.JLabel nsisVerbosityLabel;
    // End of variables declaration//GEN-END:variables
}
