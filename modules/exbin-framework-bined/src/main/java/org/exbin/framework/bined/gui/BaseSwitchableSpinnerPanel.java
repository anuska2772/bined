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

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.util.Arrays;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import org.exbin.bined.CodeAreaUtils;
import org.exbin.bined.CodeCharactersCase;
import org.exbin.bined.PositionCodeType;
import org.exbin.framework.App;
import org.exbin.framework.language.api.LanguageModuleApi;
import org.exbin.framework.utils.TestApplication;
import org.exbin.framework.utils.UtilsModule;
import org.exbin.framework.utils.WindowUtils;

/**
 * Spinner supporting multiple bases.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class BaseSwitchableSpinnerPanel extends javax.swing.JPanel {

    private final java.util.ResourceBundle resourceBundle = App.getModule(LanguageModuleApi.class).getBundle(BaseSwitchableSpinnerPanel.class);

    private boolean adjusting;
    private final PositionSpinnerEditor spinnerEditor;
    private static final String SPINNER_PROPERTY = "value";

    public BaseSwitchableSpinnerPanel() {
        initComponents();
        spinnerEditor = new PositionSpinnerEditor(spinner);
        spinner.setEditor(spinnerEditor);
        init();
    }

    private void init() {
        // Spinner selection workaround from http://forums.sun.com/thread.jspa?threadID=409748&forumID=57
        spinnerEditor.getTextField().addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (e.getSource() instanceof JTextComponent) {
                    final JTextComponent textComponent = ((JTextComponent) e.getSource());
                    SwingUtilities.invokeLater(textComponent::selectAll);
                }
            }
        });

        Dimension preferredSize = baseSwitchButton.getPreferredSize();
        setPreferredSize(new Dimension(preferredSize.width * 4, preferredSize.height));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        baseSwitchPopupMenu = new javax.swing.JPopupMenu();
        octalMenuItem = new javax.swing.JMenuItem();
        decimalMenuItem = new javax.swing.JMenuItem();
        hexadecimalMenuItem = new javax.swing.JMenuItem();
        baseSwitchButton = new javax.swing.JButton();
        spinner = new javax.swing.JSpinner();

        octalMenuItem.setText(resourceBundle.getString("octalMenuItem.text")); // NOI18N
        octalMenuItem.setToolTipText(resourceBundle.getString("octalMenuItem.toolTipText")); // NOI18N
        octalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                octalMenuItemActionPerformed(evt);
            }
        });
        baseSwitchPopupMenu.add(octalMenuItem);

        decimalMenuItem.setText(resourceBundle.getString("decimalMenuItem.text")); // NOI18N
        decimalMenuItem.setToolTipText(resourceBundle.getString("decimalMenuItem.toolTipText")); // NOI18N
        decimalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                decimalMenuItemActionPerformed(evt);
            }
        });
        baseSwitchPopupMenu.add(decimalMenuItem);

        hexadecimalMenuItem.setText(resourceBundle.getString("hexadecimalMenuItem.text")); // NOI18N
        hexadecimalMenuItem.setToolTipText(resourceBundle.getString("hexadecimalMenuItem.toolTipText")); // NOI18N
        hexadecimalMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hexadecimalMenuItemActionPerformed(evt);
            }
        });
        baseSwitchPopupMenu.add(hexadecimalMenuItem);

        setPreferredSize(new java.awt.Dimension(400, 300));

        baseSwitchButton.setText(resourceBundle.getString("codeType.decimal")); // NOI18N
        baseSwitchButton.setToolTipText(resourceBundle.getString("codeType.decimal.toolTipText")); // NOI18N
        baseSwitchButton.setComponentPopupMenu(baseSwitchPopupMenu);
        baseSwitchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baseSwitchButtonActionPerformed(evt);
            }
        });

        spinner.setModel(new javax.swing.SpinnerNumberModel(0L, null, null, 1L));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(baseSwitchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinner))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(spinner)
            .addComponent(baseSwitchButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void baseSwitchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baseSwitchButtonActionPerformed
        PositionCodeType positionCodeType = spinnerEditor.getPositionCodeType();
        switch (positionCodeType) {
            case OCTAL: {
                switchNumBase(PositionCodeType.DECIMAL);
                break;
            }
            case DECIMAL: {
                switchNumBase(PositionCodeType.HEXADECIMAL);
                break;
            }
            case HEXADECIMAL: {
                switchNumBase(PositionCodeType.OCTAL);
                break;
            }
            default:
                throw CodeAreaUtils.getInvalidTypeException(positionCodeType);
        }
    }//GEN-LAST:event_baseSwitchButtonActionPerformed

    private void octalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_octalMenuItemActionPerformed
        switchNumBase(PositionCodeType.OCTAL);
    }//GEN-LAST:event_octalMenuItemActionPerformed

    private void decimalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_decimalMenuItemActionPerformed
        switchNumBase(PositionCodeType.DECIMAL);
    }//GEN-LAST:event_decimalMenuItemActionPerformed

    private void hexadecimalMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hexadecimalMenuItemActionPerformed
        switchNumBase(PositionCodeType.HEXADECIMAL);
    }//GEN-LAST:event_hexadecimalMenuItemActionPerformed

    /**
     * Test method for this panel.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TestApplication testApplication = UtilsModule.createTestApplication();
        testApplication.launch(() -> {
            testApplication.addModule(org.exbin.framework.language.api.LanguageModuleApi.MODULE_ID, new org.exbin.framework.language.api.utils.TestLanguageModule());
            WindowUtils.invokeWindow(new BaseSwitchableSpinnerPanel());
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton baseSwitchButton;
    private javax.swing.JPopupMenu baseSwitchPopupMenu;
    private javax.swing.JMenuItem decimalMenuItem;
    private javax.swing.JMenuItem hexadecimalMenuItem;
    private javax.swing.JMenuItem octalMenuItem;
    private javax.swing.JSpinner spinner;
    // End of variables declaration//GEN-END:variables

    private void switchNumBase(PositionCodeType codeType) {
        adjusting = true;
        long value = getValue();
        String codeTypeName = codeType.name().toLowerCase();
        baseSwitchButton.setText(resourceBundle.getString("codeType." + codeTypeName));
        baseSwitchButton.setToolTipText(resourceBundle.getString("codeType." + codeTypeName + ".toolTipText"));
        spinnerEditor.setPositionCodeType(codeType);
        setValue(value);
        adjusting = false;
    }

    public long getValue() {
        return (Long) spinner.getValue();
    }

    public void setValue(long value) {
        spinnerEditor.setPositionValue(value);
    }

    public void acceptInput() {
        try {
            spinner.commitEdit();
        } catch (ParseException ex) {
            // Ignore parse exception
        }
    }

    public void initFocus() {
        /* ((JSpinner.DefaultEditor) positionSpinner.getEditor()) */
        spinnerEditor.getTextField().requestFocusInWindow();
    }

    public void setMinimum(long minimum) {
        ((SpinnerNumberModel) spinner.getModel()).setMinimum(minimum);
    }

    public void setMaximum(long maximum) {
        ((SpinnerNumberModel) spinner.getModel()).setMaximum(maximum);
    }

    public void revalidateSpinner() {
        spinner.revalidate();
    }

    public void addChangeListener(ChangeListener changeListener) {
        spinner.addChangeListener(changeListener);
    }

    public void removeChangeListener(ChangeListener changeListener) {
        spinner.removeChangeListener(changeListener);
    }

    @ParametersAreNonnullByDefault
    private class PositionSpinnerEditor extends JPanel implements ChangeListener, PropertyChangeListener, LayoutManager {

        private static final int LENGTH_LIMIT = 21;

        private PositionCodeType positionCodeType = PositionCodeType.DECIMAL;

        private final char[] cache = new char[LENGTH_LIMIT];

        private final JTextField textField;
        private final JSpinner spinner;

        public PositionSpinnerEditor(JSpinner spinner) {
            this.spinner = spinner;
            textField = new JTextField();

            init();
        }

        private void init() {
            textField.setName("Spinner.textField");
            textField.setText(getPositionAsString((Long) spinner.getValue()));
            textField.addPropertyChangeListener(this);
            textField.getDocument().addDocumentListener(new DocumentListener() {
                private final PropertyChangeEvent changeEvent = new PropertyChangeEvent(textField, SPINNER_PROPERTY, null, null);

                @Override
                public void changedUpdate(DocumentEvent e) {
                    notifyChanged();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    notifyChanged();
                }

                @Override
                public void insertUpdate(DocumentEvent e) {
                    notifyChanged();
                }

                public void notifyChanged() {
                    propertyChange(changeEvent);
                }
            });
            textField.setEditable(true);
            textField.setInheritsPopupMenu(true);

            String toolTipText = spinner.getToolTipText();
            if (toolTipText != null) {
                textField.setToolTipText(toolTipText);
            }

            add(textField);

            setLayout(this);
            spinner.addChangeListener(this);
        }

        @Nonnull
        private JTextField getTextField() {
            return textField;
        }

        @Nonnull
        private JSpinner getSpinner() {
            return spinner;
        }

        @Override
        public void stateChanged(ChangeEvent e) {
            if (adjusting) {
                return;
            }

            JSpinner sourceSpinner = (JSpinner) (e.getSource());
            SwingUtilities.invokeLater(() -> {
                textField.setText(getPositionAsString((Long) sourceSpinner.getValue()));
            });
        }

        @Override
        public void propertyChange(PropertyChangeEvent e) {
            if (adjusting) {
                return;
            }

            JSpinner sourceSpinner = getSpinner();

            Object source = e.getSource();
            String name = e.getPropertyName();
            if ((source instanceof JTextField) && SPINNER_PROPERTY.equals(name)) {
                Long lastValue = (Long) sourceSpinner.getValue();

                // Try to set the new value
                try {
                    sourceSpinner.setValue(valueOfPosition(getTextField().getText()));
                } catch (IllegalArgumentException iae) {
                    // SpinnerModel didn't like new value, reset
                    try {
                        sourceSpinner.setValue(lastValue);
                    } catch (IllegalArgumentException iae2) {
                        // Still bogus, nothing else we can do, the
                        // SpinnerModel and JFormattedTextField are now out
                        // of sync.
                    }
                }
            }
        }

        public void setPositionValue(long positionValue) {
            textField.setText(getPositionAsString(positionValue));
            spinner.setValue(positionValue);
        }

        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        /**
         * Returns the size of the parents insets.
         */
        @Nonnull
        private Dimension insetSize(Container parent) {
            Insets insets = parent.getInsets();
            int width = insets.left + insets.right;
            int height = insets.top + insets.bottom;
            return new Dimension(width, height);
        }

        @Nonnull
        @Override
        public Dimension preferredLayoutSize(Container parent) {
            Dimension preferredSize = insetSize(parent);
            if (parent.getComponentCount() > 0) {
                Dimension childSize = getComponent(0).getPreferredSize();
                preferredSize.width += childSize.width;
                preferredSize.height += childSize.height;
            }
            return preferredSize;
        }

        @Nonnull
        @Override
        public Dimension minimumLayoutSize(Container parent) {
            Dimension minimumSize = insetSize(parent);
            if (parent.getComponentCount() > 0) {
                Dimension childSize = getComponent(0).getMinimumSize();
                minimumSize.width += childSize.width;
                minimumSize.height += childSize.height;
            }
            return minimumSize;
        }

        @Override
        public void layoutContainer(Container parent) {
            if (parent.getComponentCount() > 0) {
                Insets insets = parent.getInsets();
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                getComponent(0).setBounds(insets.left, insets.top, width, height);
            }
        }

        @Nonnull
        public PositionCodeType getPositionCodeType() {
            return positionCodeType;
        }

        public void setPositionCodeType(PositionCodeType positionCodeType) {
            this.positionCodeType = positionCodeType;
        }

        @Nonnull
        private String getPositionAsString(long position) {
            if (position < 0) {
                return "-" + getNonNegativePositionAsString(-position);
            }
            return getNonNegativePositionAsString(position);
        }

        @Nonnull
        private String getNonNegativePositionAsString(long position) {
            Arrays.fill(cache, ' ');
            CodeAreaUtils.longToBaseCode(cache, 0, position, positionCodeType.getBase(), LENGTH_LIMIT, false, CodeCharactersCase.LOWER);
            return new String(cache).trim();
        }

        private long valueOfPosition(String position) {
            return Long.parseLong(position, positionCodeType.getBase());
        }
    }
}
