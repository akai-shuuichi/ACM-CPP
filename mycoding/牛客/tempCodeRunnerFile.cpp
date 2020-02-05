#pragma GCC optimize(2)
#include<bits/stdc++.h>
using namespace std;
int mod = 1e9 + 7;
struct mat
{
	long long val[2][2];
	mat mul(mat x)
	{
		mat tp = { 0 };
		for (int i = 0; i < 2; ++i)
		{
			for (int j = 0; j < 2; ++j)
			{
				tp.val[i][j] += (val[i][0] * x.val[0][j] + val[i][1] * x.val[1][j]) % mod;
			}
		}
		return tp;
	}
	void me()
	{
		val[0][0] = val[1][1] = 1;
	}
};

inline mat matpow(mat a, int n)
{
	mat ans = { 0 };
	ans.val[0][0] = ans.val[1][1] = 1;

	while (n)
	{
		if (n & 1)
		{
			ans = ans.mul(a);
		}
		a = a.mul(a);
		n >>= 1;
	}
	return ans;
}
const int man = 1e6 + 7;
char temp[man];
mat niubipow(mat tp, int len)
{
	mat ans = { 0 };
	ans.val[0][0] = ans.val[1][1] = 1;
	for (register int i = len - 1; i >= 0; --i)
	{
		int tlen = temp[i] - '0';
		if (tlen)
			ans = ans.mul(matpow(tp, tlen));
		tp = matpow(tp, 10);
	}
	return ans;
}
long long get(long long n) {
	int up = sqrt(n),ans=0;
	for (int i = 1; i <= up; i++) {
		if (i * i != n) {
			if (n % i == 0)ans += 2;

		}
		else if (n % i == 0)ans++;
	}
	return ans;

}

char str[200010];
int up[26][200010],pos[26];
int main(){
int a,b;
cin>>b>>a;
long long ans=0;
ans+=((((a-1)*a)%mod)*(b-2)%mod)+((a-2)*(a-2)%mod)*(b-1)%mod;
swap(a,b);
ans+=((((a-1)*a)%mod)*(b-2)%mod)+((a-2)*(a-2)%mod)*(b-1)%mod;
cout<<2*ans;
}


// 3 2 3     6     2：1     3：1
// 4 2 3    18     2：1     3：2
// 5 2 3    108    2 2         3
// a：0 0 1 .。。fib
// b：fib+1