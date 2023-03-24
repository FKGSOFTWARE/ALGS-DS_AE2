public class FrequencyStack {

    class Node {
        int value;
        int frequency;
        Node next;
    }

    class StackNode {
        int value;
        StackNode next;
    }

    private Node frequencyList;
    private StackNode[] stacks;
    private int maxSize;

    public FrequencyStack(int maxSize) {
        this.frequencyList = null;
        this.stacks = new StackNode[maxSize + 1];
        this.maxSize = maxSize;
    }

    public void push(int value) {
        Node existingNode = findNode(value);
        if (existingNode == null) {
            existingNode = new Node();
            existingNode.value = value;
            existingNode.frequency = 1;
            existingNode.next = frequencyList;
            frequencyList = existingNode;
        } else {
            existingNode.frequency++;
        }
        int freq = existingNode.frequency;

        StackNode newStackNode = new StackNode();
        newStackNode.value = value;
        newStackNode.next = stacks[freq];
        stacks[freq] = newStackNode;
    }

    public int pop() {
        int maxFreq = findMaxFrequency();
        if (maxFreq == 0) {
            return Integer.MIN_VALUE;
        }

        StackNode maxFreqStack = stacks[maxFreq];
        int poppedValue = maxFreqStack.value;
        stacks[maxFreq] = maxFreqStack.next;

        Node existingNode = findNode(poppedValue);
        if (existingNode != null) {
            existingNode.frequency--;
            if (existingNode.frequency == 0) {
                removeNode(poppedValue);
            }
        }

        return poppedValue;
    }

    private Node findNode(int value) {
        Node current = frequencyList;
        while (current != null) {
            if (current.value == value) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    private void removeNode(int value) {
        if (frequencyList == null) {
            return;
        }
        if (frequencyList.value == value) {
            frequencyList = frequencyList.next;
            return;
        }
        Node prev = frequencyList;
        Node current = prev.next;
        while (current != null) {
            if (current.value == value) {
                prev.next = current.next;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    private int findMaxFrequency() {
        int maxFreq = 0;
        for (int i = 1; i <= maxSize; i++) {
            if (stacks[i] != null) {
                maxFreq = i;
            }
        }
        return maxFreq;
    }

    public static void main(String[] args) {
        FrequencyStack stack = new FrequencyStack(5);
        stack.push(1);
        stack.push(2);
        stack.push(2);
        stack.push(3);
        stack.push(3);
        stack.push(3);
        stack.push(4);
        stack.push(4);
        stack.push(4);
        stack.push(4);
        stack.push(5);
        stack.push(5);
        stack.push(5);
        stack.push(5);
        stack.push(5);
    
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
        System.out.println(stack.pop()); 
    }
}