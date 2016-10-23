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
        iconBase = "uk/co/chamberlain/netbeans/nsis/actions/nsis16x16.png",
        displayName = "#CTL_CompileAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/BuildProject", position = 150, separatorAfter = 175)
    ,
  @ActionReference(path = "Toolbars/Build", position = -20)
    ,
  @ActionReference(path = "Loaders/text/x-nsi/Actions", position = 0)
    ,
  @ActionReference(path = "Editors/text/x-nsi/Popup", position = 400)
})
@Messages("CTL_CompileAction=Compile Installer")
public final class CompileAction implements ActionListener {

    private final DataObject context;
    private final String SEPARATOR = System.getProperty("file.separator");
    private final String DOUBLE_QUOTE = "\"";
    private final String SPACE = " ";

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
