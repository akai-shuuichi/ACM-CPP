#include <bits/stdc++.h>
using namespace std;
const int maxn=10000000;
bool vis[maxn];
int prime[maxn],x;
void isprime(int n) //埃氏筛
{
    for(int i=2;i<=n;i++)
    {
        if(!vis[i]) prime[x++]=i;
        for(int j=2;j*i<=n;j++)
        {
            vis[i*j]=true;
        }
    }
}
int main()
{
    int n;
    scanf("%d",&n);
    isprime(n);
    for(int i=0;i<x;i++)//输出n以内的所有素数
        printf("%d\n",prime[i]);
    return 0;
}