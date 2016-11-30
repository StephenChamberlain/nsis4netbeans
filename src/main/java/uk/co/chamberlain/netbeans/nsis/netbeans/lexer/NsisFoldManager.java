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
package uk.co.chamberlain.netbeans.nsis.netbeans.lexer;

import java.util.Iterator;
import java.util.logging.Level;
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

public class NsisFoldManager implements FoldManager {

    private static final Logger LOGGER = Logger.getLogger(NsisFoldManager.class.getName());

    public static final FoldType FOLD_TYPE_COMMENT = new FoldType("/*...*/");
    public static final FoldType FOLD_TYPE_FUNCTION = new FoldType("Function...FunctionEnd");
    public static final FoldType FOLD_TYPE_SECTION = new FoldType("Section...SectionEnd");

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

        if (ts == null) {
            return;
        }

        removeFoldsFromHierarchy(transaction);

        int start = 0;
        int offset = 0;
        int startingTokenOffset = -1;
        while (ts.moveNext()) {
            offset = ts.offset();
            final Token<NsisTokenId> token = ts.token();
            final NsisTokenId id = token.id();
            final FoldType foldType;

            if (isComment(id)) {
                foldType = FOLD_TYPE_COMMENT;

            } else if (isFunctionStart(id) || isSectionStart(id)) {
                startingTokenOffset = offset;
                continue;

            } else if (isFunctionEnd(id)) {
                foldType = FOLD_TYPE_FUNCTION;

            } else if (isSectionEnd(id)) {
                foldType = FOLD_TYPE_SECTION;

            } else {
                foldType = null;
            }

            if (foldType != null) {
                start = offset;
                try {
                    LOGGER.log(Level.INFO, "NsisFoldManager#initFolds -> addToHierarchy {0}", id.name());

                    operation.addToHierarchy(
                            foldType,
                            foldType.toString(),
                            false,
                            startingTokenOffset == -1 ? start : startingTokenOffset,
                            offset + token.length(),
                            0,
                            0,
                            hierarchy,
                            transaction);

                    startingTokenOffset = -1;
                } catch (BadLocationException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }

    private void removeFoldsFromHierarchy(FoldHierarchyTransaction transaction) {
        final Iterator<Fold> foldIterator = operation.foldIterator();
        while (foldIterator.hasNext()) {
            operation.removeFromHierarchy(foldIterator.next(), transaction);
        }
    }

    private boolean isComment(final NsisTokenId tokenId) {
        return tokenId.name().equals("FORMAL_COMMENT")
                || tokenId.name().equals("MULTI_LINE_COMMENT");
    }

    private boolean isFunctionStart(final NsisTokenId tokenId) {
        return tokenId.name().equals("FUNCTION");
    }

    private boolean isFunctionEnd(final NsisTokenId tokenId) {
        return tokenId.name().equals("FUNCTIONEND");
    }

    private boolean isSectionStart(final NsisTokenId tokenId) {
        return tokenId.name().equals("SECTION");
    }

    private boolean isSectionEnd(final NsisTokenId tokenId) {
        return tokenId.name().equals("SECTIONEND");
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
