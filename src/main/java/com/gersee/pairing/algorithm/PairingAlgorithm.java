package com.gersee.pairing.algorithm;

import com.gersee.pairing.common.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The abstract class of for pairing algorithms. Can be used as supertype to change the algorithm without
 * problems.
 *
 * @author Marc Bober
 */
public abstract class PairingAlgorithm<T> {

    /**
     * Use static factory method getInstance
     */
    protected PairingAlgorithm() {
        //use static factory method
    }

    /**
     * Creates an instance of the choosen algorithm
     * @param useAlgorithm Which algorithm should be used?
     * @param type Class of generic type of entries
     * @param <T> Generic type of entries
     * @return A class of pairingAlgorithm
     */
    public static <T> PairingAlgorithm getInstance(Algorithms useAlgorithm, Class<T> type) {
        if (useAlgorithm == null) {
            return new FirstOtherToAllAlgorithm<T>();
        }

        if (useAlgorithm == Algorithms.FIRSTOTHERTOALL) {
            return new FirstOtherToAllAlgorithm<T>();
        }

        return new FirstOtherToAllAlgorithm<T>();
    }

    /**
     * Pairs the entries. One list can be paired to every entry. Entries of other list can only be paired
     * to entries of the other list. If the second list has more entries, as much as possible will be
     * paired to the other list and the others will be paired together.
     * Lists will be changed.
     *
     * @param pairToAll Entries which can be paired to everyone
     * @param pairToOther Entries which can only be paired by the other list
     * @return A tuple with first entry a list of pairs. The second entry is optional for the not paired
     * entry if the number of entries are not odd.
     */
    public final Tuple<List<Tuple<T, T>>, Optional<T>> pairThem (List<T> pairToAll,
            Optional<List<T>> pairToOther) {

        Tuple<List<Tuple<T, T>>, Optional<T>> returnObject = this.createEmptyReturnObject();


        if (pairToAll == null || pairToAll.isEmpty()) {
            return returnObject;
        } else {
            this.runAlgorithm(pairToAll, pairToOther, returnObject);
        }

        return returnObject;
    }

    /**
     * The algorithm itself.
     * Lists will be changed.
     *
     * @param pairToAll Entries which can be paired to everyone
     * @param pairToOther Entries which can only be paired by the other list
     * @param returnObject The return object that will be changed.
     * @return A tuple with first entry a list of pairs. The second entry is optional for the not paired
     *      * entry if the number of entries are not odd.
     */
    protected abstract Tuple<List<Tuple<T, T>>, Optional<T>> runAlgorithm (List<T> pairToAll,
            Optional<List<T>> pairToOther, Tuple<List<Tuple<T, T>>, Optional<T>> returnObject);

    /**
     * Creates a empty returnObject
     * @return
     */
    private final Tuple<List<Tuple<T, T>>, Optional<T>> createEmptyReturnObject() {
        List<Tuple<T, T>> emptyList = new ArrayList<>();
        Optional<T> emptyOverhang = Optional.empty();
        return new Tuple<>(emptyList, emptyOverhang);
    }
}
