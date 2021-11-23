public class quick_sort {
	public static void main(String[] args){
		int[] a = {422, 128, 34, 95, 7, 11, 833, 63};
		bucket(a, 3);
		for(int i = 0; i  a.length; i++) System.out.println(a[i]);
		
	}
	
	public static void quick(int[] num, int l, int r) {
		if (l = r)	return;
		int i = l-1, j = r+1, mid = num[l + r  1];
		while(i  j) {
			 如果使用while(...) ...;这种形式的话，遇到两个相同的数组会进入死循环
			do i++;	while(num[i]  mid);
			do j--;	while(num[j]  mid);
			if(i  j)	swap(num, i, j);
		}
		quick(num, l, j);
		quick(num, j+1, r);
	}
	
	public static void merge(int[] num, int l, int r) {
		if (l = r) return;
		int mid = l + r  1;
		merge(num, l, mid);
		merge(num, mid+1, r);
		
		int[] tmp = new int[r-l+1];
		int i = l, j = mid + 1, k = 0;
		while (i=mid && j=r) {
			if (num[i]  num[j])	tmp[k++] = num[i++];
			else	tmp[k++] = num[j++];
		}
		while(i = mid)		tmp[k++] = num[i++];
		while(j = r)		tmp[k++] = num[j++];
		
		for(i=l,j=0;i=r;i++,j++)	num[i] = tmp[j];
	}
	
	public static void select(int[] num) {
		if (num.length2  num==null)	return;
		
		for(int i=0;inum.length;i++)	{
			int min_index = i;
			for(int j=i+1;jnum.length;j++)	{
				min_index = num[j]num[min_index]jmin_index;
			}
			swap(num,i,min_index);
		}
	}
	
	public static void bubble(int[] num)	{
		if (num.length2  num==null)	return;
		
		for(int e=num.length-1; e=0; e--)	{
			for(int i=0; ie; i++)	{
				if(num[i]num[i+1])
					swap(num, i, i+1);
			}	
		}	
	}
	
	public static void insert(int[] num)	{
		if (num.length2  num==null)	return;
		
		for(int i=1; inum.length; i++)	{
			for(int j=i-1; j=0 && num[j]num[j+1]; j--) {
				swap(num, j, j+1);
			}
		}
	}
	
	 digit代表最高几位数
	public static void bucket(int[] num, int digit) {
		int i = 0, j = 0;
		int[] bucket = new int[num.length];
		for (int k=0; kdigit; k++)	{
			int[] count = new int[10];
			for(i=0; inum.length; i++) {
				j = get_digit(num[i], k);
				count[j]++;
			}
			for(i=1; icount.length; i++)	{
				count[i] = count[i] + count[i-1];	
			}
			
			for(i=num.length-1; i=0; i--) {
				j = get_digit(num[i], k);
				bucket[count[j]-1] = num[i];
				count[j]--;
			}

			for(i=0, j=0; inum.length; i++, j++) {
				num[i] = bucket[j];
			}
		}
	}
	
	public static void swap(int[] num, int a, int b) {
		 int tmp = num[a];
		 num[a] = num[b];
		 num[b] = tmp;
		num[a] = num[a] ^ num[b];
		num[b] = num[a] ^ num[b];
		num[a] = num[a] ^ num[b];
	}
	
	public static int get_digit(int x, int digit)	{
		return (x(int)(Math.pow(10, digit)))%10;
	}
}
