import org.example.MyOwnLinkedList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование класса MyOwnLinkedList.
 */
public class MyOwnLinkedListTest {

    private MyOwnLinkedList<Integer> myList;

    /**
     * Инициализация тестируемого объекта перед каждым тестом.
     */
    @BeforeEach
    public void setUp() {
        myList = new MyOwnLinkedList<>();
    }

    /**
     * Проверяет, что добавление элемента увеличивает размер списка.
     */
    @Test
    public void testAdd() {
        myList.add(1);
        myList.add(2);
        myList.add(3);
        assertEquals(3, myList.size());
        assertEquals(1, myList.get(0));
        assertEquals(2, myList.get(1));
        assertEquals(3, myList.get(2));
    }

    /**
     * Проверяет, что элемент может быть добавлен по указанному индексу.
     */
    @Test
    public void testAddAtIndex() {
        myList.add(1);
        myList.add(2);
        myList.add(1, 3); // Добавляем 3 по индексу 1
        assertEquals(3, myList.get(1));
        assertEquals(2, myList.get(2)); // Проверяем, что 2 перемещено на индекс 2
        assertEquals(3, myList.size());
    }

    /**
     * Проверяет, что выбрасывается исключение при попытке добавления по недопустимому индексу.
     */
    @Test
    public void testAddOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> myList.add(-1, 1));
        assertThrows(IndexOutOfBoundsException.class, () -> myList.add(1, 1)); // добавление в пустой список
    }

    /**
     * Проверяет, что элементы могут быть получены по их индексу.
     */
    @Test
    public void testGet() {
        myList.add(1);
        myList.add(2);
        assertEquals(1, myList.get(0));
        assertEquals(2, myList.get(1));
    }

    /**
     * Проверяет, что выбрасывается исключение при попытке получить элемент
     * по недопустимому индексу.
     */
    @Test
    public void testGetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> myList.get(0));
    }

    /**
     * Проверяет удаление элемента по индексу.
     */
    @Test
    public void testRemove() {
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.remove(1); // Удаляем элемент по индексу 1 (значение 2)
        assertEquals(3, myList.get(1)); // Проверяем, что 3 теперь на индексе 1
        assertEquals(2, myList.size());
    }

    /**
     * Проверяет, что выбрасывается исключение, если происходит попытка
     * удалить элемент по недопустимому индексу.
     */
    @Test
    public void testRemoveOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> myList.remove(0));
    }

    /**
     * Проверяет, что все элементы очищаются при вызове метода clear().
     */
    @Test
    public void testClear() {
        myList.add(1);
        myList.add(2);
        myList.clear();
        assertEquals(0, myList.size());
        assertTrue(myList.isEmpty());
    }

    /**
     * Проверяет, что метод isEmpty() возвращает правильное значение
     * для пустого и непустого списка.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(myList.isEmpty());
        myList.add(1);
        assertFalse(myList.isEmpty());
        myList.remove(0);
        assertTrue(myList.isEmpty());
    }

    /**
     * Проверяет, что элемент может быть заменен по индексу.
     */
    @Test
    public void testSet() {
        myList.add(1);
        myList.add(2);
        myList.set(1, 3); // Устанавливаем 3 по индексу 1
        assertEquals(3, myList.get(1));
    }

    /**
     * Проверяет выброс исключения при попытке заменить элемент по индексу,
     * который выходит за границы.
     */
    @Test
    public void testSetOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class, () -> myList.set(0, 1));
    }

    /**
     * Проверяет сортировку списка с использованием пузырьковой сортировки.
     */
    @Test
    public void testBubbleSort() {
        myList.add(3);
        myList.add(1);
        myList.add(2);
        myList.bubbleSort();

        assertEquals(1, myList.get(0));
        assertEquals(2, myList.get(1));
        assertEquals(3, myList.get(2));
    }

    /**
     * Проверяет бинарный поиск элемента в отсортированном списке.
     */
    @Test
    public void testBinarySearch() {
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.bubbleSort(); // Нужно отсортировать перед поиском

        assertEquals(0, myList.binarySearch(1)); // Ищем 1
        assertEquals(1, myList.binarySearch(2)); // Ищем 2
        assertEquals(2, myList.binarySearch(3)); // Ищем 3
        assertEquals(-1, myList.binarySearch(4)); // Элемента нет
    }
}
