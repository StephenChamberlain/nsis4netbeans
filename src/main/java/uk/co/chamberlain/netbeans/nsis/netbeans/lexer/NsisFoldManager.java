/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.chamberlain.netbeans.nsis.netbeans.lexer;

import java.util.Iterator;
import java.util.logging.Logger;
import javax.swing.event.DocumentEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.editor.fold.Fold;
import org.netbeans.api.editor.fold.FoldHierarchy;
import org.netbeans.api.editor.fold.FoldType;
import org.netbeans.api.lexer.Token;
import org.netbeans.api.lexer.TokenHierarchy;
import org.netbeans.api.lexer.TokenSequence;
import org.netbeans.spi.editor.fold.FoldHierarchyTransaction;
import org.netbeans.spi.editor.fold.FoldManager;
import org.netbeans.spi.editor.fold.FoldOperation;
import org.openide.util.Exceptions;

/**
 *
 * @author Stephen Chamberlain
 */
public class NsisFoldManager implements FoldManager {

    private static final Logger LOGGER = Logger.getLogger(NsisFoldManager.class.getName());

    public static final FoldType COMMENT_FOLD_TYPE = new FoldType("/*...*/");

    private FoldOperation operation;

    @Override
    public void init(final FoldOperation operation) {
        LOGGER.info("NsisFoldManager#init");

        this.operation = operation;
    }

    @Override
    public void initFolds(FoldHierarchyTransaction transaction) {
        LOGGER.info("NsisFoldManager#initFolds");

        final FoldHierarchy hierarchy = operation.getHierarchy();
        final Document document = hierarchy.getComponent().getDocument();
        final TokenHierarchy<Document> hi = TokenHierarchy.get(document);
        final TokenSequence<NsisTokenId> ts = (TokenSequence<NsisTokenId>) hi.tokenSequence();

        Iterator<Fold> foldIterator = operation.foldIterator();
        while (foldIterator.hasNext()) {
            operation.removeFromHierarchy(foldIterator.next(), transaction);
        }

        int start = 0;
        int offset = 0;
        while (ts.moveNext()) {
            offset = ts.offset();
            final Token<NsisTokenId> token = ts.token();
            final NsisTokenId id = token.id();
            if (isComment(id)) {                
                start = offset;
                try {
                    operation.addToHierarchy(
                            COMMENT_FOLD_TYPE,
                            COMMENT_FOLD_TYPE.toString(),
                            false,
                            start,
                            offset + token.length(),
                            0,
                            0,
                            hierarchy,
                            transaction);
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    private boolean isComment(final NsisTokenId tokenId) {
        return tokenId.name().equals("FORMAL_COMMENT")
                || tokenId.name().equals("MULTI_LINE_COMMENT");
    }

    @Override
    public void insertUpdate(DocumentEvent de, FoldHierarchyTransaction fht) {
        LOGGER.info("NsisFoldManager#insertUpdate");
        initFolds(fht);
    }

    @Override
    public void removeUpdate(DocumentEvent de, FoldHierarchyTransaction fht) {
        LOGGER.info("NsisFoldManager#removeUpdate");
        initFolds(fht);
    }

    @Override
    public void changedUpdate(DocumentEvent de, FoldHierarchyTransaction fht) {
        LOGGER.info("NsisFoldManager#changedUpdate");
    }

    @Override
    public void removeEmptyNotify(Fold fold) {
        LOGGER.info("NsisFoldManager#removeEmptyNotify");
    }

    @Override
    public void removeDamagedNotify(Fold fold) {
        LOGGER.info("NsisFoldManager#removeDamagedNotify");
    }

    @Override
    public void expandNotify(Fold fold) {
        LOGGER.info("NsisFoldManager#expandNotify");
    }

    @Override
    public void release() {
        LOGGER.info("NsisFoldManager#release");
    }

}
