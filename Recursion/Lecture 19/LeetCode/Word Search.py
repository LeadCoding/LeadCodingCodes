"""
In this problem, we need to search if a given word exists inside a board[][] of characters
From one cell, we can move into it's 4 adjacent cells in the following directions - Down , Right , Up , Left
We cannot use one cell for more than once. Thus, backtracking comes into the picture
"""
class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        
        def search(i, j, n, m, k):
            
            # If k is equal to the given word's length, it means every character of word is present inside board[][], so we return true
            
            if k == len(word):
                return True
            
            # If we are moving outside the board's boundary or if board[i][j] is not equal to word[k] , we return false
            if i < 0 or j < 0 or i == n or j == m or board[i][j] != word[k]:
                return False
            
            # We store the Character present in the current Cell inside ch variable 
            
            ch = board[i][j]

            # We change board[i][j] to # so that we don't visit the Same Cell again

            board[i][j] = '#'

            # We move in Downward direction

            op1 = search(i + 1 , j , n , m, k + 1)

            # We move in Rightward direction

            op2 = search(i , j + 1 , n , m, k + 1)

            # We move in Upward direction

            op3 = search(i - 1 , j , n , m, k + 1)

            # We move in Leftward direction

            op4 = search(i , j - 1 , n , m, k + 1)

            # We backtrack and change the value of board[i][j] to it's original character stored in ch variable

            board[i][j] = ch

            # As we need to find the word, no matter from which direction we get it, so we return (op1 OR op2 OR op3 OR op4)

            return op1 or op2 or op3 or op4
        
        # exist function scope
        
        n, m = len(board), len(board[0])
        
        for i in range(n):
            for j in range(m):
                
                # We start checking only if board[i][j] matches with word[0]
                
                if board[i][j] == word[0]:
                    
                    #  If our search results return True, we directly return true 
                    
                    if search(i, j, n, m, 0):
                        return True
                    
        # Otherwise, if we searched the entire matrix and still didn't find the word, we return false 
        
        return False
                    
"""
Time Complexity:  O(N * M * 4^K)
Space Complexity: O(K) | k = len(word)
"""
