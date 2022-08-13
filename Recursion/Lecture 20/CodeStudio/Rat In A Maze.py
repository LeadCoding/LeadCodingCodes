"""
We will start from (0 , 0) and we need to reach the Last Cell , that is , (n - 1 , n - 1)
From any cell, we can move in 4 Directions - Down , Left , Right , Up
We can pass through any cell if arr[i][j] = 1
If any cell , arr[i][j] = 0 , we cannot pass through that cell
We need to return vector<string> where each string denotes the direction in which we need to move in order to reach our Target Cell(n - 1 , n - 1)
As we require all the strings to be sorted in itself, we will move in the following manner - a) Down , b) Left , c) Right , d) Up
"""

def searchMaze(arr, n):

    def help(i, j, n):
        
        # If we move out of the matrix or if arr[i][j] is Blocked(that is, it has a Value 0) , we cannot move through it hence we return back
        
        if i < 0 or j < 0 or i == n or j == n or arr[i][j] == 0:
            return
        
        # If we have reached the Target Cell(n - 1 , n - 1) , we have found one Valid Path, so we add this path into our ans[] and return back
        
        elif i == n-1 and j == n-1:
            
            # "".join() converts the path list to a string
            
            ans.append("".join(path))
            return
    
        # Else we haven't reached the Last Cell but we are in a Safe Cell that is, arr[i][j] = 1
    
        else:
            
            # We need to mark this cell as Blocked so that we don't come over it again. Else we will get struck in an Infinite Loop
            
            arr[i][j] = 0 ;
    
            # For every movement in any Directions - Down , Left , Right and Up, we will follow the following steps
            # a) Include the First Character of the direction inside our string path
            # b) Move in that particular direction using (i , j)
            # c) When we comeback, we need to undo the change we have done so we pop_back() the character we inserted at the end
            
            # We move in Downward Direction
            
            path.append('D') ;
            help(i + 1 , j , n) ;
            path.pop() ;
            
            # We move in Leftward Direction
            
            path.append('L') ;
            help(i , j - 1 , n) ;
            path.pop() ;
            
            # We move in Rightward Direction
            
            path.append('R') ;
            help(i , j + 1 , n) ;
            path.pop() ;
            
            # We move in Upward Direction
            
            path.append('U') ;
            help(i - 1 , j , n) ;
            path.pop() ;
            
            # After moving in all 4 directions, we need to backtrack and mark the cell again as 1
            
            arr[i][j] = 1 ;
    

    ans = []
    
    # taking path list as python string is im-mutable, making it dificult to pop() during backtracking 
    
    path = []
    
    # We will always start from (0 , 0) and ask recursion to check for all possible paths to reach (n - 1 , n - 1)
  
    help(0, 0, n)
    
    return ans

"""
Time Complexity: O(4^(N * N) )
Space Complexity: O(N * N)
"""
