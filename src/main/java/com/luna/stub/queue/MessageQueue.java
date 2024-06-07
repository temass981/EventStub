package com.luna.stub.queue;

import org.springframework.stereotype.Component;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class MessageQueue {
    public MessageQueue() {this.queue = new ConcurrentLinkedQueue<Message>();}
    public Queue<Message> queue;
}
