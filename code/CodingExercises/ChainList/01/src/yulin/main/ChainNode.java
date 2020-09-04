package yulin.main;

public class ChainNode<E> {
    private E value;
    private ChainNode<E> next;

    public void addToTail( E value) {
        if(value==null){
            return;
        }
        ChainNode<E> end = new ChainNode<>(value, null);
        ChainNode<E> temp = this;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(end);
    }

    public void addChainToTail(ChainNode<E> chain2){

        //TODO: fix the problem!
        if (chain2 == null) {
            return;
        }
        ChainNode<E> temp = this;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(chain2);
    }

    public void deleteLast() {
        ChainNode<E> temp = this;
        while (temp.getNext().getNext() != null) {
            temp = temp.getNext();
        }
        temp.setNext(null);
    }
    public ChainNode(E i, ChainNode<E> next) {
        this.value = i;
        this.next = next;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public ChainNode<E> getNext() {
        return next;
    }

    public void setNext(ChainNode<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "ChainNode{" + value +
                ", " + next +
                '}';
    }
}
