package com.liukai.pattern.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 类名：访问者模式
 * 描述：访问者模式的目的是封装一些施加于某种数据结构元素之上的操作，一旦这些操作需要修改的话，接受这个操作的数据结构则可以保持不变。
 * Created by Administrator on 2017/1/22 0022.
 */
public class Client {

    public static void main(String[] args) {
        //创建结构对象
        ObjectStructure objectStructure = new ObjectStructure();
        //创建节点对象
        Node nodeA = new NodeA();
        Node nodeB = new NodeB();
        //为结构对象添加节点元素
        objectStructure.add(nodeA);
        objectStructure.add(nodeB);
        //创建访问者对象
        Visitor visitor = new VisitorA();
        //执行访问操作
        objectStructure.action(visitor);
    }
}


/**
 * 类名：抽象访问者角色
 * 描述：定义访问方法，定义方法名相同，参数不同的重载方法（静态分派）
 */
interface Visitor {

    /**
     * 方法名：访问节点A的方法
     *
     * @param nodeA
     */
    void visit(NodeA nodeA);

    /**
     * 方法名：访问节点B的方法
     *
     * @param nodeB
     */
    void visit(NodeB nodeB);
}

/**
 * 类名：缺省访问者适配器
 * 描述：提供了抽象访问者类中各个接口的的默认实现
 */
abstract class DefaultAdapterVisitor implements Visitor {

    /**
     * 方法名：访问节点A的
     * 描述：缺省实现
     *
     * @param nodeA
     */
    public void visit(NodeA nodeA) {
    }

    /**
     * 方法名：访问节点B的
     * 描述：缺省实现
     *
     * @param nodeB
     */
    public void visit(NodeB nodeB) {
    }

}

/**
 * 类名：具体访问者A
 * 描述：继承缺省访问者适配器类，并重写访问方法
 */
class VisitorA extends DefaultAdapterVisitor {

    /**
     * 方法名：访问节点A的方法
     *
     * @param nodeA
     */
    @Override
    public void visit(NodeA nodeA) {
        System.out.println(nodeA.operationA());
    }

    /**
     * 方法名：访问节点B的方法
     *
     * @param nodeB
     */
    @Override
    public void visit(NodeB nodeB) {
        System.out.println(nodeB.operationB());
    }

}


/**
 * 类名：抽象节点对象
 * 描述：定义了接受访问者对象的方法
 */
interface Node {

    /**
     * 方法名：接受访问者方法
     *
     * @param visitor
     */
    void accept(Visitor visitor);

}

/**
 * 类名：具体节点A
 * 描述：实现抽象节点所声明的方法
 */
class NodeA implements Node {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * 方法名：A特有的商业方法
     */
    public String operationA() {
        return "NodeA is visited!";
    }
}

/**
 * 类名：具体节点B
 * 描述：实现抽象节点所声明的方法
 */
class NodeB implements Node {

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    /**
     * 方法名：B特有的商业方法
     *
     * @return
     */
    public String operationB() {
        return "NodeB is visited!";
    }

}

/**
 * 类名：结构对象角色。
 * 描述：
 */
class ObjectStructure {

    private List<Node> nodes = new ArrayList<>();

    /**
     * 方法名：执行访问操作
     * 描述：此方法遍历每一个节点对象，并调用其
     *
     * @param visitor
     */
    public void action(Visitor visitor) {
        for (Node node : nodes) {
            node.accept(visitor);
        }
    }

    /**
     * 方法名：增加节点
     *
     * @param node 节点
     */
    public void add(Node node) {
        nodes.add(node);
    }

}