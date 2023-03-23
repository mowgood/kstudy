package day12;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;

class Value {
	int value;

	Value(int value) {
		this.value = value;
	}
	
	public boolean equals(Object obj) {
		boolean res = false;
		if( obj != null && obj instanceof Value) 
			if(value == ((Value)obj).value)
				res = true;
		return res;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(value);
	}

	@Override
	public String toString() {
		return "Value [value=" + value + "]";
	}
	
}

public class EqualsTest3 {
	public static void main(String[] args) {
		Value v1 = new Value(10);
		Value v2 = new Value(10);
		Value v3 = new Value(20);
		System.out.println(v1.equals(null)); 		// false
		System.out.println(v1.equals(v3)); 			// false
		System.out.println(v1.equals(v2)); 			// true (equals 오버라이딩)
		System.out.println(v1.equals(new Date()));  // false
		
		// 해시코드값이 다르기 때문에 다른 객체로 판단
		// hashCode 오버라이딩
		System.out.println(v1.hashCode()); 
		System.out.println(v2.hashCode());
		System.out.println(v3.hashCode());
		
		// HashSet 객체에 v1, v2, v3를 저장한 후에
		// 저장된 객체들을 출력해 본다.
		HashSet<Value> hs = new HashSet<>();
		hs.add(v1);
		hs.add(v2);
		hs.add(v3);
		
		System.out.println(hs);
		
		for(Value v : hs) {
			System.out.println(v);
		}
		
//		if (v1.equals(v2))
//			System.out.println("v1과 v2는 같습니다.");
//		else
//			System.out.println("v1과 v2는 다릅니다.");
//		v2 = v1;
//		if (v1.equals(v2))
//			System.out.println("v1과 v2는 같습니다.");
//		else
//			System.out.println("v1과 v2는 다릅니다.");
	}
}
