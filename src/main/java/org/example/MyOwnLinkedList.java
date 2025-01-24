package org.example;

/**
 * Реализация связного списка CustomLinkedList с обобщенными типами.
 *
 * @param <T> тип элементов в списке
 */
public class MyOwnLinkedList<T> {
    private Node<T> head; // Первый элемент списка
    private Node<T> tail; // Последний элемент списка
    private int size; // Текущее количество элементов в списке

    /**
     * Конструктор, создающий новый пустой CustomLinkedList.
     */
    public MyOwnLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец списка.
     *
     * @param element элемент, который нужно добавить в список
     */
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (size == 0) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
        size++;
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
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> current = getNode(index);
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    /**
     * Возвращает элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно получить
     * @return элемент по указанному индексу
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    public T get(int index) {
        return getNode(index).data;
    }

    /**
     * Удаляет элемент по указанному индексу.
     *
     * @param index индекс элемента, который нужно удалить
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    public void remove(int index) {
        Node<T> current = getNode(index);
        if (current.prev != null) {
            current.prev.next = current.next;
        } else {
            head = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        } else {
            tail = current.prev;
        }
        size--;
    }

    /**
     * Очищает список, удаляя все элементы.
     */
    public void clear() {
        Node<T> current = head;
        while (current != null) {
            Node<T> next = current.next;
            current.data = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        head = tail = null;
        size = 0;
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
        getNode(index).data = element;
    }

    /**
     * Возвращает узел по указанному индексу.
     *
     * @param index индекс узла, который нужно получить
     * @return узел по указанному индексу
     * @throws IndexOutOfBoundsException если индекс находится вне допустимого диапазона
     */
    private Node<T> getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс: " + index + ", Размер: " + size);
        }
        Node<T> current;
        if (index < size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = size - 1; i > index; i--) {
                current = current.prev;
            }
        }
        return current;
    }

    /**
     * Узел списка.
     *
     * @param <T> тип данных узла
     */
    private static class Node<T> {
        T data; // Данные узла
        Node<T> next; // Ссылка на следующий узел
        Node<T> prev; // Ссылка на предыдущий узел

        /**
         * Конструктор, создающий новый узел с заданными данными.
         *
         * @param data данные узла
         */
        Node(T data) {
            this.data = data;
        }
    }

    /**
     * Сортирует список с использованием алгоритма сортировки пузырьком.
     */
    @SuppressWarnings("unchecked")
    public void bubbleSort() {
        if (size > 1) {
            boolean wasChanged;

            do {
                Node<T> current = head;
                wasChanged = false;

                while (current.next != null) {
                    if (((Comparable<T>) current.data).compareTo(current.next.data) > 0) {
                        // Меняем данные в узлах
                        T temp = current.data;
                        current.data = current.next.data;
                        current.next.data = temp;
                        wasChanged = true;
                    }
                    current = current.next;
                }
            } while (wasChanged);
        }
    }



    /**
     * Выполняет бинарный поиск элемента в списке.
     *
     * @param element элемент, который нужно найти
     * @return индекс элемента в списке или -1, если элемент не найден
     */
    @SuppressWarnings("unchecked")
    public int binarySearch(T element) {
        int lowerBound = 0;
        int upperBound = size - 1;

        while (lowerBound <= upperBound) {
            int middle = (lowerBound + upperBound) / 2;
            T middleValue = get(middle);

            if (((Comparable<T>) middleValue).compareTo(element) < 0) {
                lowerBound = middle + 1;
            } else if (((Comparable<T>) middleValue).compareTo(element) > 0) {
                upperBound = middle - 1;
            } else {
                return middle;
            }
        }

        return -1;
    }

}