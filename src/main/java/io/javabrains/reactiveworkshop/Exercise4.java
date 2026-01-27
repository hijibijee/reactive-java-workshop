package io.javabrains.reactiveworkshop;

import java.io.IOException;

public class Exercise4 {

    /*
    FLUX - 0 to N numbers of async events
    MONO - 0 to 1 number of async event
     */
    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono()

        // Print the value from intNumberMono when it emits
        ReactiveSources.intNumberMono().subscribe(x -> System.out.println(x));

        // Get the value from the Mono into an integer variable
        Integer number = ReactiveSources.intNumberMono().block();
        System.out.println("Variable instantiated by blocking Mono: " + number);

        System.out.println("Press a key to end");
        System.in.read();
    }

}
