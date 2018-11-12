package com.gersee.pairing.common;

/**
 * @author Marc Bober
 */
public class Tuple<T, U> {

    public T first;
    public U second;

    public Tuple(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    @Override
    public String toString() {
        return "Tuple {" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
