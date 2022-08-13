import java.util.*;
public class Solution 
{
    // Implementing comparator class to sort the arraylist of arraylist
    static class Compare implements Comparator<ArrayList<Integer>>
    {
        //  Function to compare two ArrayList
        
        public int compare(ArrayList<Integer> a,ArrayList<Integer> b)
        {
            for(int i=0;i<Math.min(a.size(),b.size());i++)
            {
                if(a.get(i)>b.get(i)) return 1;
                else if(a.get(i)<b.get(i)) return -1;
                else continue;
            }
            return a.size()-b.size();
        }
    }
    
    static void helper(int nums[],int i,ArrayList<Integer> subset,ArrayList<ArrayList<Integer>> ans)
    {
        // Our base case will hit when our i pointer reaches the end of given array nums[]
        
        if(i==nums.length)
        {
            ans.add(new ArrayList<>(subset));
            return ;
        }
        
        // We pick i-th Element
        
        subset.add(nums[i]);
        
        // We ask recursion to do rest of the task
        
        helper(nums, i + 1, subset, ans);
        
        // Backtrack and Undo the change we have done
        
        subset.remove(subset.size() - 1);
        
        // While using Don't Pick option, we must ensure we skip all the Duplicate Occurrences of nums[i]
    
        while(i < nums.length - 1 && (nums[i] == nums[i + 1] ) )
        {
            i++ ;
        }

        // Our i pointer will stop on the last Duplciate Occurencce of nums[i]
        
        // Don't Consider the Element and Ask Recursion to generate Unique Subsets
        
        helper(nums, i + 1, subset, ans);
        return ;
    }
    
    public static ArrayList<ArrayList<Integer>> uniqueSubsets(int n, int arr[]) 
    {
        // In this logic we to sort the array
        
        Arrays.sort( arr );
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        helper(arr , 0, new ArrayList<Integer>() , ans);
        
        //  There are no pre defined function to sort Arraylist of Arraylist so we need to create a comparator class
      
        Collections.sort(ans, new Compare());
        
        return ans;
    }
}

/* 
Time Complexity : O(2^N)
Space Complexity : O(N)
*/
