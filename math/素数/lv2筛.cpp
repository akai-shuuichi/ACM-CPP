#include <bits/stdc++.h>
using namespace std;
const int maxn=10000000;
bool vis[maxn];
int prime[maxn],x;
void oulasai(int n)  //欧拉筛,比lv1更快（快个log）
{
    for(int i=2;i<=n;i++)
    {
        if(!vis[i]) prime[x++]=i;
        for(int j=0;j<x;j++)
        {
            if(i*prime[j]>n) break;
            vis[i*prime[j]]=true;
            if(i%prime[j]==0) break;
        }
    }
}
int main()
{
    int n;
    scanf("%d",&n);
    oulasai(n);
    cout<<x;
    for(int i=0;i<x;i++)//输出n以内的所有素数
      //  printf("%d\n",prime[i]);
    return 0;
}