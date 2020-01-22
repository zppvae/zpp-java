package org.java.collections.set;

public class Person{  
    private String name;  
    private int age;  
     
    public static void sop(Object o){  
        System.out.println(o);  
    }  
    
    public Person(){  
        
    } 
    
    public Person(String name, int age){  
        this.name =name;  
        this.age =age;  
    }  
   
    public void setName(String name){  
        this.name =name;  
    }  
     
    public String getName(){  
        return this.name;  
    }  
   
    public void setAge(int age){  
        this.age =age;  
    }  
   
    public int getAge(){  
        return this.age;  
    }  
   
    public String toString(){  
        return this.name+"::"+this.age;  
    }  
    
	public boolean equals(Object obj) {
		if (!(obj instanceof Person)) {
			return false;
		}

		Person p = (Person) obj;

		// 用来查看equals方法是否被调用
		sop(this.name + ".......equals......." + p.name);
		// 认为名字相同并且年龄一样大的两个对象是一个
		return this.name.equals(p.name) && this.age == p.age;
	}
	
	//name是string，已经重写了Hashcode，可以直接调用
	public int hashCode(){  
	    sop(this.name +"......hashCode");  
	    return this.name.hashCode() + 29*age;  
	}  
	
	public void show(){  
	   System.out.println(name +"... show run..."+ age);  
	}
	
	private void showII(){  
	   System.out.println(name +"... showII runs..."+ age);  
	}  
	  
	public void method(){  
	   System.out.println(name +"... method run..."+ age);  
	}  
	  
	public void paramMethod(String str, int num){  
	   System.out.println(".paramMethod run..."+ str+":"+ num);  
	}
}  