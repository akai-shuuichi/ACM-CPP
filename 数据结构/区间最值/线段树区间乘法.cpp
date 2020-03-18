#include<bits/stdc++.h>
using namespace std;
const int maxn = 1e5 + 7;
//总结 先乘后加
int mod ;
#define l(x) x<<1
#define r(x) x<<1|1
struct Tree{
	int l, r;
	long long val;
	long long mul;
	long long add;
}T[maxn<<2];
int num[maxn];
inline int read() {
	int x = 0, f = 1;
	char ch = getchar();
	while (ch < '0' || ch>'9') {
		if (ch == '-')
			f = -1;
		ch = getchar();
	}
	while (ch >= '0' && ch <= '9') {
		x = (x << 1) + (x << 3) + (ch ^ 48);
		ch = getchar();
	}
	return x * f;
}
void build(int pos, int l, int r) {
	T[pos].l = l, T[pos].r = r, T[pos].mul = 1;;
	int mid = (T[pos].l + T[pos].r) >> 1;
	if (l == r) {
		T[pos].val = num[mid];
		return;
	}
	build(pos<<1, l, mid);
	build(pos<<1|1, mid + 1, r);
	T[pos].val = (T[pos<<1].val + T[pos <<1|1].val)%mod;


}
inline void addjudge(int pos) {
	T[l(pos)].val = (T[pos].mul * T[l(pos)].val + T[pos].add * (T[l(pos)].r - T[l(pos)].l + 1ll)) % mod;
		T[r(pos)].val = (T[pos].mul * T[r(pos)].val + T[pos].add * (T[r(pos)].r - T[r(pos)].l + 1ll)) % mod;//先乘再加
		T[l(pos)].mul = (T[pos].mul*T[l(pos)].mul)%mod;
		T[r(pos)].mul = (T[pos].mul*T[r(pos)].mul)%mod;
		T[l(pos)].add = (1ll*T[l(pos)].add * T[pos].mul + T[pos].add)%mod;
		T[r(pos)].add = (1ll* T[r(pos)].add* T[pos].mul + T[pos].add)%mod;
		T[pos].mul = 1;
		T[pos].add = 0;

}
void updateadd(int pos, int l, int r,int val) {
	if (T[pos].l >= l && T[pos].r <= r) {
		T[pos].val =(T[pos].val+val*(T[pos].r-T[pos].l+1ll))%mod;
		T[pos].add += val;
		return;
	}
	addjudge(pos);
	int mid = (T[pos].l+T[pos].r) >> 1;
	if (l <= mid) {
		updateadd(pos<<1, l, r, val);
	}
	if (r > mid) {
		updateadd(pos<<1|1, l, r, val);
	}
	T[pos].val = (T[l(pos)].val + T[r(pos)].val)%mod;
}
void updatemul(int pos, int l, int r, int val) {
	if (T[pos].l >= l && T[pos].r <= r) {
		
		T[pos].mul = (T[pos].mul * val) % mod;
		T[pos].add = (T[pos].add * val) % mod;
		T[pos].val = (val * T[pos].val) % mod;
		return;
	}
	addjudge(pos);
	int mid = (T[pos].l + T[pos].r) >> 1;
	if (l <= mid) {
		updatemul(pos << 1, l, r, val);
	}
	if (r > mid) {
		updatemul(pos << 1 | 1, l, r, val);
	}
	T[pos].val = (T[l(pos)].val + T[r(pos)].val)%mod;

}
long long  query(int pos, int l, int r) {
	
	if (T[pos].l >= l && T[pos].r <= r) {
		
		return T[pos].val ;
	}
	addjudge(pos);
	long long ans = 0;
	int mid = (T[pos].l + T[pos].r) >> 1;
	if (l <= mid) {
		ans=(ans+query(pos<<1, l, r))%mod;
	}
	if (r > mid) {
		ans=(ans+query(pos<<1|1, l, r))%mod;
	}
	return ans;

}
int main() {
	int n, m;
	cin >> n >> m >> mod;
	for (int i = 1; i <= n; i++) {
		scanf("%d", &num[i]);
	}
	build(1, 1, n);
	while (m--) {
		int tp,l,r,val;
		tp = read();
		if (tp == 1) {
			scanf("%d%d%d", &l, &r, &val);
			updatemul(1, l, r, val);
		}
		else if(tp==3) {
			scanf("%d%d", &l, &r);
			printf("%lld\n", query(1, l, r)%mod);
		}
		else {
			scanf("%d%d%d", &l, &r, &val);
			updateadd(1, l, r, val);
		
		}
	}
}