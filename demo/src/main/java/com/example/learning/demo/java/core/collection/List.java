package com.example.learning.demo.java.core.collection;

import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.Vector;
import java.util.function.UnaryOperator;

/**
 * 一个有序的集合（也称为<i>序列</i>）。实现此接口的用户可以精确控制每个元素插入列表的位置。
 * 用户可以通过元素的整数索引（列表中的位置）来访问元素，并在列表中搜索元素。
 * <p>
 *
 * 与集合（Set）不同，列表（List）通常允许重复元素。更正式地说，列表通常允许一对元素 <tt>e1</tt> 和 <tt>e2</tt> 满足
 * <tt>e1.equals(e2)</tt>，并且如果列表允许 null 元素，通常也允许多个 null 元素。
 * 虽然可以实现一个禁止重复的列表，通过在用户试图插入重复元素时抛出运行时异常，但我们预计这种用法非常罕见。
 * <p>
 *
 * <tt>List</tt> 接口在
 * <tt>iterator</tt>、<tt>add</tt>、<tt>remove</tt>、<tt>equals</tt> 和
 * <tt>hashCode</tt> 方法的契约上，
 * 添加了超出 <tt>Collection</tt> 接口规范的附加约定。
 * 其他继承方法的声明也包含在此处以方便使用。
 * <p>
 *
 * <tt>List</tt> 接口提供了四种方法来按位置（索引）访问列表中的元素。
 * 列表（像 Java 数组）是从 0 开始索引的。请注意，对于某些实现（例如 <tt>LinkedList</tt>
 * 类），这些操作可能按与索引值成比例的时间执行。
 * 因此，如果调用方不知道实现类，通常更推荐通过迭代遍历列表中的元素，而不是使用索引访问。
 * <p>
 *
 * <tt>List</tt> 接口提供了一种特殊的迭代器，称为 <tt>ListIterator</tt>，它允许元素的插入和替换，
 * 并在普通的 <tt>Iterator</tt> 接口提供的操作基础上增加了双向访问。
 * 此接口还提供了一种方法来从列表中指定位置开始获取列表迭代器。
 * <p>
 *
 * <tt>List</tt> 接口提供了两种方法来搜索指定对象。
 * 从性能的角度来看，使用这些方法时应谨慎。在许多实现中，这些方法将执行代价高昂的线性搜索。
 * <p>
 *
 * <tt>List</tt> 接口提供了两种方法，可以在列表中任意位置高效地插入和移除多个元素。
 * <p>
 *
 * 注意：虽然列表中包含自身作为元素是允许的，但强烈建议谨慎处理：在这种情况下，<tt>equals</tt> 和 <tt>hashCode</tt>
 * 方法将不再被良好定义。
 *
 * <p>
 * 某些列表实现对它们可以包含的元素施加限制。例如，一些实现禁止 null 元素，
 * 而另一些实现对元素的类型有限制。
 * 尝试添加不符合要求的元素将抛出未检查异常，通常是 <tt>NullPointerException</tt> 或
 * <tt>ClassCastException</tt>。
 * 试图查询列表中是否包含不符合要求的元素可能抛出异常，或者可能仅返回 false；
 * 某些实现可能表现为前者，而某些实现可能表现为后者。
 * 更一般地，尝试对不符合要求的元素执行操作，且完成操作不会导致将不符合要求的元素插入到列表中时，该操作可能会抛出异常，也可能会成功，具体由实现决定。
 * 这些异常在此接口规范中被标记为“可选的”。
 * <p>
 *
 * 该接口是
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">Java 集合框架</a>
 * 的成员。
 *
 * @param <E> 此列表中元素的类型
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @see Collection
 * @see Set
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Arrays#asList(Object[])
 * @see Collections#nCopies(int, Object)
 * @see Collections#EMPTY_LIST
 * @see AbstractList
 * @see AbstractSequentialList
 * @since 1.2
 */

public interface List<E> extends Collection<E> {
    // 查询操作

    /**
     * 返回此列表中的元素数量。如果此列表包含的元素超过<tt>Integer.MAX_VALUE</tt>个，则返回<tt>Integer.MAX_VALUE</tt>。
     *
     * @return 此列表中的元素数量
     */
    int size();

    /**
     * 如果此列表不包含任何元素，则返回<tt>true</tt>。
     *
     * @return 如果此列表不包含任何元素，则返回<tt>true</tt>
     */
    boolean isEmpty();

    /**
     * 如果此列表包含指定元素，则返回<tt>true</tt>。更正式地说，当且仅当此列表包含至少一个满足<tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>的元素<tt>e</tt>时返回<tt>true</tt>。
     *
     * @param o 要测试是否存在于列表中的元素
     * @return 如果此列表包含指定元素，则返回<tt>true</tt>
     * @throws ClassCastException   如果指定元素的类型与此列表不兼容
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>)
     * @throws NullPointerException 如果指定元素为null且此列表不允许null元素
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>)
     */
    boolean contains(Object o);

    /**
     * 按正确顺序返回此列表中元素的迭代器。
     *
     * @return 按正确顺序遍历此列表元素的迭代器
     */
    Iterator<E> iterator();

    /**
     * 返回包含此列表所有元素的数组（按正确顺序）。
     *
     * <p>
     * 返回的数组将是"安全的"，因为此列表不会维护对它的引用。（换句话说，即使列表由数组支持，此方法也必须分配新数组）。调用者可以自由修改返回的数组。
     *
     * <p>
     * 此方法充当基于数组和基于集合的API之间的桥梁。
     *
     * @return 包含此列表所有元素的数组（按正确顺序）
     * @see Arrays#asList(Object[])
     */
    Object[] toArray();

    /**
     * 返回包含此列表所有元素的数组（按正确顺序）；返回数组的运行时类型是指定数组的类型。如果列表适合指定的数组，则返回该数组。否则，将使用指定数组的运行时类型和此列表的大小分配新数组。
     *
     * <p>
     * 如果列表适合指定数组并有剩余空间（即数组的元素比列表多），则紧跟在列表末尾的数组元素被设置为<tt>null</tt>。（仅在调用者知道列表不包含任何null元素时，这对确定列表长度很有用。）
     *
     * <p>
     * 与{@link #toArray()}方法类似，此方法充当基于数组和基于集合的API之间的桥梁。此外，此方法允许精确控制输出数组的运行时类型，并且在某些情况下可以用于节省分配成本。
     *
     * <p>
     * 假设<tt>x</tt>是仅包含字符串的列表。以下代码可用于将列表转储到新分配的<tt>String</tt>数组中：
     *
     * <pre>{@code
     * String[] y = x.toArray(new String[0]);
     * }</pre>
     *
     * 注意<tt>toArray(new Object[0])</tt>与<tt>toArray()</tt>功能相同。
     *
     * @param a 要存储列表元素的数组（如果足够大）；否则将为此分配具有相同运行时类型的新数组
     * @return 包含列表元素的数组
     * @throws ArrayStoreException  如果指定数组的运行时类型不是此列表每个元素运行时类型的超类型
     * @throws NullPointerException 如果指定数组为null
     */
    <T> T[] toArray(T[] a);

    // 修改操作

    /**
     * 将指定元素追加到此列表的末尾（可选操作）。
     *
     * <p>
     * 支持此操作的列表可能对可以添加的元素施加限制。特别是，某些列表拒绝添加null元素，而其他列表对元素类型施加限制。列表类应在其文档中明确说明可以添加元素的任何限制。
     *
     * @param e 要追加到此列表的元素
     * @return <tt>true</tt>（根据{@link Collection#add}的规定）
     * @throws UnsupportedOperationException 如果此列表不支持<tt>add</tt>操作
     * @throws ClassCastException            如果指定元素的类阻止其添加到此列表
     * @throws NullPointerException          如果指定元素为null且此列表不允许null元素
     * @throws IllegalArgumentException      如果此元素的某些属性阻止其添加到此列表
     */
    boolean add(E e);

    /**
     * 从此列表中移除第一次出现的指定元素（如果存在）（可选操作）。如果列表不包含该元素，则不做更改。更正式地说，移除满足<tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>的最低索引<tt>i</tt>的元素（如果存在）。如果此列表包含指定元素，则返回<tt>true</tt>（或者等效地，如果此列表因调用而更改）。
     *
     * @param o 要从此列表中移除的元素（如果存在）
     * @return 如果此列表包含指定元素，则返回<tt>true</tt>
     * @throws ClassCastException            如果指定元素的类型与此列表不兼容
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>)
     * @throws NullPointerException          如果指定元素为null且此列表不允许null元素
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>)
     * @throws UnsupportedOperationException 如果此列表不支持<tt>remove</tt>操作
     */
    boolean remove(Object o);

    // 批量修改操作

    /**
     * 如果此列表包含指定集合的所有元素，则返回<tt>true</tt>。
     *
     * @param c 要检查是否包含在此列表中的集合
     * @return 如果此列表包含指定集合的所有元素，则返回<tt>true</tt>
     * @throws ClassCastException   如果指定集合中一个或多个元素的类型与此列表不兼容
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>)
     * @throws NullPointerException 如果指定集合包含一个或多个null元素且此列表不允许null元素
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>),
     *                              或者如果指定集合为null
     * @see #contains(Object)
     */
    boolean containsAll(Collection<?> c);

    /**
     * 将指定集合中的所有元素按指定集合迭代器返回的顺序追加到此列表的末尾（可选操作）。如果在操作进行过程中修改了指定集合，则此操作的行为是未定义的。（注意，如果指定集合是此列表且非空，则会发生这种情况。）
     *
     * @param c 包含要添加到此列表的元素的集合
     * @return 如果此列表因调用而更改，则返回<tt>true</tt>
     * @throws UnsupportedOperationException 如果此列表不支持<tt>addAll</tt>操作
     * @throws ClassCastException            如果指定集合中某个元素的类阻止其添加到此列表
     * @throws NullPointerException          如果指定集合包含一个或多个null元素且此列表不允许null元素，或者指定集合为null
     * @throws IllegalArgumentException      如果指定集合中某个元素的某些属性阻止其添加到此列表
     * @see #add(Object)
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * 将指定集合中的所有元素插入此列表的指定位置（可选操作）。将当前位于该位置的元素（如果有）和后续元素右移（增加其索引）。新元素将按指定集合迭代器返回的顺序出现在此列表中。如果在操作进行过程中修改了指定集合，则此操作的行为是未定义的。（注意，如果指定集合是此列表且非空，则会发生这种情况。）
     *
     * @param index 要插入第一个元素的索引
     * @param c     包含要添加到此列表的元素的集合
     * @return 如果此列表因调用而更改，则返回<tt>true</tt>
     * @throws UnsupportedOperationException 如果此列表不支持<tt>addAll</tt>操作
     * @throws ClassCastException            如果指定集合中某个元素的类阻止其添加到此列表
     * @throws NullPointerException          如果指定集合包含一个或多个null元素且此列表不允许null元素，或者指定集合为null
     * @throws IllegalArgumentException      如果指定集合中某个元素的某些属性阻止其添加到此列表
     * @throws IndexOutOfBoundsException     如果索引越界
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    boolean addAll(int index, Collection<? extends E> c);

    /**
     * 移除此列表中包含在指定集合中的所有元素（可选操作）。
     *
     * @param c 包含要从此列表中移除的元素的集合
     * @return 如果此列表因调用而更改，则返回<tt>true</tt>
     * @throws UnsupportedOperationException 如果此列表不支持<tt>removeAll</tt>操作
     * @throws ClassCastException            如果此列表某个元素的类型与指定集合不兼容
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>)
     * @throws NullPointerException          如果此列表包含null元素且指定集合不允许null元素
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>),
     *                                       或者如果指定集合为null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(Collection<?> c);

    /**
     * 仅保留此列表中包含在指定集合中的元素（可选操作）。换句话说，移除此列表中所有未包含在指定集合中的元素。
     *
     * @param c 包含要保留在此列表中的元素的集合
     * @return 如果此列表因调用而更改，则返回<tt>true</tt>
     * @throws UnsupportedOperationException 如果此列表不支持<tt>retainAll</tt>操作
     * @throws ClassCastException            如果此列表某个元素的类型与指定集合不兼容
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>)
     * @throws NullPointerException          如果此列表包含null元素且指定集合不允许null元素
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>),
     *                                       或者如果指定集合为null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(Collection<?> c);

    /**
     * 将列表中的每个元素替换为对该元素应用运算符的结果。运算符抛出的错误或运行时异常会传递给调用者。
     *
     * @implSpec
     *           默认实现等同于对此{@code list}执行以下操作：
     * 
     *           <pre>{@code
     *     final ListIterator<E> li = list.listIterator();
     *     while (li.hasNext()) {
     *         li.set(operator.apply(li.next()));
     *           }
     * }</pre>
     *
     *           如果列表的列表迭代器不支持{@code set}操作，则在替换第一个元素时将抛出{@code UnsupportedOperationException}。
     *
     * @param operator 要应用于每个元素的运算符
     * @throws UnsupportedOperationException 如果此列表不可修改。
     *                                       如果无法替换元素或通常不支持修改，实现可能抛出此异常
     * @throws NullPointerException          如果指定运算符为null，或者运算符结果为null值且此列表不允许null元素
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>)
     * @since 1.8
     */
    default void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        final ListIterator<E> li = this.listIterator();
        while (li.hasNext()) {
            li.set(operator.apply(li.next()));
        }
    }

    /**
     * 根据指定的{@link Comparator}对此列表进行排序。
     *
     * <p>
     * 此列表中的所有元素必须使用指定比较器<i>相互可比较</i>（即对于列表中的任何元素{@code e1}和{@code e2}，{@code c.compare(e1, e2)}不得抛出{@code ClassCastException}）。
     *
     * <p>
     * 如果指定比较器为{@code null}，则此列表中的所有元素必须实现{@link Comparable}接口，并且应使用元素的{@linkplain Comparable
     * 自然顺序}。
     *
     * <p>
     * 此列表必须是可修改的，但无需支持调整大小。
     *
     * @implSpec
     *           默认实现获取包含此列表所有元素的数组，对数组进行排序，然后遍历此列表，从数组的相应位置重置每个元素。（这样可以避免尝试对链表原地排序产生的n²
     *           log(n)性能问题。）
     *
     * @implNote
     *           此实现是一种稳定、自适应、迭代的归并排序，当输入数组部分有序时，需要的比较次数远少于n
     *           lg(n)次，同时在随机排序的输入数组上提供传统归并排序的性能。如果输入数组接近有序，实现需要大约n次比较。临时存储需求从接近有序输入数组的小常量到随机排序输入数组的n/2对象引用。
     *
     *           <p>
     *           该实现充分利用输入数组的升序和降序，并能利用同一输入数组中不同部分的升序和降序。它非常适合合并两个或多个排序数组：只需连接数组并对结果数组排序。
     *
     *           <p>
     *           该实现改编自Tim Peters为Python设计的列表排序（<a href=
     *           "http://svn.python.org/projects/python/trunk/Objects/listsort.txt">TimSort</a>）。它使用了Peter
     *           McIlroy在《第四届ACM-SIAM离散算法研讨会论文集》（1993年1月，第467-474页）中提出的"乐观排序和信息论复杂度"技术。
     *
     * @param c 用于比较列表元素的{@code Comparator}。{@code null}值表示应使用元素的{@linkplain Comparable
     *          自然顺序}
     * @throws ClassCastException            如果列表包含使用指定比较器不<i>相互可比较</i>的元素
     * @throws UnsupportedOperationException 如果列表的列表迭代器不支持{@code set}操作
     * @throws IllegalArgumentException
     *                                       (<a href=
     *                                       "Collection.html#optional-restrictions">可选</a>)
     *                                       如果发现比较器违反了{@link Comparator}约定
     * @since 1.8
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    default void sort(Comparator<? super E> c) {
        Object[] a = this.toArray();
        Arrays.sort(a, (Comparator) c);
        ListIterator<E> i = this.listIterator();
        for (Object e : a) {
            i.next();
            i.set((E) e);
        }
    }

    /**
     * 移除此列表中的所有元素（可选操作）。此调用返回后，列表将为空。
     *
     * @throws UnsupportedOperationException 如果此列表不支持<tt>clear</tt>操作
     */
    void clear();

    // 比较和哈希

    /**
     * 比较指定对象与此列表的相等性。当且仅当指定对象也是一个列表，两个列表大小相同，并且两个列表中所有对应的元素对都<i>相等</i>时返回<tt>true</tt>。（两个元素<tt>e1</tt>和<tt>e2</tt>相等当且仅当<tt>(e1==null ? e2==null : e1.equals(e2))</tt>。）换句话说，如果两个列表包含相同顺序的相同元素，则它们被定义为相等。此定义确保equals方法在<tt>List</tt>接口的不同实现中正常工作。
     *
     * @param o 要与此列表进行相等性比较的对象
     * @return 如果指定对象等于此列表，则返回<tt>true</tt>
     */
    boolean equals(Object o);

    /**
     * 返回此列表的哈希码值。列表的哈希码定义为以下计算的结果：
     * 
     * <pre>{@code
     * int hashCode = 1;
     * for (E e : list)
     *     hashCode = 31 * hashCode + (e == null ? 0 : e.hashCode());
     * }</pre>
     * 
     * 这确保<tt>list1.equals(list2)</tt>意味着<tt>list1.hashCode()==list2.hashCode()</tt>，符合{@link Object#hashCode}的一般约定。
     *
     * @return 此列表的哈希码值
     * @see Object#equals(Object)
     * @see #equals(Object)
     */
    int hashCode();

    // 位置访问操作

    /**
     * 返回此列表中指定位置的元素。
     *
     * @param index 要返回元素的索引
     * @return 此列表中指定位置的元素
     * @throws IndexOutOfBoundsException 如果索引越界
     *                                   (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E get(int index);

    /**
     * 用指定元素替换此列表中指定位置的元素（可选操作）。
     *
     * @param index   要替换元素的索引
     * @param element 要存储在指定位置的元素
     * @return 先前在指定位置的元素
     * @throws UnsupportedOperationException 如果此列表不支持<tt>set</tt>操作
     * @throws ClassCastException            如果指定元素的类阻止其添加到此列表
     * @throws NullPointerException          如果指定元素为null且此列表不允许null元素
     * @throws IllegalArgumentException      如果指定元素的某些属性阻止其添加到此列表
     * @throws IndexOutOfBoundsException     如果索引越界
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E set(int index, E element);

    /**
     * 在此列表的指定位置插入指定的元素（可选操作）。
     * 将当前位于该位置的元素（如果有）及其后续元素向右移动（索引加 1）。
     *
     * @param index   要插入指定元素的位置索引
     * @param element 要插入的元素
     * @throws UnsupportedOperationException 如果此列表不支持 <tt>add</tt> 操作
     * @throws ClassCastException            如果指定元素的类型不允许添加到此列表中
     * @throws NullPointerException          如果指定的元素为 null，且此列表不允许 null 元素
     * @throws IllegalArgumentException      如果指定元素的某些属性不允许将其添加到此列表中
     * @throws IndexOutOfBoundsException     如果索引超出范围
     *                                       (<tt>index &lt; 0 || index &gt; size()</tt>)
     */
    void add(int index, E element);

    /**
     * 移除此列表中指定位置的元素（可选操作）。
     * 将任何后续元素向左移动（索引减 1）。
     * 返回从列表中移除的元素。
     *
     * @param index 要移除的元素所在的索引
     * @return 之前位于指定位置的元素
     * @throws UnsupportedOperationException 如果此列表不支持 <tt>remove</tt> 操作
     * @throws IndexOutOfBoundsException     如果索引超出范围
     *                                       (<tt>index &lt; 0 || index &gt;= size()</tt>)
     */
    E remove(int index);

    /**
     * 返回此列表中指定元素的**第一次出现**的索引，如果列表中不包含该元素，则返回 -1。
     * 更正式地讲，返回满足以下条件的最小索引 <tt>i</tt>：
     * <tt>(o==null ? get(i)==null : o.equals(get(i)))</tt>，
     * 如果没有这样的索引，则返回 -1。
     *
     * @param o 要搜索的元素
     * @return 此列表中第一次出现指定元素的索引，如果列表不包含该元素，则返回 -1
     * @throws ClassCastException   如果指定元素的类型与此列表不兼容
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>)
     * @throws NullPointerException 如果指定的元素为 null，且此列表不允许 null 元素
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>)
     */
    int indexOf(Object o);

    /**
     * 返回此列表中指定元素的**最后一次出现**的索引，如果列表中不包含该元素，则返回 -1。
     * 更正式地讲，返回满足以下条件的最大索引 <tt>i</tt>：
     * <tt>(o==null ? get(i)==null : o.equals(get(i)))</tt>，
     * 如果没有这样的索引，则返回 -1。
     *
     * @param o 要搜索的元素
     * @return 此列表中最后一次出现指定元素的索引，如果列表不包含该元素，则返回 -1
     * @throws ClassCastException   如果指定元素的类型与此列表不兼容
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>)
     * @throws NullPointerException 如果指定的元素为 null，且此列表不允许 null 元素
     *                              (<a href=
     *                              "Collection.html#optional-restrictions">可选</a>)
     */
    int lastIndexOf(Object o);

    /**
     * 返回一个列表迭代器，它按照适当的顺序遍历此列表中的元素。
     *
     * @return 一个按正确顺序遍历此列表元素的列表迭代器
     */
    ListIterator<E> listIterator();

    /**
     * 返回一个从指定位置开始的列表迭代器，它按照适当的顺序遍历此列表中的元素。
     * 指定的索引指示**第一次调用** {@link ListIterator#next next} 时将返回的**第一个元素**。
     * **第一次调用** {@link ListIterator#previous previous} 将返回索引减一的位置上的元素。
     *
     * @param index 迭代器开始遍历时的索引位置
     * @return 一个从指定位置开始按正确顺序遍历此列表的列表迭代器
     * @throws IndexOutOfBoundsException 如果索引超出范围
     *                                   ({@code index < 0 || index > size()})
     */
    ListIterator<E> listIterator(int index);

    /**
     * 返回此列表中指定范围内的子列表视图，从 <tt>fromIndex</tt>（包含）到 <tt>toIndex</tt>（不包含）。
     * （如果 <tt>fromIndex</tt> 和 <tt>toIndex</tt> 相等，则返回一个空列表）。
     * 返回的列表受原列表支持，因此在子列表中的非结构性更改将在原列表中反映出来，反之亦然。
     * 返回的列表支持此列表支持的所有可选操作。
     * <p>
     *
     * 该方法消除了显式的范围操作（通常在数组操作中存在）的需要。
     * 任何期望列表的操作都可以通过传递 `subList` 视图而不是整个列表来进行。
     * 例如，以下代码删除列表中的一段元素：
     * 
     * <pre>{@code
     * list.subList(from, to).clear();
     * }</pre>
     * 
     * 类似的用法也可用于 <tt>indexOf</tt> 和 <tt>lastIndexOf</tt> 方法，
     * 以及 `Collections` 类中的所有算法都可以应用于 `subList` 视图。
     * <p>
     *
     * 如果修改了原始列表（即此列表），而**未通过返回的子列表进行修改**，则该方法返回的列表的行为将变得**未定义**。
     * （结构性修改是指更改列表的大小，或以其他方式影响其结构，从而导致正在进行的迭代可能产生错误的结果。）
     *
     * @param fromIndex 子列表的起始索引（包含）
     * @param toIndex   子列表的结束索引（不包含）
     * @return 该列表中指定范围的视图
     * @throws IndexOutOfBoundsException 如果端点索引值非法
     *                                   (<tt>fromIndex &lt; 0 || toIndex &gt; size || fromIndex &gt; toIndex</tt>)
     */
    List<E> subList(int fromIndex, int toIndex);

    /**
     * 在此列表的元素上创建一个 {@link Spliterator}。
     *
     * <p>
     * 此 {@code Spliterator} 具有 {@link Spliterator#SIZED} 和
     * {@link Spliterator#ORDERED} 特性。
     * 实现类应当记录所报告的其他特性值。
     *
     * @implSpec 默认实现从列表的 {@code Iterator} 创建一个
     *           <em><a href="Spliterator.html#binding">延迟绑定</a></em> 的
     *           `Spliterator`。
     *           该 `Spliterator` 继承了列表迭代器的 <em>快速失败（fail-fast）</em> 特性。
     *
     * @implNote 创建的 {@code Spliterator} 还会报告 {@link Spliterator#SUBSIZED} 特性。
     *
     * @return 一个用于遍历此列表元素的 {@code Spliterator}
     * @since 1.8
     */
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, Spliterator.ORDERED);
    }

}
