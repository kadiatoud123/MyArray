/* File: MyArrays
 *
 * Author:  Kadiatou
 *
 * Purpose: To create a class that allows you to
 * manipulate an array of integers.
 */

 import java.util.Arrays;
 import java.util.Scanner;                    
 
 public class MyArray  {
 
     // the sentinel value used to indicate end of input, initialized to -999
     // the default size of the array if one is not specified, initialized to 20
     // the lower bound of the range of integer elements, initialized to 10
     // the upper bound of the range of integer elements, initialized to 50
     // a data member to reference an array of integers
     // a data member to represent the number of elements entered into the array
     public static int SENTINEL= -999;
     public static int DEFAULT_SIZE= 20;
     public static int LOWER= 10;
     public static int UPPER= 50;
 
     private int [] arr;
     private int numElements;
     private int sum;
     private int min;
     private int max;
     private double avg;
 
 // CONSTRUCTORS
     // Initializes a MyArray object using default members
     public MyArray() {
         
        arr = new int[DEFAULT_SIZE];
        this.numElements = 0;
        this.sum=0;
        this.avg=0.0;

     }
     //creates an array of integers based on the input argument passed to n
     public MyArray(int n){
         this.arr = new int[n];
         this.computeStatistics();
         
     }
     // creates the object’s array based on the array of integers passed to the method
     public MyArray(int[] arr){
        if (arr==null){
            throw new IllegalArgumentException();
        }
         this.arr = new int[arr.length];
         for (int i = 0; i < arr.length; i++) {
             if (arr[i]>=LOWER && arr[i]<= UPPER){
                 this.arr[numElements] =  arr[i];
                 this.numElements++;
             }
         }
         this.computeStatistics();
         
     }
     //prompts the user to enter integer elements (within the range specified by LOWER and UPPER bound) and store them into the object’s array
     public void inputElements() {
         Scanner scan = new Scanner(System.in);
         System.out.print("Enter up to " + this.arr.length+ " integers between " + LOWER + " and " + UPPER + ". Enter -999 to end user input: ");
             
         for (int i = 0; i < this.arr.length; i++) {
             int num= scan.nextInt();
             if (num<= UPPER && num>=LOWER){
                 this.arr[i]= num;
                 this.numElements++;
             } else if (num== SENTINEL){
                 break;
             } else{
                 i--;
             }
         }
         this.computeStatistics();
         scan.close();
 
     }
     //determines if the integer passed to the method is within the bounds as specified by the class variables of the class, LOWER and UPPER
     public boolean validInput( int num ) {
         if (num<= UPPER && num>=LOWER){
             return true;
         } else{
             return false;
         }
     }
     //converts the array to string
     public String toString(){
         if (numElements==0){
             return "[]";
         }
         String print= "[" + this.arr[0];
         for (int i = 1; i < this.numElements; i++) {
             
             print+= ", " + this.arr[i];
         }
         print+= "]" ;
         return print;
     }
     //computes the expected statistics of the array, specifically the minimum, maximum, sum, average and middle of all the elements contained in the array
     public void computeStatistics(){
        if (numElements==0){
            this.sum=0;
            this.max=0;
            this.min=0;
            this.avg=0;

        }else{
         this.min = arr[0];
         this.max= arr[0];
         this.sum=0;
         for (int i = 0; i < numElements; i++){
             if (this.min> this.arr[i]){
                 this.min= this.arr[i];
             }
             if (this.max<this.arr[i]){
                 this.max= this.arr[i];
             }
            this.sum+=arr[i];
            this.avg = (double)this.sum/this.numElements;
            }
        }
     }
     //returns the position of the last occurence of the number passed to the method
     public int lastIndex(int n) {
         for (int i = numElements - 1; i >= 0; i--) {
             if (arr[i] == n) {
                 return i;
             }
         }
         return -1;
     }
 
     // allows you to insert a specified number at a specified position within the currently filled portion of the array
     public boolean insert(int n, int position) {
        if (this.arr.length==this.numElements ||position<0||this.numElements<position){
            return false;
        }else{
            for (int i=this.numElements-1;i>=position;i--){
                this.arr[i+1]=this.arr[i];
            }
            if(validInput(n)==true){
                this.arr[position]=n;
                this.numElements++;
                this.computeStatistics();
                return true;
            }else{
                this.computeStatistics();
                return false;
            }
        }
        
     }
     //a method to remove the smallest element in the array.
     public int removeSmallest(){
         int smallestIndex=0;
         if (this.numElements==0){
             return -1;
         } else{
             for (int i=1;i<this.numElements;i++){
                 if (this.arr[i]<this.arr[smallestIndex]){
                     smallestIndex=i;
                 }
             }
             int smallest=this.arr[smallestIndex];
             for (int i=smallestIndex;i<this.numElements-1;i++){
                 this.arr[i]=this.arr[i+1];      
             }
             this.numElements--;
             this.computeStatistics();
             return smallest;
         }
      }
     //a method to grow the physical array by some additional size n
     public boolean grow(int n) {
         if (n<1){
             return false;
         } else{
             int[] newArray = new int[this.arr.length + n];
             for (int i = 0; i < this.arr.length; i++){
                 newArray[i]= this.arr[i];
             }
         this.arr=newArray;
         this.computeStatistics();;
         return true;
         }
     }
     //a method to shrink the physical array to be exactly the same length as the number of elements currently in the array
     public boolean shrink(){
         if (this.arr.length==this.numElements || numElements==0|| arr==null){
             return false;
         } else{
             int[] newArray = new int[this.numElements];
             for (int i = 0; i < this.numElements; i++){
                 newArray[i]= this.arr[i];
             }
             this.arr= newArray;
             this.computeStatistics();
             return true;
         }
     }
     //An accessor method method to retrieve the sum 
     public int getSum() {
         return this.sum;
     }
     //An accessor method method to retrieve the minimum value
     public int getMin() {
         return this.min;
     }
     //An accessor method method to retrieve the maximum value
     public int getMax() {
         return this.max;
     }
     //An accessor method method to retrieve the average of the values
     public double getAvg() {
         return this.avg;
     }
     //An accessor method method to retrieve the array, arr
     public int[] getArr() {
         return this.arr;
     }
     //An accessor method method to retrieve the # of elements in arr
     public int getNumElements() {
         return this.numElements;
     }
      // return the current physical size (i.e. length) of the object's array
     public int getArrLength() {
         return this.arr.length;
     }
     //creates and returns s string which is a histogram like asterisk reprenstation of the array
     public String computeHistogram(){
         String result= "";
         for (int n:this.arr){
             if (n!=0){
                 for (int j = 0; j < n; j++){
                     result+="*";
                 }
             }
             result+="\n";
         }
         return result;
     }
     public static void main(String [] args) {
 
         System.out.println("\nUnit Test for MyArray.\n");
 
     // Fill in your unit tests
     MyArray m= new MyArray(10);
     m.inputElements();
     System.out.println(Arrays.toString(m.getArr()));
     m.shrink();
     System.out.println(Arrays.toString(m.getArr()));
     
     }
 }