package com.gersee.pairing.stringpairing;

import com.gersee.pairing.algorithm.PairingAlgorithm;
import com.gersee.pairing.common.Tuple;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * Uses the lists defined in "ListOfStringEntries" and pair them
 *
 * @author Marc Bober
 */
@Component
public class DoPairing {

    private List<String> listOfEntriesToPairToAll;
    private List<String> listOfEntriesToPairWithOther;
    private PairingAlgorithm<String> firstOtherToAllAlgorithm;

    @PostConstruct
    public void pairEntriesByFirstOtherToAllAlgorithm() {
        this.pairEntries();
    }

    /**
     * Pairs the entries of the lists defined in "ListOfStringEntries" and prints them out
     */
    public void pairEntries() {

        Tuple<List<Tuple<String, String>>, Optional<String>> pairs = firstOtherToAllAlgorithm
                .pairThem(listOfEntriesToPairToAll,
                Optional.of((listOfEntriesToPairWithOther)));

        System.out.println();
        System.out.println("List of pairs comes here: ");

        for (Tuple<String, String> pair : pairs.getFirst()) {
            System.out.println(pair.toString());
        }

        if (pairs.getSecond().isPresent()) {
            System.out.println("The sole developer: " + pairs.getSecond().get());
        } else {
            System.out.println("No sole developer necessary.");
        }
        System.out.println();
    }

    @Autowired
    @Qualifier("listOfEntriesToPairToAll")
    public void setListOfEntriesToPairToAll(List<String> listOfEntriesToPairToAll) {
        this.listOfEntriesToPairToAll = listOfEntriesToPairToAll;
    }

    @Autowired
    @Qualifier("listOfEntriesToPairWithOther")
    public void setListOfEntriesToPairWithOther(List<String> listOfEntriesToPairWithOther) {
        this.listOfEntriesToPairWithOther = listOfEntriesToPairWithOther;
    }

    @Autowired
    @Qualifier("firstOtherToAllAlgorithm")
    public void setFirstOtherToAllAlgorithm(
            PairingAlgorithm<String> firstOtherToAllAlgorithm) {
        this.firstOtherToAllAlgorithm = firstOtherToAllAlgorithm;
    }
}
