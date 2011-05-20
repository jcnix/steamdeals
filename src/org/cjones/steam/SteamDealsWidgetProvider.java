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
import android.content.ComponentName;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.widget.RemoteViews;


public class SteamDealsWidgetProvider extends AppWidgetProvider {

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews remoteViews;
        ComponentName steamWidget;

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.steamdeals_appwidget);
        steamWidget = new ComponentName(context, SteamDealsWidgetProvider.class);

        String title = "";
        Downloader d = new Downloader();
        Parser p = new Parser();
        
        if(d.testConnection()) {
            String htmllink = d.getSteamHomepage();
            String link = p.parseHyperlink(htmllink);

            String htmltitle = d.getGameTitle(link);
            title = p.parseTitle(htmltitle);
            remoteViews.setTextViewText(R.id.widget_textview, title);
        } else {
            remoteViews.setTextViewText(R.id.widget_textview, "Not connected to the Internet.  Connect and "+
                "restart the app.");
        }

        appWidgetManager.updateAppWidget( steamWidget, remoteViews );
    }
}

