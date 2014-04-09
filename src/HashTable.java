import java.math.BigInteger;

public class HashTable {
	
	private String[][] table;
	private int bucketNum;
	private int overflowBucketNum;
	
	public HashTable(int initialInputSize) {
		bucketNum = initialInputSize/10;
		overflowBucketNum = bucketNum/5;  //estimated overflow buckets to 20% of normal buckets
		table = new String[bucketNum+overflowBucketNum][10];
	}
	
	private int hashFunction(String value) {
		return modHash(value);
		//return fnv1(value);
	}
	
	private int modHash(String value) {
		int hash = Math.abs(value.hashCode());
		return hash % bucketNum;
	}
	
	private int fnv1(String value) {
		BigInteger hash = new BigInteger("811c9dc5",16);
		BigInteger prime32 = new BigInteger("01000193",16);
		BigInteger mod32 = new BigInteger("2").pow(32);
		
		byte[] data = value.getBytes();
	    for (byte b : data) {
	      hash = hash.multiply(prime32).mod(mod32);
	      hash = hash.xor(BigInteger.valueOf((int) b & 0xff));
	    }
	    return hash.mod(new BigInteger(Integer.toString(bucketNum))).intValue();
	}
	
	public void insert(String value) {
		int hash = hashFunction(value);
		int i = 0;
		for (i = 0; i < table[hash].length; i++) {
			if (table[hash][i] == null) {
				table[hash][i] = value;
				break;
			}
		}
		if (i == table[hash].length) { //bucket is full, insert in overflow buckets 
			for (int j = bucketNum; j < table.length; j++) {
				for (int k = 0; k < table[j].length; k++) {
					if (table[j][k] == null) {
						table[j][k] = value;
						break;
					}
				}
			}
		}
	}
	
	public boolean lookup(String value) {
		int hash = hashFunction(value);
		int i = 0;
		for (i = 0; i < table[hash].length; i++) { 
			if (table[hash][i] == null) {
				break;
			}
			if (table[hash][i].equals(value)) {
				return true;
			}
		}
		for (int j = bucketNum; j < table.length; j++) { //search in overflow buckets
			for (int k = 0; k < table[j].length; k++) {
				if (table[j][k] == null) {
					break;
				}
				
				if (table[j][k].equals(value)) {
					return true;
				}
				
			}
		}
		return false;
	}
	
	public void delete(String value) {
		int hash = hashFunction(value);
		int i = 0;
		for (i = 0; i < table[hash].length; i++) { 
			if (table[hash][i] == null) {
				break;
			}
			if (table[hash][i].equals(value)) {
				//table[hash][i] = null;
				table[hash][i] = table[hash][table[hash].length-1]; //swap last slot and this slot, empty last slot
				table[hash][table[hash].length-1] = null;
				return;
			}
		}
		for (int j = bucketNum; j < table.length; j++) { //search in overflow buckets
			for (int k = 0; k < table[j].length; k++) {
				if (table[j][k] == null) {
					break;
				}
				if (table[j][k].equals(value)) { 
					//table[j][k] = null;
					table[j][k] = table[j][table[j].length-1];  //swap last slot and this slot, empty last slot
					table[j][table[j].length-1] = null;
					return;
				}	
			}
		}
		
	}
		
	public static void main(String[] args) {
		HashTable h = new HashTable(100);
		h.insert("Mateja");
		h.insert("Ivan");
		h.insert("Bla");
		h.insert("Marica");
		h.insert("Ivica");
		h.insert("Vještica");
		h.insert("jhfjdfčdf");
		h.insert("jfjff");
		System.out.println(h.lookup("bla"));
		System.out.println(h.lookup("Mate"));
		System.out.println(h.lookup("Mateja"));
		System.out.println(h.lookup("jfjff"));
		h.delete("Ivica");
		System.out.println(h.lookup("Ivica"));
	}
}
