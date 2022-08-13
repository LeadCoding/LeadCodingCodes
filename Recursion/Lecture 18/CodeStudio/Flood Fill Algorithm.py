def floodFill(image, x, y, newColor):

    
    # [i][j] denotes the current cell
    # n*m is the size of the images which is also denoting the boundary of the image
    def flood(i, j, n, m):
        
        # If we move out of the Matrix Or oldColor is not equal to newColor, we just return back
        
        if i < 0 or j < 0 or i == n or j == m or image[i][j] != oldColor:
            return
            
        # We change the color of the Current-Cell to newColor
        
        image[i][j] = newColor
        
        # Recursive call in Downward direction
        
        flood(i+1, j, n, m)
        
        # Recursive call in the Upward direction
        
        flood(i-1, j, n, m)
        
        # Recursive call in the Right direction
        
        flood(i, j+1, n, m)
        
        # Recursive call in the left direction
        
        flood(i, j-1, n, m) 
        
    #  it is denoting the oldcolor
    
    oldColor = image[x][y]
    
    #  incase the oldColor & newColor is same then there is a possibility of forming an infinite recursion therefore to avoid such type of situation we are putting this condition over here if oldColor != newColor then it will proceed else it will return us the same image
    
    if oldColor == newColor:
        return image
    
    n, m = len(image), len(image[0]) 
    
    # recursion call
    
    flood(x, y, n, m)
    
    return image
    
"""
Time Complexity:  O(N * M) 
Space Complexity: O(N * M)
"""
