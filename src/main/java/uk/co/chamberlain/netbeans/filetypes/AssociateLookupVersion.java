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
package uk.co.chamberlain.netbeans.filetypes;

/**
 * @see MultiDataObject#associateLookup
 */
public enum AssociateLookupVersion {

    /**
     * Delegates to {@code getNodeDelegate().getLookup()}.
     */
    VERSION_0(0),
    /**
     * Delegates to {@code getCookieSet().getLookup()} and makes sure {@link FileObject}, {@code this} and {@link Node}
     * are in the lookup. The {@link Node} is created lazily by calling {@link #getNodeDelegate()}.
     */
    VERSION_1(1);

    private final int version;

    private AssociateLookupVersion(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

}
