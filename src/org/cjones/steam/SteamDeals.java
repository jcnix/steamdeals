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
import android.os.Bundle;
import android.widget.TextView;

public class SteamDeals extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        String title = "";
        TextView tv = new TextView(this);
        tv.setText(title);
        setContentView(tv);        
        
        Downloader d = new Downloader();
        Parser p = new Parser();
        
        String htmllink = d.getSteamHomepage();
        String link = p.parseHyperlink(htmllink);

        String htmltitle = d.getGameTitle(link);
        title = p.parseTitle(htmltitle);

        tv.setText(title);
    }
}
