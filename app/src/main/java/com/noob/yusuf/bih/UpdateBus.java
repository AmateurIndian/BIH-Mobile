package com.noob.yusuf.bih;

import android.content.Context;
import android.util.Log;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * Created by yusuf on 15.08.2016.
 */
public class UpdateBus {

    public UpdateBus (String file, Context ctx, int type){
        String fromCityWeek, toCityWeek, fromCityEnd, toCityEnd;

        if (type == 0){
            fromCityWeek = "fromMainWeek";
            fromCityEnd = "fromMainEnd";
            toCityEnd = "toMainEnd";
            toCityWeek = "toMainWeek";
            Log.d("Campus", "main");
        }
        else{
            fromCityWeek = "fromEastWeek";
            fromCityEnd = "fromEastEnd";
            toCityEnd = "toEastEnd";
            toCityWeek = "toEastWeek";
            Log.d("Campus", "East");
        }

        FileOutputStream file1;
        FileOutputStream file2;

        String url = file;

        try{

            Document doc= Jsoup.connect(url).get();

            Elements enteries = doc.select("table[width = 315]");
            Element firstTab = enteries.first();
            Element secondTab = enteries.get(1);

            //FOR WEEKDAYS
            Elements tabEnteries = firstTab.select("td");

            for( Element e: tabEnteries.select("td[bgcolor = #E1F0FF]"))
                e.remove();

            file1 = ctx.openFileOutput(fromCityWeek, Context.MODE_PRIVATE);
            file2 = ctx.openFileOutput(toCityWeek, Context.MODE_PRIVATE);

            String str;

            for (int i = 0 ; i < tabEnteries.size(); i++){

                if( i > 5){
                    str = tabEnteries.get(i).text() + " " + tabEnteries.get(i+1).text() + "\n";
                    file1.write(str.getBytes());
                    i++;
                    i++;
                    str = tabEnteries.get(i).text() + " " + tabEnteries.get(i+1).text() + "\n";
                    file2.write(str.getBytes());
                    i++;
                }
            }

            //FOR WEEKENDS
            tabEnteries = secondTab.select("td");

            for( Element e: tabEnteries.select("td[bgcolor = #E1F0FF]"))
                e.remove();

            file1 = ctx.openFileOutput(fromCityEnd, Context.MODE_PRIVATE);
            file2 = ctx.openFileOutput(toCityEnd, Context.MODE_PRIVATE);


            for (int i = 0 ; i < tabEnteries.size(); i++){

                if( i > 5){
                    str = tabEnteries.get(i).text() + " " + tabEnteries.get(i+1).text() + "\n";
                    file1.write(str.getBytes());
                    i++;
                    i++;
                    str = tabEnteries.get(i).text() + " " + tabEnteries.get(i+1).text() + "\n";
                    file2.write(str.getBytes());
                    i++;
                }
            }

        }catch( IOException e){
            e.printStackTrace();
        }
    }
}












