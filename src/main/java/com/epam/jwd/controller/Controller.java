package com.epam.jwd.controller;

import com.epam.jwd.service.director.ApplicationStarter;
import com.epam.jwd.view.Menu;

public class Controller {

    private static final int NUM_OF_OPERATORS = 4;

    public static void main(String[] args) {
        ApplicationStarter starter = new ApplicationStarter();
        Menu menu = new Menu();

        menu.getStarterMenu();
        menu.getUserInput();
        starter.start(NUM_OF_OPERATORS);
    }
}
