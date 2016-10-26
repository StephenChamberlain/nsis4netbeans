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
import java.io.File;
import java.util.concurrent.Future;
import javax.swing.JOptionPane;

import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

import uk.co.chamberlain.netbeans.nsis.options.NsisOptionsManager;

import static uk.co.chamberlain.netbeans.nsis.NsisConstants.*;

@ActionID(
        category = "Build",
        id = "uk.co.chamberlain.netbeans.nsis.actions.CompileNsiScriptAction"
)
@ActionRegistration(
        iconBase = "uk/co/chamberlain/netbeans/nsis/actions/nsis24x24.png",
        displayName = "#CTL_CompileNsiScriptAction"
)
@ActionReferences({
    @ActionReference(path = "Menu/BuildProject", position = 297, separatorBefore = 296)
    ,
  @ActionReference(path = "Toolbars/Build", position = 250)
    ,
  @ActionReference(path = "Loaders/text/x-nsi/Actions", position = 150, separatorBefore = 125) // position = 150, separatorBefore = 125 // original = position = -100, separatorAfter = -50
    ,
  @ActionReference(path = "Editors/text/x-nsi/Popup", position = 300, separatorAfter = 350)
})
@Messages("CTL_CompileNsiScriptAction=Compile NSIS script...")
public final class CompileNsiScriptAction implements ActionListener {

    private final String SEPARATOR = System.getProperty("file.separator");
    private final String DOUBLE_QUOTE = "\"";
    private final String SPACE = " ";

    private final DataObject context;

    public CompileNsiScriptAction(DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(ActionEvent ev) {
        final String nsisHome = NsisOptionsManager.getNsisHome();
        if (!canFindNsisExecutable(nsisHome)) {
            JOptionPane.showMessageDialog(
                    null,
                    MAKENSIS_EXE_NAME + " could not be found; please specify a valid NSIS installation in the Options dialog.",
                    "NSIS not found!",
                    JOptionPane.ERROR_MESSAGE);
        }

        final int nsisVerbosity = NsisOptionsManager.getNsisVerbosity();
        final String nsisFilePath = context.getPrimaryFile().getPath();

        final String commandLine
                = DOUBLE_QUOTE + nsisHome + SEPARATOR + MAKENSIS_EXE_NAME + DOUBLE_QUOTE
                + SPACE
                + MAKENSIS_CLI_VERBOSITY + nsisVerbosity
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

    private boolean canFindNsisExecutable(final String nsisHome) {
        final File makensis = new File(nsisHome + SEPARATOR + MAKENSIS_EXE_NAME);
        return makensis.exists();
    }
}
