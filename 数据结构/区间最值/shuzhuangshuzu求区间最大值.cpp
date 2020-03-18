#include<cstdio>
#include<iostream>
#include<cstring>
#include<cmath>
using namespace std;
const int maxn=300005;
int h[maxn],a[maxn];
int n,m;
int lowbit(int x)
{
    return x&(-x);
}
void update(int x)
{
    while(x<=n)
    {
        h[x]=a[x];
        for(int i=1;i<lowbit(x);i<<=1)
        h[x]=max(h[x],h[x-i]);
        x+=lowbit(x);
    }
    return ;
}
void findans(int begin,int end)
{
    int ans=0;
    while(end>=begin)
    {
        ans=max(ans,a[end]);
        end--;
        for(;end-lowbit(end)>=begin;end-=lowbit(end))
        ans=max(ans,h[end]);
    }
    
    printf("%d\n",ans);
    return ;
}
int main()
{

    while(scanf("%d%d",&n,&m)==2)
    {
        memset(h,0,sizeof(h));
        for(int i=1;i<=n;i++)
        {
            scanf("%d",&a[i]);
            update(i);
        }
        for(int i=1;i<=m;i++)
        {
            char c=getchar();
            while(c!='Q'&&c!='U')c=getchar();
            if(c=='U')//update
            {
                int y,z;
                scanf("%d%d",&y,&z);
                a[y]=z;
                update(y);
                continue;    
            }
            if(c=='Q')//findans
            {
                int y,z;
                scanf("%d%d",&y,&z);
                findans(y,z);
                continue;
            }
        }
    }
    
    return 0;
}