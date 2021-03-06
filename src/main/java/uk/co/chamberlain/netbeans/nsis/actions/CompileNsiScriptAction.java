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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.swing.JOptionPane;

import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.ExecutionService;
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.Exceptions;
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
    @ActionReference(path = "Menu/BuildProject", position = 297, separatorBefore = 296),
    @ActionReference(path = "Toolbars/Build", position = 250),
    @ActionReference(path = "Loaders/text/x-nsi/Actions", position = 150, separatorBefore = 125) // position = 150, separatorBefore = 125 // original = position = -100, separatorAfter = -50
    ,
    @ActionReference(path = "Editors/text/x-nsi/Popup", position = 300, separatorAfter = 350)
})
@Messages("CTL_CompileNsiScriptAction=Compile NSIS script...")
public class CompileNsiScriptAction implements ActionListener {

    private final DataObject context;

    public CompileNsiScriptAction(final DataObject context) {
        this.context = context;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {

        final String nsisHome = NsisOptionsManager.getNsisHome();
        if (!validateNsisHome(nsisHome)) {
            return;
        }

        final int nsisVerbosity = NsisOptionsManager.getNsisVerbosity();
        final String nsisFilePath = context.getPrimaryFile().getPath();

        final String commandLine = buildCommandLine(nsisHome, nsisVerbosity, nsisFilePath);

        final ExecutionService exeService = ExecutionService.newService(
                new ProcessLaunch(commandLine),
                buildExecutionDescriptor(), "NSIS Compile");

        final Future<Integer> exitCode = exeService.run();

        try {
            int result = exitCode.get();
            if (result != 0) {
                JOptionPane.showMessageDialog(
                        null,
                        "Compile returned code " + result, // TODO: i18n
                        "Non zero return code", // TODO: i18n
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (InterruptedException | ExecutionException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private boolean validateNsisHome(final String nsisHome) {
        if (!canFindNsisExecutable(nsisHome)) {
            JOptionPane.showMessageDialog(
                    null,
                    MAKENSIS_EXE_NAME + " could not be found; please specify a valid NSIS installation in the Options dialog.", // TODO: i18n
                    "NSIS not found!", // TODO: i18n
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private String buildCommandLine(String nsisHome, int nsisVerbosity, String nsisFilePath) {
        return new StringBuilder()
                .append(DOUBLE_QUOTE).append(nsisHome).append(SEPARATOR).append(MAKENSIS_EXE_NAME).append(DOUBLE_QUOTE)
                .append(SPACE)
                .append(MAKENSIS_CLI_VERBOSITY).append(nsisVerbosity)
                .append(SPACE)
                .append(DOUBLE_QUOTE).append(nsisFilePath).append(DOUBLE_QUOTE).toString();
    }

    private ExecutionDescriptor buildExecutionDescriptor() {
        return new ExecutionDescriptor()
                .controllable(true)
                .frontWindow(true)
                .preExecution(null)
                .postExecution(null);
    }

    private boolean canFindNsisExecutable(final String nsisHome) {
        final File makensis = new File(nsisHome + SEPARATOR + MAKENSIS_EXE_NAME);
        return makensis.exists();
    }
}
