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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.URL;

public class Downloader {
    private URL steam_home;

    public Downloader() {
        try {
            steam_home = new URL("http://63.228.223.104");
        } catch(MalformedURLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean testConnection() {
        InputStream is = null;
        try {
            is = steam_home.openStream();
            is.close();
        } catch(ConnectException ex) {
            return false;
        } catch(IOException ex) {
            return false;
        }
        return true;
    }

    public String getGameTitle(String surl) {
        String html = "";

        URL url = null;
        try {
            url = new URL(surl);
        } catch(MalformedURLException ex) {
            html = "<title>Error</title>";
            return html;
        }

        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = url.openStream();
            reader = new BufferedReader(new InputStreamReader(is));
            String line = "";

            while((line = reader.readLine()) != null) {
                /* HACK */
                if(line.contains("title")) {
                    html = line;
                    break;
                }
            }
            is.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }

        return html;
    }

    public String getSteamHomepage() {
        String html = "";
        InputStream is = null;
        BufferedReader reader = null;
        try {
            is = steam_home.openStream();
            reader = new BufferedReader(new InputStreamReader(is));
            String line = "";

            while((line = reader.readLine()) != null) {
                /* HACK */
                if(line.contains("dailydeal")) {
                    /* skip 7 lines */
                    for(int i = 0; i < 7; i++) {
                        reader.readLine();
                    }

                    /* This should be an <a href> */
                    html = reader.readLine();
                    break;
                }
            }
            is.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return html;
    }
}

