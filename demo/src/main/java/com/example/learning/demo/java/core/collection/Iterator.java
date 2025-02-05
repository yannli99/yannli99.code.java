package com.example.learning.demo.java.core.collection;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * 用于遍历集合的迭代器。{@code Iterator} 在 Java 集合框架中取代了
 * {@link Enumeration}。迭代器与枚举器有如下两个区别：
 *
 * <ul>
 * <li>迭代器允许调用者在遍历过程中按照明确定义的语义从底层集合中移除元素。
 * <li>方法名称经过改进，更加清晰。
 * </ul>
 *
 * <p>
 * 该接口是
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java 集合框架</a> 的一部分。
 *
 * @param <E> 迭代器返回的元素类型
 *
 * @author Josh Bloch
 * @see Collection
 * @see ListIterator
 * @see Iterable
 * @since 1.2
 */
public interface Iterator<E> extends java.util.Iterator<E> {
    /**
     * 如果迭代过程中还有更多元素，则返回 {@code true}。
     * （换句话说，如果调用 {@link #next} 方法将返回一个元素而不会抛出异常，则返回 {@code true}。）
     *
     * @return 如果迭代过程中还有更多元素，则返回 {@code true}
     */
    boolean hasNext();

    /**
     * 返回迭代过程中的下一个元素。
     *
     * @return 迭代过程中的下一个元素
     * @throws NoSuchElementException 如果迭代中没有更多元素
     */
    E next();

    /**
     * 从底层集合中移除此迭代器返回的最后一个元素（这是一个可选操作）。
     * 每调用一次 {@link #next} 方法后只能调用一次此方法。
     * 如果在遍历过程中以除调用该方法之外的方式修改底层集合，
     * 则迭代器的行为是未定义的。
     *
     * @implSpec
     *           默认实现会抛出 {@link UnsupportedOperationException} 异常，并且不执行任何其他操作。
     *
     * @throws UnsupportedOperationException 如果此迭代器不支持 {@code remove} 操作
     * @throws IllegalStateException         如果还没有调用 {@code next} 方法，或在最后一次调用
     *                                       {@code next} 后已经调用过 {@code remove} 方法
     */
    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * 对剩余的每个元素执行给定的操作，直到所有元素都被处理或该操作抛出异常。
     * 如果指定了迭代顺序，则操作按照该顺序执行。操作中抛出的异常会传递给调用者。
     *
     * @implSpec
     *           <p>
     *           默认实现的行为等同于：
     * 
     *           <pre>{@code
     *     while (hasNext())
     *         action.accept(next());
     * }</pre>
     *
     * @param action 对每个元素执行的操作
     * @throws NullPointerException 如果指定的操作为 null
     * @since 1.8
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
