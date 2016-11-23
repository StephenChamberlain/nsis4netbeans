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

import java.lang.reflect.Field;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.spi.lexer.LanguageHierarchy;
import org.netbeans.spi.lexer.Lexer;
import org.netbeans.spi.lexer.LexerRestartInfo;
import org.openide.util.Exceptions;
import uk.co.chamberlain.netbeans.nsis.javacc.lexer.NSISParserConstants;

public class NsisLanguageHierarchy extends LanguageHierarchy<NsisTokenId> {

    private static final Logger LOGGER = Logger.getLogger(NsisLanguageHierarchy.class.getName());

    private static final NSISParserConstants PARSER_CONSTANTS = new ParserConstants();

    private static final String COMMAND = "command";
    private static final String KEYWORD = "keyword";
    private static final String LITERAL = "literal";
    private static final String COMMENT = "comment";
    private static final String WHITESPACE = "whitespace";
    private static final String NUMBER = "number";
    private static final String IDENTIFIER = "identifier";
    private static final String METHOD = "method";
    private static final String OPERATOR = "";

    private static List<String> commands;
    private static List<String> whitespace;
    private static List<String> literals;
    private static List<String> comments;
    private static List<String> operators;
    private static List<String> numbers;
    private static List<String> identifiers;
    private static List<String> methods;
    private static List<NsisTokenId> tokens;
    private static Map<Integer, NsisTokenId> idToToken;
    private static NsisTokenId tokenForErrorSituation;

    private static void init() {

        commands = Arrays.asList(
                "ABORT",
                "ADDBRANDINGIMAGE",
                "ADDSIZE",
                "AUTOCLOSEWINDOW",
                "BGFONT",
                "BGGRADIENT",
                "BRANDINGTEXT",
                "BRINGTOFRONT",
                "CALL",
                "CALLINSTDLL",
                "CAPTION",
                "CHANGEUI",
                "CLEARERRORS",
                "COMPONENTTEXT",
                "GETDLLVERSION",
                "GETDLLVERSIONLOCAL",
                "GETFILETIME",
                "GETFILETIMELOCAL",
                "COPYFILES",
                "CRCCHECK",
                "CREATEDIRECTORY",
                "CREATEFONT",
                "CREATESHORTCUT",
                "SETDATABLOCKOPTIMIZE",
                "DELETEINISEC",
                "DELETEINISTR",
                "DELETEREGKEY",
                "DELETEREGVALUE",
                "DELETE",
                "DETAILPRINT",
                "DIRTEXT",
                "DIRSHOW",
                "DIRVAR",
                "DIRVERIFY",
                "GETINSTDIRERROR",
                "ALLOWROOTDIRINSTALL",
                "CHECKBITMAP",
                "ENABLEWINDOW",
                "ENUMREGKEY",
                "ENUMREGVALUE",
                "EXCH",
                "EXEC",
                "EXECWAIT",
                "EXECSHELL",
                "EXPANDENVSTRINGS",
                "FINDWINDOW",
                "FINDCLOSE",
                "FINDFIRST",
                "FINDNEXT",
                "FILE",
                "FILEBUFSIZE",
                "FLUSHINI",
                "RESERVEFILE",
                "FILECLOSE",
                "FILEERRORTEXT",
                "FILEOPEN",
                "FILEREAD",
                "FILEWRITE",
                "FILEREADBYTE",
                "FILEWRITEBYTE",
                "FILEREADUTF16LE",
                "FILEWRITEUTF16LE",
                "FILEREADWORD",
                "FILEWRITEWORD",
                "FILESEEK",
                "FUNCTION",
                "FUNCTIONEND",
                "GETDLGITEM",
                "GETFULLPATHNAME",
                "GETTEMPFILENAME",
                "HIDEWINDOW",
                "ICON",
                "IFABORT",
                "IFERRORS",
                "IFFILEEXISTS",
                "IFREBOOTFLAG",
                "IFSILENT",
                "INSTALLDIRREGKEY",
                "INSTALLCOLORS",
                "INSTALLDIR",
                "INSTPROGRESSFLAGS",
                "INSTTYPE",
                "INTOP",
                "INTCMP",
                "INTCMPU",
                "INTFMT",
                "ISWINDOW",
                "GOTO",
                "LANGSTRING",
                "LANGSTRINGUP",
                "LICENSEDATA",
                "LICENSEFORCESELECTION",
                "LICENSELANGSTRING",
                "LICENSETEXT",
                "LICENSEBKCOLOR",
                "LOADLANGUAGEFILE",
                "LOGSET",
                "LOGTEXT",
                "MESSAGEBOX",
                "NOP",
                "NAME",
                "OUTFILE",
                "PAGE",
                "PAGECALLBACKS",
                "PAGEEX",
                "PAGEEXEND",
                "POP",
                "PUSH",
                "QUIT",
                "READINISTR",
                "READREGDWORD",
                "READREGSTR",
                "READENVSTR",
                "REBOOT",
                "REGDLL",
                "RENAME",
                "RETURN",
                "RMDIR",
                "SECTION",
                "SECTIONEND",
                "SECTIONIN",
                "SUBSECTION",
                "SECTIONGROUP",
                "SUBSECTIONEND",
                "SECTIONGROUPEND",
                "SEARCHPATH",
                "SECTIONSETFLAGS",
                "SECTIONGETFLAGS",
                "SECTIONSETINSTTYPES",
                "SECTIONGETINSTTYPES",
                "SECTIONGETTEXT",
                "SECTIONSETTEXT",
                "SECTIONGETSIZE",
                "SECTIONSETSIZE",
                "GETCURINSTTYPE",
                "SETCURINSTTYPE",
                "INSTTYPESETTEXT",
                "INSTTYPEGETTEXT",
                "SENDMESSAGE",
                "SETAUTOCLOSE",
                "SETCTLCOLORS",
                "SETBRANDINGIMAGE",
                "SETCOMPRESS",
                "SETCOMPRESSOR",
                "SETCOMPRESSORDICTSIZE",
                "SETCOMPRESSIONLEVEL",
                "SETDATESAVE",
                "SETDETAILSVIEW",
                "SETDETAILSPRINT",
                "SETERRORS",
                "SETERRORLEVEL",
                "GETERRORLEVEL",
                "SETFILEATTRIBUTES",
                "SETFONT",
                "SETOUTPATH",
                "SETOVERWRITE",
                "SETPLUGINUNLOAD",
                "SETREBOOTFLAG",
                "SETREGVIEW",
                "SETSHELLVARCONTEXT",
                "SETSILENT",
                "SHOWINSTDETAILS",
                "SHOWUNINSTDETAILS",
                "SHOWWINDOW",
                "SILENTINSTALL",
                "SILENTUNINSTALL",
                "SLEEP",
                "STRCMP",
                "STRCMPS",
                "STRCPY",
                "UNSAFESTRCPY",
                "STRLEN",
                "SUBCAPTION",
                "TARGET",
                "CPU",
                "UNICODE",
                "UNINSTALLEXENAME",
                "UNINSTALLCAPTION",
                "UNINSTALLICON",
                "UNINSTPAGE",
                "UNINSTALLTEXT",
                "UNINSTALLSUBCAPTION",
                "UNREGDLL",
                "WINDOWICON",
                "WRITEINISTR",
                "WRITEREGBIN",
                "WRITEREGDWORD",
                "WRITEREGSTR",
                "WRITEREGEXPANDSTR",
                "WRITEUNINSTALLER",
                "PEDLLCHARACTERISTICS",
                "PESUBSYSVER",
                "XPSTYLE",
                "REQUESTEXECUTIONLEVEL",
                "MANIFESTDPIAWARE",
                "MANIFESTSUPPORTEDOS",
                "PACKHDR",
                "FINALIZE",
                "SYSTEM",
                "EXECUTE",
                "ADDINCLUDEDIR",
                "INCLUDE",
                "CD",
                "IF",
                "IFDEF",
                "IFNDEF",
                "ENDIF",
                "DEFINE",
                "UNDEF",
                "ELSE",
                "ECHO",
                "WARNING",
                "ERROR",
                "VERBOSE",
                "MACRO",
                "MACROEND",
                "MACROUNDEF",
                "INSERTMACRO",
                "IFMACRODEF",
                "IFMACRONDEF",
                "TEMPFILE",
                "DELFILE",
                "APPENDFILE",
                "GETDLLVERSION",
                "SEARCHPARSE",
                "SEARCHREPLACE",
                "MISCBUTTONTEXT",
                "DETAILSBUTTONTEXT",
                "UNINSTALLBUTTONTEXT",
                "INSTALLBUTTONTEXT",
                "SPACETEXTS",
                "COMPLETEDTEXT",
                "GETFUNCTIONADDRESS",
                "GETLABELADDRESS",
                "GETCURRENTADDRESS",
                "ADDPLUGINDIR",
                "INITPLUGINSDIR",
                "ALLOWSKIPFILES",
                "VAR",
                "VIADDVERSIONKEY",
                "VIPRODUCTVERSION",
                "VIFILEVERSION",
                "LOCKWINDOW"
        );

        whitespace = Arrays.asList(
                "EOF",
                "WHITESPACE"
        );

        literals = Arrays.asList(
                "INTEGER_LITERAL",
                "DECIMAL_LITERAL",
                "HEX_LITERAL",
                "OCTAL_LITERAL",
                "FLOATING_POINT_LITERAL",
                "DECIMAL_FLOATING_POINT_LITERAL",
                "DECIMAL_EXPONENT",
                "HEXADECIMAL_FLOATING_POINT_LITERAL",
                "CHARACTER_LITERAL",
                "STRING_LITERAL",
                "LETTER",
                "PART_LETTER"
        );

        comments = Arrays.asList(
                "SINGLE_LINE_COMMENT",
                "FORMAL_COMMENT",
                "MULTI_LINE_COMMENT"
        );

        operators = Arrays.asList(
                "LPAREN",
                "RPAREN",
                "LBRACE",
                "RBRACE",
                "LBRACKET",
                "RBRACKET",
                "SEMICOLON",
                "COMMA",
                "DOT",
                "AT",
                "ASSIGN",
                "LT",
                "BANG",
                "TILDE",
                "HOOK",
                "COLON",
                "EQ",
                "LE",
                "GE",
                "NE",
                "SC_OR",
                "SC_AND",
                "INCR",
                "DECR",
                "PLUS",
                "MINUS",
                "STAR",
                "SLASH",
                "BIT_AND",
                "BIT_OR",
                "XOR",
                "REM",
                "LSHIFT",
                "PLUSASSIGN",
                "MINUSASSIGN",
                "STARASSIGN",
                "SLASHASSIGN",
                "ANDASSIGN",
                "ORASSIGN",
                "XORASSIGN",
                "REMASSIGN",
                "LSHIFTASSIGN",
                "RSIGNEDSHIFTASSIGN",
                "RUNSIGNEDSHIFTASSIGN",
                "ELLIPSIS",
                "RUNSIGNEDSHIFT",
                "RSIGNEDSHIFT",
                "GT"
        );

        numbers = Arrays.asList(
                "DECIMAL_EXPONENT",
                "HEXADECIMAL_EXPONENT"
        );

        identifiers = Arrays.asList(
                "IDENTIFIER"
        );

        methods = Arrays.asList(
                "FUNCTION",
                "FUNCTIONEND",
                "MULTI_LINE_FUNCTION",
                "MULTI_LINE_SECTION"
        );

        tokens = new ArrayList<>();

        for (final Field field : NSISParserConstants.class
                .getFields()) {
            try {
                if (field.getType() == Integer.TYPE) {
                    if (commands.contains(field.getName())) {
                        addTokenId(field, COMMAND);
                        
                    }else if (whitespace.contains(field.getName())) {
                        addTokenId(field, WHITESPACE);

                    } else if (literals.contains(field.getName())) {
                        addTokenId(field, LITERAL);

                    } else if (comments.contains(field.getName())) {
                        addTokenId(field, COMMENT);

                    } else if (operators.contains(field.getName())) {
                        addTokenId(field, OPERATOR);

                    } else if (numbers.contains(field.getName())) {
                        addTokenId(field, NUMBER);

//                    } else if (identifiers.contains(field.getName())) {
//                        addTokenId(field, IDENTIFIER);                        
//
                    } else if (methods.contains(field.getName())) {
                        addTokenId(field, METHOD);

                    } else {
                        addTokenId(field, KEYWORD);
                    }
                }
            } catch (IllegalArgumentException | IllegalAccessException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        idToToken = new HashMap<>();
        for (NsisTokenId token : tokens) {
            idToToken.put(token.ordinal(), token);
        }
    }

    private static void addTokenId(final Field field, final String type)
            throws IllegalArgumentException, IllegalAccessException {

        NsisTokenId tokenId = new NsisTokenId(field.getName(), type, field.getInt(PARSER_CONSTANTS));
        for (NsisTokenId existingToken : tokens) {
            if (existingToken.ordinal() == tokenId.ordinal()) {
                LOGGER.log(
                        Level.WARNING,
                        "Duplicate ordinal; existing {0} duplicate {1}",
                        new Object[]{existingToken.name(), tokenId.name()});
                return;
            }
        }
        tokens.add(tokenId);

        if ("WHITESPACE".equals(field.getName())) {
            tokenForErrorSituation = tokenId;
        }
    }

    static synchronized NsisTokenId getToken(int id) {
        if (idToToken == null) {
            init();
        }
        return idToToken.get(id);
    }

    static synchronized NsisTokenId getTokenForErrorSituation() {
        return tokenForErrorSituation;
    }

    @Override
    protected synchronized Collection<NsisTokenId> createTokenIds() {
        if (tokens == null) {
            init();
        }
        return tokens;
    }

    @Override
    protected synchronized Lexer<NsisTokenId> createLexer(LexerRestartInfo<NsisTokenId> info) {
        return new NsisLexer(info);
    }

    @Override
    protected String mimeType() {
        return "text/x-nsi";

    }

    private static class ParserConstants implements NSISParserConstants {
        // Object handle to use in reflection
    }

}
