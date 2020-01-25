#include<bits/stdc++.h>
using namespace std;
const int maxn = 5e5+5;
typedef long long ll;
const ll mod = 1e9+7;
int Case = 1;
int n, m;
struct node{
    int x, y, k, pos, op;
}cc[maxn], t1[maxn], t2[maxn];
int res[maxn];
struct Tree{
    int c[maxn], up;
    int lowbit(int x) {
        return (x&(-x));
    }
    void update(int pos, int val) {
        for(int i = pos; i <= up; i += lowbit(i)) {
            c[i] += val;
        }
    }
    int query(int pos) {
        int res  = 0;
        for(int i = pos; i >= 1; i -= lowbit(i)) {
            res += c[i];
        }
        return res;
    }
}tree;
void cal(int L, int R, int l, int r) {
    if(l>r || L>R) return;
    if(L==R)
    {
        for(int i=l;i<=r;i++) if(cc[i].op) res[cc[i].pos]=L;
        return;
    }
    int mid=(L+R)>>1, cnt1=0, cnt2=0;
    for(int i = l; i <= r; i++) {
        if(cc[i].op) {
            int tmp=tree.query(cc[i].y)-tree.query(cc[i].x-1);
            if(tmp>=cc[i].k) t1[++cnt1]=cc[i];
            else cc[i].k-=tmp, t2[++cnt2]=cc[i];
        }
        else {
            if(cc[i].x<=mid) t1[++cnt1]=cc[i], tree.update(cc[i].pos, cc[i].y);
            else t2[++cnt2]=cc[i];
            
        }
    }
    for(int i=1;i<=cnt1;i++) if(!t1[i].op) tree.update(t1[i].pos, -t1[i].y);
    for(int i=1;i<=cnt1;i++) cc[l+i-1]=t1[i];
    for(int i=1;i<=cnt2;i++) cc[l+cnt1+i-1]=t2[i];
    cal(L, mid, l, l+cnt1-1);cal(mid+1, R, l+cnt1, r);
}
int main() {

scanf("%d", &Case);
while(Case--) {
     scanf("%d%d", &n, &m);
    tree.up = n;
    int cnt = 0;
    for(int i = 1; i <= n; i++) {
        cnt++;
        scanf("%d", &cc[cnt].x);
        cc[cnt].y = 1;cc[cnt].k = 0;cc[cnt].pos = i;cc[cnt].op = 0;
    }
    for(int i = 1; i <= m; i++) {
        cnt++;
        scanf("%d%d%d", &cc[cnt].x, &cc[cnt].y, &cc[cnt].k);
        cc[cnt].pos = i;cc[cnt].op = 1;
    }
    cal(-1e9, 1e9, 1, cnt);
    for(int i = 1; i <= m; i++) printf("%d\n", res[i]);
    
    }
return 0;
}
