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

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class SteamDeals extends Activity
{
    private TextView tv;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        tv = new TextView(this);
        tv.setText("");
        setContentView(tv);
        run();
    }

    public void run() {
        String title = "";
        Downloader d = new Downloader();
        Parser p = new Parser();
        
        if(d.testConnection()) {
            String htmllink = d.getSteamHomepage();
            String link = p.parseHyperlink(htmllink);

            String htmltitle = d.getGameTitle(link);
            title = p.parseTitle(htmltitle);
            tv.setText(title);
        } else {
            tv.setText("Not connected to the Internet.  Connect and "+
                "restart the app.");
        }

    }
    
    public boolean isOnline() {
        boolean wifi = false;
        boolean mobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] net = cm.getAllNetworkInfo();
        for(NetworkInfo ni : net) {
            if(ni.getTypeName().equalsIgnoreCase("WIFI")) {
                if(ni.isConnected())
                    wifi = true;
            }
            if(ni.getTypeName().equalsIgnoreCase("MOBILE")) {
                if(ni.isConnected())
                    mobile = true;
            }
        }

        return wifi || mobile;
    }
}
