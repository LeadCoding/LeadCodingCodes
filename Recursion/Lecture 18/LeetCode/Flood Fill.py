"""
This Question asks us to color all the Neighbouring Cells of (sr , sc) with the given Color
By Neighbouring we can move in 4 directions - a) Up , b) Down , c) Left , d) Right
And we change colors of only those Neighbouring Cells which have the same color as the Original Cell(sr, sc)
If old-Color of (sr , sc) is same as New-Color, we don't need to modify anything. Just return the previous Matrix
"""
class Solution:
    def floodFill(self, image: List[List[int]], sr: int, sc: int, newColor: int) -> List[List[int]]:
                
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


        # floodFill function scope
            
        # We calculate oldColor first using the coordinates (sr , sc)
        
        oldColor = image[sr][sc]
        
        # Incase the oldColor & given Color is same, we can simply return the Original Image
        
        if oldColor == newColor:
            return image
        
        # We calculate the Dimmensions of image matrix[]
        
        n, m = len(image), len(image[0]) 
        
        flood(sr, sc, n, m)
        
        # We return the new image[][] matrix after changing all the Neighbouring Cells of (sr , sc) having OldColor to Color
        
        return image
    
"""
Time Complexity:  O(N * M) 
Space Complexity: O(N * M)
"""
