/*
 * Copyright (C) ExBin Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.exbin.framework.bined.gui;

import java.awt.datatransfer.DataFlavor;
import javax.annotation.ParametersAreNonnullByDefault;
import org.exbin.framework.utils.ClipboardUtils;
import org.exbin.framework.utils.WindowUtils;

/**
 * Clipboard content panel.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class ClipboardContentPanel extends javax.swing.JPanel {
    
    private DataFlavor[] dataFlavors;

    public ClipboardContentPanel() {
        initComponents();
        init();
    }
    
    private void init() {
        flavorsList.setModel(new DataFlavorsListModel());
    }
    
    public void loadFromClipboard() {
        dataFlavors = ClipboardUtils.getClipboard().getAvailableDataFlavors();
        ((DataFlavorsListModel) flavorsList.getModel()).setDataFlavors(dataFlavors);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        availableFlavorsLabel = new javax.swing.JLabel();
        flavorsScrollPane = new javax.swing.JScrollPane();
        flavorsList = new javax.swing.JList<>();
        flavorContentPanel = new javax.swing.JPanel();

        availableFlavorsLabel.setText("Available Flavors");

        flavorsScrollPane.setViewportView(flavorsList);

        flavorContentPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Content"));

        javax.swing.GroupLayout flavorContentPanelLayout = new javax.swing.GroupLayout(flavorContentPanel);
        flavorContentPanel.setLayout(flavorContentPanelLayout);
        flavorContentPanelLayout.setHorizontalGroup(
            flavorContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 619, Short.MAX_VALUE)
        );
        flavorContentPanelLayout.setVerticalGroup(
            flavorContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flavorsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(availableFlavorsLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(flavorContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(flavorContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(availableFlavorsLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flavorsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 622, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Test method for this panel.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowUtils.invokeDialog(new ClipboardContentPanel());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel availableFlavorsLabel;
    private javax.swing.JPanel flavorContentPanel;
    private javax.swing.JList<String> flavorsList;
    private javax.swing.JScrollPane flavorsScrollPane;
    // End of variables declaration//GEN-END:variables
}
