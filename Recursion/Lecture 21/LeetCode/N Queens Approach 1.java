/* This problem asks us to fill N Queens in N * N ChessBoard such that no Queen will cancel each other
In order to be sure, no Queen cancel out each other, we need to check in 3 directions: 
a) In the column where we are placing the Queen
b) In the Top Right / Upper Right Diagonal of the Cell in which we are placing the Queen
c) In the Top Left  / Upper Left Diagonal of the  Cell in which we are placing the Queen
We don't need to check for the Rows, as each row will contain only a single Queen
*/

class Solution 
{
    boolean isSafe(int i , int j , char[][] chessBoard , int n)
    {
        // As we will be checking in 3 directions, we require i & j 3 times. That's why we need to preserve the initial (i , j) so that it doesn't get lost

        int tempI = i ;
        int tempJ = j ;

        // We check in the Same Column if a Queen has already been placed or not

        while(tempI >= 0)
        {
            if(chessBoard[tempI][j] == 'Q')
            return false ;

            tempI-- ;
        }

        // Again initalising tempI & tempJ with i & j respectively

        tempI = i ; 
        tempJ = j ;

        // Now we check in the Top Right Direction / Upper Right Diagonal if a Queen has already been placed or not

        while(tempI >= 0 && tempJ <= n - 1)
        {
            if(chessBoard[tempI][tempJ] == 'Q')
                return false ;

            tempI-- ;
            tempJ++ ;

        }

        // Again initalising tempI & tempJ with i & j respectively

        tempI = i ;
        tempJ = j ;

         // Lastly we check in the Top Left Direction / Upper Left Diagonal if a Queen has already been placed or not

        while(tempI >= 0 && tempJ >= 0)
        {
            if(chessBoard[tempI][tempJ] == 'Q')
                return false ;

            tempI-- ;
            tempJ-- ;

        }

        // If all the 3 directions are safe, we can place the Queen and that's why return true

        return true ;
    }
    
    void help(int i , int n , char[][] chessBoard , List<List<String>> ans)
    {
        // If i becomes equal to N , it means we have safely placed all the N Queens in N Rows such that no one attacks one another

        if(i == n)
        {
            // string temp will store the current configuration of the chessBoard which we can later put inside our ans

            List<String> temp = new ArrayList<>();

            for(int j = 0 ; j < n ; j++)
            {
                // currRow will contain the configuration of the CurrentRow which we are traversing

                String currRow = "" ;

                for(int k = 0 ; k < n ; k++)
                {
                    currRow = currRow + chessBoard[j][k] ;
                }

                // After traversing one row, we need to push it inside our temp vector

                temp.add(currRow) ;
            }

            // After vector temp[] is ready, we put it inside our ans and simply return back to explore other possible configurations

            ans.add(new ArrayList(temp)) ;
            return ;
        }

        for(int j = 0 ; j < n ; j++)
        {
            // We need to first check if we can place a Queen in (i , j) position by calling isSafe() function

            if(isSafe(i , j , chessBoard , n) )
            {
                // If isSafe() returns true, then we defintely can place a Queen in (i , j) cell

                chessBoard[i][j] = 'Q' ;

                // We ask recursion to do rest of the task

                help(i + 1 , n , chessBoard , ans) ;

                // Before leaving, we need to backtrack & undo the change we have done

                chessBoard[i][j] = '.' ;
            }
        }
    }

    public List<List<String>> solveNQueens(int n) 
    {
        List<List<String>> ans = new ArrayList<>();
        
        // We create the chessBoard which has dimmensions N * N
        // Any cell inside chessBoard will have either '.' or 'Q' as a Character
        // If chessBoard[i][j] = '.' , indicates that the cell is vacant and we can place a Queen
        // If chessBoard[i][j] = 'Q' , indicates that the cell is occupied by a Queen

        char[][] chessBoard = new char[n][n];
        for(char[] arr : chessBoard)
            Arrays.fill(arr,'.');

        help(0 , n , chessBoard , ans) ;

        return ans ;
    }
}

/* 
Time Complexity:  O(N!)
Space Complexity: O(N) { For Recursive Stack } and O(N^2) { For our ans }
*/
