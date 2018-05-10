package com.imooc.recursion;

public class Solution3 {

    /**
     * �ݹ�ʵ��leetCode�ϵ�203��
     * �ݹ�ɾ�������ڵ�
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {

        //�������ڵ�Ϊ��,�򷵻ؿ�
        if (head == null){
            return null;
        }

        /**
         * �ݹ���������,����ɾ������
         * ��������ֵ���ڴ���Ҫɾ����ֵ,������һ��
         * �����е��ɻ����������
         */
        head.next = removeElements(head.next,val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 4, 5, 6};
        ListNode listNode = new ListNode(arr);
        System.out.println(listNode);

        Solution3 solution3 = new Solution3();
        solution3.removeElements(listNode, 6);
        System.out.println(listNode);
    }
}