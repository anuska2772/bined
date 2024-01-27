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
package org.exbin.framework.bined.macro.gui;

import org.exbin.framework.bined.macro.model.MacrosTableModel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.JTable;
import org.exbin.framework.App;
import org.exbin.framework.bined.macro.model.MacroRecord;
import org.exbin.framework.language.api.LanguageModuleApi;
import org.exbin.framework.utils.WindowUtils;

/**
 * Macros manager panel.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class MacrosManagerPanel extends javax.swing.JPanel {

    private final ResourceBundle resourceBundle = App.getModule(LanguageModuleApi.class).getBundle(MacrosManagerPanel.class);
    private final MacrosTableModel macrosTableModel = new MacrosTableModel();
    private Control control = null;

    public MacrosManagerPanel() {
        initComponents();
        init();
    }

    private void init() {
        add(controlPanel, BorderLayout.EAST);
        table.getSelectionModel().addListSelectionListener((e) -> {
            int rowCount = table.getRowCount();
            int selectedRows = table.getSelectedRowCount();
            editButton.setEnabled(selectedRows == 1);
            removeButton.setEnabled(selectedRows > 0);
            selectAllButton.setEnabled(rowCount > 0);
            upButton.setEnabled(selectedRows > 0 && table.getSelectionModel().getMinSelectionIndex() > 0);
            downButton.setEnabled(selectedRows > 0 && table.getSelectionModel().getMaxSelectionIndex() < rowCount - 1);
        });
    }

    @Nonnull
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        controlPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();
        selectAllButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        listPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        addButton.setText(resourceBundle.getString("addButton.text")); // NOI18N
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        editButton.setText(resourceBundle.getString("editButton.text")); // NOI18N
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        upButton.setText(resourceBundle.getString("upButton.text")); // NOI18N
        upButton.setEnabled(false);
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });

        downButton.setText(resourceBundle.getString("downButton.text")); // NOI18N
        downButton.setEnabled(false);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });

        selectAllButton.setText(resourceBundle.getString("selectAllButton.text")); // NOI18N
        selectAllButton.setEnabled(false);
        selectAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectAllButtonActionPerformed(evt);
            }
        });

        removeButton.setText(resourceBundle.getString("removeButton.text")); // NOI18N
        removeButton.setEnabled(false);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout controlPanelLayout = new javax.swing.GroupLayout(controlPanel);
        controlPanel.setLayout(controlPanelLayout);
        controlPanelLayout.setHorizontalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(removeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(selectAllButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(downButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(upButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 118, Short.MAX_VALUE))
                .addContainerGap())
        );
        controlPanelLayout.setVerticalGroup(
            controlPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, controlPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(addButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(upButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(downButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(selectAllButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setLayout(new java.awt.BorderLayout());

        table.setModel(macrosTableModel);
        scrollPane.setViewportView(table);

        javax.swing.GroupLayout listPanelLayout = new javax.swing.GroupLayout(listPanel);
        listPanel.setLayout(listPanelLayout);
        listPanelLayout.setHorizontalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
            .addGroup(listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(listPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane)
                    .addContainerGap()))
        );
        listPanelLayout.setVerticalGroup(
            listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
            .addGroup(listPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(listPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(scrollPane)
                    .addContainerGap()))
        );

        add(listPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        control.moveUp();
    }//GEN-LAST:event_upButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        control.moveDown();
    }//GEN-LAST:event_downButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        control.addRecord();
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        control.removeRecord();
    }//GEN-LAST:event_removeButtonActionPerformed

    private void selectAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectAllButtonActionPerformed
        control.selectAll();
    }//GEN-LAST:event_selectAllButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        control.editRecord();
    }//GEN-LAST:event_editButtonActionPerformed

    /**
     * Test method for this panel.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowUtils.invokeWindow(new MacrosManagerPanel());
    }

    @Nonnull
    public List<MacroRecord> getMacroRecords() {
        return macrosTableModel.getRecords();
    }

    public void setMacroRecords(List<MacroRecord> macroRecords) {
        macrosTableModel.setRecords(macroRecords);
        macrosTableModel.fireTableDataChanged();
        table.repaint();
    }

    public void updateMacroRecords(List<MacroRecord> macroRecords) {
        macrosTableModel.setRecords(macroRecords);
        macrosTableModel.fireDataChanged();
        table.repaint();
    }

    public void updateRecord(MacroRecord macroRecord, int index) {
        macrosTableModel.updateRecord(macroRecord, index);
        table.repaint();
    }

    @Nullable
    public MacroRecord getSelectedRecord() {
        int selectedRow = table.getSelectedRow();
        return selectedRow >= 0 ? macrosTableModel.getRecords().get(selectedRow) : null;
    }

    @Nonnull
    public JTable getTable() {
        return table;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel controlPanel;
    private javax.swing.JButton downButton;
    private javax.swing.JButton editButton;
    private javax.swing.JPanel listPanel;
    private javax.swing.JButton removeButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton selectAllButton;
    private javax.swing.JTable table;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    public interface Control {

        void addRecord();

        void editRecord();

        void removeRecord();

        void selectAll();

        void moveUp();

        void moveDown();
    }
}
