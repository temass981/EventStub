package com.luna.stub.queue;

import com.luna.stub.message.MessageResend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
@RequiredArgsConstructor
public class ThreadService {
    @Autowired
    MessageQueue queue;

    private final RestTemplate restTemplate;
    ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    public void runMessage(){
        MessageResend resend = new MessageResend(restTemplate);
        while (true){
            if (!queue.queue.isEmpty()){
                Message message = queue.queue.poll();
                executor.execute(new MessageTask(message, resend));
            }
        }
    }
}
