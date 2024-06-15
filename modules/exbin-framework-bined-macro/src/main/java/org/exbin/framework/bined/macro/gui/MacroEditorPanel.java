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

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.exbin.framework.App;
import org.exbin.framework.bined.macro.model.MacroRecord;
import org.exbin.framework.language.api.LanguageModuleApi;
import org.exbin.framework.utils.TestApplication;
import org.exbin.framework.utils.UtilsModule;
import org.exbin.framework.utils.WindowUtils;

/**
 * Macro editor panel.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class MacroEditorPanel extends javax.swing.JPanel {

    private final ResourceBundle resourceBundle = App.getModule(LanguageModuleApi.class).getBundle(MacroEditorPanel.class);

    public MacroEditorPanel() {
        initComponents();
        init();
    }

    private void init() {
    }

    @Nonnull
    public MacroRecord getMacroRecord() {
        MacroRecord macroRecord = new MacroRecord(
                nameTextField.getText()
        );
        List<String> steps = new ArrayList<>();
        String stepsText = stepsTextArea.getText();
        int position = 0;
        while (position < stepsText.length()) {
            int nextLineBreak = stepsText.indexOf("\n", position);
            if (nextLineBreak >= 0) {
                String line = stepsText.substring(position, nextLineBreak);
                steps.add(line);
                position = nextLineBreak + 1;
            } else {
                if (position < stepsText.length()) {
                    String line = stepsText.substring(position);
                    steps.add(line);
                }
                break;
            }
        }

        macroRecord.setSteps(steps);
        return macroRecord;
    }

    public void setMacroRecord(MacroRecord macroRecord) {
        nameTextField.setText(macroRecord.getName());
        StringBuilder stringBuilder = new StringBuilder();
        for (String step : macroRecord.getSteps()) {
            stringBuilder.append(step);
            stringBuilder.append("\n");
        }
        stepsTextArea.setText(stringBuilder.toString());
    }

    @Nonnull
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    @Nonnull
    public String getMacroName() {
        return nameTextField.getText();
    }

    public void setMacroName(String name) {
        nameTextField.setText(name);
    }

    @Nonnull
    public String getMacroSteps() {
        return stepsTextArea.getText();
    }

    public void setMacroSteps(String name) {
        stepsTextArea.setText(name);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        stepsLabel = new javax.swing.JLabel();
        stepsScrollPane = new javax.swing.JScrollPane();
        stepsTextArea = new javax.swing.JTextArea();

        nameLabel.setText(resourceBundle.getString("nameLabel.text")); // NOI18N

        stepsLabel.setText(resourceBundle.getString("stepsLabel.text")); // NOI18N

        stepsTextArea.setColumns(20);
        stepsTextArea.setRows(5);
        stepsScrollPane.setViewportView(stepsTextArea);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(stepsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 834, Short.MAX_VALUE)
                    .addComponent(nameTextField, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameLabel)
                            .addComponent(stepsLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stepsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Test method for this panel.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestApplication testApplication = UtilsModule.createTestApplication();
        testApplication.launch(() -> {
            testApplication.addModule(org.exbin.framework.language.api.LanguageModuleApi.MODULE_ID, new org.exbin.framework.language.api.utils.TestLanguageModule());
            WindowUtils.invokeWindow(new MacroEditorPanel());
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel stepsLabel;
    private javax.swing.JScrollPane stepsScrollPane;
    private javax.swing.JTextArea stepsTextArea;
    // End of variables declaration//GEN-END:variables
}
