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
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;
import static uk.co.chamberlain.netbeans.nsis.NsisConstants.MAKENSIS_EXE_NAME;
import uk.co.chamberlain.netbeans.nsis.options.NsisOptionsManager;

@ActionID(
        category = "Build",
        id = "uk.co.chamberlain.netbeans.nsis.actions.CompileAction"
)
@ActionRegistration(
        iconBase = "uk/co/chamberlain/netbeans/nsis/actions/nsis24x24.png",
        displayName = "#CTL_CompileAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/BuildProject", position = 125, separatorAfter = 137)
    ,
  @ActionReference(path = "Toolbars/Build", position = 500)
    ,
  @ActionReference(path = "Loaders/text/x-nsi/Actions", position = 1500, separatorBefore = 1450)
    ,
  @ActionReference(path = "Editors/text/x-nsi/Popup", position = 1300)
})
@Messages("CTL_CompileAction=Compile NSIS script...")
public final class CompileAction implements ActionListener {

    private final String SEPARATOR = System.getProperty("file.separator");
    private final String DOUBLE_QUOTE = "\"";
    private final String SPACE = " ";    
    
    private final DataObject context;

    public CompileAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        final String nsisHome = NsisOptionsManager.getNsisHome();
        final String nsisFilePath = context.getPrimaryFile().getPath();

        final String commandLine
                = DOUBLE_QUOTE + nsisHome + SEPARATOR + MAKENSIS_EXE_NAME + DOUBLE_QUOTE
                + SPACE
                + DOUBLE_QUOTE + nsisFilePath + DOUBLE_QUOTE;

        final ExecutionDescriptor descriptor
                = new ExecutionDescriptor()
                        .controllable(true)
                        .frontWindow(true)
                        .preExecution(null)
                        .postExecution(null);

        final ExecutionService exeService = ExecutionService.newService(
                new ProcessLaunch(commandLine),
                descriptor, "NSIS Compile");

        final Future<Integer> exitCode = exeService.run();
    }
}
