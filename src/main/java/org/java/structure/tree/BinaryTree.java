package org.java.structure.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 二叉树
 * Description:
 * @author zpp
 * @date   2018年5月22日
 */
public class BinaryTree {

    public BinaryTree left; //左节点

    public BinaryTree right; //右节点

    public String data;  //树的内容

    public BinaryTree() {
    }

    /**
     * 构造方法
     *
     * @param data
     * @param left
     * @param right
     */
    public BinaryTree(String data, BinaryTree left, BinaryTree right) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    /**
     * 构造方法
     *
     * @param data
     */
    public BinaryTree(String data) {
        this(data, null, null);
    }

	public BinaryTree getLeft() {
		return left;
	}

	public void setLeft(BinaryTree left) {
		this.left = left;
	}

	public BinaryTree getRight() {
		return right;
	}

	public void setRight(BinaryTree right) {
		this.right = right;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
    
	/**
     * 清空n节点
     *
     * @param node
     */
    public void clearNode(BinaryTree node) {
        node.data = null;
        node.left = null;
        node.right = null;
    }
    
	/**
     * 插入节点 ，如果当前的节点没有左节点，我们就创建一个新节点，然后将其设置为当前节点的左节点。否则，就将新结点放在左结点的位置，再将原左结点置为新左结点的左结点
     *
     * @param node
     * @param value
     */
    public static void insertLeft(BinaryTree node, String value) {
        if (node != null) {
            if (node.left == null) {
                node.setLeft(new BinaryTree(value));
            } else {
                BinaryTree newNode = new BinaryTree(value);
                newNode.left = node.left;
                node.left = newNode;
            }
        } 
    }
    
    /**
     * 同插入左结点
     * @param node
     * @param value
     */
    public static void insertRight(BinaryTree node, String value) {
        if (node != null) {
            if (node.right == null) {
                node.setRight(new BinaryTree(value));
            }  else {
                BinaryTree newNode = new BinaryTree(value);
                newNode.right = node.right;
                node.right = newNode;
            }
        }
    }
   
    /**
     * 深度优先搜索（DFS）——前序遍历
     *
     * 1、输出节点的值
	   2、进入其左结点并输出。当且仅当它拥有左结点。
	   3、进入右结点并输出之。当且仅当它拥有右结点
     * @param node
     */
    public static void preOrder(BinaryTree node) {
        if (node != null) {

            System.out.println(node.data);

            if (node.left != null) {
                node.left.preOrder(node.left);
            }

            if (node.right != null) {
                node.right.preOrder(node.right);
            }
        }
    }
    
    
    /**
     * 深度优先搜索（DFS）—— 中序遍历
     * 左结点优先，之后是中间，最后是右结点。
     * 
     * 1、进入左结点并输出之。当且仅当它有左结点。
	   2、输出根结点的值。
	   3、进入结节点并输出之。当且仅当它有结节点。
     * @param node
     */
    public static void inOrder(BinaryTree node) {
        if (node != null) {
            if (node.left != null) {
                node.left.inOrder(node.left);
            }

            System.out.println(node.data);

            if (node.right != null) {
                node.right.inOrder(node.right);
            }
        }
    }
    
    /**
     * 深度优先搜索（DFS） —— 后序遍历
     * 左结点优先，之后是右结点，根结点的最后
     * 
     * 
     * @param node
     */
    public static void postOrder(BinaryTree node) {
        if (node != null) {
            if (node.left != null) {
                node.left.postOrder(node.left);
            }

            if (node.right != null) {
                node.right.postOrder(node.right);
            }

            System.out.println(node.data);
        }
    }
    
    
    /**
     * 广度排序
     *
     * @param node
     */
    public static void bfsOrder(BinaryTree node) {
        if (node != null) {
            Queue<BinaryTree> queue = new ArrayDeque<BinaryTree>();
            queue.add(node);

            while (!queue.isEmpty()) {
                BinaryTree current_node = queue.poll();

                System.out.println(current_node.data);

                if (current_node.left != null) {
                    queue.add(current_node.left);
                }
                if (current_node.right != null) {
                    queue.add(current_node.right);
                }
            }
        }
    }
    
    
    /**
     * 
     * 二叉搜索树有时候被称为二叉有序树或二叉排序树，二叉搜索树的值存储在有序的顺序中，因此，查找表和其他的操作可以使用折半查找原理
     * 二叉搜索树中的一个重要性质是，二叉搜索树中一个节点的值大于其左结点，但是小于其右结点
     * 
     * 插入树
     * 50、76、21、4、32、100、64、52。
     * 
     * 新结点值大于当前节点还是小于当前结点？
	       如果新节点的值大于当前结点，则转到右结点。如果当前节点没有右结点，则在那里插入新结点，否则返回步骤1。
	       如果新节点的值小于当前结点，则转到左结点。如果当前节点没有左结点，则在那里插入新结点，否则返回步骤1。
	        这里我们没有处理特殊情况。当新节点的值等于结点的当前值时，使用规则3。考虑在子结点的左侧插入相等的值。
	   
     * @param node   当前节点
     * @param value  新节点值
     */
    public void insertNode(BinaryTree node, Integer value) {
        if (node != null) {
            if (value <= Integer.valueOf(node.data) && node.left != null) {
                node.left.insertNode(node.left, value);
            } else if (value <= Integer.valueOf(node.data)) {
                node.left = new BinaryTree(String.valueOf(value));
            } else if (value > Integer.valueOf(node.data) && node.right != null) {
                node.right.insertNode(node.right, value);
            } else {
                node.right = new BinaryTree(String.valueOf(value));
            }
        }
    }
    
    /**
     * 查找节点是否存在
     *
     * @param node
     * @param value
     * @return
     */
    public boolean findNode(BinaryTree node, Integer value) {
        if (node != null) {
            if (value < Integer.valueOf(node.data) && node.left != null) {
                return node.left.findNode(node.left, value);
            }
            if (value > Integer.valueOf(node.data) && node.right != null) {
                return node.right.findNode(node.right, value);
            }
            return value == Integer.valueOf(node.data);
        }
        return false;
    }
    
    /**
     * 删除节点
     * @param node
     * @param value
     * @param parent
     * @return
     */
    public boolean removeNode(BinaryTree node, Integer value, BinaryTree parent) {
        if (node != null) {
        	//如果该 value 小于 current node 值，我们进入左子树，递归处理（当且仅当，current node 有左孩子）
            if (value < Integer.valueOf(node.data) && node.left != null) {
                return node.left.removeNode(node.left, value, node);
            } else if (value < Integer.valueOf(node.data)) {
                return false;
            } else if (value > Integer.valueOf(node.data) && node.right != null) {
                return node.right.removeNode(node.right, value, node);
            } else if (value > Integer.valueOf(node.data)) {
                return false;
            } else {
                if (node.left == null && node.right == null && node == parent.left) {
                    parent.left = null;
                    node.clearNode(node);
                } else if (node.left == null && node.right == null && node == parent.right) {
                    parent.right = null;
                    node.clearNode(node);
                } else if (node.left != null && node.right == null && node == parent.left) {
                    parent.left = node.left;
                    node.clearNode(node);
                } else if (node.left != null && node.right == null && node == parent.right) {
                    parent.right = node.left;
                    node.clearNode(node);
                } else if (node.right != null && node.left == null && node == parent.left) {
                    parent.left = node.right;
                    node.clearNode(node);
                } else if (node.right != null && node.left == null && node == parent.right) {
                    parent.right = node.right;
                    node.clearNode(node);
                } else {
                    node.data=String.valueOf(node.right.findMinValue(node.right));
                    node.right.removeNode(node.right,Integer.valueOf(node.right.data),node);
                }
                return true;
            }
        }
        return false;
    }
    
    /**
     * 查找树中最小值
     */
    public Integer findMinValue(BinaryTree node) {
        if (node != null) {
            if (node.left != null) {
                return node.left.findMinValue(node.left);
            } else {
                return Integer.valueOf(node.data);
            }
        }
        return null;
    }
    
    
}