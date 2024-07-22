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
package org.exbin.framework.bined.search.gui;

import org.exbin.framework.bined.search.SearchCondition;
import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ResourceBundle;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JViewport;
import org.exbin.bined.swing.section.SectCodeArea;
import org.exbin.framework.bined.handler.CodeAreaPopupMenuHandler;
import org.exbin.framework.language.api.LanguageModuleApi;
import org.exbin.framework.utils.WindowUtils;
import org.exbin.auxiliary.binary_data.EditableBinaryData;
import org.exbin.framework.App;
import org.exbin.framework.utils.TestApplication;
import org.exbin.framework.utils.UtilsModule;

/**
 * Multiline search condition editor panel.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class BinaryMultilinePanel extends javax.swing.JPanel {

    private static final String POPUP_MENU_POSTFIX = ".binaryMultilinePanel";

    private final java.util.ResourceBundle resourceBundle = App.getModule(LanguageModuleApi.class).getBundle(BinaryMultilinePanel.class);
    private SearchCondition condition;

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private SectCodeArea codeArea;
    private CodeAreaPopupMenuHandler codeAreaPopupMenuHandler;

    public BinaryMultilinePanel() {
        initComponents();
    }

    @Nonnull
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setPreferredSize(new java.awt.Dimension(400, 300));
        setLayout(new java.awt.BorderLayout());
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
            WindowUtils.invokeWindow(new BinaryMultilinePanel());
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    @Nonnull
    public String getMultilineText() {
        return textArea.getText();
    }

    @Nonnull
    public SearchCondition getCondition() {
        if (condition.getSearchMode() == SearchCondition.SearchMode.TEXT) {
            condition.setSearchText(textArea.getText());
        } else {
            condition.setBinaryData((EditableBinaryData) codeArea.getContentData());
        }

        return condition;
    }

    public void setCondition(SearchCondition condition) {
        this.condition = condition;
        if (condition.getSearchMode() == SearchCondition.SearchMode.TEXT) {
            scrollPane = new javax.swing.JScrollPane();
            textArea = new javax.swing.JTextArea();
            textArea.setColumns(20);
            textArea.setRows(5);
            textArea.setName("textArea"); // NOI18N
            scrollPane.setViewportView(textArea);

            textArea.setText(condition.getSearchText());
            add(scrollPane, BorderLayout.CENTER);
        } else {
            codeArea = new SectCodeArea();
            codeArea.setContentData(condition.getBinaryData());
            codeArea.setFocusTraversalKeysEnabled(false);
            add(codeArea, BorderLayout.CENTER);
            if (codeAreaPopupMenuHandler != null) {
                attachPopupMenu();
            }
        }
        revalidate();
    }

    public void setCodeAreaPopupMenuHandler(CodeAreaPopupMenuHandler codeAreaPopupMenuHandler) {
        this.codeAreaPopupMenuHandler = codeAreaPopupMenuHandler;
        if (codeArea != null) {
            attachPopupMenu();
        }
    }

    private void attachPopupMenu() {
        codeArea.setComponentPopupMenu(new JPopupMenu() {
            @Override
            public void show(@Nonnull Component invoker, int x, int y) {
                int clickedX = x;
                int clickedY = y;
                if (invoker instanceof JViewport) {
                    clickedX += ((JViewport) invoker).getParent().getX();
                    clickedY += ((JViewport) invoker).getParent().getY();
                }
                JPopupMenu popupMenu = codeAreaPopupMenuHandler.createPopupMenu(codeArea, POPUP_MENU_POSTFIX, clickedX, clickedY);
                popupMenu.show(invoker, x, y);
                codeAreaPopupMenuHandler.dropPopupMenu(POPUP_MENU_POSTFIX);
            }
        });
    }

    public void detachMenu() {
        if (condition.getSearchMode() == SearchCondition.SearchMode.BINARY) {
            codeAreaPopupMenuHandler.dropPopupMenu(POPUP_MENU_POSTFIX);
        }
    }
}
