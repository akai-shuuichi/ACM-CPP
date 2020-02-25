#include <bits/stdc++.h>
using namespace std;
struct List {
	int val;
	List* next, * pre;
	List(int d) :val(d), next(NULL), pre(NULL) {}
	List() :val(0), next(NULL),pre(NULL){}


	void insertpre(List* temp) {//插到前面
		temp->next = this;
		temp->pre = pre;
		pre->next = temp;
		pre = temp;
	}

	void insertnext(List* temp) {//插到后面
		temp->pre = this;
		temp->next = this->next;
		next->pre = temp;
		this->next = temp;
	}
	int del() {
		pre->next = next;
		next->pre = pre;
		return this->val;
	}
};
int listins(List* head, int pos, int val) {//在pos前插入元素
	if (pos<1 || pos>head->val)return -1;
	head->val++;
	int i = 1;
	auto t = new List(0);
	t->val = val;
	for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
		if (i == pos) {
			tp->insertpre(t);
		}
	}
	return 1;
}
int listdel(List* head, int pos) {//删除第i个元素
	if (pos<1 || pos>head->val)return -1;
	head->val--;
	int i = 1, ans;
	for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
		if (i == pos) {
			tp->del();
			ans = tp->val;
		}
	}
	return ans;
}
int listupdate(List* head, int pos, int val) {//把第i个元素更新
	if (pos<1 || pos>head->val)return -1;
	int i = 1;
	for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
		if (i == pos) {
			tp->val = val;
		}
	}
	return 1;
}
void create(List* head, int len) {//创建长度为len的链表
	head->val = len;
	head->pre = NULL;
	List* p = head;
	for (int i = 1; i <= len; i++) {
		List* temp = new List(i);
		p->next = temp;
		temp->pre = p;
		p = p->next;
		p->next = NULL;
	}
}
void outlist(List* head) {//输出链表
	for (List* tp = head->next; tp; tp = tp->next) {
		cout << tp->val << " ";
	}
	cout << endl;
}
int listget(List* head, int pos) {//获取第pos个元素
	if (pos<1 || pos>head->val)return -1;
	int i = 1;
	for (auto tp = head->next; i <= pos; i++, tp = tp->next) {
		if (i == pos) {
			return (tp->val);
		}
	}
	return 1;
}
void listsort(List* start, List* end = NULL)
{
	if (start == NULL || start == end) return;

	List* p1 = start;
	List* p2 = start->next;

	while (p2 != end) {
		if (p2->val < start->val) {
			p1 = p1->next;
			swap(p1->val, p2->val);
		}
		p2 = p2->next;
	}
	swap(p1->val, start->val);

	listsort(start, p1);
	listsort(p1->next, end);
}
int main() {
	List* head = new List(0);
	create(head, 10);//创建一个长度为10的链表
	listupdate(head, 5, 424);//把第5个更新
	listdel(head, 3);//删掉第三个
	listins(head, 4, 666);//在第四个前插入值
	outlist(head);
	listsort(head->next);//快排
	outlist(head);//输出链表

}

