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
package uk.co.chamberlain.netbeans.filetypes.nsdinc;

import java.io.IOException;
import org.netbeans.core.spi.multiview.MultiViewElement;
import org.netbeans.core.spi.multiview.text.MultiViewEditorElement;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.MIMEResolver;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;
import uk.co.chamberlain.netbeans.filetypes.AssociateLookupVersion;

@Messages({
    "LBL_Nsdinc_LOADER=Files of Nsdinc"
})
@MIMEResolver.ExtensionRegistration(
        displayName = "#LBL_Nsdinc_LOADER",
        mimeType = "text/x-nsdinc",
        extension = {"nsdinc", "NSDINC"},
        position =  2147483003
)
@DataObject.Registration(
        mimeType = "text/x-nsdinc",
        iconBase = "uk/co/chamberlain/netbeans/filetypes/nsdinc/nsdinc.png",
        displayName = "#LBL_Nsdinc_LOADER",
        position = 300
)
@ActionReferences({
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.OpenAction"),
            position = 100,
            separatorAfter = 200
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CutAction"),
            position = 300
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.CopyAction"),
            position = 400,
            separatorAfter = 500
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "Edit", id = "org.openide.actions.DeleteAction"),
            position = 600
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.RenameAction"),
            position = 700,
            separatorAfter = 800
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.SaveAsTemplateAction"),
            position = 900,
            separatorAfter = 1000
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.FileSystemAction"),
            position = 1100,
            separatorAfter = 1200
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.ToolsAction"),
            position = 1300
    )
    ,
    @ActionReference(
            path = "Loaders/text/x-nsdinc/Actions",
            id = @ActionID(category = "System", id = "org.openide.actions.PropertiesAction"),
            position = 1400
    )
})
public class NsdincDataObject extends MultiDataObject {

    public NsdincDataObject(FileObject pf, MultiFileLoader loader) throws DataObjectExistsException, IOException {
        super(pf, loader);
        registerEditor("text/x-nsdinc", true);
    }

    @Override
    protected int associateLookup() {
        return AssociateLookupVersion.VERSION_1.getVersion();
    }

    @MultiViewElement.Registration(
            displayName = "#LBL_Nsdinc_EDITOR",
            iconBase = "uk/co/chamberlain/netbeans/filetypes/nsdinc/nsdinc.png",
            mimeType = "text/x-nsdinc",
            persistenceType = TopComponent.PERSISTENCE_ONLY_OPENED,
            preferredID = "Nsdinc",
            position = 1000
    )
    @Messages("LBL_Nsdinc_EDITOR=Source")
    public static MultiViewEditorElement createEditor(Lookup lkp) {
        return new MultiViewEditorElement(lkp);
    }

}
