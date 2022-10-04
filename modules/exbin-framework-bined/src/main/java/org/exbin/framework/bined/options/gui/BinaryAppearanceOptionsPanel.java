/*
 * Copyright (C) ExBin Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.exbin.framework.bined.options.gui;

import java.util.ResourceBundle;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import org.exbin.framework.bined.options.impl.BinaryAppearanceOptionsImpl;
import org.exbin.framework.utils.LanguageUtils;
import org.exbin.framework.utils.WindowUtils;
import org.exbin.framework.options.api.OptionsCapable;
import org.exbin.framework.options.api.OptionsModifiedListener;

/**
 * Binary viewer/editor appearance options panel.
 *
 * @version 0.2.1 2021/10/17
 * @author ExBin Project (http://exbin.org)
 */
@ParametersAreNonnullByDefault
public class BinaryAppearanceOptionsPanel extends javax.swing.JPanel implements OptionsCapable<BinaryAppearanceOptionsImpl> {

    private OptionsModifiedListener optionsModifiedListener;
    private final ResourceBundle resourceBundle = LanguageUtils.getResourceBundleByClass(BinaryAppearanceOptionsPanel.class);

    public BinaryAppearanceOptionsPanel() {
        initComponents();
    }

    @Nonnull
    @Override
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    @Override
    public void loadFromOptions(BinaryAppearanceOptionsImpl options) {
        showParsingPanelCheckBox.setSelected(options.isShowParsingPanel());
        multiFileModeCheckBox.setSelected(options.isMultiFileMode());
    }

    @Override
    public void saveToOptions(BinaryAppearanceOptionsImpl options) {
        options.setShowParsingPanel(showParsingPanelCheckBox.isSelected());
        options.setMultiFileMode(multiFileModeCheckBox.isSelected());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        multiFileModeCheckBox = new javax.swing.JCheckBox();
        showParsingPanelCheckBox = new javax.swing.JCheckBox();

        setName("Form"); // NOI18N

        multiFileModeCheckBox.setSelected(true);
        multiFileModeCheckBox.setText(resourceBundle.getString("multiFileModeCheckBox.text")); // NOI18N
        multiFileModeCheckBox.setName("multiFileModeCheckBox"); // NOI18N

        showParsingPanelCheckBox.setSelected(true);
        showParsingPanelCheckBox.setText(resourceBundle.getString("showParsingPanelCheckBox.text")); // NOI18N
        showParsingPanelCheckBox.setName("showParsingPanelCheckBox"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(showParsingPanelCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(multiFileModeCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showParsingPanelCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(multiFileModeCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Test method for this panel.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        WindowUtils.invokeDialog(new BinaryAppearanceOptionsPanel());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox multiFileModeCheckBox;
    private javax.swing.JCheckBox showParsingPanelCheckBox;
    // End of variables declaration//GEN-END:variables

    private void setModified(boolean modified) {
        if (optionsModifiedListener != null) {
            optionsModifiedListener.wasModified();
        }
    }

    @Override
    public void setOptionsModifiedListener(OptionsModifiedListener listener) {
        optionsModifiedListener = listener;
    }
}
