package com.example.learning.demo.java.core.collection;

import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Predicate;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * 集合层次结构的根接口。一个集合表示一组对象，这些对象称为其“元素”。有的集合允许重复元素，有的不允许；有的集合是有序的，有的则无序。JDK
 * 并不直接提供此接口的实现，而是提供了更具体的子接口（如 <tt>Set</tt> 和
 * <tt>List</tt>）的实现。此接口通常用于传递集合或对集合进行操作，以达到最大的通用性。
 *
 * <p>
 * <i>Bag</i> 或 <i>multiset</i>（允许重复元素的无序集合）应直接实现此接口。
 *
 * <p>
 * 所有通用的 <tt>Collection</tt> 实现类（通常是通过其子接口间接实现
 * <tt>Collection</tt>）都应提供两种“标准”构造器：一种无参数构造器，用于创建空集合；一种接受单个 <tt>Collection</tt>
 * 类型参数的构造器，用于创建与参数集合拥有相同元素的新集合。实际上，后者构造器允许用户复制任意集合，从而产生所需实现类型的等效集合。由于接口中不能包含构造器，所以无法强制这一约定，但
 * Java 平台库中的所有通用 <tt>Collection</tt> 实现都遵守这一约定。
 *
 * <p>
 * 此接口中包含的那些“破坏性”方法（即修改其操作集合的方法），如果集合不支持该操作，都将抛出
 * <tt>UnsupportedOperationException</tt>。在这种情况下，这些方法可以（但不要求）在调用不会对集合产生影响时抛出
 * <tt>UnsupportedOperationException</tt>。例如，在一个不可修改的集合上调用
 * {@link #addAll(Collection)} 方法时，如果要添加的集合为空，可能（但不要求）抛出该异常。
 *
 * <p>
 * <a name="optional-restrictions">
 * 某些集合实现对它们可以包含的元素有所限制。</a> 例如，某些实现禁止 null
 * 元素，另一些则对元素类型有所限制。尝试添加不合适的元素会抛出未经检查的异常，通常为 <tt>NullPointerException</tt> 或
 * <tt>ClassCastException</tt>。查询不合适元素的存在性可能会抛出异常，也可能简单返回
 * false；不同实现可能表现不同。更一般地说，对一个不合适的元素进行某些操作，其结果不会导致该元素被插入集合中，这时可能会抛出异常，也可能成功，具体取决于实现者的选择。这类异常在接口规范中被标记为“可选”。
 *
 * <p>
 * 每个集合需要自行确定同步策略。如果实现没有提供更强的保证，那么当集合正被另一个线程修改时，
 * 无论是直接调用、将集合传给可能进行调用的方法，还是使用现有迭代器检查集合，都可能导致未定义的行为。
 *
 * <p>
 * 集合框架接口中的许多方法都是基于 {@link Object#equals(Object) equals} 方法来定义的。例如，
 * {@link #contains(Object) contains(Object o)} 方法的规范中写道：“当且仅当此集合至少包含一个元素
 * <tt>e</tt>，
 * 使得 <tt>(o==null ? e==null : o.equals(e))</tt> 时返回
 * <tt>true</tt>。”这一规范不应被解释为：对一个非 null 的参数调用
 * <tt>contains</tt> 会对集合中每个元素调用 <tt>o.equals(e)</tt>。实现者可以采用优化措施，比如先比较哈希码，
 * 从而避免调用 <tt>equals</tt>。（{@link Object#hashCode()}
 * 的规范保证两个哈希码不相等的对象不可能相等。）更一般地，
 * 各集合框架接口的实现者可在认为合适的地方充分利用底层 {@link Object} 方法的规范行为。
 *
 * <p>
 * 某些对集合执行递归遍历的操作可能会在自引用实例上失败并抛出异常（例如，当集合直接或间接包含自身时）。
 * 这包括 {@code clone()}、{@code equals()}、{@code hashCode()} 和 {@code toString()}
 * 方法。实现者可以选择性地处理自引用情况，
 * 但大多数当前实现都不处理。
 *
 * <p>
 * 此接口是
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java 集合框架</a> 的一部分。
 *
 * @implSpec
 *           默认的方法实现（无论是继承的还是其他的）不应用任何同步协议。如果一个 {@code Collection} 实现具有特定的同步协议，
 *           则必须重写默认实现以应用该协议。
 *
 * @param <E> 此集合中元素的类型
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @see Set
 * @see List
 * @see Map
 * @see SortedSet
 * @see SortedMap
 * @see HashSet
 * @see TreeSet
 * @see ArrayList
 * @see LinkedList
 * @see Vector
 * @see Collections
 * @see Arrays
 * @see AbstractCollection
 * @since 1.2
 */
public interface Collection<E> extends java.util.Collection<E> {
    // 查询操作

    /**
     * 返回此集合中的元素个数。如果此集合包含的元素个数超过 <tt>Integer.MAX_VALUE</tt>，
     * 则返回 <tt>Integer.MAX_VALUE</tt>。
     *
     * @return 此集合中的元素个数
     */
    int size();

    /**
     * 如果此集合不包含任何元素，则返回 <tt>true</tt>。
     *
     * @return 如果此集合为空，则返回 <tt>true</tt>
     */
    boolean isEmpty();

    /**
     * 如果此集合包含指定元素，则返回 <tt>true</tt>。更正式地说，
     * 当且仅当此集合至少包含一个元素 <tt>e</tt>，使得
     * <tt>(o==null ? e==null : o.equals(e))</tt> 时返回 <tt>true</tt>。
     *
     * @param o 要检测其是否存在于此集合中的元素
     * @return 如果此集合包含指定元素，则返回 <tt>true</tt>
     * @throws ClassCastException   如果指定元素的类型与此集合不兼容
     *                              (<a href="#optional-restrictions">可选限制</a>)
     * @throws NullPointerException 如果指定元素为 null 而此集合不允许 null 元素
     *                              (<a href="#optional-restrictions">可选限制</a>)
     */
    boolean contains(Object o);

    /**
     * 返回一个遍历此集合中所有元素的迭代器。对于元素的返回顺序没有任何保证
     * （除非此集合是某个提供顺序保证的类的实例）。
     *
     * @return 用于遍历此集合中所有元素的 <tt>Iterator</tt>
     */
    Iterator<E> iterator();

    /**
     * 返回一个包含此集合中所有元素的数组。如果此集合对其元素迭代器返回的顺序有任何保证，
     * 则该方法必须以相同的顺序返回这些元素。
     *
     * <p>
     * 返回的数组是“安全的”，即此集合不会保留对该数组的引用。
     * （换句话说，即使此集合是以数组为后盾，也必须分配一个新的数组。）
     * 因此，调用者可以自由修改返回的数组。
     *
     * <p>
     * 该方法充当了基于数组和基于集合 API 之间的桥梁。
     *
     * @return 一个包含此集合中所有元素的数组
     */
    Object[] toArray();

    /**
     * 返回一个包含此集合中所有元素的数组；返回数组的运行时类型与指定数组的运行时类型相同。
     * 如果此集合能够适应指定的数组，则返回该数组；否则，将为此目的分配一个具有相同运行时类型的新数组，
     * 大小与此集合相同。
     *
     * <p>
     * 如果此集合能够适应指定的数组，并且该数组有多余空间
     * （即数组长度大于此集合中的元素个数），则在集合末尾后面的第一个数组元素将被设置为 <tt>null</tt>。
     * （这在调用者确定此集合不包含任何 <tt>null</tt> 元素时有助于确定集合的实际大小。）
     *
     * <p>
     * 如果此集合对其元素迭代器返回的顺序有任何保证，则该方法必须以相同的顺序返回这些元素。
     *
     * <p>
     * 与 {@link #toArray()} 方法类似，该方法充当了基于数组和基于集合 API 之间的桥梁。
     * 此外，该方法允许精确控制输出数组的运行时类型，并且在某些情况下可以节省分配成本。
     *
     * <p>
     * 假设 <tt>x</tt> 是一个仅包含字符串的集合，下面的代码可以将该集合的内容导出到一个新分配的 <tt>String</tt> 数组中：
     *
     * <pre>
     * String[] y = x.toArray(new String[0]);
     * </pre>
     *
     * 注意，<tt>toArray(new Object[0])</tt> 的功能与 <tt>toArray()</tt> 相同。
     *
     * @param <T> 用于存放集合中元素的数组的运行时类型
     * @param a   用于存放此集合中元素的数组；如果该数组足够大，则直接使用，否则将分配一个具有相同运行时类型的新数组
     * @return 一个包含此集合中所有元素的数组
     * @throws ArrayStoreException  如果指定数组的运行时类型不是此集合中每个元素运行时类型的超类型
     * @throws NullPointerException 如果指定的数组为 null
     */
    <T> T[] toArray(T[] a);

    // 修改操作

    /**
     * 确保此集合包含指定元素（可选操作）。如果此调用使集合发生了变化，则返回 <tt>true</tt>。
     * （如果此集合不允许重复且已经包含指定元素，则返回 <tt>false</tt>。）
     * <p>
     *
     * 支持此操作的集合可能对可添加的元素施加限制。特别是，
     * 某些集合会拒绝添加 <tt>null</tt> 元素，而另一些则会对可添加元素的类型有所限制。
     * 集合类应在其文档中明确说明对可添加元素的任何限制。
     * <p>
     *
     * 如果集合因除已包含指定元素之外的任何原因拒绝添加某个元素，
     * 它 <i>必须</i> 抛出异常（而不是返回 <tt>false</tt>）。这保证了在调用返回后，
     * 集合总是包含指定元素的不变性。
     *
     * @param e 要确保存在于此集合中的元素
     * @return 如果此调用导致集合发生变化，则返回 <tt>true</tt>
     * @throws UnsupportedOperationException 如果此集合不支持 <tt>add</tt> 操作
     * @throws ClassCastException            如果指定元素的类阻止其被添加到此集合中
     * @throws NullPointerException          如果指定元素为 null 且此集合不允许 null 元素
     * @throws IllegalArgumentException      如果元素的某个属性阻止其被添加到此集合中
     * @throws IllegalStateException         如果由于插入限制，此时无法添加该元素
     */
    boolean add(E e);

    /**
     * 从此集合中移除指定元素的单个实例（可选操作）。
     * 更正式地说，移除一个满足 <tt>(o==null ? e==null : o.equals(e))</tt> 的元素，
     * 如果此集合包含一个或多个这样的元素，则返回 <tt>true</tt>（或等价地，
     * 如果此调用导致集合发生变化，则返回 <tt>true</tt>）。
     *
     * @param o 要从此集合中移除的元素（如果存在）
     * @return 如果由于此调用而移除了某个元素，则返回 <tt>true</tt>
     * @throws ClassCastException            如果指定元素的类型与此集合不兼容
     *                                       (<a href=
     *                                       "#optional-restrictions">可选限制</a>)
     * @throws NullPointerException          如果指定元素为 null 而此集合不允许 null 元素
     *                                       (<a href=
     *                                       "#optional-restrictions">可选限制</a>)
     * @throws UnsupportedOperationException 如果此集合不支持 <tt>remove</tt> 操作
     */
    boolean remove(Object o);

    // 批量操作

    /**
     * 如果此集合包含指定集合中的所有元素，则返回 <tt>true</tt>。
     *
     * @param c 要检查是否全部包含于此集合中的集合
     * @return 如果此集合包含指定集合中的所有元素，则返回 <tt>true</tt>
     * @throws ClassCastException   如果指定集合中某个或某些元素的类型与此集合不兼容
     *                              (<a href="#optional-restrictions">可选限制</a>)
     * @throws NullPointerException 如果指定集合包含一个或多个 null 元素且此集合不允许 null 元素
     *                              (<a href=
     *                              "#optional-restrictions">可选限制</a>)，或者指定集合为 null
     * @see #contains(Object)
     */
    boolean containsAll(Collection<?> c);

    /**
     * 将指定集合中的所有元素添加到此集合中（可选操作）。
     * 如果在操作过程中指定集合被修改，则此操作的行为未定义。
     * （这意味着如果指定集合就是此集合且此集合非空，则此调用的行为未定义。）
     *
     * @param c 包含要添加到此集合中的元素的集合
     * @return 如果此调用导致集合发生变化，则返回 <tt>true</tt>
     * @throws UnsupportedOperationException 如果此集合不支持 <tt>addAll</tt> 操作
     * @throws ClassCastException            如果指定集合中某个元素的类阻止其被添加到此集合中
     * @throws NullPointerException          如果指定集合包含 null 元素且此集合不允许 null 元素，
     *                                       或者指定集合为 null
     * @throws IllegalArgumentException      如果指定集合中某个元素的某个属性阻止其被添加到此集合中
     * @throws IllegalStateException         如果由于插入限制，此时无法添加所有元素
     * @see #add(Object)
     */
    boolean addAll(Collection<? extends E> c);

    /**
     * 从此集合中移除也包含在指定集合中的所有元素（可选操作）。
     * 调用此方法返回后，此集合将不包含与指定集合相同的任何元素。
     *
     * @param c 包含要从此集合中移除的元素的集合
     * @return 如果此调用导致集合发生变化，则返回 <tt>true</tt>
     * @throws UnsupportedOperationException 如果此集合不支持 <tt>removeAll</tt> 操作
     * @throws ClassCastException            如果此集合中某个或某些元素的类型与指定集合不兼容
     *                                       (<a href=
     *                                       "#optional-restrictions">可选限制</a>)
     * @throws NullPointerException          如果此集合包含一个或多个 null 元素且指定集合不允许 null 元素
     *                                       (<a href=
     *                                       "#optional-restrictions">可选限制</a>),
     *                                       或者指定集合为 null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean removeAll(Collection<?> c);

    /**
     * 移除此集合中所有满足给定谓词的元素。遍历过程中或谓词中抛出的错误或运行时异常会传递给调用者。
     *
     * @implSpec
     *           默认实现使用 {@link #iterator} 遍历集合中的所有元素，对每个匹配的元素调用
     *           {@link Iterator#remove()} 进行移除。
     *           如果集合的迭代器不支持移除，则在遇到第一个匹配元素时会抛出
     *           {@code UnsupportedOperationException}。
     *
     * @param filter 返回 {@code true} 的谓词用于确定要移除的元素
     * @return 如果移除了任一元素，则返回 {@code true}
     * @throws NullPointerException          如果指定的过滤器为 null
     * @throws UnsupportedOperationException 如果此集合不支持移除操作，
     *                                       或者遇到不能移除的匹配元素时抛出该异常
     * @since 1.8
     */
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

    /**
     * 仅保留此集合中同时也包含在指定集合中的元素（可选操作）。
     * 换句话说，从此集合中移除所有不包含在指定集合中的元素。
     *
     * @param c 包含要在此集合中保留的元素的集合
     * @return 如果此调用导致集合发生变化，则返回 <tt>true</tt>
     * @throws UnsupportedOperationException 如果此集合不支持 <tt>retainAll</tt> 操作
     * @throws ClassCastException            如果此集合中某个或某些元素的类型与指定集合不兼容
     *                                       (<a href=
     *                                       "#optional-restrictions">可选限制</a>)
     * @throws NullPointerException          如果此集合包含一个或多个 null 元素且指定集合不允许 null 元素
     *                                       (<a href=
     *                                       "#optional-restrictions">可选限制</a>),
     *                                       或者指定集合为 null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    boolean retainAll(Collection<?> c);

    /**
     * 移除此集合中的所有元素（可选操作）。调用此方法返回后，此集合将为空。
     *
     * @throws UnsupportedOperationException 如果此集合不支持 <tt>clear</tt> 操作
     */
    void clear();

    // 比较和哈希

    /**
     * 将指定对象与此集合进行相等性比较。
     * <p>
     *
     * 尽管 <tt>Collection</tt> 接口对 <tt>Object.equals</tt> 的通用约定没有额外规定，
     * 但直接实现 <tt>Collection</tt> 接口的程序员（即创建一个实现了 <tt>Collection</tt> 而不是
     * <tt>List</tt> 或 <tt>Set</tt> 的类）
     * 如果选择重写 <tt>Object.equals</tt> 方法，必须谨慎。最简单的方式是不重写，而依赖于 <tt>Object</tt> 的实现，
     * 但实现者也可能希望实现“值比较”而非默认的“引用比较”。（<tt>List</tt> 和 <tt>Set</tt> 接口要求进行值比较。）
     * <p>
     *
     * <tt>Object.equals</tt> 方法的一般合同规定相等性必须是对称的（即 <tt>a.equals(b)</tt> 当且仅当
     * <tt>b.equals(a)</tt>）。
     * <tt>List.equals</tt> 和 <tt>Set.equals</tt> 的合同规定列表只与其他列表相等，而集合只与其他集合相等。
     * 因此，一个既不实现 <tt>List</tt> 也不实现 <tt>Set</tt> 接口的集合类，如果自定义了 <tt>equals</tt> 方法，
     * 则在与任何列表或集合比较时都必须返回 <tt>false</tt>。
     * （同样地，无法编写一个既正确实现 <tt>Set</tt> 又正确实现 <tt>List</tt> 接口的类。）
     *
     * @param o 要与此集合比较相等性的对象
     * @return 如果指定对象与此集合相等，则返回 <tt>true</tt>
     *
     * @see Object#equals(Object)
     * @see Set#equals(Object)
     * @see List#equals(Object)
     */
    boolean equals(Object o);

    /**
     * 返回此集合的哈希码值。虽然 <tt>Collection</tt> 接口对 <tt>Object.hashCode</tt> 方法没有额外规定，
     * 但程序员应注意，任何重写 <tt>Object.equals</tt> 方法的类都必须重写 <tt>Object.hashCode</tt> 方法，
     * 以满足 <tt>Object.hashCode</tt> 方法的通用合同。特别地，<tt>c1.equals(c2)</tt> 意味着
     * <tt>c1.hashCode() == c2.hashCode()</tt>。
     *
     * @return 此集合的哈希码值
     *
     * @see Object#hashCode()
     * @see Object#equals(Object)
     */
    int hashCode();

    /**
     * 创建一个用于遍历此集合中所有元素的 {@link Spliterator}。
     *
     * 实现者应记录该 spliterator 所报告的特性值。如果该 spliterator 报告了 {@link Spliterator#SIZED}
     * 且此集合为空，
     * 则不要求报告其他特性值。
     *
     * <p>
     * 默认实现应被能返回更高效 spliterator 的子类重写。为了保持 {@link #stream()} 和
     * {@link #parallelStream()} 方法
     * 的预期惰性行为，spliterator 应该具有 {@code IMMUTABLE} 或 {@code CONCURRENT} 特性，或为
     * <em><a href="Spliterator.html#binding">后绑定</a></em>。如果都不切实际，重写的类应描述该
     * spliterator 的绑定策略和结构干扰情况，
     * 并应重写 {@link #stream()} 和 {@link #parallelStream()} 方法，使用 spliterator 的
     * {@code Supplier} 来创建流，例如：
     * 
     * <pre>{@code
     *     Stream<E> s = StreamSupport.stream(() -> spliterator(), spliteratorCharacteristics)
     * }</pre>
     * <p>
     * 这些要求确保由 {@link #stream()} 和 {@link #parallelStream()}
     * 方法生成的流能够反映在终端流操作开始时集合的内容。
     *
     * @implSpec
     *           默认实现通过此集合的 {@code Iterator} 创建一个
     *           <em><a href="Spliterator.html#binding">后绑定</a></em> spliterator，
     *           并继承集合迭代器的 <em>快速失败</em> 特性。
     *           <p>
     *           创建的 {@code Spliterator} 报告 {@link Spliterator#SIZED}。
     *
     * @implNote
     *           创建的 {@code Spliterator} 还额外报告 {@link Spliterator#SUBSIZED}。
     *
     *           <p>
     *           如果一个 spliterator 覆盖了零个元素，那么除了报告 {@code SIZED} 和 {@code SUBSIZED} 外，
     *           报告其他特性值并不能帮助调用者控制、专门化或简化计算；不过这确实允许空集合共享使用一个不可变且空的 spliterator 实例
     *           （参见 {@link Spliterators#emptySpliterator()}），并允许调用者判断该 spliterator
     *           是否覆盖了零个元素。
     *
     * @return 一个用于遍历此集合中所有元素的 {@code Spliterator}
     * @since 1.8
     */
    @Override
    default Spliterator<E> spliterator() {
        return Spliterators.spliterator(this, 0);
    }

    /**
     * 返回一个以此集合为数据源的顺序 {@code Stream}。
     *
     * <p>
     * 当 {@link #spliterator()} 方法不能返回具有 {@code IMMUTABLE}、
     * {@code CONCURRENT} 或 <em>后绑定</em> 特性的 spliterator 时，
     * 应重写此方法。（详情见 {@link #spliterator()}。）
     *
     * @implSpec
     *           默认实现通过此集合的 {@code Spliterator} 创建一个顺序 {@code Stream}。
     *
     * @return 一个以此集合为数据源的顺序 {@code Stream}
     * @since 1.8
     */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    /**
     * 返回一个以此集合为数据源的可能并行的 {@code Stream}。此方法允许返回一个顺序流。
     *
     * <p>
     * 当 {@link #spliterator()} 方法不能返回具有 {@code IMMUTABLE}、
     * {@code CONCURRENT} 或 <em>后绑定</em> 特性的 spliterator 时，
     * 应重写此方法。（详情见 {@link #spliterator()}。）
     *
     * @implSpec
     *           默认实现通过此集合的 {@code Spliterator} 创建一个并行 {@code Stream}。
     *
     * @return 一个以此集合为数据源的可能并行的 {@code Stream}
     * @since 1.8
     */
    default Stream<E> parallelStream() {
        return StreamSupport.stream(spliterator(), true);
    }
}
