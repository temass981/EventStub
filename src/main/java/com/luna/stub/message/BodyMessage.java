package com.luna.stub.message;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class BodyMessage {
    private final MeterRegistry meterRegistry;

    public BodyMessage(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public HashMap<String, String> getEventID(String body) throws JSONException {
        // Получение параметров и мапа для ретурна
        JSONObject obj = new JSONObject(body);
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("eventId", obj.getString("eventId"));
        data.put("confirmationKey", obj.getString("confirmationKey"));
        data.put("initialEventId", obj.getString("initialEventId"));
        data.put("eventSwitchTime", obj.getString("eventSwitchTime"));


        // Метрика
        String msg = obj.getJSONObject("aps").getJSONObject("alert").getString("body");
        Timer timer = Timer.builder("processing_time_in_kafka").publishPercentiles(0.5,0.95,0.9,0.99).register(meterRegistry);
        timer.record(System.currentTimeMillis() - Long.parseLong(msg), TimeUnit.MILLISECONDS);

        return data;
    }
}