package uk.co.chamberlain.netbeans.nsis.netbeans.lexer;

import java.util.logging.Logger;
import org.netbeans.api.editor.mimelookup.MimeRegistration;
import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldManagerFactory;

@MimeRegistration(mimeType = "text/x-nsi", service = FoldManagerFactory.class, position = Integer.MAX_VALUE)
public class NsisFoldManagerFactory implements FoldManagerFactory {

    private static final Logger LOGGER = Logger.getLogger(NsisFoldManagerFactory.class.getName());

    @Override
    public FoldManager createFoldManager() {
        LOGGER.info("Instantiating NsisFoldManager");
        return new NsisFoldManager();
    }

}
