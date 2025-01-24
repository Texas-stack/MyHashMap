import org.example.MyOwnArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестирование класса MyOwnArrayList.
 */
public class MyOwnArrayListTest {

    private MyOwnArrayList<Integer> myList;

    /**
     * Инициализация тестируемого объекта перед каждым тестом.
     */
    @BeforeEach
    public void setUp() {
        myList = new MyOwnArrayList<>();
    }

    /**
     * Проверяет, что добавление элементов увеличивает размер списка.
     */
    @Test
    public void testAddAndSize() {
        myList.add(1);
        myList.add(2);
        myList.add(3);
        assertEquals(3, myList.size());
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
        myList.clear();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.remove(1);
        assertEquals(3, myList.get(1));
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
     * Проверяет правильность преобразования списка в массив.
     */
    @Test
    public void testToArray() {
        myList.add(1);
        myList.add(2);
        Integer[] expectedArray = {1, 2};
        assertArrayEquals(expectedArray, myList.toArray());
    }

    /**
     * Проверяет сортировку списка с использованием компаратора.
     */
    @Test
    public void testSortWithComparator() {
        myList.add(3);
        myList.add(1);
        myList.add(2);

        myList.sortWithComparator(Comparator.naturalOrder());

        assertEquals(1, myList.get(0));
        assertEquals(2, myList.get(1));
        assertEquals(3, myList.get(2));
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
     * Проверяет быструю сортировку списка.
     */
    @Test
    public void testQuickSort() {
        myList.add(3);
        myList.add(1);
        myList.add(2);

        myList.quickSort(Comparator.naturalOrder());

        assertEquals(1, myList.get(0));
        assertEquals(2, myList.get(1));
        assertEquals(3, myList.get(2));
    }
}
