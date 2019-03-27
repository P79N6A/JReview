## 集合：
**collection框架有自己的接口和实现，主要分为Set接口，List接口和Queue接口。它们有各自的特点，Set的集合里不允许对象有重复的值，
List允许有重复，它对集合中的对象进行索引，Queue的工作原理是FCFS算法(First Come, First Serve)。**

**1、List 和 Set 和 Map  区别：**

    list和set两个都是collections接口的子接口，
        list：
            元素有放入顺序，大小无序，可重复，但是保证。
            list可以使用for便利和迭代器。
            和数组类似，List可以动态增长，查找元素效率高，插入删除元素效率低，因为会引起其他元素位置改变。
        set：
            元素无放入顺序，元素不可重复，重复元素会覆盖掉，
            比如hashset是通过每次计算hash值进行插入的，自然是有顺序的。（hashset，treeset）。
            set只可以进行迭代器便利。
            检索元素效率低下，删除和插入效率高，插入和删除不会引起元素位置改变。
    map是双列集合：Map中存储的数据是没有顺序的，键不能重复，值是可以有重复的。
**2、Arraylist 与 LinkedList 区别：**

    1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。 （LinkedList是双向链表，有next也有previous）
    2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。 
    3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。 
    
**3、HashMap和Hashtable的区别：**
    
    1、HashMap几乎可以等价于Hashtable，除了HashMap是非synchronized的，
        并可以接受null(HashMap可以接受为null的键值(key)和值(value)，而Hashtable则不行)。
    2、HashMap是非synchronized，而Hashtable是synchronized，这意味着Hashtable是线程安全的，
        多个线程可以共享一个Hashtable；而如果没有正确的同步的话，多个线程是不能共享HashMap的。
        Java 5提供了ConcurrentHashMap，它是HashTable的替代，比HashTable的扩展性更好。
    3、另一个区别是HashMap的迭代器(Iterator)是fail-fast迭代器，而Hashtable的enumerator迭代器不是fail-fast的。
        所以当有其它线程改变了HashMap的结构（增加或者移除元素），将会抛出ConcurrentModificationException，
        但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常。
        但这并不是一个一定发生的行为，要看JVM。这条同样也是Enumeration和Iterator的区别。
    4、由于Hashtable是线程安全的也是synchronized，所以在单线程环境下它比HashMap要慢。
        如果你不需要同步，只需要单一线程，那么使用HashMap性能要好过Hashtable。
    5、HashMap不能保证随着时间的推移Map中的元素次序是不变的。
**4、HashSet 和 HashMap 区别：**

    单列和双列集合：
    (1)接口不一样，一个实现set，一个实现map.
    (2)结构不一样，一个存键值对，一个存对象。
    (3)一个值可以重复，一个值不可以重复
    
**5、HashMap 和 ConcurrentHashMap 的区别：**

    线程安全和不安全的比较：
        ConcurrentHashMap比hashmap多采用了一个分段所来维护线程安全的，简单理解就是，ConcurrentHashMap 是一个 Segment 数组，
        Segment 通过继承 ReentrantLock 来进行加锁，所以每次需要加锁的操作锁住的是一个 segment，这样只要保证每个 Segment 是线程安全的，
        也就实现了全局的线程安全。
        
**6、HashMap 的工作原理及代码实现：**

    HashMap由数组+链表组成的，数组是HashMap的主体，链表则是主要为了解决哈希冲突而存在的，如果定位到的数组位置不含链表（当前entry的next指向null）,
    那么对于查找，添加等操作很快，仅需一次寻址即可；如果定位到的数组包含链表，对于添加操作，其时间复杂度为O(n)，首先遍历链表，存在即覆盖，否则新增；
    对于查找操作来讲，仍需遍历链表，然后通过key对象的equals方法逐一比对查找。所以，性能考虑，HashMap中的链表出现越少，性能才会越好。
    
**7、ConcurrentHashMap 的工作原理及代码实现：**

    ConcurrentHashMap采用了非常精妙的"分段锁"策略，ConcurrentHashMap的主干是个Segment数组。
    Segment继承了ReentrantLock，所以它就是一种可重入锁（ReentrantLock)。在ConcurrentHashMap，一个Segment就是一个子哈希表，
    Segment里维护了一个HashEntry数组，并发环境下，对于不同Segment的数据进行操作是不用考虑锁竞争的。
    所以，对于同一个Segment的操作才需考虑线程同步，不同的Segment则无需考虑。
    HashEntry是目前我们提到的最小的逻辑处理单元了。一个ConcurrentHashMap维护一个Segment数组，一个Segment维护一个HashEntry数组。
