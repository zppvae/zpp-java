package org.java.basic.copy;

/**
 * 浅拷贝 与深拷贝
 * 
 * 浅拷贝；创建一个新对象，然后将当前对象的非静态字段复制到该新对象，如果字段是值类型的，那么对该字段执行复制；如果该字段是引用类型的话，则复制引用但不复制引用的对象。因此，原始对象及其副本引用同一个对象
 * 深拷贝：创建一个新对象，然后将当前对象的非静态字段复制到该新对象，无论该字段是值类型的还是引用类型，都复制
 * Description:
 * @author zpp
 * @date   2018年4月9日
 */
public class CopyTest {
	
	public static void main(String[] args) {  
        Resume zhangsan = new Resume("zhangsan","男",24);
        zhangsan.setExperience("111","11111");
        zhangsan.displayResume();

        Resume zhangsan2 = (Resume)zhangsan.clone();
        zhangsan2.setExperience("111","22222");
        zhangsan2.displayResume();
        zhangsan.displayResume();
        zhangsan2.displayResume();
		
//		Resume zhangsan = new Resume("zhangsan","男",24);
//        zhangsan.setExperience("2009-2013就读于家里蹲大学","精通JAVA,C,C++,C#等代码拷贝和粘贴");
//        zhangsan.displayResume();
//
//        Resume zhangsan2 = (Resume)zhangsan.clone();
//        zhangsan2.setExperience("2009-2013就读于家里蹲大学","精通JAVA,C,C++,C#等");
//        zhangsan2.displayResume();
    } 
}
