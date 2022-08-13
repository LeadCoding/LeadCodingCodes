/* This problem asks us to fill N Queens in N * N ChessBoard such that no Queen will cancel each other
In order to be sure, no Queen cancel out each other, we need to check in 3 directions: 
a) In the column where we are placing the Queen
b) In the Top Right / Upper Right Diagonal of the Cell in which we are placing the Queen
c) In the Top Left  / Upper Left Diagonal of the  Cell in which we are placing the Queen
We don't need to check for the Rows, as each row will contain only a single Queen
Instead of using isSafe() function, which takes O(N) time to check if the Current Cell (i , j) is Safe or Not, we will use 3 arrays:
i)   col[n] --> Checks if we have already placed a Queen or not in the Same Column
ii)  topLeft[2 * n] -->  Checks if our Queen can be attacked by any Queen sitting in it's Top Left Diagonal / Upper Left Diagonal
iii) topRight[2 * n] --> Checks if our Queen can be attacked by any other Queen sitting in it's Top Right Diagonal / Upper Right Diagonal
To mark every index in our topLeft[] & topRight[] arrays, we use the following :
a) For any cell in our ChessBoard (i , j) to map it with corresponsing index in topLeft[], we use the formula: (i - j + n - 1)
b) For any cell in our ChessBoard (i , j) to map it with the corresponding index in topRight[], we use the formula(i + j)
*/

class Solution 
{
   public void help(int i , int n , char[][] chessBoard , List<List<String>> ans , int[] col , int[] topLeft , int[] topRight)
    {
        // If i becomes equal to N , it means we have safely placed all the N Queens in N Rows such that no one attacks one another

        if(i == n)
        {
            // String temp will store the current configuration of the chessBoard which we can later put inside our ans

            List<String> temp = new ArrayList<>();

            for(int j = 0 ; j < n ; j++)
            {
                // currRow will contain the configuration of the CurrentRow which we are traversing

                String currRow = "" ;

                for(int k = 0 ; k < n ; k++)
                {
                    currRow += chessBoard[j][k] ;
                }

                // After traversing one row, we need to push it inside our temp vector

                temp.add(currRow) ;
            }

            // After temp is ready, we put it inside our ans and simply return back to explore other possible configurations

            ans.add(new ArrayList(temp)) ;
            return ;
        }

        for(int j = 0 ; j < n ; j++)
        {
            // We need to first check if we can place a Queen in (i , j) position by calling isSafe() function

            if( ( col[j] == 0 ) && ( topLeft[i - j + n - 1] == 0 ) && ( topRight[i + j] == 0 ) )
            {
                // If isSafe() returns true, then we defintely can place a Queen in (i , j) cell

                chessBoard[i][j] = 'Q' ;

                col[j] = 1 ;
                topLeft[i - j + n - 1] = 1 ;
                topRight[i + j] = 1 ;

                // We ask recursion to do rest of the task

                help(i + 1 , n , chessBoard , ans , col , topLeft , topRight) ;

                // Before leaving, we need to backtrack & undo the change we have done

                chessBoard[i][j] = '.' ;

                col[j] = 0 ;
                topLeft[i - j + n - 1] = 0 ;
                topRight[i + j] = 0 ;

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

        char[][] chessBoard = new char[n][n] ;
        for(char[] arr : chessBoard)
            Arrays.fill(arr,'.');

        // col[] will help us determine if any Queen is already placed in that particular column or not in O(1) Time Complexity

        int col[] = new int[n] ;

        // topLeft[] will help us to check in the Top Left / Upper Left Direction in O(1) Time Complexity
        // We use the formula (i - j + n - 1) for mapping any index in topLeft with it's corresponding (i , j) in our ChessBoard

        int topLeft[] = new int [ 2 * n ];  

        // topRight[] will help us to check in the Top Right / Upper Right Direction in O(1) Time Complexity
        // We use the formula (i + j) for mapping any index in topRight with it's corresponding (i , j) in our ChessBoard

        int topRight[] = new int [ 2 * n ];  

        help(0 , n , chessBoard , ans , col , topLeft , topRight) ;

        return ans ;
    }
}

/* 
Time Complexity:  O(N!)
Space Complexity: O(N) {For Recursive Stack and For the 3 arrays col[] , topLeft[] , topRight[] we are using in place of isSafe() function } & O(N^2) {For our ans}
*/
