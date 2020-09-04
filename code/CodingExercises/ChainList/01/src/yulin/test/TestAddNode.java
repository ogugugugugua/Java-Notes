package yulin.test;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import yulin.main.ChainNode;

public class TestAddNode {
    @Test
    public void TestCreation(){
        ChainNode<Integer> chain = new ChainNode<>(1, null);
        System.out.println(chain);
    }

    @Test
    public void TestAddToTail(){
        ChainNode<Integer> chain = new ChainNode<>(1, null);
        chain.addToTail(2);
        chain.addToTail(3);
        chain.addToTail(-1);
        System.out.println(chain);
    }

    @Test
    public void TestDeleteLast(){
        ChainNode<Integer> chain = new ChainNode<>(1, null);
        chain.addToTail(2);
        chain.addToTail(3);
        chain.addToTail(-1);
        System.out.println(chain);
        chain.deleteLast();
        System.out.println(chain);
    }

    @Test
    public void TestAddChainToTail(){
        ChainNode<Integer> chain1 = new ChainNode<>(1, null);
        chain1.addToTail(2);
        chain1.addToTail(3);
        chain1.addToTail(-1);
        System.out.println(chain1);

        ChainNode<Integer> chain2 = new ChainNode<>(199, null);
        chain2.addToTail(6);
        chain2.addToTail(7);
        chain2.addToTail(-88);
        System.out.println(chain2);

        chain1.addChainToTail(chain2);
        ChainNode<Integer> chain3 = chain1;
        System.out.println(chain3);

        chain2.addChainToTail(chain1);
        ChainNode<Integer> chain4 = chain2;
//        System.out.println(chain4);
    }
}
