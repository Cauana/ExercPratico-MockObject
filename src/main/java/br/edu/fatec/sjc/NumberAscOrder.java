package br.edu.fatec.sjc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NumberAscOrder<T extends Number & Comparable<? super T>> {
    private CustomStack<T> stack;

    public NumberAscOrder(CustomStack<T> stack) {
        this.stack = stack;
    }

    public List<T> sort() throws StackEmptyException {
        List<T> numbers = new ArrayList<>();
        
        while (!stack.isEmpty()) {
            numbers.add(stack.pop());
        }

        Collections.sort(numbers);

        return numbers;
    }
}
