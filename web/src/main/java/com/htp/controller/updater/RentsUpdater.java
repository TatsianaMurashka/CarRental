package com.htp.controller.updater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class RentsUpdater {

    @Autowired
    private RentsUpdaterAsync updaterAsync;

    @PostConstruct
    public void startUpdater() throws IOException, InterruptedException {
        updaterAsync.async();
    }
}
