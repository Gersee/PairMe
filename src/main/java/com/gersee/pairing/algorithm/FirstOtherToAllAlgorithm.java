package com.gersee.pairing.algorithm;

import com.gersee.pairing.common.PairingException;
import com.gersee.pairing.common.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import org.apache.commons.collections4.CollectionUtils;

/**
 *  An algorithm that first pairs by random all entries from otherList with allList, then remaining
 *  entries of allList with themselfes.
 *
 * @author Marc Bober
 */
public class FirstOtherToAllAlgorithm<T> extends PairingAlgorithm<T> {

    private Random randomize = new Random();


    /**
     * An algorithm that first pairs by random all entries from otherList with allList, then remaining
     * entries of allList with themselfes.
     * Lists will be changed.
     *
     * @param pairToAll Entries which can be paired to everyone
     * @param pairToOther Entries which can only be paired by the other list
     * @param returnObject The return object that will be changed.
     * @return A tuple with first entry a list of pairs. The second entry is optional for the not paired
     *      * entry if the number of entries are not odd.
     */
    protected Tuple<List<Tuple<T, T>>, Optional<T>> runAlgorithm (List<T> pairToAll,
            Optional<List<T>> pairToOther,
            Tuple<List<Tuple<T, T>>, Optional<T>> returnObject) {

        List<T> pairToOtherList = null;
        if (pairToOther.isPresent()) {
            pairToOtherList = pairToOther.get();
        } else {
            pairToOtherList = new ArrayList<>();
        }

        //First step: Try to map entries of other list to entries of all list
        while(!pairToOtherList.isEmpty() && !pairToAll.isEmpty()) {
            returnObject.getFirst().add(this.pairByRandomIndex(pairToOtherList, pairToAll));
        }

        //Second step: If otherList is greater and has at least 2 entries, map them together
        while (pairToOtherList.size() > 1) {
            returnObject.getFirst().add(this.pairByRandomIndex(pairToOtherList, pairToOtherList));
        }

        //Third step: If allList has at least 2 entries, map them together
        while (pairToAll.size() > 1) {
            returnObject.getFirst().add(this.pairByRandomIndex(pairToAll, pairToAll));
        }

        if (pairToOtherList.size() == 1) {
            returnObject.setSecond(Optional.of(pairToOtherList.get(0)));
        }
        if (pairToAll.size() == 1) {
            returnObject.setSecond(Optional.of(pairToAll.get(0)));
        }


        return returnObject;
    }


    /**
     * Gets a entry out of both list by random.
     * @param listOne List one
     * @param listTwo List two, can be the same as list one if sameIndexAllowed is false
     * @return A pair of mapped Entries
     */
    private  Tuple<T, T> pairByRandomIndex(List<T> listOne, List<T> listTwo) {
        //If it is the same list, the indexes can not be the same!
        if (listOne == null || listTwo == null || listOne.isEmpty() || listTwo.isEmpty()) {
            throw new PairingException ("At least one of the lists has no entries");
        }
        boolean sameList = CollectionUtils.isEqualCollection(listOne, listTwo);

        int indexOfListOne = randomize.nextInt(listOne.size());
        int indexOfListTwo = randomize.nextInt(listTwo.size());

        if (sameList) {
            if (listOne.size() < 2) {
                throw new PairingException ("Same list with less than 2 entries.");
            }
            while (indexOfListOne == indexOfListTwo) {
                indexOfListTwo = randomize.nextInt(listTwo.size());
            }
        }

        T entryOfListOne = listOne.get(indexOfListOne);
        T entryOfListTwo = listTwo.get(indexOfListTwo);


        Tuple<T, T> mappedEntries = new Tuple<>(entryOfListOne, entryOfListTwo);

        listOne.remove(entryOfListOne);
        listTwo.remove(entryOfListTwo);

        return mappedEntries;
    }
}
