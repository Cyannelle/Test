import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class TIC_ {

    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        
        
        String s = "abc", s_first = ""; //s - input string, s_first - first input, which gives the same hash as last 
        long n_hash = 0, i =0;          // n_hash - last number of input, which gives the same hash as first, i - counter
        long n_first = 0;               // n_first - first number of input, which gives the same hash as last one
        boolean flag = false;
        String s1, s_hash = "", previous_s1;         //s1 - temp string for operations, s_hash - last input, which gives the same hash as first
        
        HashMap<String, Long> hashTable = new HashMap<String, Long>();
        Set<Map.Entry<String, Long>> entrySet=hashTable.entrySet();
        
        
        s1 = sha256(s);                 // first hash
        hashTable.put(s1, i);           //putting 1st hash to the table
        
        
     while (!flag) {
         previous_s1 = s1;
         s1 = sha256(s1);                //making hash basing previous one
         if (hashTable.containsKey(s1) != false) {
             flag = true;
             n_hash = i;
             s_hash = previous_s1;
             n_first = hashTable.get(s1) - 1;
         } 
         else {
            i++;
            hashTable.put(s1,i);
         }
     }
     
     
     //searching for the first input
  
    for (Map.Entry<String, Long> pair : entrySet) {
        if (pair.getValue().equals(n_first)) {
            s_first = pair.getKey();
            break;
        } 
    }
     System.out.println("First input: " + Long.toString(n_first) + " " + s_first + " hash:" + sha256(s_first));
     System.out.println("Last input: " + Long.toString(n_hash) + " " + s_hash + " hash:" + sha256(s_hash));
   
    }
    
     static String sha256(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] result = input.getBytes();
        int k = 6;
        //byte[] result_kp = new byte[k];
        StringBuilder sb = new StringBuilder();

        md.update(result);
	result=md.digest();
        for (int j = 0; j <= k-1; j++) {
            
            sb.append(Integer.toString((result[j] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
        
        static String bytesToString(byte[] input) {
            
         StringBuilder sb = new StringBuilder();
         for (int i = 0; i < input.length ; i++) {
            sb.append(Integer.toString((input[i] & 0xff) + 0x100, 16).substring(1));
        }
          System.out.println(sb.toString());
        return sb.toString();
            
        }
}

