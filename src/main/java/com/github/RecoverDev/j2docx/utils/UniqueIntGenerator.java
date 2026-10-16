package com.github.RecoverDev.j2docx.utils;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Генератор уникальных целочисленных значений.
 *
 * <p>Генератор последовательно выдает значения типа {@code int},
 * пропуская уже выданные и исключенные значения.</p>
 */
public final class UniqueIntGenerator {

    private final Set<Integer> usedValues = new HashSet<>();
    private final Set<Integer> excludedValues = new HashSet<>();

    private long nextValue;

    /**
     * Создает генератор, начинающий выдачу с нуля.
     */
    public UniqueIntGenerator() {
        this(1);
    }

    /**
     * Создает генератор с заданного начального значения.
     *
     * @param startValue начальное значение
     */
    public UniqueIntGenerator(int startValue) {
        this.nextValue = startValue;
    }

    /**
     * Возвращает следующее уникальное значение.
     *
     * @return уникальное целочисленное значение
     * @throws IllegalStateException если доступные значения закончились
     */
    public synchronized int next() {
        while (nextValue <= Integer.MAX_VALUE) {
            int candidate = (int) nextValue++;

            if (!excludedValues.contains(candidate)
                    && usedValues.add(candidate)) {
                return candidate;
            }
        }

        throw new IllegalStateException(
                "Все доступные значения int исчерпаны"
        );
    }

    /**
     * Исключает значение из дальнейшей выдачи.
     *
     * @param value исключаемое значение
     */
    public synchronized void exclude(int value) {
        excludedValues.add(value);
    }

    /**
     * Исключает несколько значений из дальнейшей выдачи.
     *
     * @param values коллекция исключаемых значений
     */
    public synchronized void excludeAll(
            Collection<Integer> values) {
        excludedValues.addAll(values);
    }

    /**
     * Проверяет, было ли значение уже выдано.
     *
     * @param value проверяемое значение
     * @return true, если значение ранее выдано
     */
    public synchronized boolean isUsed(int value) {
        return usedValues.contains(value);
    }

    /**
     * Проверяет, исключено ли значение.
     *
     * @param value проверяемое значение
     * @return true, если значение исключено
     */
    public synchronized boolean isExcluded(int value) {
        return excludedValues.contains(value);
    }
}
