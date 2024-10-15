package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Random;


@ExtendWith(MockitoExtension.class)
public class NumberAscOrderTest {

    @Mock
    CalculableStrategy<Number> calculableStrategy;

    private CustomStack<Number> stack;

    @BeforeEach
    public void setup() {
        stack = new CustomStack<>(6, calculableStrategy);
    }

    @Test
    public void testSortWithRandomNumbers() throws StackFullException, StackEmptyException {
        Mockito.when(calculableStrategy.calculateValue(Mockito.any(Number.class)))
               .thenAnswer(invocation -> invocation.getArgument(0));

        Random random = new Random();
        
        for (int i = 0; i < 6; i++) {
            int randomNumber = random.nextInt(100) + 1;
            stack.push(randomNumber);
        }

        NumberAscOrder numberAscOrder = new NumberAscOrder(stack);
        List<Number> sortedNumbers = numberAscOrder.sort();
        

        for (int i = 1; i < sortedNumbers.size(); i++) {
            assertTrue(sortedNumbers.get(i).doubleValue() >= sortedNumbers.get(i - 1).doubleValue());
        }

        Mockito.verify(calculableStrategy, Mockito.times(6))
               .calculateValue(Mockito.any(Number.class));
    }

    @Test
    public void testSortWithEmptyStack() throws StackFullException, StackEmptyException {
        NumberAscOrder numberAscOrder = new NumberAscOrder(stack);
        List<Number> sortedNumbers = numberAscOrder.sort();
        
        assertTrue(sortedNumbers.isEmpty());
        Mockito.verify(calculableStrategy, Mockito.never())
               .calculateValue(Mockito.any(Number.class));
    }
}
