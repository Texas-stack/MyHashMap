package org.example;

import java.util.LinkedList;

/**
 * Простая реализация хэш-таблицы с использованием отдельной цепочки через связанные списки.
 *
 * @param <K> тип ключей, поддерживаемых этой картой
 * @param <V> тип значений, отображаемых этой картой
 */
public class MyOwnHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Entry<K, V>>[] table;
    private final int capacity;
    private int size;

    /**
     * Конструктор, создающий пустую хэш-карту с указанной начальной емкостью.
     *
     * @param capacity начальная емкость хэш-карты
     */
    public MyOwnHashMap(int capacity) {
        this.capacity = capacity;
        this.table = new LinkedList[capacity];
        this.size = 0;

        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    /**
     * Вычисляет хэш для данного ключа.
     *
     * @param key ключ, для которого нужно вычислить хэш
     * @return индекс хэша для ключа
     */
    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    /**
     * Ассоциирует указанное значение с указанным ключом в этой карте.
     * Если карта уже содержит отображение для данного ключа, старое значение будет заменено.
     *
     * @param key   ключ, с которым требуется ассоциировать указанное значение
     * @param value значение, которое следует ассоциировать с указанным ключом
     */
    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        bucket.add(new Entry<>(key, value));
        size++;
    }

    /**
     * Возвращает значение, к которому данный ключ отображается,
     * или {@code null}, если эта карта не содержит отображения для данного ключа.
     *
     * @param key ключ, значение которого нужно вернуть
     * @return значение, к которому данный ключ отображается, или null, если отображение отсутствует
     */
    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null; // Если ключ не найден
    }

    /**
     * Удаляет отображение для ключа из этой карты, если оно присутствует.
     *
     * @param key ключ, отображение которого нужно удалить из карты
     */
    public void remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> bucket = table[index];

        for (Entry<K, V> entry : bucket) {
            if (entry.key.equals(key)) {
                bucket.remove(entry);
                size--;
                return;
            }
        }
    }

    /**
     * Возвращает количество пар "ключ-значение" в этой карте.
     *
     * @return количество пар "ключ-значение" в этой карте
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает {@code true}, если эта карта не содержит пар "ключ-значение".
     *
     * @return {@code true}, если эта карта пуста; {@code false} в противном случае
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
