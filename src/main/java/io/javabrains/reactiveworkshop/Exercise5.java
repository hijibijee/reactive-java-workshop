package io.javabrains.reactiveworkshop;

import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    /*
    Event types for a FLUX/MONO:
       1. Item - a legitimate item
       2. Complete - a completion event
       3. Failure - a failure event. There won't be any more event coming even if there are legitimate items left.
     */
    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
        ReactiveSources.intNumbersFlux()
                .subscribe(
                        number -> System.out.println(number),
                        err -> System.out.println("Error message: " + err.getMessage()),
                        () -> System.out.println("FLUX completed")
                );

        // Subscribe to a flux using an implementation of BaseSubscriber
        ReactiveSources.intNumbersFlux().subscribe(new MySubscriber<>());

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    public void hookOnSubscribe() {
        System.out.println("Subscribed");
        request(1); // Need to make request for items to handle backpressure
    }

    public void hookOnNext(T value) {
        System.out.println(value + " received");
        request(1); // Need to make request again to signal I am available to process more items
    }
}