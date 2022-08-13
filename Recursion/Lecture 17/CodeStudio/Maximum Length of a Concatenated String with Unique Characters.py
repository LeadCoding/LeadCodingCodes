def stringConcatenation(arr):
    
    def compare(curString):
        
        # Before checking with selected[], we need to check if currString has repeating characters or Not

        # This is done using selfCheck array which checks if currString has repeating characters or Not
        
        selfCheck = [0 for i in range(26)]
        
        a = ord("a")
        
        for i in curString:
            
            # As all the characters are present in LowerCase, we can use currString[i] - 'a' to map every character to a corresponding index (as discussed thoroughly in the lecture)
            
            # If selfCheck[currString[i] - 'a'] is already taken, it means currString has repeating characters
            
            if selfCheck[ord(i)-a] == 1:
                return False
            # Else we mark all the characters inside SelfCheck as 1
            
            selfCheck[ord(i)-a] = 1
            
        # The second Loop ensures whether the characters of currString has already been selected or not
        
        for i in curString:
            
            # If currString[i] - 'a' is already taken, it means we cannot take currString, thus we return false 
            
            if selected[ord(i)-a] == 1:
                return False
            
            # At the end, if currString doesn't contain repeating characters AND it's characters have already not been taken yet, we return true indicating currString can be chosen now
            
        return True
    
    def helper(i, length):
        
        # If we reach till the end of arr[], we need to return the max Length we have taken till now
        
        if i == len(arr):
            return length
        
        a = ord("a")
        
        curString = arr[i]
        
        # If currString contains Duplicate Characters or it's characters have already been taken, we have no option but to skip curr String and move to next Index
        
        if compare(curString) == False:
            # skip the currString
            
            return helper(i+1, length)
        
        # Else we have two options, One to include CurrString OR another option is to Skip CurrString
            
        else:
                # Increase the length by currString.size() 
            for ch in curString:
                selected[ord(ch)-a] = 1
            length += len(curString)
            # Ask Recursion to do rest of task

            op1 = helper(i + 1, length)

            # Backtrack and unmark all the characters of currString as NOT TAKEN in selected[] list

            for j in curString:

                selected[ord(j)-a] = 0

            # As we are not including currString, we should decrease len by currString.size()

            length -= len(curString)
            
            # Ask recursion to do rest of the task
            
            op2 = helper(i + 1, length)
            
            # Lastly, we will return the max of (op1 , op2) whichever brings the Longest Length
            
            return max(op1, op2)
    
    # G var
    selected = [0 for i in range(26)]
    
    return helper(0, 0)
    
"""
Time Complexity:  O(K * 2^N)
Space Complexity: O(N)
"""
