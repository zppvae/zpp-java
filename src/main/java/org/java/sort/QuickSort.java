package org.java.sort;
/** 
 * 快速排序 
 * 
 * https://blog.csdn.net/it_zjyang/article/details/53406764
 * @author
 */  
public class QuickSort {  
    
	public static void main(String[] args) {  
        
	    int[] a = new int[]{9,6,25,12,5,-3,10,7};

	    sort(a, 0, a.length-1);  
	          
	    System.out.println("排序后的结果：");  
	    for(int x : a){  
	        System.out.print(x+" ");  
	    }  
	}  
	
    /** 
     * 将数组的某一段元素进行划分，小的在左边，大的在右边 
     * @param a 
     * @param start 
     * @param end 
     * @return 
     */  
    public static int divide(int[] a, int start, int end){  
        //每次都以最右边的元素作为基准值  
        int base = a[end];  
        //start一旦等于end，就说明左右两个指针合并到了同一位置，可以结束此轮循环。  
        while(start < end){
            //从左边开始遍历
            while(start < end && a[start] <= base) {
                //如果比基准值小，就继续向右走
                start++;
            }
            //上面的while循环结束时，就说明当前的 a[start] > 基准值，应与基准值进行交换
            if(start < end){
                SortUtil.swap(a,start,end);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值右边)，因此右边也要同时向前移动一位
                end--;
            }
            //从右边开始遍历
            while(start < end && a[end] >= base) {
                //如果比基准值大，就继续向左走
                end--;
            }

            //上面的while循环结束时，就说明当前的 a[end] < 基准值，应与基准值进行交换
            if(start < end){
                SortUtil.swap(a,start,end);
                //交换后，此时的那个被调换的值也同时调到了正确的位置(基准值左边)，因此左边也要同时向后移动一位
                start++;
            }

        }
        //这里返回start或者end皆可，此时的start和end都为基准值所在的位置  
        return end;  
    }  
  
    /** 
     * 排序 
     * @param a 
     * @param start 
     * @param end 
     */  
    public static void sort(int[] a, int start, int end){  
        if(start < end){
            //如果不止一个元素，继续划分两边递归排序下去
            int partition = divide(a, start, end);
            //递归对小于基准元素（左边）的数据排序
            sort(a, start, partition-1);
            //递归对大于基准元素（右边）的数据排序
            sort(a, partition+1, end);
        }
              
    }

      
} 