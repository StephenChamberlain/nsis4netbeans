/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.chamberlain.netbeans.nsis.actions;

import java.io.File;
import java.util.concurrent.Callable;

class ProcessLaunch implements Callable<Process> {

    private final String[] commandLine;

    public ProcessLaunch(String... commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public Process call() throws Exception {
        ProcessBuilder pb = new ProcessBuilder(commandLine);
        pb.directory(new File(System.getProperty("user.home"))); //NOI18N
        pb.redirectErrorStream(true);
        return pb.start();
    }
}
