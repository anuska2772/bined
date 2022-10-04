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

import java.awt.Component;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.exbin.bined.swing.extended.layout.DefaultExtendedCodeAreaLayoutProfile;
import org.exbin.framework.bined.options.impl.CodeAreaLayoutOptionsImpl;
import org.exbin.framework.bined.preferences.CodeAreaLayoutPreferences;
import org.exbin.framework.utils.LanguageUtils;
import org.exbin.framework.utils.WindowUtils;
import org.exbin.framework.preferences.PreferencesWrapper;
import org.exbin.framework.preferences.StreamPreferences;

/**
 * Manage list of layout profiles panel.
 *
 * @version 0.2.1 2021/08/23
 * @author ExBin Project (http://exbin.org)
 */
@ParametersAreNonnullByDefault
public class LayoutTemplatePanel extends javax.swing.JPanel implements ProfileListPanel {

    private final java.util.ResourceBundle resourceBundle = LanguageUtils.getResourceBundleByClass(LayoutTemplatePanel.class);

    public LayoutTemplatePanel() {
        initComponents();
        init();
    }

    private void init() {
        templatesList.setModel(new ProfilesListModel());
        templatesList.setCellRenderer(new ProfileCellRenderer());
        templatesList.addListSelectionListener((ListSelectionEvent e) -> updateStates());
        loadFromOptions();
    }

    @Nonnull
    public ResourceBundle getResourceBundle() {
        return resourceBundle;
    }

    private void updateStates() {
        LayoutProfile layoutProfile = getSelectedTemplate();
        if (layoutProfile != null) {
            previewPanel.getCodeArea().setLayoutProfile(layoutProfile.getLayoutProfile());
        }
    }

    @Nullable
    public LayoutProfile getSelectedTemplate() {
        int selectedIndex = templatesList.getSelectedIndex();
        return selectedIndex < 0 ? null : getProfilesListModel().getElementAt(selectedIndex);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        templatesList.addListSelectionListener(listener);
    }

    @Nonnull
    @Override
    public List<String> getProfileNames() {
        List<String> profileNames = new ArrayList<>();
        getProfilesListModel().profiles.forEach((profile) -> profileNames.add(profile.profileName));
        return profileNames;
    }

    @Override
    public void addProfileListPanelListener(ListDataListener listener) {
        getProfilesListModel().addListDataListener(listener);
    }

    @Nonnull
    private ProfilesListModel getProfilesListModel() {
        return ((ProfilesListModel) templatesList.getModel());
    }

    public DefaultExtendedCodeAreaLayoutProfile getProfile(int profileIndex) {
        return getProfilesListModel().getElementAt(profileIndex).layoutProfile;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        profilesListScrollPane = new javax.swing.JScrollPane();
        templatesList = new javax.swing.JList<>();
        previewPanel = new org.exbin.framework.bined.options.gui.PreviewPanel();

        profilesListScrollPane.setViewportView(templatesList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(profilesListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(previewPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
                    .addComponent(profilesListScrollPane))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void loadFromOptions() {
        CodeAreaLayoutOptionsImpl options = new CodeAreaLayoutOptionsImpl();
        try (InputStream stream = getClass().getResourceAsStream("/org/exbin/framework/bined/resources/templates/layoutTemplates.xml")) {
            java.util.prefs.Preferences filePreferences = new StreamPreferences(stream);

            options.loadFromPreferences(new CodeAreaLayoutPreferences(new PreferencesWrapper(filePreferences)));

            List<LayoutProfile> profiles = new ArrayList<>();
            List<String> profileNames = options.getProfileNames();
            for (int index = 0; index < profileNames.size(); index++) {
                LayoutProfile profile = new LayoutProfile(
                        profileNames.get(index),
                        options.getLayoutProfile(index)
                );
                profiles.add(profile);
            }

            ProfilesListModel model = getProfilesListModel();
            model.setProfiles(profiles);
        } catch (IOException ex) {
            Logger.getLogger(LayoutTemplatePanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test method for this panel.
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        WindowUtils.invokeDialog(new LayoutTemplatePanel());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.exbin.framework.bined.options.gui.PreviewPanel previewPanel;
    private javax.swing.JScrollPane profilesListScrollPane;
    private javax.swing.JList<LayoutProfile> templatesList;
    // End of variables declaration//GEN-END:variables

    @ParametersAreNonnullByDefault
    public final static class LayoutProfile {

        private String profileName;
        private DefaultExtendedCodeAreaLayoutProfile layoutProfile;

        public LayoutProfile(String profileName, DefaultExtendedCodeAreaLayoutProfile layoutProfile) {
            this.profileName = profileName;
            this.layoutProfile = layoutProfile;
        }

        @Nonnull
        public String getProfileName() {
            return profileName;
        }

        @Nonnull
        public DefaultExtendedCodeAreaLayoutProfile getLayoutProfile() {
            return layoutProfile;
        }
    }

    @ParametersAreNonnullByDefault
    private static final class ProfilesListModel extends AbstractListModel<LayoutProfile> {

        private final List<LayoutProfile> profiles = new ArrayList<>();

        public ProfilesListModel() {
        }

        @Override
        public int getSize() {
            if (profiles == null) {
                return 0;
            }
            return profiles.size();
        }

        public boolean isEmpty() {
            return profiles == null || profiles.isEmpty();
        }

        @Nullable
        @Override
        public LayoutProfile getElementAt(int index) {
            return profiles.get(index);
        }

        @Nonnull
        public List<LayoutProfile> getProfiles() {
            return profiles;
        }

        public void setProfiles(List<LayoutProfile> profiles) {
            int size = this.profiles.size();
            if (size > 0) {
                this.profiles.clear();
                fireIntervalRemoved(this, 0, size - 1);
            }
            int profilesSize = profiles.size();
            if (profilesSize > 0) {
                this.profiles.addAll(profiles);
                fireIntervalAdded(this, 0, profilesSize - 1);
            }
        }

        public void addAll(List<LayoutProfile> list, int index) {
            if (index >= 0) {
                profiles.addAll(index, list);
                fireIntervalAdded(this, index, list.size() + index);
            } else {
                profiles.addAll(list);
                fireIntervalAdded(this, profiles.size() - list.size(), profiles.size());
            }
        }

        public void removeIndices(int[] indices) {
            if (indices.length == 0) {
                return;
            }
            Arrays.sort(indices);
            for (int i = indices.length - 1; i >= 0; i--) {
                profiles.remove(indices[i]);
                fireIntervalRemoved(this, indices[i], indices[i]);
            }
        }

        public void remove(int index) {
            profiles.remove(index);
            fireIntervalRemoved(this, index, index);
        }

        public void add(int index, LayoutProfile item) {
            profiles.add(index, item);
            fireIntervalAdded(this, index, index);
        }

        public void add(LayoutProfile item) {
            profiles.add(item);
            int index = profiles.size() - 1;
            fireIntervalAdded(this, index, index);
        }

        public void notifyProfileModified(int index) {
            fireContentsChanged(this, index, index);
        }
    }

    @ParametersAreNonnullByDefault
    private static final class ProfileCellRenderer implements ListCellRenderer<LayoutProfile> {

        private final DefaultListCellRenderer defaultListCellRenderer = new DefaultListCellRenderer();

        @Override
        public Component getListCellRendererComponent(JList<? extends LayoutProfile> list, LayoutProfile value, int index, boolean isSelected, boolean cellHasFocus) {
            return defaultListCellRenderer.getListCellRendererComponent(list, value.profileName, index, isSelected, cellHasFocus);
        }
    }
}
