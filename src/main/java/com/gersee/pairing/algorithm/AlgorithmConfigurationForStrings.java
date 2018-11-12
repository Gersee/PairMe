package com.gersee.pairing.algorithm;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Returns algorithms services
 * @author Marc Bober
 */
@Configuration
public class AlgorithmConfigurationForStrings {

    @Bean(name="firstOtherToAllAlgorithm")
    public PairingAlgorithm<String> firstOtherToAllAlgorithm() {
        return PairingAlgorithm.getInstance(Algorithms.FIRSTOTHERTOALL, String.class);
    }
}
