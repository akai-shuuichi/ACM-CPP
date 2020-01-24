#include<bits/stdc++.h>
using namespace std;
int expand(string s, int left, int right)
{
    int l = left;
    int r = right;
    while(s[l]&&s[r] && s[l] == s[r])
    {
        l--;
        r++;
    }
    return r - l - 1;
}
string findLongestPalindrome(string s)//返回值为最长回文子串
{
    if(s == "" || s.length() < 1)
        return "";
    int start = 0;//start是最长回文子串的开始位置
    int end = 0;//end是最长回文子串的结束位置
    int len = 0;
    for(int i = 0; i < s.length(); i++)
    {
        int len1 = expand(s, i, i);
        int len2 = expand(s, i, i+1);
        len = max(len1, len2);
        if(len > end - start)//更新最长回文子串的开始和结束位置
        {
            start = i - (len - 1)/2;
            end = i + len/2;
        }
    }
    return s.substr(start,end-start+1);//取从start到end的子串就是结果
}

int main(){
string from;
cin>>from;
cout<<findLongestPalindrome(from);
}