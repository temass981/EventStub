package com.luna.stub.queue;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Message {
    public String body;
    public String token;
    public String eventId;
    public String confirmationKey;
    public String initialEventId;
    public String eventSwitchTime;
}
