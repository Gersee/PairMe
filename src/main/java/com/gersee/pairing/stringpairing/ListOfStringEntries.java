package com.gersee.pairing.stringpairing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Definition of lists of entries we want to pair to each other
 *
 * @author Marc Bober
 */
@Configuration
public class ListOfStringEntries {

    /**
     * All entries we can pair to each other entry
     * @return
     */
    @Bean(name="listOfEntriesToPairToAll")
    public List<String> listOfEntriesToPairToAll() {
        return new ArrayList<>(Arrays.asList(
                "Marco",
                "Christopher",
                "Stefan",
                "Jan",
                "Christian",
                "Marcel",
                "Alex"
        ));
    }

    /**
     * All entries we can pair only to entries of "allList"
     * @return
     */
    @Bean(name="listOfEntriesToPairWithOther")
    public List<String> listOfEntriesToPairWithOther() {
        return new ArrayList<>(Arrays.asList(
                "Anna",
                "Ruben",
                "Ahmad"
        ));
    }
}
