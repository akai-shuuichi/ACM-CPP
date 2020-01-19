#include<bits/stdc++.h>
using namespace std;
const int maxn=1e7;
int E[maxn];
void eulershai()  
{  
    for(int i=2;i<maxn;i++){  
        if(!E[i])  
        for(int j=i;j<maxn;j+=i){  
            if(!E[j])E[j]=j;  
            E[j]=E[j]/i*(i-1);  
        }  
}
}
  
int main(){
    eulershai();
int n;
cin>>n;
cout<<E[n];

}