package com.epam.jwd.view;

import java.util.Scanner;

public class Menu {

    private static final String STARTER_MENU = """
            ******************MULTI-THREADING CALLCENTER IS GLAD TO SEE YOU******************
            
            This is a test multi-threading application with little amount of opportunities
            You have only one:
            1.Choose the number of operators of this CallCenter
            Now we have 4 active operators in our center and 30 clients!!!
            """;
    private static final String LETS_START = "//Press Enter button:";
    private static final String APPLICATION_STARTING = "Application starting, enjoy the result!";


    public void getStarterMenu() {
        System.out.println(STARTER_MENU);
    }

    public void getUserInput() {
        System.out.println(LETS_START);

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextLine()) {
            System.out.println(APPLICATION_STARTING);
        }

    }

}
