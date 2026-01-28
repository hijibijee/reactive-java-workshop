package io.javabrains.reactiveworkshop;

import reactor.core.publisher.Flux;
import reactor.core.publisher.SignalType;

import java.io.IOException;

public class Exercise8 {


    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumbersFluxWithException()

        // Print values from intNumbersFluxWithException and print a message when error happens

        /* This will completely swallow the error (no error will pop up in console) */
        ReactiveSources.intNumbersFluxWithException()
            .subscribe(x -> System.out.println(x), err -> System.out.println(err.getMessage()));

        /* This will execute the lambda on error, but the exception will still pop up in console */
        ReactiveSources.intNumbersFluxWithException()
            .doOnError(err -> System.out.println(err.getMessage()))
            .subscribe(x -> System.out.println(x));

        // Print values from intNumbersFluxWithException and continue on errors
        /* lambda will be executed on error, but the subscription will continue to consume next items from the flux */
        ReactiveSources.intNumbersFluxWithException()
            .onErrorContinue((err, item) -> System.out.println("Error: " + err.getMessage()))
            .subscribe(x -> System.out.println(x));

        // Print values from intNumbersFluxWithException and when errors
        // happen, replace with a fallback sequence of -1 and -2
        /* It will switch to the new flux on error */
        ReactiveSources.intNumbersFluxWithException()
            .onErrorResume(err -> Flux.just(-1, -2))
            .subscribe(x -> System.out.println(x));

        ReactiveSources.intNumbersFluxWithException()
            .doFinally(signalType -> {
                if (signalType == SignalType.ON_COMPLETE) {
                    System.out.println("Job done!");
                } else if (signalType == SignalType.ON_ERROR) {
                    System.out.println("Error!");
                }
            })
            .subscribe(x -> System.out.println(x));

        System.out.println("Press a key to end");
        System.in.read();
    }

}
