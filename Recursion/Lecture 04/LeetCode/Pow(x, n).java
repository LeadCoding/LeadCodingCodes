// NOTE : We need to keep in mind that if n is negative we need to return the reciprocal of the answer obtained from pow(x, n)

class Solution 
{
    public double myPow(double x, int n) 
    {
        // If the power is 0, no need to calculate futher we return 1
        
        if(n == 0)
            return 1;
        
        // Calling the helper Function to get the value
        
        double ans = helper(x,Math.abs(n));
        
        // Checking whether the n is negative or not
        
        if(n < 0)
            ans = 1/ans;
        
        return ans;
    }
    
     public double helper(double x, int n) 
     {
         // Base Case : when n equals 0 we return 1
         
         if(n == 0)
            return 1;
         double val =  helper(x, n/2);
         
         // If val is odd, we need to multiply by x
         
         if(n % 2 == 0)
            return val * val;
         else
            return x * val * val;
    }
}

//  Time Complexity :  O (log N)
//  Space Complexity : O (log N)
