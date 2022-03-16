package util;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestStepStarted;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class GetStep implements ConcurrentEventListener {
    static String stepName;
    static String keyGherkin;
    public static String gherkin;

    public EventHandler<TestStepStarted> stepHandler = event -> {
        try {
            handlerStepStarter(event);
        } catch (IOException e) {
            e.getMessage();
        }
    };

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepStarted.class, stepHandler);
    }

    public void handlerStepStarter(TestStepStarted event) throws IOException{
        if (event.getTestStep() instanceof PickleStepTestStep) {
            PickleStepTestStep testStep = (PickleStepTestStep) event.getTestStep();
            stepName = testStep.getStep().getText();
            keyGherkin = testStep.getStep().getKeyword();
            gherkin = keyGherkin + " " + stepName;
            createFile(gherkin);
        }
    }

    public void createFile(String f) throws IOException {
        FileWriter writer = new FileWriter("file.txt", true);
        writer.write(f);
        writer.write(System.getProperty("line.separator"));
        writer.close();
    }
}
