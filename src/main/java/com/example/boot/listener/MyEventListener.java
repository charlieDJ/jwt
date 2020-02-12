package com.example.boot.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class MyEventListener {

    @EventListener
    public void myEvent(MyEvent myEvent){
        System.out.println("myEvent: " + myEvent.getSource().toString());
    }

}
