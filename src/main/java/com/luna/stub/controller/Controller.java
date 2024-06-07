package com.luna.stub.controller;

import com.luna.stub.message.BodyMessage;
import com.luna.stub.queue.Message;
import com.luna.stub.queue.MessageQueue;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Service
@RestController
@RequiredArgsConstructor
public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);
    private final MeterRegistry meterRegistry;

    @Autowired
    MessageQueue queue;

    @PostMapping(value = "/device/{deviceID}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public ResponseEntity<String> pushMessage(@RequestBody String body, @PathVariable String deviceID, @Nullable @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) throws JSONException {
        HashMap event = new BodyMessage(meterRegistry).getEventID(body);
        queue.queue.add(new Message(body, authorization, event.get("eventId").toString(), event.get("confirmationKey").toString(), event.get("initialEventId").toString(), event.get("eventSwitchTime").toString()));
        return new ResponseEntity<>(event.get("eventId").toString(), HttpStatus.OK);
    }
}
