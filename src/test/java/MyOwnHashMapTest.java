import org.example.MyOwnHashMap;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для проверки функциональности класса MyOwnHashMap.
 */
public class MyOwnHashMapTest {

    /**
     * Тестирование метода put и get.
     * Проверяет добавление элементов и получение значений по ключу.
     */
    @Test
    public void testPutAndGet() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));
        assertNull(map.get("three")); // Ключ отсутствует
    }

    /**
     * Тестирование обновления значения для существующего ключа.
     * Проверяет, что значение обновляется правильно.
     */
    @Test
    public void testUpdateExistingKey() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        map.put("one", 1);
        map.put("one", 2); // Обновляем значение для ключа "one"

        assertEquals(Integer.valueOf(2), map.get("one")); // Проверяем обновленное значение
    }

    /**
     * Тестирование удаления элемента по ключу.
     * Проверяет, что элемент удаляется из карты.
     */
    @Test
    public void testRemove() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        map.put("one", 1);
        map.put("two", 2);

        map.remove("one"); // Удаляем элемент с ключом "one"

        assertNull(map.get("one")); // Проверяем, что элемент удален
        assertEquals(Integer.valueOf(2), map.get("two")); // Проверяем, что второй элемент остался
    }

    /**
     * Тестирование размера карты.
     * Проверяет корректное изменение размера при добавлении и удалении элементов.
     */
    @Test
    public void testSize() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        assertTrue(map.isEmpty());

        map.put("one", 1);
        map.put("two", 2);

        assertEquals(2, map.size()); // Проверяем размер после добавления

        map.remove("one");
        assertEquals(1, map.size()); // Проверяем размер после удаления
    }

    /**
     * Тестирование метода isEmpty.
     * Проверяет, корректно ли определяется пустота карты.
     */
    @Test
    public void testIsEmpty() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);

        assertTrue(map.isEmpty()); // Проверяем, что карта пуста
        map.put("one", 1);
        assertFalse(map.isEmpty()); // Проверяем, что карта не пуста
        map.remove("one");
        assertTrue(map.isEmpty()); // Проверяем, что карта снова пуста
    }
}
