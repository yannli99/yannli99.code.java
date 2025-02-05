package com.example.learning.demo.java.core.collection;

import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * 实现此接口的对象可以作为 “for-each 循环” 语句的目标。参见
 * <strong>
 * <a href="
 * {@docRoot}/../technotes/guides/language/foreach.html">For-each 循环</a>
 * </strong>
 *
 * @param <T> 迭代器返回的元素类型
 *
 * @since 1.5
 * @jls 14.14.2 增强型 for 语句
 */
public interface Iterable<T> extends java.lang.Iterable<T> {
    /**
     * 返回一个遍历类型为 {@code T} 的元素的迭代器。
     *
     * @return 一个迭代器。
     */
    Iterator<T> iterator();

    /**
     * 对 {@code Iterable} 中的每个元素执行给定的操作，直到所有元素都处理完毕或操作抛出异常。
     * 除非实现类另有说明，否则操作将按照迭代顺序执行（如果指定了迭代顺序）。操作中抛出的异常
     * 会传递给调用者。
     *
     * @implSpec
     *           <p>
     *           默认实现的行为等同于：
     * 
     *           <pre>{@code
     *     for (T t : this)
     *         action.accept(t);
     * }</pre>
     *
     * @param action 对每个元素执行的操作
     * @throws NullPointerException 如果指定的操作为 null
     * @since 1.8
     */
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    /**
     * 创建一个 {@link Spliterator}，用于遍历本 {@code Iterable} 所描述的元素。
     *
     * @implSpec
     *           默认实现从该 {@code Iterable} 的 {@code Iterator} 创建一个
     *           <em><a href="Spliterator.html#binding">早绑定</a></em> 的 spliterator。
     *           该 spliterator 继承了该迭代器的 <em>快速失败</em> 特性。
     *
     * @implNote
     *           默认实现通常应被重写。默认实现返回的 spliterator 切分能力较差，
     *           大小未知，且不会报告任何 spliterator 特性。实现类几乎总能提供更好的实现。
     *
     * @return 一个描述本 {@code Iterable} 中元素的 {@code Spliterator}。
     * @since 1.8
     */
    default Spliterator<T> spliterator() {
        return Spliterators.spliteratorUnknownSize(iterator(), 0);
    }
}
