package com.turbulence6th;

public class Mapper<F, S> {

    private F first;
    private S second;

    public Mapper(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public <T> void map(Setter<F, T> setter, Getter<S, T> getter) {
        setter.set(first, getter.get(second));
    }

    public <T, Y> void map(Setter<F, T> setter, Getter<S, Y> getter, Converter<T, Y> converter) {
        T t = converter.convert(getter.get(second));
        setter.set(first, t);
    }
}
