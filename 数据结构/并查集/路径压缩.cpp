#include <bits/stdc++.h>
 
using namespace std;
const int maxn=1e5+7;
int pre[maxn];
 
int Find(int x)
{
    int p,tmp;
    p=x;
    while(x!=pre[x])
        x=pre[x];
    while(p!=x)
    {
        tmp=pre[x];
        pre[x]=x;
        p=tmp;
    }
    return x;
}
 
void join(int x,int y)
{
    int fx=Find(x);
    int fy=Find(y);
    if(fx!=fy)
        pre[fx]=fy;
}
 
bool Same(int x,int y)
{
    return Find(x)==Find(y);
}
 
int main()
{
    int n,m;
    scanf("%d%d",&n,&m);
    for(int i=1;i<=n;i++)
        pre[i]=i;
    for(int i=1;i<=m;i++)
    {
        int a,b;
        scanf("%d%d",&a,&b);
        join(a,b);
    }
    for(int i=1;i<=n;i++)
        printf("%d ",Find(i));
    printf("\n");
    return 0;
}