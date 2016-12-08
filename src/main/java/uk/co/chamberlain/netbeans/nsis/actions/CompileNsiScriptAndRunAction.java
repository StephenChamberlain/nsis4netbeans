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
import org.openide.loaders.DataObject;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Build",
        id = "uk.co.chamberlain.netbeans.nsis.actions.CompileNsiScriptAndRunAction"
)
@ActionRegistration(
        iconBase = "uk/co/chamberlain/netbeans/nsis/actions/compile-and-run24x24.png",
        displayName = "#CTL_CompileNsiScriptAndRunAction"
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
@Messages("CTL_CompileNsiScriptAndRunAction=Compile NSIS script and run installer...")
public final class CompileNsiScriptAndRunAction extends CompileNsiScriptAction {

    private final DataObject context;

    public CompileNsiScriptAndRunAction(DataObject context) {
        super(context);
        this.context = context;
    }

    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        super.actionPerformed(actionEvent);

        // The installer should now exist if everything went well...
        // TODO: How to know what the output file is called? "Outfile" could set
        // the name to anything?
    }
}
