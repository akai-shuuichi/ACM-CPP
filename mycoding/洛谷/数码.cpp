#pragma GCC optimize(2)
#include<bits/stdc++.h>
using namespace std;
int num[10] = { 1 };
void solve(int a[],int up) {
	int lim = sqrt(up);
	for (int i = 1; i <= lim; i++) {
		int other = up / i;
		a[other / num[(lower_bound(num, num + 10, other) - num)]]++;
		a[i / num[(lower_bound(num, num + 10, i) - num)]]++;
	}


}
int main() {
	for (int i = 1; i < 10; i++)num[i] = num[i - 1] * 10;
	int l, r,a[10]={0},b[10]={0};
	cin >> l >> r;
	solve(a, l - 1), solve(b, r);
	for (int i = 0; i < 10; i++) {
		cout << b[i] - a[i] << endl;;
	}




}