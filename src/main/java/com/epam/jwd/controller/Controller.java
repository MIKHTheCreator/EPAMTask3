package com.epam.jwd.controller;

import com.epam.jwd.service.director.ApplicationStarter;

public class Controller {

    public static void main(String[] args) {
        ApplicationStarter starter = new ApplicationStarter();
        starter.start(9);
    }
}
