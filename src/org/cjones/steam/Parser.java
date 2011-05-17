/**
 * This file is part of Foobar.
 * @author Casey Jones
 * 
 * Foobar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Foobar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.cjones.steam;

public class Parser {

    public Parser() {
    }

    public String parseHyperlink(String html) {
        String[] arr = html.split("\"");
        return arr[1];
    }

    public String parseTitle(String html) {
        String[] arr = html.split("<*title>");
        String s = arr[1];
        s = s.substring(0, s.length() - 2);
        return s;
    }
}

