package edu.wwu.center.studenttechnology.stc.alexa.sketchfab.intent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.amazon.speech.slu.Intent;
import com.amazon.speech.speechlet.Session;

import edu.wwu.center.studenttechnology.stc.alexa.framework.intent.IntentBase;
import edu.wwu.center.studenttechnology.stc.alexa.framework.speechlet.SpeechletResponse;
import edu.wwu.center.studenttechnology.stc.alexa.framework.util.SessionUtil;
import edu.wwu.center.studenttechnology.stc.alexa.sketchfab.database.DatabaseHandler;

public class SketchfabRequestIntent extends IntentBase {
    private final DatabaseHandler databaseHandler;

    public SketchfabRequestIntent(String name,
            DatabaseHandler databaseHandler) {
        super(name);
        this.databaseHandler = databaseHandler;
    }

    @Override
    public SpeechletResponse execute(Intent intent, Session session) {
        SessionUtil.setIntentToHandleNextEvent(session, getName());

        String searchData = intent.getSlot("value").getValue();

        String URL = "https://sketchfab.com/search?q=" + searchData;

        Document doc;

        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        Elements labels = doc.getElementsByClass("label");

        int index = 0;

        List<String> results = new ArrayList<String>();

        for (int i = index; i < index + 5; i++) {
            results.add(labels.get(i).text());
        }
        
        String response = "I found ";
        
        for (int i = 0; i < results.size(); i++) {
            if (i == (results.size() - 1) && i != 1) {
                response += " and ";
                response += results.get(i);
            } else {
                response += results.get(i);
                response += ", ";
            }
        }
        
        return SpeechletResponse.newTellResponse(response);
    }
}
