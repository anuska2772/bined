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
package org.exbin.framework.bined.makro.action;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.util.Optional;
import java.util.ResourceBundle;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.AbstractAction;
import org.exbin.framework.api.XBApplication;
import org.exbin.framework.bined.makro.gui.MakroEditorPanel;
import org.exbin.framework.utils.ActionUtils;
import org.exbin.framework.bined.makro.model.MakroRecord;
import org.exbin.framework.frame.api.FrameModuleApi;
import org.exbin.framework.utils.WindowUtils;
import org.exbin.framework.utils.gui.DefaultControlPanel;

/**
 * Edit makro record action.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class EditMakroAction extends AbstractAction {

    public static final String ACTION_ID = "editMakroAction";

    private XBApplication application;
    private ResourceBundle resourceBundle;
    private MakroRecord makroRecord;

    public EditMakroAction() {
    }

    public void setup(XBApplication application, ResourceBundle resourceBundle) {
        this.application = application;
        this.resourceBundle = resourceBundle;

        ActionUtils.setupAction(this, resourceBundle, ACTION_ID);
        putValue(ActionUtils.ACTION_DIALOG_MODE, true);
    }

    @Nonnull
    public Optional<MakroRecord> getMakroRecord() {
        return Optional.ofNullable(makroRecord);
    }

    public void setMakroRecord(@Nullable MakroRecord makroRecord) {
        this.makroRecord = makroRecord;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final MakroEditorPanel makroEditorPanel = new MakroEditorPanel();
        makroEditorPanel.setMakroRecord(makroRecord);
        ResourceBundle panelResourceBundle = makroEditorPanel.getResourceBundle();
        DefaultControlPanel controlPanel = new DefaultControlPanel(panelResourceBundle);

        FrameModuleApi frameModule = application.getModuleRepository().getModuleByInterface(FrameModuleApi.class);
        final WindowUtils.DialogWrapper dialog = frameModule.createDialog(frameModule.getFrame(), Dialog.ModalityType.APPLICATION_MODAL, makroEditorPanel, controlPanel);
        frameModule.setDialogTitle(dialog, panelResourceBundle);
        controlPanel.setHandler((actionType) -> {
            switch (actionType) {
                case OK: {
                    makroRecord.setRecord(makroEditorPanel.getMakroRecord());
                    break;
                }
                case CANCEL: {
                    makroRecord = null;
                    break;
                }
            }
            dialog.close();
        });

        dialog.showCentered(frameModule.getFrame());
    }
}
