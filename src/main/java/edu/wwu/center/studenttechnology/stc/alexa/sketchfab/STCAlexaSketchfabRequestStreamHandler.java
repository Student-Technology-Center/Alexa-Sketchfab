package edu.wwu.center.studenttechnology.stc.alexa.sketchfab;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.LoggerFactory;

import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

public class STCAlexaSketchfabRequestStreamHandler
        extends SpeechletRequestStreamHandler {
    private static final Set<String> supportedApplicationIds = new HashSet<String>();

    static {
        supportedApplicationIds.add("");
    }

    public STCAlexaSketchfabRequestStreamHandler() {
        super(new STCAlexaSketchfab(
                LoggerFactory.getLogger(STCAlexaSketchfab.class)),
                supportedApplicationIds);
    }

}
