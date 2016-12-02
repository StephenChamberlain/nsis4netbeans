## Synopsis

Provides support for NSIS (Nullsoft Scriptable Install System) in NetBeans IDE.
Features include file type recognition for *.nsi script files and *.nsh header files, 
file type recognition for *.nsddef and *.nsdinc NSIS Dialog Designer files and
syntax highlighting.

## Installation

Install the plugin via the NetBeans plugin portal from inside your IDE. Search
for "NSIS" and you should find it...

## License

Common Development and Distribution License

---

## Specification

- Syntax Highlighting:
    - Commands
    - Functions
    - Sections
    - Plugins
    - etc...
- Configuration Options:
    - NSIS location on the target system, for the NSIS compile action.
    - Verbosity level of NSIS output. Defaults to "Info, warnings and errors".
- Compile script with NSIS (must be configured in options) in toolbar, project navigator and code editor.
- Open .nsddef files in NSIS Dialog Designer <http://coolsoft.altervista.org/en/nsisdialogdesigner> if installed.
- Double click *.exe files to execute.
