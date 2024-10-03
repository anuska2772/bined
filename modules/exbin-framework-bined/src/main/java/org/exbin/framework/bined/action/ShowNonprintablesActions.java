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
package org.exbin.framework.bined.action;

import java.awt.event.ActionEvent;
import java.util.ResourceBundle;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.exbin.bined.highlight.swing.NonprintablesCodeAreaAssessor;
import org.exbin.bined.swing.CodeAreaCore;
import org.exbin.bined.swing.CodeAreaSwingUtils;
import org.exbin.bined.swing.capability.ColorAssessorPainterCapable;
import org.exbin.bined.swing.section.SectCodeArea;
import org.exbin.framework.App;
import org.exbin.framework.action.api.ActionActiveComponent;
import org.exbin.framework.action.api.ActionConsts;
import org.exbin.framework.action.api.ActionModuleApi;
import org.exbin.framework.action.api.ActionType;
import org.exbin.framework.action.api.ComponentActivationManager;
import org.exbin.framework.utils.ActionUtils;

/**
 * Show nonprintables actions.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class ShowNonprintablesActions {

    public static final String VIEW_NONPRINTABLES_ACTION_ID = "viewNonprintablesAction";
    public static final String VIEW_NONPRINTABLES_TOOLBAR_ACTION_ID = "viewNonprintablesToolbarAction";

    private ResourceBundle resourceBundle;

    public ShowNonprintablesActions() {
    }

    public void setup(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Nonnull
    public Action createViewNonprintablesAction() {
        ViewNonprintablesAction viewNonprintablesAction = new ViewNonprintablesAction();
        ActionModuleApi actionModule = App.getModule(ActionModuleApi.class);
        actionModule.initAction(viewNonprintablesAction, resourceBundle, VIEW_NONPRINTABLES_ACTION_ID);
        viewNonprintablesAction.putValue(ActionConsts.ACTION_TYPE, ActionType.CHECK);
        viewNonprintablesAction.putValue(Action.ACCELERATOR_KEY, javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, ActionUtils.getMetaMask()));
        viewNonprintablesAction.putValue(ActionConsts.ACTION_ACTIVE_COMPONENT, viewNonprintablesAction);
        return viewNonprintablesAction;
    }

    @Nonnull
    public Action createViewNonprintablesToolbarAction() {
        ViewNonprintablesAction viewNonprintablesAction = new ViewNonprintablesAction();
        ActionModuleApi actionModule = App.getModule(ActionModuleApi.class);
        actionModule.initAction(viewNonprintablesAction, resourceBundle, VIEW_NONPRINTABLES_TOOLBAR_ACTION_ID);
        viewNonprintablesAction.putValue(ActionConsts.ACTION_TYPE, ActionType.CHECK);
        viewNonprintablesAction.putValue(ActionConsts.ACTION_ACTIVE_COMPONENT, viewNonprintablesAction);
        return viewNonprintablesAction;
    }

    @ParametersAreNonnullByDefault
    public static class ViewNonprintablesAction extends AbstractAction implements ActionActiveComponent {

        private CodeAreaCore codeArea;

        @Override
        public void actionPerformed(ActionEvent e) {
            ColorAssessorPainterCapable painter = (ColorAssessorPainterCapable) ((SectCodeArea) codeArea).getPainter();
            NonprintablesCodeAreaAssessor nonprintablesCodeAreaAssessor = CodeAreaSwingUtils.findColorAssessor(painter, NonprintablesCodeAreaAssessor.class);
            if (nonprintablesCodeAreaAssessor != null) {
                boolean showNonprintables = nonprintablesCodeAreaAssessor.isShowNonprintables();
                nonprintablesCodeAreaAssessor.setShowNonprintables(!showNonprintables);
                codeArea.repaint();
            }
            // TODO App.getModule(ActionModuleApi.class).updateActionsForComponent(CodeAreaCore.class, codeArea);
        }

        @Override
        public void register(ComponentActivationManager manager) {
            manager.registerUpdateListener(CodeAreaCore.class, (instance) -> {
                codeArea = instance;
                boolean hasInstance = codeArea != null;
                if (hasInstance) {
                    ColorAssessorPainterCapable painter = (ColorAssessorPainterCapable) ((SectCodeArea) codeArea).getPainter();
                    NonprintablesCodeAreaAssessor nonprintablesCodeAreaAssessor = CodeAreaSwingUtils.findColorAssessor(painter, NonprintablesCodeAreaAssessor.class);
                    if (nonprintablesCodeAreaAssessor != null) {
                        boolean showNonprintables = nonprintablesCodeAreaAssessor.isShowNonprintables();
                        putValue(Action.SELECTED_KEY, showNonprintables);
                    }
                }
                setEnabled(hasInstance);
            });
        }
    }
}
