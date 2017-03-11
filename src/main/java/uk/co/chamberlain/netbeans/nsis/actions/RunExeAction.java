/*
 * NSIS 4 NetBeans
 * Copyright (C) 2016 Stephen Chamberlain
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package uk.co.chamberlain.netbeans.nsis.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.Action;
import javax.swing.JOptionPane;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Debug",
        id = "uk.co.chamberlain.netbeans.nsis.actions.RunExeAction"
)
@ActionRegistration(
        iconBase = "uk/co/chamberlain/netbeans/nsis/actions/exe.png",
        displayName = "#CTL_RunExeAction"
)
@ActionReference(path = "Loaders/text/x-exe/Actions", position = 150, separatorBefore = 125)
@Messages("CTL_RunExeAction=Run Executable...")
public final class RunExeAction implements Action, ActionListener {

    private final DataObject context;

    public RunExeAction(final DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {

        final String exeFilePath = context.getPrimaryFile().getPath();

        final String commandLine = exeFilePath;

        final ExecutionDescriptor descriptor
                = new ExecutionDescriptor()
                        .controllable(true)
                        .frontWindow(true)
                        .preExecution(null)
                        .postExecution(null);

        final ExecutionService exeService = ExecutionService.newService(
                new ProcessLaunch(commandLine),
                descriptor,
                "Run Executable");

        final Future<Integer> exitCode = exeService.run();

        try {
            int result = exitCode.get();
            if (result != 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Process returned code " + result, // TODO: i18n
                        "Non zero return code", // TODO: i18n
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (InterruptedException | ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    @Override
    public Object getValue(final String string) {
        return context;
    }

    @Override
    public void putValue(final String string, final Object object) {
    }

    @Override
    public void setEnabled(final boolean bool) {
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
    }

    @Override
    public void removePropertyChangeListener(final PropertyChangeListener propertyChangeListener) {
    }
}
