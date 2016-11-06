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
import java.util.concurrent.Future;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
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
public final class RunExeAction implements ActionListener {

    private final DataObject context;

    public RunExeAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
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
                descriptor, "Run Executable");

        final Future<Integer> exitCode = exeService.run();
    }
}
