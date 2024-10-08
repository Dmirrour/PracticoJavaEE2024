package org.example.Helper;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    @Override
    public LocalDateTime unmarshal(String v) throws Exception {
        return (v != null) ? LocalDateTime.parse(v, DATE_FORMAT) : null;
    }

    @Override
    public String marshal(LocalDateTime v) throws Exception {
        return (v != null) ? v.format(DATE_FORMAT) : null;
    }
}

