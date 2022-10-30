package com.systemdesign.statemachine;

import java.util.Map;
import java.util.Queue;

public class DigitalWallet {


    private Map<String, Integer> accountBalanceState;

    private Queue<Command> commandQueue;

    private Queue<ApplicationEvent> eventQueue;


    // events are coming from a queue
    public void executeCommand(Command command){
        validateCommand(command);
        raiseEvents(command);

    }
    // events are coming from a queue
    public void processEvent(ApplicationEvent event){
        applyEventToChangeState(event);
    }

    private void raiseEvents(Command command) {

    }


    private void validateCommand(Command command) {

    }

    private void applyEventToChangeState(ApplicationEvent event){

    }

}
