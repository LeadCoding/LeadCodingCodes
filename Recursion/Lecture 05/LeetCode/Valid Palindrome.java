/*  
 NOTE:
 The Question is exactly similar to checking a Palindrome
 Only difference is that we need to create a new string which contains only AlphaNumeric characters and all alphabets shoould be in Lower Case only
*/

class Solution 
{
    public boolean isPalindrome(String s) 
    {
         // We need to create a seperate string devoid of spaces and Non-Alphanumeirc Values
        
        String str = "" ;
        
        for(int i=0; i<s.length(); i++)
        {
            // We pick each character from the string using the loop
            
            char chr = s.charAt(i);
            
            // If the character is an alphabet we convert it into lowercase and add to the new string
            
            if(Character.isLetter(chr))
                str = str + Character.toLowerCase(chr);
            
            // If the character is a digit we add it to the new string
            
            if(Character.isDigit(chr))
                str = str + chr;
        }
      
    // If our new string is still empty, we directly return true
        
    if(str.length() == 0)
     return true ;
        
    boolean ans = isPalindromeHelper(0 , str.length() - 1 , str) ;
        
    return ans ;
    
    }
    public boolean isPalindromeHelper(int l, int r, String s)
    {
         // While checking if our left pointer crosses our right pointer, it indicates the string is Palindrome
    
        if(l >= r)
        return true ;
    
        // At any moment if s.charAt(l) != s.charAt(r), we are sure it's not an Palindrome
    
        if(s.charAt(l) != s.charAt(r))
        return false ;
    
        // Asking recursion to do rest of the task
    
        return isPalindromeHelper(l + 1 , r - 1 , s) ;
    }
}

/*  Time Complexity: O(N) 
    Space Complexity: O(N)
*/
