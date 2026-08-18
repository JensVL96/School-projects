#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <time.h>
#define NumItems 1000000
#define MaxNum 100000000

// Declaring that assembly function is provided elsewhere
extern void asm_function(int *a);

/*
 * Prints the array by iteration
 */
int printArray(int A[], int size)
{
    int i;
    for (i=0; i < size; i++)
        printf("%d ", A[i]);
    printf("\n");
}

/*
 * Compares the two given arrays and places the numbers in order by their value
 */
int merge(int arr[], int l, int m, int r)
{
    int i, j, k;
    int n1 = m - l + 1;
    int n2 =  r - m;

    int L[n1], R[n2];

    for (i = 0; i < n1; i++)
        L[i] = arr[l + i];
    for (j = 0; j < n2; j++)
        R[j] = arr[m + 1+ j];

    i = 0;
    j = 0;
    k = l;
    while (i < n1 && j < n2)
    {
        if (L[i] <= R[j])
        {
            arr[k] = L[i];
            i++;
        }
        else
        {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1)
    {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2)
    {
        arr[k] = R[j];
        j++;
        k++;
    }
}

/*
 * Calls recursively on itself to split the array, then passes it on to merge
 */
int mergeSort(int arr[], int l, int r)
{
    if (l < r)
    {
        int m = l+(r-l)/2;

        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);

        merge(arr, l, m, r);
    }
}

/*
 * Calls recursively on itself to split the array, then passes it on to the assembly function
 */
int mergeSortasm(int arr[], int l, int r)
{
    if (l < r)
    {
        int m = l+(r-l)/2;

        mergeSort(arr, l, m);
        mergeSort(arr, m+1, r);

        asm_function(arr);
    }
}
/*
Ensures that a array is sorted. 
*/
int assert_sorted(int *a, int s) {

    for(int i = 1; i < s; i++) {
        if(a[i] < a[i-1])
        {
            printf("a[%d] %d <  a[%d] %d\n", i, a[i], (i-1), a[i-1]);
            puts("ARRAY NOT SORTED");
            exit(-1);
        }
    }

    puts("SORTED");
}

/*
 * Creates an array and sends it to be sorted through two different functions. 
 * Times and prints the array during the process.
 */
int main()
{
    int *q = malloc(sizeof(int) * NumItems);
    int *temp = malloc(sizeof(int) * NumItems);

    srand(NumItems);

    for (int i = 0; i < NumItems; i++) {
        q[i] = rand() % MaxNum;
        temp[i] = q[i];
    }

    int *r = q;

    printf("Given array is \n");                                        
    //printArray(q, NumItems);                                            // prints the starting unordered array


    clock_t beginc = clock();

    mergeSort(r, 0, NumItems - 1);
    clock_t endc = clock();
    double time_spent_c = (double)(endc - beginc) / CLOCKS_PER_SEC;
    printf("\nc-code time spent: %f\n\n", time_spent_c);
    assert_sorted(r,  NumItems);

    printf("\nc-code sorted array is \n");
    //printArray(q, NumItems);                                            // prints the array sorted with c


    clock_t beginasm = clock();

    mergeSortasm(temp, 0, NumItems - 1);
    assert_sorted(temp, NumItems);
    clock_t endasm = clock();
    double time_spent_asm = (double)(endasm - beginasm) / CLOCKS_PER_SEC;
    printf("\nassembly time spent: %f\n\n", time_spent_asm);

    printf("\nasm sorted array is \n");
    //printArray(q, NumItems);                                            // prints the array sorted with assembly

    free(q);
    free(temp);
    return 0;
}