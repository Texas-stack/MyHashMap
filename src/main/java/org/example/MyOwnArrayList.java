package org.example;

import java.util.*;

/**
 * Реализация списка CustomArrayList с обобщенными типами.
 *
 * @param <T> тип элементов в списке
 */
public class MyOwnArrayList<T> {
    private Object[] array; // Внутренний массив для хранения элементов
    private int size; // Текущее количество элементов в списке
    private static final int DEFAULT_CAPACITY = 10; // Начальная емкость списка по умолчанию

    /**
     * Создает новый пустой CustomArrayList с емкостью по умолчанию.
     */
    public MyOwnArrayList() {
        this.array = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить в список
     */
    public void add(T element) {
        if (size == array.length) {
            increaseCapacity();
        }
        array[size++] = element;
    }

    /**
     * Добавляет элемент по указанному индексу в список.
     *
     * @param index   индекс, по которому нужно добавить элемент
     * @param element элемент, который нужно добавить в список
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        if (size == array.length) {
            increaseCapacity();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно получить
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        return (T) array[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(array, index + 1, array, index, numMoved);
        }
        array[--size] = null;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    /**
     * Преобразует массив элементов в массив и возвращает его.
     *
     * @return массив элементов
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) Arrays.copyOf(array, size);
    }

    /**
     * Сортирует элементы в списке в порядке возрастания с использованием переданного компаратора.
     *
     * @param comparator компаратор для сортировки элементов
     */
    public void sortWithComparator(Comparator<? super T> comparator) {
        List<T> list = new ArrayList<>(Arrays.asList(this.toArray()));
        list.sort(comparator);
        for (int i = 0; i < list.size(); i++) {
            this.set(i, list.get(i));
        }
    }

    /**
     * Увеличивает ёмкость массива
     */
    private void increaseCapacity() {
        int newCapacity = array.length * 2;
        Object[] newArray = new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * Возвращает текущее количество элементов в списке.
     *
     * @return количество элементов в списке
     */
    public int size() {
        return size;
    }

    /**
     * Быстрая сортировка элементов в списке с использованием переданного компаратора.
     *
     * @param comparator компаратор для сортировки элементов
     */
    public void quickSort(Comparator<T> comparator) {
        quickSortHelper(0, size - 1, comparator);
    }

    /**
     * Вспомогательный метод для реализации быстрой сортировки.
     */
    private void quickSortHelper(int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivot = partition(low, high, comparator);
            quickSortHelper(low, pivot - 1, comparator);
            quickSortHelper(pivot + 1, high, comparator);
        }
    }

    /**
     * Разделительный метод для быстрой сортировки.
     */
    @SuppressWarnings("unchecked")
    private int partition(int low, int high, Comparator<T> comparator) {
        T pivot = (T) array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare((T) array[j], pivot) <= 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return i + 1;
    }

    /**
     * Меняет местами два элемента в массиве.
     */
    private void swap(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Проверяет, пуст ли список.
     *
     * @return true, если список пуст, иначе false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Заменяет элемент в списке по указанному индексу новым элементом.
     *
     * @param index   индекс элемента, который нужно заменить
     * @param element новый элемент для замены
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    public void set(int index, T element) {
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.size());
        }
        T[] elements = this.toArray();
        elements[index] = element;
        this.array = elements;
    }
}