import org.example.MyOwnHashMap;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MyOwnHashMapTest {

    @Test
    public void testPutAndGet() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        map.put("one", 1);
        map.put("two", 2);

        assertEquals(Integer.valueOf(1), map.get("one"));
        assertEquals(Integer.valueOf(2), map.get("two"));
        assertNull(map.get("three")); // Ключа "three" нет
    }

    @Test
    public void testUpdateExistingKey() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        map.put("one", 1);
        map.put("one", 2); // Обновляем значение ключа "one"

        assertEquals(Integer.valueOf(2), map.get("one"));
    }

    @Test
    public void testRemove() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        map.put("one", 1);
        map.put("two", 2);

        map.remove("one");

        assertNull(map.get("one")); // Ключ "one" должен быть удален
        assertEquals(Integer.valueOf(2), map.get("two")); // "two" все еще существует
    }

    @Test
    public void testSize() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);
        assertTrue(map.isEmpty()); // Сначала карта должна быть пустой

        map.put("one", 1);
        map.put("two", 2);

        assertEquals(2, map.size()); // Должно быть 2 элемента

        map.remove("one");
        assertEquals(1, map.size()); // Должно быть 1 элемент после удаления "one"
    }

    @Test
    public void testIsEmpty() {
        MyOwnHashMap<String, Integer> map = new MyOwnHashMap<>(10);

        assertTrue(map.isEmpty()); // Должен быть пустым
        map.put("one", 1);
        assertFalse(map.isEmpty()); // Теперь не пустой
        map.remove("one");
        assertTrue(map.isEmpty()); // Вернулся в пустое состояние
    }
}
