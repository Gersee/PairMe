package com.gersee.pairing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PairingApplication {

    /**
     * Runs automatically the pairing algorithm at class stringpairing/DoPairing.
     * There are to lists that will be used for it you can find at stringpairing/ListOfStringEntries:
     *   1. listOfEntriesToPairToAll -> These entries can be paired to each other entry of each list
     *   2. listOfEntriesToPairWithOther -> These entries can not be paired to each other. They can only be
     *          paired to entries of first list. Exception: Second list is larger than first list.
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(PairingApplication.class, args);
    }
}
