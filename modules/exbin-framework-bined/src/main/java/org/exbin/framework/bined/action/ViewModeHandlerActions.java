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
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.exbin.bined.basic.CodeAreaViewMode;
import org.exbin.bined.capability.ViewModeCapable;
import org.exbin.bined.swing.CodeAreaCore;
import org.exbin.framework.App;
import org.exbin.framework.action.api.ActionActiveComponent;
import org.exbin.framework.action.api.ActionConsts;
import org.exbin.framework.action.api.ActionModuleApi;
import org.exbin.framework.action.api.ActionType;

/**
 * View mode actions.
 *
 * @author ExBin Project (https://exbin.org)
 */
@ParametersAreNonnullByDefault
public class ViewModeHandlerActions {

    public static final String DUAL_VIEW_MODE_ACTION_ID = "dualViewModeAction";
    public static final String CODE_MATRIX_VIEW_MODE_ACTION_ID = "codeMatrixViewModeAction";
    public static final String TEXT_PREVIEW_VIEW_MODE_ACTION_ID = "textPreviewViewModeAction";

    public static final String VIEW_MODE_RADIO_GROUP_ID = "viewModeRadioGroup";

    private ResourceBundle resourceBundle;

    public ViewModeHandlerActions() {
    }

    public void setup(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Nonnull
    public Action createDualModeAction() {
        DualModeAction dualModeAction = new DualModeAction();
        ActionModuleApi actionModule = App.getModule(ActionModuleApi.class);
        actionModule.setupAction(dualModeAction, resourceBundle, DUAL_VIEW_MODE_ACTION_ID);
        dualModeAction.putValue(ActionConsts.ACTION_TYPE, ActionType.RADIO);
        dualModeAction.putValue(ActionConsts.ACTION_RADIO_GROUP, VIEW_MODE_RADIO_GROUP_ID);
        dualModeAction.putValue(ActionConsts.ACTION_ACTIVE_COMPONENT, dualModeAction);
        return dualModeAction;
    }

    @Nonnull
    public Action getCodeMatrixModeAction() {

        CodeMatrixModeAction codeMatrixModeAction = new CodeMatrixModeAction();
        ActionModuleApi actionModule = App.getModule(ActionModuleApi.class);
        actionModule.setupAction(codeMatrixModeAction, resourceBundle, CODE_MATRIX_VIEW_MODE_ACTION_ID);
        codeMatrixModeAction.putValue(ActionConsts.ACTION_TYPE, ActionType.RADIO);
        codeMatrixModeAction.putValue(ActionConsts.ACTION_RADIO_GROUP, VIEW_MODE_RADIO_GROUP_ID);
        codeMatrixModeAction.putValue(ActionConsts.ACTION_ACTIVE_COMPONENT, codeMatrixModeAction);
        return codeMatrixModeAction;
    }

    @Nonnull
    public Action getTextPreviewModeAction() {
        TextPreviewModeAction textPreviewModeAction = new TextPreviewModeAction();
        ActionModuleApi actionModule = App.getModule(ActionModuleApi.class);
        actionModule.setupAction(textPreviewModeAction, resourceBundle, TEXT_PREVIEW_VIEW_MODE_ACTION_ID);
        textPreviewModeAction.putValue(ActionConsts.ACTION_RADIO_GROUP, VIEW_MODE_RADIO_GROUP_ID);
        textPreviewModeAction.putValue(ActionConsts.ACTION_TYPE, ActionType.RADIO);
        textPreviewModeAction.putValue(ActionConsts.ACTION_ACTIVE_COMPONENT, textPreviewModeAction);
        return textPreviewModeAction;
    }

    @ParametersAreNonnullByDefault
    private static class DualModeAction extends AbstractAction implements ActionActiveComponent {

        private CodeAreaCore codeArea;

        @Override
        public void actionPerformed(ActionEvent e) {
            ((ViewModeCapable) codeArea).setViewMode(CodeAreaViewMode.DUAL);
            App.getModule(ActionModuleApi.class).updateActionsForComponent(codeArea);
        }

        @Nonnull
        @Override
        public Set<Class<?>> forClasses() {
            return Collections.singleton(CodeAreaCore.class);
        }

        @Override
        public void componentActive(Set<Object> affectedClasses) {
            boolean hasInstance = !affectedClasses.isEmpty();
            codeArea = hasInstance ? (CodeAreaCore) affectedClasses.iterator().next() : null;
            if (hasInstance) {
                CodeAreaViewMode viewMode = ((ViewModeCapable) codeArea).getViewMode();
                putValue(Action.SELECTED_KEY, viewMode == CodeAreaViewMode.DUAL);
            }
            setEnabled(hasInstance);
        }
    }

    @ParametersAreNonnullByDefault
    private static class CodeMatrixModeAction extends AbstractAction implements ActionActiveComponent {

        private CodeAreaCore codeArea;

        @Override
        public void actionPerformed(ActionEvent e) {
            ((ViewModeCapable) codeArea).setViewMode(CodeAreaViewMode.CODE_MATRIX);
            App.getModule(ActionModuleApi.class).updateActionsForComponent(codeArea);
        }

        @Nonnull
        @Override
        public Set<Class<?>> forClasses() {
            return Collections.singleton(CodeAreaCore.class);
        }

        @Override
        public void componentActive(Set<Object> affectedClasses) {
            boolean hasInstance = !affectedClasses.isEmpty();
            codeArea = hasInstance ? (CodeAreaCore) affectedClasses.iterator().next() : null;
            if (hasInstance) {
                CodeAreaViewMode viewMode = ((ViewModeCapable) codeArea).getViewMode();
                putValue(Action.SELECTED_KEY, viewMode == CodeAreaViewMode.CODE_MATRIX);
            }
            setEnabled(hasInstance);
        }
    }

    @ParametersAreNonnullByDefault
    private static class TextPreviewModeAction extends AbstractAction implements ActionActiveComponent {

        private CodeAreaCore codeArea;

        @Override
        public void actionPerformed(ActionEvent e) {
            ((ViewModeCapable) codeArea).setViewMode(CodeAreaViewMode.TEXT_PREVIEW);
            App.getModule(ActionModuleApi.class).updateActionsForComponent(codeArea);
        }

        @Nonnull
        @Override
        public Set<Class<?>> forClasses() {
            return Collections.singleton(CodeAreaCore.class);
        }

        @Override
        public void componentActive(Set<Object> affectedClasses) {
            boolean hasInstance = !affectedClasses.isEmpty();
            codeArea = hasInstance ? (CodeAreaCore) affectedClasses.iterator().next() : null;
            if (hasInstance) {
                CodeAreaViewMode viewMode = ((ViewModeCapable) codeArea).getViewMode();
                putValue(Action.SELECTED_KEY, viewMode == CodeAreaViewMode.TEXT_PREVIEW);
            }
            setEnabled(hasInstance);
        }
    }
}
