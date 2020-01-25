#include<bits/stdc++.h>
using namespace std;
#define LL long long
const int MAXN=300005;
const int inf=1<<29;
int bit[MAXN],n,a[MAXN];
int lowbit(int x)
{
    return x&(-x);
}
void add(int i,int x)
{
    while(i<=n)
    {
        bit[i]+=x;
        i+=lowbit(i);
    }
}
void sub(int i,int x)
{
    while(i<=n)
    {
        bit[i]-=x;
        i+=lowbit(i);
    }
}
int sum(int i)
{
    int s=0;
    while(i>0)
    {
        s+=bit[i];
        i-=lowbit(i);
    }
    return s;
}
int main()
{
    int t;
    scanf("%d",&t);
    for(int tt=1;tt<=t;tt++)
    {
        printf("Case %d:\n",tt);
        scanf("%d",&n);
        memset(bit,0,sizeof bit);
        for(int i=1;i<=n;i++)
        {
            scanf("%d",&a[i]);
            add(i,a[i]);
        }
        char str[10];
        while(scanf("%s",str) && str[0]!='E')
        {
            if(str[0]=='Q')
            {
                int u,v;
                scanf("%d%d",&u,&v);
                printf("%d\n",sum(v)-sum(u-1));
            }
            else if(str[0]=='A')
            {
                int u,v;
                scanf("%d%d",&u,&v);
                add(u,v);
            }
            else if(str[0]=='S')
            {
                int u,v;
                scanf("%d%d",&u,&v);
                sub(u,v);
            }
        }
    }
    return 0;
}
 