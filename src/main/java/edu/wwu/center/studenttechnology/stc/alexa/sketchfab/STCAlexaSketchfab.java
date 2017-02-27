package edu.wwu.center.studenttechnology.stc.alexa.sketchfab;

import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SpeechletException;

import edu.wwu.center.studenttechnology.stc.alexa.framework.speechlet.Speechlet;
import edu.wwu.center.studenttechnology.stc.alexa.framework.speechlet.SpeechletResponse;
import edu.wwu.center.studenttechnology.stc.alexa.sketchfab.database.DatabaseHandler;
import edu.wwu.center.studenttechnology.stc.alexa.sketchfab.intent.SketchfabRequestIntent;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;

public class STCAlexaSketchfab extends Speechlet {
    private final Logger log;
    private final DatabaseHandler databaseHandler;

    public STCAlexaSketchfab(Logger log) {
        this.log = log;
        BasicConfigurator.configure();

        databaseHandler = new DatabaseHandler();
        
        registerIntent(new SketchfabRequestIntent("SketchfabRequestIntent", databaseHandler));
    }
}
