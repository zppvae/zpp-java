package org.java.sort.test;

/**
 * 堆排序
 */
public class HeapSort {   //(1)
    public static void main(String[] args) {
        HeapSort heapSort = new HeapSort();
        int[] array = {19, 8, 27, 6, 35, 14, 3, 12, 1, 0, 9, 10, 7};

        System.out.println("Before heap:");
        heapSort.printArray(array);

        heapSort.heapSort(array);

        System.out.println("After heap sort:");
        heapSort.printArray(array);
    }

    public void heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        buildMaxHeap(array);//建立最大堆，构建完成后下标为0的位置就是最大值
        for (int i = array.length - 1; i >= 1; i--) {
            //最大的在0位置，那么开始沉降，这样每交换一次最大的值就丢到最后了
            exchangeElements(array, 0, i);
            //继续获取0位置最大值
            maxHeap(array, i, 0);
        }
    }

    //(2)		//建立最大堆
    private void buildMaxHeap(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        //一半的位置，通过左右子树的关联关系可计算出剩下的一半数据
        int half = (array.length - 1) / 2;
        for (int i = half; i >= 0; i--) {
            maxHeap(array, array.length, i);
        }
    }

    /**
     * 构建大堆
     * @param array
     * @param heapSize      剩余数组元素
     * @param index
     */
    private void maxHeap(int[] array, int heapSize, int index) {
        //左节点
        int left = index * 2 + 1;
        //右节点
        int right = index * 2 + 2;
        //最大值索引
        int largest = index;
        if (left < heapSize && array[left] > array[index]) {
            largest = left;
        }
        if (right < heapSize && array[right] > array[largest]) {
            largest = right;
        }
        //最大值是左节点或者右节点
        if (index != largest) {
            exchangeElements(array, index, largest);
            //继续遍历子树
            maxHeap(array, heapSize, largest);
        }
    }

    //（3）
    public void printArray(int[] array) {
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    /***
     * 数据交换
     * @param array
     * @param index1
     * @param index2
     */
    public void exchangeElements(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}