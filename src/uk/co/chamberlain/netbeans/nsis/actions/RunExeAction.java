/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
