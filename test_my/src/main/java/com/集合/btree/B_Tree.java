package com.集合.btree;

import java.util.LinkedList;

import java.util.List;

import java.util.Objects;

public class B_Tree<K extends Comparable<K>, V> {

    public BT_Node<K, V> root;

    public int degree;    //度

    public int number;    //树种结点的数量

    public B_Tree(int degree) {

        if (degree < 2) {

            throw new IllegalArgumentException("degree must >= 2");

        }

        root = null;

        number = 0;

        this.degree = degree;

    }

    /**
     * B树的结点
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    class BT_Node<K extends Comparable<K>, V> {

        BT_Node<K, V> parent;

        /*

         *注：这里的children和pointers应该用定义成数组的（因为链表的增加和删除操作比数组方便，所以引用链表），

         *但是实际上，只有B+树的内部结点才定义了链表

         */

        final List<Entry<K, V>> children;   //存放的键值对链表

        final List<BT_Node<K, V>> pointers;  //子树指针的链表

        /**
         * 除根节点外，每个节点必须有至少t-1个key，t个孩子。树不为空时，根节点至少有一个key。
         * <p>
         * 每个节点至多有2*t-1个key，每个内部节点至多有2*t个孩子。当一个节点有2*t-1个key时，称其为满节点。
         */

        public BT_Node() {

            parent = null;

            children = new LinkedList<Entry<K, V>>();

            pointers = new LinkedList<BT_Node<K, V>>();

        }

        /**
         * 返回键值对的数量
         */

        public int getKeyNum() {

            int keyNum = children.size();

            return keyNum;

        }

        /**
         * 判断当前结点是否已满
         */

        public boolean isFull() {

            return this.getKeyNum() == 2 * degree - 1;

        }

        /**
         * 判断当前结点是否是叶子结点
         */

        public boolean isLeafNode() {

            return this.pointers.size() == 0;

        }

        /**
         * 判断当前结点的键值对数目是否符合约束条件
         */

        public boolean isQualified() {

            int keyNum = this.getKeyNum();

            if (this != root) {

                if (keyNum < (degree - 1)) {

                    throw new IllegalArgumentException("The least keyNum is (degree-1)");

                }

                if (keyNum > (2 * degree - 1)) {

                    throw new IllegalArgumentException("The most keyNum is (2*degree-1)");

                }

            }

            return true;

        }

        @Override

        public String toString() {

            return "BT_Node [children=" + children + "]" + " count:" + this.getKeyNum();

        }

    }


    /**
     * 返回是否为空
     */

    public boolean isEmpty() {

        return size() == 0;

    }

    /**
     * 返回容量大小
     */

    public int size() {

        return number;

    }

    /**
     * 匿名内部类——键值对
     */

    private static class Entry<K extends Comparable<K>, V> {

        K k;

        V v;

        public Entry(K k, V v) {

            this.k = k;

            this.v = v;

        }

        @Override
        public final boolean equals(Object o) {

            if (o == this) {

                return true;
            }


            if (o instanceof Entry) {

                Entry<?, ?> e = (Entry<?, ?>) o;

                if (Objects.equals(k, e.k) && Objects.equals(v, e.v)) {
                    return true;
                }
            }

            return false;

        }

        public K getK() {

            return k;

        }

        public V getV() {

            return v;

        }

        @Override

        public String toString() {

            return "Entry [k=" + k + ",v=" + v + "]";

        }

    }


    /**
     * 查找方法
     * <p>
     * B树的查找和二叉树查找类似，首先在当前节点中查找，如果没有并且存在孩子节点，
     * <p>
     * 就递归的到可能存在该key的孩子节点中查找。
     * <p>
     * 不同的是，B树节点有多个key，需要每个都比较，为了提高性能，可以使用二分法加速节点中的查找。
     */

    public Entry<K, V> search(BT_Node<K, V> x, K key) {

        int i = 0;

        Entry<K, V> entry, next;

        while (i < x.getKeyNum()) {

            entry = x.children.get(i);

            next = (i == x.getKeyNum() - 1) ? null : x.children.get(i + 1);

            //遍历数组，如果数组内的元素包含了目标元素，return

            if (key.equals(entry.getK())) {

                return entry;

            }

            if (key.compareTo(x.children.get(x.getKeyNum() - 1).getK()) > 0) {

                i = x.getKeyNum();

                break;

            } else {

                //如果没有包括，则需要递归寻找

                if (key.compareTo(entry.getK()) < 0) {

                    i = 0;

                    break;

                } else if (key.compareTo(entry.getK()) > 0 && key.compareTo(next.getK()) < 0) {

                    i = i + 1;

                    break;

                }

            }

            i++;

        }

        //i对应的是子树指向的链表的索引位置,用一个“尾递归”

        if (x.isLeafNode()) {

            return null;

        } else {

            return search(x.pointers.get(i), key);

        }

    }

    /**
     * 1、先判断当前节点的Entry是否已满
     * <p>
     * 2、如果已满，就分裂
     * <p>
     * 3、如果结点是叶子结点做插入操作，不是则向下递归
     */

    private void insertAction(BT_Node<K, V> current, Entry<K, V> entry) {

        //先判断root结点是否为空

        if (root == null) {

            root = new BT_Node<K, V>();

            current = root;

        }

        //如果当前结点已满，需要分裂

        if (current.isFull()) {

            //分裂
            current = split(current);

        }

        //如果当前结点是叶子结点

        if (current.isLeafNode()) {

            //插入

            int index = getPosition(current, entry);

            System.out.println(index+","+current.getClass());

            current.children.add(index, entry);

            number++;

            return;

        } else {

            //否则，向下递归寻找

            int index = getPosition(current, entry);

            //System.out.println("向下递归的索引"+index);

            insertAction(current.pointers.get(index), entry);

        }

    }

    public void insert(K key, V value) {

        Entry<K, V> entry = new Entry(key, value);

        insertAction(root, entry);

    }

    /**
     * 该方法用于定位在链表中，新增Entry结点适合插入的位置
     */

    private int getPosition(BT_Node<K, V> x, Entry<K, V> entry) {

        List<Entry<K, V>> list = x.children;

        int index = 0;

        Entry<K, V> ent, next;

        for (int i = 0; i < list.size(); i++) {

            ent = x.children.get(i);

            next = (i == x.getKeyNum() - 1) ? null : x.children.get(i + 1);

            if (entry.k.compareTo(list.get(list.size() - 1).k) > 0) {

                index = list.size();

                break;

            } else {

                //在左边

                if (entry.k.compareTo(ent.k) <= 0) {

                    index = 0;

                    break;

                }

                //在中间

                else if (entry.k.compareTo(next.k) <= 0) {

                    index = i + 1;

                    break;

                }

            }

        }

        return index;

    }

    /**
     * 分裂
     * <p>
     * B树的插入需要考虑的一个问题就是当节点以满时，需要将该节点分裂成两个节点。
     * <p>
     * 一个满的节点有2*t-1个key，内部节点有2*t 个孩子，分裂将其分成两个各有t-1个key，
     * <p>
     * 内部节点各t个孩子，多余的一个节点插入到父节点中，作为分裂之后两个节点的分割key。
     */

    /*

     * 步骤：1、将当前结点的中间Entry结点插入到父节点中，并将剩余的B_TNode拆分

     * 难点：绑定父结点和子结点的关系；

     *       定义pointer指针

     */
    private BT_Node<K, V> split(BT_Node<K, V> x) {

        BT_Node<K, V> parent;

        BT_Node<K, V> left = new BT_Node<K, V>();

        BT_Node<K, V> right = new BT_Node<K, V>();

        int len = x.getKeyNum();

        Entry<K, V> mid = x.children.get(len / 2);

        for (int i = 0; i < len; i++) {

            if (i < len / 2) {

                left.children.add(x.children.get(i));

            }

            if (i > len / 2) {

                right.children.add(x.children.get(i));

            }

        }

        if (x.isLeafNode()) {

            //如果parent为null,说明对根结点做split操作

            if (x == root) {

                parent = new BT_Node<K, V>();

                parent.children.add(mid);

                parent.pointers.add(left);

                parent.pointers.add(right);

                left.parent = parent;

                right.parent = parent;

                root = parent;

                x = root;

            } else {

                parent = x.parent;

                int position = this.getPosition(parent, mid);

                parent.children.add(position, mid);

                parent.pointers.remove(position);

                parent.pointers.add(position, left);

                parent.pointers.add(position + 1, right);

                left.parent = parent;

                right.parent = parent;

                x = parent;

            }

        } else {

            if (x == root) {

                //绑定父节点

                parent = new BT_Node<K, V>();

                parent.children.add(mid);

                parent.pointers.add(left);

                parent.pointers.add(right);

                left.parent = parent;

                right.parent = parent;

                root = parent;

                //绑定子节点

                for (int i = 0; i < x.pointers.size(); i++) {

                    BT_Node<K, V> node = x.pointers.get(i);

                    if (i <= len / 2) {

                        left.pointers.add(node);

                        node.parent = left;

                    } else {

                        right.pointers.add(node);

                        node.parent = right;

                    }

                }

                x = root;

            } else {

                //绑定父节点

                parent = x.parent;

                int position = this.getPosition(parent, mid);

                parent.children.add(position, mid);

                parent.pointers.remove(position);

                parent.pointers.add(position, left);

                parent.pointers.add(position + 1, right);

                left.parent = parent;

                right.parent = parent;

                //绑定子节点

                for (int i = 0; i < x.pointers.size(); i++) {

                    BT_Node<K, V> node = x.pointers.get(i);

                    if (i <= len / 2) {

                        left.pointers.add(node);

                        node.parent = left;

                    } else {

                        right.pointers.add(node);

                        node.parent = right;

                    }

                }

                x = parent;

            }

        }

        return x;

    }

    /**
     * 删除
     */

    public Entry remove(K key) {

        Entry<K, V> entry = search(root, key);

        if (entry == null) {

            return null;

        }

        return removeAction(root, entry);

    }

    /**
     * 删除动作
     * <p>
     * 第一步：先判断根节点是否只有一个元素，并且根节点的左右子节点的keyNum均等于Min。如果是merge,否则到第二步
     * <p>
     * 第二步：通过key，定位到Entry在当前结点的索引位，并根据该索引位得到左子结点和右子节点
     * <p>
     * 例如： C    G    N     --------- BT_Node1
     * <p>
     * AB   DE   JK  OP   --------- BT_Node2
     * <p>
     * 在BT_Node1中，通过key(G)找到Entry G所在的位置为1，然后得到G的左右子结点分别为：DE(左) 和 JK(右)
     * <p>
     * <p>
     * <p>
     * 第三步：如果待删除的结点是父节点，判断当前结点的左子结点和右子结点
     * <p>
     * case1：如果左和右子节点的keyNum均大于最小数Min(degree-1),那么取出左子树最大元素替换待删除元素
     * <p>
     * case2: 如果左子结点的KeyNum>Min, 但是右子结点的keyNum =Min,取出左子树的最大元素替换待删除元素
     * <p>
     * case3: 如果左子结点的KeyNum=Min, 但是右子结点的keyNum > Min,取出右子树点的最小元素替换待删除元素
     * <p>
     * case4: 如果左和右子节点的keyNum均等于Min, 那么做merge操作
     * <p>
     * 第四步：递归到叶子结点，如果叶子结点的keyNum>Min，则直接删除，反之需要做merge操作
     */

    private Entry<K, V> removeAction(BT_Node<K, V> parent, Entry<K, V> entry) {

        BT_Node<K, V> left, right;

        Entry<K, V> replace;

        int index;

        if (parent == root && parent.getKeyNum() == 1) {

            left = parent.pointers.get(0);

            right = parent.pointers.get(1);

            if (left.getKeyNum() == degree - 1 && right.getKeyNum() == degree - 1) {

                parent = merge(root, entry, 0);

            }

        }

        //判断是否是叶子结点，如果是叶子结点且keyNum>Min,则直接删除

        //如果keyNum=Min,需要对其父结点做merge操作

        if (parent.isLeafNode()) {

            if (parent.getKeyNum() > degree - 1) {

                parent.children.remove(entry);

            } else {

                BT_Node<K, V> grand = parent.parent;

                index = grand.pointers.indexOf(parent);

                System.out.println("index is " + index);

                Entry<K, V> ent = grand.children.get(index);

                grand = merge(grand, ent, index);

                parent = grand == root ? root : grand.pointers.get(index);

                parent.children.remove(entry);

            }

            return entry;

        }

        //判断parent是否包含entry

        if (!parent.isLeafNode() && parent.children.contains(entry)) {

            index = parent.children.indexOf(entry);

            //4中不同的条件

            left = parent.pointers.get(index);

            right = parent.pointers.get(index + 1);

            if (left.getKeyNum() > degree - 1 && right.getKeyNum() >= degree - 1) {

                replace = findLeftReplaceEntry(left);

                parent.children.set(index, replace);

                left.children.remove(replace);

                return entry;

            } else if (left.getKeyNum() == degree - 1 && right.getKeyNum() > degree - 1) {

                replace = findRightReplaceEntry(right);

                parent.children.set(index, replace);

                right.children.remove(replace);

                return entry;

            } else {

                parent = merge(parent, entry, index);

            }

        }

        index = getPosition(parent, entry);

        return removeAction(parent.pointers.get(index), entry);

    }

    /**
     * 向左子树遍历，找左子树中最大的元素
     */

    private Entry<K, V> findLeftReplaceEntry(BT_Node<K, V> node) {

        BT_Node<K, V> current = node;

        int keyNum;

        while (!current.isLeafNode()) {

            keyNum = current.getKeyNum();

            current = current.pointers.get(keyNum);

        }

        return current.children.get(current.getKeyNum() - 1);

    }

    /**
     * 向右子树遍历，找右子树中最小的元素
     */

    private Entry<K, V> findRightReplaceEntry(BT_Node<K, V> node) {

        BT_Node<K, V> current = node;

        while (!current.isLeafNode()) {

            current = current.pointers.get(0);

        }

        return current.children.get(0);

    }

    /**
     * merge方法：entry会从parent中移动到merge后的新节点当中
     * <p>
     * 其中parent为当前结点，entry是待删除的元素，index是entry在parent中的索引位置
     * <p>
     * 例如：      C G L
     * <p>
     * /  / \ \
     * <p>
     * AB  DE JK NO
     * <p>
     * 对DE和JK做merge操作，结果：
     * <p>
     * 结果：   C   L
     * <p>
     * /  /  \
     * <p>
     * AB DEGJK NO
     */

    private BT_Node<K, V> merge(BT_Node<K, V> parent, Entry<K, V> entry, int index) {

        BT_Node<K, V> node;

        BT_Node<K, V> left = parent.pointers.get(index);

        BT_Node<K, V> right = parent.pointers.get(index + 1);

        if (parent == root) {

            //创建一个新的BT_Node

            node = new BT_Node<K, V>();

            //绑定children键值对

            node.children.addAll(left.children);

            node.children.add(entry);

            node.children.addAll(right.children);

            //如果左右结点不是叶子结点

            if (!left.isLeafNode() && !right.isLeafNode()) {

                //绑定pointers指针引用

                node.pointers.addAll(left.pointers);

                node.pointers.addAll(right.pointers);

                //重新定义父子关系

                for (int i = 0; i < left.getKeyNum(); i++) {

                    left.pointers.get(i).parent = node;

                }

                for (int i = 0; i < right.getKeyNum(); i++) {

                    right.pointers.get(i).parent = node;

                }

            }

            //干掉所有的引用

            parent = null;

            left = null;

            right = null;

            //重新赋值root

            root = node;

            return root;

        } else {

            node = new BT_Node<K, V>();

            //绑定children键值对

            node.children.addAll(left.children);

            node.children.add(entry);

            node.children.addAll(right.children);

            //如果左右不是叶子结点

            if (!left.isLeafNode() && !right.isLeafNode()) {

                //绑定pointers指针引用

                node.pointers.addAll(left.pointers);

                node.pointers.addAll(right.pointers);

                //重新定义父子关系

                for (int i = 0; i < left.getKeyNum(); i++) {

                    left.pointers.get(i).parent = node;

                }

                for (int i = 0; i < right.getKeyNum(); i++) {

                    right.pointers.get(i).parent = node;

                }

            }

            //删除parent中的entry

            parent.children.remove(entry);

            parent.pointers.remove(left);

            parent.pointers.remove(right);

            //重新赋值parent

            parent.pointers.add(index, node);

            return parent;

        }

    }

    /**
     * 树的遍历
     * <p>
     * 先遍历本节点的键值对元素，然后再递归遍历pointer指针里的结点元素
     */

    public void frontIterator(BT_Node<K, V> node) {

        if (node.isLeafNode()) {

            print(node);

            return;

        } else {

            print(node);

            for (int i = 0; i < node.pointers.size(); i++) {

                frontIterator(node.pointers.get(i));

            }

        }

    }

    /**
     * 打印BT_Node结点
     */

    public void print(BT_Node<K, V> node) {

        System.out.println(node);

    }

}
