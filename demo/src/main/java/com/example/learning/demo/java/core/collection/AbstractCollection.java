package com.example.learning.demo.java.core.collection;

import java.util.Arrays;

/**
 * 这个类提供了一个 <tt>Collection</tt> 接口的骨架实现，以减少实现此接口所需的工作量。
 * <p>
 *
 * 要实现一个不可修改的集合，程序员只需扩展此类并提供 <tt>iterator</tt> 和 <tt>size</tt> 方法的实现。
 * （由 <tt>iterator</tt> 方法返回的迭代器必须实现 <tt>hasNext</tt> 和 <tt>next</tt>。）
 * <p>
 *
 * 要实现一个可修改的集合，程序员还必须重写此类的 <tt>add</tt> 方法（否则该方法会抛出
 * <tt>UnsupportedOperationException</tt>），
 * 并且由 <tt>iterator</tt> 方法返回的迭代器还必须实现 <tt>remove</tt> 方法。
 * <p>
 *
 * 程序员通常应该提供一个无参构造器和一个接受 <tt>Collection</tt> 参数的构造器，符合 <tt>Collection</tt>
 * 接口规范中的建议。
 * <p>
 *
 * 这个类中每个非抽象方法的文档详细描述了它的实现。每个方法都可以被重写，如果实现的集合允许更加高效的实现。
 * <p>
 *
 * 这个类是
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java 集合框架</a> 的一部分。
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @see Collection
 * @since 1.2
 */

public abstract class AbstractCollection<E> implements Collection<E> {
    /**
     * 唯一的构造器。（由子类构造器调用，通常是隐式的。）
     */
    protected AbstractCollection() {
    }

    // 查询操作

    /**
     * 返回一个迭代器，用于遍历此集合中的元素。
     *
     * @return 一个用于遍历此集合中元素的迭代器
     */
    public abstract Iterator<E> iterator();

    public abstract int size();

    /**
     * {@inheritDoc}
     *
     * <p>
     * 此实现返回 <tt>size() == 0</tt>。
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * 此实现遍历集合中的元素，逐一检查它们是否与指定元素相等。
     *
     * @throws ClassCastException   {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean contains(Object o) {
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext())
                if (it.next() == null)
                    return true;
        } else {
            while (it.hasNext())
                if (o.equals(it.next()))
                    return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * 此实现返回一个包含所有元素的数组，这些元素由集合的迭代器按相同顺序返回，存储在数组的连续元素中，
     * 从索引 0 开始。
     * 返回的数组长度等于迭代器返回的元素个数，即使集合的大小在遍历过程中发生变化（如果集合允许在遍历过程中进行并发修改）。
     * {@code size} 方法仅作为优化提示进行调用；即使迭代器返回的元素个数不同，仍会返回正确的结果。
     *
     * <p>
     * 此方法等效于：
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }</pre>
     */
    public Object[] toArray() {
        // 估算数组大小；准备好看到更多或更少的元素
        Object[] r = new Object[size()];
        Iterator<E> it = iterator();
        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) // 元素比预期少
                return Arrays.copyOf(r, i);
            r[i] = it.next();
        }
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * 此实现返回一个包含所有元素的数组，这些元素由集合的迭代器按相同顺序返回，存储在数组的连续元素中，
     * 从索引 0 开始。如果迭代器返回的元素数量大到无法容纳在指定数组中，那么元素会返回到一个新分配的数组中，
     * 数组的大小与迭代器返回的元素个数相同，即使集合的大小在遍历过程中发生变化（如果集合允许并发修改）。
     * {@code size} 方法仅作为优化提示进行调用；即使迭代器返回的元素个数不同，仍会返回正确的结果。
     *
     * <p>
     * 此方法等效于：
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray(a);
     * }</pre>
     *
     * @throws ArrayStoreException  {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        // 估算数组大小；准备好看到更多或更少的元素
        int size = size();
        T[] r = a.length >= size ? a
                : (T[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size);
        Iterator<E> it = iterator();

        for (int i = 0; i < r.length; i++) {
            if (!it.hasNext()) { // 元素比预期少
                if (a == r) {
                    r[i] = null; // null 结束
                } else if (a.length < i) {
                    return Arrays.copyOf(r, i);
                } else {
                    System.arraycopy(r, 0, a, 0, i);
                    if (a.length > i) {
                        a[i] = null;
                    }
                }
                return a;
            }
            r[i] = (T) it.next();
        }
        // 元素比预期多
        return it.hasNext() ? finishToArray(r, it) : r;
    }

    /**
     * 数组分配的最大大小。
     * 一些虚拟机会在数组中保留一些头部字节。
     * 尝试分配更大的数组可能会导致
     * OutOfMemoryError: Requested array size exceeds VM limit
     */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * 当迭代器返回的元素比预期多时，重新分配用于存储元素的数组，
     * 并从迭代器继续填充。
     *
     * @param r  已包含之前存储的元素的数组
     * @param it 进行中的迭代器
     * @return 包含给定数组中元素的数组，外加从迭代器返回的任何进一步的元素，大小已调整
     */
    @SuppressWarnings("unchecked")
    private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
        int i = r.length;
        while (it.hasNext()) {
            int cap = r.length;
            if (i == cap) {
                int newCap = cap + (cap >> 1) + 1;
                // 防止溢出
                if (newCap - MAX_ARRAY_SIZE > 0)
                    newCap = hugeCapacity(cap + 1);
                r = Arrays.copyOf(r, newCap);
            }
            r[i++] = (T) it.next();
        }
        // 修剪多分配的部分
        return (i == r.length) ? r : Arrays.copyOf(r, i);
    }

    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) // 溢出
            throw new OutOfMemoryError("Required array size too large");
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    // 修改操作

    /**
     * {@inheritDoc}
     *
     * <p>
     * 此实现总是抛出一个 <tt>UnsupportedOperationException</tt>。
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegalArgumentException      {@inheritDoc}
     * @throws IllegalStateException         {@inheritDoc}
     */
    public boolean add(E e) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * 此实现遍历集合，查找指定元素。如果找到该元素，
     * 则通过迭代器的 <tt>remove</tt> 方法将其从集合中移除。
     *
     * <p>
     * 注意，如果此集合的迭代器没有实现 <tt>remove</tt> 方法，
     * 且集合中包含指定的元素，则此实现会抛出一个 <tt>UnsupportedOperationException</tt>。
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     */
    public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o == null) {
            while (it.hasNext()) {
                if (it.next() == null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }
}
