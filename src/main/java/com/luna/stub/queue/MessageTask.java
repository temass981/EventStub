package com.luna.stub.queue;

import com.luna.stub.message.MessageResend;

public class MessageTask implements Runnable {
    private final Message message;
    private final MessageResend kr;

    public MessageTask(Message message, MessageResend kr) {
        this.message = message;
        this.kr = kr;
    }

    @Override
    public void run() {
        try {
            kr.sendResponce(message.body, message.token, message.eventId, message.confirmationKey,message.initialEventId,message.eventSwitchTime);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
