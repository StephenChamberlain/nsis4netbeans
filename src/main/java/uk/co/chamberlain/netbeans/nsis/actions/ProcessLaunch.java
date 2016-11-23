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

import java.io.File;
import java.util.concurrent.Callable;

class ProcessLaunch implements Callable<Process> {

    private final String[] commandLine;

    public ProcessLaunch(String... commandLine) {
        this.commandLine = commandLine;
    }

    @Override
    public Process call() throws Exception {
        final ProcessBuilder processBuilder = new ProcessBuilder(commandLine);
        processBuilder.directory(new File(System.getProperty("user.home"))); //NOI18N
        processBuilder.redirectErrorStream(true);
        return processBuilder.start();
    }
}